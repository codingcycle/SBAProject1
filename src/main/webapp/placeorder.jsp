<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Place Order(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />

	<br>
	<form action="orderConfirmation" action="post">
		<label>
			<h4>
				<b>Please enter the complete shipping address:</b>
			</h4> <textarea name="address" rows="4" cols="25"></textarea>
		</label>
		<button>Confirm Order</button>
	</form>

	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>