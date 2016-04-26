package com.crown.shoppingonline.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Crown on 2016/4/26.
 */
public class Order implements Parcelable {


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getProductImgName() {
        return productImgName;
    }

    public void setProductImgName(String productImgName) {
        this.productImgName = productImgName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    private int orderId;
    private int orderState; //0:逻辑删除  1：待支付  2：已支付  3:交易成功
    private int userId;
    private int productId;
    private int productCount;
    private String productImgName;
    private String productDescription;
    private double productPrice;

    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(orderId);
        dest.writeInt(orderState);
        dest.writeInt(userId);
        dest.writeInt(productId);
        dest.writeInt(productCount);

        dest.writeString(productImgName);
        dest.writeString(productDescription);
        dest.writeDouble(productPrice);

        dest.writeString(shopName);
    }

    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {

        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    private Order(Parcel source) {
        orderId = source.readInt();
        orderState = source.readInt();
        userId = source.readInt();
        productId = source.readInt();
        productCount = source.readInt();

        productImgName = source.readString();
        productDescription = source.readString();

        productPrice = source.readDouble();
        shopName = source.readString();
    }
}
