package hu.rft.adminController;

import hu.rft.konyvtar.Main;
import hu.rft.model.ActiveLoan;
import hu.rft.model.RestClient;
import hu.rft.model.User;
import java.time.LocalDate;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AdminEditUserController {
    
    private Main main;
    private User user;
    private RestClient rc = new RestClient();
    
    Alert error = new Alert(Alert.AlertType.ERROR);
    Alert info = new Alert(Alert.AlertType.INFORMATION);
    
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
    private TextField lastnameField;
    
    @FXML
    private TextField firstnameField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private PasswordField pwdField;
    
    @FXML
    private PasswordField pwd2Field;
    
    @FXML
    private void initialize() {
        
        
    }

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;
        
        viewUserData();
    }
    
    private void viewUserData() {
        
        lastnameText.setText(user.getSurname());
        firstnameText.setText(user.getForename());
        dayOfBirthText.setText(user.getDateOfBirth().toString());
        usernameText.setText(user.getLoginName());
        emailText.setText(user.getEmailAddr());
        idText.setText(user.getUserId().toString());
        registrationText.setText(user.getRegisteredOn().toLocalDate().toString());
        
        List<ActiveLoan> loans = rc.getLoansForUser(user.getUserId());
        
        numberOfBorrowsText.setText(Integer.toString(loans.size()));
        
        int counter = 0;
        for(ActiveLoan c : loans) {
            
            if(c.getReturnDeadline().isBefore(LocalDate.now()))
                counter++;
        }
        
        borrowsOverDeadlineText.setText(Integer.toString(counter));
    }

    @FXML
    private void BackHome() {
        main.AdminHomePage(user);

    }
    
    @FXML
    private void save() {
        
        String lastname = lastnameField.getText();
        String firstname = firstnameField.getText();
        String email = emailField.getText();
        String pwd = pwdField.getText();
        String pwd2 = pwd2Field.getText();
        
        if(lastname.isEmpty() && firstname.isEmpty() && email.isEmpty() && pwd.isEmpty() && pwd2.isEmpty()) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nincs mit frissíteni!");
            error.showAndWait();
            return;
        }
        
        if(!pwd.equals(pwd2)) {
            
            error.setTitle("Hiba");
            error.setHeaderText("A beírt jelszavak nem egyeznek!");
            error.showAndWait();
            return;
        }
        
        if(!email.isEmpty() && !isValidEmailAddress(email)) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem megfelelő email formátum!");
            error.showAndWait();
            return;
        }
        
        User updated = user;
        
        if(!lastname.isEmpty())
            updated.setSurname(lastname);
        
        if(!firstname.isEmpty())
            updated.setForename(firstname);
        
        if(!email.isEmpty())
            updated.setEmailAddr(email);
        
        if(!pwd.isEmpty())
            updated.setPassword(pwd);
        
        try {
        
            user = rc.updateUser(updated);
            
        } catch(Exception e) {
            
            if(e.getMessage().contains("500")) {
                
                error.setTitle("Hiba");
                error.setHeaderText("A megadott email cím már létezik!");
                error.showAndWait();
                
                return;
            }
            
            error.setTitle("Hiba");
            error.setHeaderText("Hiba történt a frissítés során!");
            error.showAndWait();
            
            return;
        }
        
        info.setTitle("Siker");
        info.setHeaderText("Az adatok frissítve lettek!");
        info.showAndWait();
        
        main.AdminEditUser(user);
    }
    
    private boolean isValidEmailAddress(String Email) {

        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(Email);
        return m.matches();
    }
}
