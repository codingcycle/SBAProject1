<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />

	<br>
	<h4>
		<b> Order Summary: </b>
	</h4>
	<h4>
		<b>User Details:</b>
	</h4>
	<br>
	<label>User Full Name: ${userdetails.userName} </label>
	<br>
	<label>Contact Number: ${userdetails.mobile} </label>
	<br>
	<label>e-mail: ${userdetails.email} </label>
	<br>
	<label>Address: ${userdetails.deliveryAddress} </label>
	<br>
	<c:choose>
		<c:when test="${kititems==null || kititems.isEmpty() }">
			<h4>No Products Selected</h4>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Id</th>
					<th>Title</th>
					<th>Cost</th>
					<th>Description</th>
					<th>Quantity</th>
					<th>Total Price</th>
				</tr>
				<c:forEach items="${kititems}" var="item">
					<tr>
						<td>${item.product.getid()}</td>
						<td>${item.product.getproductName()}</td>
						<td>${item.product.getproductCost()}</td>
						<td>${item.product.getproductDescription()}</td>
						<td>${item.quantity}</td>
						<td>${item.price}</td>
					</tr>
				</c:forEach>

			</table>
		</c:otherwise>

	</c:choose>
	<br>
	<%
		String str = session.getAttribute("total").toString();
	%>
	<h4>
		<b>Total Price: <%=str%></b>
	</h4>

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>