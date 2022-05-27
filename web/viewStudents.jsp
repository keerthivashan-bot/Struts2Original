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
	window.onload=function(){
		 const formForParticularStudent = document.getElementById('formForViewingParticlarStudent');
		 formForParticularStudent.style.display = 'none';
	const btnForParticularStuDetail = document.getElementById('particularStudentShow');
	const btnForAllStuDetail=document.getElementById('submitButton');

	btnForParticularStuDetail.addEventListener('click', () => {
		document.getElementById("mydiv").innerHTML = '';
	 
		
	  if (formForParticularStudent.style.display === 'none') {
	    // 👇️ this SHOWS the form
	    formForParticularStudent.style.display = 'block';
	  } else {
	    // 👇️ this HIDES the form
	    formForParticularStudent.style.display = 'none';
	  }
	});
	
	btnForAllStuDetail.addEventListener('click', () => {
		document.getElementById("mydiv").innerHTML = '';
		document.getElementById("formForViewingParticlarStudent").reset();
		  const formForParticularStudent = document.getElementById('formForViewingParticlarStudent');
			// 👇️ this HIDES the form
		    formForParticularStudent.style.display = 'none';
		});
	
	}
	// When DOM is loaded this
	// function will get executed
	function deletingParticularStudent(rowid){
		$(() => {
				var url = "http://localhost:8080/Struts2Starter/deleteParticularStudent";
				$.ajax({
					type: "POST",
					url: url,
					data:{"rowId":rowid},
					success: function(response) {
						alert("Deleted Successfully");
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
			document.getElementById("divForparticularStudent").innerHTML ='';
			//this.disabled = true;
			var e=1;
			var url = "http://localhost:8080/Struts2Starter/viewAllStudents";
			//var e = document.getElementById("dropDownSelect");
			//var selectedDropdown = e.value;
			var selectedDropdown = e;
			$.ajax({
				type: "GET",
				url: url,
				data:{"selectedDropDownValue":selectedDropdown},
				success: function(response) {
					document.getElementById("mydiv").innerHTML ="";
					var JSONObject = JSON.parse(response);
					 var html_text=`
					        <table style="width:25%" id="table">
					          <thead>
					            <tr>
					              <th>Name</th>
					              <th>RollNo</th>
					              <th>Marks</th>
					              <th>Password</th>
					              <th>Delete Student</th>
					            </tr>
					          </thead>
					          <tbody>
					        `; 
					for(var i=0;i<JSONObject.length;i++){
						var obj=JSONObject[i];
						var idForRow=obj.id;
						html_text+="<tr id="+idForRow+">";
							html_text+="<td>";
				            html_text+=obj.name; 
				            html_text+="</td>";
				            html_text+="<td>";
				            html_text+=obj.id; 
				            html_text+="</td>";
				            html_text+="<td>";
				            html_text+=obj.marks; 
				            html_text+="</td>";
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
			
			
		}); //end of submitButton function
		
	});
	function generatediv(){
		return "<label>Name : </label><input type='text' id='updatedName' class='text1'>  <label>password : </label><input type='text' id='updatePassword' class='text3'> <button onclick='checker(this)'>Update</button>";
	}
	
	$(() => {
		$("#submitBtnForDropdown").click(function(ev) {
			//event.preventDefault();
			var e = document.getElementById("dropDownSelect");
			var selectedDropdown = e.value;
			var url = "http://localhost:8080/Struts2Starter/sorting";
			//document.getElementById(submitBtnForDropdown).disabled = true;
			//this.disabled = true;
			$.ajax({
				type: "GET",
				url: url,
				data:{"selectedDropDownValue":selectedDropdown},
				success: function(response) {
					 $('#divForparticularStudent').html('');
					document.getElementById("mydiv").innerHTML ="";
					var JSONObject = JSON.parse(response);
					 var html_text=`
					        <table style="width:25%" id="table">
					          <thead>
					            <tr>
					              <th>Name</th>
					              <th>RollNo</th>
					              <th>Marks</th>
					              <th>Password</th>
					              <th>Delete Student</th>
					            </tr>
					          </thead>
					          <tbody>
					        `; 
					for(var i=0;i<JSONObject.length;i++){
						var obj=JSONObject[i];
						var idForRow=obj.id;
						html_text+="<tr id="+idForRow+">";
							html_text+="<td>";
				            html_text+=obj.name; 
				            html_text+="</td>";
				            html_text+="<td>";
				            html_text+=obj.id; 
				            html_text+="</td>";
				            html_text+="<td>";
				            html_text+=obj.marks; 
				            html_text+="</td>";
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
					alert("some Error");
				}	
			});
			
			event.preventDefault();
		}); //end of submitButton function
		
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
<table>	
	<thead>
		<tr>
			<th>
				<form action='' id = "formid">
					<button type='submit'
						id='submitButton'>
						VIEW ALL STUDENTS
					</button>
				</form>
			</th>
			<th>
				<button id="particularStudentShow">Particular Student Details</button>
			</th>
			<th>
				<form action="">
				  
				  <select id="dropDownSelect">
					  <option value="" selected disabled hidden>Choose here</option>
					  <option value="1">Name</option>
					  <option value="2">Roll No</option>
					  <option value="3">Marks</option>
				  </select>
				 <button id = "submitBtnForDropdown">submit</button>
				  <!-- <br><br>
				  <input type="submit" value="Submit"> -->
				</form>
			</th>
		</tr>
	</thead>
</table>	
<div id="mydiv"></div>	
<div id="divForUpdatingStudent"></div>
<form action='' name="form1" id="formForViewingParticlarStudent">
					<table style="width: 35%">
						<tr>
							<td><h3>VIEW PARTICULAR STUDENT</h3></td>
							<td><input type="number" name="userName1" required></td>
							<td><button type='submit' id='submitButton1'>Submit</button></td>
						</tr>
					</table>
					
</form>
	<div id="divForparticularStudent"></div>
</body>
</html>