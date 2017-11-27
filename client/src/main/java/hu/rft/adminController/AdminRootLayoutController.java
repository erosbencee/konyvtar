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
	  	main.AdminHomePage();

	  }
	
	@FXML
	  private void BackLogin() {
	  	main.initLogin();

	  }
	
	@FXML
	  private void AddNewBook() {
	  	main.AdminAddNewBook();

	  }
	
	@FXML
	  private void EditEvent() {
	  	main.AdminEditEvent();

	  }
	
	@FXML
	  private void EditUser() {
	  	main.AdminEditUser();

	  }
	
	@FXML
	  private void BorrowsOverview() {
	  	main.BorrowsOverview();

	  }
	
	
}
