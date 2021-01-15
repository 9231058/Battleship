package battleship;

public class Battleship {
	private BattleshipType battleshipType;
	private GridPoint gridPoint;
	private Direction direction;

	public Battleship(GridPoint gridPoint, BattleshipType battleshipType,
			Direction direction) {
		this.battleshipType = battleshipType;
		this.gridPoint = gridPoint;
		this.direction = direction;
	}

	public GridPoint getGridPoint() {
		return gridPoint;
	}

	public BattleshipType getBattleshipType() {
		return battleshipType;
	}

	public Direction getDirection() {
		return direction;
	}
}
