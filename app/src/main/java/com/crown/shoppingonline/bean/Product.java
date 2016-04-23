package com.crown.shoppingonline.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Crown on 2016/4/22.
 */
public class Product implements Parcelable {

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getpTypeId() {
        return pTypeId;
    }

    public void setpTypeId(int pTypeId) {
        this.pTypeId = pTypeId;
    }

    public String getpImgName() {
        return pImgName;
    }

    public void setpImgName(String pImgName) {
        this.pImgName = pImgName;
    }

    public double getpDiscount() {
        return pDiscount;
    }

    public void setpDiscount(double pDiscount) {
        this.pDiscount = pDiscount;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpShopId() {
        return pShopId;
    }

    public void setpShopId(int pShopId) {
        this.pShopId = pShopId;
    }

    private int pId;
    private int pTypeId;
    private String pName;
    private double pPrice;
    private double pDiscount;
    private String pImgName;
    private int pShopId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getpId());
        dest.writeInt(getpTypeId());
        dest.writeString(getpName());
        dest.writeDouble(getpPrice());
        dest.writeDouble(getpDiscount());
        dest.writeString(getpImgName());
        dest.writeInt(getpShopId());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {

        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    private Product(Parcel source) {
        this.pId = source.readInt();
        this.pTypeId = source.readInt();
        this.pName = source.readString();
        this.pPrice = source.readDouble();
        this.pDiscount = source.readDouble();
        this.pImgName = source.readString();
        this.pShopId = source.readInt();
    }
}
