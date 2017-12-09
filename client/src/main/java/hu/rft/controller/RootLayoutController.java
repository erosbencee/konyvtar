package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.RestClient;
import hu.rft.model.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;

public class RootLayoutController {

    private Main main;
    private User user;
    private RestClient rc = new RestClient();
    
    @FXML
    private Text userText;
    
    @FXML
    private Text dateText;
    
    @FXML
    private Hyperlink goAdminButton;
    
    @FXML
    private void initialize() {
        
        
    }

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;
        
        userText.setText(user.getLoginName());
        dateText.setText(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        
        if(!rc.isAdmin(user.getUserId())) {
            
            goAdminButton.setDisable(true);
            goAdminButton.setVisible(false);
        }
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
    private void SearchBooks() {
        main.SearchBooks(user);
    }

    @FXML
    private void EventList() {
        main.EventList(user);
    }
    
    @FXML
    private void FAQOverview() {
        main.FAQOverview(user);
    }
    
    @FXML
    private void toAdminSurface() {
        
        main.initAdminRootLayout(user);
        main.AdminHomePage(user);
    }

}
