package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import models.Mur2D;
import models.Personnage2D;
import models.Sauvegarde;
import models.Settings;
import models.Statiques;
import utils.TickTimer;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;

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

	private Label infos;
	private Label infosPosition = new Label();
	private boolean infosPositionExist = false;
	
	private AnchorPane fen_scores = null;

	@Override
	public void start(Stage primaryStage) {
		
		System.out.println("start");
		
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
	
	
	
	public void afficheInfos(Mur2D mur, MouseEvent me, boolean aff){
		
		AnchorPane root = Statiques.getRoot();
		
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
	
    void afficherSores() {

    	if (fen_scores == null){
    		fen_scores = new FenetreScores();
    		
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

        AnchorPane root = Sauvegarde.getNiveau().getFullGame();
        Scene scene = new Scene(root);

		Personnage2D r0 = Sauvegarde.getNiveau().getPerso();
		
		r0.toFront();
		r0.cacherLesFleches();

		Stage stage = Statiques.getStage();
		stage.setScene(scene);
		
		Statiques.setScene(scene);
		Statiques.setStage(stage);
		Statiques.setRoot(root);
		
		stage.setWidth(1005);
		stage.setHeight(635);
		
		for (Mur2D mur : Sauvegarde.getNiveau().getListeDesMurs()){
			mur.setOnMouseEntered(c -> {
				if (c.isAltDown())
				afficheInfos(mur, c, true);
			});
			mur.setOnMouseExited(d -> {
				afficheInfos(mur, d, false);
			});
		}
		
		scene.setOnMouseMoved(e -> {
				ControleurSouris.gerer_sourisBouge(e, !e.isAltDown());
		});
		
		TickTimer.nouveauTimer();
	}
	
	public static void nouvelleFenetreNiveaux(){
		
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
		
    	scene = root.getScene();
    	stagePrincipal = (Stage) scene.getWindow();
    	
		ControleurSettings cts = new ControleurSettings();
		scene = cts.init();
		
		stagePrincipal.setScene(scene);
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("initialize");
		
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

	public Label getInfos() {
		return infos;
	}

	public void setInfos(Label infos) {
		this.infos = infos;
	}

	public Label getInfosPosition() {
		return infosPosition;
	}

	public void setInfosPosition(Label infosPosition) {
		this.infosPosition = infosPosition;
	}

	public boolean isInfosPositionExist() {
		return infosPositionExist;
	}

	public void setInfosPositionExist(boolean infosPositionExist) {
		this.infosPositionExist = infosPositionExist;
	}
}
