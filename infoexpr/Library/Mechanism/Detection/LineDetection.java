package infoexpr.Library.Mechanism.Detection;

import infoexpr.Library.Device.Input.Sensor.ColorSensor;
import infoexpr.Library.Enums.ColorName;
import infoexpr.Library.Enums.DirectionSide;
import infoexpr.Library.Enums.MonoPattern;
import infoexpr.Library.Factory.SensorFactory;

/*=============================================================
検知機構  床下検知  黒線検知
=============================================================*/

public class LineDetection
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

	public LineDetection()
	{
		this.left = SensorFactory.createColorSensor(DirectionSide.LEFT);
		this.right = SensorFactory.createColorSensor(DirectionSide.RIGHT);
	}

	/*------------------------------------------------------
	左右の明暗による黒線と位置関係の判定
	------------------------------------------------------*/

	public MonoPattern getMonoPattern()
	{
		ColorName leftColorName = left.getColorName();
		ColorName rightColorName = this.right.getColorName();

		// ---- 偽色の補正
		if (leftColorName == ColorName.GREEN)
		{
			leftColorName = ColorName.WHITE;
		}
		if (leftColorName == ColorName.YELLOW)
		{
			leftColorName = ColorName.WHITE;
		}
		if (rightColorName == ColorName.GREEN)
		{
			rightColorName = ColorName.WHITE;
		}
		if (rightColorName == ColorName.YELLOW)
		{
			rightColorName = ColorName.WHITE;
		}

		// ---- 左右の明暗の判定
		if (leftColorName == ColorName.WHITE && rightColorName == ColorName.WHITE)
			return MonoPattern.PatternWW;
		if (leftColorName == ColorName.WHITE && rightColorName == ColorName.BLACK)
			return MonoPattern.PatternWK;
		if (leftColorName == ColorName.BLACK && rightColorName == ColorName.WHITE)
			return MonoPattern.PatternKW;
		if (leftColorName == ColorName.BLACK && rightColorName == ColorName.BLACK)
			return MonoPattern.PatternKK;

		// ---- 明暗の不明
		return MonoPattern.PatternKW;
	}

}
