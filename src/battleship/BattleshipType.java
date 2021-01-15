package battleship;

public enum BattleshipType {

	VERYBIG_BATTLESHIP(5), BIG_BATTLESHIP(4), NORMAL_BATTLESHIP(3), SMALL_BATTLESHIP(
			2), VERYSMALL_BATTLESHIP(1);
	private final int size;

	private BattleshipType(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return String.valueOf(size);
	}

}
