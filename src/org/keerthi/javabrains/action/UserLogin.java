package org.keerthi.javabrains.action;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

public class UserLogin extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException{
		res.setContentType("text/html");
		Student s=new Student("Keerthi",1,100,"1");
		String userName=req.getParameter("userName");
		String password=req.getParameter("password");
		if(userName.equals(s.getName()) && password.equals(s.getPassword())) {
			res.sendRedirect("loggedin.jsp");
		}
		else {
			res.sendRedirect("error.jsp");
		}
		PrintWriter out= res.getWriter();
		out.println("You are logged in");
	}
}



