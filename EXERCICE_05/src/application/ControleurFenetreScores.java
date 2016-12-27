package application;

import java.util.Date;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import utils.Contexte;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.*;

public class ControleurFenetreScores extends AnchorPane{
	
	private int score;
	private Date temps;
	private int touches;
	private int deplacements;
	
	public ControleurFenetreScores(int score, Date temps, int touches, int deplacements) {
		this.score = score;
		this.temps = temps;
		this.touches = touches;
		this.deplacements = deplacements;
	}
	
	public ControleurFenetreScores() {

		Stage stageScores = new Stage();

		stageScores.initStyle(StageStyle.UNDECORATED);
		Label bravo = new Label("BRAVO, Practice terminé !");
		Label votreTemps = new Label(Contexte.getNiveau().getChronoTask().messageProperty().get());
		votreTemps.setStyle("-fx-font-size: 15pt;" +
			    "-fx-text-fill: rgb(30, 40, 4);");
		Button fermer = new Button ("ok");
		VBox vb = new VBox();
		vb.setAlignment(Pos.CENTER);
		VBox.setMargin(bravo, new Insets(10, 20, 10, 20));
		VBox.setMargin(fermer, new Insets(20));
		vb.setSpacing(20);
		
		this.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, new CornerRadii(10) ,Insets.EMPTY)));
		vb.getChildren().addAll(bravo,votreTemps, fermer);
		this.getChildren().add(vb);	
		
		fermer.setOnAction(a -> stageScores.fireEvent(new WindowEvent(stageScores, WindowEvent.WINDOW_CLOSE_REQUEST)));
		
		Scene SceneScores = new Scene(this, 200, 150);
		stageScores.setScene(SceneScores);
		stageScores.sizeToScene();
		stageScores.show();	
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getTemps() {
		return temps;
	}

	public void setTemps(Date temps) {
		this.temps = temps;
	}

	public int getTouches() {
		return touches;
	}

	public void setTouches(int touches) {
		this.touches = touches;
	}

	public int getDeplacements() {
		return deplacements;
	}

	public void setDeplacements(int deplacements) {
		this.deplacements = deplacements;
	}
	
	
	
	

}
