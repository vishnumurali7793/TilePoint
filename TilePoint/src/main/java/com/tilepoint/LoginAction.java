package com.tilepoint;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.entities.LoginBean;
import com.hibernatedao.LoginHibernateDao;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {
	

	private static final long serialVersionUID = 1L;

	private LoginBean loginBean;

	private LoginHibernateDao loginHibernateDao = new LoginHibernateDao();

	private SessionMap<String, Object> session;

	boolean flag = false;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String authenticateUser() {
		HttpSession httpSession = ServletActionContext.getRequest().getSession(true);

		if (loginBean.getUserName() != null && loginBean.getPassword() != null) {
			flag = loginHibernateDao.authenticateUser(loginBean);
			if (flag) {
				session.put("userName", loginBean.getUserName());
				return SUCCESS;
			}
		}
		return INPUT;
	}

	public String logoutUser() {
		session.remove("userName");
		session.invalidate();
		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		this.session = (SessionMap<String, Object>) session;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public SessionMap<String, Object> getSession() {
		return session;
	}

	public void setSession(SessionMap<String, Object> session) {
		this.session = session;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
