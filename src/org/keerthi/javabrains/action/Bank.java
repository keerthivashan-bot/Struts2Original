package org.keerthi.javabrains.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bank {
	 private static final Bank SINGLE_INSTANCE = new Bank();
	 private Bank() {}
	 public String execute() {
		 return "success";
	 }
	 public static Bank getInstance() {
	      return SINGLE_INSTANCE;
	 }
	  
	 public String atmWithdrawalInBank(HashMap<Integer,Customer> customerHash,int loggedUser,int amountToBeWithDraw) {
			int recentlyLoggedInUser=loggedUser;
			Customer obj = customerHash.get(recentlyLoggedInUser);
			int currentBalance=obj.getBalance();
			if(currentBalance<amountToBeWithDraw) {
				return("The current Balance is very low to withdraw");
			}
			if(currentBalance>1000 && currentBalance>amountToBeWithDraw) {
				//loggedInUser.remove(0);
				if(amountToBeWithDraw>5000) {
					currentBalance-=amountToBeWithDraw+10;
				}
				else {
					currentBalance-=amountToBeWithDraw;
				}	
				obj.setBalance(currentBalance);
				//customerHash.remove(recentlyLoggedInUser);
				customerHash.put(recentlyLoggedInUser, obj);
				Customer obj1= customerHash.get(recentlyLoggedInUser);
				return("The withdrawl completed and Your current Account Balance is"+obj1.getBalance());
			}
			else {
				return("The current Balance is very low to withdraw");
			}	
	 }
	 
	 public String cashDepositInBank(HashMap<Integer,Customer> customerHash,int loggedUser,int depositedAmount) {
		 	int recentlyLoggedInUser=loggedUser;
		 	int totalBalance=0;
			Customer obj = customerHash.get(recentlyLoggedInUser);
			int currentBalance=obj.getBalance();
			if(depositedAmount>5000) {
				totalBalance=currentBalance+depositedAmount-10;
			}
			else {
				totalBalance=currentBalance+depositedAmount;
			}
			
			obj.setBalance(totalBalance);
			//customerHash.remove(recentlyLoggedInUser);
			customerHash.put(recentlyLoggedInUser,obj);
			Customer obj1 = customerHash.get(recentlyLoggedInUser);
			return("The Current account balance is "+obj1.getBalance());
	 }
	 
	 public String AccountWithdrawalInBank(HashMap<Integer,Customer> customerHash,int accno,Customer FromAccountObject,int amt,int currentLoginCustomer) {
		for (Map.Entry<Integer, Customer> entry : customerHash.entrySet()) {
			 Customer c1 = entry.getValue();
			 if(c1.getAccountNumber()==accno) {
				 int remAmount=0;
				 int currentKey=entry.getKey();
				 if(amt>5000) {
					 remAmount=FromAccountObject.getBalance()-amt-10;
				 }else {
					 remAmount=FromAccountObject.getBalance()-amt;
				 }
				 
				 FromAccountObject.setBalance(remAmount);
				 customerHash.put(currentLoginCustomer, FromAccountObject);
				 int currentBalance=c1.getBalance();
				 int totAmount=currentBalance+amt;
				 c1.setBalance(totAmount);
				 customerHash.put(currentKey, c1);
				 int bal = FromAccountObject.getBalance();
				 return("The Current Balance is "+bal);
			 }
		 }
		 return "The Account No is Invalid";
	 }
}
