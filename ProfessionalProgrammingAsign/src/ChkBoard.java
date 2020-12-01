import java.util.HashMap;

import org.eclipse.swt.widgets.*;

/**
 * Class that handles all the game baord checks
 * 
 * @author ryand 01/12/2020 Ver 1.0.1
 */
public class ChkBoard {
	// button values
	HashMap<Button, Integer> btnPlayerMap = new HashMap<Button, Integer>();
	HashMap<Integer, Button> btnPositionMap = new HashMap<Integer, Button>();
	// win sequences
	int[][] winSeq = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 },
			{ 2, 4, 6 } };

	/**
	 * setup the game board
	 */
	public void setup() {
		btnPlayerMap.clear();
		for (int i = 0; i < Main.run.mainGUI.btnArrayLength(); i++) {
			btnPlayerMap.put(Main.run.mainGUI.getBtnArray()[i], 0);
		}
		setButtonPositions();
	}

	// create the game board buttons to hold player selections
	private void setButtonPositions() {
		btnPositionMap.put(0, Main.run.mainGUI.tl);
		btnPositionMap.put(1, Main.run.mainGUI.tm);
		btnPositionMap.put(2, Main.run.mainGUI.tr);
		btnPositionMap.put(3, Main.run.mainGUI.ml);
		btnPositionMap.put(4, Main.run.mainGUI.mm);
		btnPositionMap.put(5, Main.run.mainGUI.mr);
		btnPositionMap.put(6, Main.run.mainGUI.bl);
		btnPositionMap.put(7, Main.run.mainGUI.bm);
		btnPositionMap.put(8, Main.run.mainGUI.br);
	}

	/**
	 * sets a game board button to a player value
	 * 
	 * @param btn   button to set
	 * @param value player value to set
	 */
	public void setButtonValue(Button btn, int value) {
		btnPlayerMap.put(btn, value);
	}

	/**
	 * checks the game board to see if someone has won. If a winner is declared will
	 * run the win method in the main class
	 * 
	 * @return true for winner or draw false for no winner
	 */
	public Boolean chkWin() {
		// loop thru the win sequence array
		for (int i = 0; i < winSeq.length; i++) {
			// check if player one has won the current win sequence
			if (btnPlayerMap.get(btnPositionMap.get(winSeq[i][0])) == 1
					&& btnPlayerMap.get(btnPositionMap.get(winSeq[i][1])) == 1
					&& btnPlayerMap.get(btnPositionMap.get(winSeq[i][2])) == 1) {
				Main.run.win(1);
				return true;
				// check if player two has won the current win sequence
			} else if (btnPlayerMap.get(btnPositionMap.get(winSeq[i][0])) == 2
					&& btnPlayerMap.get(btnPositionMap.get(winSeq[i][1])) == 2
					&& btnPlayerMap.get(btnPositionMap.get(winSeq[i][2])) == 2) {
				Main.run.win(2);
				return true;
			}

		}
		// check for a draw
		if (!btnPlayerMap.containsValue(0)) {
			Main.run.win(3);
			return true;
		}
		return false;
	}

	/**
	 * checks a tile(button) on the game board to see if it belongs to a player
	 * 
	 * @param tile the integer value of the tile(button) to check
	 * @return true if a player has selected it, false if the tile is free
	 */
	public boolean chkTile(int tile) {
		return btnPlayerMap.get(btnPositionMap.get(tile)) == 0;
	}

	/**
	 * returns the location of the button on the game board
	 * 
	 * @param btn button to get
	 * @return button location
	 */
	public Button getBtn(int btn) {
		return btnPositionMap.get(btn);
	}

	/**
	 * checks a button on the game board to see if it has been selected by a player
	 * 
	 * @param btn the button to check
	 * @return true if the button has not been selected selected it, false if it has
	 *         been assigned to a player already
	 */
	public Boolean chkPlayerMap(Button btn) {
		if (btnPlayerMap.get(btn) == 0) {
			return true;
		}
		return false;

	}

}
