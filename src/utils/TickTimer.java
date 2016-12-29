package utils;

import java.util.Timer;
import java.util.TimerTask;

import application.ControleurClavier;
import javafx.application.Platform;

public class TickTimer {
	
	static TimerTask timerTask;
	static Timer timer;
	
	public static void nouveauTimer(long delai ){
		
		ControleurClavier.init(Contexte.getNiveau());

		timer = new Timer(true);
		timerTask = makeTask();
		timer.schedule(timerTask, 0, delai);
		Statiques.setTimer(timer);
	}
	
	public static void nouveauTimer(){
		nouveauTimer(10);
	}
	
	public static TimerTask makeTask(){

		return new TimerTask() {
        	@Override
        	public void run() {
        		Platform.runLater(() -> {
        			ControleurClavier.gerer_keys();
        		});
        	}
		};
	}
	
	public static void reloadTimer(long delai){
		timer = new Timer(true);
		timerTask = makeTask();
		timer.schedule(timerTask, 0, delai);
		Statiques.setTimer(timer);
	}

}
