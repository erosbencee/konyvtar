package hu.rft.controller;

import hu.rft.db.DBConnector;
import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;

public class RootLayoutController {
	private Main main;
        DBConnector dbc;
	
	  public void setMainApp(Main main, DBConnector dbcon) {
	      this.main = main;
	      dbc = dbcon;
	  }
	  
	  @FXML
	  private void Back() {
	  	main.initLogin(false, dbc);

	  }
	  @FXML
	  private void BackHome() {
	  	main.UserMainPage(dbc);

	  }
	  
	  @FXML
	  private void PersonalData() {
	  	main.PersonalData(dbc);

	  }
}
