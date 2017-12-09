package hu.rft.adminController;

import hu.rft.konyvtar.Main;
import hu.rft.model.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AdminRootLayoutController {

    private Main main;
    private User user;
    
    @FXML
    private Text adminUsernameText;
    
    @FXML
    private Text dateText;

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;
        
        adminUsernameText.setText(user.getLoginName());
        dateText.setText(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
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
    private void EditUser() {
        main.AdminEditUser(user);

    }
    
    @FXML
    private void toRegularSurface() {
        
        main.initRootLayout(user);
        main.UserMainPage(user);
    }

}
