package com.crown.shoppingonline.bean;

/**
 * Created by Crown on 2016/4/7.
 */
public class HotProduct {
    private int hotId;
    private String proComment;
    private Shop shop;
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getHotId() {
        return hotId;
    }

    public void setHotId(int hotId) {
        this.hotId = hotId;
    }

    public String getProComment() {
        return proComment;
    }

    public void setProComment(String proComment) {
        this.proComment = proComment;
    }
}
