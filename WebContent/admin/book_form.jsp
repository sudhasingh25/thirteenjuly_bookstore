<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create user</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css">

<script type="text/javascript" src="../js/query-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/query.validate.min.js"></script>
<script type="text/javascript" src="../js/query.jquery-ui.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp"/>
	
	<div align="center">
		<c:if test="${book==null }">
		<h2 class="pageheading"> Create New Book</h2>
		</c:if>
		<c:if test="${book!=null }">
		<h2 class="pageheading">Edit Book</h2>
		</c:if>
	</div>
	
	<div align="center">
		<c:if test="${book==null }">
			<form action="create_book" method="post" id="bookForm">
		</c:if>
		<c:if test="${user!=null }">
			<form action="update_book" method="post" id="bookForm">
			<input type="hidden" name="bookId" value="${book.bookId}">
		</c:if>	
		<table class="form">
			<tr>
				<td>Category:</td>
				<td>
					<select name="category">
						<c:forEach items="${listCategory}" var="category">
							<option value="${category.categoryId}">
								${category.name}
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input type="text" id="title" name="title" size="20" value="${book.title}"></td>
			</tr>
			<tr>	
				<td align="right">Author:</td>
				<td align="left"><input type="text" id="author" name="author" size="20" value="${book.author}"></td>
			</tr>
			
			<tr>	
				<td align="right">ISBN:</td>
				<td align="left"><input type="text" id="isbn" name="isbn" size="20" value="${book.isbn}"></td>
			</tr>
			
			<tr>	
				<td align="right">publishDate:</td>
				<td align="left"><input type="text" id="publishDate" name="publishDate" size="20" value="${book.publishDate}"></td>
			</tr>
			
			<tr>	
				<td align="right">Book Image:</td>
				<td align="left">
					<input type="file" id="bookImage" name="bookImage" size="20" ><br/>
					<img id="thumbnail" alt="Image Preview" style="width:20%; margin-top:10px"/>
				</td>
			</tr>
			
			<tr>	
				<td align="right">Price:</td>
				<td align="left"><input type="text" id="price" name="price" size="20" value="${book.price}"></td>
			</tr>
			<tr>	
				<td align="right">Description:</td>
				<td align="left">
					<textarea cols="20" rows="5" name="description" id="description"></textarea>
				</td>
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
		$('#publishDate').datepicker();
		$('#bookImage').change(function(){
			showImageThumbnail(this);
		});
		
		$("#bookForm").validate({
			rules:{
				category:"required",
				title:"required",
				author:"required",
				isbn:"required",
				publishDate:"required",
				bookImage:"required",
				price:"required",
				description:"required"
			},	
			
			message:{
				category:"Choose a category",
				title:"title is required",
				author:"author name is required",
				isbn:"isbn is required",
				publishDate:"publish Date is required",
				bookImage:"choose a book image",
				price:"enter price",
				description:"description is required"
			}
		
			
		});
		
		$("#buttonCancel").click(function(){
			history.go(-1);
		});
	});
	
	function showImageThumbnail(fileInput){
		var file= fileInput.files[0];
		
		var reader=new FileReader();
		
		reader.onload= function(e){
			$('#thumbnail').attr('src',e.target.result);
		}
		
		reader.readAsDataURL(file);
	}
</script>
</html>