<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
	<script>
	$(() => {
		// function will get executed
		// on click of submit button
		$("#atmAmount").click(function(ev) {
			var amount= document.getElementById("amount").value;
			console.log(amount);
			var url = "http://localhost:8080/Struts2Starter/deposit";
			$.ajax({
				type: "POST",
				url: url,
				//data:{"array":strArray},
				data:{"amount":amount},
				
				success: function(response) {
					alert(response);
					// window.location.reload(); 
					
					//console.log(response);
				},
				error: function(response) {
					alert("some Error");
				}	
			});
			event.preventDefault();
		});
	});
	</script>
</head>
<body>

				   <label>Enter the amount to Deposit</label>
				   <input type="number" id="amount" placeholder="Enter the amount" class="text1">
				 	<br>	  		   
	<button id="atmAmount">
		CASH DEPOSIT
	</button>

</body>
</html>