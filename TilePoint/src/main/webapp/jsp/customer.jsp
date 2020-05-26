<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="resources/jquery/jquery-3.5.0.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="resources/bootstrap/js/bootstrap.min.js"></script>
<title>tessaract</title>
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

/* #submitButton {
	float: right;
} */

/* #statusDropdown {
	float: right;
	align-items: left;
} */
.tax {
	justify-content: center;
	align-items: center;
}
</style>
<script type="text/javascript">
	function editCustomer(custid) {
		location.href = "editCustomer?customerBean.customerId=" + custid;
	}

	function deleteCustomer(custid) {
		location.href = "deleteCustomer?customerBean.customerId=" + custid;
	}
</script>

<%-- <header
	class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar"
	id="allign">
	TAX -
	<s:property value="loginBean.userName" />
</header> --%>
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
						<li><a href="goToCategory">Category</a></li>
						<li><a href="goToProduct">Product</a></li>
						<li class="active"><a href="goToCustomer">Customer</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Transactions <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="goToSales">Sales</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<%-- <li><a href="#"><span class="glyphicon glyphicon-user"></span>
						Sign Up</a></li> --%>
				<li><a href="logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="panel">
				<h2>CUSTOMER</h2>
				<div class="panel-group" id="accordion">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse1">Add Customer</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse in">
							<div class="panel-body ">
								<s:form action="saveCustomer">
									<div class="row">
										<div class=" col s12">
											<s:hidden name="customerBean.customerId" />

											<label>Customer Code</label> <input
												name="customerBean.customerCode" type="text"
												value="<s:property value="customerBean.customerCode"/>"
												class="validate" placeholder="customerCode"> <label>Customer
												Name</label> <input name="customerBean.customerName" type="text"
												value="<s:property value="customerBean.customerName"/>"
												class="validate" placeholder="customerName"> <label>Category
												Address1</label> <input name="customerBean.address1" type="text"
												class="validate"
												value="<s:property value="customerBean.address1"/>"
												placeholder="address"> <label>Customer
												Address2</label> <input name="customerBean.address2" type="text"
												class="validate"
												value="<s:property value="customerBean.address2"/>"
												placeholder="address"> <label>Email</label> <input
												name="customerBean.email" type="text" class="validate"
												value="<s:property value="customerBean.email"/>"
												placeholder="email"> <label>Customer Contact</label>
											<input name="customerBean.contact" type="text"
												class="validate"
												value="<s:property value="customerBean.contact"/>"
												placeholder="contactNumber"> <label>Customer
												Pin</label> <input name="customerBean.pin" type="text"
												class="validate"
												value="<s:property value="customerBean.pin"/>"
												placeholder="CustomerPin"> <label>State</label> <input
												name="customerBean.state" type="text" class="validate"
												value="<s:property value="customerBean.state"/>"
												placeholder="STATE">
											<%-- 
												<label>Event Date</label> <input
												name="customerBean.eventDate" type="date" class="validate"
												value="<s:property value="customerBean.eventDate"/>"
												required="required"> <i class="fa fa-calendar"
												style="font-size: 22px; float: right; margin: -46px auto;"></i> --%>

											<label>Date</label> <input name="customerBean.addedOn"
												type="date" class="validate"
												value="<s:property value="customerBean.addedOn"/>"
												required="required"> <i class="fa fa-calendar"
												style="font-size: 22px; float: right; margin: -46px auto;"></i>

											<label>Status</label> <select
												name="customerBean.activeStatus"
												value="<s:property value="customerBean.activeStatus"/>"
												required="required" class="">
												<option value="Active">Active</option>
												<option value="Inactive">Inactive</option>
											</select>&nbsp &nbsp
											<button class="waves-effect waves-light btn" type="submit">Submit</button>

										</div>
									</div>

								</s:form>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading" role="tab" id="collapse-two">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								class="collapsed" role="button" aria-expanded="true"
								aria-controls="collapse2" href="#collapse2">Customer Details</a>
						</h4>
					</div>
					<div id="collapse2" class="panel-collapse collapse in"
						role="tabpanel" aria-labelledby="collapse-two">
						<div class="panel-body">
							<div class="container">
								<h2>Customer Table</h2>
								<table class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>CUSTOMER CODE</th>
											<th>CUSTOMER NAME</th>
											<th>CUSTOMER ADDRESS</th>
											<th>CUSTOMER CONTACT</th>
											<th>STATE</th>
											<th>DATE</th>
											<th>Active Status</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<!-- ***list name from redirectaction*** -->
										<s:if test="customerList!=null && customerList.size()>0">

											<s:iterator value="customerList" status="row">
												<tr>
													<td><s:property value="#row.count" /></td>
													<td><s:property value="customerCode" /></td>
													<td><s:property value="customerName" /></td>
													<td><s:property value="address1" /></br> <s:property
															value="address2" /></br> <s:property value="email" /></br> <s:property
															value="pin" /></td>
													<td><s:property value="contact" /></td>
													<td><s:property value="state" /></td>
													<td><s:property value="addedOn" /></td>
													<td><s:property value="activeStatus" /></td>
													<td><button class="btn-xs btn-link"
															onclick="editCustomer('<s:property value="customerId"/>')">[EDIT]</button>
														<button class="btn-xs btn-link"
															onclick="deleteCustomer('<s:property value="customerId"/>')">[DELETE]</button></td>
												</tr>
											</s:iterator>


										</s:if>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>




</body>
</html>