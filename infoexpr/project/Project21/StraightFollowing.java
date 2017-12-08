package infoexpr.project.Project21;

import infoexpr.Library.Mechanism.Actuator.WheelActuator;

/*=============================================================
図形模走  直線模走
=============================================================*/

public class StraightFollowing
{

	private WheelActuator actuator;

	public StraightFollowing(WheelActuator actuator)
	{
		this.actuator = actuator;
	}

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 確定走行のパラメタ(修正が必要)
	// -- L1での前方直進 50cm
	private int powerL1 = 720;
	private int timeL1 = 2500;
	// -- L2での前方直進 80cm
	private int powerL2 = 720;
	private int timeL2 = 4500;
	// -- V1での左側転向 90度
	private int powerV1 = 720;
	private int timeV1 = 1000;
	// -- V2での左側転向 60度
	private int powerV2 = 360;
	private int timeV2 = 1500;

	/*------------------------------------------------------
	直線模走 順次実行
	------------------------------------------------------*/

	public void traveling()
	{
		// ---- L1での前方直進
		actuator.runStraightByTime(powerL1, timeL1);

		// ---- V1での左側転向
		actuator.runSpinByTime(powerV1, timeV1);

		// ---- L2での前方直進
		actuator.runStraightByTime(powerL2, timeL2);

		// ---- V2での左側転向
		actuator.runSpinByTime(powerV2, timeV2);
	}

	/*------------------------------------------------------
	直線模走 時間指定での順次実行
	------------------------------------------------------*/

	public void traveling(int timeStraight)
	{
		// ---- V2での左側転向のパラメタの調整
		int newTimeV2 = timeStraight - timeL1 - timeL2 - timeV1;
		powerV2 = powerV2 * timeV2 / newTimeV2;
		timeV2 = newTimeV2;

		// ---- 直線模走
		this.traveling();
	}

}
