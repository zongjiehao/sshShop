package com.haozj.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.haozj.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeIntercepter extends MethodFilterInterceptor{
/**
 * 没有登录的用户无法访问后台
 */
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
        if (adminUser == null) {
            //没有登录
            ActionSupport actionSupport =(ActionSupport) invocation.getAction();
            return "loginFail";
        }
        else{
            //已经登录,执行放行操作
            return invocation.invoke();
        }
    }

}
