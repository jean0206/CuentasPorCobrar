package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane) FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root, 1000, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Accounts Receivable");
//			primaryStage.getIcons().add(new Image("file:data/logo.jpg"));
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}