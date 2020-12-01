import java.util.Random;

/**
 * Class to handle the computer players turn
 * @author ryand
 * 01/12/2020
 */
public class CompTurn {

	//method to handle the computer players turn
	public void turn() {
		Main.run.mainGUI.setStatus("Computers Turn....");
		Main.run.wait(1000, 2000);
		int selectedTile = selectTile();
		Main.run.mainGUI.compHandler(Main.run.chkBoard.getBtn(selectedTile));
	}

	//method to select which tile the computer will select
	private int selectTile() {
		int tile = random();
		//keeps randomly selecting tiles and checking to see if they are free until it has a free tile
		while (!Main.run.chkBoard.chkTile(tile)) {
			tile = random();
		}
		return tile;
	}

	/**
	 * selects a random tile
	 * @return the randomly selected tile
	 */
	private int random() {
		Random rn = new Random();
		return rn.nextInt(7);
	}
}

