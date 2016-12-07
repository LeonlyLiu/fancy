package lineTrace3;

import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;
import lejos.robotics.RegulatedMotor;

public class RedSearch
{
	RegulatedMotor rightMotor;
	RegulatedMotor leftMotor;
	ColorSensor leftColor;

	GetColor gt = new GetColor();

	// 回転速度
	private final int turnSpeed = 300;
	// 回転角度
	private final int turnAngle = 720;

	public void detectRed()
	{
		// 赤の場合
		if (gt.getColor(leftColor) == ColorTypes.RED)
		{
			fullTurn();	// 一回転
		}
		return;
	}

	// 一回転をする
	private void fullTurn()
	{
		leftMotor.resetTachoCount();
		leftMotor.setSpeed(turnSpeed);
		rightMotor.setSpeed(turnSpeed);

		while (leftMotor.getTachoCount() < turnAngle)
		{
			leftMotor.forward();
			rightMotor.backward();
		}
	}

}
