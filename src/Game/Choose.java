package Game;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Choose {

	int q=0;
	int i=0;
	int j=0;
	
	protected static Shell shlchoose;

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
	public static void main(String[] args) {
		try {
			Choose window = new Choose();
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
		shlchoose = new Shell();
		shlchoose.setSize(450, 449);
		shlchoose.setText("SWT Application");

		Label lblangeklickt = new Label(shlchoose, SWT.NONE);
		lblangeklickt.setBounds(62, 385, 55, 15);

		Label lblChooseBoxes = new Label(shlchoose, SWT.NONE);
		lblChooseBoxes.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblChooseBoxes.setBounds(10, 10, 123, 21);
		lblChooseBoxes.setText("Choose 10 Boxes!");
		q=0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int abstandtop = 75;
				int abstandright = 75;
				int x = abstandright + (i * 30);
				int y = abstandtop + (j * 30);

				playerset[i][j] = new Button(shlchoose, SWT.CHECK);
				playerset[i][j].setBounds(x, y, 13, 13);
				
				
				playerset[i][j].addSelectionListener(new SelectionAdapter()
				{
				    @Override
				    public void widgetSelected(SelectionEvent e)
				    {
				        Button button = (Button) e.widget;
				        if (button.getSelection())
				            q++;
				        lblangeklickt.setText(""+q);
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
					MessageBox msgBox = new MessageBox(shlchoose);
					msgBox.setText("Warning!");
					msgBox.setMessage("Please choose 10 Boxes!");
					msgBox.open();
				} else {
					shlchoose.setVisible(false);
					Play.startgui();
				}

			}
		});
		btnsave.setBounds(349, 375, 75, 25);
		btnsave.setText("Save");

		Label lblChosen = new Label(shlchoose, SWT.NONE);
		lblChosen.setBounds(10, 385, 41, 15);
		lblChosen.setText("chosen:");
	}
}
