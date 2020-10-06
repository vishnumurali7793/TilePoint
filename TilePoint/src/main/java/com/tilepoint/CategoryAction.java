package com.tilepoint;

import java.util.List;


import com.entities.CategoryBean;
import com.hibernatedao.CategoryHibernateDao;
import com.opensymphony.xwork2.ActionSupport;


public class CategoryAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<CategoryBean> catList;
	private CategoryHibernateDao masterHibernateDao = new CategoryHibernateDao();
	
	public String goToCategory() {
		catList = masterHibernateDao.getCategryList();
		return SUCCESS;
	}

	public List<CategoryBean> getCatList() {
		return catList;
	}

	public void setCatList(List<CategoryBean> catList) {
		this.catList = catList;
	}
	
}
