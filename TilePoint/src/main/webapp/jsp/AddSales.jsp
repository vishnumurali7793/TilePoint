<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="resources/jquery/jquery-3.5.0.min.js"></script>
<script type="text/javascript" src="resources/jquery-ui/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/transaction-styles.css">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-datetimepicker.css">
<link rel="stylesheet" type="text/css" href="resources/jquery-ui/jquery-ui.css">
<script type="text/javascript" src="resources/js/AddSales.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-datetimepicker.js"></script>
<meta charset="UTF-8">
<title>Add New Sales</title>
</head>
<body>
	<nav class="navbar navbar-inverse bar">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="goToHome">Tesseract</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="goToHome">Home</a></li>
				<li class=""><a href="goToDashBoard">Dashboard</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Master<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="active"><a href="goToCategory">Category</a></li>
						<li><a href="goToProduct">Product</a></li>
						<li><a href="goToCustomer">Customer</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Transactions <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="goToSales">Sales</a></li>
					</ul></li>
				<li class="quick-sales active"><a href="gotToQuickSales"><span
						class="glyphicon glyphicon-piggy-bank"></span>&nbsp;&nbsp;Quick
						Sales</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</nav>


	<div class="container-fluid">
		<form action="initiateSales" id="addSales">
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="panel panel-default form-panel-primary">
						<div class="panel-heading">Generate Sales Invoice</div>
						<div class="panel-body">
							<div class="panel panel-default">
		  						<div class="panel-body inner-panel">
			  						<div class="row m-all-15">
			  							<h4>Customer Details</h4>
			  						</div>
			  						<div class="row">
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="invoiceNo">Invoice Number</label>
				  								<s:textfield id="invoiceNo" placeholder="Invoice Number" 
				  											class="input-sm form-control" name="salesBaseBean.invoiceNo" />
				  							</div>
			  							</div>
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="invoiceDate">Invoice Date</label>
				  								<%-- <s:textfield id="invoiceDate" placeholder="Invoice Date" 
				  											 class="input-sm form-control" name="salesBaseBean.invoiceDate" /> --%>
				  								<div class='input-group date' id='datetimepicker1'>
                   					 				<input type='text' class="form-control input-sm" id="invoiceDate" name="salesBaseBean.invoiceDate" />
                    									<span class="input-group-addon">
                        									<span class="glyphicon glyphicon-calendar"></span>
                    									</span>
               		 							</div>
				  							</div>
			  							</div>
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="customerCode">Customer</label>
				  								<s:textfield id="customerCode" placeholder="Customer Code" 
				  											 class="input-sm form-control" name="salesBaseBean.customerId.customerCode" />
				  							</div>
			  							</div>
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="stateOfSupply">State of Supply</label>
				  								<s:textfield id="stateOfSupply" placeholder="State of Supply" 
				  											 class="input-sm form-control" name="salesBaseBean.stateToSupply" onkeypress="getStateList(this)" />
				  							</div>
			  							</div>
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="placeOfSupply">Place of Supply</label>
				  								<s:textfield id="placeOfSupply" placeholder="Place of Supply" 
				  											 class="input-sm form-control" name="salesBaseBean.placeToSupply" onkeypress="getStateList(this)" />
				  							</div>
			  							</div>
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="vehiclleNumber">Vehicle Number</label>
				  								<s:textfield id="vehiclleNumber" placeholder="Vehicle Number" 
				  											 class="input-sm form-control" name="salesAmountBean.vehicleno" />
				  							</div>
			  							</div>
			  							<%-- <div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
			  									<label for="invoiceDate">Invoice Date</label>
                								<div class='input-group date' id='datetimepicker1'>
                   					 				<input type='text' class="form-control input-sm" id="invoiceDate" />
                    									<span class="input-group-addon">
                        									<span class="glyphicon glyphicon-calendar"></span>
                    									</span>
               		 							</div>
				  							</div>
			  							</div> --%>
		  							</div>
		  						</div>
							</div>
							<div class="panel panel-default">
		  						<div class="panel-body inner-panel">
		  							<div class="row m-all-15">
			  							<h4>Item Details</h4>
			  						</div>
			  						<div class="m-all-15">
			  							<div class="row">
			  								<table class="table table-borderless borderless" id="itemTable">
				  								<thead>
				  									<tr>
				  										<th class="text-center width-4">#</th>
				  										<th class="text-center">Item</th>
				  										<th class="text-center">HSN Code</th>
				  										<th class="text-center">Quantity</th>
				  										<th class="text-center">Rate</th>
				  										<th class="text-center">Gross Amount</th>
				  										<th class="text-center width-10px"></th>
				  									</tr>
				  								</thead>
				  								<tbody class="text-center tbody">
				  									<tr>
				  										<td><label id="serialNumber1">1.</label></td>
				  										<td>
				  											<input type="hidden" name="itemName" class="itemId">
				  											<input type="text" class="items_table itemName" placeholder="Item Name">
				  										</td>
				  										<td><input type="text" class="items_table" placeholder="HSN Code"></td>
				  										<td><input type="text" class="items_table" placeholder="Quantity"></td>
				  										<td><input type="text" class="items_table" placeholder="Rate"></td>
				  										<td><input type="text" class="items_table" placeholder="Gross Amount"></td>
				  										<td>
				  											<button class="removeRow btn btn-default btn-xs form-btn-danger pull-left">
				  												<span class='glyphicon glyphicon-remove'></span>
				  											</button>
				  										</td>
				  									</tr>
				  									<tr>
				  										<td><label id="serialNumber2">2.</label></td>
				  										<td>
				  											<input type="hidden" name="itemName" class="itemId">
				  											<input type="text" class="items_table itemName" placeholder="Item Name">
				  										</td>
				  										<td><input type="text" class="items_table" placeholder="HSN Code"></td>
				  										<td><input type="text" class="items_table" placeholder="Quantity"></td>
				  										<td><input type="text" class="items_table" placeholder="Rate"></td>
				  										<td><input type="text" class="items_table" placeholder="Gross Amount"></td>
				  										<td>
				  											<button class="removeRow btn btn-default btn-xs form-btn-danger pull-left">
				  												<span class='glyphicon glyphicon-remove'></span>
				  											</button>
				  										</td>
				  									</tr>
				  									<tr>
				  										<td><label id="serialNumber3">3.</label></td>
				  										<td>
				  											<input type="hidden" name="itemName" class="itemId">
				  											<input type="text" class="items_table itemName" placeholder="Item Name">
				  										</td>
				  										<td><input type="text" class="items_table" placeholder="HSN Code"></td>
				  										<td><input type="text" class="items_table" placeholder="Quantity"></td>
				  										<td><input type="text" class="items_table" placeholder="Rate"></td>
				  										<td><input type="text" class="items_table" placeholder="Gross Amount"></td>
				  										<td>
				  											<button class="removeRow btn btn-default btn-xs form-btn-danger pull-left">
				  												<span class='glyphicon glyphicon-remove'></span>
				  											</button>
				  										</td>
				  									</tr>
				  									<tr>
				  										<td><label  id="serialNumber4">4.</label></td>
				  										<td>
				  											<input type="hidden" name="itemName" class="itemId">
				  											<input type="text" class="items_table itemName" placeholder="Item Name">
				  										</td>
				  										<td><input type="text" class="items_table" placeholder="HSN Code"></td>
				  										<td><input type="text" class="items_table" placeholder="Quantity"></td>
				  										<td><input type="text" class="items_table" placeholder="Rate"></td>
				  										<td><input type="text" class="items_table" placeholder="Gross Amount"></td>
				  										<td>
				  											<button class="removeRow btn btn-default btn-xs form-btn-danger pull-left">
				  												<span class='glyphicon glyphicon-remove'></span>
				  											</button>
				  										</td>
				  									</tr>
				  									<tr>
				  										<td><label id="serialNumber5">5.</label></td>
				  										<td>
				  											<input type="hidden" name="itemName" class="itemId">
				  											<input type="text" class="items_table itemName" placeholder="Item Name">
				  										</td>
				  										<td><input type="text" class="items_table" placeholder="HSN Code"></td>
				  										<td><input type="text" class="items_table" placeholder="Quantity"></td>
				  										<td><input type="text" class="items_table" placeholder="Rate"></td>
				  										<td><input type="text" class="items_table" placeholder="Gross Amount"></td>
				  										<td>
				  											<button class="removeRow btn btn-default btn-xs form-btn-danger pull-left">
				  												<span class='glyphicon glyphicon-remove'></span>
				  											</button>
				  										</td>
				  									</tr>
				  								</tbody>
				  							</table>
											<div class="row">
												<div
													class="col-lg-12 col-md-4 col-sm-3 text-center m-all-15 capsule"
													onclick="addNewRow('itemTable')" style="cursor: pointer;">
													<label style="padding-top: 2px; cursor: pointer;" class="mt-5"> <span
														class="glyphicon glyphicon-plus"></span>&nbsp;Add Rows
													</label>
												</div>
											</div>
										</div>
			  							<div class="row mt-15">
			  								<div class="col-md-2 col-lg-2 col-sm-12">
			  									<label for="enableTax">Enable TAX</label>
			  									<input type="checkbox" id="enableTax" checked="checked" />
			  								</div>
			  								<div id="taxGroup" hidden="true">
			  									<div class="col-md-2 col-lg-2 col-sm-12">
			  									<input type="text" class="items_table" placeholder="GST %" />
			  								</div>
			  								<div class="col-md-2 col-lg-2 col-sm-12">
			  									<input type="text" class="items_table" placeholder="IGST %" />
			  								</div>
			  								</div>
			  								<div class="col-md-2 col-lg-2 col-sm-12">
			  									<input type="text" class="items_table" placeholder="Vehicle Charge" />
			  								</div>
			  								<div class="col-md-2 col-lg-2 col-sm-12">
			  									<input type="text" class="items_table" placeholder="Loading Charge" />
			  								</div>
			  								<div class="col-md-2 col-lg-2 col-sm-12">
			  									<input type="text" class="items_table" placeholder="Net Amount" />
			  								</div>
			  							</div>
			  						</div>
		  						</div>
							</div>
							<div class="row">
								<div class="col-md-12 col-sm-12 col-lg-12 pull-center">
									<button type="button" class="btn btn-default btn-sm form-btn-success form-btn-large">
										<span class="glyphicon glyphicon-floppy-save"></span>&nbsp;Add Bill</button> &emsp;
									<button type="button" class="btn btn-default btn-sm form-btn-danger form-btn-large">
										<span class="glyphicon glyphicon-print"></span>&nbsp;Generate Invoice</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>