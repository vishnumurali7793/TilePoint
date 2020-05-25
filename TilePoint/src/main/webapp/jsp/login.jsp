<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="resources/jquery/jquery-3.5.0.min.js"></script>
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<title>tessaract</title>
</head>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background: rgb(196, 85, 42);
	background: linear-gradient(90deg, rgba(196, 85, 42, 1) 10%,
		rgba(195, 12, 157, 1) 100%);
		background-image:url("resources/images/log.jpg");
	color: white;
}
#body-sub{
  position: fixed;
  top: 0;
  left: 0;
  border: 3px solid #8a6d3b;
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  box-sizing: border-box;

}
.container-fluid{padding-top:100px;}
#loginPane {
	border: 4px solid #2267a9;
	padding: 0 10px;
	margin-right: 50px;
	margin-left: 50px;
	
}
input{background-color: #f0ad4e}

#submit {
	margin: 0px;
}

#header h3 {
	margin-left: 0px;
	height: 70px;
	font-weight: bold;
	text-align:center;
	padding-top: 20px;
	font-size: 32px;
}

.form-group {
	color: black;
}

#label {
	color: #fefffc;
}
</style>
<body>
<div id="body-sub">
	<!-- 	<h3 align="center">Tesseract</h3> -->
	 <div id="header">
		<h3>Welcome to Tesseract</h3>
	</div> 
	<div class="container-fluid">
		<div class="row">
		<div class="col-xs-4 col-md-4"></div>
			<div class="col-xs-12 col-md-4" id="login-panel">
				<s:form action="login">

					<div id="loginPane" align="center">
						<!-- <h3 align="center" class="header">Login</h3> --><br>
						<img src="resources/images/logo.jpg" alt="logo" height="60" width="150">
						<div class="input-group form-group"><br>
							<label id="label">Username</label>&emsp;<input type="text"
								name="loginBean.userName" id="name" />
						</div>
						<br>
						<div class="input-group form-group">
							<label id="label">Password</label>&emsp;<input type="password"
								name="loginBean.password" id="password" /><br> <br>
							<div id="input-group form-group">
								<input type="submit" name="submit" value="login"
									class="btn btn-warning" />
							</div>
						</div>
						<br>
					</div>
				</s:form>
			</div>
			<div class="col-xs-4 col-md-4"></div>
		</div>
	</div>
	</div>
</body>
</html>