package application;

import java.util.Optional;

import enums.Sens;
import javafx.event.Event;
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
import models.Sauvegarde;
import models.Statiques;

public class ControleurClavier {
	
	private static Double deltaChange = 0.999;
	
	private static Optional<KeyEvent> e = Optional.empty();
	
	static public void gerer_keys(Niveau niveau){
		
		AnchorPane root = Statiques.getRoot();
        Scene scene = Statiques.getScene();
        Main_Exercice_05 main = Statiques.getMain();
        Stage stage = Statiques.getStage();
		
		Personnage2D perso = niveau.getPerso();
		Goal2D goal2D = niveau.getGoal2D();
		
		if (niveau.getChronoThread() == null){
			
			niveau.getHorloge().getH11c1().textProperty().unbind();
			niveau.getHorloge().getH11c1().textProperty().bind(niveau.getChronoTask().messageProperty());
			
			Thread t = new Thread(niveau.getChronoTask());
			niveau.setChronoThread(t);
			t.start();
			
			stage.setOnCloseRequest(a -> niveau.getChronoTask().cancel());	
		}
		
        if (perso.getRectangle2D().intersects(goal2D.getRectangle2D())){
			
        	goal2D.setImage(new Image("goal_vert.png"));
			
        	main.inactive();
        	main.afficherSores();
		}
		
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {	
				e = Optional.of((KeyEvent) event);
				}

		});
		
        scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {	
				e = Optional.empty();
				}

		});
		
		if (e.isPresent()){

			KeyCode kc = e.get().getCode();
			
			if(e.isPresent() && e.get().isShortcutDown() && e.get().isShiftDown()){
				perso.montrerLesFleches(true);
			}
			
			else if(e.isPresent() && e.get().isShortcutDown()){
				perso.montrerLesFleches(false);
			}
			else {
				perso.cacherLesFleches();
			}
			
			switch (kc) {
			case Z:
			case UP:   if(e.isPresent() && e.get().isShortcutDown()){
				          if (e.isPresent() && e.get().isShiftDown()){
				        	  perso.deformationBas(deltaChange);
				          }
				          else {
				        	  perso.deformationHaut(1/deltaChange);
				          }
				          perso.getFleches().get(Sens.HAUT).activation();
			            }
			            else {
			            	perso.deplacement(0, -1);
			            }
				break;
			case S :
			case DOWN: if(e.isPresent() && e.get().isShortcutDown()){
		          		  if (e.isPresent() && e.get().isShiftDown()){
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
			case LEFT: if(e.isPresent() && e.get().isShortcutDown()){
		          		  if (e.isPresent() && e.get().isShiftDown()){
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
			case RIGHT:  if(e.isPresent() && e.get().isShortcutDown()){
		                    if (e.isPresent() && e.get().isShiftDown()){
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
				
				Sauvegarde.setPerso(perso);
				Sauvegarde.setNiveau(niveau);
				Sauvegarde.setGoal2D(goal2D);
				
				scene.setRoot(new AnchorPane());
				
				Statiques.getTimer().cancel();
				e = Optional.empty();

				main.retourMenu();
			}
			break;
			default :
			}	
		}
		
		else {
			perso.deplacement(0, 0);
		}
	}
}
