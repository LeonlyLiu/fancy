package lineTrace2;

import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;
import jp.ac.kagawa_u.infoexpr.Sensor.TouchSensor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;

public class LineTrace implements Runnable
{
	private TouchSensor touch = new TouchSensor(SensorPort.S1);
	private RegulatedMotor rightMotor = Motor.B;
	private RegulatedMotor leftMotor = Motor.C;
	private ColorSensor rightColor = new ColorSensor(SensorPort.S2);
	private ColorSensor leftColor = new ColorSensor(SensorPort.S3);

	// 遅い速度
	private final int lowSpeed = 200;
	// 速い速度
	private final int highSpeed = 600;

	GetColor gt = new GetColor();

	public void run()
	{
		while (!touch.isPressed())
		{

			// 黒＆黒
			if (gt.getColor(leftColor) == ColorTypes.BLACK
					&& gt.getColor(rightColor) == ColorTypes.BLACK)
			{
				rightMotor.stop();
				leftMotor.stop();
			}
			// 黒＆白
			else if (gt.getColor(leftColor) == ColorTypes.BLACK
					&& gt.getColor(rightColor) == ColorTypes.WHITE)
			{
				motorSetSpeed(lowSpeed, highSpeed);
				motorForward();
				// detectColor(gt.getColor(leftColor));
			}
			// 白＆黒
			else if (gt.getColor(leftColor) == ColorTypes.WHITE
					&& gt.getColor(rightColor) == ColorTypes.BLACK)
			{
				motorSetSpeed(highSpeed, lowSpeed);
				motorForward();
				// detectColor(gt.getColor(leftColor));
			}
			// 白＆白
			else if (gt.getColor(leftColor) == ColorTypes.WHITE
					&& gt.getColor(rightColor) == ColorTypes.WHITE)
			{
				motorSetSpeed(highSpeed, highSpeed);
				motorForward();
				// detectColor(gt.getColor(leftColor));
			}
		}
	}

	private void motorSetSpeed(int leftMotorSpeed, int rightMotorSpeed)
	{
		leftMotor.setSpeed(leftMotorSpeed);
		rightMotor.setSpeed(rightMotorSpeed);
	}

	private void motorForward()
	{
		leftMotor.forward();
		rightMotor.forward();
	}

}
