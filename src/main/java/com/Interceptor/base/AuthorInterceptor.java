package com.Interceptor.base;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class AuthorInterceptor implements Interceptor {
	
	
	private String not_login_msg;	

	public String getNot_login_msg() {
		return not_login_msg;
	}

	public void setNot_login_msg(String not_login_msg) {
		this.not_login_msg = not_login_msg;
	}

	@Override  
    public void destroy() {  
        // TODO Auto-generated method stub  
  
    }  
  
    @Override  
    public void init() {  
        // TODO Auto-generated method stub  
  
    }  
  
    @Override  
    public String intercept(ActionInvocation actionInvocation) throws Exception {  
        // TODO Auto-generated method stub  
        Map session=actionInvocation.getInvocationContext().getSession();  
//        Users user=(Users)session.get("temploginbean");  
        if(session.get("temploginbean")==null)  
        {  
	        System.out.println("还没登录，故请求被拦截！");
	        not_login_msg="您还没有登录呢！";
	        ServletActionContext.getRequest().getSession().setAttribute("not_login_msg", not_login_msg);
	        return "not_login";  
        }  
        else  
        {  
        return actionInvocation.invoke();  
        }  
    }  
  
}  
