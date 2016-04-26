package com.crown.shoppingonline.bean.json;

import com.crown.shoppingonline.bean.Order;

import java.util.List;

/**
 * Created by Crown on 2016/4/26.
 */
public class GetCartResult {

    private int resultCode;
    private List<Order> orders;

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
