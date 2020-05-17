<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body id="modalBody">
	<div class="container-fluid">
		<!-- <div class="row">
			<div class="panel">
				<h2>Add vendor</h2>
			</div>
		</div> -->

		<form action="saveSalesBase" id="addCustomer">
			<div class="row">
				<div class="col-xs-3">
				<label class="form-group" for="invoice-no"
						value="salesBaseBean.invoice">INVOICE NO</label>
					<s:textfield name="salesBaseBean.invoiceNo" type="text"
						value="%{salesBaseBean.invoiceNo}" id="invoice-no"
						class="form-control" placeholder="" readonly="true" />
				
				</div>
				<div class="col-xs-3">
					<label class="form-group" for="invoice-date">INVOICE DATE</label> <input
						name="salesBaseBean.invoiceDate" type="date" class="form-control"
						id="invoice-date"
						value="<s:property value="salesBaseBean.invoiceDate"/>"
						required="required"> <i class="fa fa-calendar"
						style="font-size: 22px; float: right; margin: -46px auto;"></i>
				</div>
				<div class="col-xs-3">
				<label class="form-group" for="Customer-code">Customer Code</label>
					<s:textfield name="salesBaseBean.customerId.customerCode" type="text"
						id="Customer-code" value="" class="form-control"
						onkeyup="getValue(this)" placeholder="CustomerCode" />
				
				</div>
				<div class="col-xs-3">
				<label class="form-group" for="gst-code">STATE TO SUPPLY</label>
					<s:textfield name="salesBaseBean.stateToSupply" type="text" value=""
						id="gst-code" class="form-control" placeholder="GSTCODE" />
				</div>
			</div>
			<div class="row">
				<div class="col-xs-3">
					<label class="form-group" for="gst-code">PLACE TO SUPPLY</label>
					<s:textfield name="salesBaseBean.placeToSupply" type="text" value=""
						id="gst-code" class="form-control" placeholder="GSTCODE" />
				</div>
				<div class="col-xs-3">
					<label class="form-group" for="purch-date">PURCHASE DATE</label> <input
						name="salesBaseBean.purchaseDate" type="date" id="purch-date"
						class="form-control"
						value="<s:property value="salesBaseBean.purchaseDate"/>"
						required="required"> <i class="fa fa-calendar"
						style="font-size: 22px; float: right; margin: -46px auto;"></i>
				</div>
				<div class="col-xs-3">
					
				</div>
				<div class="col-xs-3">
					<button class="btn btn-sm btn-success" type="submit"
						id="submit-btn">Submit</button>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-xs-3">
					
				</div>
				<div class="col-xs-3">
					
				</div>
				<div class="col-xs-3">
					
				</div>
				<div class="col-xs-3">
					
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-xs-6">
					
				</div>
				<div class="col-xs-6">
					
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#Customer-code').autocomplete({
				source : function(request, response) {
					$.ajax({
						url : 'getCusDetails',
						data : {
							'salesBaseBean.customerId.customerCode' : request.term,
						},
						success : function(data) {
							if (data == null || !data.length) {
								var result = [ {
									label : '--No Matches Found--',
									value : 0
								} ];
								response(result);
							} else {
								response($.map(data, function(i) {
									return {
										label : i[0] + "-" + i[1]+ "-" +i[2],
										value : i[1]
									};
								}));
							}
						},
					});
				},
				appendTo : '#addCustomer',
				autoFocus : true,
				delay : 100
			});
		});
	</script>
</body>
</html>