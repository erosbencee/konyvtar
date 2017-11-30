package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PersonalDataController {

    private Main main;
    private User user;
    
    @FXML
    private Text lastnameText;
    
    @FXML
    private Text firstnameText;
    
    @FXML
    private Text dayOfBirthText;
    
    @FXML
    private Text usernameText;
    
    @FXML
    private Text emailText;
    
    @FXML
    private Text idText;
    
    @FXML
    private Text registrationText;
    
    @FXML
    private Text numberOfBorrowsText;
    
    @FXML
    private Text borrowsOverDeadlineText;
    
    @FXML
    private Text fineText;
    
    @FXML
    private TextField lastnameField;
    
    @FXML
    private TextField firstnameField;
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private TextField emailField;

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;
    }

    @FXML
    private void BackHome() {
        main.UserMainPage(user);

    }
}
