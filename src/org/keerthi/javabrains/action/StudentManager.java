package org.keerthi.javabrains.action;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class StudentManager {
	 private static final StudentManager SINGLE_INSTANCE = new StudentManager();
	 public ArrayList<Student> studentsList=new ArrayList<Student>();
	 public JSONArray jsonArray = new JSONArray();
	 
	 private StudentManager() {}
	 public String execute() {
		 return "success";
	 }
	 public static StudentManager getInstance() {
	      return SINGLE_INSTANCE;
	    }
	 
	 public void addStudent(Student s) {
		 studentsList.add(s);
		 for(Student s1:studentsList) {
//			 System.out.println(s1.getName());
			 JSONObject obj = new JSONObject();
				obj.put("name",s1.getName());
				obj.put("id",s1.getId());
				obj.put("password",s1.getPassword());
			 
		jsonArray.put(obj);
//		System.out.println(jsonArray);
		 }
	 }
	 
	 public JSONArray getJsonArray() {
		 System.out.println("From getJsonArray() ");
		 System.out.println(jsonArray);
		 return jsonArray;
	 }
	 
	 public String findingParticularStuDetail(String name) {
		 
		 System.out.println("Arrived serach student");
		 for(Student s1:studentsList) {
			 if(s1.getName().equals(name)) {
				 return s1.toString();
			 }
		 }
		 return "No Student Available ";
	 }
}

