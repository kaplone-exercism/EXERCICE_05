package models;

import enums.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import utils.Settings;

public class Mur2D extends Rectangle{
	
	private final Orientation orientation;
	private final int epaisseur;
	private final int position;
	private final int debut;
	private final int fin;
	private final String nom;
	private Label infos;
	private Rectangle2D rectangle2D;
	
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
		
		if(horizontal()){
			this.rectangle2D = new Rectangle2D(debut, position, fin - debut, epaisseur);
		}
		else if(vertical()){
			this.rectangle2D = new Rectangle2D(position, debut, epaisseur, fin - debut);
		}
	}

	@Override
	public String toString(){
		return String.format("%s :\nposition = %d\ndébut = %d\nfin = %d", this.nom, this.position, this.debut, this.fin);
	}
	
	public boolean estEnContact(Rectangle2D r){		
		return this.rectangle2D.contains(r);	
	}

    public Rectangle getRectangle(){
    	return this;
    }
    
    public Rectangle2D getRectangle2D(){
    	return this.rectangle2D;
    }

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
		return new Label(this.toString());
	}

	public void setInfos(Label infos) {
		this.infos = infos;
	}	
	
	
}
