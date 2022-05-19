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
	
	// When DOM is loaded this
	// function will get executed
	$(() => {
		// function will get executed
		// on click of submit button
		$("#submitButton").click(function(ev) {
			var form = $("#formid");
			var url = "http://localhost:8080/Struts2Starter/loggedin";
			$.ajax({
				type: "POST",
				url: url,
				data: form.serialize(),
				success: function(data) {
					// Ajax call completed successfully
					$("html").html(data);
				},
				error: function(data) {
					// Some error in ajax call
					alert("some Error");
				}	
			});
			event.preventDefault();
		});
	});
	</script>
</head>
<body>
	<form action='' id = "formid" method="post">
			<table style="width: 20%">
			<tr>
				<td>User Name</td>
				<td><input type="text" name="userName"/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"/></td>
			</tr>
		</table>
		<button type='submit'
			id='submitButton'>
			Submit
		</button>
		<!--  input type="submit" id ="submit" value="Login" />-->
	</form>	
</body>
</html>

