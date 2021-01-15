package battleship.screen;

import battleship.GridPart;
import battleship.Screen;
import java.util.Scanner;

public class StringBaseScreen implements Screen {

	private static Scanner cin = new Scanner(System.in);

	@Override
	public int getInt(String message) {
		System.out.println(message);
		return cin.nextInt();
	}

	@Override
	public int getInt() {
		return cin.nextInt();
	}

	@Override
	public void showGridPart(GridPart gridPart) {
		if (gridPart == null) {
			System.out.print('.');
		} else {
			if (gridPart.isHaveBattleship() && gridPart.isHaveShot()) {
				System.out.print('*');
			} else if (gridPart.isHaveBattleship()) {
				System.out.print('#');
			} else if (gridPart.isHaveShot()) {
				System.out.print('%');
			} else {
				System.out.print("-");
			}
		}
	}

	@Override
	public void showNextLine() {
		System.out.println();
	}

	@Override
	public void showScore(int score1, int score2) {
		System.out.println("Player 1 Score : " + score1);
		System.out.println("Player 2 Score : " + score2);
	}

	@Override
	public String getString(String message) {
		System.out.println(message);
		return cin.next();
	}

	@Override
	public void showMassage(String message) {
		System.out.println(message);
	}

}
