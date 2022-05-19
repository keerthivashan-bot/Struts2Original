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
	<script>
		$(() => {
			
			$("#submitButton").click(function(event) {
				var divElement=document.createElement('div');
				
			var html_text=`
				<form action=''  method="post">
				<table style="width: 20%">
				<tr>
					<td>User Name</td>
					<td><input type="text" name="userName"/></td>
				</tr>
				<tr>	
					<td>Password</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<div></div>
				</table>
				<br></br>
			</form>	`;
			event.preventDefault();
			//document.getElementById("divAddStudent").innerHTML = html_text; 
			divElement.insertAdjacentHTML('beforeEnd',html_text);
			
			var formId=document.getElementById("divAddStudent");
			console.log(formId);
			
			formId.appendChild(divElement);
		});
		});	
	</script>
</head>
<body>
	<h2>Add Student Details</h2>
	<s:form  action="StudentAction" id="form1">
	  <s:textfield name="name" label="Name" />
	  <s:textfield type="number" name="id" label="id" value=""/>
	  <s:textfield name="password" label="Password" value=""/>
	  <div id="divAddStudent"></div>
	  <s:submit />
	</s:form>	
	
	<form action='' id = "formid">
	<button type=''
		id='submitButton'>
		ADD ONE MORE STUDENT
	</button>
</form>

</body>
</html>
