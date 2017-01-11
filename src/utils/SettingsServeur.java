package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SettingsServeur {
	
	static String home =  System.getProperty("user.home");
	static File settings_file = new File(home, "shapesinthemazes_serveur.txt");
	static FileReader fr = null;
	
	public static void readSettingServeur(EchangeAvecServeur echange){
		try {
			fr = new FileReader(settings_file);

	    	BufferedReader br = new BufferedReader(fr);
	    	
	    	String s = br.readLine();
			
	    	while(s != null){
	    		
	    		if (s.startsWith("#") || s.trim().equals("")){
	    		}
	    		
	    		String cle = s.split("=>")[0].trim();
    			String valeur = s.split("=>")[1].trim();
    			
    			switch(cle){
    			case "adresse" : echange.setAdresse(valeur);
    			break;
    			case "port" : echange.setPort(valeur);
    			break;
    			case "route" : echange.setRoute(valeur);
    			break;
    			case "user" : echange.setUser(valeur);
    			break;
    			case "pass" : echange.setPass(valeur);
    			break;
    			case "body" : echange.setBody(valeur);
    			break;
    			case "type" : echange.setType(valeur);
    			break;
    			}
	    		
    			s = br.readLine();
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
