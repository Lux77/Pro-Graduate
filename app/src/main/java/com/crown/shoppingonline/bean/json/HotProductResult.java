package com.crown.shoppingonline.bean.json;

import com.crown.shoppingonline.bean.HotProduct;

import java.util.List;

public class HotProductResult {
	private int resultCode;
	private List<HotProduct> hotProducts;
	
	public int getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	
	public List<HotProduct> getHotProducts() {
		return hotProducts;
	}
	
	public void setHotProducts(List<HotProduct> hotProducts) {
		this.hotProducts = hotProducts;
	}
}