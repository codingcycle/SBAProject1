<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<h3>
		<b> Welcome Visitor!!! </b>
	</h3>
	<h4>
		<b> Please fill the below details to proceed...</b>
	</h4>
	<br>
	<br>
	<form action="newuser" method="post">
		<div>
		<label>Full Name: <input type="text" name="userName" required></label>
		</div> <br> 
			<div><label> Mobile Number: <input type="text" name="mobile" pattern="[1-9][0-9]{9}" required></label></div><br> 
			<div><label> e-mail: <input type="email" name="useremail" required></label></div><br>
		<div>
			<div><input type="submit" value="Click To View Available Products"> </div>
		</div>
	</form>


	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>