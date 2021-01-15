package battleship;

import battleship.screen.StringBaseScreen;

public class Grid {

	private GridPart[][] parts = new GridPart[10][10];
	private Screen screen = new StringBaseScreen();

	public Grid() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				parts[i][j] = new GridPart();
			}
		}
	}

	public void putBattleship(Battleship battleship)
			throws IllegalArgumentException {
		if (battleship == null) {
			throw (new IllegalArgumentException("Direction is Not Valid"));
		}
		if (battleship.getDirection() == Direction.RIGHT) {
			try {
				for (int i = 0; i < battleship.getBattleshipType().getSize(); i++) {
					if (!parts[battleship.getGridPoint().getX()][battleship
							.getGridPoint().getY() + i].isHaveBattleship())
						parts[battleship.getGridPoint().getX()][battleship
								.getGridPoint().getY() + i]
								.setHaveBattleship(true);
					else {
						throw (new IllegalArgumentException(
								"Grid Point is Not Valid"));
					}

				}
			} catch (ArrayIndexOutOfBoundsException exception) {
				throw (new IllegalArgumentException("Grid Point is Not Valid"));
			}
		} else if (battleship.getDirection() == Direction.LEFT) {
			try {
				for (int i = 0; i < battleship.getBattleshipType().getSize(); i++) {
					if (!parts[battleship.getGridPoint().getX()][battleship
							.getGridPoint().getY() - i].isHaveBattleship())
						parts[battleship.getGridPoint().getX()][battleship
								.getGridPoint().getY() - i]
								.setHaveBattleship(true);
					else {
						throw (new IllegalArgumentException(
								"Grid Point is Not Valid"));
					}
				}
			} catch (ArrayIndexOutOfBoundsException exception) {
				throw (new IllegalArgumentException("Grid Point is Not Valid"));
			}
		} else if (battleship.getDirection() == Direction.UP) {
			try {
				for (int i = 0; i < battleship.getBattleshipType().getSize(); i++) {
					if (!parts[battleship.getGridPoint().getX() - i][battleship
							.getGridPoint().getY()].isHaveBattleship())
						parts[battleship.getGridPoint().getX() - i][battleship
								.getGridPoint().getY()].setHaveBattleship(true);
					else {
						throw (new IllegalArgumentException(
								"Grid Point is Not Valid"));
					}
				}
			} catch (ArrayIndexOutOfBoundsException exception) {
				throw (new IllegalArgumentException("Grid Point is Not Valid"));
			}
		} else if (battleship.getDirection() == Direction.DOWN) {
			try {
				for (int i = 0; i < battleship.getBattleshipType().getSize(); i++) {
					if (!parts[battleship.getGridPoint().getX() + i][battleship
							.getGridPoint().getY()].isHaveBattleship())
						parts[battleship.getGridPoint().getX() + i][battleship
								.getGridPoint().getY()].setHaveBattleship(true);
					else {
						throw (new IllegalArgumentException(
								"Grid Point is Not Valid"));
					}
				}
			} catch (ArrayIndexOutOfBoundsException exception) {
				throw (new IllegalArgumentException("Grid Point is Not Valid"));
			}
		}

	}

	public void showGrid() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				screen.showGridPart(parts[i][j]);
			}
			screen.showNextLine();
		}
	}

	public void showGridForEnemy() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (parts[i][j].isSee())
					screen.showGridPart(parts[i][j]);
				else
					screen.showGridPart(null);
			}
			screen.showNextLine();
		}
	}

	Result getShot(GridPoint gridPoint) throws IllegalArgumentException {
		try {
			if (parts[gridPoint.getX()][gridPoint.getY()].isHaveShot()) {
				throw new IllegalArgumentException("Grid Point is Not Valid");
			}
			parts[gridPoint.getX()][gridPoint.getY()].setSee(true);
			parts[gridPoint.getX()][gridPoint.getY()].setHaveShot(true);
			if (parts[gridPoint.getX()][gridPoint.getY()].isHaveBattleship()) {
				return Result.HIT;
			} else {
				return Result.LOSE;
			}
		} catch (ArrayIndexOutOfBoundsException exception) {
			throw new IllegalArgumentException("Grid Point is Not Valid");
		}
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Screen getScreen() {
		return screen;
	}
}
