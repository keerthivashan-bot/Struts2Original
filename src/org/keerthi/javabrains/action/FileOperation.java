package org.keerthi.javabrains.action;

import java.io.*;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;



public class FileOperation {
	 HttpServletResponse resp;
	 File file = new File("C:\\\\Users\\\\Keerthi\\\\eclipse-workspace\\\\Struts2Starter\\\\src\\\\org\\\\keerthi\\\\javabrains\\\\action\\\\CustomerDetails.dat");
	public  String dataWriter(File f,Customer c) throws FileNotFoundException, IOException {
		ObjectOutputStream objOutput = file.exists() ? new AppendingObjectOutputStream(new FileOutputStream(file,true)):  new ObjectOutputStream(new FileOutputStream(file,true));
		objOutput.writeObject(c);
		objOutput.close();	
		return("Students added successfully");
	}
	
	public void adddingDataToHashMap(HashMap<Integer,Customer> customerHash) throws IOException {
		 int currentCustomerId=0;
		    ObjectInputStream is=null;
		    try {
		    	is =new ObjectInputStream(new FileInputStream("C:\\Users\\Keerthi\\eclipse-workspace\\Struts2Starter\\src\\org\\keerthi\\javabrains\\action\\CustomerDetails.dat"));
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		 	boolean eof=false;
		 	Customer cus = null;
			while(!eof) {
				try {
					cus = (Customer)is.readObject();
					currentCustomerId=cus.getCustomerId();
					customerHash.put(currentCustomerId,cus);
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
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(customerHash);
			is.close();		 
	 }
}
	
