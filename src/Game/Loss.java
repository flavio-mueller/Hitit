package Game;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Loss {

	protected Shell shlloss;

	/**
	 * @wbp.parser.entryPoint
	 */
	
	public static void startgui() {
		try {
			Loss window = new Loss();
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
		shlloss.open();
		shlloss.layout();
		while (!shlloss.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlloss = new Shell(SWT.CLOSE| SWT.MIN);
		shlloss.setImage(SWTResourceManager.getImage(Loss.class, "/img/hitit.png"));
		shlloss.setSize(450, 300);
		shlloss.setText("Hitit");

		Button button = new Button(shlloss, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		button.setText("Close!");
		button.setBounds(349, 226, 75, 25);
		
		Play.playsound("res/loss.wav");

		Button button_1 = new Button(shlloss, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlloss.setVisible(false);
				Main.windows.add(shlloss);
				Main.main(null);
			}
		});
		button_1.setText("Try it again!");
		button_1.setBounds(268, 226, 75, 25);

		Label lblTheComputerHas = new Label(shlloss, SWT.NONE);
		lblTheComputerHas.setText("The computer has won!");
		lblTheComputerHas.setFont(SWTResourceManager.getFont("Segoe UI", 27, SWT.NORMAL));
		lblTheComputerHas.setBounds(10, 10, 414, 75);

	}

}
