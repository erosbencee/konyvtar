package hu.rft.adminController;

import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;

public class AdminEditEventController {
	private Main main;
	
	  public void setMainApp(Main main) {
	      this.main = main;
	  }
	  
	  @FXML
	  private void BackHome() {
		  main.AdminHomePage();

	  }
}
