<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<br>

	<a href="showProductsToAdd.jsp"> Click to View the list of
		Available Products</a>
	<h4>
		<b> Items in the Kit: </b>
	</h4>
	<br>
	<form action="calculateTotals">
				<c:choose>
					<c:when test="${kititems==null || kititems.isEmpty() }">
					<h4>No Products Selected</h4>
					</c:when>
					<c:otherwise>
						<table border="1" cellspacing="5px" cellpadding="5px">
							<tr>
								<th>Product Id</th>
								<th>Product Name</th>
								<th>Product Cost</th>
								<th>Description</th>
								<th>Quantity</th>
							</tr>
							<c:forEach items="${kititems}" var="item">
								<tr>
									<td>${item.product.getId()}</td>
									<td>${item.product.getproductName()}</td>
									<td>${item.product.getCost()}</td>
									<td>${item.product.getproductDescription()}</td>
									<td><input type="text"
										name="quantityOfProduct${item.product.Id}"
										value="${item.quantity}" pattern="[0-9]+" required></td>
								</tr>
							</c:forEach>

						</table>


					</c:otherwise>
				</c:choose>
		<br>

		<button>Save Items</button>
	</form>

	<%
		if (request.getAttribute("Message") != null) {
	%>
	<%
		String message = request.getAttribute("Message").toString();
	%>
	<%=message%>
	<%
		}
	%>>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>