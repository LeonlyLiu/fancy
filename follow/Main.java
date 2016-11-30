package follow;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Main {

	int timer = 0;
	RegulatedMotor leftMotor = Motor.C;
	RegulatedMotor rightMotor = Motor.B;

	public void main(String[] args) {

		//前進L1
		runStraight(1500, 700, 0);

		//90°左に回転
		runTurn(200, 380, 0);

		//前進L2
		runStraight(2500, 700, 0);

		//45°左に回転
		runTurn(100, 200, 0);

		//大きい曲進
		runStraight(3000, 300, 100);

		//180°左に回転
		runTurn(600, 360, 0);

		//小さい曲進
		runStraight(2000, 300, 130);

	}

	//前進
	public void runStraight(int time, int speed, int turn) {
		timer = 0;
		while(timer <= time) {
			forwardRobot(speed, turn);
			Delay.msDelay(100);
			timer += 100;
		}
	}

	//左に回転
	public void runTurn(int time, int speed, int turn) {
		timer = 0;
		while ( timer <= time) {
			turnLeft( speed, turn);
			Delay.msDelay(100);
			timer += 100;
		}
	}

	//速度設定
	private void setSpeed(int speed, int turn) {
		leftMotor.setSpeed(speed - turn);
		rightMotor.setSpeed(speed + turn);
	}

	//ロボット前進
	private void forwardRobot(int speed, int turn) {
		setSpeed(speed, turn);
		leftMotor.forward();
		rightMotor.forward();
	}

	//左に回転
	private void turnLeft(int speed, int turn) {
		setSpeed(speed, turn);
		leftMotor.backward();
		rightMotor.forward();
	}
}
