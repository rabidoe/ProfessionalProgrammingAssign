
/**
 * Class to handle the players turn
 * 
 * @author ryand 01/12/2020 Ver 1.0.1
 */

public class PlayerTurn {

	/**
	 * method to handle the players turn
	 */
	public void turn() {
		Main.run.mainGUI.enableButtons();
		Main.run.mainGUI.setStatus("Your Turn....");
		Main.run.mainGUI.enableButtons();
	}
}
