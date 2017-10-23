package com.haozj.shop.index.action;

import java.util.List;

import com.haozj.shop.category.service.CategoryService;
import com.haozj.shop.category.vo.Category;
import com.haozj.shop.product.service.ProductService;
import com.haozj.shop.product.vo.Product;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的action
 * @author haozj
 *
 */
public class IndexAction extends ActionSupport {
	private CategoryService categoryService;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public String execute(){
		List<Category> cList=categoryService.findAll();
		//将一级分类存入session
		ActionContext.getContext().getSession().put("cList", cList);
		List<Product> pList = productService.findHot();
		ActionContext.getContext().getValueStack().set("pList", pList);
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}
