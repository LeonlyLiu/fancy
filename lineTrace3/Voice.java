package lineTrace3;

import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;
import lejos.hardware.Sound;

public class Voice
{
	ColorSensor leftColor;
	GetColor gt = new GetColor();

	public void makeVoice()
	{
		if (gt.getColor(leftColor) == ColorTypes.GREEN)
			Sound.playTone(200, 100);// 音を鳴らす
	}

}
