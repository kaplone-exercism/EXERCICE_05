package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.stage.StagePeerListener;

import enums.Sens;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import models.Fleche;
import models.Goal2D;
import models.Mur2D;
import models.Niveau;
import models.Personnage2D;
import models.Sauvegarde;
import models.Settings;
import models.Temps;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;

public class Main_Exercice_04 extends Application implements Initializable{
		
	int bonus = 0;
	Rectangle  p1;

	@FXML
	ImageView maze;
	@FXML
	ImageView settings;
	@FXML
	ImageView launch;
	@FXML
	ImageView exit;
	
	@FXML
	AnchorPane root;

	Scene scene;
	
	Stage stagePrincipal;
	
	Thread t_launch;
	Thread tm_launch;
	Thread ts_launch;
	Thread te_launch;
	
	Double deltaChange = 0.95;
	
	Label infos;
	Label infosPosition = new Label();
	boolean infosPositionExist = false;
	
	private AnchorPane fen_scores = null;
	
	@Override
	public void start(Stage primaryStage) {
		
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		
		try {
			
			scene = new Scene((Parent) JfxUtils.loadFxml("menu2.fxml"), 600, 400);

			primaryStage.setScene(scene);
			primaryStage.setWidth(600);
			primaryStage.setHeight(435);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Personnage2D gerer_keys(KeyEvent e, AnchorPane root, Stage importedstage, Niveau niveau){
		
		Personnage2D perso = niveau.getPerso();
		Goal2D goal2D = niveau.getGoal2D();
		
		if (niveau.getChronoThread() == null){
			
			niveau.getHorloge().getH11c1().textProperty().unbind();
			niveau.getHorloge().getH11c1().textProperty().bind(niveau.getChronoTask().messageProperty());
			
			Thread t = new Thread(niveau.getChronoTask());
			niveau.setChronoThread(t);
			t.start();
			
			importedstage.setOnCloseRequest(a -> niveau.getChronoTask().cancel());
			
			
		}
		
        if (perso.getRectangle2D().intersects(goal2D.getRectangle2D())){
			
        	goal2D.setImage(new Image("goal_vert.png"));
			
			inactive(root);
			afficherSores(root);
		}
			
		KeyCode kc = e.getCode();
		
		if(e.isShortcutDown() && e.isShiftDown()){
			perso.montrerLesFleches(true);
		}
		
		else if(e.isShortcutDown()){
			perso.montrerLesFleches(false);
		}
		else {
			perso.cacherLesFleches();
		}
		
		switch (kc) {
		case Z:
		case UP:   if(e.isShortcutDown()){
			          if (e.isShiftDown()){
			        	  perso.deformationBas(deltaChange);
			          }
			          else {
			        	  perso.deformationHaut(1/deltaChange);
			          }
			          perso.getFleches().get(Sens.HAUT).activation();
		            }
		            else {
		            	perso.deplacement(0, -5);
		            }
			break;
		case S :
		case DOWN: if(e.isShortcutDown()){
	          		  if (e.isShiftDown()){
	          			  perso.deformationHaut(deltaChange);
		              }
		              else {
		            	  perso.deformationBas(1/deltaChange);
		              }
	          		  perso.getFleches().get(Sens.BAS).activation();
	                }
	                else {
	                	perso.deplacement(0, 5);
	                }
		    break;
		case Q :
		case LEFT: if(e.isShortcutDown()){
	          		  if (e.isShiftDown()){
	        	          perso.deformationDroite(deltaChange);
		              }
		              else {
		        	      perso.deformationGauche(1/deltaChange);
		              }
	                  perso.getFleches().get(Sens.GAUCHE).activation();
	              }
	              else {
	            	  perso.deplacement(-5, 0);
	              }
		    break;
		case D :
		case RIGHT:  if(e.isShortcutDown()){
	                    if (e.isShiftDown()){
	        	           perso.deformationGauche(deltaChange);
		                }
		                else {
		        	       perso.deformationDroite(1/deltaChange);
		                }
	                    perso.getFleches().get(Sens.DROITE).activation();
	                 }
	                 else {
	            	    perso.deplacement(5, 0);
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
		case ADD: bonus += 5;
		break;
		case SUBTRACT: bonus -= 5;
		break;
		case ESCAPE: {
			
			niveau.getChronoThread().interrupt();
			
			Sauvegarde.setPerso(perso);
			Sauvegarde.setNiveau(niveau);
			Sauvegarde.setGoal2D(goal2D);
			
			Scene sc = root.getScene();
			sc.setRoot(new AnchorPane());
			start(importedstage);
		}
		break;
		}
		
		return perso;		
	}
	
	public void inactive(AnchorPane root){
		root.setDisable(true);
    	root.setOpacity(0.6);
	}
	
	public void gerer_sourisBouge(MouseEvent me, AnchorPane root, boolean aff){
		
		if (!infosPositionExist){
			
			DropShadow dropShadow = new DropShadow();
			dropShadow.setRadius(5.0);
			dropShadow.setOffsetX(3.0);
			dropShadow.setOffsetY(3.0);
			dropShadow.setColor(Color.color(0.5, 0.5, 0.5));

			infosPosition.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-5))));
			root.getChildren().add(infosPosition);

			infosPosition.setEffect(dropShadow);
			infosPosition.setStyle("-fx-border-color: grey; -fx-border-width: 1; -fx-border-style: solid inside; -fx-border-insets: -5;");
			
			infosPositionExist = true;
		}
		
		infosPosition.setText(String.format("X=%d\nY=%d", (int)me.getSceneX(), (int)me.getSceneY()));

//		double decalageH = me.getSceneX() < (root.getWidth() - 50) ? 30 : -50;
//		double decalageV = me.getSceneY() < (root.getHeight() - 40) ? 20 : -40;
		
		infosPosition.setLayoutX(me.getSceneX() -55);
		infosPosition.setLayoutY(me.getSceneY() -45);
		
		infosPosition.setVisible(aff && Settings.isAffPositionSouris());
		infosPosition.toFront();		
	}
	
	public void afficheInfos(AnchorPane root, Mur2D mur, MouseEvent me, boolean aff){
		
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

		
		if (aff && Settings.isAffInfosMurs()){
			infos = mur.getInfos(me);
			infos.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-5))));
			root.getChildren().add(infos);	
			
			double decalageH = me.getSceneX() < (root.getWidth() - 120) ? 30 : -120;
			double decalageV = me.getSceneY() < (root.getHeight() - 80) ? 20 : -80;
			
			infos.setLayoutX(me.getSceneX() + decalageH);
			infos.setLayoutY(me.getSceneY() + decalageV);
			
			infos.setEffect(dropShadow);
			infos.setStyle("-fx-border-color: grey; -fx-border-width: 1; -fx-border-style: solid inside; -fx-border-insets: -5;");
			
			mur.toFront();
			mur.setStrokeWidth(2);
			mur.setStroke(Color.CORAL);
			
		}
		else {
			root.getChildren().remove(infos);
			infos = null;
			mur.setStrokeWidth(0);
		}
	}
	
    private void afficherSores(AnchorPane root) {

    	if (fen_scores == null){
    		fen_scores = new FenetreScores();
    		
    		Stage fenStage = (Stage) fen_scores.getScene().getWindow();
    		fenStage.setOnCloseRequest(a -> {
    			fen_scores = null;
    			nouvelleFenetre(root);
    		});
    	}
	
	}

	public Personnage2D gerer_clicks(Personnage2D r, MouseEvent e){
		
		r.setX(e.getSceneX());
		r.setY(e.getSceneY());
		
		return r;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void retourMenu(){
		start(stagePrincipal);
	}
	
	public void retourPartie(Scene scene){
		
        AnchorPane root_ = Sauvegarde.getNiveau().getFullGame();
        
		Personnage2D r0 = Sauvegarde.getNiveau().getPerso();
		Goal2D g0 = Sauvegarde.getNiveau().getGoal2D();
		
		r0.toFront();
		r0.cacherLesFleches();

		scene.setRoot(root_);
		Stage stagePrincipal = (Stage) scene.getWindow();
		
		stagePrincipal.setWidth(1005);
		stagePrincipal.setHeight(635);

		root.setOnMouseClicked(e -> gerer_clicks(r0, e));
		scene.setOnKeyPressed(e1 -> gerer_keys(e1, root_, stagePrincipal, Sauvegarde.getNiveau()));
		
		for (Mur2D mur : Sauvegarde.getNiveau().getListeDesMurs()){
			mur.setOnMouseEntered(c -> {
				if (c.isAltDown())
				afficheInfos(root_, mur, c, true);
			});
			mur.setOnMouseExited(d -> {
				afficheInfos(root_, mur, d, false);
			});
		}
		
		scene.setOnMouseMoved(e -> {
				gerer_sourisBouge(e, root_, !e.isAltDown());
		});
	}
	
	public void nouvelleFenetre(){
		
		scene = root.getScene();
		stagePrincipal = (Stage) scene.getWindow();

		ControlleurNiveaux ctn = new ControlleurNiveaux();
		root = ctn.init(root, this);
		
		root.setDisable(false);
		root.setOpacity(1);
		
		stagePrincipal.setWidth(600);
		stagePrincipal.setHeight(455);
	
	}
	
    public void nouvelleFenetre(AnchorPane root){
		
		this.root = root;
		nouvelleFenetre();
	
	}
	
    public void nouvelleFenetreSettings(){
		
    	scene = root.getScene();
    	stagePrincipal = (Stage) scene.getWindow();
    	
		ControlleurSettings cts = new ControlleurSettings();
		scene = cts.init(root, this);
		
		stagePrincipal.setScene(scene);
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		maze.setOnMouseEntered(a -> {
			tm_launch = new Thread(m_launch);
			tm_launch.start();
		});
		maze.setOnMouseExited(a -> {
			tm_launch.stop();
			maze.setImage(new Image("maze_v2_2_nb.png"));
		});
		
		launch.setOnMouseEntered(a -> {
			t_launch = new Thread(r_launch);
			t_launch.start();
		});
		launch.setOnMouseExited(a -> {
			t_launch.stop();
			launch.setImage(new Image("launch_nb.png"));
		});
		
		exit.setOnMouseEntered(a -> {
			te_launch = new Thread(e_launch);
			te_launch.start();
		});
		exit.setOnMouseExited(a -> {
			te_launch.stop();
			exit.setImage(new Image("exit.png"));
		});
		
		settings.setOnMouseEntered(a -> {
			ts_launch = new Thread(s_launch);
			ts_launch.start();
		});
		settings.setOnMouseExited(a -> {
			ts_launch.stop();
			settings.setImage(new Image("settings2_nb.png"));
		});
		
		settings.setOnMouseClicked(a -> nouvelleFenetreSettings());
		exit.setOnMouseClicked(a -> System.exit(0));
		launch.setOnMouseClicked(a -> nouvelleFenetre());	
	}
	
	Runnable r_launch = new Runnable() {		
		@Override
		public void run() {
			
			while (true){
				try {
					launch.setImage(new Image("launch_2.png"));	
					Thread.sleep(100);
					launch.setImage(new Image("launch.png"));	
					Thread.sleep(50);
					
				} catch (InterruptedException e) {
					launch.setImage(new Image("launch_nb.png"));
					e.printStackTrace();
				}
			}
			
		}
	};
	
	Runnable m_launch = new Runnable() {		
		@Override
		public void run() {
			
			while (true){
				try {
					maze.setImage(new Image("maze_v2_color1.png"));	
					Thread.sleep(80);
					maze.setImage(new Image("maze_v2_color2.png"));	
					Thread.sleep(50);
					maze.setImage(new Image("maze_v2_color3.png"));	
					Thread.sleep(70);
					
				} catch (InterruptedException e) {
					maze.setImage(new Image("maze_v2.png"));
					e.printStackTrace();
				}
			}
			
		}
	};
	
	Runnable s_launch = new Runnable() {		
		@Override
		public void run() {
			
			while (true){
				try {
					settings.setImage(new Image("settings2_r2.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r3.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r4.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r5.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r6.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r7.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r8.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r9.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r10.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r11.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r12.png"));	
					Thread.sleep(80);
					settings.setImage(new Image("settings2_r13.png"));	
					Thread.sleep(80);
					
				} catch (InterruptedException e) {
					settings.setImage(new Image("settings2_nb.png"));
					e.printStackTrace();
				}
			}
			
		}
	};
	
	Runnable e_launch = new Runnable() {		
		@Override
		public void run() {
			
			while (true){
				try {
					exit.setImage(new Image("exit_0_01.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_01_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_02.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_02_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_03.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_03_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_04.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_04_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_05.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_05_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_06.png"));	
					Thread.sleep(300);
					exit.setImage(new Image("exit_0_05_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_05.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_04_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_04.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_03_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_03.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_02_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_02.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_01_.png"));	
					Thread.sleep(80);
					exit.setImage(new Image("exit_0_01.png"));	
					Thread.sleep(220);
					
				} catch (InterruptedException e) {
					exit.setImage(new Image("exit.png"));
					e.printStackTrace();
				}
			}		
		}
	};
}
