<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
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

.p-image {
	height: 300px;
	width: 300px;
}

.box {
	border: 1px solid #ECF0F1;
}

.logo {
	color: #ffffff;
}
		
.logo:hover {
	color: #ffffff;
	text-decoration: none;
}
</style>

</head>
<body class="bg-light">
	<div class="bg-success d-flex justify-content-between p-3 text-light">
		<a href="/YourmartAdmin/home?username=abc" class="logo">
			<span class="font-weight-light title">YourMart</span>
		</a>
		<span
			class="font-weight-light welcome">Welcome <c:out
				value="${user}" /></span>
	</div>
	<div class="text-sm-center m-5 error font-weight-light text-danger">
		<c:out value="${error}" />
	</div>
	<div class="container">
		<div class="row my-5 box shadow rounded py-3">
			<div class="col-sm-6 d-flex justify-content-center">
				<img src="${product.primaryImage}" class="p-image rounded">
			</div>
			<div class="col-sm-6">
				<div class="d-flex justify-content-between">
					<div>
						<h2 class="font-weight-light">
							<c:out value="${product.name}" />
						</h2>
						<span class="text-secondary">Product Id: <c:out
								value="${product.productId}" /></span>
					</div>
					<div class="py-3">
						<h5>
							<c:out value="${product.status}" />
						</h5>
					</div>
				</div>

				<div class="my-3">
					<table class="table table-hover">
						<tbody>
							<tr>
								<td>Product Code</td>
								<td><c:out value="${product.sellerProdCode}" /></td>
							</tr>
							<tr>
								<td>Short description</td>
								<td><c:out value="${product.shortDesc}" /></td>
							</tr>
							<tr>
								<td>Long Description</td>
								<td><c:out value="${product.longDesc}" /></td>
							</tr>
							<tr>
								<td>Dimensions</td>
								<td><c:out value="${product.dimensions}" /></td>
							</tr>
							<tr>
								<td>MRP</td>
								<td><c:out value="${product.mrp}" /></td>
							</tr>
							<tr>
								<td>SSP</td>
								<td><c:out value="${product.ssp}" /></td>
							</tr>
							<tr>
								<td>YMP</td>
								<td><c:out value="${product.ymp}" /></td>
							</tr>
							<tr>
								<td>Comments</td>
								<td><c:out value="${product.comments}" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="d-flex justify-content-center">
			<c:if
				test="${product.status.equals('NEED_APPROVAL') || product.status.equals('REVIEW')}">
				<form action="/YourmartAdmin/updateStatus"  method="post">
					<input type="hidden" name="status" value="APPROVED">
					<input type="hidden" name="productId" value="${product.productId }">
					<input type="hidden" name="comment" id="commentid" value="">
					<input class="btn m-3 btn-danger" type="submit" value="Approve ${product.name }" onclick="openPrompt()">
				</form>
				<form action="/YourmartAdmin/updateStatus"  method="post">
					<input type="hidden" name="status" value="REJECTED">
					<input type="hidden" name="productId" value="${product.productId }">
					<input type="hidden" name="comment" id="commentid" value="">
					<input class="btn m-3 btn-danger" type="submit" value="Reject ${product.name }" onclick="openPrompt()">
				</form>
			</c:if>
			<c:if test="${product.status.equals('APPROVED')}">
				<form action="/YourmartAdmin/updateStatus"  method="post">
					<input type="hidden" name="status" value="REJECTED">
					<input type="hidden" name="productId" value="${product.productId }">
					<input type="hidden" name="comment" id="commentid" value="">
					<input class="btn m-3 btn-danger" type="submit" value="Reject ${product.name }" onclick="openPrompt()">
				</form>
			</c:if>
			<c:if test="${product.status.equals('REJECTED')}">
				<form action="/YourmartAdmin/updateStatus"  method="post">
					<input type="hidden" name="status" value="APPROVED">
					<input type="hidden" name="productId" value="${product.productId }">
					<input type="hidden" name="comment" id="commentid" value="">
					<input class="btn m-3 btn-danger" type="submit" value="Approve ${product.name }" onclick="openPrompt()">
				</form>
			</c:if>
		</div>
	</div>
	<script>
	function openPrompt() {
	    var txt;
	    var comment = prompt("Enter comment here:", "");
	    if (comment == null || comment == "") {
	        txt = "";
	    } else {
	        txt = comment
	    }
	    document.getElementById("commentid").value = txt;
	} 
	</script>
</body>
</html>