<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin ListCategory</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/query-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/query.validate.min.js"></script>

</head>
<body>

	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2 class="pageheading">Category Management</h2>
	</div>
	
	<div align="center">
		<a href="category_form.jsp">Create New Category</a><br/><br/>
	</div>
	
	<c:if test="${message!=null}" >
	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>
	
	
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="cat" items="${listcat}" varStatus="status">
				<tr>
					<td>${status.index +1 }</td>
					<td>${cat.categoryId }</td>
					<td>${cat.name }</td>
					<td>
						<a href="edit_category?id=${cat.categoryId}">Edit</a>&nbsp;
						<a href="javascript:void(0);" class="deleteLink" id="${cat.categoryId }">Delete</a>
					</td>
				</tr>	
			</c:forEach>
		</table>
	</div>
	
	<jsp:directive.include file="footer.jsp"/>

		<script>
		$(document).ready(function(){
			$(".deleteLink").each(function(){
				$(this).on("click",function(){
					categoryId=$(this).attr("id");
					if(confirm('are you sure you want to delete user with Id ' +categoryId)){
						window.location='delete_category?id='+categoryId;
					}
				})
			})
		});
	</script>	
</body>
</html>