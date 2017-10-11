package hu.rft.controller;
import java.time.LocalDate;

import hu.rft.konyvtar.Main;



import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class RegistrationController {
		private Main main;
		private Stage dateStage;
		@FXML
		private DatePicker datePicker;
	@FXML
	private void initialize() {


	}
	

  public void setMainApp(Main main) {
      this.main = main;
        
  }

  
 public void Date(){
//Create the DatePicker.
  datePicker = new DatePicker();

//Add some action (in Java 8 lambda syntax style).
datePicker.setOnAction(event -> {
   LocalDate date = datePicker.getValue();
   System.out.println("Selected date: " + date);
});

//Add the DatePicker to the Stage.
StackPane root = new StackPane();
root.getChildren().add(datePicker);
 dateStage.setScene(new Scene(root, 500, 650));
dateStage.show();
 }
 


}
