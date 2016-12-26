package enums;

public enum Sens {
	
	HAUT(Orientation.VERTICAL),
	BAS(Orientation.VERTICAL),
	DROITE(Orientation.HORIZONTAL),
	GAUCHE(Orientation.HORIZONTAL);
	
	private Orientation orientation;

	private Sens(Orientation orientation) {
		this.orientation = orientation;
	}

	public Orientation getOrientation() {
		return orientation;
	}
}
