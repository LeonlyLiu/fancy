package followLine;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Main {

	private static int timer = 0;
	private static RegulatedMotor leftMotor =  Motor.C;
	private static RegulatedMotor rightMotor = Motor.B;

	public static void main(String[] args) {

		// 前進
		while ( timer <= 1500){
			forwardRobot( 700, 0);
			Delay.msDelay(100);
			timer += 100;
		}

		//90°左に回転
		timer = 0;
		while ( timer <= 200){
			turnLeft(380, 0);
			Delay.msDelay(100);
			timer += 100;
		}

		//前進
		timer = 0;
		while ( timer <= 2500){
			forwardRobot(700, 0);
			Delay.msDelay(100);
			timer += 100;
		}

		//45°左に回転
		timer = 0;
		while ( timer <= 100){
			turnLeft(200, 0);
			Delay.msDelay(100);
			timer += 100;
		}

		//大きい曲進
		timer = 0;
		while ( timer <= 3000){
			forwardRobot(300, 100);
			Delay.msDelay(100);
			timer += 100;
		}

		//160°右に回転
		timer = 0;
		while ( timer <= 500){
			turnRight(360, 0);
			Delay.msDelay(100);
			timer += 100;
		}

		//小さい曲進
		timer = 0;
		while ( timer <= 2000){
			forwardRobot(300, 130);
			Delay.msDelay(100);
			timer += 100;
		}

	}

	//速度設定
	private static void setSpeed(int speed, int turn) {
		leftMotor.setSpeed(speed - turn);
		rightMotor.setSpeed(speed + turn);
	}

	//ロボット前進
	private static void forwardRobot(int speed, int turn) {
		setSpeed(speed, turn);
		leftMotor.forward();
		rightMotor.forward();
	}

	//左に回転
	private static void turnLeft(int speed, int turn) {
		setSpeed(speed, turn);
		leftMotor.backward();
		rightMotor.forward();
	}

	//右に回転
	private static void turnRight(int speed, int turn) {
		setSpeed(speed, turn);
		leftMotor.forward();
		rightMotor.backward();
	}
}
