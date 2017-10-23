package com.haozj.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.haozj.shop.category.vo.Category;
import com.haozj.shop.categorysecond.dao.CategorySecondDao;
import com.haozj.shop.categorysecond.vo.CategorySecond;
import com.haozj.shop.utils.PageBean;
@Transactional
public class CategorySecondService {
	private CategorySecondDao categorySecondDao;
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
    public PageBean<CategorySecond> findByPage(Integer page) {
        PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
        pageBean.setPage(page);
        int limit = 5;
        pageBean.setLimit(limit);
        int totalCount = categorySecondDao.findCount();
        pageBean.setTotalCount(totalCount);
        int pageCount = (totalCount-1)/limit+1;
        pageBean.setPageCount(pageCount);
        int begin = (page - 1) * limit;
        List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
        pageBean.setList(list);
        return pageBean ;
    }
    public void save(CategorySecond categorySecond) {
        categorySecondDao.save(categorySecond);
        
    }
    public void delete(CategorySecond categorySecond) {
        categorySecondDao.delete(categorySecond);
        
    }
    public CategorySecond findByCsid(Integer csid) {
        return categorySecondDao.findByCsid(csid);
    }
    public void update(CategorySecond categorySecond) {
        categorySecondDao.update(categorySecond);
        
    }
    public List<CategorySecond> findAll() {
        return categorySecondDao.findAll();
    }
}
