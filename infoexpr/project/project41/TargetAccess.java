package infoexpr.project.project41;

import infoexpr.Library.Device.Input.Button;
import infoexpr.Library.Device.Input.Timer;
import infoexpr.Library.Mechanism.Actuator.WheelActuator;
import infoexpr.Library.Mechanism.Detection.TargetDetection;

/*=============================================================
個別任務  目標接近  目標接近
=============================================================*/

public class TargetAccess
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/
	// ---- 機構
	public static WheelActuator wheelActuator;
	public static TargetDetection targetDetection;

	// ---- 機素
	public static Button button;
	public static Timer timer;

	// ---- 任務
	public static SpiralPatrol spiralPatrol;
	public static NeighborStop neighborStop;
	public static RapidAccess rapidAccess;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public TargetAccess()
	{
		wheelActuator = new WheelActuator();
		targetDetection = new TargetDetection();

		spiralPatrol = new SpiralPatrol();
		neighborStop = new NeighborStop();
		rapidAccess = new RapidAccess();

		button = new Button();
		timer = new Timer();

	}

	/*------------------------------------------------------
	メイン
	------------------------------------------------------*/

	public static void main(String[] args)
	{
		new TargetAccess().doGame();
	}

	/*------------------------------------------------------
	課題の実行
	------------------------------------------------------*/

	public void doGame()
	{
		spiralPatrol.traveling();
		rapidAccess.closeStraight();
		neighborStop.closeAndStop();
	}

}
