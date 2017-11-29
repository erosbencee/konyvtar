package hu.rft.adminController;

import hu.rft.konyvtar.Main;
import hu.rft.model.User;
import javafx.fxml.FXML;

public class AdminEditEventController {

    private Main main;
    private User user;

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;
    }

    @FXML
    private void BackHome() {
        main.AdminHomePage(user);

    }
}
