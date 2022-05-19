package org.keerthi.javabrains.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.keerthi.javabrains.action.*;
//import org.keerthi.javabrains.action.StudentsCreator.studentsList;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StudentAction  extends ActionSupport implements ModelDriven<Student>, ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	public JSONArray jsonArray1 = new JSONArray();
	
	Student student = null;
	public Student getModel() {
		student = new Student();
		return student;
	}
	
	public void setServletResponse(HttpServletResponse resp) {
		this.resp = resp;
	}
	
	public HttpServletRequest getServletResponse() {
		return this.req;
	}
	
	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public HttpServletRequest getServletRequest() {
		return this.req;
	}
	public String addStudent() {
		System.out.println("Arrived add Student");
		StudentManager sm  = StudentManager.getInstance();
		sm.addStudent(student);
		return "success";
	}
	
	public void getAllStudents() throws IOException {
		System.out.println("From get all Students");
		StudentManager sm  = StudentManager.getInstance();
		resp.setStatus(200);
		PrintWriter out = resp.getWriter();
		String jsonString=sm.getJsonArray().toString();
		out.print(jsonString);
	}

	public void searchStudent() throws IOException {
		System.out.println("Going To search student");
		StudentManager sm  = StudentManager.getInstance();
		String name=req.getParameter("name");
		System.out.println("name is "+name);
		System.out.println(sm.findingParticularStuDetail(name));
		String studentDetail=sm.findingParticularStuDetail(name);
		PrintWriter out = resp.getWriter();
		out.print(studentDetail);	
	}
	
}


