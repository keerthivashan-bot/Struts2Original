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
		$("#accTransfer").click(function(ev) {
			var accno= document.getElementById("accno").value;
			console.log("Type of"+typeof accno);	
			var amtToTransfer=parseInt( document.getElementById("amtToTransfer").value);
			var bool=true;
			if(amtToTransfer<0){
				console.log("inside if");
				alert("cannot transfer Negative amount");
				Math.abs(amtToTransfer);
				bool=false;
			}
			console.log(accno);
			var url = "http://localhost:8080/Struts2Starter/accountTransfer";
			if(bool){
				$.ajax({
					type: "POST",
					url: url,
					//data:{"array":strArray},
					data:{"accno":accno,"amt":amtToTransfer},

					success: function(response) {
						alert(response);
						// window.location.reload(); 
						
						//console.log(response);
					},
					error: function(response) {
						alert("some Error");
					}	
				});
			}
			
			event.preventDefault();
		});
	});
	</script>
</head>
<body>

				   <label>Enter the account number to Deposit</label>
				   <input type="number" id="accno"  min="0" step="2" placeholder="Enter the ACC NO" class="text1">
				   <label>Enter the amount to Deposit</label>
				   <input type="number" id="amtToTransfer" min="0" step="2" placeholder="Enter the amount" class="text1">
				 	<br>	  		   
	<button id="accTransfer">
		Account Transfer
	</button>

</body>
</html>