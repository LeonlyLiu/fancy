package infoexpr.Library.Mechanism.Detection;

import infoexpr.Library.Device.Input.Sensor.TouchSensor;
import infoexpr.Library.Factory.SensorFactory;

/*=============================================================
検知機構  目標検知  接近検知
=============================================================*/

public class CollisionDetection
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 機素
	private final TouchSensor touch;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public CollisionDetection()
	{
		this.touch = SensorFactory.createTouchSensor();
	}

	/*------------------------------------------------------
	衝突の検知
	------------------------------------------------------*/

	public boolean isCollided()
	{
		if (touch.isPressed())
		{
			return true;
		}
		return false;
	}
}
