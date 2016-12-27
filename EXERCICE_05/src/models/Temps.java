package models;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import utils.Contexte;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Temps {
	
	private int minutes;
	private int minutesTopJoueur;
	private int minutesTopMonde;
	private int secondes;
	private int secondesTopJoueur;
	private int secondesTopMonde;
	private int dixiemes;
	private int dixiemesTopJoueur;
	private int dixiemesTopMonde;
	
	private HBox h1;
	private Label h11c1;
	
	public Temps(int minutesTopJoueur, int minutesTopMonde, int secondesTopJoueur,
			int secondesTopMonde, int dixiemesTopJoueur, int dixiemesTopMonde) {
		
		this.minutes = 0;
		this.minutesTopJoueur = minutesTopJoueur;
		this.minutesTopMonde = minutesTopMonde;
		this.secondes = 0;
		this.secondesTopJoueur = secondesTopJoueur;
		this.secondesTopMonde = secondesTopMonde;
		this.dixiemes = 0;
		this.dixiemesTopJoueur = dixiemesTopJoueur;
		this.dixiemesTopMonde = dixiemesTopMonde;
		
		h1 = new HBox();
		h1.setAlignment(Pos.CENTER);
		h1.setSpacing(15);
		h1.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-5))));
		VBox v1 = new VBox();
		v1.setAlignment(Pos.CENTER);
		v1.setSpacing(5);
		v1.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), new Insets(-5))));
		VBox v2 = new VBox();
		v2.setSpacing(5);
		v2.setAlignment(Pos.CENTER);
		v2.setBackground(new Background(new BackgroundFill(Color.DARKGREY, new CornerRadii(5), new Insets(-5))));
		Label l1 = new Label("Temps partie"); 
		l1.setStyle("-fx-font-size: 12pt;" +
			    "-fx-text-fill: rgb(40, 239, 4);");
		Label l2 = new Label("Temps Maximal"); 
		HBox h11 = new HBox();
		h11.setAlignment(Pos.CENTER);
		h11c1 = new Label("00:00.0"); 
		h11c1.setStyle("-fx-font-size: 15pt;" +
			    "-fx-text-fill: rgb(40, 239, 4);" +
	            "-fx-border-color: rgb(40, 239, 4);" +
			    "-fx-border-radius: 5;" +
	            "-fx-padding: 3 6 6 6;");
		HBox h21 = new HBox();
		h21.setAlignment(Pos.CENTER);
		Label h21c1 = new Label(String.format("%02d:%02d.%d", minutesTopMonde, secondesTopMonde, dixiemesTopMonde)); 
		h21c1.setStyle("-fx-font-size: 15pt;" +
			    "-fx-text-fill: rgb(49, 89, 23);" +
	            "-fx-border-color: rgb(49, 89, 23);" +
			    "-fx-border-radius: 5;" +
	            "-fx-padding: 3 6 6 6;");
		
		h11.getChildren().add(h11c1);
		h21.getChildren().add(h21c1);
		
		v1.getChildren().addAll(l1, h11);
		v2.getChildren().addAll(l2, h21);
		
		h1.getChildren().addAll(v1, v2);
		h1.setLayoutX(760);
		h1.setLayoutY(15);
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getMinutesTopJoueur() {
		return minutesTopJoueur;
	}
	public void setMinutesTopJoueur(int minutesTopJoueur) {
		this.minutesTopJoueur = minutesTopJoueur;
	}
	public int getMinutesTopMonde() {
		return minutesTopMonde;
	}
	public void setMinutesTopMonde(int minutesTopMonde) {
		this.minutesTopMonde = minutesTopMonde;
	}
	public int getSecondes() {
		return secondes;
	}
	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}
	public int getSecondesTopJoueur() {
		return secondesTopJoueur;
	}
	public void setSecondesTopJoueur(int secondesTopJoueur) {
		this.secondesTopJoueur = secondesTopJoueur;
	}
	public int getSecondesTopMonde() {
		return secondesTopMonde;
	}
	public void setSecondesTopMonde(int secondesTopMonde) {
		this.secondesTopMonde = secondesTopMonde;
	}
	public int getDixiemes() {
		return dixiemes;
	}
	public void setDixiemes(int dixiemes) {
		this.dixiemes = dixiemes;
	}
	public int getDixiemesTopJoueur() {
		return dixiemesTopJoueur;
	}
	public void setDixiemesTopJoueur(int dixiemesTopJoueur) {
		this.dixiemesTopJoueur = dixiemesTopJoueur;
	}
	public int getDixiemesTopMonde() {
		return dixiemesTopMonde;
	}
	public void setDixiemesTopMonde(int dixiemesTopMonde) {
		this.dixiemesTopMonde = dixiemesTopMonde;
	}
	public HBox getH1() {
		return h1;
	}
	public void setH1(HBox h1) {
		this.h1 = h1;
	}
	
	public Label getH11c1() {
		return h11c1;
	}
	public void setH11c1(Label h11c1) {
		this.h11c1 = h11c1;
	}
}
