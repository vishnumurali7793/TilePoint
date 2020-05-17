package com.tilepoint;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.entities.CustomerBean;
import com.entities.ProductBean;
import com.entities.SalesAmountBean;
import com.entities.SalesBaseBean;
import com.entities.SalesDetailsBean;
import com.hibernatedao.ProductHibernateDao;
import com.hibernatedao.SalesHibernateDao;
import com.opensymphony.xwork2.ActionSupport;

public class SalesAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private ProductHibernateDao prodHibernateDao = new ProductHibernateDao();
	private SalesHibernateDao salesHibernateDao = new SalesHibernateDao();
	private SalesBaseBean salesBaseBean;
	private List<SalesBaseBean> salbeanList;
	private Collection<Object> cusList;
	private List<SalesDetailsBean> salDetList;
	private String[] checkbox;
	private SalesDetailsBean salesDetails;
	private List<ProductBean> prodList;
	private SalesAmountBean salesAmountBean;
	private List<SalesAmountBean> salamtList;

	public List<ProductBean> getProdList() {
		return prodList;
	}

	public void setProdList(List<ProductBean> prodList) {
		this.prodList = prodList;
	}

	public String goToSales() {
		salbeanList = salesHibernateDao.getPurchaseList();
		return SUCCESS;
	}

	public String getmodalForSales() {
		String newInvc = null;
		int year = 0;
		Integer invoival = salesHibernateDao.invoiceSales();
		int abc = 0;
		if (invoival != null) {
			abc = invoival + 1;
			Date dt = new Date();
			year = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dt)).getYear();
			newInvc = "SS/" + abc + "/" + year;
		} else {
			newInvc = "SS/" + "1" + "/" + year;
		}
		try {
			salesBaseBean = new SalesBaseBean();
			salesBaseBean.setInvoiceNo(newInvc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// Customer page action
	public String saveSalesBase() {
		int year = 0;
		int month = 0;
		if (salesBaseBean != null) {
			Integer customerId = salesHibernateDao.getCusDetails(salesBaseBean.getCustomerId().getCustomerCode());
			salesBaseBean.setCustomerId(new CustomerBean());
			salesBaseBean.getCustomerId().setCustomerId(customerId);
			Date dt = new Date();
			year = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dt)).getYear();
			month = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(dt)).getMonthValue();
			String mnth = month + "";
			String yr = year + "";
			salesBaseBean.setMonth(mnth);
			salesBaseBean.setYear(yr);
			salesHibernateDao.addSalestoBase(salesBaseBean);
			return SUCCESS;
		}

		return INPUT;
	}

	public String getCusDetails() throws Exception {
		if (salesBaseBean != null && salesBaseBean.getCustomerId() != null) {
			cusList = new ArrayList<Object>();
			cusList = salesHibernateDao.getCusListByCusCode(salesBaseBean.getCustomerId().getCustomerCode());
		}
		return SUCCESS;
	}

	public String editsalesdetails() {
		if (salesBaseBean != null && salesBaseBean.getSalesId() != null) {
			salDetList = salesHibernateDao.getsalesDetailsList(salesBaseBean.getSalesId());
			salesAmountBean = salesHibernateDao.getsalestotamt(salesBaseBean.getSalesId());
		}
		return SUCCESS;
	}

	public String savesalesform() {
		if (checkbox != null && checkbox.length > 0 && salesBaseBean.getSalesId() != null) {
			for (String chboxdata : checkbox) {
				salesDetails = new SalesDetailsBean();
				salesDetails.setProductId(new ProductBean());
				salesDetails.getProductId().setProductId(Integer.parseInt(chboxdata));
				salesDetails.setSalesId(new SalesBaseBean());
				salesDetails.getSalesId().setSalesId(salesBaseBean.getSalesId());
				salesHibernateDao.savesalesdetails(salesDetails);
			}
		}
		return SUCCESS;
	}

	public String getProductListForSales() {
		prodList = prodHibernateDao.getProductList();
		return SUCCESS;
	}

	public String savesaleDetails() {
		if (salDetList != null && salesBaseBean.getSalesId() != null) {
			List<SalesDetailsBean> newprodlist = new ArrayList<SalesDetailsBean>();
			newprodlist = salesHibernateDao.getsalesDetailsList(salesBaseBean.getSalesId());
			int i = 0;
 			for (SalesDetailsBean pd : salDetList) {
				pd.setProductId(newprodlist.get(i).getProductId());
				pd.setSalesId(new SalesBaseBean());
				pd.getSalesId().setSalesId(newprodlist.get(i).getSalesId().getSalesId());
				pd.setSalesDetailsId(newprodlist.get(i).getSalesDetailsId());
				salesHibernateDao.savesalesdetails(pd);
				i++;
			}
			salesAmountBean.setSalesId(new SalesBaseBean());
			salesAmountBean.getSalesId().setSalesId(salesBaseBean.getSalesId());
			salesHibernateDao.savesalesnetamt(salesAmountBean);
		}
		return SUCCESS;
	}

	public String removeProductFromSales() {
		if (salesDetails != null && salesDetails.getSalesDetailsId() != null) {
			salesHibernateDao.removeProductFromSales(salesDetails);
		}
		return SUCCESS;
	}

	public SalesBaseBean getSalesBaseBean() {
		return salesBaseBean;
	}

	public void setSalesBaseBean(SalesBaseBean salesBaseBean) {
		this.salesBaseBean = salesBaseBean;
	}

	public List<SalesBaseBean> getSalbeanList() {
		return salbeanList;
	}

	public void setSalbeanList(List<SalesBaseBean> salbeanList) {
		this.salbeanList = salbeanList;
	}

	public List<SalesDetailsBean> getSalDetList() {
		return salDetList;
	}

	public void setSalDetList(List<SalesDetailsBean> salDetList) {
		this.salDetList = salDetList;
	}

	public Collection<Object> getCusList() {
		return cusList;
	}

	public void setCusList(Collection<Object> cusList) {
		this.cusList = cusList;
	}

	public String[] getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}

	public SalesAmountBean getSalesAmountBean() {
		return salesAmountBean;
	}

	public void setSalesAmountBean(SalesAmountBean salesAmountBean) {
		this.salesAmountBean = salesAmountBean;
	}

	public List<SalesAmountBean> getSalamtList() {
		return salamtList;
	}

	public void setSalamtList(List<SalesAmountBean> salamtList) {
		this.salamtList = salamtList;
	}

	public SalesDetailsBean getSalesDetails() {
		return salesDetails;
	}

	public void setSalesDetails(SalesDetailsBean salesDetails) {
		this.salesDetails = salesDetails;
	}

}
