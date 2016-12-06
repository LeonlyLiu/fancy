package lineTrace2;

public class Main
{
	public void main(String[] args)
	{
		Thread ltThread = new Thread(new LineTrace());
		Thread greenThread = new Thread(new GreenSearch());

		RedSearch rs = new RedSearch();
		RunStraight rst = new RunStraight();

		rst.runStraight(1600, 300);
		rs.detectRed();

		ltThread.start();
		greenThread.start();
		try
		{
			ltThread.join();
			greenThread.join();
		} catch (InterruptedException e)
		{
		}
	}
}
