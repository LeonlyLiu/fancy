package lineTrace3;

import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;
import jp.ac.kagawa_u.infoexpr.Sensor.TouchSensor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;

public class LineTrace
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
	Voice vc = new Voice();

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
			else if ((gt.getColor(leftColor) == ColorTypes.WHITE
					|| gt.getColor(leftColor) == ColorTypes.GREEN)
					&& gt.getColor(rightColor) == ColorTypes.BLACK)
			{
				if(gt.getColor(leftColor) == ColorTypes.GREEN)
				{
					vc.makeVoice();
				}
				motorSetSpeed(highSpeed, lowSpeed);
				motorForward();
			}
			// 白＆白
			else if ((gt.getColor(leftColor) == ColorTypes.WHITE
					|| gt.getColor(leftColor) == ColorTypes.GREEN)
					&& gt.getColor(rightColor) == ColorTypes.WHITE)
			{
				if(gt.getColor(leftColor) == ColorTypes.GREEN)
				{
					vc.makeVoice();
				}
				motorSetSpeed(highSpeed, highSpeed);
				motorForward();
			}
			if(touch.isPressed()){
				leftMotor.stop();
				rightMotor.stop();
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
