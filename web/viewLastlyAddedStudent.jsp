<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Struts 2 ModelDriven example</h1>

	<h2>Student Details</h2>
	Name : <s:property value="name" /><br>
	Id:	   <s:property value="id" /><br>
	Password : <s:property value="password" /><br>
	
	<form action='buttonLoggedin' id = "formid">
	<button type='submit'>
		BACK
	</button>
</form>
</body>
</html>