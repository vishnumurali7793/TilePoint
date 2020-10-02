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
<link href="resources/fontawesome/css/fontawesome.css" rel="stylesheet">
<link href="resources/fontawesome/css/brands.css" rel="stylesheet">
<link href="resources/fontawesome/css/solid.css" rel="stylesheet">
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
		<form id="addSales" action="saveAndGenerateSalesInvoice">
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
				  								<label for="invoiceNo">Invoice Number&nbsp;<i class="fas fa-asterisk mandatory icon-small"></i></label>
				  								<s:textfield id="invoiceNo" placeholder="Invoice Number" 
				  											class="input-sm form-control" name="itemsBase.invoiceNo" />
				  							</div>
			  							</div>
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="invoiceDate">Invoice Date&nbsp;<i class="fas fa-asterisk mandatory icon-small"></i></label>
				  								<div class='input-group date' id='datetimepicker1'>
                   					 				<input type='text' class="form-control input-sm" id="invoiceDate" 
                   					 					   name="itemsBase.invoiceDate" />
                    									<span class="input-group-addon">
                        									<span class="glyphicon glyphicon-calendar"></span>
                    									</span>
               		 							</div>
				  							</div>
			  							</div>
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="customerCode">Customer&nbsp;<i class="fas fa-asterisk mandatory icon-small"></i></label>
				  								<s:textfield id="customerCode" placeholder="Customer Code" 
				  											 class="input-sm form-control" name="itemsBase.customerId.customerCode" />
				  							</div>
			  							</div>
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="stateOfSupply">State of Supply&nbsp;<i class="fas fa-asterisk mandatory icon-small"></i></label>
				  								<s:textfield id="stateOfSupply" placeholder="State of Supply" 
				  											 class="input-sm form-control" name="itemsBase.stateToSupply" 
				  											 onkeypress="getStateList(this)" />
				  							</div>
			  							</div>
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="placeOfSupply">Place of Supply&nbsp;<i class="fas fa-asterisk mandatory icon-small"></i></label>
				  								<s:textfield id="placeOfSupply" placeholder="Place of Supply" 
				  											 class="input-sm form-control" name="itemsBase.placeToSupply" />
				  							</div>
			  							</div>
			  							<div class="col-lg-3 col-md-6 col-sm-12">
			  								<div class="form-group m-all-15">
				  								<label for="vehiclleNumber">Vehicle Number</label>
				  								<s:textfield id="vehiclleNumber" placeholder="Vehicle Number" 
				  											 class="input-sm form-control" name="invoiceAmount.vehicleno" />
				  							</div>
			  							</div>
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
				  										<th class="text-center">Item&nbsp;<i class="fas fa-asterisk mandatory icon-small"></i></th>
				  										<th class="text-center">HSN Code</th>
				  										<th class="text-center">Quantity&nbsp;<i class="fas fa-asterisk mandatory icon-small"></i></th>
				  										<th class="text-center">Rate&nbsp;<i class="fas fa-asterisk mandatory icon-small"></i></th>
				  										<th class="text-center">Gross Amount</th>
				  										<th class="text-center width-10px"></th>
				  									</tr>
				  								</thead>
				  								<tbody class="text-center tbody">
				  									<tr>
				  										<td><label id="serialNumber1">1.</label></td>
				  										<td>
				  											<input type="hidden" name="itemsDetails[0].productId.productId" class="itemId" />
				  											<input type="text" class="items_table itemName" placeholder="Item Name" />
				  										</td>
				  										<td><input type="text" class="items_table" placeholder="HSN Code" name="itemsDetails[0].hsnCode" /></td>
				  										<td><input type="text" class="items_table itemQuantity" placeholder="Quantity" name="itemsDetails[0].quantity" id="quantity_1" onkeyup="calculateItemwiseRate(1);" /></td>
				  										<td><input type="text" class="items_table itemRate" placeholder="Rate" name="itemsDetails[0].rate" id="rate_1" onkeyup="calculateItemwiseRate(1);" /></td>
				  										<td><input type="text" class="items_table gross_amount" placeholder="Gross Amount" name="itemsDetails[0].totalamount" id="grossAmount_1" /></td>
				  										<td>
				  											<button class="removeRow btn btn-default btn-xs form-btn-danger pull-left" id="remove_1">
				  												<span class='glyphicon glyphicon-remove'></span>
				  											</button>
				  										</td>
				  									</tr>
				  									<tr>
				  										<td><label id="serialNumber2">2.</label></td>
				  										<td>
				  											<input type="hidden" name="itemsDetails[1].productId.productId" class="itemId" />
				  											<input type="text" class="items_table itemName" placeholder="Item Name" />
				  										</td>
				  										<td><input type="text" class="items_table" placeholder="HSN Code" name="itemsDetails[1].hsnCode" /></td>
				  										<td><input type="text" class="items_table itemQuantity" placeholder="Quantity" name="itemsDetails[1].quantity" id="quantity_2" onkeyup="calculateItemwiseRate(2);"/></td>
				  										<td><input type="text" class="items_table itemRate" placeholder="Rate" name="itemsDetails[1].rate" id="rate_2" onkeyup="calculateItemwiseRate(2);" /></td>
				  										<td><input type="text" class="items_table gross_amount" placeholder="Gross Amount" name="itemsDetails[1].totalamount" id="grossAmount_2" /></td>
				  										<td>
				  											<button class="removeRow btn btn-default btn-xs form-btn-danger pull-left" id="remove_2">
				  												<span class='glyphicon glyphicon-remove'></span>
				  											</button>
				  										</td>
				  									</tr>
				  									<tr>
				  										<td><label id="serialNumber3">3.</label></td>
				  										<td>
				  											<input type="hidden" name="itemsDetails[2].productId.productId" class="itemId" />
				  											<input type="text" class="items_table itemName" placeholder="Item Name" />
				  										</td>
				  										<td><input type="text" class="items_table" placeholder="HSN Code" name="itemsDetails[2].hsnCode" /></td>
				  										<td><input type="text" class="items_table itemQuantity" placeholder="Quantity" name="itemsDetails[2].quantity" id="quantity_3" onkeyup="calculateItemwiseRate(3);" /></td>
				  										<td><input type="text" class="items_table itemRate" placeholder="Rate" name="itemsDetails[2].rate" id="rate_3" onkeyup="calculateItemwiseRate(3);" /></td>
				  										<td><input type="text" class="items_table gross_amount" placeholder="Gross Amount" name="itemsDetails[2].totalamount" id="grossAmount_3" /></td>
				  										<td>
				  											<button class="removeRow btn btn-default btn-xs form-btn-danger pull-left" id="remove_3">
				  												<span class='glyphicon glyphicon-remove'></span>
				  											</button>
				  										</td>
				  									</tr>
				  									<tr>
				  										<td><label  id="serialNumber4">4.</label></td>
				  										<td>
				  											<input type="hidden" name="itemsDetails[3].productId.productId" class="itemId" />
				  											<input type="text" class="items_table itemName" placeholder="Item Name" />
				  										</td>
				  										<td><input type="text" class="items_table" placeholder="HSN Code" name="itemsDetails[3].hsnCode" /></td>
				  										<td><input type="text" class="items_table itemQuantity" placeholder="Quantity" name="itemsDetails[3].quantity" id="quantity_4" onkeyup="calculateItemwiseRate(4);" /></td>
				  										<td><input type="text" class="items_table itemRate" placeholder="Rate" name="itemsDetails[3].rate" id="rate_4" onkeyup="calculateItemwiseRate(4);" /></td>
				  										<td><input type="text" class="items_table gross_amount" placeholder="Gross Amount" name="itemsDetails[3].totalamount" id="grossAmount_4" /></td>
				  										<td>
				  											<button class="removeRow btn btn-default btn-xs form-btn-danger pull-left" id="remove_4">
				  												<span class='glyphicon glyphicon-remove'></span>
				  											</button>
				  										</td>
				  									</tr>
				  									<tr>
				  										<td><label id="serialNumber5">5.</label></td>
				  										<td>
				  											<input type="hidden" name="itemsDetails[4].productId.productId" class="itemId" />
				  											<input type="text" class="items_table itemName" placeholder="Item Name" />
				  										</td>
				  										<td><input type="text" class="items_table" placeholder="HSN Code" name="itemsDetails[4].hsnCode" /></td>
				  										<td><input type="text" class="items_table itemQuantity" placeholder="Quantity" name="itemsDetails[4].quantity" id="quantity_5" onkeyup="calculateItemwiseRate(5);" /></td>
				  										<td><input type="text" class="items_table itemRate" placeholder="Rate" name="itemsDetails[4].rate" id="rate_5" onkeyup="calculateItemwiseRate(5);" /></td>
				  										<td><input type="text" class="items_table gross_amount" placeholder="Gross Amount" name="itemsDetails[4].totalamount" id="grossAmount_5" /></td>
				  										<td>
				  											<button class="removeRow btn btn-default btn-xs form-btn-danger pull-left" id="remove_5">
				  												<span class='glyphicon glyphicon-remove'></span>
				  											</button>
				  										</td>
				  									</tr>
				  								</tbody>
				  							</table>
										</div>
										<div class="row">
											<div class="col-lg-8 col-md-6 col-sm-12 text-center m-all-15 capsule"
												 onclick="addNewRow('itemTable')" style="cursor: pointer;">
												<label style="padding-top: 2px; cursor: pointer;"
													   class="mt-5"> <span class="glyphicon glyphicon-plus"></span>&nbsp;Add Rows
												</label>
											</div>
											<div class="col-lg-2 col-md-3 col-sm-12 text-center pull-right mr-30">
												<span class="font-25 font-blue">
													<i class="fas fa-rupee-sign"></i>&nbsp;
													<label style="padding-top: 2px; cursor: pointer;"
													   class="mt-5 font-30" id="totalAmount"></label>
													  <input type="hidden" name="invoiceAmount.grossamount" id="totalAmountHidden">
												</span>
											</div>
											<div class="col-lg-2 col-md-3 col-sm-12 text-right pull-right mr-15">
													<label style="padding-top: 3px; cursor: pointer;"
													   class="mt-5 font-20" id="totalAmount">Taxable Amount</label>
											</div>
										</div>
									</div>
		  						</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-body inner-panel">
									<div class="row m-all-15">
			  							<h4>Amount Details</h4>
			  						</div>
			  						<div class="m-all-15">
			  							<div class="row">
			  								<div class="col-md-2 col-lg-2 col-sm-12">
			  									<label for="enableTax">Enable TAX</label>
			  									<input type="checkbox" id="enableTax" checked="checked" /><br>
			  									<label for="cgst" hidden="true" id="label_cgst" class="mt-15 mandatory">CGST%</label><br>
			  									<label for="sgst" hidden="true" id="label_sgst" class="mt-15 mandatory">SGST%</label>
			  								</div>
			  								<div id="taxGroup" hidden="true">
			  									<div class="col-md-2 col-lg-2 col-sm-12">
			  										<input type="text" class="items_table" 
			  											   placeholder="GST %" onkeyup="calculateTaxAmount();"
			  											   id="gstPercentage" />
			  										<div class="form-group">
			  											<!-- <label for="cgst" hidden="true" id="label_cgst">CGST%</label>&emsp; -->
			  											<input type="text" class="items_table mt-15" 
			  											   	   placeholder="CGST %" readonly="readonly" 
			  											   	   hidden="true" id="cgst" 
			  											   	   name="invoiceAmount.cgst" />
			  										</div>
			  										<div class="form-group">
				  										<!-- <label for="sgst" hidden="true" id="label_sgst">SGST%</label>&emsp; -->
				  										<input type="text" class="items_table mt-10" 
				  											   placeholder="SGST %" readonly="readonly" 
				  											   hidden="true" id="sgst" 
				  											   name="invoiceAmount.sgst" />
			  										</div>
			  									</div>
			  								<div class="col-md-2 col-lg-2 col-sm-12">
			  									<input type="text" class="items_table" placeholder="IGST %" 
			  										   name="invoiceAmount.igst" id="igstPercentage"
			  										   onkeyup="calculateTaxAmount();"/>
			  								</div>
			  								</div>
			  								<div class="col-md-2 col-lg-2 col-sm-12">
			  									<input type="text" class="items_table" placeholder="CGST Amount" 
			  										   name="invoiceAmount.cgstamt" id="cgstAmount" />
			  								</div>
			  								<div class="col-md-2 col-lg-2 col-sm-12">
			  									<input type="text" class="items_table" placeholder="SGST Amount"
			  										   name="invoiceAmount.sgstamt" id="sgstAmount" />
												<div class="text-right pull-right mt-120">
													<label style="padding-top: 10px;" class="mt-5 font-20">Net
														Amount </label>
												</div>
											</div>
			  								<div class="col-md-2 col-lg-2 col-sm-12">
			  									<input type="text" class="items_table" placeholder="Total Tax Amount"
			  										   id="taxAmount" /><br>
			  									<input type="text" class="items_table mt-30" placeholder="Vehicle Charge" 
			  										   name="invoiceAmount.vehicleamount" onchange="addExtraCharge(this);" id="vehicleCharge" /><br>
			  									<input type="text" class="items_table mt-30" placeholder="Loading Charge"
			  										   name="invoiceAmount.loadingcharge" onchange="addExtraCharge(this);" id="loadingCharge" /><br>
			  									<input type="text" class="items_table mt-30" placeholder="Net Amount"
			  										   name="invoiceAmount.netamount" id="netAmount" />
											</div>
			  							</div>
			  						</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 col-sm-12 col-lg-12 pull-center">
									<button type="button" class="btn btn-default btn-sm form-btn-success form-btn-large" onclick="addSalesBill('S');">
										<span class="glyphicon glyphicon-floppy-save"></span>&nbsp;Add Bill
									</button> &emsp;
									<button type="button" class="btn btn-default btn-sm form-btn-danger form-btn-large" onclick="addSalesBill('G');">
										<span class="glyphicon glyphicon-print"></span>&nbsp;Generate Invoice
									</button>
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