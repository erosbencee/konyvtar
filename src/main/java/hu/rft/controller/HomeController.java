package hu.rft.controller;
import hu.rft.konyvtar.Main;




import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class HomeController {
	@FXML
	private Button loginBtn;
	@FXML
	private Button reigstrationBtn;
	private Main main;
	
	@FXML
	private void initialize() {


	}
	

  public void setMainApp(Main main) {
      this.main = main;
        
  }


  @FXML
 private void login(){
	  System.exit(0);
  }
  
  @FXML
 private void OpenRegistration(){
	  main.registration();
  }




}
