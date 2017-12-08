package infoexpr.project.Project31;

import infoexpr.Library.Device.Input.Sensor.TouchSensor;
import infoexpr.Library.Mechanism.Actuator.WheelActuator;
import infoexpr.Library.Mechanism.Detection.LineDetection;
import infoexpr.Library.Mechanism.Detection.TileDetection;
import lejos.hardware.port.SensorPort;

/*=============================================================
応用課題  黒線追跡  演舞追跡
=============================================================*/

public class LineTracing extends SimpleTracing
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 機素
	static TouchSensor touchSensor;

	// ---- 機構
	static WheelActuator wheelActuator;
	static LineDetection lineDetection;
	static TileDetection tileDetection;

	// ---- 任務
	private SequentialTraveling sequentialTraveling;
	private FeedbackTraveling feedbackTraveling;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public LineTracing()
	{
		touchSensor = new TouchSensor(SensorPort.S1);
		wheelActuator = new WheelActuator();
		lineDetection = new LineDetection();
		tileDetection = new TileDetection();

		sequentialTraveling = new SequentialTraveling(wheelActuator, tileDetection);
		feedbackTraveling = new FeedbackTraveling(wheelActuator, lineDetection);

	}

	/*------------------------------------------------------
	メイン
	------------------------------------------------------*/

	public static void main(String[] args)
	{
		LineTracing lineTracing = new LineTracing();
		lineTracing.doGame();
	}

	/*------------------------------------------------------
	課題の実行
	------------------------------------------------------*/

	public void doGame()
	{

		// ---- 確定走行
		sequentialTraveling.travelingUntilRed();

		// ---- 検知走行(3回目の発音まで)
		feedbackTraveling.travelingUntilGreen();

		// ---- 検知走行(障害物まで)
		feedbackTraveling.travelingUntilTouch();
	}

}
