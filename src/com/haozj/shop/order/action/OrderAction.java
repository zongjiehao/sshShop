package com.haozj.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.haozj.shop.cart.vo.Cart;
import com.haozj.shop.cart.vo.CartItem;
import com.haozj.shop.order.dao.OrderDao;
import com.haozj.shop.order.service.OrderService;
import com.haozj.shop.order.vo.Order;
import com.haozj.shop.order.vo.OrderItem;
import com.haozj.shop.user.vo.User;
import com.haozj.shop.utils.PageBean;
import com.haozj.shop.utils.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private Order order = new Order();
	private OrderService orderService;
	private Integer page;
	//接收支付通道的编码
	private String pd_FrpId;
	//接收付款成功后的响应数据
	//订单编号
	private String r6_Order;
	//支付金额
	private String r3_Amt;
	
	public void setR6_Order(String r6_Order) {
        this.r6_Order = r6_Order;
    }
    public void setR3_Amt(String r3_Amt) {
        this.r3_Amt = r3_Amt;
    }
    public void setPd_FrpId(String pd_FrpId) {
        this.pd_FrpId = pd_FrpId;
    }
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	@Override
	public Order getModel() {
		return order;
	}
	public String save(){
		//订单数据补全
		order.setOrdertime(new Date());
		//1:未付款 2:付款未发货 3:发货没确认收货	4:交易完成
		order.setState(1);
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart==null) {
			this.addActionError("亲，你还没有购物呢！请先去购物");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (existUser==null) {
			this.addActionError("您还没有登录！请先去登录！");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		//将order保存到值栈当中,因为order在模型驱动里，所以不用保存
		//ActionContext.getContext().getValueStack().set("order", order);
		cart.clearCart();
		return "saveSuccess";
	}
	//通过用户名查询订单
	public String findByUid(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		Integer page = 1;
		if (user!=null) {
		    PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
            ActionContext.getContext().getValueStack().set("pageBean", pageBean);
            return "findByUidSuccess";
        }
		    return "msg";
	}
	//通过订单编号查询订单
	public String findByOid(){
	    order = orderService.findByOid(order.getOid());
	    return "findByOidSuccess";
	}
	//订单支付
	public String payOrder() throws IOException{
	    //修改订单
	    Order currentOrder = orderService.findByOid(order.getOid());
	    currentOrder.setAddr(order.getAddr());
	    currentOrder.setName(order.getName());
	    currentOrder.setPhone(order.getPhone());
	    orderService.update(currentOrder);
	    //订单付款
	    this.pay();
	    return NONE;
	}
    private void pay() throws IOException {
        String p0_Cmd="Buy";//业务类型
	    String p1_MerId="10001126856";//商户编号
	    String p2_Order=order.getOid().toString();//商户订单号
	    String p3_Amt="0.01";//支付金额
	    String p4_Cur="CNY";//交易币种
	    String p5_Pid="";//商品名称
	    String p6_Pcat="";//商品种类
	    String p7_Pdesc="";//商品描述
	    String p8_Url="http://localhost:8080/sshShop/order_callBack.action";//用户接收支付成功数据的地址
	    String p9_SAF="";//送货地址
	    String pa_MP="";//商户扩展信息
	    String pd_FrpId=this.pd_FrpId;//支付通道编码
	    String pr_NeedResponse="1";//应答机制
	    String keyValue="69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";//密钥
	    String hmac=PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);//签名数据
	    
	    StringBuilder sb = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node?");
	    sb.append("p0_Cmd=").append(p0_Cmd).append("&");
	    sb.append("p1_MerId=").append(p1_MerId).append("&");
	    sb.append("p2_Order=").append(p2_Order).append("&");
	    sb.append("p3_Amt=").append(p3_Amt).append("&");
	    sb.append("p4_Cur=").append(p4_Cur).append("&");
	    sb.append("p5_Pid=").append(p5_Pid).append("&");
	    sb.append("p6_Pcat=").append(p6_Pcat).append("&");
	    sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
	    sb.append("p8_Url=").append(p8_Url).append("&");
	    sb.append("p9_SAF=").append(p9_SAF).append("&");
	    sb.append("pd_FrpId=").append(pd_FrpId).append("&");
	    sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
	    sb.append("keyValue=").append(keyValue).append("&");
	    sb.append("hmac=").append(hmac);
	    
	    ServletActionContext.getResponse().sendRedirect(sb.toString());
    }
    public String callBack(){
        //修改订单状态，改为已经付款
        Order currentOrder = orderService.findByOid(Integer.parseInt(r6_Order));
        currentOrder.setState(2);
        orderService.update(currentOrder);
        this.addActionMessage("订单已经付款成功！订单编号:"+r6_Order+" 付款金额:"+r3_Amt);
        return "msg";
        
    }
    public String updateState(){
        Order currentOrder = orderService.findByOid(order.getOid());
        currentOrder.setState(4);
        orderService.update(currentOrder);
        return "updateState";
    }
}
