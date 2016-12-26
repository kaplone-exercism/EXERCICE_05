package utils;

import java.util.Timer;
import java.util.TimerTask;

import application.ControleurClavier;
import javafx.application.Platform;
import models.Sauvegarde;
import models.Statiques;

public class TickTimer {
	
	public static void nouveauTimer(long delai ){
		
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				 Platform.runLater(() -> {
				ControleurClavier.gerer_keys(Sauvegarde.getNiveau());
				 });
				
			}
		}, 0, delai);
		
		Statiques.setTimer(timer);
	}
	
	public static void nouveauTimer(){
		nouveauTimer(5);
	}

}
