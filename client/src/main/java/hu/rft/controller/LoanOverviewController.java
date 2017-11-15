package hu.rft.controller;

import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;

public class LoanOverviewController {
	private Main main;
    

  public void setMainApp(Main main) {
      this.main = main;
     
  }

@FXML
  private void BackHome() {
  	main.UserMainPage();

  }

}
