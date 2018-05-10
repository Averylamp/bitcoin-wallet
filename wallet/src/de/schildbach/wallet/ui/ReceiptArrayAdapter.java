package de.schildbach.wallet.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.schildbach.wallet.R;
import io.grpc.bverify.Receipt;

public class ReceiptArrayAdapter extends ArrayAdapter<Receipt> {

    Context context;
    int layoutResourceId;
    ArrayList<Receipt> adsData = null;

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

        Receipt item = adsData.get(position);
        holder.titleTextView.setText(item.getDate());
        holder.subtitleTextView.setText(item.getCategory());
        holder.detailTextView.setText("Price: " + item.getPrice());

        return row;
    }

    private class ReceiptHolder{
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
    }


}
