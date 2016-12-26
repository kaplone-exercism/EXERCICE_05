package application;

import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import models.Personnage2D;
import models.Settings;
import models.Statiques;

public class ControleurSouris {
	
    static public void gerer_sourisBouge(MouseEvent me, boolean aff){
    	
		if (!Statiques.getMain().isInfosPositionExist()){
			
			DropShadow dropShadow = new DropShadow();
			dropShadow.setRadius(5.0);
			dropShadow.setOffsetX(3.0);
			dropShadow.setOffsetY(3.0);
			dropShadow.setColor(Color.color(0.5, 0.5, 0.5));

			Statiques.getMain().getInfosPosition().setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-5))));
			Statiques.getRoot().getChildren().add(Statiques.getMain().getInfosPosition());

			Statiques.getMain().getInfosPosition().setEffect(dropShadow);
			Statiques.getMain().getInfosPosition().setStyle("-fx-border-color: grey; -fx-border-width: 1; -fx-border-style: solid inside; -fx-border-insets: -5;");
			
			Statiques.getMain().setInfosPositionExist(true);
		}
		
		Statiques.getMain().getInfosPosition().setText(String.format("X=%d\nY=%d", (int)me.getSceneX(), (int)me.getSceneY()));
		
		Statiques.getMain().getInfosPosition().setLayoutX(me.getSceneX() -55);
		Statiques.getMain().getInfosPosition().setLayoutY(me.getSceneY() -45);
		
		Statiques.getMain().getInfosPosition().setVisible(aff && Settings.isAffPositionSouris());
		Statiques.getMain().getInfosPosition().toFront();		
	}
    

	static public Personnage2D gerer_clicks(Personnage2D r, MouseEvent e){
		
		r.setX(e.getSceneX());
		r.setY(e.getSceneY());
		
		return r;
		
	}
}
