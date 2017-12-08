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

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public RevolutionTraveling()
	{

	}

	/*------------------------------------------------------
	公転
	------------------------------------------------------*/

	public void revolution()
	{
		while (true)
		{
			Traveling();
		}
	}

	public void Traveling()
	{
		TargetRound.wheelActuator.runCurve(curvePowerL, curvePowerR);

	}
}
