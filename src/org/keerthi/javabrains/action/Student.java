package org.keerthi.javabrains.action;

import java.io.*;
import org.json.JSONObject;

public class Student implements Serializable{
	private String name;
	private int id;
	private int marks;
	private String password;
	
	Student () {}
	
	Student(String name,int id,int marks,String password){
		this.name=name;
		this.id=id;
		this.marks=marks;
		this.password=password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return(" Student Name "+this.name+" and student id is "+this.id+" and "+this.name+" amrk is "+this.getMarks()+" "+this.name+" password is "+this.password+"\n");
	}
	
	public JSONObject getObject(){
		JSONObject obj = new JSONObject();
		obj.put("name",this.name);
		obj.put("id",this.id);
		obj.put("marks",this.marks);
		obj.put("password",this.password);
		return obj;
	}
}
