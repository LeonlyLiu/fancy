package infoexpr.project.Project21;

import infoexpr.Library.Mechanism.Actuator.WheelActuator;

/*=============================================================
図形模走  曲線模走
=============================================================*/

public class CurveFollowing
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 確定走行のパラメタ(修正が必要)
	// -- V3での右側転向 180cm
	private int powerV3 = -720;
	private int timeV3 = 2000;
	// -- C1での左方曲進 半径40cm 180度
	private int powerC1L = 480;
	private int powerC1R = 720;
	private int timeC1 = 4000;
	// -- C2での左方曲進 半径30cm 180度
	private int powerC2L = 360;
	private int powerC2R = 720;
	private int timeC2 = 3000;

	private WheelActuator actuator;

	public CurveFollowing(WheelActuator actuator)
	{
		this.actuator = actuator;
	}
	/*------------------------------------------------------
	曲線模走 順次実行
	------------------------------------------------------*/

	public void traveling()
	{
		// ---- C1での左方曲進
		actuator.runCurveByTime(powerC1L, powerC1R, timeC1);

		// ---- V3での右側転向
		actuator.runSpinByTime(powerV3, timeV3);

		// ---- C2での左方曲進
		actuator.runCurveByTime(powerC2L, powerC2R, timeC2);
	}

}
