package org.keerthi.javabrains.action;

import java.io.*;
import org.json.JSONObject;

public class Student implements Serializable{
	private String name;
	private int id;
	private int tamil;
	private int english;
	private int maths;
	private int science;
	private int social;
	private String password;
	public int getTamil() {
		return tamil;
	}

	public void setTamil(int tamil) {
		this.tamil = tamil;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMaths() {
		return maths;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}

	public int getScience() {
		return science;
	}

	public void setScience(int science) {
		this.science = science;
	}

	public int getSocial() {
		return social;
	}

	public void setSocial(int social) {
		this.social = social;
	}

	
	
	Student () {}
	
	Student(String name,int id,int tamil,int english,int maths,int science,int social,String password){
		this.name=name;
		this.id=id;
		this.tamil=tamil;
		this.english=english;
		this.maths=maths;
		this.science=science;
		this.social=social;
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
		return(" Student Name "+this.name+" and student id is "+this.id+" and "+this.name+" Tamil mark is "+this.getTamil()+" and English mark is "+this.getEnglish()+" and Maths mark is "+this.getMaths()+" "+" and Science mark is "+this.getScience()+" and Social mark is "+this.getSocial()+" "+" "+this.name+" password is "+this.password+"\n");
	}
	
	public JSONObject getObject(){
		JSONObject obj = new JSONObject();
		obj.put("name",this.name);
		obj.put("id",this.id);
		obj.put("tamil",this.tamil);
		obj.put("english",this.english);
		obj.put("maths",this.maths);
		obj.put("science",this.science);
		obj.put("social",this.social);
		obj.put("password",this.password);
		return obj;
	}
}
