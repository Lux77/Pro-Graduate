package com.crown.shoppingonline.bean;

/**
 * Created by Crown on 2016/4/7.
 */
public class SellerShow {
    private String shopTitle;
    private int imgId;

    public SellerShow(String shopTitle, int imgId) {
        this.shopTitle = shopTitle;
        this.imgId = imgId;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
