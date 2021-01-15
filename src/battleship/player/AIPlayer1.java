package battleship.player;

import java.util.Random;

import battleship.Battleship;
import battleship.BattleshipType;
import battleship.Direction;
import battleship.GridPoint;
import battleship.Player;
import battleship.Result;

public class AIPlayer1 implements Player {

	private Random rand = new Random();
	private boolean lastHitSucess = false;
	private GridPoint lastHitPoint;
	private int checkAll;

	@Override
	public GridPoint shot() {
		if (!lastHitSucess && (checkAll == 0)) {
			lastHitPoint = new GridPoint(rand.nextInt(10), rand.nextInt(10));
			return lastHitPoint;
		} else {
			if (checkAll == 0) {
				checkAll = (checkAll + 1) % 4;
				return new GridPoint((lastHitPoint.getX() + 1) % 10,
						lastHitPoint.getY());
			} else if (checkAll == 1) {
				checkAll = (checkAll + 1) % 4;
				return new GridPoint(
						(lastHitPoint.getX() - 1 > 0) ? lastHitPoint.getX() - 1
								: 0, lastHitPoint.getY());
			} else if (checkAll == 2) {
				checkAll = (checkAll + 1) % 4;
				return new GridPoint(lastHitPoint.getX(),
						(lastHitPoint.getY() + 1) % 10);
			} else {
				checkAll = (checkAll + 1) % 4;
				return new GridPoint(lastHitPoint.getX(),
						(lastHitPoint.getY() - 1 > 0) ? lastHitPoint.getY() - 1
								: 0);
			}
		}
	}

	@Override
	public void setResult(Result result) {
		if (result == Result.HIT) {
			lastHitSucess = true;
			checkAll = 0;
		} else if (result == Result.LOSE) {
			lastHitSucess = false;
		} else if (result == Result.INVALID_GRIDPOINT) {
			lastHitSucess = false;
		}
	}

	@Override
	public Battleship putBattleship(BattleshipType battleshipType) {
		int x = rand.nextInt(10);
		int y = rand.nextInt(10);
		Direction direction = Direction.values()[rand.nextInt(4)];
		return new Battleship(new GridPoint(x, y), battleshipType, direction);
	}

}
