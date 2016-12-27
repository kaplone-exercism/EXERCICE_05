package application;

import enums.Sens;
import javafx.animation.Timeline;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Goal2D;
import models.Niveau;
import models.Personnage2D;
import utils.Contexte;
import utils.Statiques;

public class ControleurClavier {
	
	private static Double deltaChange = 0.999;
	
	static final long repereTemporel = 25;
	static long ralentissement = repereTemporel;
	
	static final Timeline timeline = new Timeline();
	private static boolean tickable = false;
	private static boolean moveable = false;
	
	private static AnchorPane root;
	private static Scene scene;
	private static Main_Exercice_05 main;
	private static Stage stage;
	private static Personnage2D perso;
	private static Goal2D goal2D;
	private static Niveau niveau;
	private static KeyEvent e;
	
	static public void init(Niveau niveau_){
		
		niveau = niveau_;
		
		root = Statiques.getRoot();
        scene = Statiques.getScene();
        main = Statiques.getMain();
        stage = Statiques.getStage();
		
		perso = niveau.getPerso();
		goal2D = niveau.getGoal2D();
		
		niveau.getHorloge().getH11c1().textProperty().unbind();
		niveau.getHorloge().getH11c1().textProperty().bind(niveau.getChronoTask().messageProperty());
		
		Thread t = new Thread(niveau.getChronoTask());
		niveau.setChronoThread(t);
		t.start();
		
		stage.setOnCloseRequest(a -> niveau.getChronoTask().cancel());

		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent e_) {
				moveable = true;
				e = e_;
			}

		});
		
        scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent e) {	
				e = null;
				tickable = false;
				moveable = false;
				ralentissement = repereTemporel;
				}

		});
	}
	
	static public void gerer_keys(){
		
		if(moveable){
			if (ralentissement > 14){
				tickable = ralentissement % 5 == 0;
			}
			else if (ralentissement > 7){
				tickable = ralentissement % 2 == 0;
			}
			else tickable = true;
			
			//System.out.println(ralentissement + " -> " + tickable);
			
			ralentissement --;
		}

	
		if (e != null && tickable){
			
			KeyCode kc = e.getCode();
			
			if(e != null && e.isShortcutDown() && e.isShiftDown()){
				perso.montrerLesFleches(true);
			}
			
			else if(e != null && e.isShortcutDown()){
				perso.montrerLesFleches(false);
			}
			else {
				perso.cacherLesFleches();
			}
			
			switch (kc) {
			case Z:
			case UP:   if(e != null && e.isShortcutDown()){
				          if (e != null && e.isShiftDown()){
				        	  perso.deformationBas(deltaChange);
				          }
				          else {
				        	  perso.deformationHaut(1/deltaChange);
				          }
				          perso.getFleches().get(Sens.HAUT).activation();
			            }
			            else {
                            
			            	// Animation pour des mouvements plus fluides
			            	// mais hélas, pas de détection des contacts
			            	
//			            	KeyValue xValueFinale   = new KeyValue(perso.yProperty(), perso.getY() - (1 + bonus));
//			            	
//			            	timeline.getKeyFrames().clear();
//			            	timeline.getKeyFrames().add(new KeyFrame(new Duration(30), xValueFinale));
//			            	timeline.play();
			            	
			            	perso.deplacement(0, -1);
			            }
				break;
			case S :
			case DOWN: if(e != null && e.isShortcutDown()){
		          		  if (e != null && e.isShiftDown()){
		          			  perso.deformationHaut(deltaChange);
			              }
			              else {
			            	  perso.deformationBas(1/deltaChange);
			              }
		          		  perso.getFleches().get(Sens.BAS).activation();
		                }
		                else {
		                	perso.deplacement(0, 1);
		                }
			    break;
			case Q :
			case LEFT: if(e != null && e.isShortcutDown()){
		          		  if (e != null && e.isShiftDown()){
		        	          perso.deformationDroite(deltaChange);
			              }
			              else {
			        	      perso.deformationGauche(1/deltaChange);
			              }
		                  perso.getFleches().get(Sens.GAUCHE).activation();
		              }
		              else {
		            	  perso.deplacement(-1, 0);
		              }
			    break;
			case D :
			case RIGHT:  if(e != null && e.isShortcutDown()){
		                    if (e != null && e.isShiftDown()){
		        	           perso.deformationGauche(deltaChange);
			                }
			                else {
			        	       perso.deformationDroite(1/deltaChange);
			                }
		                    perso.getFleches().get(Sens.DROITE).activation();
		                 }
		                 else {
		            	    perso.deplacement(1, 0);
		                 }
			    break;

			case NUMPAD9 : perso.setFill(Color.BLACK);
	        break;
			case NUMPAD8 : perso.setFill(Color.CHOCOLATE);
	        break;
			case NUMPAD7 : perso.setFill(Color.RED);
	        break;
			case NUMPAD6 : perso.setFill(Color.BLUE);
	        break;
			case NUMPAD5 : perso.setFill(Color.MAROON);
	        break;
			case NUMPAD4 : perso.setFill(Color.GREEN);
	        break;
			case NUMPAD3 : perso.setFill(Color.CORNFLOWERBLUE);
	        break;
			case NUMPAD2 : perso.setFill(Color.BLUEVIOLET);
	        break;
			case NUMPAD1 : perso.setFill(Color.YELLOW);
	        break;
			case ESCAPE: {
				
				niveau.getChronoThread().interrupt();
				
				Contexte.setPerso(perso);
				Contexte.setNiveau(niveau);
				Contexte.setGoal2D(goal2D);
				
				scene.setRoot(new AnchorPane());
				
				Statiques.getTimer().cancel();
				
				e = null;

				main.retourMenu();
			}
			break;
			default :
			}
			
		    if (perso.getRectangle2D().intersects(goal2D.getRectangle2D())){
				
	        	goal2D.setImage(new Image("goal_vert.png"));
				
	        	main.inactive();
	        	main.afficherSores();
			}
		}
		
        
	}
}
