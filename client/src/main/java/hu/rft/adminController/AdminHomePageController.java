package hu.rft.adminController;

import hu.rft.konyvtar.Main;
import hu.rft.model.ActiveEvent;
import hu.rft.model.ActiveLoan;
import hu.rft.model.Book;
import hu.rft.model.RestClient;
import hu.rft.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminHomePageController {

    private Main main;
    private User user;
    private RestClient rc = new RestClient();
    
    Alert error = new Alert(Alert.AlertType.ERROR);
    Alert info = new Alert(Alert.AlertType.INFORMATION);
    
    @FXML
    private TableView<ActiveLoan> borrowsTable;
    
    @FXML
    private TableView<User> usersTable;
    
    @FXML
    private TableView<ActiveEvent> eventsTable;
    
    @FXML
    private TableColumn<ActiveLoan, Integer> borrowIdCol;
    
    @FXML
    private TableColumn<ActiveLoan, Integer> userOfBorrowCol;
    
    @FXML
    private TableColumn<ActiveLoan, String> borrowedTitleCol;
    
    @FXML
    private TableColumn<ActiveLoan, String> isbnCol;
    
    @FXML
    private TableColumn<ActiveLoan, LocalDate> startOfBorrowCol;
    
    @FXML
    private TableColumn<ActiveLoan, LocalDate> endOfBorrowCol;
    
    @FXML
    private TableColumn<User, Integer> useridCol;
    
    @FXML
    private TableColumn<User, String> lastnameCol;
    
    @FXML
    private TableColumn<User, String> firstnameCol;
    
    @FXML
    private TableColumn<User, String> usernameCol;
    
    @FXML
    private TableColumn<User, String> emailCol;
    
    @FXML
    private TableColumn<ActiveEvent, Integer> eventIdCol;
    
    @FXML
    private TableColumn<ActiveEvent, String> eventNameCol;
    
    @FXML
    private TableColumn<ActiveEvent, LocalDateTime> eventTimeCol;
    
    @FXML
    private TableColumn<ActiveEvent, String> eventPlaceCol;
    
    @FXML
    private TableColumn<ActiveEvent, Integer> eventUsersCol;
    
    @FXML
    private TableColumn<ActiveEvent, Integer> organizerCol;
    
    @FXML
    private void initialize() {
        
        //borrowTable columns
        borrowIdCol.setCellValueFactory(new PropertyValueFactory<ActiveLoan, Integer>("loanId"));
        borrowIdCol.setCellFactory(col -> new TableCell<ActiveLoan, Integer>() {
            
            @Override
            protected void updateItem(Integer id, boolean empty) {
                super.updateItem(id, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(Integer.toString(id));
                }
            }
        });
        
        userOfBorrowCol.setCellValueFactory(new PropertyValueFactory<ActiveLoan, Integer>("loanerId"));
        userOfBorrowCol.setCellFactory(col -> new TableCell<ActiveLoan, Integer>() {
            
            @Override
            protected void updateItem(Integer loanerid, boolean empty) {
                super.updateItem(loanerid, empty);
                if (empty) {
                    setText(null);
                } else {
                    
                    User u = rc.findUserByID(loanerid);
                    
                    setText(u.getLoginName());

                }
            }
        });
        
        borrowedTitleCol.setCellValueFactory(new PropertyValueFactory<ActiveLoan, String>("bookIsbn"));
        borrowedTitleCol.setCellFactory(col -> new TableCell<ActiveLoan, String>() {
            
            @Override
            protected void updateItem(String isbn, boolean empty) {
                super.updateItem(isbn, empty);
                if (empty) {
                    setText(null);
                } else {
                    
                    Book b = rc.findBookByISBN(isbn);
                    
                    setText(b.getTitle());
                }
            }
        });
        
        isbnCol.setCellValueFactory(new PropertyValueFactory<ActiveLoan, String>("bookIsbn"));
        startOfBorrowCol.setCellValueFactory(new PropertyValueFactory<ActiveLoan, LocalDate>("startDate"));
        startOfBorrowCol.setCellFactory(col -> new TableCell<ActiveLoan, LocalDate>() {
            
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
            }
        });
        
        endOfBorrowCol.setCellValueFactory(new PropertyValueFactory<ActiveLoan, LocalDate>("returnDeadline"));
        endOfBorrowCol.setCellFactory(col -> new TableCell<ActiveLoan, LocalDate>() {
            
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
            }
        });
        
        //userTable columns
        useridCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
        useridCol.setCellFactory(col -> new TableCell<User, Integer>() {
            
            @Override
            protected void updateItem(Integer id, boolean empty) {
                super.updateItem(id, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(Integer.toString(id));
                }
            }
        });
        
        lastnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("forename"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("loginName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("emailAddr"));
        
        //eventsTable columns
        eventIdCol.setCellValueFactory(new PropertyValueFactory<ActiveEvent, Integer>("eventId"));
        eventIdCol.setCellFactory(col -> new TableCell<ActiveEvent, Integer>() {
            
            @Override
            protected void updateItem(Integer id, boolean empty) {
                super.updateItem(id, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(Integer.toString(id));
                }
            }
        });
        
        eventNameCol.setCellValueFactory(new PropertyValueFactory<ActiveEvent, String>("eventName"));
        eventTimeCol.setCellValueFactory(new PropertyValueFactory<ActiveEvent, LocalDateTime>("eventBegins"));
        eventTimeCol.setCellFactory(col -> new TableCell<ActiveEvent, LocalDateTime>() {
            
            @Override
            protected void updateItem(LocalDateTime date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
            }
        });
        
        eventPlaceCol.setCellValueFactory(new PropertyValueFactory<ActiveEvent, String>("eventLocation"));
        eventUsersCol.setCellValueFactory(new PropertyValueFactory<ActiveEvent, Integer>("expectedPpl"));
        eventUsersCol.setCellFactory(col -> new TableCell<ActiveEvent, Integer>() {
            
            @Override
            protected void updateItem(Integer users, boolean empty) {
                super.updateItem(users, empty);
                if (empty) {
                    setText(null);
                } else {
                    
                    ActiveEvent ae = (ActiveEvent) this.getTableRow().getItem();
                    
                    setText(ae.getExpectedPpl() + "/" + ae.getMaxPpl());
                }
            }
        });
        
        organizerCol.setCellValueFactory(new PropertyValueFactory<ActiveEvent, Integer>("organizerId"));
        organizerCol.setCellFactory(col -> new TableCell<ActiveEvent, Integer>() {
            
            @Override
            protected void updateItem(Integer id, boolean empty) {
                super.updateItem(id, empty);
                if (empty) {
                    setText(null);
                } else {
                    
                    User u = rc.findUserByID(id);
                    
                    setText(u.getSurname() + " " + u.getForename());
                }
            }
        });
    }
    
    @FXML
    private void closeBorrow() {
        
        ActiveLoan loan = borrowsTable.getSelectionModel().getSelectedItem();
        
        if(loan == null) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem választottál kölcsönzést!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        try {
            
            String result = rc.closeBorrow(loan);
            
            info.setTitle("Siker");
            info.setHeaderText(result);
            info.setContentText("");
            info.showAndWait();
            
            borrowsTable.getItems().clear();
            
        } catch(RuntimeException re) {
            
            error.setTitle("Hiba");
            error.setHeaderText(re.getMessage());
            error.setContentText("");
            error.showAndWait();
            
        } catch(Exception e) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Hiba történt a lezárás során!");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }
    
    @FXML
    public void deleteUser() {
        
        User selected = usersTable.getSelectionModel().getSelectedItem();
        
        if(selected == null) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem választottál felhasználót!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        if(selected.equals(user)) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem törölheted magad!");
            error.setContentText("Adminisztrátori fiók törlésével fordulj egy DBA-hez!");
            error.showAndWait();
            
            return;
        }
        
        try {
            
            rc.deleteUser(selected);
            
            info.setTitle("Siker");
            info.setHeaderText("A felhasználó eltávolításra került!");
            info.setContentText("");
            info.showAndWait();
            
            borrowsTable.getItems().clear();
            
        } catch(Exception e) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Hiba történt a felhasználó törlése közben!");
            error.setContentText(e.getMessage());
            error.showAndWait();
            
        }
    }
    
    @FXML
    public void cancelEvent() {
        
        ActiveEvent event = eventsTable.getSelectionModel().getSelectedItem();
        
        if(event == null) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem választottál rendezvényt!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        try {
            
            String result = rc.cancelEvent(event.getEventId());
            
            info.setTitle("Siker");
            info.setHeaderText(result);
            info.setContentText("");
            info.showAndWait();
            
            eventsTable.getItems().clear();
            
        } catch(Exception e) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Hiba történt a lemondás során!");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }
    
    @FXML
    private void queryAllBorrows() {
        
        List<ActiveLoan> loans = rc.getAllBorrows();
        
        if(loans.isEmpty())
            borrowsTable.setPlaceholder(new Label("Jelenleg nincs aktív kölcsönzés a rendszerben!"));
        
        borrowsTable.getItems().clear();
        borrowsTable.getItems().addAll(loans);
    }
    
    @FXML
    private void queryAllUsers() {
        
        List<User> users = rc.getAllUsers();
        
        if(users.isEmpty())
            usersTable.setPlaceholder(new Label("Nincs egy regisztrált felhasználó sem a rendszerben!"));
        
        usersTable.getItems().clear();
        usersTable.getItems().addAll(users);
    }
    
    @FXML
    private void queryAllEvents() {
        
        List<ActiveEvent> users = rc.getAllEvents();
        
        if(users.isEmpty())
            usersTable.setPlaceholder(new Label("Nincs egy rendezvény sem a rendszerben!"));
        
        eventsTable.getItems().clear();
        eventsTable.getItems().addAll(users);
    }

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;
    }

}
