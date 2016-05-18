//package GUI;
//
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Button;
//
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.MessageBox;
//import org.eclipse.wb.swt.SWTResourceManager;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//
//public class Choose {
//
//	protected static Shell shlHitit;
//
//	/**
//	 * Launch the application.
//	 * 
//	 * @param args
//	 * @wbp.parser.entryPoint
//	 */
//	
//	public static void startgui() {
//		try {
//			Choose window = new Choose();
//			window.open();
//			} catch (Exception e) {
//			e.printStackTrace();
//			}
//	}
//
//	/**
//	 * Open the window.
//	 * @wbp.parser.entryPoint
//	 */
//	
//	Display display = Display.getDefault();
//	
//	public void open() {
//		display = Display.getDefault();
//		createContents();
//		shlHitit.open();
//		shlHitit.layout();
//		while (!shlHitit.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//	}
//
//	/**
//	 * Create contents of the window.
//	 * @wbp.parser.entryPoint
//	 */
//	public static Button[][] pcb = new Button[5][5];
//	protected void createContents() {
//		shlHitit = new Shell();
//		shlHitit.setSize(319, 316);
//		shlHitit.setText("Choose");
//		
//		//set Labels
//		Label lblPunkteSetzen = new Label(shlHitit, SWT.NONE);
//		lblPunkteSetzen.setFont(SWTResourceManager.getFont("Segoe UI", 17, SWT.NORMAL));
//		lblPunkteSetzen.setBounds(10, 10, 205, 31);
//		lblPunkteSetzen.setText("Set your points");
//		
//		Label lblChoosePoints = new Label(shlHitit, SWT.NONE);
//		lblChoosePoints.setBounds(10, 47, 83, 15);
//		lblChoosePoints.setText("choose 5 points");
//	
//		//set Checkboxes
//		for(int i=0;i<5;i++){
//			for(int j=0;j<5;j++){
//				int x=68;
//				int y=68;
//				x = x+(i*35);
//				y = y+(j*30);
//				pcb[i][j] = new Button(shlHitit, SWT.CHECK);
//				pcb[i][j].setBounds(x, y, 33, 36);
//			}
//		}
//
//		//set The Button to save
//		Button saveposition = new Button(shlHitit, SWT.NONE);
//		saveposition.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				
//				int active = 0;
//				
//				for(int i=0;i<5;i++){
//					for(int j=0;j<5;j++){
//						if(pcb[i][j].getSelection()){
//							active++;
//						}
//					}
//				}
//				//check if 5 boxes are checked
//				if(active!=5){
//					MessageBox msgbox = new MessageBox(shlHitit, SWT.RETRY);
//					msgbox.setText("Warning");
//					msgbox.setMessage("Please choose 5 Fields!");
//					msgbox.open();
//				}
//				else{
//					for(int i=0;i<5;i++){
//						for(int j=0;j<5;j++){
//							pcb[i][j].getSelection();
//							
//						}
//					}
//					shlHitit.setVisible(false);
//					Game.startgui();
//				}
//				
//			}
//		});
//		saveposition.setBounds(200, 242, 75, 25);
//		saveposition.setText("Save");
//	}
//}
