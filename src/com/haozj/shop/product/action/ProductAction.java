package com.haozj.shop.product.action;

import java.util.List;

import com.haozj.shop.category.service.CategoryService;
import com.haozj.shop.category.vo.Category;
import com.haozj.shop.product.service.ProductService;
import com.haozj.shop.product.vo.Product;
import com.haozj.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//用于接收数据的模型驱动
	private Product product=new Product();
	private ProductService productService;
	//接收分类的cid
	private Integer cid;
	//当前页
	private Integer page;
	private Integer csid;
	
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	//注入一级分类的service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	@Override
	public Product getModel() {
		return product;
	}
	//根据商品id查询商品详情
	public String findByPid(){
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	//根据一级分类的id查询商品
	public String findByCid(){
		//List<Category> categories = categoryService.findAll();
		//根据一级分类查询商品，带分页的查询
		 PageBean<Product> pageBean = productService.findByPageCid(cid,page);
		 //将Pagebean存入值栈
		 ActionContext.getContext().getValueStack().set("pageBean", pageBean);;
		return "findByCid";
	}
	//根据二级分类的id查询商品
	public String findByCsid(){
		//List<Category> categories = categoryService.findAll();
		//根据一级分类查询商品，带分页的查询
		 PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		 //将Pagebean存入值栈
		 ActionContext.getContext().getValueStack().set("pageBean", pageBean);;
		return "findByCsid";
	}

}
