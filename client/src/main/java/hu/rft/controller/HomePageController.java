package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.ActiveLoan;
import hu.rft.model.Book;
import hu.rft.model.RestClient;
import hu.rft.model.User;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TableView<List<String>> kolcsonzesTablazat;
    
    @FXML
    private TableColumn<List<String>, String> isbnCol;
    
    @FXML
    private TableColumn<List<String>, String> titleCol;
    
    @FXML
    private TableColumn<List<String>, String> authorCol;
    
    @FXML
    private TableColumn<List<String>, String> publisherCol;
    
    @FXML
    private TableColumn<List<String>, String> genreCol;
    
    @FXML
    private TableColumn<List<String>, String> quantityCol;
    
    @FXML
    private TableColumn<List<String>, String> dayOfBorrowCol;
    
    @FXML
    private TableColumn<List<String>, String> dayOfDeadlineCol;
    
    @FXML
    private Button queryBorrowBtn;
    
    @FXML
    private Button queryEventsBtn;
    
    @FXML
    private void initialize() {
        
        isbnCol.setCellValueFactory((p) -> {
            
            List<String> x = p.getValue();
            return new SimpleStringProperty(x != null && x.size() > 0 ? x.get(0) : "<no data>");
        });

        titleCol.setCellValueFactory((p) -> {
            
            List<String> x = p.getValue();
            return new SimpleStringProperty(x != null && x.size() > 0 ? x.get(1) : "<no data>");
        });
        
        authorCol.setCellValueFactory((p) -> {
            
            List<String> x = p.getValue();
            return new SimpleStringProperty(x != null && x.size() > 0 ? x.get(2) : "<no data>");
        });
        
        publisherCol.setCellValueFactory((p) -> {
            
            List<String> x = p.getValue();
            return new SimpleStringProperty(x != null && x.size() > 0 ? x.get(3) : "<no data>");
        });
        
        genreCol.setCellValueFactory((p) -> {
            
            List<String> x = p.getValue();
            return new SimpleStringProperty(x != null && x.size() > 0 ? x.get(4) : "<no data>");
        });
        
        quantityCol.setCellValueFactory((p) -> {
            
            List<String> x = p.getValue();
            return new SimpleStringProperty(x != null && x.size() > 0 ? x.get(5) : "<no data>");
        });
        
        dayOfBorrowCol.setCellValueFactory((p) -> {
            
            List<String> x = p.getValue();
            return new SimpleStringProperty(x != null && x.size() > 0 ? x.get(6) : "<no data>");
        });
        
        dayOfDeadlineCol.setCellValueFactory((p) -> {
            
            List<String> x = p.getValue();
            return new SimpleStringProperty(x != null && x.size() > 0 ? x.get(7) : "<no data>");
        });
        
        
    }
    
    private List<List<String>> buildBorrowList() {
        
        List<List<String>> result = new ArrayList<>();
        List<ActiveLoan> loans = rc.getLoansForUser(user.getUserId());
        
        for(ActiveLoan c : loans) {
            
            Book b = rc.findBookByISBN(c.getBookIsbn());
            
            List<String> tmp = new ArrayList<>();
            
            tmp.add(b.getIsbnId());
            tmp.add(b.getTitle());
            tmp.add(b.getAuthor());
            tmp.add(b.getPublisher());
            tmp.add(b.getGenre());
            tmp.add(Integer.toString(c.getQuantity()));
            tmp.add(c.getStartDate().toString());
            tmp.add(c.getReturnDeadline().toString());
            
            result.add(tmp);
        }
        
        return result;
    }
    
    @FXML
    private void queryBorrows() {
        
        List<List<String>> values = buildBorrowList();
        
        kolcsonzesTablazat.getItems().clear();
        kolcsonzesTablazat.getItems().addAll(values);
        
        if(values.isEmpty()) {
            
            kolcsonzesTablazat.setPlaceholder(new Label("Nem található kölcsönzés a neved alatt!"));
        }
    }

    public void setMainApp(Main main, User current) {
        
        this.main = main;
        this.user = current;
    }

    @FXML
    private void Back() {
        main.initLogin();

    }
}
