import java.util.Random;

/**
 * Main class
 * @author ryand
 * 01/12/2020
 * Ver 1.0.1
 */
public class Main {
	//create instances of classes
	static Main run = new Main();
	GUImain mainGUI = new GUImain();
	ChkBoard chkBoard = new ChkBoard();
	CompTurn compTurn = new CompTurn();
	PlayerTurn playTurn = new PlayerTurn();

	//variable to store player turn
	int player = 1;

	/**
	 * run the program
	 * @param args none accepted
	 */
	public static void main(String[] args) {
		run.setup();
	}

	 //setup the game board
	private void setup() {
		chkBoard.setup();
		mainGUI.displayGUI();
	}


	 /**
	  * main program loop
	  */
	public void loop() {
		if (!run.chkBoard.chkWin()) {
			if (player == 1) {
				player = 2;
			} else {
				player = 1;
			}
			if (player == 1) {
				run.playTurn.turn();
			} else {
				compTurn.turn();
			}
		}
	}

	/**
	 * returns the current player
	 * @return value of the player variable
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * Method to run once a winner has been decided
	 * @param player which player has won (1=P1, 2=P2, 3=Draw)
	 */
	public void win(int player) {
		mainGUI.disableButtons();
		mainGUI.startButtonText();
		if (player == 1) {
			mainGUI.setStatus("You win!!!!!!!");
		} else if (player == 2) {
			mainGUI.setStatus("Computer wins!!!!!!!");
		} else {
			mainGUI.setStatus("Its a draw!!!!!!!");
		}
	}

	/**
	 * Implements a wait(pause) in the program 
	 * @param min min wait time in millis
	 * @param max max wait time in millis
	 */
	public void wait(int min, int max) {
		Random rn = new Random();
		int randomNum = rn.nextInt((max - min) + 1) + min;
		try {
			Thread.sleep(randomNum);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}

