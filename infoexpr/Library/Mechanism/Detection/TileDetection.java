package infoexpr.Library.Mechanism.Detection;

import infoexpr.Library.Device.Input.Sensor.ColorSensor;
import infoexpr.Library.Enums.ColorName;
import infoexpr.Library.Enums.DirectionSide;
import infoexpr.Library.Factory.SensorFactory;

/*=============================================================
検知機構  床下検知  標識検知
=============================================================*/

public class TileDetection
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 機素
	private final ColorSensor right;
	private final ColorSensor left;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public TileDetection()
	{
		this.left = SensorFactory.createColorSensor(DirectionSide.LEFT);
		this.right = SensorFactory.createColorSensor(DirectionSide.RIGHT);
	}

	/*------------------------------------------------------
	色彩標識の検知
	------------------------------------------------------*/

	public boolean getColorTile(ColorName colorName, DirectionSide dirSide)
	{
		switch (dirSide) {
		case LEFT:
			if (this.left.getColorName() == colorName)
				return true;
			break;
		case RIGHT:
			if (this.right.getColorName() == colorName)
				return true;
			break;
		default:
			break;
		}
		return false;
	}

}
