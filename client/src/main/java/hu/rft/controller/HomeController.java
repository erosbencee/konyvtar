package hu.rft.controller;
import hu.rft.konyvtar.Main;
import hu.rft.db.DBConnector;




import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.text.StyledEditorKit.BoldAction;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class HomeController {
	
        DBConnector dbc;
        boolean startUp;
        
        Alert errorAlert = new Alert(AlertType.ERROR);
        Alert generalAlert = new Alert(AlertType.INFORMATION);
    
        @FXML
	private Button loginBtn;
	@FXML
	private Button reigstrationBtn;
	@FXML 
	private TextField login;
        @FXML
        private PasswordField jelszo;
        
	private Main main;
	
	@FXML
	private void initialize() {
            
            
            
            
	}
	

  public void setMainApp(Main main, boolean status, DBConnector dbcon) {
      this.main = main;
      
      startUp = status;
      
      if(!startUp) {
          
          dbc = dbcon;
          return;
      }
      
      try {
                
                System.out.println("startUp is: " + Boolean.toString(startUp));
                
                if(startUp) {
                    
                    System.out.println("In new DBC");
                    dbc = new DBConnector();
                    
                    generalAlert.setTitle("Sikeres kapcsolódás");
                    generalAlert.setHeaderText("Sikeres kapcsolódás az adatbázishoz!");
                    generalAlert.showAndWait();
                    
                }
                
            } catch(SQLException ex) {
                
                errorAlert.setTitle("SQL Exception");
                errorAlert.setHeaderText("Nem lehet csatlakozni az adatbázishoz");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
                System.exit(-1);
                
            } catch(ClassNotFoundException ex) {
                
                errorAlert.setTitle("Class Not Found Exception");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
                System.exit(-1);
            }
  }


  @FXML
 private void login(){
	  System.exit(0);
  }
  
  @FXML
 private void OpenRegistration(){
	  main.registration(dbc);
  }

  @FXML
 private void Login(){
     
          if(!login.getText().isEmpty() && !jelszo.getText().isEmpty()) {
              
              try {
                  
                  boolean exists = dbc.checkLogin(login.getText(), jelszo.getText());
                  
                  if(exists) {
                      
                      main.initRootLayout(dbc);
                      main.UserMainPage(dbc);
                      
                  } else {
                      
                      errorAlert.setTitle("Hiba");
                      errorAlert.setHeaderText("A megadott felhasználónév-jelszó páros nem létezik!");
                      errorAlert.showAndWait();
                  }
                  
              } catch(SQLException ex) {
                  
                errorAlert.setTitle("SQL Exception");
                errorAlert.setHeaderText("Hiba történt a kérés feldolgozása során:");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
              }
          }
		 
  }



  public boolean isValidEmailAddress (String Email) {
	  
      String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
      java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
      java.util.regex.Matcher m = p.matcher(Email);
      return m.matches();
}
  
}
