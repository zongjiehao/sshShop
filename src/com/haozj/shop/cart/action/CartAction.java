package com.haozj.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.haozj.shop.cart.vo.Cart;
import com.haozj.shop.cart.vo.CartItem;
import com.haozj.shop.product.service.ProductService;
import com.haozj.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport{
	private Integer pid;
	private Integer quantity;
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String addCart(){
		CartItem cartItem = new CartItem();
		cartItem.setCount(quantity);
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//购物车存入session
		Cart cart =getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	//清空购物车
	public String clearCart(){
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	public String removeCart(){
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	public String myCart(){
		
		return "myCart";
	}
	/**
	 * 从session中取出购物车
	 * @return
	 */
	private Cart getCart() {
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart =new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
