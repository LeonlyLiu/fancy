package lineTrace;

import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;
import jp.ac.kagawa_u.infoexpr.Sensor.TouchSensor;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;

public class Main 
{

	private TouchSensor touch = new TouchSensor(SensorPort.S1);
	private ColorSensor rightColor = new ColorSensor(SensorPort.S2);
	private ColorSensor leftColor = new ColorSensor(SensorPort.S3);
	private RegulatedMotor rightMotor  = Motor.B;
	private RegulatedMotor leftMotor  = Motor.C;

	//遅い速度
	private final int lowSpeed = 200;
	//速い速度
	private final int highSpeed = 600;
	//回転速度
	private final int turnSpeed = 300;
	//回転角度
	private final int turnAngle = 720;

	private final float lowValue = 0.05F;

	private final float middleValue = 0.10F;

	private final float highValue = 0.15F;

	public void main(String[] args)
	{

		while (! touch.isPressed ())
		{

			// 黒＆黒
			if (getColor(leftColor ) == ColorTypes.BLACK && getColor(rightColor) == ColorTypes.BLACK)
			{
				rightMotor.stop();
				leftMotor.stop();
			}
			// 黒＆白
			else if (getColor(leftColor) == ColorTypes.BLACK && getColor(rightColor) == ColorTypes.WHITE)
			{
				motorSetSpeed(lowSpeed, highSpeed);
				motorForward();
				detectColor(getColor(leftColor));
			}
			// 白＆黒
			else if (getColor(leftColor) == ColorTypes.WHITE && getColor(rightColor) == ColorTypes.BLACK)
			{
				motorSetSpeed(highSpeed, lowSpeed);
				motorForward();
				detectColor(getColor(leftColor));
			}
			// 白＆白
			else if (getColor(leftColor) == ColorTypes.WHITE && getColor(rightColor) == ColorTypes.WHITE)
			{
				motorSetSpeed(highSpeed, highSpeed);
				motorForward();
				detectColor(getColor(leftColor));
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

	private ColorTypes getColor(ColorSensor color)
	{
		//赤を認識
		if (color.getBlue() < lowValue && color.getRed() > highValue && color.getGreen() < lowValue) return ColorTypes.RED;
		//緑を認識
		else if (color.getBlue() < lowValue && color.getRed() < lowValue && color.getGreen() > highValue) return ColorTypes.GREEN;
		//黒を認識
		else if (color.getBlue() < lowValue && color.getRed() < lowValue && color.getGreen() < lowValue) return ColorTypes.BLACK;
		//白を認識
		else if (color.getBlue() > middleValue && color.getRed() > middleValue && color.getGreen() > middleValue) return ColorTypes.WHITE;

		return null;

	}

	private void detectColor(ColorTypes color)
	{
		//赤の場合
		if (color == ColorTypes.RED) fullTurn();//一回転
		//緑の場合
		if (color == ColorTypes.GREEN) Sound.playTone(200, 100);//音を鳴らす
	}

	//一回転をする
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
