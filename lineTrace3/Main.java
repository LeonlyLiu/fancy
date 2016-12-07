package lineTrace3;
//vol.3
//leonly
//20161207
public class Main
{
	public void main(String[] args)
	{
		LineTrace lt = new LineTrace();
		RedSearch rs = new RedSearch();
		RunStraight rst = new RunStraight();

		rst.runStraight(1600, 300);
		rs.detectRed();
		lt.run();

	}
}
