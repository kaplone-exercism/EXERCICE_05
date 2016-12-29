package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
//import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import enums.Sens;


public class Personnage2D extends Rectangle {

	//private Rectangle2D rectangle2D;
	private Niveau niveau;
	private final double surface;
	private Map<Sens, Fleche> fleches;
	
	private double undoRectangleX;
	private double undoRectangleY;
	private double undoRectangleWidth;
	private double undoRectangleHeight;
	
	
	public Personnage2D(double x, double y, double width, double height, Paint fill, Niveau niveau) {
		super(x, y, width, height);
		
		this.setFill(fill);
//		this.setStroke(Color.GREEN);
//		this.setStrokeWidth(1);
		//this.rectangle2D = new Rectangle2D(x, y, width, height);
		this.surface = width * height;
		
		fleches = new HashMap<Sens, Fleche>();
		ajoutFleches();
		
		this.niveau = niveau;
	}
	
	public void ajoutFleches(){
		fleches.put(Sens.BAS,
				    new Fleche(
				    		new Image("fleche_bas_20.png"),
                            this,
                            Sens.BAS));
		
		fleches.put(Sens.HAUT, new Fleche(new Image("fleche_haut_20.png"), this, Sens.HAUT));
		fleches.put(Sens.DROITE, new Fleche(new Image("fleche_droite_20.png"), this, Sens.DROITE));
		fleches.put(Sens.GAUCHE, new Fleche(new Image("fleche_gauche_20.png"), this, Sens.GAUCHE));
		
		for (Fleche f : fleches.values()){
			f.bindAttaches();
		}
	}
	
	public void deplacement(double x, double y){
		
		deplacement((int) x, (int) y);
	}
	
	public void deplacement(int x, int y){
		
		while (x != 0 || y != 0){
			
			if (x > 0 && !enContact()){
				this.setX(this.getX() + 1);
				if(enContact()){
					this.setX(this.getX() - 1);
					x = 0;
				}
				else {
				    x--;	
				}
			}
			else if (x < 0){
				this.setX(this.getX() - 1);
				if(enContact()){
					this.setX(this.getX() + 1);
					x = 0;
				}
				else {
					x++;	
				}
						
			}
			
			if (y > 0 && !enContact()){
				this.setY(this.getY() + 1);
				if(enContact()){
					this.setY(this.getY() - 1);
					y = 0;
				}
				else {
				    y--;
				}
			}
			else if (y < 0 && !enContact()){
				this.setY(this.getY() - 1);
				if(enContact()){
					this.setY(this.getY() + 1);
					y = 0;
				}
				else {
					y++;
				}
			}
		}		
	}
	
	public boolean sansContact(){
		
		for (Mur2D m2D : niveau.getListeDesMurs()){			
			
			if (m2D.getBoundsInParent().intersects(this.getX() +1, this.getY() +1, this.getWidth() -2, this.getHeight() -2)){
				return false;
			}
		}
		return true;		
	}
	
    public boolean enContact(){
		return ! sansContact();	
	}
	
    public void deformationGauche(double ratio){
    	
    	double widthSave = this.getWidth();
    	double delta = this.widthProperty().multiply(ratio).doubleValue() - widthSave;
    	
    	if(delta > 0){
    		while (delta > 0 && deformationGaucheUnitaire(1)){
    			delta--;
    		}
    	}
    	else {
    		while (delta < 0 && deformationGaucheUnitaire(-1)){
    			delta++;
    		}
    	}
    }
    
    public boolean deformationGaucheUnitaire(int deformation){
    	
    	makeUndoRectangle();
    	
    	double widthSave = this.getWidth();
 	
    	this.setWidth(Math.round(this.widthProperty().add(deformation).doubleValue()));
    	if (this.getWidth() < 20){
    		this.setWidth(20);
    		return false;
    	}
    	
    	this.setHeight(Math.round(surface / this.getWidth()));
    	if(this.getHeight() < 20){
    		this.setHeight(20);
    		this.setWidth(Math.round(surface / this.getHeight()));
    		return false;
    	}
    	
    	if (enContact()){
        	readUndoRectangle();
        	return false;
        }
    	
    	deplacement(- deformation, 0);
    		
        if (enContact()){
        	readUndoRectangle();
        	return false;
        }	
    	return true;
    }
    
    public void deformationDroite(double ratio){
    	
    	double widthSave = this.getWidth();
    	double delta = this.widthProperty().multiply(ratio).doubleValue() - widthSave;
    	
    	if(delta > 0){
    		while (delta > 0 && deformationDroiteUnitaire(1)){
    			delta--;
    		}
    	}
    	else {
    		while (delta < 0 && deformationDroiteUnitaire(-1)){
    			delta++;
    		}
    	}
    }
    public boolean deformationDroiteUnitaire(int deformation){
    	
    	makeUndoRectangle();
       	
    	this.setWidth(Math.round(this.widthProperty().add(deformation).doubleValue())); 
    	if (this.getWidth() < 20){
    		this.setWidth(20);	
    		return false;
    	}
    	
    	this.setHeight(Math.round(surface / this.getWidth()));
    	if(this.getHeight() < 20){
    		this.setHeight(20);
    		this.setWidth(Math.round(surface / this.getHeight()));
    		return false;
    	}
	
        if (enContact()){
        	readUndoRectangle();
        	return false;
        }	
        return true;
    }
    
    public void deformationHaut(double ratio){
    	
    	double heightSave = this.getHeight();
    	double delta = this.heightProperty().multiply(ratio).doubleValue() - heightSave;
	
    	if(delta > 0){
    		while (delta >= 0 && deformationHautUnitaire(1)){
    			delta--;
    		}
    	}
    	else {
    		while (delta <= 0 && deformationHautUnitaire(-1)){
    			delta++;
    		}
    	}
	}
    
    public boolean deformationHautUnitaire(int deformation){
    	
    	makeUndoRectangle();
    	
    	double heightSave = this.getHeight();
    	
    	this.setHeight(Math.round(this.heightProperty().add(deformation).doubleValue()));
    	if (this.getHeight() < 20){
    		this.setHeight(20);	
    		return false;
    	}

    	this.setWidth(Math.round(surface / this.getHeight()));
    	if(this.getWidth() < 20){
    		this.setWidth(20);
    		this.setHeight(Math.round(surface / this.getWidth()));
    		return false;
    	}
    	
    	if (enContact()){
        	readUndoRectangle();
        	return false;
        }
	    
    	deplacement(0, heightSave -this.getHeight());

        if (enContact()){
        	readUndoRectangle();
        	return false;
        }	
        return true;
    }
    
    public void deformationBas(double ratio){
        
    	double heightSave = this.getHeight();
    	double delta = this.heightProperty().multiply(ratio).doubleValue() - heightSave;
    	
    	if(delta > 0){
    		while (delta > 0 && deformationBasUnitaire(1)){
    			delta--;
    		}
    	}
    	else {
    		while (delta < 0 && deformationBasUnitaire(-1)){
    			delta++;
    		}
    	}
	}
    
    public boolean deformationBasUnitaire(int deformation){
    	
    	makeUndoRectangle();
    	
    	this.setHeight(Math.round(this.heightProperty().add(deformation).doubleValue()));
    	if (this.getHeight() < 20){
    		this.setHeight(20);	
    		return false;
    	}

    	this.setWidth(Math.round(surface / this.getHeight()));
    	if(this.getWidth() < 20){
    		this.setWidth(20);
    		this.setHeight(Math.round(surface / this.getWidth()));
    		return false;
    	}

        if (enContact()){
        	readUndoRectangle();
        	return false;
        }	
    	return true;
    }
    
    public void montrerLesFleches(boolean in){
    	
    	if (in){
    		for (Fleche f : fleches.values()){
    			f.setOut(false);
        		f.setVisible(true);
        		//f.toFront();
        	}
    	}
    	else {
    		for (Fleche f : fleches.values()){
    			f.setOut(true);
        		f.setVisible(true);
        		//f.toFront();
        	}
    	}
    }
    
    public void cacherLesFleches(){
    	for (Fleche f : fleches.values()){
    		f.setVisible(false);
    	}
    }

	public Map<Sens, Fleche> getFleches() {
		return fleches;
	}	
	
	private void makeUndoRectangle(){
		
		undoRectangleX = this.getX();
		undoRectangleY = this.getY();
		undoRectangleWidth = this.getWidth();
		undoRectangleHeight = this.getHeight();
	}
	
	private void readUndoRectangle(){

		this.setX(undoRectangleX);
		this.setY(undoRectangleY);
		this.setWidth(undoRectangleWidth);
		this.setHeight(undoRectangleHeight);
	}
}
