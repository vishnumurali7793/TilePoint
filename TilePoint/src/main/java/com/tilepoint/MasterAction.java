package com.tilepoint;

import java.util.List;

import com.entities.CategoryBean;
import com.entities.CustomerBean;
import com.entities.ProductBean;
import com.entities.SalesBaseBean;
import com.hibernatedao.CategoryHibernateDao;
import com.hibernatedao.CustomerHibernateDao;
import com.hibernatedao.ProductHibernateDao;
import com.opensymphony.xwork2.ActionSupport;

public class MasterAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private CategoryBean categoryBean;
	private ProductBean productBean;
	private CustomerBean customerBean;
	private SalesBaseBean salesBean;
	private List<CategoryBean> catList;
	private List<ProductBean> prodList;
	private List<CustomerBean> customerList;

	private CategoryHibernateDao catHibernateDao = new CategoryHibernateDao();
	private ProductHibernateDao proHibernateDao = new ProductHibernateDao();
	private CustomerHibernateDao cusHibernateDao = new CustomerHibernateDao();

	public String goToHome() {
		return SUCCESS;
	}
	
	// category page action
	public String saveCategory() {
		if (categoryBean != null) {
			catHibernateDao.addCategorydata(categoryBean);
			return SUCCESS;
		}

		return INPUT;
	}

	public String deleteCategory() {
		if (categoryBean != null && categoryBean.getCategoryId() != null) {
			catHibernateDao.deleteCategoryById(categoryBean);
		}
		setCatList(catHibernateDao.getCategryList());
		return SUCCESS;
	}

	public String editCategory() {
		if (categoryBean != null && categoryBean.getCategoryId() != null) {
			setCategoryBean(catHibernateDao.getCatEditById(categoryBean));
		}
		catList = catHibernateDao.getCategryList();
		return SUCCESS;
	}

	// Product page action
	public String saveProduct() {
		if (productBean != null) {
			proHibernateDao.addProductdata(productBean);
			return SUCCESS;
		}

		return INPUT;
	}

	public String deleteProduct() {
		if (productBean != null && productBean.getProductId() != null) {
			proHibernateDao.deleteProductById(productBean);
		}
		setProdList(proHibernateDao.getProductList());
		return SUCCESS;
	}

	public String editProduct() {
		if (productBean != null && productBean.getProductId() != null) {
			setProductBean(proHibernateDao.getProdEditById(productBean));
		}
		prodList = proHibernateDao.getProductList();
		catList = catHibernateDao.getCategryList();
		return SUCCESS;
	}

	// Customer page action
	public String saveCustomer() {
		if (customerBean != null) {
			cusHibernateDao.addCustomerdata(customerBean);
			return SUCCESS;
		}

		return INPUT;
	}

	// delete customer
	public String deleteCustomer() {
		if (customerBean != null && customerBean.getCustomerId() != null) {
			cusHibernateDao.deleteCustomerById(customerBean);
		}
		setCustomerList(cusHibernateDao.getCustomerList());
		return SUCCESS;
	}

	// edit customer
	public String editCustomer() {
		if (customerBean != null && customerBean.getCustomerId() != null) {
			setCustomerBean(cusHibernateDao.getCustomerEditById(customerBean));
		}
		customerList = cusHibernateDao.getCustomerList();
		return SUCCESS;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public List<CustomerBean> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerBean> customerList) {
		this.customerList = customerList;
	}

	public CategoryBean getCategoryBean() {
		return categoryBean;
	}

	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}

	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

	public List<CategoryBean> getCatList() {
		return catList;
	}

	public void setCatList(List<CategoryBean> catList) {
		this.catList = catList;
	}

	public List<ProductBean> getProdList() {
		return prodList;
	}

	public void setProdList(List<ProductBean> prodList) {
		this.prodList = prodList;
	}

}
