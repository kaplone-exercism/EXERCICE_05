package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import models.Mur2D;
import models.Personnage2D;
import utils.Contexte;
import utils.Settings;
import utils.Statiques;
import utils.TickTimer;

public class Main_Exercice_05 extends Application implements Initializable{

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

	private static Scene scene;
	
	private static Stage stagePrincipal;
	
	private static Thread t_launch;
	private static Thread tm_launch;
	private static Thread ts_launch;
	private static Thread te_launch;
	
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
		
		Statiques.setMain(this);
		Statiques.setStage(primaryStage);
		Statiques.setScene(scene);	
		Statiques.setRoot((AnchorPane)scene.getRoot());

		primaryStage.setOnCloseRequest(a -> {try{
			ControleurNiveaux.getTimer().cancel();
		    }
		    catch (NullPointerException npe){
		    	
		    }}
		);
		
		
	}
	
	public void inactive(){
		Statiques.getRoot().setDisable(true);
		Statiques.getRoot().setOpacity(0.6);
	}
	
    void afficherSores() {

    	if (fen_scores == null){
    		fen_scores = new ControleurFenetreScores();
    		
    		Stage fenStage = (Stage) fen_scores.getScene().getWindow();
    		fenStage.setOnCloseRequest(a -> {
    			fen_scores = null;
    			nouvelleFenetreScores();
    		});
    	}
	
	}
	
	public static void main(String[] args) {

		launch(args);
	}

	public void retourMenu(){
		start(Statiques.getStage());
	}
	
	public void retourPartie(){

        AnchorPane root = Contexte.getNiveau().getFullGame();
        Scene scene = new Scene(root);

		Personnage2D r0 = Contexte.getNiveau().getPerso();
		
		r0.toFront();
		r0.cacherLesFleches();

		Stage stage = Statiques.getStage();
		stage.setScene(scene);
		
		Statiques.setScene(scene);
		Statiques.setStage(stage);
		Statiques.setRoot(root);
		
		stage.setWidth(1005);
		stage.setHeight(635);
		
		for (Mur2D mur : Contexte.getNiveau().getListeDesMurs()){
			mur.setOnMouseEntered(c -> {
				if (c.isAltDown())
					mur.afficheInfos(c, true);
			});
			mur.setOnMouseExited(d -> {
				mur.afficheInfos( d, false);
			});
		}
		
		scene.setOnMouseMoved(e -> {
				ControleurSouris.gerer_sourisBouge(e, !e.isAltDown());
		});
		
		TickTimer.nouveauTimer();
		Contexte.getNiveau().setEnCoursDeFonctionnement(true);
	}
	
	public static void nouvelleFenetreNiveaux(){
		
		stopAnimations();
		
		AnchorPane root = Statiques.getRoot();	
		scene = root.getScene();
    	stagePrincipal = (Stage) scene.getWindow();
		//stagePrincipal = Statiques.getStage();

		ControleurNiveaux ctn = new ControleurNiveaux();
		root = ctn.init();
		
		stagePrincipal.setWidth(600);
		stagePrincipal.setHeight(455);
	
	}
	
    public void nouvelleFenetreScores(){
    	
    	System.out.println("nouvelle fenetre scores");
	
	}
	
    public void nouvelleFenetreSettings(){
    	
    	stopAnimations();
		
    	scene = root.getScene();
    	stagePrincipal = (Stage) scene.getWindow();
    	
		ControleurSettings cts = new ControleurSettings();
		scene = cts.init();
		
		stagePrincipal.setScene(scene);
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Statiques.setLocation(location);
		Statiques.setResources(resources);
		
		maze.setOnMouseEntered(a -> {
			tm_launch = new Thread(ControleurAnimation.mazeGetRunnable(maze));
			tm_launch.start();
		});
		maze.setOnMouseExited(a -> {
			tm_launch.stop();
			maze.setImage(new Image("maze_v2_2_nb.png"));
		});
		
		launch.setOnMouseEntered(a -> {
			t_launch = new Thread(ControleurAnimation.launchGetRunnable(launch));
			t_launch.start();
		});
		launch.setOnMouseExited(a -> {
			t_launch.stop();
			launch.setImage(new Image("launch_nb.png"));
		});
		
		exit.setOnMouseEntered(a -> {
			te_launch = new Thread(ControleurAnimation.exitGetRunnable(exit));
			te_launch.start();
		});
		exit.setOnMouseExited(a -> {
			te_launch.stop();
			exit.setImage(new Image("exit.png"));
		});
		
		settings.setOnMouseEntered(a -> {
			ts_launch = new Thread(ControleurAnimation.settingsGetRunnable(settings));
			ts_launch.start();
		});
		settings.setOnMouseExited(a -> {
			ts_launch.stop();
			settings.setImage(new Image("settings2_nb.png"));
		});
		
		settings.setOnMouseClicked(a -> nouvelleFenetreSettings());
		exit.setOnMouseClicked(a -> System.exit(0));
		launch.setOnMouseClicked(a -> nouvelleFenetreNiveaux());	
	}
	
	private static void stopAnimations(){
		if(ts_launch != null) ts_launch.stop();
		if(te_launch != null) te_launch.stop();
		if(t_launch != null) t_launch.stop();
		if(tm_launch != null) tm_launch.stop();
	}
}
