<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/query-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/query.validate.min.js"></script>
</head>
<body>

	
	<div align="center">BookStore Administration</div>

	<div align="center">
		<h2 class="pageheading">Admin Login</h2>
	</div>
	
	<c:if test="${message!=null}" >
	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>

	<div align="center">
		<form action="login" method="post" id="loginForm">
			<table class="form">
				<tr>
					<td align="right">Email:</td>
					<td align="left"><input type="text" id="mail" name="mail"
						size="20"></td>
				</tr>

				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password"
						name="password" size="20" ></td>
				</tr>
				<tr></tr>
				<tr>
					<td align="center" colspan="3">
						<button type="submit">Login</button>&nbsp;&nbsp;
						
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	
</body>

<script type="text/javascript">
	$(document).ready(function(){
		$("#loginForm").validate({
			rules:{
				mail:{
					required:true,
					mail:true
				},	
				password:"required"
			},	
		
			message:{
				mail:{
					required:"Please enter email",
					mail:"Please enter a valid email address"
				},
				password:"Please enter password"
			}
		});
		
	
	});
	
</script>
</html>