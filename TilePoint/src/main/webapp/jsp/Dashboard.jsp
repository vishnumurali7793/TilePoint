<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<script type="text/javascript"
	src="resources/jquery/jquery-3.5.0.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/canvasjs/canvasjs.min.js"></script>

</head>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background: rgb(196, 85, 42);
	background: linear-gradient(90deg, rgba(196, 85, 42, 1) 10%,
		rgba(195, 12, 157, 1) 100%);
	color: white;
}

.bar {
	border-radius: 0px;
}

.dbelem {
	background-color: #ffffff;
	color: black;
	min-height: 150px;
}

#chartContainer {
	margin: 75px;
}
</style>
</head>
<script type="text/javascript">
	// 	window.onload = function() {
	// 		var chart = new CanvasJS.Chart("chartContainer", {
	// 			title : {
	// 				text : "My First Chart in CanvasJS"
	// 			},
	// 			data : [ {
	// 				// Change type to "doughnut", "line", "splineArea", "column" etc.
	// 				type : "splineArea",
	// 				dataPoints : [ {
	// 					label : "apple",
	// 					y : 10
	// 				}, {
	// 					label : "orange",
	// 					y : 15
	// 				}, {
	// 					label : "banana",
	// 					y : 25
	// 				}, {
	// 					label : "mango",
	// 					y : 30
	// 				}, {
	// 					label : "grape",
	// 					y : 28
	// 				} ]
	// 			} ]
	// 		});
	// 		chart.render();
	// 	}

	$(document).ready(function() {
		var datas = [];
		$.ajax({
			type : "GET",
			url : 'getSalesDetails',
			dataType : "json",
			success : function(data) {
				for (var i = 0; i < data.length; i++) {
					datas.push({
						label : data[i][1],
						y : data[i][0]
					});
				}
				drawChart(datas);
			},
		});
	});

	function drawChart(saleData) {
		var chart = new CanvasJS.Chart("chartContainer", {
			title : {
				text : ""
			},
			axisX : {
				title : 'Date (YYYY-MM-DD)',
				labelFontSize : 10,
			},
			axisY : {
				title : 'No. of sales',
				labelFontSize : 10,
			},
			data : [ {
				// Change type to "doughnut", "line", "splineArea", "column" etc.
				type : "line",
				dataPoints : saleData,
			} ]
		});
		chart.render();
	}
</script>
<body>
	<nav class="navbar navbar-inverse bar">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="goToHome">Tesseract</a>
			</div>
			<ul class="nav navbar-nav">
				<li class=""><a href="goToHome">Home</a></li>
				<li class="active"><a href="goToDashBoard">Dashboard</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Master <span class="caret"></span></a>
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
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<h2 class="card-title" align="center">Sales trend</h2>
						<div id="chartContainer" align="center"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>