package models;

import javafx.scene.layout.AnchorPane;

public class Sauvegarde {
	
	private static Niveau niveau;
	private static Personnage2D perso;
	private static Goal2D goal2D;
	
	public static Niveau getNiveau() {
		return niveau;
	}
	public static void setNiveau(Niveau niveau_) {
		niveau = niveau_;
	}
	public static Personnage2D getPerso() {
		return perso;
	}
	public static void setPerso(Personnage2D perso_) {
		perso = perso_;
	}
	public static Goal2D getGoal2D() {
		return goal2D;
	}
	public static void setGoal2D(Goal2D goal2d) {
		goal2D = goal2d;
	}
}
