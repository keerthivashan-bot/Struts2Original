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
			var name= document.getElementById("customerName").value;
			var password= document.getElementById("password").value;
			
			var confirmPassword= document.getElementById("confirmPassword").value;
			var url = "http://localhost:8080/Struts2Starter/addingCustomers";
			$.ajax({
				type: "POST",
				url: url,
				//data:{"array":strArray},
				data:{"name":name,
					"password":password,
					"confirmPassword":confirmPassword},
				
				success: function(response) {
					$("html").html(response);
					//alert(response);
					// window.location.reload(); 
					
					//console.log(response);
				},
				error: function(response) {
					alert("please enter the Password correctly");
				}	
			});
			event.preventDefault();
		});
	});
	</script>
</head>
<body>
	<h2>Sign up page</h2>
				   <label>Name</label>
				   <input type="text" id="customerName" placeholder="Enter name" class="text1">
				    <label>Password</label>
				   <input type="password" id="password" placeholder="password" class="text2">
				   <label>Confirm Password</label>
				   <input type="password" id="confirmPassword" placeholder="confirm Password" class="text4">				  		   
	<button id="submitButton">
		SUBMIT
	</button>
</body>
</html>

