package modelo;

import java.util.Date;

public class Account {
	
	private Date generationDate;
	
	private String id;
	
	private Date dueDate;
	
	private String description;	


	private double accountValue;
	
	private double iva;
	
	
	
	private String paymentType;
	
	private boolean paid;

	public Account(Date generationDate,String description, String id, Date dueDate, double accountValue, double iva, double interest,
			String paymentType, boolean paid) {
		super();
		this.generationDate = generationDate;
		this.id = id;
		this.description=description;
		this.dueDate = dueDate;
		this.accountValue = accountValue;
		this.iva = iva;		
		this.paymentType = paymentType;
		this.paid = paid;
	}

	public Date getGenerationDate() {
		return generationDate;
	}

	public void setGenerationDate(Date generationDate) {
		this.generationDate = generationDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public double getAccountValue() {
		return accountValue;
	}

	public void setAccountValue(double accountValue) {
		this.accountValue = accountValue;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}



	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
