package hu.rft.adminController;

import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;

public class AdminRootLayoutController {

	private Main main;
	
	  public void setMainApp(Main main) {
	      this.main = main;
	  }
	
	@FXML
	  private void BackHome() {
	  	main.UserMainPage();

	  }
	
	
}
