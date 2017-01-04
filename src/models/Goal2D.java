package models;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Goal2D extends ImageView{

	private Image goal;
	
	public Goal2D(double x, double y){
		goal = new Image("goal.png");
		super.setImage(goal);
		super.setX(x);
		super.setY(y);
	}


	public Image getGoal() {
		return goal;
	}

	public void setGoal(Image goal) {
		this.goal = goal;
	}
	
	public Goal2D getImv(){
		return this;
	}

}
