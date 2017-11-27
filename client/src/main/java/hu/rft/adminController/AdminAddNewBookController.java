package hu.rft.adminController;

import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;

public class AdminAddNewBookController {
	private Main main;
	
	  public void setMainApp(Main main) {
	      this.main = main;
	  }
	  
	  @FXML
	  private void BackHome() {
		  main.AdminHomePage();

	  }
}
