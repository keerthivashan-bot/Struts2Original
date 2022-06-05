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
				    <label>Roll No</label>
				   <input type="number" id="id" placeholder="Roll no" class="text2">
				   <label>Tamil</label>
				   <input type="number" id="id" placeholder="Enter mark" class="text4">
				   <label>English</label>
				   <input type="number" id="id" placeholder="Enter mark" class="text5">
				   <label>Maths</label>
				   <input type="number" id="id" placeholder="Enter mark" class="text6">
				   <label>Science</label>
				   <input type="number" id="id" placeholder="Enter mark" class="text7">
				   <label>Social</label>
				   <input type="number" id="id" placeholder="Enter mark" class="text8">
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
			return "<label>Name</label><input type='text' placeholder='Enter name' class='text1'>  <label>Roll No</label><input type='number' placeholder='Enter id'class='text2'> <label>Tamil</label><input type='number' placeholder='Enter mark'class='text4'> <label>English</label><input type='number' placeholder='Enter mark'class='text5'><label>Maths</label><input type='number' placeholder='Enter mark'class='text6'>  <label>Science</label><input type='number' placeholder='Enter mark'class='text7'><label>Social</label><input type='number' placeholder='Enter mark'class='text8'> <label>Password</label><input type='password' placeholder='Enter Password' class='text3'> <button onclick='removediv(this)'>Remove</button>";
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
			var text4Count=document.querySelectorAll(".text4");
			var text5Count=document.querySelectorAll(".text5");
			var text6Count=document.querySelectorAll(".text6");
			var text7Count=document.querySelectorAll(".text7");
			var text8Count=document.querySelectorAll(".text8");
			
			var str="";
			for(var i=0;i<text1Count.length;i++){
				array[i]={
						"name"     :text1Count[i].value,
						"id"       :text2Count[i].value,
						"tamil"    :text4Count[i].value,
						"english"  :text5Count[i].value,
						"maths"    :text6Count[i].value,
						"science"  :text7Count[i].value,
						"social"   :text8Count[i].value,
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