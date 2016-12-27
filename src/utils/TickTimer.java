package utils;

import java.util.Timer;
import java.util.TimerTask;

import application.ControleurClavier;
import javafx.application.Platform;

public class TickTimer {
	
	static TimerTask timerTask;
	static Timer timer;
	
	public static void nouveauTimer(long delai ){

		timer = new Timer(true);
		timer.schedule(makeTask(), 0, delai);
		Statiques.setTimer(timer);
	}
	
	public static void nouveauTimer(){
		nouveauTimer(10);
	}
	
	public static TimerTask makeTask(){
		
		ControleurClavier.init(Contexte.getNiveau());
		
        timerTask = new TimerTask() {
        	@Override
        	public void run() {
        		Platform.runLater(() -> {
        			ControleurClavier.gerer_keys();
        		});
        	}
		};
		
		return timerTask;
	}
	
	public static void updateTimer(long delai){
		timerTask.cancel();
		makeTask();
		Statiques.getTimer().schedule(timerTask, 0, delai);
	}

}
