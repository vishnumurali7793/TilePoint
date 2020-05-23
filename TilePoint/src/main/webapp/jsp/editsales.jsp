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
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function getProductList() {
		$('#productModal #modalTitle').html("Add items to sales bill");
		$
				.ajax({
					type : "GET",
					url : "getProductListForSales",
					data : {
						"salesBaseBean.salesId" : '<s:property value="salesBaseBean.salesId"/>'
					},
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
	var totamt = 0;
	function calculateamount(index) {
		debugger;
		var quantity = document.getElementById('quantity' + index).value;
		var rate = document.getElementById('rate' + index).value;
		var netamt = parseFloat(quantity * rate);
		document.getElementById('totalamount' + index).value = Math
				.round(netamt);
		totamt += Math.round(netamt);
		netamttot();
	}

	function netamttot() {
		document.getElementById('grossamount').value = totamt;
		var CGST = parseFloat(document.getElementById('cgst').value);
		var SGST = parseFloat(document.getElementById('sgst').value);
		var IGST = parseFloat(document.getElementById('igst').value);
		var taxamt = 0;
		if (CGST > 0 || SGST > 0) {
			taxamt = (totamt * (parseFloat(CGST + SGST))) / 100;
		} else {
			taxamt = (totamt * IGST) / 100;
		}
		document.getElementById('ccgst').innerHTML = taxamt / 2;
		document.getElementById('ssgst').innerHTML = taxamt / 2;
		if (IGST > 0) {
			document.getElementById('iigst').innerHTML = taxamt;
		} else {
			document.getElementById('iigst').innerHTML = 0;
		}
		document.getElementById('netamount').value = totamt - taxamt;
	}
	function goback() {
		location.href = "goToSales";
	}
</script>
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

td {
	border: 1px solid #dddddd;
	text-align: right;
	padding: 8px;
}

th {
	text-align: center;
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
</style>
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
						<li><a href="goToProduct">Product</a></li>
						<li><a href="goToCustomer">Customer</a></li>
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
		<div class="row"></div>
		<div class="row">
			<div class="col-md-6">
				<s:hidden name="salesBaseBean.salesId" />
				<button type="button" class="btn btn-success btn-sm"
					data-toggle="modal" onclick="getProductList()">
					<span class="glyphicon glyphicon-th"></span> &nbsp;Add items
				</button>
			</div>
			<div class="col-md-6">
				<s:hidden name="salesBaseBean.salesId" />
				<button type="button" class="btn btn-danger btn-sm pull-right"
					data-toggle="modal" onclick="goback()">
					<span class="glyphicon glyphicon-circle-arrow-left"></span> Back
				</button>
			</div>
		</div>

		<div class="row">
			<form action="savesaleDetails">
				<s:hidden name="salesBaseBean.salesId" />
				<s:hidden name="salesAmountBean.salesAmountId" />
				<div class="col-xs-12">

					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>#</th>
								<th>PRODUCT</th>
								<th>HSNCODE</th>
								<th>QUANTITY</th>
								<th>RATE</th>
								<th>AMOUNT</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<s:if test="salDetList!=null && salDetList.size()>0">
								<s:iterator value="salDetList" status="row">
									<tr>
										<td><s:property value="#row.count" /></td>
										<td><s:property value="productId.category.categoryName" /></br>
											<s:property value="productId.productName" /></td>

										<td><input class="form-control" type="text"
											name="salDetList[<s:property value="#row.index"/>].hsnCode"
											value='<s:property value="hsnCode"/>' /></td>

										<td><input class="form-control" type="text"
											name="salDetList[<s:property value="#row.index"/>].quantity"
											id="quantity<s:property value="#row.index" />"
											value="<s:property value="quantity"/>"
											onchange="calculateamount('<s:property value="#row.index" />')" /></td>

										<td><input class="form-control" type="text"
											name="salDetList[<s:property value="#row.index"/>].rate"
											value="<s:property value="rate"/>"
											id="rate<s:property value="#row.index" />"
											onchange="calculateamount('<s:property value="#row.index" />')" /></td>

										<td><input class="form-control" type="text"
											name="salDetList[<s:property value="#row.index"/>].totalamount"
											value="<s:property value="totalamount"/>"
											id="totalamount<s:property value="#row.index" />"
											onchange="calculateamount('<s:property value="#row.index" />')" /></td>
										<td><a role="button" class="btn btn-sm btn-info"
											title="Remove item from invoice"
											href="removeProductFromSales?salesDetails.salesDetailsId=<s:property value="salesDetailsId" />
											&salesBaseBean.salesId=<s:property value="salesBaseBean.salesId" />">D</a></td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>no product added</s:else>
							<tr>
								<td colspan="5" align="right"><label>Net amount</label></td>
								<td colspan="1"><input class="form-control"
									id="grossamount" type="text" name="salesAmountBean.grossamount"
									value="<s:property value="salesAmountBean.grossamount"/>" /></td>
							</tr>
							<tr>
								<td colspan="5" align="right"><label>Cgst</label></td>
								<td colspan="1"><input class="form-control" id="cgst"
									type="text" name="salesAmountBean.cgst"
									value="<s:property value="salesAmountBean.cgst"/>"
									onchange="netamttot()" /></td>
								<td colspan="1" align="left"><label id="ccgst">&#x20B9;
										<input class="form-control" id="igst" readonly="false"
										type="text" name="salesAmountBean.cgstamt" style="width: 10px"
										value="<s:property value="salesAmountBean.cgstamt"/>" />
								</label></td>
							</tr>
							<tr>
								<td colspan="5" align="right"><label>Sgst</label></td>
								<td colspan="1"><input class="form-control" id="sgst"
									type="text" name="salesAmountBean.sgst"
									value="<s:property value="salesAmountBean.sgst"/>"
									onchange="netamttot()" /></td>
								<td colspan="1" align="left"><label id="ssgst">&#x20B9;
										<input class="form-control" id="igst" readonly="false"
										type="text" name="salesAmountBean.sgstamt" style="width: 10px"
										value="<s:property value="salesAmountBean.sgstamt"/>" />
								</label></td>
							</tr>
							<tr>
								<td colspan="5" align="right"><label>Igst</label></td>
								<td colspan="1"><input class="form-control" id="igst"
									type="text" name="salesAmountBean.igst"
									value="<s:property value="salesAmountBean.igst"/>"
									onchange="netamttot()" /></td>
								<td colspan="1" align="left"><label id="iigst">&#x20B9;
										<input class="form-control" id="igst" readonly="false"
										type="text" name="salesAmountBean.igstamt" style="width: 10px"
										value="<s:property value="salesAmountBean.igstamt"/>" />
								</label></td>
							</tr>

							<!-- 	<div class="row"> -->
							<tr>
								<td colspan="4" align="right"><label>Vehicle
										details </label></td>
								<td colspan="1"><input class="form-control" id="vehicleno"
									type="text" name="salesAmountBean.vehicleno"
									placeholder="vehicle no"
									value="<s:property value="salesAmountBean.vehicleno"/>" /></td>

								<td colspan="1"><input class="form-control"
									id="vehicleamount" type="text"
									name="salesAmountBean.vehicleamount"
									placeholder="vehicle amount"
									value="<s:property value="salesAmountBean.vehicleamount"/>" /></td>
							</tr>
							<tr>
								<td colspan="5" align="right"><label>Loading charge
								</label></td>
								<td colspan="1"><input class="form-control" id="vehicleno"
									type="text" name="salesAmountBean.loadingcharge"
									placeholder="loading charge"
									value="<s:property value="salesAmountBean.loadingcharge"/>" /></td>
							</tr>
							<tr>
								<td colspan="5" align="right"><label>Total Net
										amount</label></td>
								<td colspan="1"><input class="form-control" id="netamount"
									type="text" name="salesAmountBean.netamount"
									value="<s:property value="salesAmountBean.netamount"/>" /></td>
							</tr>
							<!-- </div> -->
						</tbody>
					</table>
					<div class="row">
						<div align="center">
							<s:if test="salDetList != null">
								<s:submit class="btn btn-primary" value="Save"></s:submit>
							</s:if>
							<s:else>-- no products added</s:else>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="modal fade" id="productModal" role="dialog">
		<div class="modal-dialog modal-lg modal-xl">
			<div class="modal-content">
				<div class="modal-header" style="background-color: #581845;">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
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