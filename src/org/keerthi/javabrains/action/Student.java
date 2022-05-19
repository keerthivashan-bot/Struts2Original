package org.keerthi.javabrains.action;

import org.json.JSONObject;

public class Student {
	private String name;
	private int id;
	private String password;
	
	Student () {}
	
	Student(String name,int id,String password){
		this.name=name;
		this.id=id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return(" Student Name "+this.name+" and student id is "+this.id+" and "+this.name+" password is "+this.password+"\n");
	}
	
	public JSONObject getObject(){
		JSONObject obj = new JSONObject();
		obj.put("name",this.name);
		obj.put("id",this.id);
		obj.put("password",this.password);
		return obj;
	}
}
