package infoexpr.project.project42;

import infoexpr.Library.Enums.ColorName;
import infoexpr.Library.Enums.DirectionSide;
import infoexpr.Library.Mechanism.Detection.TargetDetection;
import infoexpr.Library.Mechanism.Detection.TileDetection;

/*=============================================================
個別任務  目標周回  目標発見
=============================================================*/

public class TargetSeek
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private TargetDetection targetDetection;
	private TileDetection tileDetection;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public TargetSeek()
	{
		targetDetection = new TargetDetection();
		tileDetection = new TileDetection();
	}

	/*------------------------------------------------------
	反響センサで目標発見
	------------------------------------------------------*/

	public boolean seekBySonic(float limitDistance, float bufferDistance)
	{
		if (targetDetection.getDistance() < limitDistance + bufferDistance)
		{
			return true;
		}
		return false;
	}

	/*------------------------------------------------------
	色彩センサで目標発見
	------------------------------------------------------*/

	public boolean seekByColor()
	{

		if (tileDetection.getColorTile(ColorName.BLACK, DirectionSide.RIGHT))
		{
			return true;
		}
		return false;
	}
}
