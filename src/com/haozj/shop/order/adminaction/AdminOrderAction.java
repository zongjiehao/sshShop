package com.haozj.shop.order.adminaction;

import java.util.List;

import com.haozj.shop.order.service.OrderService;
import com.haozj.shop.order.vo.Order;
import com.haozj.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
    private int page;
    private Order order = new Order();
    private OrderService orderService;
    @Override
    public Order getModel() {
        return order;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    public String findAll(){
        PageBean<Order> pageBean = orderService.findByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return "findAll";
    }
    public String findOrderItem(){
        List<Order> list = orderService.findOrderItem(order.getOid());
        ActionContext.getContext().getValueStack().set("itemList", list);
        return "findOrderItem";
    }
    public String updateState(){
        //根据订单状态查询订单
        Order currentOrder = orderService.findByOid(order.getOid());
        //更改订单状态
        currentOrder.setState(3);
        orderService.update(currentOrder);
        //页面跳转
        return "updateState";
    }
     
}
