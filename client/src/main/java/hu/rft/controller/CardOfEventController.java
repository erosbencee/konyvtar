package hu.rft.controller;



import java.io.IOException;

import hu.rft.konyvtar.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class CardOfEventController {
	private Main main;
	
	 public void setMainApp(Main main) throws IOException {
	      this.main = main;

	      
	  }
	
	@FXML
	private Label cardTitle;
	
	@FXML
	private Label cardNote;
	
	@FXML
	private Label cardNote2;
	
	@FXML
	private Label day;
	
	@FXML
	private Label month;
	
	@FXML
	private Label hour;
	
	@FXML
	private ImageView cardCover;

	/**
	 * @return the cardCover
	 */
	public ImageView getCardCover() {
		return cardCover;
	}

	/**
	 * @param cardCover the cardCover to set
	 */
	public void setCardCover(ImageView cardCover) {
		this.cardCover = cardCover;
	}

	/**
	 * @return the main
	 */
	public Main getMain() {
		return main;
	}

	/**
	 * @param main the main to set
	 */
	public void setMain(Main main) {
		this.main = main;
	}

	/**
	 * @return the cardTitle
	 */
	public Label getCardTitle() {
		return cardTitle;
	}

	/**
	 * @param cardTitle the cardTitle to set
	 */
	public void setCardTitle(Label cardTitle) {
		this.cardTitle = cardTitle;
	}

	/**
	 * @return the cardNote
	 */
	public Label getCardNote() {
		return cardNote;
	}

	/**
	 * @param cardNote the cardNote to set
	 */
	public void setCardNote(Label cardNote) {
		this.cardNote = cardNote;
	}

	/**
	 * @return the day
	 */
	public Label getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(Label day) {
		this.day = day;
	}

	/**
	 * @return the month
	 */
	public Label getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(Label month) {
		this.month = month;
	}

	/**
	 * @return the hour
	 */
	public Label getHour() {
		return hour;
	}

	/**
	 * @param hour the hour to set
	 */
	public void setHour(Label hour) {
		this.hour = hour;
	}

	public Label getCardNote2() {
		return cardNote2;
	}

	public void setCardNote2(Label cardNote2) {
		this.cardNote2 = cardNote2;
	}
	
	

	
}
