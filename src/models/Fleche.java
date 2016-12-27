package models;

import enums.Sens;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fleche extends ImageView{

	private Sens sens;
	private boolean out;
	
	private Image image;
	
	private Personnage2D perso;


	public Fleche(Image image, Personnage2D perso, Sens sens) {
		super(image);
		this.image = image;
		this.sens = sens;
		this.out = true;
		this.perso = perso;
		
		
	}
	
	public void bindAttaches(){
		
		switch(sens){
		
		case BAS :
			this.xProperty().bind(perso.xProperty().add(perso.widthProperty().divide(2)).subtract(image.getWidth()/2));
			this.yProperty().bind(perso.yProperty().add(perso.heightProperty()));
			break;
		case HAUT :
			this.xProperty().bind(perso.xProperty().add(perso.widthProperty().divide(2)).subtract(image.getWidth()/2));
			this.yProperty().bind(perso.yProperty().subtract(image.getHeight()));
			break;
		case DROITE :
			this.xProperty().bind(perso.xProperty().add(perso.widthProperty()));
			this.yProperty().bind(perso.yProperty().add(perso.heightProperty().divide(2)).subtract(image.getHeight()/2));
			break;
		case GAUCHE :
			this.xProperty().bind(perso.xProperty().subtract(image.getWidth()));
			this.yProperty().bind(perso.yProperty().add(perso.heightProperty().divide(2)).subtract(image.getHeight()/2));
			break;
		}	
	}
	
	public void activation(){
		
		for(Fleche f : perso.getFleches().values()){
			if (f.equals(this)){
				f.setVisible(true);
			}
			else {
				f.setVisible(false);
			}
		}
	}

	public boolean isOut() {
		return out;
	}

	public void setOut(boolean out) {
		if (out){
			bindAttaches();
		}
		else {
			switch(sens){
			
			case BAS :
				this.xProperty().bind(perso.xProperty().add(perso.widthProperty().divide(2)).subtract(image.getWidth()/2));
				this.yProperty().bind(perso.yProperty());
				break;
			case HAUT :
				this.xProperty().bind(perso.xProperty().add(perso.widthProperty().divide(2)).subtract(image.getWidth()/2));
				this.yProperty().bind(perso.yProperty().subtract(image.getHeight()).add(perso.heightProperty()));
				break;
			case DROITE :
				this.xProperty().bind(perso.xProperty());
				this.yProperty().bind(perso.yProperty().add(perso.heightProperty().divide(2)).subtract(image.getHeight()/2));
				break;
			case GAUCHE :
				this.xProperty().bind(perso.xProperty().subtract(image.getWidth()).add(perso.widthProperty()));
				this.yProperty().bind(perso.yProperty().add(perso.heightProperty().divide(2)).subtract(image.getHeight()/2));
				break;
			}
		}
	}

	public Sens getSens() {
		return sens;
	}
	
	

}
