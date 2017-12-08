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

	public static WheelActuator wheelActuator;
	public static TargetDetection targetDetection;

	public static Button button;
	public static Timer timer;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public TargetAccess()
	{
		wheelActuator = new WheelActuator();
		targetDetection = new TargetDetection();
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

	}

}
