package org.keerthi.javabrains.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
import java.util.Map.Entry;


public class CustomerAction  implements ServletRequestAware, ServletResponseAware{
	FileOperation f = new FileOperation();
	
	public static HashMap<Integer,Customer> customerHash = new HashMap<Integer,Customer>();
	public static HashMap<Integer,String> passwordHash = new HashMap<Integer,String>();
	public static ArrayList<Integer> loggedInUse = new ArrayList<Integer>();
	static int loggedUser;
	public JSONArray jsonArray1 = new JSONArray();
	 File file = new File("C:\\\\Users\\\\Keerthi\\\\eclipse-workspace\\\\Struts2Starter\\\\src\\\\org\\\\keerthi\\\\javabrains\\\\action\\\\CustomerDetails.dat");
	HttpServletRequest req;
	HttpServletResponse resp;
	FileOperation fo = new FileOperation();
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
	
//	static {
//		Bank bk  = Bank.getInstance();
//			try {
//			bk.adddingDataToHashMap(customerHash);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	}
	public String addingCustomer() throws IOException {
		System.out.println("addingCustomer");
		PrintWriter out = resp.getWriter();
		String customerName=req.getParameter("name");
		String password=req.getParameter("password");
		String conpassword=req.getParameter("confirmPassword");
		System.out.println(customerName);
		//boolean passwordCrt=true;
		
		
		
		if(password.equals(conpassword)) {
			String encryptedPassword=passwordEncryptor(password);
			
			int smallLet=0;
		    int capLet=0;
		    int numeric=0;
		    boolean passVal=true;
		    for(int i=0;i<password.length();i++){
		        if(Character.isUpperCase(password.charAt(i))){
		            capLet++;
		        }
		        if(Character.isLowerCase(password.charAt(i))){
		            smallLet++;
		        }
		        
		        if(Character.isDigit(password.charAt(i))){
		            numeric++;
		        }
		    }
		    if(smallLet<2 || capLet<2 || numeric<2){
		        passVal=false;
		    }
		    
			if(passVal) {
				Customer c1 = new Customer(customerName,encryptedPassword,10000);
				passwordHash.put(c1.getCustomerId(),password);
				loggedUser=c1.getCustomerId();
				//loggedInUser.add(c1.getCustomerId());	
				fo.dataWriter(file, c1);
				customerHash.put(c1.getCustomerId(), c1);
				System.out.println("encryptedPassword"+encryptedPassword);
				System.out.println(customerHash);
				System.out.println(passwordHash);
				out.print("Customer Added Successfully");
				return "success";
			}
			
		}
		else {
			out.print("your password and confirm password are not matching");
			//passwordCrt=false;
		}
		return "failure";
	}
	
	public String loginchecker() throws IOException {
		System.out.println("customerHash "+customerHash);
		PrintWriter out = resp.getWriter();
		int customerId=Integer.parseInt(req.getParameter("customerId"));
		System.out.println("customerId is "+customerId);
		String password=req.getParameter("password");
		String passwordAfterencrypted = passwordEncryptor(password);
		System.out.println("after Encrypted "+passwordAfterencrypted);
		
		if(customerHash.containsKey(customerId)) {
			Customer obj = customerHash.get(customerId);
			String encryptedPasswordFromHash = obj.getPassword();
			if(encryptedPasswordFromHash.equals(passwordAfterencrypted)) {
				loggedUser=customerId;
				return "success";
			}
			else {
				out.print("The password is incorrect");
			}
		}
		else {
			out.print("The customer id is incorrect");
		}
		return "failure";
	}	
	
	public void atmWithdrawal() throws IOException {
		System.out.println("arrived atmWithdrawal");
		Bank b= Bank.getInstance();
		PrintWriter out = resp.getWriter();
		int amountToBeWithDraw=Integer.parseInt(req.getParameter("amount"));
		String result =b.atmWithdrawalInBank(customerHash,loggedUser,amountToBeWithDraw);
//		sort();
		out.print(result);
	}
	
	public void cashDeposit() throws IOException {
		Bank b= Bank.getInstance();
		PrintWriter out = resp.getWriter();
		int amountToBeDeposit=Integer.parseInt(req.getParameter("amount"));
		String result =b.cashDepositInBank(customerHash,loggedUser,amountToBeDeposit);
		out.print(result);
	}
	
	public void accountTransfer() throws IOException {
		PrintWriter out = resp.getWriter();
		int accno=Integer.parseInt(req.getParameter("accno"));
		int amt = Integer.parseInt(req.getParameter("amt"));
		Customer c=customerHash.get(loggedUser);
		int currentLoginCustomer =loggedUser;// loggedInUser.get(0);
		Bank b= Bank.getInstance();
		String result = b.AccountWithdrawalInBank(customerHash, accno,c,amt,currentLoginCustomer);
		out.print(result);
	}
	
	public String customerLogout() {
		loggedUser=0;
		return "success";
	}
	
	public String passwordEncryptor(String passwordenc) {
		String newPassword="";
		for(int i=0;i<passwordenc.length();i++){
		    if(Character.isDigit(passwordenc.charAt(i))){
		        int no = Integer.parseInt(String.valueOf(passwordenc.charAt(i)));
		        if(no>=9)
		            newPassword+=0;
		        else{
		             no+=1;
    		        newPassword+=no;
		        }
		    }
		    else if(Character.isAlphabetic(passwordenc.charAt(i))){
		        if(passwordenc.charAt(i)=='z' || passwordenc.charAt(i)=='Z'){
		            newPassword+='a';
		        }
		        else{
		            char ch=(char)(passwordenc.charAt(i)+1);
		         newPassword+=ch;
		        }
		         
		    }
		    else{
		        newPassword+=passwordenc.charAt(i);
		    }
		}
		return newPassword;
	}
	
	public String sort() {
		Set<Entry<Integer,Customer>> entrySet=customerHash.entrySet();
		 List<Entry<Integer,Customer>> list= new ArrayList<>(entrySet);
		
			 Collections.sort(list,new Comparator<Entry<Integer,Customer>>(){
				 @Override
				 public int compare(Entry<Integer,Customer>o1,Entry<Integer,Customer>o2) {
					 Customer s1=o1.getValue();
					 Customer s2=(Customer)o2.getValue();
					 return s1.getBalance()-s2.getBalance();
				 }
			 });	
			 Customer c1=(Customer) list.get(0);
			 System.out.println(c1.getCustomerId());
			return "success";
	}
}
