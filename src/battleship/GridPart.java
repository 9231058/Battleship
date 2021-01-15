package battleship;

public class GridPart {

	private boolean see;
	private boolean haveBattleship;
	private boolean haveShot;

	public GridPart() {
	}

	public boolean isSee() {
		return see;
	}

	public void setSee(boolean see) {
		this.see = see;
	}

	public boolean isHaveBattleship() {
		return haveBattleship;
	}

	public void setHaveBattleship(boolean haveBattleship) {
		this.haveBattleship = haveBattleship;
	}

	public boolean isHaveShot() {
		return haveShot;
	}

	public void setHaveShot(boolean haveShot) {
		this.haveShot = haveShot;
	}

}
