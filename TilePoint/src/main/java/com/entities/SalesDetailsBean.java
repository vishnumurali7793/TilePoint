package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(schema = "tilespoint", name = "salesdetails")
public class SalesDetailsBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_details_id")
	private Integer salesDetailsId;
	
	@ManyToOne
	@JoinColumn(name="salesId")
	private SalesBaseBean salesId;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private ProductBean productId;
	
	@Column(name = "hsn_code")
	private Double hsnCode;

	@Column(name = "quantity")
	private Double quantity;
	
	@Column(name = "rate")
	private Double rate;
	
	@Column(name = "totalamount")
	private Double totalamount;
	
	@Column(name = "deletestatus")
	private String deleteStatus="N";

	public Integer getSalesDetailsId() {
		return salesDetailsId;
	}

	public void setSalesDetailsId(Integer salesDetailsId) {
		this.salesDetailsId = salesDetailsId;
	}

	public SalesBaseBean getSalesId() {
		return salesId;
	}

	public void setSalesId(SalesBaseBean salesId) {
		this.salesId = salesId;
	}

	public Double getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(Double hsnCode) {
		this.hsnCode = hsnCode;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public ProductBean getProductId() {
		return productId;
	}

	public void setProductId(ProductBean productId) {
		this.productId = productId;
	}
	
	
}
