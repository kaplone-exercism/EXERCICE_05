package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class Niveau extends AnchorPane {
	
	private String nom;
	private AnchorPane preview;
	private AnchorPane fullGame;
	private Personnage2D perso;
	private Goal2D goal2D;
	
	private Temps horloge;
    private Thread chronoThread;
	private Task<Object> chronoTask;

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public AnchorPane getPreview() {
		return preview;
	}
	public void setPreview(AnchorPane preview) {
		this.preview = preview;
	}
	public AnchorPane getFullGame() {
		return fullGame;
	}
	public void setFullGame(AnchorPane fullGame) {
		this.fullGame = fullGame;
	}
	public Personnage2D getPerso() {
		return perso;
	}
	public void setPerso(Personnage2D perso) {
		this.perso = perso;
	}
	public ObservableList<Mur2D> getListeDesMurs() {
		return listeDesMurs(fullGame.getChildrenUnmodifiable());
	}
    public Temps getHorloge() {
		return horloge;
	}
	public void setHorloge(Temps horloge) {
		this.horloge = horloge;
	}
	public Goal2D getGoal2D() {
		return goal2D;
	}
	public void setGoal2D(Goal2D goal2d) {
		goal2D = goal2d;
	}
	public Thread getChronoThread() {
		return chronoThread;
	}
	public void setChronoThread(Thread chronoThread) {
		this.chronoThread = chronoThread;
	}
	
	private ObservableList<Mur2D> listeDesMurs(ObservableList<Node> listeDesNoeuds){
		
		ObservableList<Mur2D> retour = FXCollections.observableArrayList();
		
		for (Node n : listeDesNoeuds){
			if (n instanceof Mur2D){
				retour.add((Mur2D)n);
			}
		}
		return retour;
	}
	public Task<Object> getChronoTask() {
		return chronoTask;
	}
	public void setChronoTask(Task<Object> task) {
		this.chronoTask = task;
	}
}
