package infoexpr.project.Project21;

import infoexpr.Library.Mechanism.Actuator.WheelActuator;

/*=============================================================
図形模走  単純模走
=============================================================*/

public class SimpleFollowing
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	public final WheelActuator wheelActuator;

	private final StraightFollowing straightFollowing;
	private final CurveFollowing curveFollowing;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public SimpleFollowing()
	{
		this.wheelActuator = new WheelActuator();

		this.straightFollowing = new StraightFollowing(this.wheelActuator);
		this.curveFollowing = new CurveFollowing(this.wheelActuator);
	}

	/*------------------------------------------------------
	メイン
	------------------------------------------------------*/

	public void doGame()
	{
		// ---- 直線模走
		this.straightFollowing.traveling();

		// ---- 曲線模走
		this.curveFollowing.traveling();
	}

	public static void main(String[] args)
	{
		SimpleFollowing simpleFollowing = new SimpleFollowing();
		simpleFollowing.doGame();
	}
}
