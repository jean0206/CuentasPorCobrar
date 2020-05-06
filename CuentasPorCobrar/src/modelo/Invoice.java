package modelo;

public class Invoice {

	private String clientName;
	
	private long accountId;
	
	private double amountPaid;

	public Invoice(String clientName, long accountId, double amountPaid) {
		super();
		this.clientName = clientName;
		this.accountId = accountId;
		this.amountPaid = amountPaid;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	
}
