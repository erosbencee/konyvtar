package hu.rft.controller;

import java.io.File;
import java.io.IOException;

import hu.rft.konyvtar.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class EventListController {
	private Main main;
	  
	  @FXML
	  private AnchorPane firstEvent;
	  
	  @FXML
	  private AnchorPane secondEvent;
	  
	  @FXML
	  private AnchorPane thirdEvent;
	  
	  private CardOfEventController card1 = new CardOfEventController();
	
	  public void setMainApp(Main main) throws IOException {
	      this.main = main;
	      itemMembres(main,firstEvent,"/images/event_piano.jpg", "Zongora koncert", "Előadó: Trab Antal","2017.november 30-án 15 órától","30","november","15:00");
	      itemMembres(main,secondEvent,"/images/eventDebrecen.jpg", "Debrecen múltja - előadás", "Előadó: Wincs Eszter","2017.december 6-án 10 órától","6","december","10:00");
	     itemMembres(main,thirdEvent,"/images/event_christmas.jpg", "Karácsonyi könyvbemutató", "Előadó: Kelep Elek és Kopp Anna","2017.december 16-án 11 órától","16","december","11:00");
	 
	      
	  }
	  





  
	private void itemMembres(Main main,AnchorPane anchorPane, String cardCoverText, String cardTitleText, String cardNoteText, String cardNoteText2, String dayText, String monthText, String hourText) throws IOException{

	try {
			
		    FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/FXMLs/CardOfEvent.fxml"));
            AnchorPane Overview = (AnchorPane) loader.load();

  
            anchorPane.getChildren().setAll(Overview);
           CardOfEventController controller = loader.getController();
           controller.getCardTitle().setText(cardTitleText);
           controller.getCardNote().setText(cardNoteText);
           controller.getCardNote2().setText(cardNoteText2);
           controller.getDay().setText(dayText);
           controller.getMonth().setText(monthText);
           controller.getHour().setText(hourText);
           Image img = new Image(getClass().getResource(cardCoverText).toExternalForm());
           controller.getCardCover().setImage(img);
           controller.setMainApp(main);
            
        
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	  
	  }
	

	  
	  
}