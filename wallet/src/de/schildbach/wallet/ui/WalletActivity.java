/*
 * Copyright the original author or authors.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.schildbach.wallet.ui;

import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.core.VersionedChecksummedBytes;
import org.bitcoinj.wallet.Wallet;

import de.schildbach.wallet.Configuration;
import de.schildbach.wallet.Constants;
import de.schildbach.wallet.R;
import de.schildbach.wallet.WalletApplication;
import de.schildbach.wallet.data.PaymentIntent;
import de.schildbach.wallet.data.WalletLiveData;
import de.schildbach.wallet.service.BlockchainService;
import de.schildbach.wallet.ui.InputParser.BinaryInputParser;
import de.schildbach.wallet.ui.InputParser.StringInputParser;
import de.schildbach.wallet.ui.backup.BackupWalletDialogFragment;
import de.schildbach.wallet.ui.backup.RestoreWalletDialogFragment;
import de.schildbach.wallet.ui.monitor.NetworkMonitorActivity;
import de.schildbach.wallet.ui.preference.PreferenceActivity;
import de.schildbach.wallet.ui.scan.ScanActivity;
import de.schildbach.wallet.ui.send.SendCoinsActivity;
import de.schildbach.wallet.ui.send.SweepWalletActivity;
import de.schildbach.wallet.util.CrashReporter;
import de.schildbach.wallet.util.Nfc;
import de.schildbach.wallet.util.OnFirstPreDraw;
import demo.MockDepositor;
import io.grpc.bverify.IssueReceiptRequest;
import io.grpc.bverify.Receipt;
import io.grpc.bverify.TransferReceiptRequest;
import pki.Account;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Andreas Schildbach
 */
public final class WalletActivity extends AbstractWalletActivity {
    private WalletApplication application;
    private Handler handler = new Handler();

    private ViewModel viewModel;
    private AnimatorSet enterAnimation;
    private View contentView;

    public static enum EnterAnimationState {
        WAITING, ANIMATING, FINISHED
    }

    public static class ViewModel extends AndroidViewModel implements OnFirstPreDraw.Callback {
        private final WalletApplication application;
        private WalletLiveData wallet;
        private MutableLiveData<EnterAnimationState> enterAnimation;
        private boolean doAnimation, globalLayoutFinished, balanceLoadingFinished, addressLoadingFinished,
                transactionsLoadingFinished;

        public ViewModel(final Application application) {
            super(application);
            this.application = (WalletApplication) application;
        }

        public WalletLiveData getWallet() {
            if (wallet == null)
                wallet = new WalletLiveData(application);
            return wallet;
        }

        public MutableLiveData<EnterAnimationState> getEnterAnimation() {
            if (enterAnimation == null)
                enterAnimation = new MutableLiveData<>();
            return enterAnimation;
        }

        public void animateWhenLoadingFinished() {
            doAnimation = true;
            maybeToggleState();
        }

        @Override
        public boolean onFirstPreDraw() {
            globalLayoutFinished = true;
            maybeToggleState();
            return true;
        }

        public void balanceLoadingFinished() {
            balanceLoadingFinished = true;
            maybeToggleState();
        }

        public void addressLoadingFinished() {
            addressLoadingFinished = true;
            maybeToggleState();
        }

        public void transactionsLoadingFinished() {
            transactionsLoadingFinished = true;
            maybeToggleState();
        }

        public void animationFinished() {
            enterAnimation.setValue(EnterAnimationState.FINISHED);
        }

        private void maybeToggleState() {
            final MutableLiveData<EnterAnimationState> enterAnimation = getEnterAnimation();
            if (enterAnimation.getValue() == null) {
                if (doAnimation && globalLayoutFinished)
                    enterAnimation.setValue(EnterAnimationState.WAITING);
            } else if (enterAnimation.getValue() == EnterAnimationState.WAITING) {
                if (balanceLoadingFinished && addressLoadingFinished && transactionsLoadingFinished)
                    enterAnimation.setValue(EnterAnimationState.ANIMATING);
            }
        }
    }

    private static final int REQUEST_CODE_SCAN = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = getWalletApplication();
        final Configuration config = application.getConfiguration();

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        setContentView(R.layout.wallet_content);
        contentView = findViewById(android.R.id.content);
        OnFirstPreDraw.listen(contentView, viewModel);
        enterAnimation = buildEnterAnimation(contentView);

        viewModel.getWallet().observe(this, new Observer<Wallet>() {
            @Override
            public void onChanged(final Wallet wallet) {
                invalidateOptionsMenu();
            }
        });
        viewModel.getEnterAnimation().observe(this, new Observer<EnterAnimationState>() {
            @Override
            public void onChanged(final EnterAnimationState state) {
                if (state == EnterAnimationState.WAITING) {
                    // API level 26: enterAnimation.setCurrentPlayTime(0);
                    for (final Animator animation : enterAnimation.getChildAnimations())
                        ((ValueAnimator) animation).setCurrentPlayTime(0);
                } else if (state == EnterAnimationState.ANIMATING) {
                    reportFullyDrawn();
                    enterAnimation.start();
                    enterAnimation.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(final Animator animation) {
                            viewModel.animationFinished();
                        }
                    });
                } else if (state == EnterAnimationState.FINISHED) {
                    getWindow().getDecorView().setBackground(null);
                }
            }
        });
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            viewModel.animateWhenLoadingFinished();
        else
            viewModel.animationFinished();

        final View exchangeRatesFragment = findViewById(R.id.wallet_main_twopanes_exchange_rates);
        if (exchangeRatesFragment != null)
            exchangeRatesFragment.setVisibility(Constants.ENABLE_EXCHANGE_RATES ? View.VISIBLE : View.GONE);

        if (savedInstanceState == null)
            checkSavedCrashTrace();

        config.touchLastUsed();

        handleIntent(getIntent());

        final FragmentManager fragmentManager = getSupportFragmentManager();
        MaybeMaintenanceFragment.add(fragmentManager);
        AlertDialogsFragment.add(fragmentManager);

        setupDepositor();

    }

    MockDepositor aliceClient;
    public void setupDepositor(){

        String base = "/demos/";
        ArrayList<InputStream> listOfFileInputStream = new ArrayList<>();

        try {
            String host = "hubris.media.mit.edu";
            int port = 50051;
            AssetManager am = getAssets();
//            String fullFilename = "demos/pki/" + filename;
            InputStream inputStream = am.open("demos/pki/df3b507b-31c7-4b07-bea2-4256144c2c41");
            File f = createFileFromInputStream(inputStream, "Alice");
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            Account alice = Account.fromBytes(data);

            inputStream = am.open("demos/pki/e5985074-99c1-4fa6-80bc-dca299b5b12f");
            f = createFileFromInputStream(inputStream, "Alice");
            fis = new FileInputStream(f);
            data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            Account bob = Account.fromBytes(data);

            String model = Build.MODEL;
            log.info(model);
            if (                                                             model.equalsIgnoreCase("SM-N950U1")) {
                aliceClient = new MockDepositor(alice, host, port, this);
                aliceClient.transferAccount = bob;
            }else{
                aliceClient = new MockDepositor(bob, host, port, this);
                aliceClient.transferAccount = alice;
            }
            // create a thread that polls the server and automatically approves any requests
            ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
            exec.scheduleAtFixedRate(aliceClient, 0, 5, TimeUnit.SECONDS);

        }catch (Exception e){
            Log.d("Exception", e.getLocalizedMessage());
            e.printStackTrace();
        }
        log.info("Finished initializing alice");

//        PKIDirectory pki = new PKIDirectory(listOfFileInputStream);

        /**
         * Alice: 59d6dd79-4bbe-4043-ba3e-e2a91e2376ae
         * Bob: b132bbfa-98bc-4e5d-b32d-f78d603600f5
         * Warehouse: 2cd00d43-bf5c-4728-9323-d2ea0092ed36
         */

//
//
//
//        Scanner sc = new Scanner(System.in);
//        sc.nextLine();
//        System.out.println("Press enter to shutdown");
//        sc.close();
//        System.out.println("shutdown");
//        try {
//            aliceClient.shutdown();
//            exec.shutdown();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            throw new RuntimeException("something went wrong trying to shutdown");
//        }
    }



    private File createFileFromInputStream(InputStream inputStream, String filename) {

        try{
            File f = new File(getFilesDir(), filename);
            OutputStream outputStream = new FileOutputStream(f);
            byte buffer[] = new byte[1024];
            int length = 0;

            while((length=inputStream.read(buffer)) > 0) {
                outputStream.write(buffer,0,length);
            }

            outputStream.close();
            inputStream.close();

            return f;
        }catch (IOException e) {
            e.printStackTrace();
            //Logging exception
        }

        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // delayed start so that UI has enough time to initialize
                BlockchainService.start(WalletActivity.this, true);
            }
        }, 1000);
    }

    @Override
    protected void onPause() {
        handler.removeCallbacksAndMessages(null);

        super.onPause();
    }

    private AnimatorSet buildEnterAnimation(final View contentView) {
        final Drawable background = getWindow().getDecorView().getBackground();
        final int duration = getResources().getInteger(android.R.integer.config_mediumAnimTime);
        final Animator splashFadeOut = AnimatorInflater.loadAnimator(WalletActivity.this, R.animator.fade_out_drawable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            splashFadeOut.setTarget(((LayerDrawable) background).getDrawable(1));
        else
            splashFadeOut.setDuration(0); // skip this animation, as there is no splash icon
        final AnimatorSet fragmentEnterAnimation = new AnimatorSet();
        final AnimatorSet.Builder fragmentEnterAnimationBuilder = fragmentEnterAnimation.play(splashFadeOut);

        final View slideInLeftView = contentView.findViewWithTag("slide_in_left");
        if (slideInLeftView != null) {
            final ValueAnimator slide = ValueAnimator.ofFloat(-1.0f, 0.0f);
            slide.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimator animator) {
                    float animatedValue = (float) animator.getAnimatedValue();
                    slideInLeftView.setTranslationX(
                            animatedValue * (slideInLeftView.getWidth() + slideInLeftView.getPaddingLeft()));
                }
            });
            slide.setInterpolator(new DecelerateInterpolator());
            slide.setDuration(duration);
            slide.setTarget(slideInLeftView);
            final Animator fadeIn = AnimatorInflater.loadAnimator(WalletActivity.this, R.animator.fade_in_view);
            fadeIn.setTarget(slideInLeftView);
            fragmentEnterAnimationBuilder.before(slide).before(fadeIn);
        }

        final View slideInRightView = contentView.findViewWithTag("slide_in_right");
        if (slideInRightView != null) {
            final ValueAnimator slide = ValueAnimator.ofFloat(1.0f, 0.0f);
            slide.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimator animator) {
                    float animatedValue = (float) animator.getAnimatedValue();
                    slideInRightView.setTranslationX(
                            animatedValue * (slideInRightView.getWidth() + slideInRightView.getPaddingRight()));
                }
            });
            slide.setInterpolator(new DecelerateInterpolator());
            slide.setDuration(duration);
            slide.setTarget(slideInRightView);
            final Animator fadeIn = AnimatorInflater.loadAnimator(WalletActivity.this, R.animator.fade_in_view);
            fadeIn.setTarget(slideInRightView);
            fragmentEnterAnimationBuilder.before(slide).before(fadeIn);
        }

        final View slideInTopView = contentView.findViewWithTag("slide_in_top");
        if (slideInTopView != null) {
            final ValueAnimator slide = ValueAnimator.ofFloat(-1.0f, 0.0f);
            slide.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimator animator) {
                    float animatedValue = (float) animator.getAnimatedValue();
                    slideInTopView.setTranslationY(
                            animatedValue * (slideInTopView.getHeight() + slideInTopView.getPaddingTop()));
                }
            });
            slide.setInterpolator(new DecelerateInterpolator());
            slide.setDuration(duration);
            slide.setTarget(slideInTopView);
            final Animator fadeIn = AnimatorInflater.loadAnimator(WalletActivity.this, R.animator.fade_in_view);
            fadeIn.setTarget(slideInTopView);
            fragmentEnterAnimationBuilder.before(slide).before(fadeIn);
        }

        final View slideInBottomView = contentView.findViewWithTag("slide_in_bottom");
        if (slideInBottomView != null) {
            final ValueAnimator slide = ValueAnimator.ofFloat(1.0f, 0.0f);
            slide.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(final ValueAnimator animator) {
                    float animatedValue = (float) animator.getAnimatedValue();
                    slideInBottomView.setTranslationY(
                            animatedValue * (slideInBottomView.getHeight() + slideInBottomView.getPaddingBottom()));
                }
            });
            slide.setInterpolator(new DecelerateInterpolator());
            slide.setDuration(duration);
            slide.setTarget(slideInBottomView);
            final Animator fadeIn = AnimatorInflater.loadAnimator(WalletActivity.this, R.animator.fade_in_view);
            fadeIn.setTarget(slideInBottomView);
            fragmentEnterAnimationBuilder.before(slide).before(fadeIn);
        }

        final View levitate = contentView.findViewWithTag("levitate");
        if (levitate != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                final ObjectAnimator elevate = ObjectAnimator.ofFloat(levitate, "elevation", 0.0f,
                        levitate.getElevation());
                elevate.setDuration(duration);
                fragmentEnterAnimationBuilder.before(elevate);
            }
            final Drawable levitateBackground = levitate.getBackground();
            final Animator fadeIn = AnimatorInflater.loadAnimator(WalletActivity.this, R.animator.fade_in_drawable);
            fadeIn.setTarget(levitateBackground);
            fragmentEnterAnimationBuilder.before(fadeIn);
        }

        return fragmentEnterAnimation;
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(final Intent intent) {
        final String action = intent.getAction();

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            final String inputType = intent.getType();
            final NdefMessage ndefMessage = (NdefMessage) intent
                    .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)[0];
            final byte[] input = Nfc.extractMimePayload(Constants.MIMETYPE_TRANSACTION, ndefMessage);

            new BinaryInputParser(inputType, input) {
                @Override
                protected void handlePaymentIntent(final PaymentIntent paymentIntent) {
                    cannotClassify(inputType);
                }

                @Override
                protected void error(final int messageResId, final Object... messageArgs) {
                    dialog(WalletActivity.this, null, 0, messageResId, messageArgs);
                }
            }.parse();
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        if (requestCode == REQUEST_CODE_SCAN) {
            if (resultCode == Activity.RESULT_OK) {
                final String input = intent.getStringExtra(ScanActivity.INTENT_EXTRA_RESULT);

                new StringInputParser(input) {
                    @Override
                    protected void handlePaymentIntent(final PaymentIntent paymentIntent) {
                        SendCoinsActivity.start(WalletActivity.this, paymentIntent);
                    }

                    @Override
                    protected void handlePrivateKey(final VersionedChecksummedBytes key) {
                        if (Constants.ENABLE_SWEEP_WALLET)
                            SweepWalletActivity.start(WalletActivity.this, key);
                        else
                            super.handlePrivateKey(key);
                    }

                    @Override
                    protected void handleDirectTransaction(final Transaction tx) throws VerificationException {
                        application.processDirectTransaction(tx);
                    }

                    @Override
                    protected void error(final int messageResId, final Object... messageArgs) {
                        dialog(WalletActivity.this, null, R.string.button_scan, messageResId, messageArgs);
                    }
                }.parse();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.wallet_options, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        super.onPrepareOptionsMenu(menu);

        final Resources res = getResources();
        final String externalStorageState = Environment.getExternalStorageState();

        menu.findItem(R.id.wallet_options_exchange_rates)
                .setVisible(Constants.ENABLE_EXCHANGE_RATES && res.getBoolean(R.bool.show_exchange_rates_option));
        menu.findItem(R.id.wallet_options_sweep_wallet).setVisible(Constants.ENABLE_SWEEP_WALLET);
        menu.findItem(R.id.wallet_options_restore_wallet)
                .setEnabled(Environment.MEDIA_MOUNTED.equals(externalStorageState)
                        || Environment.MEDIA_MOUNTED_READ_ONLY.equals(externalStorageState));
        menu.findItem(R.id.wallet_options_backup_wallet)
                .setEnabled(Environment.MEDIA_MOUNTED.equals(externalStorageState));
        final Wallet wallet = viewModel.getWallet().getValue();
        if (wallet != null) {
            final MenuItem encryptKeysOption = menu.findItem(R.id.wallet_options_encrypt_keys);
            encryptKeysOption.setTitle(wallet.isEncrypted() ? R.string.wallet_options_encrypt_keys_change
                    : R.string.wallet_options_encrypt_keys_set);
            encryptKeysOption.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.wallet_options_request:
            handleRequestCoins();
            return true;

        case R.id.wallet_options_send:
            handleSendCoins();
            return true;

        case R.id.wallet_options_scan:
            handleScan(null);
            return true;

        case R.id.wallet_options_address_book:
            AddressBookActivity.start(this);
            return true;

        case R.id.wallet_options_exchange_rates:
            startActivity(new Intent(this, ExchangeRatesActivity.class));
            return true;

        case R.id.wallet_options_sweep_wallet:
            SweepWalletActivity.start(this);
            return true;

        case R.id.wallet_options_network_monitor:
            startActivity(new Intent(this, NetworkMonitorActivity.class));
            return true;

        case R.id.wallet_options_restore_wallet:
            handleRestoreWallet();
            return true;

        case R.id.wallet_options_backup_wallet:
            handleBackupWallet();
            return true;

        case R.id.wallet_options_encrypt_keys:
            handleEncryptKeys();
            return true;

        case R.id.wallet_options_preferences:
            startActivity(new Intent(this, PreferenceActivity.class));
            return true;

        case R.id.wallet_options_safety:
            HelpDialogFragment.page(getSupportFragmentManager(), R.string.help_safety);
            return true;

        case R.id.wallet_options_technical_notes:
            HelpDialogFragment.page(getSupportFragmentManager(), R.string.help_technical_notes);
            return true;

        case R.id.wallet_options_report_issue:
            handleReportIssue();
            return true;

        case R.id.wallet_options_help:
            HelpDialogFragment.page(getSupportFragmentManager(), R.string.help_wallet);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void handleReceiptIssue(IssueReceiptRequest receipt){
        Intent receiptIssueIntent = new Intent(this, ReceiptIssueActivity.class);
        receiptIssueIntent.putExtra("receipt", receipt);
        startActivity(receiptIssueIntent);
    }

    public void handleReceiptTransfer(TransferReceiptRequest receipt){
        Intent receiptIssueIntent = new Intent(this, ReceiptTransferActivity.class);
        receiptIssueIntent.putExtra("receipt", receipt);
        startActivity(receiptIssueIntent);
    }

    public static void handleInitiateReceiptTranfer(Receipt receipt){

    }

    public void handleRequestCoins() {
        startActivity(new Intent(this, RequestCoinsActivity.class));
    }

    public void handleSendCoins() {
        startActivity(new Intent(this, SendCoinsActivity.class));
    }

    public void handleScan(final View clickView) {
        // The animation must be ended because of several graphical glitching that happens when the
        // Camera/SurfaceView is used while the animation is running.
        enterAnimation.end();
        ScanActivity.startForResult(this, clickView, WalletActivity.REQUEST_CODE_SCAN);
    }

    public void handleBackupWallet() {
        BackupWalletDialogFragment.show(getSupportFragmentManager());
    }

    public void handleRestoreWallet() {
        RestoreWalletDialogFragment.show(getSupportFragmentManager());
    }

    public void handleEncryptKeys() {
        EncryptKeysDialogFragment.show(getSupportFragmentManager());
    }

    private void handleReportIssue() {
        ReportIssueDialogFragment.show(getSupportFragmentManager(), R.string.report_issue_dialog_title_issue,
                R.string.report_issue_dialog_message_issue, Constants.REPORT_SUBJECT_ISSUE, null);
    }

    private void checkSavedCrashTrace() {
        if (CrashReporter.hasSavedCrashTrace())
            ReportIssueDialogFragment.show(getSupportFragmentManager(), R.string.report_issue_dialog_title_crash,
                    R.string.report_issue_dialog_message_crash, Constants.REPORT_SUBJECT_CRASH, null);
    }
}
