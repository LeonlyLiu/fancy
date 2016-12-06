package lineTrace2;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class RunStraight
{

	RegulatedMotor rightMotor;
	RegulatedMotor leftMotor;

	public void runStraight(int time, int speed)
	{
		int timer = 0;
		while (timer <= time)
		{
			forwardRobot(speed);
			Delay.msDelay(100);
			timer += 100;
		}
	}

	private void setSpeed(int MotorSpeed)
	{
		leftMotor.setSpeed(MotorSpeed);
		rightMotor.setSpeed(MotorSpeed);
	}

	// ロボット前進
	private void forwardRobot(int speed)
	{
		setSpeed(speed);
		leftMotor.forward();
		rightMotor.forward();
	}
}
