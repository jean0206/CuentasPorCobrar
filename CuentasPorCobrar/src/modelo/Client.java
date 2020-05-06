package modelo;

import java.util.ArrayList;

public class Client {
	
	
	private String name;
	
	private String typeDocument;
	
	private String idDocument;
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private String totalToPay;
	
	private ArrayList<Account> paidAccounts;
	
	private ArrayList<Account> noPaidAccounts;

	public Client(String name, String typeDocument, String idDocument, String email, String phone, String address,
			String totalToPay) {	
		this.name = name;
		this.typeDocument = typeDocument;
		this.idDocument = idDocument;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.totalToPay = totalToPay;
		this.paidAccounts = new ArrayList<Account>();
		this.noPaidAccounts = new ArrayList<Account>();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeDocument() {
		return typeDocument;
	}

	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public String getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(String idDocument) {
		this.idDocument = idDocument;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTotalToPay() {
		return totalToPay;
	}

	public void setTotalToPay(String totalToPay) {
		this.totalToPay = totalToPay;
	}

	public ArrayList<Account> getPaidAccounts() {
		return paidAccounts;
	}

	public void setPaidAccounts(ArrayList<Account> paidAccounts) {
		this.paidAccounts = paidAccounts;
	}

	public ArrayList<Account> getNoPaidAccounts() {
		return noPaidAccounts;
	}

	public void setNoPaidAccounts(ArrayList<Account> noPaidAccounts) {
		this.noPaidAccounts = noPaidAccounts;
	}
	
	
	
	
	
	
	

}
