package com.tilepoint;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.hibernatedao.SalesHibernateDao;
import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Collection<Object> salesDetailsList;
	private SalesHibernateDao salesHibernateDao = new SalesHibernateDao();

	public String execute() {
		return SUCCESS;
	}

	public String goToDashBoard() {
		return SUCCESS;
	}

	public String getSalesDetails() {
		Date currentDate = new Date();
		String currentMonth = sdf.format(currentDate).split("-")[1];
		String currentYear = sdf.format(currentDate).split("-")[0];
		String startDate, endDate = null;
		endDate = sdf.format(currentDate);
		startDate = currentYear + "-" + currentMonth + "-" + String.valueOf((Integer.parseInt(endDate.split("-")[2]) - 7));
		
		salesDetailsList = salesHibernateDao.getSalesCountByDate(startDate, endDate);
		return SUCCESS;
	}

	public Collection<Object> getSalesDetailsList() {
		return salesDetailsList;
	}

	public void setSalesDetailsList(Collection<Object> salesDetailsList) {
		this.salesDetailsList = salesDetailsList;
	}
}
