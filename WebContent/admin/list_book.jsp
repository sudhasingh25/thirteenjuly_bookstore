<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin ListBooks</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/query-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/query.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<h2 class="pageheading">Book Management</h2>
	</div>
	
	<div align="center">
		<a href="create_book">Create New Book</a><br/><br/>
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
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="book" items="${listBook}" varStatus="status">
				<tr>
					<td>${status.index +1 }</td>
					<td>${book.bookId }</td>
					<td><img src="data:image/jpg;base64,${book.base64Image}" width="84" height="110"/></td>
					<td>${book.title }</td>
					<td>${book.author }</td>
					<td>${book.category }</td>
					<td>${book.price }</td>
					<td>${book.lastUpdateTime }</td>
					<td>
						<a href="edit_book?id=${book.bookId}">Edit</a>&nbsp;
						<a href="javascript:void(0);" class="deleteLink" id="${book.bookId}">Delete</a>
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
					bookId=$(this).attr("id");
					if(confirm('are you sure you want to delete book with Id ' +bookId)){
						window.location='delete_book?id='+bookId;
					}
				})
			})
		});
	</script>
	
</body>
</html>