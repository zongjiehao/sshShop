package com.haozj.shop.order.service;
/**
 * 订单业务层代码
 * @author haozj
 *
 */

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.haozj.shop.order.dao.OrderDao;
import com.haozj.shop.order.vo.Order;
import com.haozj.shop.utils.PageBean;

@Transactional
public class OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    // 保存用户订单
    public void save(Order order) {
        orderDao.save(order);

    }

    // 查看我的所有订单
    public PageBean<Order> findByPageUid(Integer uid, Integer page) {
        // TODO Auto-generated method stub
        PageBean<Order> pageBean = new PageBean<Order>();
        // 设置当前页数
        pageBean.setPage(page);
        // 设置每页显示记录数
        Integer limit = 5;
        pageBean.setLimit(limit);
        // 设置总记录数
        Integer totalCount = orderDao.findByCountUid(uid);
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        Integer totalPage = null;
        totalPage = (totalCount - 1) / limit + 1;
        pageBean.setPageCount(totalPage);
        Integer begin = (page - 1) * limit;
        List<Order> list = orderDao.findByPageUid(uid, begin, limit);
        pageBean.setList(list);
        return pageBean;
    }

    public Order findByOid(Integer oid) {
        return orderDao.findByOid(oid);

    }

    // 业务层修改订单
    public void update(Order currentOrder) {
        orderDao.update(currentOrder);
    }

    public PageBean<Order> findByPage(int page) {
        PageBean<Order> pageBean = new PageBean<Order>();
        int limit = 5;
        pageBean.setLimit(limit);
        pageBean.setPage(page);
        int totalCount = orderDao.findByCount();
        pageBean.setTotalCount(totalCount);
        int pageCount = (totalCount - 1) / limit + 1;
        pageBean.setPageCount(pageCount);
        int begin = (page-1)*limit;
        List<Order> list = orderDao.findByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
    }

    public List<Order> findOrderItem(Integer oid) {
        return orderDao.findOrderItem(oid);
    }
}
