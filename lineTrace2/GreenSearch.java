package lineTrace2;

import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;
import lejos.hardware.Sound;

public class GreenSearch implements Runnable
{
	ColorSensor leftColor;
	GetColor gt = new GetColor();

	public void run()
	{
		if (gt.getColor(leftColor) == ColorTypes.GREEN)
			Sound.playTone(200, 100);// 音を鳴らす
	}

}
