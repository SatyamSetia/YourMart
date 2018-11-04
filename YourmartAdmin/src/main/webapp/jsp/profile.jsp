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
		
		.form-label {
			
		}
		
		.small-label {
			font-size: 0.75rem;
		}
		
		.filters {
			margin-top: 75px;
		}
		
		.seller-list-item {
			border: 1px solid #EAECEE;
			box-shadow: 1px 1px #EAECEE;
			border-radius: 10px;
			padding: 15px;
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
		
		a:hover {
			text-decoration: none;
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
		
		.logo {
			color: #ffffff;
		}
		
		.logo:hover {
			color: #ffffff;
		}
	
	</style>
</head>
<body class="bg-light">
	<div class="bg-success d-flex justify-content-between p-3 text-light">
		<a href="/YourmartAdmin/home?username=abc" class="logo">
			<span class="font-weight-light title">YourMart</span>
		</a>
		<div class="d-flex">
			<form action="products">
				<input type="submit" value="Products" class="nav-link">
			</form>
			<span class="font-weight-light welcome">Welcome <c:out value="${user}" /></span>
		</div>
	</div>
	<div class="container py-4">
		<div class="row">
			<div class="col-sm-3 px-5 filters">
				<div class="">
					<form action="search">
						<div class="mt-4 mb-2 form-label text-success text-sm-center">Sort By</div>
						<input type="radio" name="sortBy" value="sellerId"> Seller Id<br>
						<input type="radio" name="sortBy" value="createdAt"> Registration time<br>
						<div class="mt-4 mb-2 form-label text-success text-sm-center">Filter By</div>
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
				<div class="list-title font-weight-light">Sellers</div>
				<form action="search">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="dropdown mt-2">
						  <span class="bg-success text-light p-2 rounded-left">Search by</span>
						  <div class="dropdown-content">
						    <input type="radio" name="searchBy" value="company" class="mb-2"> Company</br>
						    <input type="radio" name="searchBy" value="ownerName" class="mb-2"> Owner Name</br>
						    <input type="radio" name="searchBy" value="telephone"> Telephone</br>
						  </div>
						</div>
					</div>
					<input type="text" class="form-control" placeholder="Enter keywords here" name="keyword">
					<input type="submit" value="Find">
				</div>
				</form>
				<div>
					<div><c:out value="${listError}" /></div>
					<form action="approveAll">
						<c:forEach var="seller" items="${sellerList}">
							<a href="seller/${seller.sellerId}">
								<div class="seller-list-item my-3">
									<div class="row">
										<div class="col-sm-1">
											<c:if test="${seller.status.equals('NEED_APPROVAL')}">
												<input type="checkbox" name="check" value="${seller.sellerId}">
											</c:if>
											<c:if test="${!seller.status.equals('NEED_APPROVAL')}">
												<input type="checkbox" name="check" value="" disabled>
											</c:if>
										</div>
										<div class="col-sm-4 d-flex flex-column">
											<span><c:out value="${seller.ownerName}" /></span>
											<span class="text-secondary small-label">Registered 5 days ago</span>
										</div>
										<div class="col-sm-3 text-sm-center">
											<span>Seller Id: <c:out value="${seller.sellerId}" /></span>
										</div>
										<div class="col-sm-4">
											<span class="float-right pr-4"><c:out value="${seller.status}" /></span>
										</div>
									</div>
								</div>
							</a>
						</c:forEach>
						<input type="submit" value="Approve Checked" class="btn btn-success">
					</form>
				</div>
			</div>
		</div>	
	</div>
</body>
</html>