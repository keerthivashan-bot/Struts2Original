<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
								<script src=
	"https://code.jquery.com/jquery-3.5.0.js">
	</script>

</head>
<body>
	<form action='buttonLoggedin' id = "formid">
	<button type='submit'>
		BACK
	</button>
</form>
	<h2>Add Student Details</h2>
	<div id="box">
				   <label>Name</label>
				   <input type="text" id="userName" placeholder="Enter name" class="text1">
				    <label>Id</label>
				   <input type="number" id="id" placeholder="Enter id" class="text2">
				   <label>Password</label>
				   <input type="password" id="password" placeholder="Enter Password" class="text3">
				   <button id='btn'>ADD ONE MORE STUDENT</button>
				   
	</div>
	<button id="submitButton">
		SUBMIT
	</button>
	<script>
		var btn=document.querySelector("#btn");
		btn.onclick=function(){
			var div=document.createElement("div");
			div.innerHTML=generatediv();
			document.getElementById("box").appendChild(div);
		}
		function generatediv(){
			return "<label>Name</label><input type='text' placeholder='Enter name' class='text1'>  <label>Id</label><input type='number' placeholder='Enter id'class='text2'> <label>Password</label><input type='password' placeholder='Enter Password' class='text3'> <button onclick='removediv(this)'>Remove</button>";
		}
		function removediv(div){
			document.getElementById("box").removeChild(div.parentNode);
		}
			
		var submitButton=document.querySelector("#submitButton");
		submitButton.onclick=function(){
			var array=[];
			var text1Count=document.querySelectorAll(".text1");
			var text2Count=document.querySelectorAll(".text2");
			var text3Count=document.querySelectorAll(".text3");
			
			var str="";
			for(var i=0;i<text1Count.length;i++){
				array[i]={
						"name"     :text1Count[i].value,
						"id"       :text2Count[i].value,
						"password" :text3Count[i].value
				}
			}
			
			$(() => {
					console.log("from Ajax Call");
					var strArray=JSON.stringify(array);
					console.log(strArray);
					var url = "http://localhost:8080/Struts2Starter/StudentAction";
					$.ajax({
						type: "POST",
						url: url,
						data:{"arrayOfObjects":strArray},
						
						success: function(response) {
							alert(response);
							 window.location.reload(); 
							
							console.log(response);
						},
						error: function(response) {
							alert("some Error");
						}	
					});
			});
		}
	</script>	
</body>
</html>