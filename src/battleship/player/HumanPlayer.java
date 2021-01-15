package battleship.player;

import battleship.Battleship;
import battleship.BattleshipType;
import battleship.Direction;
import battleship.GridPoint;
import battleship.Player;
import battleship.Result;
import battleship.Screen;
import battleship.screen.StringBaseScreen;

public class HumanPlayer implements Player {

	private Screen screen = new StringBaseScreen();

	@Override
	public GridPoint shot() {
		int x = screen.getInt("Please enter 2 integer for fire zone");
		int y = screen.getInt();
		return new GridPoint(x, y);
	}

	@Override
	public void setResult(Result result) {
	}

	@Override
	public Battleship putBattleship(BattleshipType battleshipType) {
		screen.showMassage("You must put battleship with size "
				+ battleshipType.getSize());
		int x = screen
				.getInt("Please enter 2 intger for battleship start place");
		int y = screen.getInt("");
		String direction = screen
				.getString("Enter direction for your battleship");
		if (direction.equalsIgnoreCase("LEFT")) {
			return new Battleship(new GridPoint(x, y), battleshipType,
					Direction.LEFT);
		} else if (direction.equalsIgnoreCase("RIGHT")) {
			return new Battleship(new GridPoint(x, y), battleshipType,
					Direction.RIGHT);
		} else if (direction.equalsIgnoreCase("UP")) {
			return new Battleship(new GridPoint(x, y), battleshipType,
					Direction.UP);
		} else if (direction.equalsIgnoreCase("DOWN")) {
			return new Battleship(new GridPoint(x, y), battleshipType,
					Direction.DOWN);
		}
		return null;
	}
}
