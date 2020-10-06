package com.tilepoint;

import java.util.List;

import com.entities.CustomerBean;
import com.hibernatedao.CustomerHibernateDao;
import com.opensymphony.xwork2.ActionSupport;

public class CustomerAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<CustomerBean> customerList;
	private CustomerHibernateDao masterHibernateDao = new CustomerHibernateDao();

	public String goToCustomer() {
		customerList = masterHibernateDao.getCustomerList();
		return SUCCESS;
	}

	public List<CustomerBean> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerBean> customerList) {
		this.customerList = customerList;
	}

}
