package Game;

public class Main {

	public static void main(String[] args) 
	{
		Choose.startgui();

	}

	public static void startgui()
	{
		Thread guiStarter = new Thread()
				{
				public void run()
				{
					Choose.startgui();
				}
				};
				guiStarter.start();
	}
}
