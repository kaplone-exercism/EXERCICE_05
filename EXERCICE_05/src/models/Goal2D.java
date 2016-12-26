package models;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Goal2D extends ImageView{
	
	private Rectangle2D rectangle2D;
	private Image goal;
	
	public Goal2D(double x, double y){
		goal = new Image("goal.png");
		super.setImage(goal);
		super.setX(x);
		super.setY(y);
		rectangle2D = new Rectangle2D(x, y, goal.getWidth(), goal.getHeight());
	}

	public Rectangle2D getRectangle2D() {
		return rectangle2D;
	}

	public void setRectangle2D(Rectangle2D rectangle2d) {
		rectangle2D = rectangle2d;
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
