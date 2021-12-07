package com.example.chapter13;

import android.os.Parcel;
import android.os.Parcelable;

public class listitem implements Parcelable {
    private String ID, NamaItem, Harga;

    //img
    public listitem(String ID, String NamaItem, String Harga) {
        this.ID = ID;
        this.NamaItem = NamaItem;
        this.Harga = Harga;
    }

    public String getID() {
        return ID;
    }

    public String getNamaItem() {
        return NamaItem;
    }

    public String getHarga() {
        return Harga;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNamaItem(String NamaItem) {
        this.NamaItem = NamaItem;
    }

    public void setHarga(String Harga) {
        this.Harga = Harga;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeString(this.ID);
        parcel.writeString(this.NamaItem);
        parcel.writeString(this.Harga);
    }

    protected listitem(Parcel in){
        this.ID = in.readString();
        this.NamaItem = in.readString();
        this.Harga = in.readString();
    }

    public static final Parcelable.Creator<listitem> CREATOR = new Parcelable.Creator<listitem>(){

        @Override
        public listitem createFromParcel(Parcel source) {
            return new listitem(source);
        }

        @Override
        public listitem[] newArray(int size) {
            return new listitem[size];
        }
    };
}
