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
		<div class="card w-50">
		  <div class="card-body  px-5 py-4">
		  	<div class="d-flex justify-content-between">
		  		<div>
		  			<div class="card-title"><c:out value="${seller.ownerName}" /></div>
		  			<span class="text-secondary">Seller Id: <c:out value="${seller.sellerId}" /></span>
		  		</div>
		  		<div class="pr-3">
		  			<div>Username: <c:out value="${seller.username}" /></div>
		  			<div>Email: <c:out value="${seller.email}" /></div>
		  			<div>Telephone: <c:out value="${seller.telephone}" /></div>
		  		</div>
		  	</div>
		    <div class="container w-100 my-5">
		  		<table class="table text-sm-center my-3">
				  <tbody>
				    <tr>
				      <td>Company Name:</td>
				      <td><c:out value="${seller.company}" /></td>
				    </tr>
				    <tr>
				      <td>Address:</td>
				      <td><c:out value="${seller.address}" /></td>
				    </tr>
				    <tr>
				      <td>GST Number:</td>
				      <td><c:out value="${seller.gstNumber}" /></td>
				    </tr>
				    <tr>
				      <td>Current Status:</td>
				      <td><c:out value="${seller.status}" /></td>
				    </tr>
				  </tbody>
				</table>
				<div>
					<c:if test="${seller.status.equals('NEED_APPROVAL')}">
						<div class="d-flex">
							<form action="changeStatus" class="mr-2">
								<input type="hidden" value="${seller.sellerId}" name="sellerId">
								<input type="hidden" value="APPROVED" name="newStatus">
								<input type="submit" value="Approve" class="btn btn-success">
							</form>
							<form action="changeStatus">
								<input type="hidden" value="${seller.sellerId}" name="sellerId">
								<input type="hidden" value="REJECTED" name="newStatus">
								<input type="submit" value="Reject" class="btn btn-danger">
							</form>
						</div>
					</c:if>
					<c:if test="${seller.status.equals('APPROVED')}">
						<form action="changeStatus">
							<input type="hidden" value="${seller.sellerId}" name="sellerId">
							<input type="hidden" value="REJECTED" name="newStatus">
							<input type="submit" value="Reject" class="btn btn-danger">
						</form>
					</c:if>
					<c:if test="${seller.status.equals('REJECTED')}">
						<form action="changeStatus">
							<input type="hidden" value="${seller.sellerId}" name="sellerId">
							<input type="hidden" value="APPROVED" name="newStatus">
							<input type="submit" value="Approve" class="btn btn-success">
						</form>
					</c:if>
				</div>
		    </div>
		  </div>
</div>
	</div>
</body>
</html>