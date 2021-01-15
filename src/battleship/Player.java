package battleship;

public interface Player {

	GridPoint shot();

	void setResult(Result result);

	Battleship putBattleship(BattleshipType battleshipType);

}
