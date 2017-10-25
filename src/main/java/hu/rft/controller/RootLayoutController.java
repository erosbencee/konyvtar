package hu.rft.controller;

import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;

public class RootLayoutController {
	private Main main;
	
	  public void setMainApp(Main main) {
	      this.main = main;
	        
	  }
	  
	  @FXML
	  private void Back() {
	  	main.initLogin();

	  }
}
