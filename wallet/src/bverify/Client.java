package bverify;

import java.util.ArrayList;

public class Client {


    private final ArrayList<Receipt> ownedReceipts = new ArrayList<Receipt>();

    private final String accountName;


    public Client(String accountName){
        this.accountName = accountName;
    }

    public void addReceipt(Receipt receipt){
        this.ownedReceipts.add(receipt);
    }

}
