//package GUI;
//
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.MessageBox;
//
//import java.util.Random;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.wb.swt.SWTResourceManager;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.widgets.Canvas;
//
//public class Game {
//
//	protected Shell shlGame;
//
//	
//	int pgetroffen = 0;
//	int cgetroffen = 0;
//	/**
//	 * Launch the application.
//	 * 
//	 * @param args
//	 */
//	public static void startgui() {
//		try {
//			Game window = new Game();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Open the window.
//	 */
//	public void open() {
//		Display display = Display.getDefault();
//		createContents();
//		shlGame.open();
//		shlGame.layout();
//		while (!shlGame.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//	}
//
//	/**
//	 * Create contents of the window.
//	 * 
//	 * @wbp.parser.entryPoint
//	 */
//	protected void createContents() {
//		shlGame = new Shell();
//		shlGame.setSize(450, 301);
//		shlGame.setText("Game");
//
//		// array for Computer
//		Button[][] ccb = new Button[5][5];
//
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				ccb[i][j] = new Button(shlGame, SWT.CHECK);
//			}
//		}
//
//		// Array of computer gets filled with 5 checked checkboxes
//		Random rnd = new Random();
//
//		for (int x = 0; x < 5; x++) {
//			int i = rnd.nextInt(5);
//			int j = rnd.nextInt(5);
//			if (ccb[i][j].getSelection() != true) {
//				ccb[i][j].setSelection(true);
//			} else {
//				x--;
//			}
//		}
//
//		// Checkboxes for player to shoot
//		Button[][] hcb = new Button[5][5];
//
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				int x = 247;
//				int y = 41;
//				x = x + (i * 33);
//				y = y + (j * 36);
//				hcb[i][j] = new Button(shlGame, SWT.CHECK);
//				hcb[i][j].setBounds(x, y, 25, 28);
//			}
//		}
//
//		// Checkboxes witch the player has chosen in the first shell
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < 5; j++) {
//				int x = 35;
//				int y = 41;
//				x = x + (i * 33);
//				y = y + (j * 36);
//				Choose.pcb[i][j].setBounds(x, y, 25, 28);
//				Choose.pcb[i][j].setParent(shlGame);
//				Choose.pcb[i][j].setEnabled(false);
//			}
//		}
//
//		// labels on the shell
//		Label lblYourField = new Label(shlGame, SWT.NONE);
//		lblYourField.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
//		lblYourField.setBounds(10, 10, 83, 25);
//		lblYourField.setText("Your Field");
//
//		Label lblComputersField = new Label(shlGame, SWT.NONE);
//		lblComputersField.setText("Computers Field");
//		lblComputersField.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
//		lblComputersField.setBounds(281, 10, 143, 25);
//
//		Label label = new Label(shlGame, SWT.SEPARATOR | SWT.VERTICAL);
//		label.setBounds(212, 0, 2, 261);
//
//		// Button for the player to hit.
//		Button btnHit = new Button(shlGame, SWT.NONE);
//		btnHit.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//
//				int active = 0;
//
//				
//				//start player part
//				// checks if only one Checkbox is checked
//				for (int i = 0; i < 5; i++) {
//					for (int j = 0; j < 5; j++) {
//						if (hcb[i][j].getSelection()) {
//							if (hcb[i][j].getVisible()) {
//								active++;
//							}
//						}
//					}
//				}
//
//				// Messagebox if more or less than one checkbox is chosen.
//				if (active != 1) {
//					MessageBox msgbox = new MessageBox(shlGame, SWT.RETRY);
//					msgbox.setText("Warning");
//					msgbox.setMessage("Please choose only 1 Field!");
//					msgbox.open();
//				}
//				
//				// if 1 checkbox is chosen
//				else {
//					int x = 0;
//					int y = 0;
//
//					//gets the place in the array from the chosen checkbox
//					for (int i = 0; i < 5; i++) {
//						for (int j = 0; j < 5; j++) {
//							if (hcb[i][j].getSelection() && hcb[i][j].getVisible()) {
//								x = i;
//								y = j;
//							}
//						}
//					}
//					
//					//if the player hits the checkbox from the pc
//					if (ccb[x][y].getSelection()) {
//						int f = 247 + (x * 33);
//						int g = 48 + (y * 36);
//						setCanvasgreen(f, g, 14, 13);
//						hcb[x][y].setVisible(false);
//						pgetroffen++;
//
//					} 
//					//if the player doesnt hit the right checkbox
//					else {
//						int f = 247 + (x * 33);
//						int g = 48 + (y * 36);
//						setCanvasred(f, g, 14, 13);
//						hcb[x][y].setVisible(false);
//					}
//
//				}
//				//end player part
//
//				
//				//start computer part
//				int p = 0;
//				int q = 0;
//				
//				
//				//checks if the computer already hit this checkbox
//				boolean foundValid = false;
//				while (!foundValid) {
//					
//					Random rnd = new Random();
//					
//					p = rnd.nextInt(5);
//					q = rnd.nextInt(5);
//
//					if (Choose.pcb[p][q].getVisible()) {
//						foundValid = true;
//					}
//				}
//				
//				//checks if the checkbox is checked
//				if (Choose.pcb[p][q].getSelection()) {
//					int f = 35 + (p * 33);
//					int g = 48 + (q * 36);
//					setCanvasgreen(f, g, 14, 13);
//					Choose.pcb[q][p].setVisible(false);
//					cgetroffen++;
//				} else {
//					int f = 35 + (p * 33);
//					int g = 48 + (q * 36);
//					setCanvasred(f, g, 14, 13);
//					Choose.pcb[p][q].setVisible(false);
//				}
//				//end computer part
//
//				//start Winnerpart
//				
//				System.out.println(cgetroffen);
//				System.out.println(pgetroffen);
//				
//				if(pgetroffen==5 && cgetroffen<5){
//					Msgbox("Won", "You have won the game!");
//				}else if(pgetroffen<5 && cgetroffen==5){
//					Msgbox("Loss", "You have loss the game!");
//				}else if(pgetroffen==5 && cgetroffen==5){
//					Msgbox("Draw", "Nobody has won the game!");		
//				}
//			}
//		});
//		btnHit.setBounds(349, 227, 75, 25);
//		btnHit.setText("Hit");
//
//	}
//	
//	private void setCanvasgreen(int posx, int posy, int height, int width){
//		Canvas canvasc = new Canvas(shlGame, SWT.NONE);
//		canvasc.setBounds(posx, posy, height, width);
//		canvasc.setBackground(canvasc.getDisplay().getSystemColor(SWT.COLOR_GREEN));
//	}
//	
//	private void setCanvasred(int posx, int posy, int height, int width){
//		Canvas canvasc = new Canvas(shlGame, SWT.NONE);
//		canvasc.setBounds(posx, posy, height, width);
//		canvasc.setBackground(canvasc.getDisplay().getSystemColor(SWT.COLOR_RED));
//	}
//	
//	private void Msgbox(String text, String Message){
//		MessageBox msgbox = new MessageBox(shlGame);
//		msgbox.setText(text);
//		msgbox.setMessage(Message);
//		msgbox.open();
//	}
//}
