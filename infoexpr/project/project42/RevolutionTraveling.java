package infoexpr.project.project42;

/*=============================================================
個別任務  目標周回  公転走行
=============================================================*/

public class RevolutionTraveling
{
	private int curvePowerR;
	private int curvePowerL;

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private TargetSeek targetSeek;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public RevolutionTraveling()
	{
		targetSeek = new TargetSeek();
	}

	/*------------------------------------------------------
	公転
	------------------------------------------------------*/

	public void revolution()
	{
		while (!targetSeek.seekByColor())
		{
			traveling();
		}
	}

	public void traveling()
	{
		TargetRound.wheelActuator.runCurve(curvePowerL, curvePowerR);

	}
}
