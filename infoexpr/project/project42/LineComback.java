package infoexpr.project.project42;

/*=============================================================
個別任務  目標周回  黒線復帰
=============================================================*/

public class LineComback
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private TargetSeek targetSeek;
	private FeedbackTraveling feedbackTraveling;
	private int straightPower;
	private int straightTime;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public LineComback()
	{
		targetSeek = new TargetSeek();
		feedbackTraveling = new FeedbackTraveling(TargetRound.wheelActuator, TargetRound.lineDetection);
	}

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public void backToLine()
	{
		if (targetSeek.seekByColor())
		{
			// --- 短距離前進
			TargetRound.wheelActuator.runStraightByTime(straightPower, straightTime);
			// ---
			feedbackTraveling.travelingUntilTime();
		}
	}
}
