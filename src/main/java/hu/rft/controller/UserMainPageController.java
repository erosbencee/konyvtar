package hu.rft.controller;

import hu.rft.db.DBConnector;
import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class UserMainPageController {
	private Main main;
        DBConnector dbc;

@FXML
private void initialize() {


}


public void setMainApp(Main main, DBConnector dbcon) {
  this.main = main;
  dbc = dbcon;
}

@FXML
private void Back() {
	main.initLogin(false, dbc);

}
}
