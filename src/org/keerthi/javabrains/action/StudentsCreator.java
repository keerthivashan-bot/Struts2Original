package org.keerthi.javabrains.action;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.util.ArrayList;

public class StudentsCreator {
	private String students;
	public String execute() {
		ArrayList<Student> studentsList=new ArrayList<Student>();
		studentsList.add(new Student("Dharsan",1,90,"1@13"));
		studentsList.add(new Student("Mani",2,80,"1#133"));
		for(Student i: studentsList) {
			students+=i.toString();
			students+="\n";
		}
		return "success";		
	}
	public String getStudents() {
		return students;
	}
	public void setStudents(String studentsList) {
		this.students = studentsList;
	}
}
