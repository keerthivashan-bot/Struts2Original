package org.keerthi.javabrains.action;


import java.io.IOException;
import java.io.PrintWriter;

//public String execute(){
//	System.out.println("im from UserLogin");
//	return"";
//	}
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

public class UserLogin extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException{
		res.setContentType("text/html");
		Student s=new Student("Keerthi",1,"1");
		System.out.println("From user Login");
		
		String userName=req.getParameter("userName");
		String password=req.getParameter("password");
		System.out.println(userName);
		System.out.println(password);
		if(userName.equals(s.getName()) && password.equals(s.getPassword())) {
			System.out.println("You are a valid user");
			res.sendRedirect("loggedin.jsp");
		}
		else {
			System.out.println("You are a Invalid user");
			res.sendRedirect("error.jsp");
		}
		PrintWriter out= res.getWriter();
		out.println("You are logged in");
	}
}



