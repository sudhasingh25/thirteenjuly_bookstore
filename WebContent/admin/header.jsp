<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">
	<div>
		<img src="../images/BookstoreAdminLogo.png"><br/>
		 Welcome, <b><c:out value="${sessionScope.useremail}" />  | </b> <a href="logout">Logout</a>
	</div>
	<div>&nbsp;</div>
	<div id="headermenu">
		<b> 
			<div class="menu_item">
				<a href="list_users">
					<img src="../images/users.png"><br/>Users</a> &nbsp;
			</div>
			<div class="menu_item">
				<a href="list_category">
					<img src="../images/category.png"><br/>Categories</a> &nbsp;
			</div>
			<div class="menu_item">
				<a href="list_book">
					<img src="../images/bookstack.png"><br/>Books</a> &nbsp;
			</div>
			<div class="menu_item">
				<a href="customers">
					<img src="../images/customer.png"><br/>Customers</a> &nbsp; 
			</div>
			<div class="menu_item">
				<a	href="reviews">
					<img src="../images/review.png"><br/>Reviews</a> &nbsp;
			</div>
			<div class="menu_item">
				<a href="orders">
					<img src="../images/order.png"><br/>Orders</a> &nbsp;
			</div>
		</b>
	</div>
</div>