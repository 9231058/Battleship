package battleship.player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import battleship.Battleship;
import battleship.BattleshipType;
import battleship.Direction;
import battleship.Grid;
import battleship.GridPart;
import battleship.GridPoint;
import battleship.Player;
import battleship.Result;
import battleship.Screen;
import battleship.screen.StringBaseScreen;

public class NetworkPlayer implements Player, Screen {

	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private Screen screen = new StringBaseScreen();
	private ServerSocket serverSocket;
	private Socket socket;

	public NetworkPlayer() {
		try {
			screen.showMassage("Waiting for Network ......");
			serverSocket = new ServerSocket(1373);
			socket = serverSocket.accept();
			screen.showMassage("Connection establish with "
					+ socket.getInetAddress());
			objectOutputStream = new ObjectOutputStream(
					socket.getOutputStream());
			objectOutputStream.flush();
			objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public GridPoint shot() {
		int x = 0;
		int y = 0;
		try {
			x = (Integer) objectInputStream.readObject();
			y = (Integer) objectInputStream.readObject();
			return new GridPoint(x, y);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setResult(Result result) {
	}

	@Override
	public Battleship putBattleship(BattleshipType battleshipType) {
		int x = 0;
		int y = 0;
		try {
			x = (Integer) objectInputStream.readObject();
			y = (Integer) objectInputStream.readObject();
			String direction = (String) objectInputStream.readObject();
			if (direction.equalsIgnoreCase("UP")) {
				return new Battleship(new GridPoint(x, y), battleshipType,
						Direction.UP);
			} else if (direction.equalsIgnoreCase("RIGHT")) {
				return new Battleship(new GridPoint(x, y), battleshipType,
						Direction.RIGHT);
			} else if (direction.equalsIgnoreCase("LEFT")) {
				return new Battleship(new GridPoint(x, y), battleshipType,
						Direction.LEFT);
			} else if (direction.equalsIgnoreCase("DOWN")) {
				return new Battleship(new GridPoint(x, y), battleshipType,
						Direction.DOWN);
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void sendGridOverTheNetwork(Grid player1Grid, Grid player2Grid) {
		player1Grid.setScreen(this);
		player2Grid.setScreen(this);
		screen.showMassage("Your map :");
		player1Grid.showGrid();
		screen.showMassage("Your enemy map :");
		player2Grid.showGridForEnemy();
		player1Grid.setScreen(new StringBaseScreen());
		player2Grid.setScreen(new StringBaseScreen());
	}

	public void sendScoreOverTheNetwork(int player1Score, int player2Score) {
		this.showScore(player1Score, player2Score);
	}

	@Override
	public int getInt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String message) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getString(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showGridPart(GridPart gridPart) {
		try {
			if (gridPart == null) {
				objectOutputStream.writeObject(".");
			} else {
				if (gridPart.isHaveBattleship() && gridPart.isHaveShot()) {
					objectOutputStream.writeObject("*");
				} else if (gridPart.isHaveBattleship()) {
					objectOutputStream.writeObject("#");
				} else if (gridPart.isHaveShot()) {
					objectOutputStream.writeObject("%");
				} else {
					objectOutputStream.writeObject("-");
				}
			}
			objectOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showMassage(String message) {
		try {
			objectOutputStream.writeObject(message);
			objectOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showNextLine() {
		try {
			objectOutputStream.writeObject("\n");
			objectOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void showScore(int score1, int score2) {
		try {
			objectOutputStream.writeObject("Player 1 Score : " + score1 + "\n");
			objectOutputStream.writeObject("Player 2 Score : " + score2 + "\n");
			objectOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
