package models;

import enums.Orientation;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
//import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.Settings;
import utils.Statiques;

public class Mur2D extends Rectangle{
	
	private final Orientation orientation;
	private final int epaisseur;
	private final int position;
	private final int debut;
	private final int fin;
	private final String nom;
	private Label infos;
	//private Rectangle2D rectangle2D;
	
	private double decalageH;
	private double decalageV;
	
	private DropShadow dropShadow;
	private Background background;
	
	public Mur2D(Orientation orientation, int epaisseur, int position, int debut, int fin, String nom) {
		
		super(orientation == Orientation.HORIZONTAL ? debut : position,
		      orientation == Orientation.HORIZONTAL ? position : debut, 
		      orientation == Orientation.HORIZONTAL ? fin - debut : epaisseur,
		      orientation == Orientation.HORIZONTAL ? epaisseur : fin - debut);
		
		this.orientation = orientation;
		this.epaisseur = epaisseur;
		this.position = position;
		this.debut = debut;
		this.fin = fin;
		this.nom = nom;
		this.setFill(Settings.getCouleurMurs());
		
//		if(horizontal()){
//			this.rectangle2D = new Rectangle2D(debut, position, fin - debut, epaisseur);
//		}
//		else if(vertical()){
//			this.rectangle2D = new Rectangle2D(position, debut, epaisseur, fin - debut);
//		}
	}

	@Override
	public String toString(){
		return String.format("%s :\nposition = %d\ndébut = %d\nfin = %d", this.nom, this.position, this.debut, this.fin);
	}
	
    public void afficheInfos(MouseEvent me, boolean aff){	
    	
    	AnchorPane root = Statiques.getRoot();
	
		if (aff && Settings.isAffInfosMurs()){
			
			infos = this.getInfos(me);

			this.toFront();
			this.setStrokeWidth(2);
			this.setStroke(Color.CORAL);
			
		}
		else {
			root.getChildren().remove(infos);
			this.setStrokeWidth(0);
		}
	}
    
    protected DropShadow getDropShadow(){
    	
    	if (dropShadow == null){

    		dropShadow = new DropShadow();
    		dropShadow.setRadius(5.0);
    		dropShadow.setOffsetX(3.0);
    		dropShadow.setOffsetY(3.0);	
    	}
        return dropShadow;	 	
    }
    
    protected Background getBackground(){
    	
    	if (background == null){

    		background = new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-5)));
    	}
        return background;	 	
    }
	
//	public boolean estEnContact(Rectangle2D r){		
//		return this.rectangle2D.contains(r);	
//	}
	public boolean estEnContact(Bounds r){		
		return this.getBoundsInLocal().contains(r);	
	}

    public Rectangle getRectangle(){
    	return this;
    }
    
//    public Rectangle2D getRectangle2D(){
//    	return this.rectangle2D;
//    }

	public Orientation getOrientation() {
		return orientation;
	}

	public int getEpaisseur() {
		return epaisseur;
	}

	public int getPosition() {
		return position;
	}

	public int getDebut() {
		return debut;
	}

	public int getFin() {
		return fin;
	}

	public boolean horizontal(){
		return this.orientation == Orientation.HORIZONTAL;
	}
	
	public boolean vertical(){
		return this.orientation == Orientation.VERTICAL;
	}

	public Label getInfos(MouseEvent me) {
		
		AnchorPane root = Statiques.getRoot();
		
		if (infos == null){
			infos = new Label(this.toString());
			infos.setBackground(getBackground());
			infos.setEffect(getDropShadow());
			infos.setStyle("-fx-border-color: grey; -fx-border-width: 1; -fx-border-style: solid inside; -fx-border-insets: -5;");
			
			if(! root.getChildren().contains(infos)){
				root.getChildren().add(infos);	
			}
		}
		
		decalageH = me.getSceneX() < (root.getWidth() - 120) ? 30 : -120;
		decalageV = me.getSceneY() < (root.getHeight() - 80) ? 20 : -80;
		
		infos.setLayoutX(me.getSceneX() + decalageH);
		infos.setLayoutY(me.getSceneY() + decalageV);
		
		return infos;
	}

	public void setInfos(Label infos) {
		this.infos = infos;
	}	
	
	
}
