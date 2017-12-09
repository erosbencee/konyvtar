package hu.rft.adminController;

import hu.rft.konyvtar.Main;
import hu.rft.model.Book;
import hu.rft.model.RestClient;
import hu.rft.model.User;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.math.NumberUtils;

public class AdminAddNewBookController {

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
    private TextField isbnField;
    
    @FXML
    private TextField titleField;
    
    @FXML
    private TextField subtitleField;
    
    @FXML
    private TextField authorField;
    
    @FXML
    private TextField publisherField;
    
    @FXML
    private TextField qtyField;
    
    @FXML
    private TextField genreField;
    
    @FXML
    private void save() {
        
        String isbn = isbnField.getText();
        String title = titleField.getText();
        String subtitle = subtitleField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        String qty = qtyField.getText();
        String genre = genreField.getText();
        
        if(isbn.isEmpty() || title.isEmpty() || author.isEmpty() || publisher.isEmpty() || qty.isEmpty() || genre.isEmpty()) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Az alcím kivételével az összes mező megadása kötelező!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        if(!NumberUtils.isDigits(isbn)) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Az ISBN csak számjegyeket tartalmazhat!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        if(isbn.length() != 13) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Az ISBN számnak pontosan 13 számjegyből kell állnia!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        
        if(!NumberUtils.isCreatable(qty)) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Érvénytelen darabszám!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        if(Integer.parseInt(qty) < 1) {
            
            error.setTitle("Hiba");
            error.setHeaderText("A darabszám legalább 1 kell legyen!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        List<Book> books = rc.getAllBooks();
        
        for(Book c : books) {
            
            if(c.getIsbnId().equals(isbn)) {
                
                error.setTitle("Hiba");
                error.setHeaderText("Már létezik könyv a rendszerben ilyen ISBN számmal!");
                error.setContentText("");
                error.showAndWait();
                
                return;
            }
        }
        
        Book book = new Book();
        
        book.setIsbnId(isbn);
        book.setTitle(title);
        book.setSubtitle(subtitle);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setOnhandQty(Integer.parseInt(qty));
        book.setGenre(genre);
        
        try {
            
            rc.newBook(book);
            
            info.setTitle("Siker");
            info.setHeaderText("Az új könyv sikeresen bekerült a rendszerbe!");
            info.setContentText("");
            info.showAndWait();
            
            main.AdminAddNewBook(user);
            
        } catch(Exception e) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Hiba történt a mentés során!");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }

    @FXML
    private void BackHome() {
        main.AdminHomePage(user);
    }
}
