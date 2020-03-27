package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SampleController {
	
	public void initialize() {
		
	}
	
	public void updateAccount() {
		
	}
	
	public void createAccount() {
		Label lgenerationDate = new Label("Generation Date");

		Label lid = new Label("ID");

		Label ldueDate = new Label("Due Date(dd/MM/yyyy)");
		
		Label laccountValue = new Label("Account Value");
		
		Label liva = new Label("IVA");
		
		Label linterest = new Label("Interest");
		
		Label lpaymentType = new Label("Payment Type");
		
		Label lpaid = new Label("Paid");
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		Label generationDate = new Label(formatter.format(date));
		generationDate.setMaxWidth(200);

		TextField id = new TextField();
		id.setMaxWidth(200);

		TextField dueDate = new TextField();
		dueDate.setMaxWidth(200);

		TextField accountValue = new TextField();
		accountValue.setMaxWidth(200);
		
		TextField iva = new TextField();
		iva.setMaxWidth(200);
		
		TextField interest = new TextField();
		interest.setMaxWidth(200);
		
		TextField paymentType = new TextField();
		paymentType.setMaxWidth(200);
		
		TextField paid = new TextField();
		paid.setMaxWidth(200);
		
		Stage newWindow = new Stage();
		Button accept = new Button();
		accept.setText("Accept");
		accept.setOnAction(event -> {

			try {
				
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
		grid.add(ldueDate, 0, 2);
		grid.add(dueDate, 1, 2);
		grid.add(laccountValue, 0, 3);
		grid.add(accountValue, 1, 3);
		grid.add(liva, 0, 4);
		grid.add(iva, 1, 4);
		grid.add(linterest, 0, 5);
		grid.add(interest, 1, 5);
		grid.add(lpaymentType, 0, 6);
		grid.add(paymentType, 1, 6);
		grid.add(lpaid, 0, 7);
		grid.add(paid, 1, 7);
		grid.add(accept, 0, 8);
		grid.add(cancel, 1, 8);
		
		newWindow.setTitle("Create Account");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	public void searchAccount() {
		Label msg = new Label("Enter the ID of the account:");
		
		TextField searchField = new TextField();
		searchField.setMaxWidth(500);
		searchField.setPromptText("Enter an ID");
		
		Button search = new Button("Search");
		
		Stage newWindow = new Stage();
		Button accept = new Button();
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
		
		Scene secondScene = new Scene(secondaryLayout, 400, 100);

		Button cancel = new Button();
		cancel.setText("Cancel");
		cancel.setOnAction(event -> {
			newWindow.close();
		});

		secondaryLayout.getChildren().add(grid);
		grid.add(msg, 0, 0);
		grid.add(searchField, 0, 1);
		grid.add(search, 1, 1);
		
		newWindow.setTitle("Search Account");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	public void showOutdatedAccounts() {
		
	}
	
	public void showNoPaidAccounts() {
		
	}
	
	public void sortAccountsByDueDate() {
		
	}
	
	public void sortAccountsByType() {
		
	}
	
	public void showBills() {
		
	}
	
	public void showClients() {
		
	}
	
	public void registerClient() {
		Label lname = new Label("Name");

		Label lid = new Label("ID");

		Label ltypeDocument = new Label("Type of document");

		Label lidDocument = new Label("ID Document");
		
		Label lemail = new Label("Email");
		
		Label lphone = new Label("Phone number");
		
		Label ladress = new Label("Adress");
		
		Label ltotalToPay = new Label("Total to pay");

		TextField name = new TextField();
		name.setMaxWidth(200);

		TextField id = new TextField();
		id.setMaxWidth(200);

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
		grid.add(lid, 0, 1);
		grid.add(id, 1, 1);
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
	
	public void updateClient() {
		
	}
	
	public void searchClient() {
		Label msg = new Label("Enter the ID of the user:");
		
		TextField searchField = new TextField();
		searchField.setMaxWidth(500);
		searchField.setPromptText("Enter an ID");
		
		Button search = new Button("Search");
		
		Stage newWindow = new Stage();
		Button accept = new Button();
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
		
		Scene secondScene = new Scene(secondaryLayout, 400, 100);

		Button cancel = new Button();
		cancel.setText("Cancel");
		cancel.setOnAction(event -> {
			newWindow.close();
		});

		secondaryLayout.getChildren().add(grid);
		grid.add(msg, 0, 0);
		grid.add(searchField, 0, 1);
		grid.add(search, 1, 1);
		
		newWindow.setTitle("Search Client");
		newWindow.initModality(Modality.APPLICATION_MODAL);
		newWindow.setScene(secondScene);

		newWindow.show();
	}
	
	public void save() {
		
	}
	
	public void exit() {
		System.exit(0);
	}
}
