package hu.rft.controller;

import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class HomePageController {
	
    private Main main;
        

@FXML
private void initialize() {


}


public void setMainApp(Main main) {
  this.main = main;
}

@FXML
private void Back() {
	main.initLogin();

}
}
