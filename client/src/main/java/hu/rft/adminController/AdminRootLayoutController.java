package hu.rft.adminController;

import hu.rft.konyvtar.Main;
import hu.rft.model.User;
import javafx.fxml.FXML;

public class AdminRootLayoutController {

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

    @FXML
    private void BackLogin() {
        main.initLogin();

    }

    @FXML
    private void AddNewBook() {
        main.AdminAddNewBook(user);

    }

    @FXML
    private void EditEvent() {
        main.AdminEditEvent(user);

    }

    @FXML
    private void EditUser() {
        main.AdminEditUser(user);

    }

    @FXML
    private void BorrowsOverview() {
        main.BorrowsOverview(user);

    }

}
