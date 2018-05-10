package de.schildbach.wallet.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.schildbach.wallet.R;
import demo.MockDepositor;
import io.grpc.bverify.Receipt;

public class ReceiptArrayAdapter extends ArrayAdapter<Receipt> {

    Context context;
    int layoutResourceId;
    ArrayList<Receipt> adsData = null;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MockDepositor.class);

    public ReceiptArrayAdapter(Context context, int resource, List<Receipt> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.adsData = (ArrayList) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ReceiptHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ReceiptHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.receipt_list_item_title);
            holder.subtitleTextView = (TextView)row.findViewById(R.id.receipt_list_item_subtitle);
            holder.detailTextView = (TextView)row.findViewById(R.id.receipt_list_item_detail);

            row.setTag(holder);
        }
        else
        {
            holder = (ReceiptHolder) row.getTag();
        }

        final Receipt item = adsData.get(position);

        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm:ss a, EEE MMM dd ");
        if (MockDepositor.receiptDateMap.getOrDefault(item, null) != null ){
            holder.titleTextView.setText(dateFormat.format(MockDepositor.receiptDateMap.get(item)));
        }else{
            holder.titleTextView.setText(item.getDate());
        }
        holder.subtitleTextView.setText(item.getCategory());
        holder.detailTextView.setText("Price: " + item.getPrice());
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler mainHandler = new Handler(Looper.getMainLooper());

                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {

                        Intent receiptIssueIntent = new Intent(context, ReceiptInitiateTransferActivity.class);
                        receiptIssueIntent.putExtra("receipt", item);
                        context.startActivity(receiptIssueIntent);
                    }
                };
                mainHandler.post(myRunnable);

            }
        });
        return row;
    }

    private class ReceiptHolder{
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
    }


}
