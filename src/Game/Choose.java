package Game;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class Choose {

	int q = 0;
	int i = 0;
	int j = 0;
	Timer t;
	boolean isvisible = true;

	protected static Shell shlchoose;

	/**
	 * @wbp.parser.entryPoint
	 */

	public static void startgui() {
		try {
			Choose window = new Choose();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlchoose.open();
		shlchoose.layout();
		while (!shlchoose.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	public static Button[][] playerset = new Button[10][10];

	protected void createContents() {

		t = new Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				play("src/choose.wav");
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

		shlchoose = new Shell(SWT.CLOSE | SWT.MIN);
		shlchoose.setImage(SWTResourceManager.getImage("C:\\Users\\FlavioMueller\\git\\Hitit\\src\\hitit.png"));
		shlchoose.setSize(450, 449);
		shlchoose.setText("SWT Application");

		Label lblangeklickt = new Label(shlchoose, SWT.NONE);
		lblangeklickt.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblangeklickt.setBounds(71, 375, 55, 25);

		Label lblChooseBoxes = new Label(shlchoose, SWT.NONE);
		lblChooseBoxes.setImage(null);
		lblChooseBoxes.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblChooseBoxes.setBounds(10, 10, 123, 21);
		lblChooseBoxes.setText("Choose 10 boxes!");
		q = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int abstandtop = 75;
				int abstandright = 75;
				int x = abstandright + (i * 30);
				int y = abstandtop + (j * 30);

				playerset[i][j] = new Button(shlchoose, SWT.CHECK);
				playerset[i][j].setBounds(x, y, 13, 13);

				playerset[i][j].addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						Button button = (Button) e.widget;
						if (button.getSelection()) {
							q++;
							lblangeklickt.setText("" + q);
							if (q == 10) {

								for (int i = 0; i < 10; i++) {
									for (int j = 0; j < 10; j++) {
										if (!playerset[i][j].getSelection()) {
											playerset[i][j].setEnabled(false);
										}
									}
								}

							}

						} else {
							q--;
							for (int i = 0; i < 10; i++) {
								for (int j = 0; j < 10; j++) {
									if (!playerset[i][j].getSelection()) {
										playerset[i][j].setEnabled(true);
									}
								}
							}
							lblangeklickt.setText("" + q);
						}
					}
				});

			}
		}

		Label line = new Label(shlchoose, SWT.SEPARATOR | SWT.HORIZONTAL);
		line.setBounds(10, 37, 414, 2);

		Button btnsave = new Button(shlchoose, SWT.NONE);
		btnsave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int chosen = 0;

				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (playerset[i][j].getSelection()) {
							chosen++;
						}
					}
				}

				if (chosen != 10) {
					Play.playsound("confirm.stargui");
					MessageBox msgBox = new MessageBox(shlchoose);
					msgBox.setText("Warning!");
					msgBox.setMessage("Please choose 10 Boxes!");
					msgBox.open();
				} else {
					isvisible = false;
					shlchoose.setVisible(false);
					Main.windows.add(shlchoose);
					Play.startgui();
				}

			}
		});
		btnsave.setBounds(349, 375, 75, 25);
		btnsave.setText("Save");

		Label lblChosen = new Label(shlchoose, SWT.NONE);
		lblChosen.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblChosen.setBounds(10, 375, 55, 25);
		lblChosen.setText("chosen:");
	}
}
