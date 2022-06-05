<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
								<script src=
		"https://code.jquery.com/jquery-3.5.0.js">
	</script>
	<script>
	$(() => {
		// function will get executed
		// on click of submit button
		$("#submitButton").click(function(ev) {
			var customerId= document.getElementById("customerId").value;
			var password= document.getElementById("password").value;
			var url = "http://localhost:8080/Struts2Starter/loginchecker";
			$.ajax({
				type: "POST",
				url: url,
				//data:{"array":strArray},
				data:{"customerId":customerId,
					"password":password,
					},
				
				success: function(response) {
					
					$('html').html(response);
					//alert(response);
					// window.location.reload(); 
					
					//console.log(response);
				},
				error: function(response) {
					alert("The customer Id or password is Incorrect");
				}	
			});
			event.preventDefault();
		});
	});
	</script>
</head>
<body>
<h2>LOGIN PAGE</h2>
	<label>Enter Customer Id</label>
	<input type="number" id="customerId" placeholder="Enter customer id" class="text1">
	 <label>Password</label>
	<input type="password" id="password" placeholder="password" class="text2">
	<button id="submitButton">
		SUBMIT
	</button>
</body>
</html>