<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="resources/jquery/jquery-3.5.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
<title>product</title>
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
	function editCategory(productid) {
		location.href = "editProduct?productBean.productId=" + productid;
	}

	function deleteCategory(productid) {
		location.href = "deleteProduct?productBean.productId=" + productid;
	}
</script>
<body>
	<nav class="navbar navbar-inverse bar">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="goToHome">Tesseract</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="goToHome">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Master<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="goToCategory">Category</a></li>
						<li class="active"><a href="goToProduct">Product</a></li>
						<li><a href="goToCustomer">Customer</a></li>
						<li><a href="#">Page 1-2</a></li>
						<li><a href="#">Page 1-3</a></li>
					</ul></li>
				<li><a href="#">Page 2</a></li>
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
				<h2>PRODUCT</h2>
				<div class="panel-group" id="accordion">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse1">Add Product</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse in">
							<div class="panel-body ">
								<s:form action="saveProduct">
									<div class="row">
										<div class=" col s12">
											<s:hidden name="categoryBean.categoryId" />
											<s:hidden name="productBean.productId" />
											<label>CategoryName</label> <select
												class="custom-select custom-select-sm" id="select"
												value="<s:property value="productBean.category.categoryName" />"
												name="productBean.category.categoryId">
												<s:iterator value="catList" status="row">
													<option value="<s:property value='categoryId'/>"><s:property
															value='categoryName' /></option>
												</s:iterator>
											</select> <label>ProductCode</label> <input
												name="productBean.productCode" type="text"
												value="<s:property value="productBean.productCode"/>"
												class="validate" placeholder="ProductCode"> <label>ProductName</label>
											<input name="productBean.productName" type="text"
												class="validate"
												value="<s:property value="productBean.productName"/>"
												placeholder="ProductName"> <label>Date</label> <input
												name="productBean.addedOn" type="date" class="validate"
												value="<s:property value="productBean.addedOn"/>"
												required="required"> <i class="fa fa-calendar"
												style="font-size: 22px; float: right; margin: -46px auto;"></i>
											<label>Status</label> <select name="productBean.activeStatus"
												value="<s:property value="productBean.activeStatus"/>"
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
								aria-controls="collapse2" href="#collapse2">Category Details</a>
						</h4>
					</div>
					<div id="collapse2" class="panel-collapse collapse in" role="tabpanel"
						aria-labelledby="collapse-two">
						<div class="panel-body">
							<div class="container">
								<h2>Product Table</h2>
								<table class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>CATEGORY NAME</th>
											<th>PRODUCT CODE</th>
											<th>PRODUCT NAME</th>
											<th>DATE</th>
											<th>ACTIVE STATUS</th>
											<th>ACTIONS</th>
										</tr>
									</thead>
									<tbody>
										<!-- ***list name from redirectaction*** -->
										<s:if test="prodList!=null && prodList.size()>0">

											<s:iterator value="prodList" status="row">
												<tr>
													<td><s:property value="#row.count" /></td>
													<td><s:property value="category.categoryName" /></td>
													<td><s:property value="productCode" /></td>
													<td><s:property value="productName" /></td>
													<td><s:property value="addedOn" /></td>
													<td><s:property value="activeStatus" /></td>
													<td><button class="btn-xs btn-link"
															onclick="editCategory('<s:property value="productId"/>')">[EDIT]</button>
														<button class="btn-xs btn-link"
															onclick="deleteCategory('<s:property value="productId"/>')">[DELETE]</button></td>
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