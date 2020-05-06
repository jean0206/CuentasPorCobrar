package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class AccountsReceivableApp {
	
	private Hashtable<String,Client> clients;
	
	
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
		chosenClient.getPaidAccounts().add(newAccount);
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
	
	public static void main(String[] args) {
		AccountsReceivableApp c1= new AccountsReceivableApp();
		c1.registerClient("nam", "typeDocument", "123", "email", "phone", "address", 230);
		Client cli= c1.searchClient("123");
		System.out.println(cli.getTotalToPay());
		Client c2= new Client("nam2", "typeDocument", "124", "email", "phone", "address");
		c1.updateClient(c2.getIdDocument(),c2);
		//System.out.println(cli.getName());
	}

}
