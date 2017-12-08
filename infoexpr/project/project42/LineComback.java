package infoexpr.project.project42;

/*=============================================================
個別任務  目標周回  黒線復帰
=============================================================*/

public class LineComback
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private FeedbackTraveling feedbackTraveling;

	private int straightPower;
	private int straightTime;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public LineComback()
	{
		feedbackTraveling = new FeedbackTraveling(TargetRound.wheelActuator, TargetRound.lineDetection);
	}

	/*------------------------------------------------------
	復帰動作のあとに検知走行
	------------------------------------------------------*/

	public void backToLine()
	{

		// --- 短距離前進
		TargetRound.wheelActuator.runStraightByTime(straightPower, straightTime);
		// ---
		feedbackTraveling.travelingUntilTime();

	}
}
