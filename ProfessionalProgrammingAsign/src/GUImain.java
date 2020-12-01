
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

/**
 * GUI class
 * @author ryand
 * 01/12/2020
 */
public class GUImain {
	//create the GUI components
	Display display = new Display();
	Shell shell = new Shell(display);

	Label status = new Label(shell, SWT.CENTER);

	Button startButton = new Button(shell, SWT.PUSH);
	Button tl = new Button(shell, SWT.PUSH);
	Button tm = new Button(shell, SWT.PUSH);
	Button tr = new Button(shell, SWT.PUSH);
	Button ml = new Button(shell, SWT.PUSH);
	Button mm = new Button(shell, SWT.PUSH);
	Button mr = new Button(shell, SWT.PUSH);
	Button bl = new Button(shell, SWT.PUSH);
	Button bm = new Button(shell, SWT.PUSH);
	Button br = new Button(shell, SWT.PUSH);

	//create an array to hold all the game board buttons to allow for easier functionality
	Button[] btnArray = { tl, tm, tr, ml, mm, mr, bl, bm, br };

	/**
	 * returns the length of the button array
	 * @return length of btnArray
	 */
	public int btnArrayLength() {
		return btnArray.length;
	}

	/**
	 * returns the button array to allow other classes access
	 * @return value of btnArray
	 */
	public Button[] getBtnArray() {
		return btnArray;
	}

	/**
	 * method to display the GUI
	 */
	public void displayGUI() {
		shell.setText("Professional Programming Assignment");
		createGUI();
		shell.setSize(330, 370);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	//creates the GUI
	private void createGUI() {
		Label label = new Label(shell, SWT.CENTER);
		label.setSize(300, 20);
		label.setLocation(10, 20);
		label.setFont(new Font(display, "Arial", 14, SWT.BOLD));
		label.setText("NOUGHTS AND CROSSES");

		status.setSize(150, 15);
		status.setLocation(85, 45);
		status.setFont(new Font(display, "Arial", 8, SWT.BOLD));
		status.setForeground(new org.eclipse.swt.graphics.Color(31, 181, 71));
		status.setText("Click Start...");

		startButton.setSize(250, 30);
		startButton.setLocation(30, 290);
		startButton.setFont(new Font(display, "Arial", 14, SWT.BOLD));
		startButton.setText("Start");

		setGameBoard();
		addButtonListeners();
	}

	//sets the location of the game board tiles
	private void setGameBoard() {
		for (int i = 0; i < btnArray.length; i++) {
			btnArray[i].setSize(75, 75);
		}

		tl.setLocation(50, 60);
		tm.setLocation(120, 60);
		tr.setLocation(190, 60);
		ml.setLocation(50, 130);
		mm.setLocation(120, 130);
		mr.setLocation(190, 130);
		bl.setLocation(50, 200);
		bm.setLocation(120, 200);
		br.setLocation(190, 200);

		disableButtons();
	}

	//sets the game board to a new game state
	private void setupGameBoard() {
		for (int i = 0; i < btnArray.length; i++) {
			btnArray[i].setText("");
		}
	}

	//adds listeners to the game board tiles
	private void addButtonListeners() {
		// add start button listener
		startButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				setupGameBoard();
				Main.run.chkBoard.setup();
				Random rn = new Random();
				Main.run.player = rn.nextInt(2);
				startButton.setText("Restart");
				Main.run.loop();
			}
		});

		// add listeners to button array
		for (int i = 0; i < btnArray.length; i++) {
			Button btn = btnArray[i];
			btn.addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event event) {
					buttonHandler(btn);
				}
			});
		}
	}

	/**
	 * sets the text on the start button to start when a new game can be started
	 */
	public void startButtonText() {
		startButton.setText("Start");
	}

	/**
	 * enables the game board tiles(buttons)
	 */
	public void enableButtons() {
		for (int i = 0; i < btnArray.length; i++) {
			btnArray[i].setEnabled(true);
		}
	}

	/**
	 * disables the game board tiles(buttons)
	 */
	public void disableButtons() {
		for (int i = 0; i < btnArray.length; i++) {
			btnArray[i].setEnabled(false);
		}
	}

	/**
	 * handles the functionality of the game board tiles
	 * @param btn the button that is being manipulated
	 */
	private void buttonHandler(Button btn) {
		if (Main.run.chkBoard.chkPlayerMap(btn)) {
			disableButtons();
			if (Main.run.getPlayer() == 1) {
				Main.run.chkBoard.setButtonValue(btn, 1);
			} else {
				Main.run.chkBoard.setButtonValue(btn, 2);
			}
			setButtonImage(btn);
			Main.run.loop();
		}
	}

	/**
	 * changes the image(text) on the game board tiles
	 * @param btn the button that is being manipulated
	 */
	private void setButtonImage(Button btn) {
		String text = "";
		if (Main.run.getPlayer() == 1) {
			text = "O";
		} else {
			text = "X";
		}
		btn.setFont(new Font(display, "Arial", 50, SWT.BOLD));
		btn.setText(text);
	}

	/**
	 * allows access to the button handler method by outside classes
	 * @param btn button the be passed thru
	 */
	public void compHandler(Button btn) {
		buttonHandler(btn);
	}

	/**
	 * sets the text of the status label
	 * @param str status to be displayed
	 */
	public void setStatus(String str) {
		status.setText(str);
	}

}

