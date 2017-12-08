package infoexpr.project.Project31;

import infoexpr.Library.Device.Output.Speaker;
import infoexpr.Library.Enums.ColorName;
import infoexpr.Library.Enums.DirectionSide;
import infoexpr.Library.Mechanism.Detection.TileDetection;

/*=============================================================
緑標識検知と発音(3回)
=============================================================*/

public class BeepWithGreen implements Runnable
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 機構
	private Speaker speaker;
	private TileDetection tileDetection;

	// ---- 発音回数
	private final int numberOfGreenTile = 3;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public BeepWithGreen()
	{
		this.speaker = new Speaker();
		this.tileDetection = new TileDetection();
	}

	/*------------------------------------------------------
	ラン
	------------------------------------------------------*/

	public void run()
	{
		for (int i = 0; i < numberOfGreenTile; i++)
		{
			while (isNotGreen())
				; // wait for green tile
			speaker.beep();
			while (isGreen())
				; // wait for escaping green tile
		}

	}

	/*------------------------------------------------------
	緑認識
	------------------------------------------------------*/

	private boolean isGreen()
	{
		if (tileDetection.getColorTile(ColorName.GREEN, DirectionSide.LEFT))
		{
			return true;
		}
		return false;
	}

	private boolean isNotGreen()
	{
		return !isGreen();
	}

}
