package com.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(schema = "tilespoint", name = "salesBase")
public class SalesBaseBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_id")
	private Integer salesId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private CustomerBean customerId;
	
	@Column(name = "invoice_no")
	private String invoiceNo;
	
	@Column(name = "invoice_date")
	private Date invoiceDate;
	
	@Column(name = "state_to_supply")
	private String stateToSupply;
	
	@Column(name = "place_to_supply")
	private String placeToSupply;
	
	@Column(name = "purchase_date")
	private Date purchaseDate;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "year")
	private String year;

	

	public Integer getSalesId() {
		return salesId;
	}

	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}

	public CustomerBean getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerBean customerId) {
		this.customerId = customerId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getStateToSupply() {
		return stateToSupply;
	}

	public void setStateToSupply(String stateToSupply) {
		this.stateToSupply = stateToSupply;
	}

	public String getPlaceToSupply() {
		return placeToSupply;
	}

	public void setPlaceToSupply(String placeToSupply) {
		this.placeToSupply = placeToSupply;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	
}
