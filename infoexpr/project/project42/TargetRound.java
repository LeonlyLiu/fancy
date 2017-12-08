package infoexpr.project.project42;

import infoexpr.Library.Mechanism.Actuator.WheelActuator;
import infoexpr.Library.Mechanism.Detection.LineDetection;

/*=============================================================
個別任務  目標周回  目標周回
=============================================================*/

public class TargetRound
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	public static WheelActuator wheelActuator;
	public static LineDetection lineDetection;

	public static FeedbackTraveling feedbackTraveling;
	public static LineComback lineComback;
	public static RevolutionTraveling revolutionTraveling;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public TargetRound()
	{
		wheelActuator = new WheelActuator();
		lineDetection = new LineDetection();

		feedbackTraveling = new FeedbackTraveling(wheelActuator, lineDetection);
		lineComback = new LineComback();
		revolutionTraveling = new RevolutionTraveling();
	}

	/*------------------------------------------------------
	メイン
	------------------------------------------------------*/

	public static void main(String[] args)
	{
		new TargetRound().doGame();
	}

	/*------------------------------------------------------
	課題の実行
	------------------------------------------------------*/

	public void doGame()
	{
		feedbackTraveling.traveling();
		revolutionTraveling.revolution();
		lineComback.backToLine();
	}
}
