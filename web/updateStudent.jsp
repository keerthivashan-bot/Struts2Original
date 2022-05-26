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
		// function will get executed
		// on click of submit button

		$("#submitButtonForUpdate").click(function(ev) {
			this.disabled = true;
			console.log("arrived Here++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			var stuId = document.updatingForm.updatingStudentId.value;
			var url = "http://localhost:8080/Struts2Starter/updateParticularStudent";
			
			$.ajax({
				type: "GET",
				url: url,
				data:{"stuId":stuId},
				dataType: 'json',
				success: function(response) {
					var div=document.createElement("div");
					var name=response.name;
					var password =response.password;
					alert(password);
					var password=response.password
					div.innerHTML=generatediv();
					document.getElementById("divForUpdatingStudent").appendChild(div);
					let nameField = document.getElementById('updatedName');
					nameField.value=name;
					let passwordField = document.getElementById('updatePassword');
					passwordField.value=password;				 
				},
				error: function(response) {
					// Some error in ajax call
					alert("some Error");
				}	
			});
			
			event.preventDefault();
		}); //end of submitButton function
		
	});
	function generatediv(){
		return "<label>Name : </label><input type='text' id='updatedName' class='text1'>  <label>password : </label><input type='text' id='updatePassword' class='text3'> <button onclick='checker(this)'>Update</button>";
	}
	</script>
</head>
<body>
	<form action='' name="updatingForm">
		<table style="width: 35%">
			<tr>
				<td><h2>UPDATE PARTICULAR STUDENT</h2></td>
				<td><input type="number" name="updatingStudentId"/></td>
			</tr>
		</table>
	<button type='submit' id='submitButtonForUpdate'>
		Submit
	</button>
	</form>	
	<div id="divForUpdatingStudent"></div>
</body>
</html>