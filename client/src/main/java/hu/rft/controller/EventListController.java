package hu.rft.controller;

import hu.rft.konyvtar.Main;
import hu.rft.model.ActiveEvent;
import hu.rft.model.RestClient;
import hu.rft.model.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.lang3.math.NumberUtils;

public class EventListController {

    private Main main;
    private User user;
    private RestClient rc = new RestClient();
    
    Alert error = new Alert(Alert.AlertType.ERROR);
    Alert info = new Alert(Alert.AlertType.INFORMATION);
    
    @FXML
    private TableView<ActiveEvent> allEventsTable;
    
    @FXML
    private TableColumn<ActiveEvent, LocalDateTime> dateOfEventCol1;
    
    @FXML
    private TableColumn<ActiveEvent, String> nameOfEventCol1;
    
    @FXML
    private TableColumn<ActiveEvent, String> placeOfEventCol1;
    
    @FXML
    private TableColumn<ActiveEvent, Integer> organizerCol1;
    
    @FXML
    private TableColumn<ActiveEvent, Integer> headcountCol1;
    
    @FXML
    private TableView<ActiveEvent> signedUpTable;
    
    @FXML
    private TableColumn<ActiveEvent, LocalDateTime> dateOfEventCol2;
    
    @FXML
    private TableColumn<ActiveEvent, String> nameOfEventCol2;
    
    @FXML
    private TableColumn<ActiveEvent, String> placeOfEventCol2;
    
    @FXML
    private TableColumn<ActiveEvent, Integer> organizerCol2;
    
    @FXML
    private TableColumn<ActiveEvent, Integer> headcountCol2;
    
    @FXML
    private TableView<ActiveEvent> organizedTable;
    
    @FXML
    private TableColumn<ActiveEvent, LocalDateTime> dateOfEventCol3;
    
    @FXML
    private TableColumn<ActiveEvent, String> nameOfEventCol3;
    
    @FXML
    private TableColumn<ActiveEvent, String> placeOfEventCol3;
    
    @FXML
    private TableColumn<ActiveEvent, Integer> organizerCol3;
    
    @FXML
    private TableColumn<ActiveEvent, Integer> headcountCol3;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private ComboBox<String> timeComboBox;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField placeField;
    
    @FXML
    private TextField maxPpl;
    
    @FXML
    private void initialize() {
        
        timeComboBox.setItems(FXCollections.observableArrayList("00:00", "01:00", "02:00", 
                                                                      "03:00", "04:00", "05:00", 
                                                                      "06:00", "07:00", "08:00", 
                                                                      "09:00", "10:00", "11:00", 
                                                                      "12:00", "13:00", "14:00", 
                                                                      "15:00", "16:00", "17:00",
                                                                      "18:00", "19:00", "20:00",
                                                                      "21:00", "22:00", "23:00"
                                                                     ));

        dateOfEventCol1.setCellValueFactory(new PropertyValueFactory<ActiveEvent, LocalDateTime>("eventBegins"));
        dateOfEventCol1.setCellFactory(col -> new TableCell<ActiveEvent, LocalDateTime>() {
            
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
        
        nameOfEventCol1.setCellValueFactory(new PropertyValueFactory<ActiveEvent, String>("eventName"));
        placeOfEventCol1.setCellValueFactory(new PropertyValueFactory<ActiveEvent, String>("eventLocation"));
        organizerCol1.setCellValueFactory(new PropertyValueFactory<ActiveEvent, Integer>("organizerId"));
        organizerCol1.setCellFactory(col -> new TableCell<ActiveEvent, Integer>() {
            
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
        
        headcountCol1.setCellValueFactory(new PropertyValueFactory<ActiveEvent, Integer>("eventId"));
        headcountCol1.setCellFactory(col -> new TableCell<ActiveEvent, Integer>() {
            
            @Override
            protected void updateItem(Integer eventid, boolean empty) {
                super.updateItem(eventid, empty);
                if (empty) {
                    setText(null);
                } else {
                    
                    List<ActiveEvent> events = rc.getAllEvents();
                    
                    ActiveEvent ae = new ActiveEvent();
                    
                    for(ActiveEvent c : events) {
                        
                        if(c.getEventId().equals(eventid))
                            ae = c;
                    }
                    
                    setText(ae.getExpectedPpl() + "/" + ae.getMaxPpl());
                }
            }
        });
        
        dateOfEventCol2.setCellValueFactory(new PropertyValueFactory<ActiveEvent, LocalDateTime>("eventBegins"));
        dateOfEventCol2.setCellFactory(col -> new TableCell<ActiveEvent, LocalDateTime>() {
            
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
        
        nameOfEventCol2.setCellValueFactory(new PropertyValueFactory<ActiveEvent, String>("eventName"));
        placeOfEventCol2.setCellValueFactory(new PropertyValueFactory<ActiveEvent, String>("eventLocation"));
        organizerCol2.setCellValueFactory(new PropertyValueFactory<ActiveEvent, Integer>("organizerId"));
        organizerCol2.setCellFactory(col -> new TableCell<ActiveEvent, Integer>() {
            
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
        
        headcountCol2.setCellValueFactory(new PropertyValueFactory<ActiveEvent, Integer>("eventId"));
        headcountCol2.setCellFactory(col -> new TableCell<ActiveEvent, Integer>() {
            
            @Override
            protected void updateItem(Integer eventid, boolean empty) {
                super.updateItem(eventid, empty);
                if (empty) {
                    setText(null);
                } else {
                    
                    List<ActiveEvent> events = rc.getAllEvents();
                    
                    ActiveEvent ae = new ActiveEvent();
                    
                    for(ActiveEvent c : events) {
                        
                        if(c.getEventId().equals(eventid))
                            ae = c;
                    }
                    
                    setText(ae.getExpectedPpl() + "/" + ae.getMaxPpl());
                }
            }
        });
        
        dateOfEventCol3.setCellValueFactory(new PropertyValueFactory<ActiveEvent, LocalDateTime>("eventBegins"));
        dateOfEventCol3.setCellFactory(col -> new TableCell<ActiveEvent, LocalDateTime>() {
            
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
        
        nameOfEventCol3.setCellValueFactory(new PropertyValueFactory<ActiveEvent, String>("eventName"));
        placeOfEventCol3.setCellValueFactory(new PropertyValueFactory<ActiveEvent, String>("eventLocation"));
        organizerCol3.setCellValueFactory(new PropertyValueFactory<ActiveEvent, Integer>("organizerId"));
        organizerCol3.setCellFactory(col -> new TableCell<ActiveEvent, Integer>() {
            
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
        
        headcountCol3.setCellValueFactory(new PropertyValueFactory<ActiveEvent, Integer>("eventId"));
        headcountCol3.setCellFactory(col -> new TableCell<ActiveEvent, Integer>() {
            
            @Override
            protected void updateItem(Integer eventid, boolean empty) {
                super.updateItem(eventid, empty);
                if (empty) {
                    setText(null);
                } else {
                    
                    List<ActiveEvent> events = rc.getAllEvents();
                    
                    ActiveEvent ae = new ActiveEvent();
                    
                    for(ActiveEvent c : events) {
                        
                        if(c.getEventId().equals(eventid))
                            ae = c;
                    }
                    
                    setText(ae.getExpectedPpl() + "/" + ae.getMaxPpl());
                }
            }
        });
    }
    
    @FXML
    private void queryAllEvents() {
        
        List<ActiveEvent> events = rc.getAllEvents();
        
        if(events.isEmpty())
            allEventsTable.setPlaceholder(new Label("Nincsenek rendezvények amikre jelentkezhetsz!"));
        
        allEventsTable.getItems().clear();
        allEventsTable.getItems().addAll(events);
    }
    
    @FXML
    private void querySignedUpEvents() {
        
        List<ActiveEvent> events = rc.getEventsByAttendee(user.getUserId());
        
        if(events.isEmpty())
            signedUpTable.setPlaceholder(new Label("Nem jelentkeztél egy rendezvényre sem!"));
        
        signedUpTable.getItems().clear();
        signedUpTable.getItems().addAll(events);
    }
    
    @FXML
    private void queryOrganizedEvents() {
        
        List<ActiveEvent> events = rc.getEventsByOrganizer(user.getUserId());
        
        if(events.isEmpty())
            organizedTable.setPlaceholder(new Label("Jelenleg nem rendezel rendezvényt!"));
        
        organizedTable.getItems().clear();
        organizedTable.getItems().addAll(events);
    }
    
    @FXML
    private void signUpToEvent() {
        
        List<ActiveEvent> signedUp = rc.getEventsByAttendee(user.getUserId());
        
        ActiveEvent event = allEventsTable.getSelectionModel().getSelectedItem();
        
        if(event == null) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem választottál rendezvényt!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        if(signedUp.contains(event)) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Már jelentkeztél erre a rendezvényre!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        if(!(event.getExpectedPpl() + 1 <= event.getMaxPpl())) {
            
            error.setTitle("Hiba");
            error.setHeaderText("A rendezvény betelt!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        if(event.getOrganizerId() == user.getUserId()) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Ennek az eseménynek te vagy a szervezője, nem kell rá jelentkezned!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        try {
            
            String result = rc.signUpToEvent(event.getEventId(), user.getUserId());
            
            info.setTitle("Siker");
            info.setHeaderText(result);
            info.setContentText("");
            info.showAndWait();
            
            allEventsTable.getItems().clear();
            
        } catch(RuntimeException re) {
            
            error.setTitle("Hiba");
            error.setHeaderText(re.getMessage());
            error.setContentText("");
            error.showAndWait();
            
        } catch(Exception e) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Hiba történt a jelentkezés során!");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }
    
    @FXML
    private void pullBackFromEvent() {
        
        ActiveEvent event = signedUpTable.getSelectionModel().getSelectedItem();
        
        if(event == null) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem választottál rendezvényt!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        try {
            
            String result = rc.pullBackFromEvent(event.getEventId(), user.getUserId());
            
            info.setTitle("Siker");
            info.setHeaderText(result);
            info.setContentText("");
            info.showAndWait();
            
            signedUpTable.getItems().clear();
            
        } catch(RuntimeException re) {
            
            error.setTitle("Hiba");
            error.setHeaderText(re.getMessage());
            error.setContentText("");
            error.showAndWait();
            
        } catch(Exception e) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Hiba történt a lejelentkezés során!");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }
    
    @FXML
    public void cancelEvent() {
        
        ActiveEvent event = organizedTable.getSelectionModel().getSelectedItem();
        
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
            
            organizedTable.getItems().clear();
            
        } catch(RuntimeException re) {
            
            error.setTitle("Hiba");
            error.setHeaderText(re.getMessage());
            error.setContentText("");
            error.showAndWait();
            
        } catch(Exception e) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Hiba történt a lemondás során!");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }
    
    @FXML
    private void postNewEvent() {
        
        String name = nameField.getText();
        String place = placeField.getText();
        String maxppl = maxPpl.getText();
        
        LocalDate date = datePicker.getValue();
        
        String timeStr = timeComboBox.getSelectionModel().getSelectedItem();
        
        if(name.isEmpty() || place.isEmpty() || maxppl.isEmpty() || date == null || timeStr == null) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Minden mező megadása kötelező!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        if(!NumberUtils.isCreatable(maxppl) || maxppl.length() > 3) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Érvénytelen érték a max létszámnál!");
            error.setContentText("Ellenőrízd a következőket:\n- Valóban számot adtál-e meg\n- A szám amit beírtál kisebb mint 1000");
            error.showAndWait();
            
            return;
        }
        
        LocalTime time = LocalTime.parse(timeStr, DateTimeFormatter.ISO_LOCAL_TIME);
        
        LocalDateTime eventStart = LocalDateTime.of(date, time);
        
        ActiveEvent event = new ActiveEvent();
        
        int max = Integer.parseInt(maxppl);
        
        if(max < 1) {
            
            error.setTitle("Hiba");
            error.setHeaderText("A max létszám pozitív kell legyen!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        event.setEventName(name);
        event.setMaxPpl(max);
        event.setEventLocation(place);
        event.setEventBegins(eventStart);
        event.setEventEnds(eventStart.plusHours(1));
        event.setExpectedPpl(0);
        event.setOrganizerId(user.getUserId());
        
        try {
            
            rc.newEvent(event);
//            rc.signUpToEvent(event.getEventId(), user.getUserId());
            
            info.setTitle("Siker");
            info.setHeaderText("Az új esemény kiírásra került!");
            info.setContentText("Neve: " + event.getEventName() 
                            + "\nHelyszíne: " + event.getEventLocation() 
                            + "\nMax létszám: " + event.getMaxPpl());
            info.showAndWait();
            
            main.EventList(user);
            
        } catch(RuntimeException re) {
            
            error.setTitle("Hiba");
            error.setHeaderText(re.getMessage());
            error.setContentText("");
            error.showAndWait();
            
            
        } catch(Exception e) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Hiba történt a rendezvény kiírása során!");
            error.setContentText(e.getMessage());
            error.showAndWait();
            
        }
    }
    
    @FXML
    public void closeEvent() {
        
        ActiveEvent event = organizedTable.getSelectionModel().getSelectedItem();
        
        if(event == null) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem választottál rendezvényt!");
            error.setContentText("");
            error.showAndWait();
            
            return;
        }
        
        if(event.getEventBegins().isAfter(LocalDateTime.now())) {
            
            error.setTitle("Hiba");
            error.setHeaderText("Nem zárhatsz le olyan rendezvényt, ami el sem kezdődött!");
            error.setContentText("Ha le akarod mondani a rendezvényt, kattints a Lemond gombra!");
            error.showAndWait();
            
            return;
        }
        
        try {
            
            String result = rc.closeEvent(event);
            
            info.setTitle("Siker");
            info.setHeaderText(result);
            info.setContentText("");
            info.showAndWait();
            
            organizedTable.getItems().clear();
            
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

    public void setMainApp(Main main, User current) {
        this.main = main;
        this.user = current;
    }
    
    @FXML
    private void backHome() {
        main.UserMainPage(user);

    }

}
