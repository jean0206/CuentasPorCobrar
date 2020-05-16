package modelo;

import java.io.File;

import java.io.*; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class AccountsReceivableApp implements Serializable{
	
	private Hashtable<String,Client> clients;
	


	public static String SERIALHASH = "./hashRecords/hash.dat"; 
	
	public AccountsReceivableApp() {
		
		clients= new Hashtable<String,Client>();
		
	}
	
	/*RegisterClient()
	 * Registra el cliente en una HashTable
	 * su key, es su cedula.
	 */
	
	public void registerClient(String name, String typeDocument, String idDocument, String email, String phone, String address) {
		
		Client newClient= new Client(name,typeDocument,idDocument,email,phone,address);
		if(clients.get(idDocument)==null) {
			clients.put(newClient.getIdDocument(), newClient);
			JOptionPane.showMessageDialog(null,"Client created successfylly");
		}else {
			JOptionPane.showMessageDialog(null,"The client already exists");
		}
	}
	

	
	/*getAccountsNoPaid()
	 *busca por cada cliente las cuentas no pagadas
	 * las almacena y retorna en un arrayList
	 */
	
	public ArrayList<Account> getAccountsNoPaid() {
		
		ArrayList<Account> accountsNoPaid=new ArrayList<Account>();		
		Enumeration e = clients.elements();
		Client client;
		while( e.hasMoreElements() ){
		  client = (Client) e.nextElement();
		  for (int i = 0; i < client.getNoPaidAccounts().size(); i++) {
			  accountsNoPaid.add(client.getNoPaidAccounts().get(i));
		}
		}
		
		return accountsNoPaid;
	}
	
	public ArrayList<Account> getOutDateAccounts() {
		Date dateNow= new Date();
		ArrayList<Account> outdatesAccounts=new ArrayList<Account>();
		Enumeration e = clients.elements();
		Client client;
		while( e.hasMoreElements() ){
		  client = (Client) e.nextElement();
		  for (int i = 0; i < client.getNoPaidAccounts().size(); i++) {
			  Account temporalAccount=client.getNoPaidAccounts().get(i);
			  if(temporalAccount.isPaid()!=true) {
				  long diff=(temporalAccount.getDueDate().getTime()-dateNow.getTime());
				  int days=(int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				  if(days<0) {
					  outdatesAccounts.add(temporalAccount);
				  }
			  }
		}
		}	
		return outdatesAccounts;
	}
	
	
	/*SearchClient()
	 * Busca a un cliente en la HashTable
	 * el parametro de busqueda es la cedula
	 * retorna null si no encuentra al cliente
	 */
	
	public Client searchClient(String id) {
		Client foundClient;
		foundClient=clients.get(id);
		if(foundClient==null) {
			JOptionPane.showMessageDialog(null, "The client was not found");
		}
		return foundClient;
	}
	
	/*createAccount()
	 * crea una cuenta con un cliente, con el valor a pagar
	 * la fecha de inicio y la fecha de vencimiento.
	 * busca primero al cliente al cual se le va a asignar la cuenta
	 * luego agrega la cuenta al arraylist de cuentas por pagar del cliente
	 */
	
	public void createAccount(String clientId,Date generationDate,String description, String id, Date dueDate, double accountValue, double iva,
			String paymentType) {
		Client chosenClient= searchClient(clientId);
		Account newAccount= new Account(generationDate,description, id, dueDate, accountValue, iva, paymentType);
		chosenClient.getNoPaidAccounts().add(newAccount);
		clients.put(clientId, chosenClient);
		createReceivable(chosenClient, id, accountValue, description);
		
	}
	
	/*updateClient()
	 * actualiza la informacion del cliente
	 * se pasa como parametro el id y un objeto cliente
	 */
	
	public void updateClient(String id, Client client) {
		if(clients.get(id)!=null) {
			clients.put(id, client);	
			JOptionPane.showMessageDialog(null,"Client data was updated successfully");
		}else {
			JOptionPane.showMessageDialog(null,"The client was not found");
		}
	}	
	
	

	
	//<----------------------------------------SERIALIZACION
	public void save() {
		try {
			File fl = new File(SERIALHASH);
			ObjectOutputStream duct = new ObjectOutputStream(new FileOutputStream(fl));
			duct.writeObject(clients);
			duct.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Description: Permite deserializar el programa de fidelizaciones
	 */
	public void load() {
		File file = new File(SERIALHASH);
		Hashtable<String,Client> temporalHash;
		//Fidelization temporalFidelization;
		try {
			FileInputStream fi = new FileInputStream(file);
			ObjectInputStream co = new ObjectInputStream(fi);
			temporalHash = (Hashtable<String,Client>) co.readObject();
			clients = temporalHash; 
			co.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*searchAccount()
	 * recibe como parametro un cliente y el id de la cuenta
	 * que se desea buscar, retorna la posicion de la cuenta
	 */
	public int searchAccount( String idCuenta) {
		int counter=-1;
		for (int i = 0; i < getAccountsNoPaid().size(); i++) {
			if(getAccountsNoPaid().get(i).getId().equals(idCuenta)) {
				counter=i;
			}
		}
		return counter;
	}
	
	public void deleteAccount(int indexAccount, String clientId) {
		ArrayList<Account> accounts = searchClient(clientId).getNoPaidAccounts();
		accounts.remove(indexAccount);
		for (int i = indexAccount; i < accounts.size(); i++) {
			if((i+1)<accounts.size()) {
				accounts.set(i,accounts.get(i+1));
			}
		}
		
	}
	
	/*paidAccount()
	 * recibe como parametro el id del cliente,
	 * el id de la cuenta y el valor a pagar
	 * si el valor a pagar es mayor que el que debe lanza error
	 * si es igual, la cuenta queda en cero
	 * si es menor resta con la cantidad que tiene
	 */
	
	public void paidAccount(String clientId, String  idAccount,double valuePaid) {
		Client client= searchClient(clientId);
		if(client!=null) {
			int indexAccount=searchAccount( idAccount);
			Account account= client.getNoPaidAccounts().get(indexAccount);
			double total=account.getAccountValue()+account.getIva();
			double paidValue=account.getAccountValue()-valuePaid;
			if(paidValue<0) {
				JOptionPane.showMessageDialog(null, "The value exceed the account balance");
				System.out.println("1+1");
			}
			else if(paidValue==0){
				account.setAccountValue(paidValue);
				account.setPaid(true);
				client.getNoPaidAccounts().set(indexAccount, account);
				client.getPaidAccounts().add(account);
				updateClient(clientId, client);
				JOptionPane.showMessageDialog(null, "The account was payed off");	
				createQuittance(client, idAccount, total ,paidValue, valuePaid, account.getDescription());
			}
			else{
				
				account.setAccountValue(paidValue);
				client.getNoPaidAccounts().set(indexAccount, account);
				updateClient(clientId, client);
				JOptionPane.showMessageDialog(null, "The account value was modified to: $"+paidValue);
				createQuittance(client, idAccount, total ,paidValue, valuePaid, account.getDescription());
			}
		}
		
		
	}
	
	/*createQuittance()
	 * El metodo crea la factura cuando un cliente paga
	 * Los valores son el cliente, el total de la cuenta
	 * el saldo, el total a pagar, la descripcion,
	 * y el valor pagado por el cliente
	 */
	
	public void createQuittance(Client client,String idAccount,double totalAccount, double balance,double paidValue,String description) {
				
		LocalDate date= LocalDate.now();
		int numQuit= (int) (Math.random()*100+1);
		//Decimal format establece la cantidad de decimales
		DecimalFormat df= new DecimalFormat("#.00");
		try {
			Document document= new Document();
			FileOutputStream filePdf= new FileOutputStream("../CuentasPorCobrar/src/Pagos/"+client.getName()+"_"+idAccount+"_"+numQuit+".pdf");
			PdfWriter.getInstance(document, filePdf);
			document.open();
			Paragraph title= new Paragraph("Client name: "+client.getName()+"\n"
					                      +"ID number:"+client.getIdDocument()+"\n"
					                      +"Account number: "+idAccount+"\n"
					                      +"\n");
			document.add(title);
			PdfPTable table= new PdfPTable(3);
			table.addCell("DATE");			
			table.addCell("DESCRIPTION");
			table.addCell("TOTAL TO PAY");
			table.addCell(date.toString());			
			table.addCell(description);
			table.addCell(""+totalAccount);
			table.addCell("");			
			table.addCell("PAYMENT");
			table.addCell(""+paidValue);
			table.addCell("");			
			table.addCell("BALANCE");
			table.addCell(""+df.format(balance));
			
			document.add(table);
			document.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*createReceivable()
	 * 
	 * 
	 */
	
	public void createReceivable(Client client,String idAccount,double totalAccount,String description) {
		LocalDate date= LocalDate.now();
		int numQuit= (int) (Math.random()*100+1);
		//Decimal format establece la cantidad de decimales
		DecimalFormat df= new DecimalFormat("#.00");
		try {
			Document document= new Document();
			FileOutputStream filePdf= new FileOutputStream("../CuentasPorCobrar/src/Cuentas/"+client.getName()+"_"+idAccount+"_"+numQuit+".pdf");
			PdfWriter.getInstance(document, filePdf);
			document.open();
			Paragraph title= new Paragraph("Client name: "+client.getName()+"\n"
					                      +"ID number: "+client.getIdDocument()+"\n"
					                      +"Account number: "+idAccount+"\n"
					                      +"\n");
			document.add(title);
			PdfPTable table= new PdfPTable(3);
			table.addCell("DATE");			
			table.addCell("DESCRIPTION");
			table.addCell("TOTAL TO PAY");
			table.addCell(date.toString());			
			table.addCell(description);
			table.addCell(""+totalAccount);			
			
			document.add(table);
			document.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	/*
	 * Metodos getters and setter de la tabla Hash
	 */
	
	public Hashtable<String, Client> getClients() {
		return clients;
	}

	public void setClients(Hashtable<String, Client> clients) {
		this.clients = clients;
	}

	
	

//	public static void main(String[] args) throws ParseException {
//		AccountsReceivableApp c1= new AccountsReceivableApp();
//		c1.registerClient("Jean Carlos Ortiz", "typeDocument", "1010096896", "email", "phone", "address", 230);
//		Client cli= c1.searchClient("1010096896");
//		Date date1=new Date(120,2,4);
//		System.out.println("Prueba"+date1.toString());
//		Date date2=new Date(120,3,4);		
		

//		c1.createAccount("1010096896",date1,"Mercancia vendida sdfdsf sdfsdfsd dfsdfsd dfsdfsdf sdfsdfsdf sdfsdfsdfsd", "1", date2, 10000, 0.19, 0.2, "sdfsd", false);
//		System.out.println(cli.getTotalToPay());
//		System.out.println("Vencidas:"+c1.getOutDateAccounts().size());
//		c1.paidAccount("1010096896","1",5000.19);		
//		System.out.println(c1.getClients().get("1010096896").getTotalToPay());
		
//		System.out.println(cli.getTotalToPay());
//		c1.createDocument(cli,"1");
		
		/*Client c2= new Client("nam2", "typeDocument", "124", "email", "phone", "address");
		c1.updateClient(c2.getIdDocument(),c2);*/
//		System.out.println(cli.getName());
//	}

}
