package hu.rft.adminController;

import hu.rft.konyvtar.Main;
import hu.rft.model.User;

public class AdminHomePageController {

    private Main main;
    private User user;

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;
    }

}
