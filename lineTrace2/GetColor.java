package lineTrace2;

import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;

public class GetColor
{
	private final float lowValue = 0.05F;

	private final float middleValue = 0.10F;

	private final float highValue = 0.15F;

	public ColorTypes getColor(ColorSensor color)
	{
		// 赤を認識
		if (color.getBlue() < lowValue && color.getRed() > highValue
				&& color.getGreen() < lowValue)
			return ColorTypes.RED;
		// 緑を認識
		else if (color.getBlue() < lowValue && color.getRed() < lowValue
				&& color.getGreen() > highValue)
			return ColorTypes.GREEN;
		// 黒を認識
		else if (color.getBlue() < lowValue && color.getRed() < lowValue
				&& color.getGreen() < lowValue)
			return ColorTypes.BLACK;
		// 白を認識
		else if (color.getBlue() > middleValue && color.getRed() > middleValue
				&& color.getGreen() > middleValue)
			return ColorTypes.WHITE;

		return null;

	}
}
