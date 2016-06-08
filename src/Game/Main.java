package Game;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Shell;

public class Main {

	public static ArrayList<Shell> windows = new ArrayList<Shell>();

	static Thread guiStarter = new Thread();
	
	public static void main(String[] args) {
		
			startgui();
	}

	public static void startgui() {
		cleanUp();
		if(!guiStarter.isAlive()){
		guiStarter.run();
		}
		Choose.startgui();
	}
	
	public static void cleanUp()
	{
		for(Shell s : windows)
		{
			try
			{
				s.close();
				s.dispose();
			}catch(Exception e)
			{}
		}
		windows.clear();
	}
}