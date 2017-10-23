package com.haozj.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.haozj.shop.product.dao.ProductDao;
import com.haozj.shop.product.vo.Product;
import com.haozj.shop.utils.PageBean;
@Transactional
public class ProductService{
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	//查询热门商品
	public List<Product> findHot() {
		return productDao.findHot();
	}
	//查询最新商品
	public List<Product> findNew() {
	
		return productDao.findNew();
	}
	//根据商品id查询商品
	public Product findByPid(Integer pid) {
		
		return productDao.findByPid(pid);
	}
	public PageBean<Product> findByPageCid(Integer cid, Integer page) {
		int limit=8;//每页显示多少记录
		int totalCount=0;//总记录
		int totalpage=0;//总页数
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		totalCount=productDao.findCountCid(cid);
		totalpage=(totalCount-1)/limit+1;
		pageBean.setTotalCount(totalCount);
		pageBean.setPageCount(totalpage);
		int begin=(page-1)*limit;
		List<Product> list = productDao.findByPage(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public PageBean<Product> findByPageCsid(Integer csid, Integer page) {
		int limit=8;//每页显示多少记录
		int totalCount=0;//总记录
		int totalpage=0;//总页数
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		totalCount=productDao.findCountCsid(csid);
		totalpage=(totalCount-1)/limit+1;
		pageBean.setTotalCount(totalCount);
		pageBean.setPageCount(totalpage);
		int begin=(page-1)*limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
    public PageBean<Product> findByPage(int page) {
        PageBean<Product> pageBean = new PageBean<Product>();
        pageBean.setPage(page);
        int limit=10;
        pageBean.setLimit(limit);
        int totalCount = productDao.findCount();
        pageBean.setTotalCount(totalCount);
        int pageCount = (totalCount-1)/limit+1;
        pageBean.setPageCount(pageCount);
        int begin = (page-1)*limit;
        List<Product> list = productDao.findAllByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
    }
    public void save(Product product) {
        productDao.save(product);
        
    }
    public void delete(Product product) {
        productDao.delete(product);
        
    }
    public void update(Product product) {
        productDao.update(product);
        
    }
}
