package bverify;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import io.grpc.bverify.Receipt;

public class ReceiptDateComparator implements Comparator<Receipt> {
    Map<Receipt, Date> base;

    public ReceiptDateComparator(Map<Receipt, Date> base){
        this.base = base;
    }

    public int compare(Receipt a, Receipt b){
        int r = base.get(b).compareTo(base.get(a));
        if (r == 0){
            return -1;
        }
        return r;
    }

}
