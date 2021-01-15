import battleship.GameManneger;
import battleship.player.AIPlayer1;
import battleship.player.HumanPlayer;
import battleship.player.NetworkPlayer;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameManneger gameManneger;
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("MultiPlayer")) {
				gameManneger = new GameManneger(new HumanPlayer(),
						new HumanPlayer());
			} else if (args[0].equalsIgnoreCase("OverTheNetwork")) {
				gameManneger = new GameManneger(new NetworkPlayer(),
						new AIPlayer1());
			} else {
				gameManneger = new GameManneger(new HumanPlayer(),
						new AIPlayer1());
			}
		} else {
			gameManneger = new GameManneger(new HumanPlayer(), new AIPlayer1());
		}
		gameManneger.run();
	}
}
