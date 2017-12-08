package infoexpr.project.Project32;

import infoexpr.Library.Enums.MonoPattern;
import infoexpr.Library.Mechanism.Actuator.WheelActuator;
import infoexpr.Library.Mechanism.Detection.LineDetection;

/*=============================================================
領域掃出 乱数走行
=============================================================*/

public class RandomTraveling
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 機構
	public WheelActuator wheelActuator;
	public LineDetection lineDetector;

	// ---- 乱数走行パラメタ

	private int power = 400;
	private int untilTime = 500;
	private int spinTime = 300;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public RandomTraveling()
	{
		this.wheelActuator = new WheelActuator();
		this.lineDetector = new LineDetection();
	}

	/*------------------------------------------------------
	ラン
	------------------------------------------------------*/

	public void traveling()
	{
		while (true)
		{
			this.driving();
		}
	}

	public void driving()
	{
		MonoPattern monoPattern = lineDetector.getMonoPattern();

		switch (monoPattern) {
		case PatternKK:
			this.sweepAction();
			this.movingForward();
			break;
		case PatternKW:
			this.sweepAction();
			this.movingForward();
			break;
		case PatternWK:
			this.sweepAction();
			this.movingForward();
			break;
		case PatternWW:
			this.movingForward();
			break;
		default:
			break;
		}

	}

	private void sweepAction()
	{
		wheelActuator.reversePedal();
		wheelActuator.untilTime(untilTime);
		wheelActuator.runSpinByTime(power, spinTime);
	}

	private void movingForward()
	{
		wheelActuator.runStraight(power);
	}
}
