package battleship;

import battleship.player.HumanPlayer;
import battleship.player.NetworkPlayer;
import battleship.screen.StringBaseScreen;

public class GameManneger implements Runnable {
	private Player player1;
	private Player player2;
	private Grid gridPlayer1 = new Grid();
	private Grid gridPlayer2 = new Grid();
	private int player1Score = 150;
	private int player2Score = 150;
	private Screen screen = new StringBaseScreen();

	public GameManneger(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		int i = 0;
		screen.showMassage("Player 1 : ");
		while (i < BattleshipType.values().length) {
			try {
				Battleship battleship = player1.putBattleship(BattleshipType
						.values()[i]);
				gridPlayer1.putBattleship(battleship);
				i++;
			} catch (IllegalArgumentException exception) {
				System.err.println(exception);
			}

		}
		screen.showMassage("Player 2 : ");
		i = 0;
		while (i < BattleshipType.values().length) {
			try {
				Battleship battleship = player2.putBattleship(BattleshipType
						.values()[i]);
				gridPlayer2.putBattleship(battleship);
				i++;

			} catch (IllegalArgumentException exception) {
				System.err.println(exception);
			}
		}
	}

	@Override
	public void run() {
		boolean isPlayer1Trun = true;
		while (player1Score > 0 && player2Score > 0) {
			this.showScore();
			if (isPlayer1Trun) {
				if (player1 instanceof HumanPlayer) {
					screen.showMassage("Your map :");
					gridPlayer1.showGrid();
					screen.showMassage("Your enemy map :");
					gridPlayer2.showGridForEnemy();
				}
				if (player1 instanceof NetworkPlayer) {
					((NetworkPlayer) player1).showMassage("CONTINUE");
					((NetworkPlayer) player1).showScore(player1Score,
							player2Score);
					((NetworkPlayer) player1).sendGridOverTheNetwork(
							gridPlayer1, gridPlayer2);
				}
				Result result;
				try {
					result = gridPlayer2.getShot(player1.shot());
				} catch (IllegalArgumentException exception) {
					player1.setResult(Result.INVALID_GRIDPOINT);
					continue;
				}
				player1.setResult(result);
				if (result == Result.HIT) {
					player2Score -= 10;
					player2.setResult(Result.DAMAGE);
				} else {
					player2.setResult(Result.NOT_DAMAGE);
				}
				isPlayer1Trun = false;
			} else {
				if (player2 instanceof HumanPlayer) {
					screen.showMassage("Your map :");
					gridPlayer2.showGrid();
					screen.showMassage("Your enemy map :");
					gridPlayer1.showGridForEnemy();
				}
				if (player2 instanceof NetworkPlayer) {
					((NetworkPlayer) player2).showMassage("CONTINUE");
					((NetworkPlayer) player2).showScore(player1Score,
							player2Score);
					((NetworkPlayer) player2).sendGridOverTheNetwork(
							gridPlayer2, gridPlayer1);
				}
				Result result;
				try {
					result = gridPlayer1.getShot(player2.shot());
				} catch (IllegalArgumentException exception) {
					player2.setResult(Result.INVALID_GRIDPOINT);
					continue;
				}
				player2.setResult(result);
				if (result == Result.HIT) {
					player1Score -= 10;
					player1.setResult(Result.DAMAGE);
				} else {
					player1.setResult(Result.NOT_DAMAGE);
				}
				isPlayer1Trun = true;
			}
		}
		if (player1Score == 0) {
			if (player1 instanceof NetworkPlayer) {
				((NetworkPlayer) player1).showMassage("END");
				((NetworkPlayer) player1).showMassage("Player 2 Win ....");
			}
			if (player2 instanceof NetworkPlayer) {
				((NetworkPlayer) player2).showMassage("END");
				((NetworkPlayer) player2).showMassage("Player 2 Win ....");
			}
			screen.showMassage("Player 2 Win ....");
		} else {
			if (player2 instanceof NetworkPlayer) {
				((NetworkPlayer) player2).showMassage("END");
				((NetworkPlayer) player2).showMassage("Player 1 Win ....");
			}
			if (player1 instanceof NetworkPlayer) {
				((NetworkPlayer) player1).showMassage("END");
				((NetworkPlayer) player1).showMassage("Player 1 Win ....");
			}
			screen.showMassage("Player 1 Win ....");
		}
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}

	public void showScore() {
		screen.showScore(player1Score, player2Score);
	}

}
