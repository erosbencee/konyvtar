package hu.rft.konyvtar;


import java.io.IOException;

import hu.rft.controller.HomeController;
import hu.rft.controller.RegistrationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * A program main f�ggv�ny�t tartalmaz� oszt�ly.
 * 
 * @author bedonorbert
 *
 */

public class Main extends Application {

	private static Stage primaryStage;
	private AnchorPane rootLayout;
	/**
	 * A start met�dus elk�sz�ti az indul�skor megjelen�tend� ablakot.
	 * 
	 */
	
	@Override
	public void start(Stage primaryStage) {

		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("RFT K�nyvt�r");
		
		Main.primaryStage.getIcons().add(new Image("/images/icon.png"));
		
		initLogin();
	}
	
	/**
	 * Elk�sz�ti a bejelentkez�shez sz�ks�ges fel�letet.
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

	/**
	 * Visszaadja az ablakhoz tartoz� Stage objektumot.
	 * 
	 * @return az ablakhoz tartoz� Stage objektum.
	 */
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Be�ll�tja az ablakhoz tartoz� Stage objektumot.
	 * 
	 * @param primaryStage az ablakhoz be�ll�tani k�v�nt Stage objektum.
	 */
	
	public void setPrimaryStage(Stage primaryStage) {
		Main.primaryStage = primaryStage;
	}

	/**
	 * A program main f�ggv�nye.
	 * 
	 * @param args az estleges parancssori argomentumok.
	 */
	
	public static void main(String[] args) {
		
		launch(args);
	}
}

