package infoexpr.project.Project31;

import infoexpr.Library.Enums.ColorName;
import infoexpr.Library.Enums.DirectionSide;
import infoexpr.Library.Mechanism.Actuator.WheelActuator;
import infoexpr.Library.Mechanism.Detection.TileDetection;

/*=============================================================
黒線追跡  自転任務
=============================================================*/

public class SpinWithRed implements Runnable
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private WheelActuator wheelActuator;
	private TileDetection tileDetection;

	private int powerSpinInV1 = 100;
	private int timeSpinInV1 = 100;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public SpinWithRed(final WheelActuator wheelActuator, final TileDetection tileDetection)
	{
		this.wheelActuator = wheelActuator;
		this.tileDetection = tileDetection;

	}

	public SpinWithRed(final WheelActuator wheelActuator)
	{
		this.wheelActuator = wheelActuator;

	}

	/*------------------------------------------------------
	ラン
	------------------------------------------------------*/

	public void run()
	{
		while (!isRed())
			;
		wheelActuator.stopForce();
		rotation();

	}

	/*------------------------------------------------------
	自転
	------------------------------------------------------*/

	public void rotation()
	{
		wheelActuator.runSpinByTime(powerSpinInV1, timeSpinInV1);
	}

	/*------------------------------------------------------
	赤認識
	------------------------------------------------------*/

	private boolean isRed()
	{
		return tileDetection.getColorTile(ColorName.RED, DirectionSide.LEFT);
	}

}
