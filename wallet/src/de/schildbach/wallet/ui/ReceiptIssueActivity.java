/*
 * Copyright 2011-2015 the original author or authors.
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

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.schildbach.wallet.R;
import demo.MockDepositor;
import io.grpc.bverify.IssueReceiptRequest;
import io.grpc.bverify.Receipt;

/**
 * @author Andreas Schildbach
 */
public final class ReceiptIssueActivity extends AbstractWalletActivity {

    private IssueReceiptRequest receiptToVerify;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.receipt_issue_dialog);

        receiptToVerify = (IssueReceiptRequest) getIntent().getSerializableExtra("receipt");


        final TextView descriptionTextView = findViewById(R.id.receipt_issue_request_information);
        descriptionTextView.setText(receiptInfoString(receiptToVerify));

        final Button acceptReceipt  = findViewById(R.id.accept_receipt_button);
        acceptReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                IssueReceiptRequest signedReceiptRequest = MockDepositor.approveRequestAndApply(receiptToVerify);
                MockDepositor.submitApprovedRequest(signedReceiptRequest);
                finish();// Closing Activity
            }
        });

        final Button declineReceipt = findViewById(R.id.decline_receipt_button);
        declineReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();// Closing Activity
            }
        });
    }

    public String receiptInfoString(IssueReceiptRequest receiptIssue){
        String result = "";
        Receipt receipt = receiptIssue.getReceipt();
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
