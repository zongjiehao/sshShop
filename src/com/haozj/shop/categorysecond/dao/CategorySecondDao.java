package com.haozj.shop.categorysecond.dao;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.junit.Test;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.haozj.shop.category.vo.Category;
import com.haozj.shop.categorysecond.vo.CategorySecond;
import com.haozj.shop.product.vo.Product;
import com.haozj.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport{
    int begin=1;
    int limit=5;
    public int findCount() {
        String hql="select count(*) from CategorySecond";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if (list != null && list.size()>0) {
            return list.get(0).intValue();
        }
        return 0;
    }
    public  List<CategorySecond> findByPage(int begin, int limit) {
        String hql="from CategorySecond order by csid desc";
        List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql,null, begin, limit));
        if (list != null && list.size()>0) {
            return list;
        }
        return null;
    }
    public void save(CategorySecond categorySecond) {
        this.getHibernateTemplate().save(categorySecond);
        
    }
    public void delete(CategorySecond categorySecond) {
       this.getHibernateTemplate().delete(categorySecond);
        
    }
    public CategorySecond findByCsid(Integer csid) {
        return this.getHibernateTemplate().get(CategorySecond.class,csid);
        
    }
    public void update(CategorySecond categorySecond) {
        this.getHibernateTemplate().update(categorySecond);
        
    }
    public List<CategorySecond> findAll() {
        String hql="from CategorySecond";
        List<CategorySecond> list = (List<CategorySecond>) this.getHibernateTemplate().find(hql);
        if (list != null && list.size()>0) {
            return list;
        }
        return null;
    }
}
