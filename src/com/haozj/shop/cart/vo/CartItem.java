package com.haozj.shop.cart.vo;

import com.haozj.shop.product.vo.Product;

public class CartItem {
	private Product product;	//商品信息
	private int count;			//数量
	private Double subtotal;	//小计
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return count * product.getShop_price();
	}
}
