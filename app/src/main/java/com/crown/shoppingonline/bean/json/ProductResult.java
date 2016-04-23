package com.crown.shoppingonline.bean.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.crown.shoppingonline.bean.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crown on 2016/4/22.
 */
public class ProductResult implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getResultCode());
        dest.writeTypedList(getProducts());
    }

    public static final Parcelable.Creator<ProductResult> CREATOR = new Creator<ProductResult>() {
        @Override
        public ProductResult createFromParcel(Parcel source) {
            return new ProductResult(source);
        }

        @Override
        public ProductResult[] newArray(int size) {
            return new ProductResult[size];
        }
    };

    private ProductResult(Parcel source) {
        this();
        resultCode = source.readInt();
        source.readTypedList(products, Product.CREATOR);
    }

    private ProductResult() {
        products = new ArrayList<>();
    }
}
