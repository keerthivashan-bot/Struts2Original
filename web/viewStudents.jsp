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
	
	// When DOM is loaded this
	// function will get executed
	$(() => {
		// function will get executed
		// on click of submit button
		$("#submitButton").click(function(ev) {
			
			var url = "http://localhost:8080/Struts2Starter/viewAllStudents";
			$.ajax({
				type: "GET",
				url: url,
	
				success: function(response) {
					document.getElementById("mydiv").innerHTML = response; 
					var JSONObject = JSON.parse(response);
					 var html_text=`
					        <table style="width:25%">
					          <thead>
					            <tr>
					              <th>Name</th>
					              <th>Id</th>
					              <th>Password</th>
					            </tr>
					          </thead>
					          <tbody>
					        `;
					for(var i=0;i<JSONObject.length;i++){
						html_text+="<tr>";
							var obj=JSONObject[i];
							html_text+="<td>";
				             html_text+=obj.name; 
				            html_text+="</td>"
				            	html_text+="<td>";
				             html_text+=obj.id; 
				            html_text+="</td>"
				            	html_text+="<td>";
				             html_text+=obj.password; 
				            html_text+="</td>"
			          html_text+="</tr>";
					}
					document.getElementById("mydiv").innerHTML = html_text;
				},
				error: function(response) {
					// Some error in ajax call
					alert("some Error");
				}	
			});
			event.preventDefault();
		});
	});
	
	//get ajax call for particular Student
	
	$(() => {
		// function will get executed
		// on click of submit button
		$("#submitButton1").click(function() {
			
			var name = document.form1.userName1.value;
			console.log(name);
			
			var url = "http://localhost:8080/Struts2Starter/viewParticularStudent";
			$.ajax({
				type: "GET",
				url: url,
				data:{"name":name},
				
				success: function(response) {
					// Ajax call completed successfully
					
					//alert(response);
					//console.log(data);
					//$("html").html(response);
					document.getElementById("divForparticularStudent").innerHTML = response; 
				},
				error: function(response) {
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
<form action='buttonLoggedin' id = "formid">
	<button type='submit'>
		BACK
	</button>
</form>
	<h1>Student Details Page</h1>
	
<form action='' id = "formid">
	<button type='submit'
		id='submitButton'>
		VIEW ALL STUDENTS
	</button>
</form>
<div id="mydiv"></div>	
<form action='' name="form1">
	<table style="width: 35%">
		<tr>
			<td>VIEW PARTICULAR STUDENT</td>
			<td><input type="text" name="userName1"/></td>
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