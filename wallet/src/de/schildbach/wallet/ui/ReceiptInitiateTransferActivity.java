package de.schildbach.wallet.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.schildbach.wallet.R;
import demo.MockDepositor;
import io.grpc.bverify.Receipt;
import io.grpc.bverify.TransferReceiptRequest;

public class ReceiptInitiateTransferActivity extends AbstractWalletActivity{

    private Receipt receipt;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.receipt_initiate_transfer_dialog);

        receipt = (Receipt) getIntent().getSerializableExtra("receipt");


        final TextView descriptionTextView = findViewById(R.id.receipt_initiate_transfer_request_information);

        descriptionTextView.setText(receiptInfoString(receipt));

        final Button acceptReceipt  = findViewById(R.id.accept_receipt_initiate_transfer_button);
        acceptReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                MockDepositor.transferReceipt(MockDepositor.transferAccount, receipt);
//                TransferReceiptRequest signedReceiptRequest = MockDepositor.approveTransferRequestAndApply(receipt);
//                MockDepositor.submitApprovedRequest(signedReceiptRequest);
                finish();// Closing Activity
            }
        });

        final Button declineReceipt = findViewById(R.id.decline_receipt_initiate_transfer_button);
        declineReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();// Closing Activity
            }
        });
    }

    public String receiptInfoString(Receipt receiptIssue){
        String result = "";
        result += "To: " + MockDepositor.transferAccount.getIdAsString()+ "\n";
        result += "To: " + MockDepositor.transferAccount.getFirstName()+ "\n";
        result += "From: " + MockDepositor.account.getIdAsString() + "\n";
        result += "From: " + MockDepositor.account.getFirstName() + "\n";
        result += "Warehouse: " + receipt.getWarehouseId() + "\n";
        result += "Date Created: " + receipt.getDate() + "\n";
        result += "Category: " + receipt.getCategory() + "\n";
        result += "Depositor: " + receipt.getDepositorId() + "\n";
        result += "Accountant: " + receipt.getAccountant() + "\n";
        result += "Insurance: " + receipt.getInsurance() + "\n";
        result += "Weight: " + receipt.getWeight() + "\n";
        result += "Volume: " + receipt.getVolume() + "\n";
        result += "Humidity: " + receipt.getHumidity() + "\n";
        result += "Price: " + receipt.getPrice()+ "\n";
        result += "Extra Details: " + receipt.getDetails() + "\n";
        return result;
    }

    @Override
    public void onAttachedToWindow() {
        setShowWhenLocked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
//        getMenuInflater().inflate(R.menu.request_coins_activity_options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
//        switch (item.getItemId()) {
//        case R.id.request_coins_options_help:
//            HelpDialogFragment.page(getSupportFragmentManager(), R.string.help_request_coins);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
