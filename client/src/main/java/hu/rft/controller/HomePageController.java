package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.User;
import javafx.fxml.FXML;

import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class HomePageController {

    private Main main;
    private User user;
    
    @FXML
    private Text msgText;
    
    @FXML
    private TableView kolcsonzesTablazat;
    
    @FXML
    private void initialize() {
        
        
    }

    public void setMainApp(Main main, User current) {
        this.main = main;
    }

    @FXML
    private void Back() {
        main.initLogin();

    }
}
