package com.haozj.shop.cart.vo;
/**
 * 购物车
 * @author haozj
 *
 */

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{
	//购物项
	private Map<Integer, CartItem> map=new LinkedHashMap<Integer, CartItem>();
	//总计
	private double total;
	public double getTotal() {
		return total;
	}
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	//加入购物车
	public void addCart(CartItem cartItem){
		//判断是否已经存在该商品
		//如果存在，总计=总计+小计
		Integer pid = cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			CartItem cartItemBefore =map.get(pid);
			cartItemBefore.setCount(cartItem.getCount()+cartItemBefore.getCount());
		}	
		//如果不存在，向map中添加商品，总计=总计+小计
		else {
			map.put(pid, cartItem);
		}
		total=total+cartItem.getSubtotal();
	}
	//清除购物项
	public void removeCart(Integer pid){
		//移除购物项
		CartItem cartItem=map.get(pid);
		map.remove(pid);
		//总计-subTotal
		total=total-cartItem.getSubtotal();
		
	}
	//清空购物车
	public void clearCart(){
		//清空所有购物项和总计数
		map.clear();
		total=0;
	}
	
	
}
