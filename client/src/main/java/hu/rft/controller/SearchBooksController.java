package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.Book;
import hu.rft.model.RestClient;
import hu.rft.model.User;
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

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;

    }
    
    @FXML
    private Button searchBtn;
    
    @FXML
    private TableView<Book> bookTable = new TableView<>();
    
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
//        quantity.setCellFactory(lv -> createIntCell());
//        quantity.setButtonCell(createIntCell());
        
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
        
        bookTable.getItems().setAll(rc.findBooks(titleField.getText(), authorField.getText(), publisherField.getText(), genreField.getText()));
        bookTable.setVisible(true);      
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
        }
    }
    
    @FXML
    private void BackHome() {
        main.UserMainPage(user);

    }
    
    private ListCell<Integer> createIntCell() {
        
        return new ListCell<Integer>() {
            
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString());
                }
            }
        };
    }

}
