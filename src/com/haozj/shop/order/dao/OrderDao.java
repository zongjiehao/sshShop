package com.haozj.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.haozj.shop.order.vo.Order;
import com.haozj.shop.utils.PageHibernateCallback;

/**
 * 订单持久层
 * 
 * @author haozj
 *
 */
public class OrderDao extends HibernateDaoSupport {
	// 保存用户订单
	public void save(Order order) {
		this.getHibernateTemplate().save(order);

	}

	// 查询一共有多少条记录
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, uid);
		if (list!=null && list.size()>0) {
			return list.get(0).intValue();
		}
		return null;
	}

	// 查询订单具体信息
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql="from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		return list;
	}

    public Order findByOid(Integer oid) {
        return this.getHibernateTemplate().get(Order.class, oid);
    }

    public void update(Order currentOrder) {
        this.getHibernateTemplate().update(currentOrder);        
    }
    //后台查询订单记录
    public int findByCount() {
        String hql = "select count(*) from Order";
        List<Long> list =  (List<Long>) this.getHibernateTemplate().find(hql);
        if (list != null && list.size()>0) {
            return list.get(0).intValue();
        }
        return 0;
    }
    //后台查询所有订单
    public List<Order> findAll() {
        String hql = "from Order order by ordertime desc";
        List<Order> list = (List<Order>) this.getHibernateTemplate().find(hql);
        if (list != null && list.size()>0) {
            return list;
        }
        return null;
    }

    public List<Order> findByPage(int begin, int limit) {
        String hql = "from Order order by ordertime desc";
        List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
        if (list != null && list.size()>0) {
            return list;
        }
        return null;
    }

    public List<Order> findOrderItem(Integer oid) {
        String hql = "from OrderItem oi where oi.order.oid = ?";
        List<Order> list = (List<Order>) this.getHibernateTemplate().find(hql, oid);
        if (list != null && list.size()>0) {
            return list;
        }
        return null;
    }

}
