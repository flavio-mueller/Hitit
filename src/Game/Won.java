package Game;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Won {

	protected Shell shlwon;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	
	public static void startgui() {
		try {
			Won window = new Won();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 * @param args
	 */

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlwon.open();
		shlwon.layout();
		while (!shlwon.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		
		Play.playsound("won.wav");
		
		shlwon = new Shell();
		shlwon.setSize(450, 300);
		shlwon.setText("SWT Application");
		
		Label lblYouHaveWon = new Label(shlwon, SWT.NONE);
		lblYouHaveWon.setText("You have won!");
		lblYouHaveWon.setFont(SWTResourceManager.getFont("Segoe UI", 27, SWT.NORMAL));
		lblYouHaveWon.setBounds(10, 10, 414, 75);
		
		Button button = new Button(shlwon, SWT.NONE);
		button.setText("Back to the selection!");
		button.setBounds(140, 226, 124, 25);
		
		Button button_1 = new Button(shlwon, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlwon.setVisible(false);
				Main.windows.add(shlwon);
				Main.main(null);
			}
		});
		button_1.setText("Try it again!");
		button_1.setBounds(268, 226, 75, 25);
		
		Button button_2 = new Button(shlwon, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		button_2.setText("Close!");
		button_2.setBounds(349, 226, 75, 25);

	}

}
