package Game;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Play {

	protected Shell shlplay;

	int pchit = 0;
	int phit = 0;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Play window = new Play();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void startgui() {
		try {
			Play window = new Play();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlplay.open();
		shlplay.layout();
		while (!shlplay.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		shlplay = new Shell();
		shlplay.setSize(900, 450);
		shlplay.setText("SWT Application");

		// set all the labels and stuff
		Label lblYourField = new Label(shlplay, SWT.NONE);
		lblYourField.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblYourField.setBounds(10, 10, 66, 21);
		lblYourField.setText("your field");

		Label label = new Label(shlplay, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 37, 864, 2);

		Label lblComputersField = new Label(shlplay, SWT.NONE);
		lblComputersField.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblComputersField.setBounds(458, 10, 108, 21);
		lblComputersField.setText("computers field");

		Label label_1 = new Label(shlplay, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(450, 0, 2, 411);

		Label lblComputerAttaksThis = new Label(shlplay, SWT.NONE);
		lblComputerAttaksThis.setBounds(214, 15, 227, 15);
		lblComputerAttaksThis.setText("computer hits this side");

		Label lblYouAttackThis = new Label(shlplay, SWT.NONE);
		lblYouAttackThis.setText("please choose max. 5 fields to hit");
		lblYouAttackThis.setBounds(647, 16, 227, 15);

		// paint hthe players checkboxes
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int abstandright = 75;
				int abstandtop = 75;
				int x = abstandright + (i * 30);
				int y = abstandtop + (j * 30);
				Choose.playerset[i][j].setBounds(x, y, 13, 13);
				Choose.playerset[i][j].setParent(shlplay);
				Choose.playerset[i][j].setEnabled(false);
			}
		}

		// paint the checkboxes for the player to hit
		Button[][] playerhit = new Button[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int abstandright = 75 + 450;
				int abstandtop = 75;
				int x = abstandright + (i * 30);
				int y = abstandtop + (j * 30);
				playerhit[i][j] = new Button(shlplay, SWT.CHECK);
				playerhit[i][j].setBounds(x, y, 13, 13);
			}
		}

		// computer sets its checkboxes
		Button[][] computerset = new Button[10][10];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				computerset[i][j] = new Button(shlplay, SWT.CHECK);
			}
		}

		Random rnd = new Random();

		for (int a = 0; a < 10; a++) {
			int i = rnd.nextInt(10);
			int j = rnd.nextInt(10);
			if (computerset[i][j].getSelection()) {
				a--;
			} else {
				computerset[i][j].setSelection(true);
			}
		}

		// button to hit one field
		Button btnhit = new Button(shlplay, SWT.NONE);
		btnhit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				boolean mtone = false;

				int active = 0;

				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (playerhit[i][j].getSelection() && playerhit[i][j].getVisible()) {
							active++;
						}
					}
				}

				if (active > 5) {
					MsgBox("Warning", "Please choose max. 5 fields");
					mtone = true;
				} else {

					for (int p = 0; p < active; p++) {
						int a = 0;
						int b = 0;

						// gets the position in the array of the choosen field
						for (int i = 0; i < 10; i++) {
							for (int j = 0; j < 10; j++) {
								if (playerhit[i][j].getSelection() && playerhit[i][j].getVisible()) {
									a = i;
									b = j;
								}
							}
						}

						if (computerset[a][b].getSelection() && playerhit[a][b].getVisible()) {
							// if the chosen field was correct
							playerhit[a][b].setVisible(false);
							int posx = 450 + 75 + (a * 30);
							int posy = 75 + (b * 30);
							setCanvasgreen(posx, posy, 13, 13);
							phit++;
						} else {
							playerhit[a][b].setVisible(false);
							int posx = 450 + 75 + (a * 30);
							int posy = 75 + (b * 30);
							setCanvasred(posx, posy, 13, 13);
						}
					}

				}
				// end playerpart

				if (!mtone) {
					// start computerpart

					for (int i = 0; i < active; i++) {

						Random rnd = new Random();

						boolean frei = true;

						int x = 0;
						int y = 0;

						while (frei) {

							int a = rnd.nextInt(10);
							int b = rnd.nextInt(10);

							if (Choose.playerset[a][b].getVisible()) {
								x = a;
								y = b;
								frei = false;
							}

						}

						if (Choose.playerset[x][y].getSelection() && Choose.playerset[x][y].getVisible()) {
							// if the computer hit the players box
							Choose.playerset[x][y].setVisible(false);
							int posx = 75 + (x * 30);
							int posy = 75 + (y * 30);
							setCanvasgreen(posx, posy, 13, 13);
							pchit++;

						} else {
							// if the computer didnt hit the playersbox
							Choose.playerset[x][y].setVisible(false);
							int posx = 75 + (x * 30);
							int posy = 75 + (y * 30);
							setCanvasred(posx, posy, 13, 13);
						}
					}
				}
				
				if(pchit==10 && phit <10){
					MsgBox("LOSS!", "You have lost the game!");
				}else if(pchit <10 && phit ==10){
					MsgBox("WON!", "You have won the game!");
				}else if(pchit == 10 && phit ==10){
					MsgBox("DRAW!", "It's a draw!");
				}
			}
		});
		btnhit.setBounds(799, 376, 75, 25);
		btnhit.setText("Hitit");

	}

	private void MsgBox(String Text, String Message) {
		MessageBox msgbox = new MessageBox(shlplay);
		msgbox.setText(Text);
		msgbox.setMessage(Message);
		msgbox.open();
	}

	private void setCanvasgreen(int posx, int posy, int height, int width) {
		Canvas canvasc = new Canvas(shlplay, SWT.NONE);
		canvasc.setBounds(posx, posy, height, width);
		canvasc.setBackground(canvasc.getDisplay().getSystemColor(SWT.COLOR_GREEN));
	}

	private void setCanvasred(int posx, int posy, int height, int width) {
		Canvas canvasc = new Canvas(shlplay, SWT.NONE);
		canvasc.setBounds(posx, posy, height, width);
		canvasc.setBackground(canvasc.getDisplay().getSystemColor(SWT.COLOR_RED));
	}
}
