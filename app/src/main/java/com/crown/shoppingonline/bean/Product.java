package com.crown.shoppingonline.bean;

/**
 * Created by Crown on 2016/4/22.
 */
public class Product {
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

    private int pId;
    private int pTypeId;
    private String pName;
    private double pPrice;
    private double pDiscount;
    private String pImgName;
}
