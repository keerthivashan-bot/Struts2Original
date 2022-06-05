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
		$("#sort").click(function(ev) {
			var url = "http://localhost:8080/Struts2Starter/customersSorting";
			$.ajax({
				type: "POST",
				url: url,
				//data:{"array":strArray},
				//data:{"accno":accno,"amt":amtToTransfer},
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

				   	   
	<button id="sort">
		Account Transfer
	</button>

</body>
</html>