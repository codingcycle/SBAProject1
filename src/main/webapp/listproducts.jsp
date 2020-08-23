<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<center>
        <h1>Product Management</h1>
        <h2>
            <a href="/new">Add New Product</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Products</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Products</h2></caption>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Cost</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="product" items="${listAllProducts}">
                <tr>
                    <td><c:out value="${product.id}" /></td>
                    <td><c:out value="${product.productName}" /></td>
                    <td><c:out value="${product.productDescription}" /></td>
                    <td><c:out value="${product.cost}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${product.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${product.id}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
	
	<jsp:include page="footer.jsp" />
</body>
</html>