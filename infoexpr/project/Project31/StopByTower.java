package infoexpr.project.Project31;

import infoexpr.Library.Mechanism.Actuator.WheelActuator;
import infoexpr.Library.Mechanism.Detection.CollisionDetection;

/*=============================================================
黒線追跡  停止任務
=============================================================*/

public class StopByTower implements Runnable
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private final CollisionDetection collisionDetection;
	private final WheelActuator wheelActuator;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public StopByTower()
	{
		this.collisionDetection = new CollisionDetection();
		this.wheelActuator = new WheelActuator();
	}

	/*------------------------------------------------------
	ラン
	------------------------------------------------------*/

	public void run()
	{
		if (collisionDetection.isCollided())
		{
			wheelActuator.stopForce();
		}

	}

}
