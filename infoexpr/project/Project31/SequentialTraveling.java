package infoexpr.project.Project31;

import infoexpr.Library.Enums.DirectionSide;
import infoexpr.Library.Mechanism.Actuator.WheelActuator;
import infoexpr.Library.Mechanism.Detection.TileDetection;

/*=============================================================
個別任務  黒線追跡  確定走行
=============================================================*/

public class SequentialTraveling
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 機構
	private WheelActuator wheelActuator;
	private TileDetection tileDetection;

	// ---- 走行パラメタ
	// 暫定値なので、各自で適切に設定
	private int powerL = 500;
	private int powerR = 500;
	private int time = 3000;
	private int tacho = 10000;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public SequentialTraveling(final WheelActuator wheelActuator, final TileDetection tileDetection)
	{
		this.wheelActuator = wheelActuator;
		this.tileDetection = tileDetection;
	}

	public SequentialTraveling(final WheelActuator wheelActuator)
	{
		this.wheelActuator = wheelActuator;
	}

	/*------------------------------------------------------
	指定時間までの確定走行
	------------------------------------------------------*/

	public void setParamPower(int powerL, int powerR)
	{
		this.powerL = powerL;
		this.powerR = powerR;
	}

	/*------------------------------------------------------
	指定時間までの確定走行
	------------------------------------------------------*/

	// ---- 実時間による指定
	public void travelingUntilTime()
	{
		wheelActuator.runCurveByTime(powerL, powerR, time);
	}

	/*------------------------------------------------------
	指定回転量までの確定走行
	------------------------------------------------------*/

	// ---- 回転量による指定
	public void travelingUntilTacho(DirectionSide side)
	{
		wheelActuator.runCurveByTacho(powerL, powerR, tacho, side);
	}

	/*------------------------------------------------------
	色彩標識を検知までの確定走行
	------------------------------------------------------*/

	// ---- 赤タイルまで走行
	public void travelingUntilRed()
	{

		Thread spinWithRed = new Thread(new SpinWithRed(wheelActuator, tileDetection));

		spinWithRed.start();
		wheelActuator.runCurve(powerL, powerR);

		try
		{
			spinWithRed.join();

		} catch (InterruptedException e)
		{
			e.printStackTrace(System.out);
		}
	}

}
