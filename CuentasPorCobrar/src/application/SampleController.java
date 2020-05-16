package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Account;
import modelo.AccountsReceivableApp;
import modelo.Client;

public class SampleController {
	
	AccountsReceivableApp ara = new AccountsReceivableApp();
	
	@FXML
	private GridPane allAccounts;
	
	@FXML
	private GridPane outdatedAccounts;
	
	public void initialize() {
		ara.load();
		showNoPaidAccounts();
		showOutdatedAccounts();
		
	}
	
	public void payAccount() {
		Label lclientId = new Label("Enter the Client ID: ");
		Label laccountId = new Label("Enter the Account ID: ");
		Label lvalue = new Label("Enter the amount to pay: ");
		
		TextField clientId = new TextField();
		clientId.setMaxWidth(200);
		
		TextField accountId = new TextField();
		accountId.setMaxWidth(200);
		
		TextField value = new TextField();
		value.setMaxWidth(200);
		
		Stage newWindow = new Stage();
		Button accept = new Button();
		accept.setText("Accept");
		accept.setOnAction(event -> {

			try {
				double valuePaid = Double.parseDouble(value.getText());
				ara.paidAccount(clientId.getText(), accountId.getText(), valuePaid);
				showNoPaidAccounts();
				showOutdatedAccounts();
				newWindow.close();
			} catch (Exception e2) {
				System.out.println("Failed to pay");
			}
		});
		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(80));

		StackPane secondaryLayout = new StackPane();
		
		Scene secondScene = new Scene(secondaryLayout, 600, 300);

		Button cancel = new Button();
		cancel.setText("Cancel");
		cancel.setOnAction(event -> {
			newWindow.close();
		});

		secondaryLayout.getChildren().add(grid);
		
		grid.add(lclientId, 0, 0);
		grid.add(clientId, 1, 0);
		grid.add(laccountId, 0, 1);
		grid.add(accountId, 1, 1);
		grid.add(lvalue, 0, 2);
		grid.add(value, 1, 2);
				
		grid.add(accept, 0, 3);
		grid.add(cancel, 1, 3);
		
		newWindow.setTitle("Pay Account");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	//Done
	public void createAccount() {
		Label lgenerationDate = new Label("Generation Date");

		Label lid = new Label("ID");
		
		Label lclientId = new Label("Client ID");

		Label ldueDate = new Label("Due Date(dd/MM/yyyy)");
		
		Label laccountValue = new Label("Account Value");
		
		Label liva = new Label("IVA");
		
		Label linterest = new Label("Interest");
		
		Label lpaymentType = new Label("Payment Type");
		
		Label ldescription = new Label("Description");
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		Label generationDate = new Label(formatter.format(date));
		generationDate.setMaxWidth(200);

		TextField id = new TextField();
		id.setMaxWidth(200);
		
		TextField clientId = new TextField();
		clientId.setMaxWidth(200);

		DatePicker dueDate = new DatePicker();
		dueDate.setMaxWidth(200);
		

		TextField accountValue = new TextField();
		accountValue.setMaxWidth(200);
		
		
		TextField iva = new TextField();
		iva.setMaxWidth(200);
		
		
		TextField interest = new TextField();
		interest.setMaxWidth(200);
		
		
		TextField paymentType = new TextField();
		paymentType.setMaxWidth(200);
		
		TextArea description = new TextArea();
		description.setMaxWidth(200);
		
		
		Stage newWindow = new Stage();
		Button accept = new Button();
		accept.setText("Accept");
		accept.setOnAction(event -> {

			try {
				System.out.println(dueDate.getValue()+"");
				double av = Double.parseDouble(accountValue.getText());
				double iv = Double.parseDouble(iva.getText());
				double inte = Double.parseDouble(interest.getText());
				LocalDate ld = dueDate.getValue();
				String stringDate = ld+"";
				String[] arraydate = stringDate.split("-");
				Date auxDate = new Date();
				int day = Integer.parseInt(arraydate[2]);
				int month = Integer.parseInt(arraydate[1]);
				int year = Integer.parseInt(arraydate[0]);
				auxDate.setDate(day);
				auxDate.setMonth(month-1);;
				auxDate.setYear(year-1900);;
				
				
				ara.createAccount(clientId.getText(), date, description.getText(), id.getText(), auxDate, av, iv, inte, paymentType.getText());
				showNoPaidAccounts();
				showOutdatedAccounts();
				newWindow.close();
			} catch (Exception e2) {
				System.out.println("Failed to Create");
			}
		});
		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(80));

		StackPane secondaryLayout = new StackPane();
		
		Scene secondScene = new Scene(secondaryLayout, 600, 600);

		Button cancel = new Button();
		cancel.setText("Cancel");
		cancel.setOnAction(event -> {
			newWindow.close();
		});

		secondaryLayout.getChildren().add(grid);
		grid.add(lgenerationDate, 0, 0);
		grid.add(generationDate, 1, 0);
		grid.add(lid, 0, 1);
		grid.add(id, 1, 1);
		grid.add(lclientId, 0, 2);
		grid.add(clientId, 1, 2);
		grid.add(ldueDate, 0, 3);
		grid.add(dueDate, 1, 3);
		grid.add(laccountValue, 0, 4);
		grid.add(accountValue, 1, 4);
		grid.add(liva, 0, 5);
		grid.add(iva, 1, 5);
		grid.add(linterest, 0, 6);
		grid.add(interest, 1, 6);
		grid.add(lpaymentType, 0, 7);
		grid.add(paymentType, 1, 7);
		grid.add(ldescription, 0, 8);
		grid.add(description, 1, 8);
		grid.add(accept, 0, 9);
		grid.add(cancel, 1, 9);
		
		newWindow.setTitle("Create Account");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	//Done
	public void searchAccount() {
		GridPane grid = new GridPane();
		Label msg = new Label("Enter the ID of the account:");
		
		TextField searchField = new TextField();
		searchField.setMaxWidth(500);
		searchField.setPromptText("Enter an ID");
		
		Button search = new Button("Search");
		
		Stage newWindow = new Stage();
		
		Button cancel = new Button();
		cancel.setText("Cancel");
		cancel.setOnAction(event -> {
			newWindow.close();
		});
		
		search.setText("Accept");
		search.setOnAction(event -> {

			try {
				String s = "Generation Date: " + 
							ara.getAccountsNoPaid().get(ara.searchAccount(searchField.getText())).getGenerationDate() +
							"\nID: " + 
							ara.getAccountsNoPaid().get(ara.searchAccount(searchField.getText())).getDueDate() +
							"\nAccount Value: " +
							ara.getAccountsNoPaid().get(ara.searchAccount(searchField.getText())).getAccountValue() +
							"\nIVA: " + 
							ara.getAccountsNoPaid().get(ara.searchAccount(searchField.getText())).getIva() +
							"\nPayment Type: " + 
							ara.getAccountsNoPaid().get(ara.searchAccount(searchField.getText())).getPaymentType() +
							"\nDescription: " + ara.getAccountsNoPaid().get(ara.searchAccount(searchField.getText())).getDescription();
				Label l = new Label(s);
				grid.getChildren().clear();
				grid.add(l, 0, 2);
				grid.add(cancel, 0, 3);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null,"The account was not found");
				System.out.println("Failed to search");
			}
		});
		
		
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(50));

		StackPane secondaryLayout = new StackPane();
		
		Scene secondScene = new Scene(secondaryLayout, 500, 300);

		

		secondaryLayout.getChildren().add(grid);
		grid.add(msg, 0, 0);
		grid.add(searchField, 0, 1);
		grid.add(search, 1, 1);
		
		newWindow.setTitle("Search Account");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	//Done
	public void showOutdatedAccounts() {
		outdatedAccounts.getChildren().clear();
		GridPane gp = new GridPane();
		
		
		ArrayList<Account> a = ara.getOutDateAccounts();
		for(int i = 0; i < a.size(); i++) {
			Label l = new Label("Generation Date: " + a.get(i).getGenerationDate() +
								"\nID: " + a.get(i).getId() +
								"\nDue Date: " + a.get(i).getDueDate() +
								"\nAccount Value: " + a.get(i).getAccountValue() +
								"\nIVA: " + a.get(i).getIva() +
								"\nPayment Type: " + a.get(i).getPaymentType() +
								"\nDescription: " + a.get(i).getDescription() +
								"\n-----------------------------------------------------"+"\n "+"\n ");
			gp.add(l, 0, (i));
		}
		outdatedAccounts.add(gp, 0, 0);
	}
	
	//Done
	public void showNoPaidAccounts() {
		allAccounts.getChildren().clear();
		
		GridPane gp = new GridPane();

		ArrayList<Account> a = ara.getAccountsNoPaid();
		for(int i = 0; i < a.size(); i++) {
			Label l = new Label("Generation Date: " + a.get(i).getGenerationDate() +
								"\nID: " + a.get(i).getId() +
								"\nDue Date: " + a.get(i).getDueDate() +
								"\nAccount Value: " + a.get(i).getAccountValue() +
								"\nIVA: " + a.get(i).getIva() +
								"\nPayment Type: " + a.get(i).getPaymentType() +
								"\nDescription: " + a.get(i).getDescription() +
								"\n-----------------------------------------------------");
			gp.add(l, 0, i);
			
		}
		allAccounts.add(gp, 0, 0);
	}

	
	public void deleteAccount() {
		Label lclientId = new Label("Enter the Client ID: ");
		Label laccountId = new Label("Enter the Account ID: ");
		
		TextField clientId = new TextField();
		clientId.setMaxWidth(200);
		
		TextField accountId = new TextField();
		accountId.setMaxWidth(200);
		
		
		Stage newWindow = new Stage();
		Button accept = new Button();
		accept.setText("Accept");
		accept.setOnAction(event -> {

			try {
				int index = -1;
				for(int i = 0; i < ara.getAccountsNoPaid().size(); i++) {
					if(ara.getAccountsNoPaid().get(i).getId().equals(accountId.getText())) {
						index = i;
						i = ara.getAccountsNoPaid().size();
					}
				}
				ara.deleteAccount(index , clientId.getText());
				showNoPaidAccounts();
				showOutdatedAccounts();
				JOptionPane.showMessageDialog(null, "The account was paid successfully");
				newWindow.close();
			} catch (Exception e2) {
				System.out.println("Failed to pay");
				
			}
		});
		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(80));

		StackPane secondaryLayout = new StackPane();
		
		Scene secondScene = new Scene(secondaryLayout, 600, 300);

		Button cancel = new Button();
		cancel.setText("Cancel");
		cancel.setOnAction(event -> {
			newWindow.close();
		});

		secondaryLayout.getChildren().add(grid);
		
		grid.add(lclientId, 0, 0);
		grid.add(clientId, 1, 0);
		grid.add(laccountId, 0, 1);
		grid.add(accountId, 1, 1);
				
		grid.add(accept, 0, 2);
		grid.add(cancel, 1, 2);
		
		newWindow.setTitle("Delete Account");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	
	//Done
	public void registerClient() {
		Label lname = new Label("Name");

		Label ltypeDocument = new Label("Type of document");

		Label lidDocument = new Label("ID Document");
		
		Label lemail = new Label("Email");
		
		Label lphone = new Label("Phone number");
		
		Label ladress = new Label("Adress");
		
		Label ltotalToPay = new Label("Total to pay");

		TextField name = new TextField();
		name.setMaxWidth(200);

		TextField typeDocument = new TextField();
		typeDocument.setMaxWidth(200);

		TextField idDocument = new TextField();
		idDocument.setMaxWidth(200);
		
		TextField email = new TextField();
		email.setMaxWidth(200);
		
		TextField phone = new TextField();
		phone.setMaxWidth(200);
		
		TextField adress = new TextField();
		adress.setMaxWidth(200);
		
		TextField totalToPay = new TextField();
		totalToPay.setMaxWidth(200);
		
		
		Stage newWindow = new Stage();
		Button accept = new Button();
		accept.setText("Accept");
		accept.setOnAction(event -> {

			try {
				double totalPay = Double.parseDouble(totalToPay.getText());
				ara.registerClient(name.getText(), typeDocument.getText(), idDocument.getText(), email.getText()
				, phone.getText(), adress.getText(), totalPay);
				newWindow.close();
			} catch (Exception e2) {
				System.out.println("Failed to register");
			}
		});
		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));

		StackPane secondaryLayout = new StackPane();
		
		Scene secondScene = new Scene(secondaryLayout, 600, 600);

		Button cancel = new Button();
		cancel.setText("Cancel");
		cancel.setOnAction(event -> {
			newWindow.close();
		});

		secondaryLayout.getChildren().add(grid);
		grid.add(lname, 0, 0);
		grid.add(name, 1, 0);
		grid.add(ltypeDocument, 0, 2);
		grid.add(typeDocument, 1, 2);
		grid.add(lidDocument, 0, 3);
		grid.add(idDocument, 1, 3);
		grid.add(lemail, 0, 4);
		grid.add(email, 1, 4);
		grid.add(lphone, 0, 5);
		grid.add(phone, 1, 5);
		grid.add(ladress, 0, 6);
		grid.add(adress, 1, 6);
		grid.add(ltotalToPay, 0, 7);
		grid.add(totalToPay, 1, 7);
		grid.add(accept, 0, 8);
		grid.add(cancel, 1, 8);
		
		newWindow.setTitle("Register Client");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	//Done
	public void updateClient() {
		Label lname = new Label("Name");

		Label ltypeDocument = new Label("Type of document");

		Label lidDocument = new Label("ID Document");
		
		Label lemail = new Label("Email");
		
		Label lphone = new Label("Phone number");
		
		Label ladress = new Label("Adress");

		TextField name = new TextField();
		name.setMaxWidth(200);

		TextField typeDocument = new TextField();
		typeDocument.setMaxWidth(200);

		TextField idDocument = new TextField();
		idDocument.setMaxWidth(200);
		
		TextField email = new TextField();
		email.setMaxWidth(200);
		
		TextField phone = new TextField();
		phone.setMaxWidth(200);
		
		TextField adress = new TextField();
		adress.setMaxWidth(200);
		
		
		
		Stage newWindow = new Stage();
		Button accept = new Button();
		accept.setText("Accept");
		accept.setOnAction(event -> {

			try {
			
				Client c = new Client(name.getText(), typeDocument.getText(), idDocument.getText(), email.getText(), phone.getText(), adress.getText());
				ara.updateClient(idDocument.getText(), c);
				newWindow.close();
			} catch (Exception e2) {
				System.out.println("Failed to update");
			}
		});
		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(120));

		StackPane secondaryLayout = new StackPane();
		
		Scene secondScene = new Scene(secondaryLayout, 600, 600);

		Button cancel = new Button();
		cancel.setText("Cancel");
		cancel.setOnAction(event -> {
			newWindow.close();
		});

		secondaryLayout.getChildren().add(grid);
		grid.add(lname, 0, 0);
		grid.add(name, 1, 0);
		grid.add(ltypeDocument, 0, 2);
		grid.add(typeDocument, 1, 2);
		grid.add(lidDocument, 0, 3);
		grid.add(idDocument, 1, 3);
		grid.add(lemail, 0, 4);
		grid.add(email, 1, 4);
		grid.add(lphone, 0, 5);
		grid.add(phone, 1, 5);
		grid.add(ladress, 0, 6);
		grid.add(adress, 1, 6);
		
		grid.add(accept, 0, 7);
		grid.add(cancel, 1, 7);
		
		newWindow.setTitle("Update Client");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	//Done
	public void searchClient() {
		Label msg = new Label("Enter the ID of the user:");
		GridPane grid = new GridPane();
		
		TextField searchField = new TextField();
		searchField.setMaxWidth(500);
		searchField.setPromptText("Enter an ID");
		
		
		
		Stage newWindow = new Stage();
		
		Button cancel = new Button();
		cancel.setText("Accept");
		cancel.setOnAction(event -> {
			newWindow.close();
		});
		
		Button accept = new Button();
		accept.setText("Search");
		accept.setOnAction(event -> {

			try {
				Client c = ara.searchClient(searchField.getText());
				String s = "Name: " + c.getName() +
							"\nDocument type: " + c.getTypeDocument() +
							"\nID: " + c.getIdDocument() +
							"\nE-mail: " + c.getEmail() +
							"\nPhone: " + c.getPhone() +
							"\nAdress: " + c.getAddress() +
							"\nTotal to Pay: " + c.getTotalToPay();
				Label l = new Label(s);
				grid.getChildren().clear();
				grid.add(l, 0, 2);
				grid.add(cancel, 0, 3);
				
			} catch (Exception e2) {
				System.out.println("Failed to search");
			}
		});
		
		
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(50));

		StackPane secondaryLayout = new StackPane();
		
		Scene secondScene = new Scene(secondaryLayout, 500, 300);


		secondaryLayout.getChildren().add(grid);
		grid.add(msg, 0, 0);
		grid.add(searchField, 0, 1);
		grid.add(accept, 1, 1);
		
		newWindow.setTitle("Search Client");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	//Done
	public void showCreators() {
		
		Label l = new Label("Created by:"
				+ "\nJean Carlos Ortiz"
				+ "\nSamuel Satizabal"
				+ "\nJuan David Ossa");
		
		l.setFont(new Font(24));
		
		Button accept = new Button("Accept");
		
		Stage newWindow = new Stage();
		
		accept.setText("Accept");
		accept.setOnAction(event -> {

			try {
				
				newWindow.close();
			} catch (Exception e2) {
				System.out.println("Failed to register");
			}
		});
		
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(50));

		StackPane secondaryLayout = new StackPane();
		
		Scene secondScene = new Scene(secondaryLayout, 300, 300);

		Button cancel = new Button();
		cancel.setText("Cancel");
		cancel.setOnAction(event -> {
			newWindow.close();
		});

		secondaryLayout.getChildren().add(grid);
		grid.add(l, 0, 0);
		grid.add(accept, 0, 1);
		
		newWindow.setTitle("CREATORS!!!");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	public void exit() {
		ara.save();
		System.exit(0);
	}
}
