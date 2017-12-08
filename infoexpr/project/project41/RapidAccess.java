package infoexpr.project.project41;

import infoexpr.Library.Device.Input.Button;
import infoexpr.Library.Device.Input.Timer;
import infoexpr.Library.Mechanism.Actuator.WheelActuator;

/*=============================================================
個別任務  目標接近  迅速接近
=============================================================*/

public class RapidAccess
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private TargetLock targetLock;

	private float targetDistance;
	private float bufferDistance;
	private int forwardPower;
	private int delayTime = 100;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public RapidAccess()
	{
		TargetAccess.wheelActuator = new WheelActuator();
		targetLock = new TargetLock();
		TargetAccess.button = new Button();
		TargetAccess.timer = new Timer();
	}

	/*------------------------------------------------------
	接近
	------------------------------------------------------*/
	public void closeStraight()
	{
		while (!TargetAccess.button.IsEscapeButtonDown())
		{
			if (targetLock.LockWithDistance(targetDistance, bufferDistance))
			{
				TargetAccess.wheelActuator.runStraight(forwardPower);
				TargetAccess.timer.delay(delayTime);
			}
		}
	}
}
