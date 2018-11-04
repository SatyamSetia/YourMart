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
			font-size: 1.5rem;
		}
		
		.list-title {
			font-size: 2rem;
		}
		
		.small-label {
			font-size: 0.75rem;
		}
		
		.product-list-item {
			border: 1px solid #EAECEE;
			box-shadow: 1px 1px #EAECEE;
			border-radius: 10px;
			padding: 15px;
		}
		
		.filters {
			margin-top: 75px;
		}
		
		.id-label {
			font-size: 0.9rem;
		}
		
		.dropdown {
		    position: relative;
		    display: inline-block;
		}
		
		.dropdown-content {
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		    min-width: 160px;
		    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		    padding: 12px 16px;
		    z-index: 1;
		}
		
		.dropdown:hover .dropdown-content {
		    display: block;
		}
		
		.nav-link {
			background: none;
			border: none;
			color: #ffffff;
		}
		
		.nav-link:hover {
			color: #EAECEE;
			cursor: pointer;
			text-decoration: underline;
		}
	
	</style>
</head>
<body class="bg-light">
	<div class="bg-success d-flex justify-content-between p-3 text-light">
		<span class="font-weight-light title">YourMart</span>
		<div class="d-flex">
			<form action="home">
				<input type="submit" value="Sellers" class="nav-link">
			</form>
			<span class="font-weight-light welcome">Welcome <c:out value="${user}" /></span>
		</div>
	</div>
	<div class="container py-4">
		<div class="row">
			<div class="col-sm-3 px-5 filters">
				<div class="">
					<form action="searchProducts">
						<div class="mt-4 mb-2 form-label text-success text-sm-center">Sort By</div>
						<input type="radio" name="sortBy" value="mrp"> MRP<br>
						<input type="radio" name="sortBy" value="ssp"> SSP<br>
						<input type="radio" name="sortBy" value="ymp"> YMP<br>
						<input type="radio" name="sortBy" value="createdAt"> Registration Time<br>
						<div class="mt-4 mb-2 form-label text-success text-sm-center">Filter By</div>
						<input type="checkbox" name="status" value="REVIEW"> REVIEW<br>
						<input type="checkbox" name="status" value="NEED_APPROVAL"> Need Approval<br>
						<input type="checkbox" name="status" value="APPROVED"> Approved<br>
						<input type="checkbox" name="status" value="REJECTED"> Rejected<br>
						<div class="">
							<input type="submit" value="Apply" class="mt-4 btn btn-success w-100">
						</div>
					</form>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="list-title font-weight-light">Products</div>
				<form action="searchProducts">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="dropdown mt-2">
						  <span class="bg-success text-light p-2 rounded-left">Search by</span>
						  <div class="dropdown-content">
						    <input type="radio" name="searchBy" value="sellerProdCode" class="mb-2"> Seller Product Code</br>
						    <input type="radio" name="searchBy" value="name" class="mb-2"> Product Name</br>
						    <input type="radio" name="searchBy" value="productId"> Product Id</br>
						    <input type="radio" name="searchBy" value="sellerId"> Seller Id</br>
						    <input type="radio" name="searchBy" value="company"> Company Name</br>
						  </div>
						</div>
					</div>
					<input type="text" class="form-control" placeholder="Enter keywords here" name="keyword">
					<input type="submit" value="Find">
				</div>
				</form>
				<div>
					<form>
						<c:forEach var="product" items="${productList}">
							<div class="product-list-item my-3">
								<div onclick="location.href='products/${product.productId}'">
									<div class="row">
										<div class="col-sm-1">
											<c:if test="${product.status.equals('NEED_APPROVAL')}">
												<input type="checkbox" name="check" value="${product.productId}">
											</c:if>
											<c:if test="${!product.status.equals('NEED_APPROVAL')}">
												<input type="checkbox" name="check" value="" disabled>
											</c:if>
										</div>
										<div class="col-sm-2">
											<img src="${product.primaryImage}" height="100" width="100" class="rounded">
										</div>
										<div class="col-sm-9">
											<div class="row">
												<div class="col-sm-4 d-flex flex-column">
													<span><c:out value="${product.name}" /></span>
													<span class="text-secondary small-label">Added 5 days ago</span>
												</div>
												<div class="col-sm-3 text-sm-center">
													<span class="id-label">Product Id: <c:out value="${product.productId}" /></span>
												</div>
												<div class="col-sm-5">
													<span class="float-right pr-4"><c:out value="${product.status}" /></span>
												</div>
											</div>
											<hr>
											<div class="d-flex justify-content-between pr-4">
												<c:out value="${product.shortDesc}" />
												<span class="id-label">Seller Id: <c:out value="${product.sellerId}" /></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>