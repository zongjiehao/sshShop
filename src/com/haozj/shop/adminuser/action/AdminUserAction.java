package com.haozj.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.haozj.shop.adminuser.service.AdminUserService;
import com.haozj.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
    private AdminUser adminUser = new AdminUser();
    private AdminUserService adminUserService;
    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }
    @Override
    public AdminUser getModel() {
        return adminUser;
    }
    //后台登录
    public String login(){
        AdminUser existAdminUser = adminUserService.login(adminUser);
        if (existAdminUser ==null) {
            this.addActionError("亲,您的用户名或者密码错误!");
            return "loginFail";
        }
        else {
            ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
            return "loginPass";
        }
        
    }
    //退出
    public String quit(){
        ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", null);;
        
        return "quit";
    }
}
