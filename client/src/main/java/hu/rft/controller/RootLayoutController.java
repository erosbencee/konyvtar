package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class RootLayoutController {

    private Main main;
    private User user;
    
    @FXML
    private Text userText;
    
    @FXML
    private Text dateText;
    
    @FXML
    private void initialize() {
        
        
    }

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;
        
        userText.setText(user.getLoginName());
        dateText.setText(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
    }

    @FXML
    private void Back() {
        main.initLogin();

    }

    @FXML
    private void BackHome() {
        main.UserMainPage(user);

    }

    @FXML
    private void PersonalData() {
        main.PersonalData(user);

    }

    @FXML
    private void LoanOverview() {
        main.LoanOverview(user);
    }

    @FXML
    private void SearchBooks() {
        main.SearchBooks(user);
    }

    @FXML
    private void EventList() {
        main.EventList(user);
    }

}
