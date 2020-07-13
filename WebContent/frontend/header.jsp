<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
<div>
	<img src="images/BookstoreLogo.png">
</div>

<div>
	<input type="text" size="50" name="keyword"/>
	<input type="button" value="Search" />
	
	
	
	&nbsp;&nbsp;&nbsp;
	
	
	<a href="login">Sign In</a>|
	<a href="register">Register</a>|
	<a href="cart">Cart</a>
	
	<div>
		&nbsp;
	</div>
	
	<div>
		<c:forEach var="cat" items="${listCategory}" varStatus="status">
			<b><a href="view_category?=${cat.categoryId}">${cat.name}</a></b>
			<c:if test="${not status.last}">
				&nbsp;<b> |</b> &nbsp;
			</c:if>
		</c:forEach>
	</div>
</div>
</div>