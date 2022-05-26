<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib  prefix = "s"  uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C/DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		$("#submitButton1").click(function() {
			var name = document.form1.userName1.value;
			var form = document.getElementById("form1");
			url = "http://localhost:8080/Struts2Starter/viewParticularStudent";
			$.ajax({
				type: "GET",
				url: url,
				data:{"name":name},
				success: function(response) {
					alert(response);
					document.getElementById("divForparticularStudent").innerHTML = response;
				},
				error: function(response) {
					document.getElementById("divForparticularStudent").innerHTML = "No student available";
					//alert("some Error");
				}	
			});
			event.preventDefault();
		});
	});
	</script>
</head>
<body>
	<form action='' name="form1">
	<table style="width: 35%">
		<tr>
			<td>VIEW PARTICULAR STUDENT</td>
			<td><input type="number" name="userName1"/></td>
		</tr>
	</table>
	<button type='submit' id='submitButton1'>
		Submit
		</button>
	</form>	
	<h2>Particular Student Details</h2>
	<div id="divForparticularStudent"></div>
</body>
</html>