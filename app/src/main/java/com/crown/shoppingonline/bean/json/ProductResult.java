package com.crown.shoppingonline.bean.json;

import com.crown.shoppingonline.bean.Product;

import java.util.List;

/**
 * Created by Crown on 2016/4/22.
 */
public class ProductResult {
    private int resultCode;
    private List<Product> products;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
