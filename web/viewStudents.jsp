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
	function deletingParticularStudent(rowid){
			console.log("function called "+rowid);
			$(() => {
				console.log("from Ajax Call"+rowid);
				var url = "http://localhost:8080/Struts2Starter/deleteParticularStudent";
				$.ajax({
					type: "POST",
					url: url,
					data:{"rowId":rowid},
					success: function(response) {
						alert(response);
						console.log(response);
					},
					error: function(response) {
						alert("some Error");
					}	
				});
		});
		}
		
	$(() => {
		// function will get executed
		// on click of submit button

		$("#submitButton").click(function(ev) {
			console.log("arrived Here");
			this.disabled = true;
			var url = "http://localhost:8080/Struts2Starter/viewAllStudents";
			
			$.ajax({
				type: "GET",
				url: url,
				success: function(response) {
					document.getElementById("mydiv").innerHTML ="";
					var JSONObject = JSON.parse(response);
					 var html_text=`
					        <table style="width:25%" id="table">
					          <thead>
					            <tr>
					              <th>Name</th>
					              <th>Id</th>
					              <th>Password</th>
					              <th>Delete Student</th>
					            </tr>
					          </thead>
					          <tbody>
					        `; 
					for(var i=0;i<JSONObject.length;i++){
						var obj=JSONObject[i];
						var idForRow=obj.id;
						console.log(typeof idForRow);
						//console.log(idForRow);
						html_text+="<tr id="+idForRow+">";
							html_text+="<td>";
				            html_text+=obj.name; 
				            html_text+="</td>";
				            html_text+="<td>";
				            html_text+=obj.id; 
				            html_text+="</td>"
				            html_text+="<td>";
				            html_text+=obj.password; 
				            html_text+="</td>"
				            html_text+="<td>";
				             html_text+="<button id="+idForRow+" onclick='deleteRow(this)'>DELETE</button>"; 
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
		}); //end of submitButton function
		
	});
	
	function deleteRow(rowid){  
		var row = document.getElementById(rowid.id);
		var row1=rowid.id
		deletingParticularStudent(row1);
	    row.parentNode.removeChild(row);
	}
	
	//get ajax call for particular Student
	
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
					// Some error in ajax call
					alert("some Error");
				}	
			});
			event.preventDefault();
		});
	});
	
	
	$(() => {
		// function will get executed
		// on click of submit button

		$("#submitButtonForUpdate").click(function(ev) {
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