package models;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import application.Main_Exercice_05;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Statiques {
	
	private static Stage stage;
	private static Scene scene;
	private static AnchorPane root;
	private static Main_Exercice_05 main;
	private static URL location;
	private static ResourceBundle resources;
	private static Timer timer;
	
	public static Stage getStage() {
		return stage;
	}
	public static void setStage(Stage stage) {
		Statiques.stage = stage;
	}
	public static Scene getScene() {
		return scene;
	}
	public static void setScene(Scene scene) {
		Statiques.scene = scene;
	}
	public static AnchorPane getRoot() {
		return root;
	}
	public static void setRoot(AnchorPane root) {
		Statiques.root = root;
	}
	public static Main_Exercice_05 getMain() {
		return main;
	}
	public static void setMain(Main_Exercice_05 main) {
		Statiques.main = main;
	}
	public static URL getLocation() {
		return location;
	}
	public static void setLocation(URL location) {
		Statiques.location = location;
	}
	public static ResourceBundle getResources() {
		return resources;
	}
	public static void setResources(ResourceBundle resources) {
		Statiques.resources = resources;
	}
	public static Timer getTimer() {
		return timer;
	}
	public static void setTimer(Timer timer) {
		Statiques.timer = timer;
	}
	
	
}
