package hu.rft.konyvtar;


import java.io.IOException;

import hu.rft.controller.HomeController;
import hu.rft.controller.RegistrationController;
import hu.rft.controller.UserMainPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A program main függvényét tartalmazó osztály.
 * 
 * @author bedonorbert
 *
 */

public class Main extends Application {

	private static Stage primaryStage;
	private AnchorPane rootLayout;
	/**
	 * A start metódus elkészíti az induláskor megjelenítendő ablakot.
	 * 
	 */
	
	@Override
	public void start(Stage primaryStage) {

		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("RFT Könyvtár");
		
		Main.primaryStage.getIcons().add(new Image("/images/icon.png"));
		
		initLogin();
	}
	
	/**
	 * Elkészíti a bejelentkezéshez sz�kséges felületet.
	 * 
	 */

	public void initLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/FXMLs/Login.fxml"));
			rootLayout = (AnchorPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			HomeController controller = loader.getController();
            controller.setMainApp(this);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void registration() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/FXMLs/Registration.fxml"));
			rootLayout = (AnchorPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			RegistrationController controller = loader.getController();
            controller.setMainApp(this);
			primaryStage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void UserMainPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/FXMLs/MainPage.fxml"));
			rootLayout = (AnchorPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			UserMainPageController controller = loader.getController();
            controller.setMainApp(this);
			primaryStage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Visszaadja az ablakhoz tartozó Stage objektumot.
	 * 
	 * @return az ablakhoz tartozó Stage objektum.
	 */
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Beállítja az ablakhoz tartozó Stage objektumot.
	 * 
	 * @param primaryStage az ablakhoz beállítani kívánt Stage objektum.
	 */
	
	public void setPrimaryStage(Stage primaryStage) {
		Main.primaryStage = primaryStage;
	}

	/**
	 * A program main függvénye.
	 * 
	 * @param args az estleges parancssori argomentumok.
	 */
	
	public static void main(String[] args) {
		
		launch(args);
	}
}

