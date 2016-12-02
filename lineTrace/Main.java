package lineTrace;

import jp.ac.kagawa_u.infoexpr.Sensor.ColorSensor;
import jp.ac.kagawa_u.infoexpr.Sensor.TouchSensor;
import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;

public class Main {

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

	public void main(String[] args) {

		while (! touch.isPressed ()) {

//			ColorTypes LeftColorType = ColorTypes.;
//			ColorTypes RightColorType = getColor(rightColor);

			// 黒＆黒
			if (getColor(leftColor ) == 3 && getColor(rightColor) == 3) {}
			// 黒＆白
			else if (getColor(leftColor) == 3 && getColor(rightColor) == 4) {
				motorSetSpeed(lowSpeed, highSpeed);
				motorForward();
				detectColor(getColor(leftColor));
			}
			// 白＆黒
			else if (getColor(leftColor) == 4 && getColor(rightColor) == 3) {
				motorSetSpeed(highSpeed, lowSpeed);
				motorForward();
				detectColor(getColor(leftColor));
			}
			// 白＆白
			else if (getColor(leftColor) == 4 && getColor(rightColor) == 4) {
				motorSetSpeed(highSpeed, highSpeed);
				motorForward();
				detectColor(getColor(leftColor));
			}
		}
	}

	private void motorSetSpeed(int leftMotorSpeed, int rightMotorSpeed) {
		leftMotor.setSpeed(leftMotorSpeed);
		rightMotor.setSpeed(rightMotorSpeed);
	}

	private void motorForward() {
		leftMotor.forward();
		rightMotor.forward();
	}

	private int getColor(ColorSensor color) {
		//赤を認識
		if (color.getBlue() < 0.05F && color.getRed() > 0.15F && color.getGreen() < 0.05F) return 1;
		//緑を認識
		else if (color.getBlue() < 0.05F && color.getRed() < 0.06F && color.getGreen() > 0.11F) return 2;
		//黒を認識
		else if (color.getBlue() < 0.04F && color.getRed() < 0.04F && color.getGreen() < 0.04F) return 3;
		//白を認識
		else if (color.getBlue() > 0.10F && color.getRed() > 0.10F && color.getGreen() > 0.10F) return 4;

		return 0;
	}

	private void detectColor(int color) {
		//赤の場合
		if (color == 1) fullTurn();//一回転
		//緑の場合
		if (color == 2) Sound.playTone(200, 100);//音を鳴らす
	}

//	private void foundLine() {
//
//	}

	//一回転をする
	private void fullTurn() {
		leftMotor.resetTachoCount();
		leftMotor.setSpeed(turnSpeed);
		rightMotor.setSpeed(turnSpeed);

		while (leftMotor.getTachoCount() < turnAngle) {
			leftMotor.forward();
			rightMotor.backward();
		}
	}
}
