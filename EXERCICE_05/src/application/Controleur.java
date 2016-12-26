package application;

import javafx.scene.paint.Color;
import models.Niveau;
import models.Personnage2D;

public class Controleur {
	
	private Personnage2D perso;
	

	public Controleur(){
	}
	
	public Personnage2D init(int x, int y, int width, int height, Color couleur, Niveau niveau){
		
		perso = new Personnage2D(x, y, width, height, couleur, niveau);
		perso.ajoutFleches();
		
		return perso;

	}

	public Personnage2D getR0() {
		return perso;
	}

	public void setR0(Personnage2D r0) {
		this.perso = r0;
	}
}
