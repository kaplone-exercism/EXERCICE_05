package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Sauvegarde;
import models.Settings;
import models.Statiques;

public class ControleurSettings implements Initializable {
	
	@FXML
	ColorPicker colorPicker;
	@FXML
	ColorPicker colorPicker1;
	@FXML
	ColorPicker colorPicker2;
	
	@FXML
	CheckBox moveOnContact;
	
	@FXML
	CheckBox changeOnContact;
	
	@FXML
	CheckBox affCoordSouris;
	
	@FXML
	CheckBox AffInfosMur;
	
	Stage stage;
	Main_Exercice_05 main;
	
	
	
	public Scene init() {
		
		AnchorPane root =Statiques.getRoot();
        main = Statiques.getMain();
		stage = Statiques.getStage();
		
		Scene scene = new Scene((Parent) JfxUtils.loadFxml("settings.fxml"), 800, 500);
		
		Button versMenu = new Button("Menu principal");
		versMenu.setLayoutX(350);
		versMenu.setLayoutY(350);
		versMenu.setOnAction(a -> main.retourMenu());
		
		Button versPartie = new Button("Retour partie");
		versPartie.setLayoutX(470);
		versPartie.setLayoutY(350);
		versPartie.setOnAction(a -> {
			Sauvegarde.getPerso().setFill(Settings.getCouleurPerso());
			Sauvegarde.getNiveau().getListeDesMurs().stream().forEach(m -> m.setFill(Settings.getCouleurMurs()));
			Optional<Node> optFond = Sauvegarde.getNiveau()
					                                .getFullGame()
					                                .getChildren()
					                                .stream()
					                                .filter(u -> u instanceof Rectangle)
					                                .filter(v -> ((Rectangle)v).getWidth() >= 1000 && ((Rectangle)v).getHeight() >= 600)
					                                .findFirst();
			optFond.ifPresent(o -> ((Rectangle)o).setFill(Settings.getCouleurFond()));
			main.retourPartie();
		});
		versPartie.setDisable(Sauvegarde.getNiveau() == null);

		((AnchorPane) scene.getRoot()).getChildren().addAll(versMenu, versPartie);

		return scene;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		colorPicker.setValue(Settings.getCouleurMurs());
		colorPicker.setOnAction( a -> Settings.setCouleurMurs(colorPicker.getValue()));
		
		colorPicker1.setValue(Settings.getCouleurPerso());
		colorPicker1.setOnAction( a -> Settings.setCouleurPerso(colorPicker1.getValue()));
		
		colorPicker2.setValue(Settings.getCouleurFond());
		colorPicker2.setOnAction( a -> Settings.setCouleurFond(colorPicker2.getValue()));
		
		moveOnContact.setSelected(Settings.isRetourContact());
		moveOnContact.setOnAction(a -> Settings.setRetourContact(moveOnContact.isSelected()));
		
		affCoordSouris.setSelected(Settings.isAffPositionSouris());
		affCoordSouris.setOnAction(a -> Settings.setAffPositionSouris(affCoordSouris.isSelected()));
		
		AffInfosMur.setSelected(Settings.isAffInfosMurs());
		AffInfosMur.setOnAction(a -> Settings.setAffInfosMurs(AffInfosMur.isSelected()));
		
	}

}
