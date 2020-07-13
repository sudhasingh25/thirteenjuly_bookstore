<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create user</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/query-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/query.validate.min.js"></script>

</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<c:if test="${user==null }">
		<h2 class="pageheading"> Create New User</h2>
		</c:if>
		<c:if test="${user!=null }">
		<h2 class="pageheading">Edit User</h2>
		</c:if>
	</div>
	
	<div align="center">
		<c:if test="${user==null }">
			<form action="create_user" method="post" id="userForm">
		</c:if>
		<c:if test="${user!=null }">
			<form action="update_user" method="post" id="userForm">
			<input type="hidden" name="userId" value="${user.userId}">
		</c:if>	
		<table class="form">
			<tr>
				<td align="right">Email:</td>
				<td align="left"><input type="text" id="mail" name="mail" size="20" value="${user.email }"></td>
			</tr>
			<tr>	
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" id="fullname" name="fullname" size="20" value="${user.fullName }"></td>
			</tr>
			<tr>	
				<td align="right">Password:</td>
				<td align="left"><input type="password" id="password"  name="password" size="20" value="${user.password }"></td>
			</tr>
			<tr></tr>
			<tr>
				<td align="center" colspan="3">
					<button type="submit" >Save</button>&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
		<jsp:directive.include file="footer.jsp"/>

</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#userForm").validate({
			rules:{
				mail:{
					required:true,
					mail:true
				},	
				fullname:"required",
				password:"required"
			},	
		
			message:{
				mail:{
					required:"Please enter email",
					mail:"Please enter a valid email address"
				},
				fullname:"Please enter fullname",
				password:"Please enter password"
			}
		});
		
		$("#buttonCancel").click(function(){
			history.go(-1);
		});
	});
	
</script>
</html>