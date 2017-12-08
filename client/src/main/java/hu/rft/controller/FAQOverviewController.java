package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.User;
import javafx.scene.text.Text;


public class FAQOverviewController {
    private Main main;
    private User user;
    private Text egy;
   
    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;
   
    }



 
}
