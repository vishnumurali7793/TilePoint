<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="resources/jquery/jquery-3.5.0.js"></script>
<script type="text/javascript" src="resources/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript"
	src="resources/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/jquery-ui/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="resources/jquery-ui/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css"
	href="resources/jquery-ui/jquery-ui.theme.css">
<title>Sales</title>
</head>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

.bd-navbar {
	min-height: 4rem;
	background-color: #563d7c;
	box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, .05), inset 0 -1px 0
		rgba(0, 0, 0, .1);
}

#allign {
	text-align: center;
	font-weight: bold;
}

#tablecolor {
	background-color: #563d7c;
}

.bar {
	border-radius: 0px;
}

#accordion {
	max-height: 100%;
}

.panel, .tax {
	margin: 10px;
}

.element {
	margin-top: 15px;
}

.tax {
	justify-content: center;
	align-items: center;
}
</style>
<script type="text/javascript">
	function editVendor(venid) {
		location.href = "editVendor?vendorBean.vendorId=" + venid;
	}
	function deleteVendor(venid) {
		location.href = "deleteVendor?vendorBean.vendorId=" + venid;
	}

	function addSales() {
		$('#productModal #modalTitle').html("Add items to Sales bill");
		$.ajax({
			type : "GET",
			url : "getmodalForSales",
			beforeSend : function() {
				$('#productModal .modal-body').html('Loading..');
			},
			success : function(msg) {
				$('#productModal .modal-body').html(msg);
			}
		});
		$('#productModal').modal('show');
		return false;
	}

	function generatereport(salid) {
		window.open("generateSalesReport?salesBaseBean.salesId=" + salid);
	}
</script>
<body>
	<nav class="navbar navbar-inverse bar">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="goToHome">HOME</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="goToHome">Home</a></li>
				<li class=""><a href="goToDashBoard">Dashboard</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Master<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="goToCategory">Category</a></li>
						<li><a href="goToProduct">Product</a></li>
						<li><a href="goToCustomer">Customer</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Transactions <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="active"><a href="goToSales">Sales</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						Sign Up</a></li>
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="panel">
				<h2>SALES</h2>
				<div class="panel-group" id="accordion">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse1">Add Sales</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse in">
							<div class="row">
								<div class="panel-body ">
									<%-- <s:form action=""> --%>
									<div class="row">
										<div class="col-md-12">
											<button type="button" class="btn btn-sm btn-success"
												title="Add new sales bill" style="margin: 10px;"
												data-toggle="modal" onclick="addSales()">
												<span class="glyphicon glyphicon-plus"></span>
											</button>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<table class="table">
										<thead>
											<tr>
												<th>#</th>
												<th>INVOICE NO & DATE</th>
												<th>CUSTOMER DETAILS</th>
												<th>PLACE TO SUPPLY</th>
												<th>STATE TO SUPPLY</th>
												<th>DATE OF PURCHASE</th>
												<th>ACTIONS</th>
											</tr>
										</thead>
										<!-- ***list name from redirectaction*** -->
										<s:if test="salbeanList!=null && salbeanList.size()>0">

											<s:iterator value="salbeanList" status="row">
												<tr>
													<td><s:property value="#row.count" /></td>
													<td><s:property value="invoiceNo" />&</br> <s:property
															value="invoiceDate" /></td>
													<td><s:property value="customerId.customerName" /></br> <s:property
															value="customerId.address1" />, <s:property
															value="customerId.address2" />, <s:property
															value="customerId.contact" />, <s:property
															value="customerId.pin" /></td>
													<td><s:property value="placeToSupply" /></td>
													<td><s:property value="stateToSupply" /></td>
													<td><s:property value="purchaseDate" /></td>
													<td><a role="button" class="btn btn-info btn-xs"
														title="Add/Edit sales bill"
														href="editsalesdetails?salesBaseBean.salesId=<s:property value="salesId" />"
														src="tessaract/src/main/webapp/images/edit.png">E</a>
														<button class="btn-xs btn btn-danger"
															title="Generate sales invoice">
															<span class="glyphicon glyphicon-print"
																onclick="generatereport('<s:property value="salesId" />')"></span>
														</button></td>
												</tr>
											</s:iterator>
										</s:if>
									</table>
								</div>
							</div>
							<%-- </s:form> --%>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	</div>
	<div class="modal fade" id="productModal" role="dialog">
		<div class="modal-dialog modal-lg modal-xl">
			<div class="modal-content">
				<div class="modal-header" style="background-color: #581845;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>

						<h4 class="modal-title" align="center">
							<span style="color: white;" class="" id="modalTitle"></span>
						</h4>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
</body>
</html>