<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>

<style>

body {
	background-color:  #F8F9F9;
}

.form-control {
	background: none;
}

.form {
	margin-top: 100px;
}
</style>
</head>
<body class="h-100 w-100">
	<div class="row h-100">
		<div class="col-sm-6 h-100">
    	</div>
    	<div class="col-sm-6 form">
    		<h4 class="font-weight-light text-secondary">YourMart Admin Login</h4>
	      <form action="authenticate" method="post" class="d-flex flex-column w-50 login" id="loginForm">
	        <div class="d-flex justify-content-center text-danger">
	          <c:out value="${error}" />
	        </div>
	        <input type="text" placeholder="Username" name="username" class="form-control mx-2 my-2">
		    <input type="password" placeholder="Password" name="password" class="form-control mx-2 my-2">
		    <div class=" ml-2 g-recaptcha" data-sitekey="6LcSqHgUAAAAAL2Wul-sVz7QG-IMxo6fzBd8ORUw"></div>  
		    <input type="submit" value="login" class="btn btn-success mx-2 my-2">
	      </form>
    </div>
	</div>
<script>
	$('#loginForm').on('submit', function(e) {
		if(grecaptcha.getResponse() == "") {
			console.log('dnkjd')
			e.preventDefault();
		    return false
		} else {
		    return true
		}
	});
</script>
</body>
</html>
