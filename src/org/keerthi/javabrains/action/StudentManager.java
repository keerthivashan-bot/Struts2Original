package org.keerthi.javabrains.action;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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
	 
	 public JSONArray getJsonArrayFromBinaryFile(HashMap<Integer,Object> studentHash) throws FileNotFoundException, IOException {
		    jsonArrayFromBinary.clear();
		    
		    for(Entry<Integer, Object> entry:studentHash.entrySet()){
		    	JSONObject obj1 = new JSONObject();
		        int key=entry.getKey();  
		        Student stu=(Student) entry.getValue();  
		        obj1.put("name", stu.getName());
		        obj1.put("id", key);
		        obj1.put("tamil",stu.getTamil());
		        obj1.put("english",stu.getEnglish());
		        obj1.put("maths",stu.getMaths());
		        obj1.put("science",stu.getScience());
		        obj1.put("social",stu.getSocial());
		        obj1.put("password", stu.getPassword());
		        jsonArrayFromBinary.put(obj1);
		    }    
		    
		    	    
		 return jsonArrayFromBinary;
	 }
	 
	 public String findingParticularStuDetail(int id,HashMap<Integer,Object> studentHash) throws IOException {
		 if( studentHash.containsKey(id)) {
			 Object obj = studentHash.get(id);
			 return obj.toString();
 		}
		 else {
			 return("No student available on this rollno"+id);
		 }

	 }
	 
//	 copying data from binary file to Hashmap
	 public void adddingDataToHashMap(HashMap<Integer,Object> studentHash) throws IOException {
		
		 int currentStudentId=0;
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
					currentStudentId=s.getId();
					studentHash.put(currentStudentId,s);
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
	 }
	 
	 public String addStudentInManager(String stringArrayOfStudents,File file,HashMap<Integer,Object> studentHash) throws IOException {
		 JSONArray arrayOfStudents = new JSONArray(stringArrayOfStudents);
			StudentManager sm  = StudentManager.getInstance();
//			PrintWriter out = resp.getWriter();	
			String repeatedStudentsId="";
			String repStudIdInHash="";
			ObjectInputStream is=null;
			boolean ToaddStuDetailinHashMap=true;
		    try {
		    	is =new ObjectInputStream(new FileInputStream("C:\\Users\\Keerthi\\eclipse-workspace\\Struts2Starter\\src\\org\\keerthi\\javabrains\\action\\StudentsDetails.dat"));
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		 	
			ObjectOutputStream objOutput = file.exists() ? new AppendingObjectOutputStream(new FileOutputStream(file,true)):  new ObjectOutputStream(new FileOutputStream(file,true));
				for(int i=0;i<arrayOfStudents.length();i++) {	
			    	JSONObject innerObj =  arrayOfStudents.getJSONObject(i);
			    	String name=(String)innerObj.get("name");
			    	String id=(String)innerObj.get("id");
			    	int id1=Integer.parseInt(id);
			    	String mar=(String)innerObj.get("tamil");
			    	int tamil=Integer.parseInt(mar);
			    	String eng=(String)innerObj.get("english");
			    	int english=Integer.parseInt(eng);
			    	String mat=(String)innerObj.get("maths");
			    	int maths=Integer.parseInt(mat);
			    	String sci=(String)innerObj.get("science");
			    	int science=Integer.parseInt(sci);
			    	String soc=(String)innerObj.get("social");
			    	int social=Integer.parseInt(soc);
			    	String password=(String)innerObj.get("password");
			    	String tempRepeatedStudentsId=" ";
			    	boolean ifExist=false;
			    	
			    	
//			    	Checking If the student already exists in hashMap
			    		if(studentHash.containsKey(id1)) {
			    			ifExist=true;
			    			repStudIdInHash+=" "+id;
			    		}
			    	
			    	if(!ifExist) {
					 	boolean eof=false;
						Student s = new Student();
						while(!eof) {
							try {
								s = (Student)is.readObject();
								if(s.getId()==id1) {
									tempRepeatedStudentsId+=id1+" ";
									repeatedStudentsId+=id1+" ";
								}
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
						}
						if(tempRepeatedStudentsId==" ") {
				    		Student s1= new Student(name,id1,tamil,english,maths,science,social,password);
					    	sm.addStudent(s1);
					    	int idForHash=s1.getId();
					    	studentHash.put(idForHash,s1);
					    	objOutput.writeObject(s1);
						}
					}
				}
				is.close();
				objOutput.close();	
				 if(repStudIdInHash=="") {
					 repStudIdInHash+="Students added successfully";
			 		return("Students added successfully");
			 	}
			 	else {
			 		return("Roll no "+repStudIdInHash+" are already present");
			 	}
	 }
	 
	 public JSONArray sortingStudent(HashMap<Integer,Object> studentHash,int no) {
		 jsonArray.clear();
		 Set<Entry<Integer,Object>> entrySet=studentHash.entrySet();
		 List<Entry<Integer,Object>> list= new ArrayList<>(entrySet);
		 if(no==1) {
			 Collections.sort(list,new Comparator<Entry<Integer,Object>>(){
				 @Override
				 public int compare(Entry<Integer,Object>o1,Entry<Integer,Object>o2) {
					 Student s1=(Student)o1.getValue();
					 Student s2=(Student)o2.getValue();
					 return s1.getName().compareTo(s2.getName());
				 }
			 });
			 
		 }
		 
		 else if(no==2) {
			 Collections.sort(list,new Comparator<Entry<Integer,Object>>(){
				 @Override
				 public int compare(Entry<Integer,Object>o1,Entry<Integer,Object>o2) {
					 Student s1=(Student)o1.getValue();
					 Student s2=(Student)o2.getValue();
					 return s1.getId()-s2.getId();
				 }
			 });
			 
		 }
		 
		 else if(no==3){
			 Collections.sort(list,new Comparator<Entry<Integer,Object>>(){
				 @Override
				 public int compare(Entry<Integer,Object>o1,Entry<Integer,Object>o2) {
					 Student s1=(Student)o1.getValue();
					 Student s2=(Student)o2.getValue();
					 return s1.getTamil()-s2.getTamil();
				 }
			 });
			 
		 }
		 else if(no==4){
			 Collections.sort(list,new Comparator<Entry<Integer,Object>>(){
				 @Override
				 public int compare(Entry<Integer,Object>o1,Entry<Integer,Object>o2) {
					 Student s1=(Student)o1.getValue();
					 Student s2=(Student)o2.getValue();
					 return s1.getEnglish()-s2.getEnglish();
				 }
			 });
			 
		 }
		
		 else if(no==5){
			 Collections.sort(list,new Comparator<Entry<Integer,Object>>(){
				 @Override
				 public int compare(Entry<Integer,Object>o1,Entry<Integer,Object>o2) {
					 Student s1=(Student)o1.getValue();
					 Student s2=(Student)o2.getValue();
					 return s1.getMaths()-s2.getMaths();
				 }
			 });
			 
		 }
		 else if(no==6){
			 Collections.sort(list,new Comparator<Entry<Integer,Object>>(){
				 @Override
				 public int compare(Entry<Integer,Object>o1,Entry<Integer,Object>o2) {
					 Student s1=(Student)o1.getValue();
					 Student s2=(Student)o2.getValue();
					 return s1.getScience()-s2.getScience();
				 }
			 });
		 }
		 
		 else if(no==7){
			 Collections.sort(list,new Comparator<Entry<Integer,Object>>(){
				 @Override
				 public int compare(Entry<Integer,Object>o1,Entry<Integer,Object>o2) {
					 Student s1=(Student)o1.getValue();
					 Student s2=(Student)o2.getValue();
					 return s1.getSocial()-s2.getSocial();
				 }
			 });
		 }
		 for(Entry<Integer,Object> entry:list) {
			 jsonArrayFromBinary.clear(); 
			 JSONObject obj = new JSONObject();
			 	Student s1=(Student) entry.getValue();
				obj.put("name",s1.getName());
				obj.put("id",entry.getKey());
				obj.put("tamil",s1.getTamil());
		        obj.put("english",s1.getEnglish());
		        obj.put("maths",s1.getMaths());
		        obj.put("science",s1.getScience());
		        obj.put("social",s1.getSocial());
				obj.put("password",s1.getPassword());
		jsonArray.put(obj);
		 } 
		return jsonArray;
	 }	
}
