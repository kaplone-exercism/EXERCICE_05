package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import enums.Orientation;
import enums.Sens;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Fleche;
import models.Goal2D;
import models.Mur2D;
import models.Niveau;
import models.Personnage2D;
import models.Temps;
import utils.Contexte;
import utils.Settings;
import utils.Statiques;
import utils.TickTimer;

public class ControleurNiveaux{
	
	String home =  System.getProperty("user.home");
	File settings_file = new File(home, "shapesinthemazes_niveaux.txt");

	private static ImageView imv;
	
	private Runnable chrono;
	private Duration duree;
	private static Timer timer;
	
	public AnchorPane init() {
		
		AnchorPane root = Statiques.getRoot();
        Main_Exercice_05 main = Statiques.getMain();

		final Scene scene = root.getScene();
		
        FileReader fr = null;
        root.getChildren().clear();
        ScrollPane sc = new ScrollPane();
        sc.setHbarPolicy(ScrollBarPolicy.NEVER);
        sc.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        sc.setPrefHeight(435);
        VBox vb = new VBox(); 
        vb.setPrefSize(580, 435);
        HBox hb = null;
        Label l = null;
        AnchorPane preview = null;
        AnchorPane fullGame = null;
        Goal2D goal = null;
        Niveau niveau = new Niveau();
        final Map<AnchorPane, Niveau> tableauDesNiveaux = new HashMap<>();
    	
		try {
			fr = new FileReader(settings_file);

	    	BufferedReader br = new BufferedReader(fr);
	    	
	    	String s = br.readLine();
			
	    	while(s != null){
	    		
	    		if (s.startsWith("#") || s.trim().equals("")){
	    		}
	    		
	    		else if(s.startsWith("[")){
	    			
	    			if(hb != null){
	    					    				
	    				hb.getChildren().add(preview);
	    				vb.getChildren().add(hb);
	    				niveau.setFullGame(fullGame);
	    				niveau.setPreview(preview);
	    				tableauDesNiveaux.put(preview, niveau);
	    				
	    				preview.setOnMouseClicked(b -> selectionneNiveau(b,
	    	                    tableauDesNiveaux));
	    				
	    			}
	    			
	    			niveau = new Niveau();

	    			hb = new HBox();
	    			hb.setPadding(new Insets(20));
	    			preview = new AnchorPane();
	    			preview.setPrefWidth(200);
	    			preview.setPrefHeight(120);
	    			preview.getChildren().add(new Rectangle(200,120, Settings.getCouleurFond()));
	    			
	    			fullGame = new AnchorPane();
	    			fullGame.setPrefWidth(1000);
	    			fullGame.setPrefHeight(600);
	    			fullGame.getChildren().add(new Rectangle(1000,600, Settings.getCouleurFond()));
	    			
	    			niveau.setNom(s.split("\\[")[1].split("\\]")[0]);
	    			
	    			l = new Label(niveau.getNom() + " : ");
	    			hb.getChildren().add(l);
	    			

    				niveau.setHorloge(new Temps(5, 4, 14, 24, 3, 7));
    				fullGame.getChildren().add(niveau.getHorloge().getH1());
	    		}
	    		else {
	    			String marqueur = s.split("=")[0].trim().toUpperCase();
	    			String ligne = s.split("=")[1];
	    			
	    			if ("PERSO".equals(marqueur)){
	    				//Color couleur = Color.valueOf(ligne.split(",")[0].trim());
	    				Color couleur = Settings.getCouleurPerso();
	    				int x = Integer.parseInt(ligne.split(",")[1].trim());
		    			int y = Integer.parseInt(ligne.split(",")[2].trim());
		    			int w = Integer.parseInt(ligne.split(",")[3].trim());
		    			int h = Integer.parseInt(ligne.split(",")[4].trim());

		    			niveau.setPerso(new Personnage2D(x, y, w, h, couleur, niveau));
	    			}
	    			else if ("MUR".equals(marqueur)){
	    				
	    				Orientation or = Orientation.valueOf(ligne.split(",")[0].trim());
		    			
		    			int epais = Integer.parseInt(ligne.split(",")[1].trim());
		    			int dist = Integer.parseInt(ligne.split(",")[2].trim());
		    			int debut = Integer.parseInt(ligne.split(",")[3].trim());
		    			int fin = Integer.parseInt(ligne.split(",")[4].trim());
		    			String nom = ligne.split(",").length > 5 ? ligne.split(",")[5].trim() : "Sans nom";
		    			fullGame.getChildren().add(new Mur2D(or, epais, dist, debut, fin, nom));
		    			
		    			
		    			epais = epais / 5;
		    			dist = dist / 5;
		    			debut = debut / 5;
		    			fin = fin / 5;
		    			
		    			preview.getChildren().add(new Mur2D(or, epais, dist, debut, fin, nom));
	    			}	
	    			else if ("GOAL".equals(marqueur)){
	    				
	    				int x = Integer.parseInt(ligne.split(",")[0].trim());
		    			int y = Integer.parseInt(ligne.split(",")[1].trim());
		    		    goal = new Goal2D(x, y);
		    		    niveau.setGoal2D(goal);
	    			}
	    			
                    else if ("INFOS".equals(marqueur)){
	    				
                    	String infos = ligne.split(";")[0].trim();
	    				int x = Integer.parseInt(ligne.split(";")[1].trim());
		    			int y = Integer.parseInt(ligne.split(";")[2].trim());
		    			int w = Integer.parseInt(ligne.split(";")[3].trim());
		    			
		    			Label text = new Label(infos);
		    			text.setWrapText(true);
		    			text.setLayoutX(x);
		    			text.setLayoutY(y);
		    			text.setPrefWidth(w);
		    			text.setOpacity(0.50);
		    			
	    				fullGame.getChildren().add(text);
	    			}
	    		}
	    		
	    		s = br.readLine();
	    	}
	    	
	    	br.close();
	    	
	    	hb.getChildren().add(preview);
			vb.getChildren().add(hb);
			niveau.setFullGame(fullGame);
			niveau.setPreview(preview);
			tableauDesNiveaux.put(preview, niveau);
			
			preview.setOnMouseClicked(b -> selectionneNiveau(b,
                    tableauDesNiveaux));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.setContent(vb);
		root.getChildren().add(sc);
		return root;
	}
	
	public void selectionneNiveau(MouseEvent me,
			                 Map<AnchorPane,Niveau> tableauDesNiveaux){
		
		AnchorPane root = Statiques.getRoot();
        Scene scene = Statiques.getScene();
        Main_Exercice_05 main = Statiques.getMain();
		
		Niveau niveau = tableauDesNiveaux.get(me.getSource());
		AnchorPane root_ = niveau.getFullGame();
		
		Personnage2D r0 = niveau.getPerso();
		Goal2D g0 = niveau.getGoal2D();
		
		Fleche fb = r0.getFleches().get(Sens.BAS);
		Fleche fh = r0.getFleches().get(Sens.HAUT);
		Fleche fd = r0.getFleches().get(Sens.DROITE);
		Fleche fg = r0.getFleches().get(Sens.GAUCHE);

		root_.getChildren().add(r0);
		root_.getChildren().addAll(fb, fh, fd, fg, g0);
		
		r0.toFront();
		fb.toFront();
		fh.toFront();
		fd.toFront();
		fg.toFront(); 
		g0.toFront();
		r0.cacherLesFleches();

		scene.setRoot(root_);
		Stage stagePrincipal = (Stage) scene.getWindow();
		
		stagePrincipal.setWidth(1005);
		stagePrincipal.setHeight(635);
		
		Contexte.setNiveau(niveau);
		niveau.setChronoTask(createWorker());

		TickTimer.nouveauTimer();
	
		for (Mur2D mur : niveau.getListeDesMurs()){
			mur.setOnMouseEntered(c -> {
				if (c.isAltDown())
				main.afficheInfos(mur, c, true);
			});
			mur.setOnMouseExited(d -> {
				main.afficheInfos(mur, d, false);
			});
		}
		
		scene.setOnMouseMoved(e -> {
				ControleurSouris.gerer_sourisBouge(e, !e.isAltDown());
		});
	}
	
	public Task<Object> createWorker() {
        return new Task<Object>() {
            @Override
            protected Object call() throws Exception {
            	
            	updateMessage("00:00.00");
            	
            	LocalDateTime debut = LocalDateTime.now();

            	while (Contexte.getNiveau().isEnCoursDeFonctionnement()){
            			
            		Thread.sleep(100);     
            		duree = Duration.between(debut, LocalDateTime.now());
            		
            		long secondes = duree.getSeconds() % 60;
            		long minutes = duree.getSeconds() / 60;
            		long dixiemes = ((duree.getNano() / 1000000) % 1000) / 100;

                    updateMessage(String.format("%02d:%02d.%d", minutes, secondes, dixiemes));
                    
                    
                }
                return true;
            }
        };
    }
	
	public ObservableList<Mur2D> listeDesMurs(ObservableList<Node> listeDesNoeuds){
		
		ObservableList<Mur2D> retour = FXCollections.observableArrayList();
		
		for (Node n : listeDesNoeuds){
			if (n instanceof Mur2D){
				retour.add((Mur2D)n);
			}
		}
		return retour;
	}

	public static void setImage(String im) {
		imv.setImage(new Image(im));
	}

	public Runnable getChrono() {
		return chrono;
	}

	public void setChrono(Runnable chrono) {
		this.chrono = chrono;
	}

	public static Timer getTimer() {
		return timer;
	}
}