package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.ActiveLoan;
import hu.rft.model.Book;
import hu.rft.model.RestClient;
import hu.rft.model.User;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class HomePageController {

    private Main main;
    private User user;
    private RestClient rc = new RestClient();
    
    @FXML
    private Text msgText;
    
    @FXML
    private TableView kolcsonzesTablazat;
    
    @FXML
    private TableColumn<Book, String> isbnCol;
    
    @FXML
    private TableColumn<Book, String> titleCol;
    
    @FXML
    private TableColumn<Book, String> authorCol;
    
    @FXML
    private TableColumn<Book, String> publisherCol;
    
    @FXML
    private TableColumn<Book, String> genreCol;
    
    @FXML
    private TableColumn<Book, String> quantityCol;
    
    @FXML
    private TableColumn<ActiveLoan, String> dayOfBorrowCol;
    
    @FXML
    private TableColumn<ActiveLoan, String> dayOfDeadlineCol;
    
    @FXML
    private void initialize() {
        
//        isbnCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbnId"));
//        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
//        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
//        publisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
//        genreCol.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
//        quantityCol.setCellValueFactory(new PropertyValueFactory<Book, String>("onhandQty"));
//        dayOfBorrowCol.setCellValueFactory(new PropertyValueFactory<ActiveLoan, String>("startDate"));
//        dayOfDeadlineCol.setCellValueFactory(new PropertyValueFactory<ActiveLoan, String>("returnDeadline"));
        
//        kolcsonzesTablazat.setVisible(false);

        List<ActiveLoan> loans = rc.getLoansForUser(user.getUserId());
        
        for(ActiveLoan c : loans) {
            
            Book b = rc.findBookByISBN(Integer.parseInt(c.getBookIsbn()));
            
            kolcsonzesTablazat.getItems().
        }
    }

    public void setMainApp(Main main, User current) {
        this.main = main;
    }

    @FXML
    private void Back() {
        main.initLogin();

    }
}
