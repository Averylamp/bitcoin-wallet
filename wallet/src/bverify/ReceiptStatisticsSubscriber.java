package bverify;

public interface ReceiptStatisticsSubscriber {

    public void newStatisticsUpdates(int receipts, int commitments);

}
