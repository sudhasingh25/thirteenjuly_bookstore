<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create category</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/query-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/query.validate.min.js"></script>
</head>
<body>
		<jsp:directive.include file="header.jsp"/>
	

	<div align="center">
		<c:if test="${cat==null }">
		<h2 class="pageheading"> Create New Category</h2>
		</c:if>
		<c:if test="${cat!=null }">
		<h2 class="pageheading">Edit Category</h2>
		</c:if>
	</div>
	
	<div align="center">
		<c:if test="${cat==null }">
			<form action="create_category" method="post" id="categoryForm">
		</c:if>
		<c:if test="${cat!=null }">
			<form action="update_category" method="post" id="categoryForm">
			<input type="hidden" name="categoryId" value="${cat.categoryId}">
		</c:if>	
		<table class="form">
			<tr>	
				<td align="right">Name:</td>
				<td align="left"><input type="text" id="name" name="name" size="20" value="${cat.name }"></td>
			</tr>
			
			<tr></tr><tr></tr>
			<tr>
				<td align="center" colspan="2">
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
		$("#categoryForm").validate({
			rules:{
				name:"required",
			},	
		
			message:{
				name:"Please enter category name",
			}
		})
		
		$("#buttonCancel").click(function(){
			history.go(-1);
		});
	});
	
</script>
</html>