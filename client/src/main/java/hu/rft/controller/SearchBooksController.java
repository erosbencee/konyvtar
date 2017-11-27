package hu.rft.controller;

import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;

public class SearchBooksController {
	private Main main;
    
	
	  public void setMainApp(Main main) {
	      this.main = main;
	      
	  }
	  
	  @FXML
	  private void BackHome() {
	  	main.UserMainPage();

	  }
	  
}
