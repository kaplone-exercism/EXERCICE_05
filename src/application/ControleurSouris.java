package application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import models.Personnage2D;
import utils.Settings;
import utils.Statiques;

public class ControleurSouris {
	
	private static DropShadow dropShadow;
	private static Background background;
	private static Label infos;
	
    static public void gerer_sourisBouge(MouseEvent me, boolean aff){
    	
    	infos = getInfos(me);
    	AnchorPane root = Statiques.getRoot();
    	
    	infos.setBackground(getBackground());
    	if(! root.getChildren().contains(infos)){
			root.getChildren().add(infos);	
		}

    	infos.setEffect(getDropShadow());
    	infos.setStyle("-fx-border-color: grey; -fx-border-width: 1; -fx-border-style: solid inside; -fx-border-insets: -5;");
		
    	infos.setText(String.format("X=%d\nY=%d", (int)me.getSceneX(), (int)me.getSceneY()));
		
    	infos.setLayoutX(me.getSceneX() -55);
    	infos.setLayoutY(me.getSceneY() -45);
		
		infos.setVisible(aff && Settings.isAffPositionSouris());
		infos.toFront();		
	}
    

	static public Personnage2D gerer_clicks(Personnage2D r, MouseEvent e){
		
		r.setX(e.getSceneX());
		r.setY(e.getSceneY());
		
		return r;
		
	}
	
    protected static DropShadow getDropShadow(){
    	
    	if (dropShadow == null){

    		dropShadow = new DropShadow();
    		dropShadow.setRadius(5.0);
    		dropShadow.setOffsetX(3.0);
    		dropShadow.setOffsetY(3.0);	
    	}
        return dropShadow;	 	
    }
    
    protected static Background getBackground(){
    	
    	if (background == null){

    		background = new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-5)));
    	}
        return background;	 	
    }
    
    public static Label getInfos(MouseEvent me) {
		
		if (infos == null){
			infos = new Label();
		}
		return infos;
	}
}
