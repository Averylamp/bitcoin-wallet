package bverify;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Receipt implements  Parcelable {
    public String warehouse_id;
    public String depositor_id;
    public String accountant;
    public String category;
    public String dateCreated;
    public String insurance;
    public double weight;
    public double volume;
    public double humidity;
    public double price;
    public String receiptDetails;

    public Receipt(String warehouse_id, String depositor_id, String accountant, String category, String dateCreated, String insurance, double weight, double volume, double humidity, double price, String receiptDetails) {
        this.warehouse_id = warehouse_id;
        this.depositor_id = depositor_id;
        this.accountant = accountant;
        this.category = category;
        this.dateCreated = dateCreated;
        this.insurance = insurance;
        this.weight = weight;
        this.volume = volume;
        this.humidity = humidity;
        this.price = price;
        this.receiptDetails = receiptDetails;
    }

    public static Receipt createDemoReceipt(){
        return new Receipt("Warehouse 1", "AOVNEIW", "John Smith", "Corn", "2018/04/24", "NONE", 12, 13, 1.02, 18300, "Corn deposited on 2018/04/23.  Everything looks good.");
    }

    protected Receipt(Parcel in) {
        warehouse_id = in.readString();
        depositor_id = in.readString();
        accountant = in.readString();
        category = in.readString();
        dateCreated = in.readString();
        insurance = in.readString();
        weight = in.readDouble();
        volume = in.readDouble();
        humidity = in.readDouble();
        price = in.readDouble();
        receiptDetails = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(warehouse_id);
        dest.writeString(depositor_id);
        dest.writeString(accountant);
        dest.writeString(category);
        dest.writeString(dateCreated);
        dest.writeString(insurance);
        dest.writeDouble(weight);
        dest.writeDouble(volume);
        dest.writeDouble(humidity);
        dest.writeDouble(price);
        dest.writeString(receiptDetails);
    }

    public String toFullDetailString(){
        String result = "";
        result += "Warehouse: " + this.warehouse_id + "\n";
        result += "Date Created: " + this.dateCreated + "\n";
        result += "Category: " + this.category + "\n";
        result += "Depositor: " + this.depositor_id + "\n";
        result += "Accountant: " + this.accountant + "\n";
        result += "Insurance: " + this.insurance + "\n";
        result += "Weight: " + this.weight + "\n";
        result += "Volume: " + this.volume + "\n";
        result += "Humidity: " + this.humidity + "\n";
        result += "Price: " + this.price + "\n";
        result += "Extra Details: " + this.receiptDetails + "\n";
        return result;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Receipt> CREATOR = new Parcelable.Creator<Receipt>() {
        @Override
        public Receipt createFromParcel(Parcel in) {
            return new Receipt(in);
        }

        @Override
        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };
}