package org.keerthi.javabrains.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.keerthi.javabrains.action.*;
//import org.keerthi.javabr000ains.action.StudentsCreator.studentsList;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.*;
public class StudentAction  extends ActionSupport implements ModelDriven<Student>, ServletRequestAware, ServletResponseAware{
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	public JSONArray jsonArray1 = new JSONArray();
	public int count=0;
	File file = new File("C:\\\\Users\\\\Keerthi\\\\eclipse-workspace\\\\Struts2Starter\\\\src\\\\org\\\\keerthi\\\\javabrains\\\\action\\\\StudentsDetails.dat");
 			
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
	
	public void addStudent() throws IOException {
		String stringArrayOfStudents=req.getParameter("arrayOfObjects");
		JSONArray arrayOfStudents = new JSONArray(stringArrayOfStudents);
		StudentManager sm  = StudentManager.getInstance();
		PrintWriter out = resp.getWriter();	
		String repeatedStudentsId="";
		ObjectInputStream is=null;
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
		    	String password=(String)innerObj.get("password");
		    	String tempRepeatedStudentsId=" ";
		    	
//		    	Checking If the student already exists
		    	
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
		    		Student s1= new Student(name,id1,password);
			    	sm.addStudent(s1);
			    	objOutput.writeObject(s1);
				}
			}
			is.close();
			objOutput.close();
			if(repeatedStudentsId=="") {
	    		repeatedStudentsId+="Students added successfully";
	    		out.print("Students added successfully");
	    	}
	    	else {
	    		out.print(repeatedStudentsId+" are already present");
	    	}
	}
	
	
	public void getAllStudents() throws IOException {
		StudentManager sm  = StudentManager.getInstance();
		
		resp.setStatus(200);
		PrintWriter out = resp.getWriter();
		
		String jsonString=sm.getJsonArrayFromBinaryFile().toString();
		out.print(jsonString);
	}

	public void searchStudent() throws IOException {
		StudentManager sm  = StudentManager.getInstance();
		String name=req.getParameter("name");
		int id=Integer.parseInt(name);
		String studentDetail=sm.findingParticularStuDetail(id);
		PrintWriter out = resp.getWriter();
		out.print(studentDetail);	
	}	
	
	public void deleteParticularStudent() throws FileNotFoundException, IOException {
		File tempFile = new File("C:\\\\Users\\\\Keerthi\\\\eclipse-workspace\\\\Struts2Starter\\\\src\\\\org\\\\keerthi\\\\javabrains\\\\action\\\\temp.dat");
//		FileOutputStream outfile =null;		
		ObjectInputStream is=null;
		int rowId=Integer.parseInt(req.getParameter("rowId"));
		try {
	    	is =new ObjectInputStream(new FileInputStream("C:\\Users\\Keerthi\\eclipse-workspace\\Struts2Starter\\src\\org\\keerthi\\javabrains\\action\\StudentsDetails.dat"));
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
		ObjectOutputStream objOutput = tempFile.exists() ? new AppendingObjectOutputStream(new FileOutputStream(tempFile,true)):  new ObjectOutputStream(new FileOutputStream(tempFile,true));
		boolean eof=false;
		Student s = new Student();
		while(!eof) {		
			try {
				s = (Student)is.readObject();
				if(s.getId()!=rowId) {
					objOutput.writeObject(s);
				}
				else {
					continue;
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
		is.close();
		objOutput.close();
		file.delete();
			
		File tempFile1 = new File("C:\\\\Users\\\\Keerthi\\\\eclipse-workspace\\\\Struts2Starter\\\\src\\\\org\\\\keerthi\\\\javabrains\\\\action\\\\StudentsDetails.dat");				
		tempFile.renameTo(tempFile1);
	}
	
	public void updateParticularStudent() throws IOException {
		JSONObject jsonObj=new JSONObject();    
		int stuId=Integer.parseInt(req.getParameter("stuId"));	
		PrintWriter out = resp.getWriter();
//		out.print(studentDetail);
		System.out.println(stuId);
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
					if(s.getId()==stuId) {
						jsonObj.put("name", s.getName());
						jsonObj.put("password", s.getPassword());
						out.print(jsonObj);
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
	}
}
