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
	/* margin-left: 30px;
	margin-right: 30px;
	padding: 0; */
	/* background-color: #e7e6b8; */
}

.bar {
	border-radius: 0px;
}
.panel-body label{
padding-right: 10px;
padding-left: 20px;
}
.btn{
color: #ffffff;
background-color: #337ab7;
}
.panel h2{
color:#337ab7;}

.dropdown-menu>li>a {
	color: #dcf1cf;
}

.navbar-nav>li>.dropdown-menu {
	background-color: #222222;
}
.dropdown-menu>li>a:hover {
    color: #fff;
    text-decoration: none;
    background-color: #337ab7;
    outline: 0;
}
.slider{
height: 100% !important;
width: 100% !important;
}
.container{
height: 100% !important;
width: 100% !important;
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
				<li class=""><a href="goToDashBoard">Dashboard</a></li>
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
				<li class="quick-sales"><a href="gotToQuickSales"><span
						class="glyphicon glyphicon-piggy-bank"></span>&nbsp;&nbsp;Quick Sales</a></li>
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
		<div class="slider" align="center">
	<div class="">
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" width="1349" height="450" role="listbox">

      <div class="item active">
        <img src="resources/images/ss1.jpg" alt="tilesPoint" width="1349" height="450">
        <div class="carousel-caption">
		</div>
	  </div>

      <div class="item">
       <img src="resources/images/ss2.jpg" alt="tilesPoint" width="1349" height="450">
        <div class="carousel-caption">
		</div>
	  </div>
    
      <div class="item">
       <img src="resources/images/ss3.jpg" alt="tilesPoint" width="1349" height="450">
        <div class="carousel-caption">
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