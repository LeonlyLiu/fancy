package infoexpr.project.Project31;

import infoexpr.Library.Mechanism.Actuator.WheelActuator;
import infoexpr.Library.Mechanism.Detection.LineDetection;

/*=============================================================
基本課題  黒線追跡  単純追跡
=============================================================*/

public class SimpleTracing
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 機構
	private WheelActuator actuator;
	private LineDetection lineDetection;

	// ---- 任務
	private FeedbackTraveling feedbackTraveling;
	private SequentialTraveling sequentialTraveling;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public SimpleTracing()
	{
		// 機構の作成
		this.actuator = new WheelActuator();
		this.lineDetection = new LineDetection();

		// 任務の作成
		this.feedbackTraveling = new FeedbackTraveling(actuator, lineDetection);
		this.sequentialTraveling = new SequentialTraveling(actuator);
	}

	/*------------------------------------------------------
	メイン
	------------------------------------------------------*/

	public static void main(String[] args)
	{
		SimpleTracing simpleTraceing = new SimpleTracing();
		simpleTraceing.doGame();
	}

	/*------------------------------------------------------
	課題の実行
	------------------------------------------------------*/

	public void doGame()
	{
		// ---- 確定走行
		sequentialTraveling.travelingUntilTime();

		// ---- 検知走行
		feedbackTraveling.traveling();
	}

}
