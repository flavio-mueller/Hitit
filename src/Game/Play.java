package Game;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;

public class Play {

	int q = 0;

	protected static Shell shlplay;

	Timer t;
	int pchit = 0;
	int phit = 0;
	boolean isvisible = true;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	/**
	 * @wbp.parser.entryPoint
	 */

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
		t.cancel();
		isvisible = false;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				play("res/ingame.wav");
			}

			public void play(String filename) {
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File(filename)));
					clip.start();
					Thread.sleep(500);
					while (clip.isRunning() && isvisible) {
						Thread.sleep(1000);
					}
					clip.stop();
					clip.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}, 0, 305_000);

		shlplay = new Shell(SWT.CLOSE | SWT.MIN);
		shlplay.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				System.exit(0);
			}
		});
		shlplay.setImage(SWTResourceManager.getImage(Play.class, "/img/hitit.png"));
		shlplay.setSize(900, 450);
		shlplay.setText("Hitit");

		// set all the labels and stuff
		Label lblYourField = new Label(shlplay, SWT.NONE);
		lblYourField.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblYourField.setBounds(10, 10, 66, 21);
		lblYourField.setText("Your field");

		Label pntcomp = new Label(shlplay, SWT.NONE);
		pntcomp.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		pntcomp.setBounds(434, 51, 10, 21);
		pntcomp.setText("0");

		Label pntplay = new Label(shlplay, SWT.NONE);
		pntplay.setText("0");
		pntplay.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		pntplay.setBounds(458, 51, 10, 21);

		Label label = new Label(shlplay, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 37, 864, 2);

		Label lblComputersField = new Label(shlplay, SWT.NONE);
		lblComputersField.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblComputersField.setBounds(458, 10, 111, 21);
		lblComputersField.setText("Computers field");

		Label label_1 = new Label(shlplay, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(450, 0, 2, 421);

		Label lblYouAttackThis = new Label(shlplay, SWT.NONE);
		lblYouAttackThis.setText("please choose max. 5 fields to hit");
		lblYouAttackThis.setBounds(647, 16, 227, 15);

		Label lblgewählt = new Label(shlplay, SWT.NONE);
		lblgewählt.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblgewählt.setBounds(511, 390, 55, 20);

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

				q = 0;
				playerhit[i][j].addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Button button = (Button) e.widget;
						if (button.getSelection()) {
							q++;
							lblgewählt.setText(" " + q);

							if (q == 5) {
								for (int i = 0; i < 10; i++) {
									for (int j = 0; j < 10; j++) {
										if (!playerhit[i][j].getSelection()) {
											playerhit[i][j].setEnabled(false);
										}
									}
								}
							}

						} else {
							q--;
							for (int i = 0; i < 10; i++) {
								for (int j = 0; j < 10; j++) {
									if (!playerhit[i][j].getSelection()) {
										playerhit[i][j].setEnabled(true);
									}
								}
							}
							lblgewählt.setText(" " + q);
						}
					}
				});

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
				q = 0;

				lblgewählt.setText(" " + q);
				boolean mtone = false;

				int active = 0;

				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (playerhit[i][j].getSelection() && playerhit[i][j].getVisible()) {
							active++;
						}
					}
				}

				

					for (int i = 0; i < 10; i++) {
						for (int j = 0; j < 10; j++) {
							if (!playerhit[i][j].getSelection()) {
								playerhit[i][j].setEnabled(true);
							}
						}
					}
					
					playsound("res/hit.wav");

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

				pntcomp.setText(Integer.toString(pchit));
				pntplay.setText(Integer.toString(phit));

				if (pchit == 10 && phit < 10) {
					isvisible = false;
					shlplay.setVisible(false);
					Main.windows.add(shlplay);
					Loss.startgui();
				} else if (pchit < 10 && phit == 10) {
					isvisible = false;
					shlplay.setVisible(false);
					Main.windows.add(shlplay);
					Won.startgui();
				} else if (pchit == 10 && phit == 10) {
					isvisible = false;
					shlplay.setVisible(false);
					Main.windows.add(shlplay);
					Draw.startgui();
				}
			}
		});
		btnhit.setBounds(799, 376, 75, 25);
		btnhit.setText("Hitit");

		Label lblChosen = new Label(shlplay, SWT.NONE);
		lblChosen.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblChosen.setBounds(458, 390, 66, 21);
		lblChosen.setText("chosen:");

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

	public static synchronized void playsound(String path) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File(path)));
					clip.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
