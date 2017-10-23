package com.haozj.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.haozj.shop.user.service.UserService;
import com.haozj.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户模块
 * 
 * @author haozj
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserService userService;
	private String checkcode;
	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	// 跳到注册页面
	public String registPage() {
		return "registPage";
	}

	// 跳到登录页面
	public String loginPage() {
		return "loginPage";
	}

	// ajax异步校验用户名
	public String findByName() throws IOException {
		// 调用Service进行查询:
		User existUser = userService.findByUsername(user.getUsername());
		// 获得response对象,项页面输出:
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		// 判断
		if (existUser != null) {
			// 查询到该用户:用户名已经存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			// 没查询到该用户:用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	// 用户注册
	public String regists() {
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (checkcode1!=null && checkcode.equalsIgnoreCase(checkcode1)) {
			userService.save(user);
			this.addActionMessage("注册成功,请去邮箱激活");
			return "msg";
		}else {
			this.addActionError("您的验证码输入错误，请重试");
			return "checkcodeFail";
		}
	}

	// 用户激活
	public String active() {
		User existUser = userService.findByCode(user.getCode());
		if (existUser == null) {
			this.addActionMessage("激活失败，激活码错误");
		} else {
			this.addActionMessage("激活成功");
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("激活成功，请去登录");
		}
		return "msg";
	}

	// 用户登录
	public String login() {
		User existUser = userService.login(user);
		if (existUser == null) {
			this.addActionError("登录失败，用户名或者密码错误或者用户未激活");
			return LOGIN;
		}else {
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("user", existUser);
			return "loginSuccess";
		}	
	}
	//用户退出
	public String quit(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		//销毁session
		session.invalidate();
		return "quit";
	}
}
