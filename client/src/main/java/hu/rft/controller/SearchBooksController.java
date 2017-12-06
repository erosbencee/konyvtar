package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.ActiveLoan;
import hu.rft.model.Book;
import hu.rft.model.RestClient;
import hu.rft.model.User;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class SearchBooksController {

    private Main main;
    private User user;
    private RestClient rc = new RestClient();
    
    Alert error = new Alert(AlertType.ERROR);
    Alert info = new Alert(AlertType.INFORMATION);

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;

    }
    
    @FXML
    private Button searchBtn;
    
    @FXML
    private TableView<Book> bookTable;
    
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
    private TableColumn<Book, String> qtyCol;
    
    @FXML
    private ComboBox<Integer> quantity;
    
    @FXML
    private Button borrowBtn;
    
    @FXML
    private TextField titleField;
    
    @FXML
    private TextField authorField;
    
    @FXML
    private TextField publisherField;
    
    @FXML
    private TextField genreField;
    
    @FXML
    private void initialize() {
        
        quantity.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        
        isbnCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbnId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        genreCol.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<Book, String>("onhandQty"));
        
        bookTable.setVisible(false);
    }
    
    @FXML
    private void search() {
        
        Book needed = new Book();
        
        needed.setTitle(titleField.getText());
        needed.setAuthor(authorField.getText());
        needed.setPublisher(publisherField.getText());
        needed.setGenre(genreField.getText());
        
        List<Book> searchResult = rc.findBooks(needed);
        
        if(searchResult.isEmpty()) {
            
            info.setTitle("Keresés");
            info.setHeaderText("A megadott szűrőkre a keresés nem járt eredménnyel");
            info.showAndWait();
            
        } else {
            
            bookTable.getItems().setAll(searchResult);
            bookTable.setVisible(true);
        }
        
    }
    
    @FXML
    private void borrow() {
        
        Book selected = bookTable.getSelectionModel().getSelectedItem();
        
        if(selected == null) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem választottál könyvet!");
            error.showAndWait();
            
            return;
        }
        
        if(quantity.getSelectionModel().isEmpty()) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem válaszottál darabszámot!");
            error.showAndWait();
            
            return;
        }
        
        int chosenQuantity = quantity.getSelectionModel().getSelectedItem();
        
        if(chosenQuantity > selected.getOnhandQty()) {
            
            error.setTitle("Hiba");
            error.setHeaderText("A választott könyvből nincs készleten a megadott mennyiség!");
            error.showAndWait();
            
            return;
        }
        
        ActiveLoan loan = new ActiveLoan();
        
        loan.setBookIsbn(selected.getIsbnId());
        loan.setLoanerId(user.getUserId());
        loan.setQuantity(chosenQuantity);
        loan.setStartDate(LocalDate.now());
        loan.setReturnDeadline(LocalDate.now().plusWeeks(3));
        
        try {
            
            rc.startLoan(loan);
            
            info.setTitle("Siker");
            info.setHeaderText("A kölcsönzés létrejött az alábbi adatokkal:");
            info.setContentText("Könyv címe: " + selected.getTitle() 
                              + "\nDarabszám: " + chosenQuantity 
                              + "\nHatáridő: " + loan.getReturnDeadline().toString());
            info.showAndWait();
            
        } catch(Exception e) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Hiba történt a kölcsönzési folyamat során!");
            error.setContentText(e.getMessage());
        }
    }
    
    @FXML
    private void BackHome() {
        main.UserMainPage(user);

    }

}
