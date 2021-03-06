package hu.rft.controller;

import java.time.LocalDate;

import hu.rft.konyvtar.Main;
import hu.rft.model.RestClient;
import hu.rft.model.User;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RegistrationController {

    Alert errorAlert = new Alert(AlertType.ERROR);
    Alert generalAlert = new Alert(AlertType.INFORMATION);
    Alert confirmAlert = new Alert(AlertType.CONFIRMATION);

    private Main main;
    private Stage dateStage;
    @FXML
    private TextField vezeteknev;
    @FXML
    private TextField keresztnev;
    @FXML
    private TextField felhasznalonev;
    @FXML
    private PasswordField jelszo;
    @FXML
    private PasswordField jelszo2;
    @FXML
    private TextField email;
    @FXML
    private TextField email2;
    @FXML
    private DatePicker datePicker;

    @FXML
    private void initialize() {

    }

    public void setMainApp(Main main) {
        this.main = main;
    }

    @FXML
    private void RegBack() {
        main.initLogin();

    }

    @FXML
    private void Registration() {

        String error = InputValid();

        if (error.length() == 0) {

            User user = new User();

            RestClient rc = new RestClient();

            user.setForename(keresztnev.getText());
            user.setSurname(vezeteknev.getText());
            user.setDateOfBirth(datePicker.getValue());
            user.setLoginName(felhasznalonev.getText());
            user.setPassword(jelszo.getText());
            user.setEmailAddr(email.getText());
            user.setRegisteredOn(LocalDateTime.now());
            user.setLastLogin(LocalDateTime.of(2022, Month.JANUARY, 1, 0, 0, 0));

            try {

                rc.registerUser(user);

                confirmAlert.setTitle("Siker");
                confirmAlert.setHeaderText("Sikeres regisztráció!");
                confirmAlert.showAndWait();
                
                main.initLogin();

            } catch (Exception ex) {

                errorAlert.setTitle("Hiba");
                errorAlert.setHeaderText("Hiba történt a kérés végrehajtása során");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();

            }

        } else {

            errorAlert.setTitle("Hiba");
            errorAlert.setHeaderText("Hiba a bevitt adatokban, kérem ellenőrízze!");
            errorAlert.setContentText(error);
            errorAlert.showAndWait();
        }

    }

    private String InputValid() {
        String error = "";
        if (vezeteknev.getText().toString().isEmpty()) {
            error += "A vezetéknév mező nem lehet üres!\n";
        }
        if (keresztnev.getText().toString().isEmpty()) {
            error += "A keresztnév mező nem lehet üres!\n";
        }
        if (datePicker.getValue() == null) {
            error += "Nem adott meg születési időt!\n";
        }
        if (felhasznalonev.getText().toString().isEmpty()) {
            error += "A felhasználónév mező nem lehet üres!\n";
        }
        if ((jelszo.getText().toString().isEmpty()) && (jelszo2.getText().toString().isEmpty())) {
            error += "A jelszó mező nem lehet üres!\n";
        } else {
            String password = jelszo.getText().toString();
            String password2 = jelszo2.getText().toString();
            if (!password.equals(password2)) {
                error += "A két jelszó nem egyezik egymással!\n";
            }
        }
        if ((email.getText().toString().isEmpty()) && (email2.getText().toString().isEmpty())) {
            error += "Az email mező nem lehet üres!\n";
        } else {
            String emailcim = email.getText().toString();
            String emailcim2 = email2.getText().toString();
            if (!(emailcim.equals(emailcim2))) {
                error += "A két email nem egyezik meg egymással!\n";
            } else {
                if (!isValidEmailAddress(email.getText().toString())) {
                    error += "Nem megfelelő az email formátuma";
                }
            }
        }

        return error;
    }

    public boolean isValidEmailAddress(String Email) {

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(Email);
        return m.matches();
    }

    public void Date() {
//Create the DatePicker.
        datePicker = new DatePicker();

//Add some action (in Java 8 lambda syntax style).
        datePicker.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            System.out.println("Selected date: " + date);
        });

//Add the DatePicker to the Stage.
        StackPane root = new StackPane();
        root.getChildren().add(datePicker);
        dateStage.setScene(new Scene(root, 500, 650));
        dateStage.show();
    }

}
