<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<script type="text/javascript"
	src="resources/jquery/jquery-3.5.0.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="resources/bootstrap/js/bootstrap.min.js"></script>
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
</style>
</head>
<body>
	<nav class="navbar navbar-inverse bar">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="goToHome">Tesseract</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="goToHome">Home</a></li>
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
</body>
</html>