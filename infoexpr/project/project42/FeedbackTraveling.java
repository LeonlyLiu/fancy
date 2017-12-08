package infoexpr.project.project42;

import infoexpr.Library.Enums.MonoPattern;
import infoexpr.Library.Mechanism.Actuator.WheelActuator;
import infoexpr.Library.Mechanism.Detection.LineDetection;
import infoexpr.project.Project31.BeepWithGreen;

/*=============================================================
個別任務  目標周回  検知走行
=============================================================*/

public class FeedbackTraveling
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 機構
	private WheelActuator wheelActuator;
	private LineDetection lineDetection;

	// ---- 検知走行のパラメタ
	// -- 前方直進
	private int powerStraight = 720;

	// -- 右方曲進
	private int powerLeftInKW = 240;
	private int powerRightInKW = 480;

	// -- 左方曲進
	private int powerLeftInWK = 480;
	private int powerRightInWK = 240;

	// -- 制限時間
	private final int timeLimit = 2000;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public FeedbackTraveling()
	{
	}

	public FeedbackTraveling(final WheelActuator wheelActuator, final LineDetection lineDetection)
	{
		this.wheelActuator = wheelActuator;
		this.lineDetection = lineDetection;
	}

	/*------------------------------------------------------
	検知走行(無限)
	------------------------------------------------------*/

	public void traveling()
	{
		// ---- 検知走行
		while (true)
		{
			this.driving();
		}
	}

	/*------------------------------------------------------
	検知走行(時間まで)
	------------------------------------------------------*/

	public void travelingUntilTime()
	{
		// ---- 検知走行
		Thread beepWithGreen = new Thread(new BeepWithGreen());

		beepWithGreen.start();
		this.traveling();

		wheelActuator.untilTime(timeLimit);
		wheelActuator.stopForce();

	}

	/*------------------------------------------------------
	明暗パターンによる運転
	------------------------------------------------------*/

	private void driving()
	{
		// ---- 左右の明暗パターンを取得
		MonoPattern monoPattern = lineDetection.getMonoPattern();

		// ---- 左右の明暗パターンで操舵
		switch (monoPattern) {
		// -- 白白で前方直進
		case PatternWW:
			runForward();
			break;
		// -- 黒白で左方曲進
		case PatternKW:
			turnLeft();
			break;
		// -- 白黒で右方曲進
		case PatternWK:
			turnRight();
			break;
		// -- 黒黒で自然停止
		case PatternKK:
			stop();
			break;
		default:
			break;
		}
	}

	// -- 停止
	private void stop()
	{
		wheelActuator.stopNatural();
	}

	// -- 左方曲進
	private void turnLeft()
	{
		wheelActuator.runCurve(powerLeftInKW, powerRightInKW);
	}

	// -- 右方曲進
	private void turnRight()
	{
		wheelActuator.runCurve(powerLeftInWK, powerRightInWK);
	}

	// -- 前方直進
	private void runForward()
	{
		wheelActuator.runStraight(powerStraight);
	}

}
