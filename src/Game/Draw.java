package Game;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Draw {

	protected Shell shldraw;
	
	/**
	 * @wbp.parser.entryPoint
	 */

	public static void startgui() {
		try {
			Draw window = new Draw();
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
		shldraw.open();
		shldraw.layout();
		while (!shldraw.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shldraw = new Shell();
		shldraw.setImage(SWTResourceManager.getImage(Draw.class, "/img/hitit.png"));
		shldraw.setSize(450, 300);
		shldraw.setText("Hitit");
		
		Play.playsound("res/draw.wav");

		Label lblItsADraw = new Label(shldraw, SWT.NONE);
		lblItsADraw.setText("It's a draw!");
		lblItsADraw.setFont(SWTResourceManager.getFont("Segoe UI", 27, SWT.NORMAL));
		lblItsADraw.setBounds(10, 10, 414, 75);

		Button button_1 = new Button(shldraw, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shldraw.setVisible(false);
				Main.windows.add(shldraw);
				Main.main(null);
			}
		});
		button_1.setText("Try it again!");
		button_1.setBounds(268, 226, 75, 25);

		Button button_2 = new Button(shldraw, SWT.NONE);
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
