package infoexpr.project.project41;

import infoexpr.Library.Mechanism.Actuator.WheelActuator;

/*=============================================================
個別任務  目標接近  螺旋巡回
=============================================================*/

public class SpiralPatrol
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private int powerLeftInCurve;
	private int powerRightInCurve;
	private int diffPowerLeft;
	private int diffPowerRight;
	private int delayTime;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public SpiralPatrol()
	{
		TargetAccess.wheelActuator = new WheelActuator();
	}

	/*------------------------------------------------------
	螺旋走行
	------------------------------------------------------*/

	public void traveling()
	{
		while (true)
		{
			drive();
		}
	}

	private void drive()
	{
		TargetAccess.wheelActuator.runCurve(powerLeftInCurve, powerRightInCurve);
		TargetAccess.timer.delay(delayTime);
		TargetAccess.wheelActuator.changePowerBoth(diffPowerLeft, diffPowerRight);
	}

}
