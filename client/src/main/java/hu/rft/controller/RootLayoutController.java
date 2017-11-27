package hu.rft.controller;

import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;

public class RootLayoutController {
	private Main main;
	
	  public void setMainApp(Main main) {
	      this.main = main;
	  }
	  
	  @FXML
	  private void Back() {
	  	main.initLogin();

	  }
	  @FXML
	  private void BackHome() {
	  	main.UserMainPage();

	  }
	  
	  @FXML
	  private void PersonalData() {
	  	main.PersonalData();

	  }
	  
	  @FXML
	  private void LoanOverview(){
		main.LoanOverview();
	  }
	  
	  @FXML
	  private void SearchBooks(){
		main.SearchBooks();
	  }
	  
	  @FXML
	  private void EventList(){
		main.EventList();
	  }
	  
	 }
	  

