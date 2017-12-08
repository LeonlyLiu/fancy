package infoexpr.project.Project21;

/*=============================================================
図形模走  規定模走
=============================================================*/

public class FigureFollowing extends SimpleFollowing
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private final int TimeTotal = 1500;
	private int timeCurve = 800;
	private int timeStraight = TimeTotal - timeCurve;
	private StraightFollowing straightFollowing;
	private CurveFollowing curveFollowing;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public FigureFollowing()
	{
		super();

	}

	/*------------------------------------------------------
	メイン
	------------------------------------------------------*/

	public void doGame()
	{
		// ---- 警音任務(並列処理)
		Thread missionBeepThread = new Thread(new BeepAtPosition());
		missionBeepThread.start();

		// ---- 直線模走(時間指定)
		this.straightFollowing.traveling(timeStraight);

		// ---- 曲線模走
		this.curveFollowing.traveling();

		try
		{
			missionBeepThread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace(System.out);
		}
	}

	public static void main(String[] args)
	{
		FigureFollowing figureFollowing = new FigureFollowing();
		figureFollowing.doGame();
	}
}
