package com.haozj.shop.product.dao;

import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.haozj.shop.product.vo.Product;
import com.haozj.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport {

	public List<Product> findHot() {
		// 使用离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		// 倒序显示最新的热门商品
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list = (List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	public List<Product> findNew() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 倒序显示最新的热门商品
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list = (List<Product>) this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	// 根据cid计算不同的总记录数
	public int findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据cid查询商品的集合，带分页功能
	public List<Product> findByPage(Integer cid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, new Object[] { cid }, begin, limit));
		if (list.size() > 0 && list != null) {
			return list;
		}
		return null;
	}

	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list = this.getHibernateTemplate()
				.execute(new PageHibernateCallback<Product>(hql, new Object[] { csid }, begin, limit));
		if (list.size() > 0 && list != null) {
			return list;
		}
		return null;
	}
	//查询所有商品
    public int findCount() {
        String hql="select count(*) from Product";
        List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
        if (list !=null && list.size()>0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    public List<Product> findAllByPage(int begin, int limit) {
        String hql = "from Product order by pdate desc";
        List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
        if (list.size() > 0 && list != null) {
            return list;
        }
        return null;
    }

    public void save(Product product) {
        this.getHibernateTemplate().save(product);
        
    }

    public void delete(Product product) {
        this.getHibernateTemplate().delete(product);
        
    }

    public void update(Product product) {
        this.getHibernateTemplate().update(product);
        
    }

}
