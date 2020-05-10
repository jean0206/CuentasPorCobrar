package modelo;

import java.io.File;

import java.io.*; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class AccountsReceivableApp {
	
	private Hashtable<String,Client> clients;
	


	public static String SERIALHASH = "./hashRecords/hash.dat"; 
	
	public AccountsReceivableApp() {
		
		clients= new Hashtable<String,Client>();
		
	}
	
	/*RegisterClient()
	 * Registra el cliente en una HashTable
	 * su key, es su cedula.
	 */
	
	public void registerClient(String name, String typeDocument, String idDocument, String email, String phone, String address,
			double totalToPay) {
		
		Client newClient= new Client(name,typeDocument,idDocument,email,phone,address);
		if(clients.get(idDocument)==null) {
			clients.put(newClient.getIdDocument(), newClient);
			JOptionPane.showMessageDialog(null,"Se ha creado con exito");
		}else {
			JOptionPane.showMessageDialog(null,"Usuario ya existe");
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
		
		ArrayList<Account> outdatesAccounts=new ArrayList<Account>();
		 	
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
			JOptionPane.showMessageDialog(null, "Cliente no ha sido encontrado");
		}
		return foundClient;
	}
	
	/*createAccount()
	 * crea una cuenta con un cliente, con el valor a pagar
	 * la fecha de inicio y la fecha de vencimiento.
	 * busca primero al cliente al cual se le va a asignar la cuenta
	 * luego agrega la cuenta al arraylist de cuentas por pagar del cliente
	 */
	
	public void createAccount(String clientId,Date generationDate, long id, Date dueDate, double accountValue, double iva, double interest,
			String paymentType, boolean paid) {
		Client chosenClient= searchClient(clientId);
		Account newAccount= new Account(generationDate, id, dueDate, accountValue, iva, interest, paymentType, paid);
		chosenClient.getNoPaidAccounts().add(newAccount);
		clients.put(clientId, chosenClient);
		
	}
	
	/*updateClient()
	 * actualiza la informacion del cliente
	 * se pasa como parametro el id y un objeto cliente
	 */
	
	public void updateClient(String id, Client client) {
		if(clients.get(id)!=null) {
			clients.put(id, client);	
			JOptionPane.showMessageDialog(null,"Se actulizó la infromación del cliente");
		}else {
			JOptionPane.showMessageDialog(null,"No se ha encontrado el cliente");
		}
	}	
	
	//<----------------------------------------SERIALIZACION
	public void saveFidelization() {
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
	public void loadFidelization() {
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
	public int searchAccount(Client client, long idCuenta) {
		int counter=0;
		for (int i = 0; i < client.getNoPaidAccounts().size(); i++) {
			if(client.getNoPaidAccounts().get(i).getId()==(idCuenta)) {
				counter=i;
			}
		}
		return counter;
	}
	
	public void deleteAccount(int indexAccount,ArrayList<Account> accounts) {
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
	
	public void paidAccount(String clientId, long  idAccount,double valuePaid) {
		Client client= searchClient(clientId);
		if(client!=null) {
			int indexAccount=searchAccount(client, idAccount);
			Account account= client.getNoPaidAccounts().get(indexAccount);
			double paidValue=account.getAccountValue()+account.getIva()-valuePaid;
			if(paidValue<0) {
				JOptionPane.showMessageDialog(null, "El valor sobrepasa el saldo de la cuenta");
				System.out.println("1+1");
			}
			else if(paidValue==0){
				account.setAccountValue(paidValue);
				account.setPaid(true);
				client.getNoPaidAccounts().set(indexAccount, account);
				client.getPaidAccounts().add(account);
				updateClient(clientId, client);
				JOptionPane.showMessageDialog(null, "Se ha saldado la cuenta");				
			}
			else{
				account.setAccountValue(paidValue);
				client.getNoPaidAccounts().set(indexAccount, account);
				updateClient(clientId, client);
				JOptionPane.showMessageDialog(null, "Se ha modificado el saldo de la cuenta a: $"+paidValue);				
			}
		}
		
	}
	
	public void createDocument(String nombre,long idAccount) {		
		
		try {
			Document document= new Document();
			FileOutputStream filePdf= new FileOutputStream("C:\\Users\\jeank\\git\\CuentasPorCobrar1\\CuentasPorCobrar\\bin\\personas.pdf");
			PdfWriter.getInstance(document, filePdf);
			document.open();
			Paragraph title= new Paragraph("Recibo de: "+nombre);
			document.add(title);
			PdfPTable table= new PdfPTable(2);
			table.addCell("id");
			table.addCell("nombre");
			table.addCell("jean");
			table.addCell("jana");
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

	
	

	public static void main(String[] args) throws ParseException {
		AccountsReceivableApp c1= new AccountsReceivableApp();
		c1.registerClient("nam", "typeDocument", "123", "email", "phone", "address", 230);
		Client cli= c1.searchClient("123");
		Date date1=new SimpleDateFormat("dd/M/yyyy").parse("10/05/2020");
		Date date2=new SimpleDateFormat("dd/M/yyyy").parse("10/06/2020");		
		c1.createAccount("123",date1, 1, date2, 10000, 0.19, 0.01, "sdfsd", false);
		System.out.println(cli.getTotalToPay());
		c1.paidAccount("123",1,5000.19);
		cli=c1.searchClient("123");
		System.out.println(c1.getClients().get("123").getTotalToPay());
		c1.createDocument("juan",1);
		
		/*Client c2= new Client("nam2", "typeDocument", "124", "email", "phone", "address");
		c1.updateClient(c2.getIdDocument(),c2);*/
		//System.out.println(cli.getName());
	}

}
