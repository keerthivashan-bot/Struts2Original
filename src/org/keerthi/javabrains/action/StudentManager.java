package org.keerthi.javabrains.action;

import java.io.*;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class StudentManager {
	 private static final StudentManager SINGLE_INSTANCE = new StudentManager();
	 public ArrayList<Student> studentsList=new ArrayList<Student>();
	 public JSONArray jsonArray = new JSONArray();
	 public JSONArray jsonArrayFromBinary = new JSONArray();
	 
	 
	 private StudentManager() {}
	 public String execute() {
		 return "success";
	 }
	 public static StudentManager getInstance() {
	      return SINGLE_INSTANCE;
	    }
	 
	 public void addStudent(Student s) {
		 jsonArray.clear();
		 studentsList.add(s);
		 for(Student s1:studentsList) {
			 JSONObject obj = new JSONObject();
				obj.put("name",s1.getName());
				obj.put("id",s1.getId());
				obj.put("password",s1.getPassword());
		jsonArray.put(obj);
		 }
	 }
	 
	 public JSONArray getJsonArray() {
		 return jsonArray;
	 }
	 
	 public JSONArray getJsonArrayFromBinaryFile() throws FileNotFoundException, IOException {
		    jsonArrayFromBinary.clear();
		    ObjectInputStream is=null;
		    try {
		    	is =new ObjectInputStream(new FileInputStream("C:\\Users\\Keerthi\\eclipse-workspace\\Struts2Starter\\src\\org\\keerthi\\javabrains\\action\\StudentsDetails.dat"));
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		 	boolean eof=false;
			Student s = new Student();
			
			while(!eof) {
				try {
					s = (Student)is.readObject();
					JSONObject obj1 = new JSONObject();
					obj1.put("name",s.getName());
					obj1.put("id",s.getId());
					obj1.put("password",s.getPassword());
					jsonArrayFromBinary.put(obj1);
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				} 
				catch(EOFException e) {
					eof=true;
					break;
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			is.close();
		 return jsonArrayFromBinary;
	 }
	 
	 public String findingParticularStuDetail(int id) throws IOException {
		 
		 ObjectInputStream is=null;
		    try {
		    	is =new ObjectInputStream(new FileInputStream("C:\\Users\\Keerthi\\eclipse-workspace\\Struts2Starter\\src\\org\\keerthi\\javabrains\\action\\StudentsDetails.dat"));
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		 	boolean eof=false;
			Student s = new Student();
			
			while(!eof) {
				try {
					s = (Student)is.readObject();
					if(s.getId()==id) {
						return s.toString();
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} 
				catch(EOFException e) {
					eof=true;
					break;
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			is.close();
		 return "No Student Available ";
	 }
}
