package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable{
	
	
	private String name;
	
	private String typeDocument;
	
	private String idDocument;
	
	private String email;
	
	private String phone;
	
	private String address;
	
	private double totalToPay;
	
	private ArrayList<Account> paidAccounts;
	
	private ArrayList<Account> noPaidAccounts;

	public Client(String name, String typeDocument, String idDocument, String email, String phone, String address
			) {	
		this.name = name;
		this.typeDocument = typeDocument;
		this.idDocument = idDocument;
		this.email = email;
		this.phone = phone;
		this.address = address;		
		this.paidAccounts = new ArrayList<Account>();
		this.noPaidAccounts = new ArrayList<Account>();
		this.totalToPay = getTotalToPay();
		
	}
	
	
	public void setAccount(long id,Account account) {
		
	}
	
	public Account searchAccountNoPaid(String id) {
		Account foundClient=null;
		for (int i = 0; i < noPaidAccounts.size(); i++) {
			if(noPaidAccounts.get(i).getId().equals(id)) {
				foundClient= noPaidAccounts.get(i);
			}
		}
		return foundClient;
	}
	
	public Account searchAccountPaid(String id) {
		Account foundClient=null;
		for (int i = 0; i < paidAccounts.size(); i++) {
			if(paidAccounts.get(i).getId().equals(id)) {
				foundClient= paidAccounts.get(i);
			}
		}
		return foundClient;
	}
	
	
	public void addAccount(Account newAccoutn) {
		noPaidAccounts.add(newAccoutn);
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

	public double getTotalToPay() {
		double total=0;
		for (int i = 0; i < noPaidAccounts.size(); i++) {
			total+=noPaidAccounts.get(i).getAccountValue()+noPaidAccounts.get(i).getIva();
		}
		this.totalToPay=total;
		return totalToPay;
	}

	public void setTotalToPay(double totalToPay) {
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
