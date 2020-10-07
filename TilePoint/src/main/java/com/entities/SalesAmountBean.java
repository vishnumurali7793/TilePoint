package com.entities;

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
@Table(schema = "tilespoint", name = "salesamount")
public class SalesAmountBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_amount_id")
	private Integer salesAmountId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="salesId")
	private SalesBaseBean salesId;
	
	@Column(name = "cgst")
	private Double cgst;
	
	@Column(name = "sgst")
	private Double sgst;
	
	@Column(name = "igst")
	private Double igst;
	
	@Column(name = "vehicleno")
	private String vehicleno;
	
	@Column(name = "vehicleamount")
	private Double vehicleamount;
	
	@Column(name = "grossamount")
	private Double grossamount;
	
	@Column(name = "netamount")
	private Double netamount;
	
	@Column(name = "cgstamt")
	private Double cgstamt=0.00;
	
	@Column(name = "sgstamt")
	private Double sgstamt=0.00;
	
	@Column(name = "igstamt")
	private Double igstamt=0.00;
	
	@Column(name = "govt_tax")
	private Double govtTaxAmount = 0.0;
	
	@Column(name = "loadingcharge")
	private Double loadingcharge=0.00;

	public Double getLoadingcharge() {
		return loadingcharge;
	}

	public void setLoadingcharge(Double loadingcharge) {
		this.loadingcharge = loadingcharge;
	}

	public Double getCgstamt() {
		return cgstamt;
	}

	public void setCgstamt(Double cgstamt) {
		this.cgstamt = cgstamt;
	}

	public Double getSgstamt() {
		return sgstamt;
	}

	public void setSgstamt(Double sgstamt) {
		this.sgstamt = sgstamt;
	}

	public Double getIgstamt() {
		return igstamt;
	}

	public void setIgstamt(Double igstamt) {
		this.igstamt = igstamt;
	}

	public Integer getSalesAmountId() {
		return salesAmountId;
	}

	public void setSalesAmountId(Integer salesAmountId) {
		this.salesAmountId = salesAmountId;
	}

	public SalesBaseBean getSalesId() {
		return salesId;
	}

	public void setSalesId(SalesBaseBean salesId) {
		this.salesId = salesId;
	}

	public Double getCgst() {
		return cgst;
	}

	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}

	public Double getSgst() {
		return sgst;
	}

	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}

	public Double getIgst() {
		return igst;
	}

	public void setIgst(Double igst) {
		this.igst = igst;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public Double getVehicleamount() {
		return vehicleamount;
	}

	public void setVehicleamount(Double vehicleamount) {
		this.vehicleamount = vehicleamount;
	}

	public Double getGrossamount() {
		return grossamount;
	}

	public void setGrossamount(Double grossamount) {
		this.grossamount = grossamount;
	}

	public Double getNetamount() {
		return netamount;
	}

	public void setNetamount(Double netamount) {
		this.netamount = netamount;
	}

	public Double getGovtTaxAmount() {
		return govtTaxAmount;
	}

	public void setGovtTaxAmount(Double govtTaxAmount) {
		this.govtTaxAmount = govtTaxAmount;
	}
	
	
	
}
