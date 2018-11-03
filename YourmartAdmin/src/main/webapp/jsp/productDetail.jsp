<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<style>
		.title {
			font-size: 1.8rem;
		}
		
		.welcome {
			font-size: 1.3rem;
		}
		
		.error {
			font-size: 2rem;
		}
		
		.card-title {
			font-size: 2rem;
			margin-bottom: 0px;
		}
		
		.details {
			font-size: 1.2rem;
		}
	</style>

</head>
<body class="bg-light">
	<div class="bg-success d-flex justify-content-between p-3 text-light">
		<span class="font-weight-light title">YourMart</span>
		<span class="font-weight-light welcome">Welcome <c:out value="${user}" /></span>
	</div>
	<div class="text-sm-center m-5 error font-weight-light text-danger"><c:out value="${error}"/></div>
	<div class="d-flex justify-content-center">
		<c:out value="${product.name}"></c:out>
	</div>
</body>
</html>