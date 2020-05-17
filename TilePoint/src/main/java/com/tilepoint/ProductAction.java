package com.tilepoint;

import java.util.List;

import com.entities.CategoryBean;
import com.entities.ProductBean;
import com.hibernatedao.CategoryHibernateDao;
import com.hibernatedao.ProductHibernateDao;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private List<ProductBean> prodList;
	private List<CategoryBean> catList;
	private ProductHibernateDao ProductHibernateDao = new ProductHibernateDao();
	private CategoryHibernateDao masterHibernateDao = new CategoryHibernateDao();
	
	public String goToProduct() {
		prodList = ProductHibernateDao.getProductList();
		catList = masterHibernateDao.getCategryList();
		return SUCCESS;
	}

	public List<ProductBean> getProdList() {
		return prodList;
	}

	public void setProdList(List<ProductBean> prodList) {
		this.prodList = prodList;
	}

	public List<CategoryBean> getCatList() {
		return catList;
	}

	public void setCatList(List<CategoryBean> catList) {
		this.catList = catList;
	}
	
}
