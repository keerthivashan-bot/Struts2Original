package org.keerthi.javabrains.action;

import java.io.Serializable;

public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
	static int no=0;
	static int accno=2345;
	private String name;
	private String password;
	private  int balance=10000;
	private int accountNumber;
	private int customerId;
	
	Customer(String name,String password,int balance){
		accno++;
		no++;
		this.name=name;
		this.password = password;
		this.balance=balance;
		accountNumber = accno;
		customerId = no;	
	}

	public static int getNo() {
		return no;
	}

	public static void setNo(int no) {
		Customer.no = no;
	}

	public static int getAccno() {
		return accno;
	}

	public static void setAccno(int accno) {
		Customer.accno = accno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public int getBalance() {
		return balance;
	}

	public  void setBalance(int balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
