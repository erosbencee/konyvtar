package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.RestClient;
import hu.rft.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;


public class HomeController {

    private RestClient rc = new RestClient();

    private User current = null;

    Alert error = new Alert(AlertType.ERROR);
    Alert general = new Alert(AlertType.INFORMATION);
    Alert confirm = new Alert(AlertType.CONFIRMATION);

    @FXML
    private Button loginBtn;
    @FXML
    private Button reigstrationBtn;
    @FXML
    private TextField login;
    @FXML
    private PasswordField jelszo;

    private Main main;

    @FXML
    private void initialize() {

    }

    public void setMainApp(Main main) {

        this.main = main;

    }

    @FXML
    private void login() {
        System.exit(0);
    }

    @FXML
    private void OpenRegistration() {
        main.registration();
    }

    @FXML
    private void Login() {

        if (login.getText().isEmpty() || jelszo.getText().isEmpty()) {

            error.setTitle("Hiba");
            error.setHeaderText("A felhasználónév és a jelszó megadása kötelező!");
            error.showAndWait();
            
            return;
        }

        try {

            current = rc.loginUser(login.getText(), jelszo.getText());

        } catch (RuntimeException e) {

            error.setTitle("Hiba");
            error.setHeaderText(e.getMessage());
            error.showAndWait();

            return;
        }
        
        if(current.getEndDate() != null) {
            
            error.setTitle("Hiba");
            error.setHeaderText("A megadott felhasználói fiók inaktiválva lett!");
            error.setContentText("Amennyiben nem tudsz erről, kérjük lépj kapcsolatba egy adminisztrátorral!");
            error.showAndWait();

            return;
        }
        
        if (rc.isAdmin(current.getUserId())) {

            main.initAdminRootLayout(current);
            main.AdminHomePage(current);

        } else {

            main.initRootLayout(current);
            main.UserMainPage(current);
        }

    }

    public boolean isValidEmailAddress(String Email) {

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(Email);
        return m.matches();
    }

}
