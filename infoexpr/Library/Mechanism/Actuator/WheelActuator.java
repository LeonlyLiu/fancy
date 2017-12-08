package infoexpr.Library.Mechanism.Actuator;

import infoexpr.Library.Device.Input.Timer;
import infoexpr.Library.Enums.DirectionSide;
import infoexpr.Library.Enums.DutationType;
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

/*=============================================================
機構  動作機構  車輪機構
=============================================================*/

public class WheelActuator
{
	private RegulatedMotor right;
	private RegulatedMotor left;
	private Timer timer = new Timer();

	private int powerL = 0;
	private int powerR = 0;

	private float maxSpeed = 33.0F;
	// private float bodyWidth = 12.0F;
	private float wheelSize = 2.5F;

	/*---------------------------------------------------------
	コンストラクタ
	---------------------------------------------------------*/

	public WheelActuator()
	{
		this.right = Motor.B;
		this.left = Motor.C;
	}

	/*
	 * public WheelActuator(float width, float radius) { bodyWidth = width;
	 * wheelSize = radius; }
	 */

	/*---------------------------------------------------------
	片側出力指定
	---------------------------------------------------------*/

	public void setPowerSide(int power, DirectionSide side)
	{
		switch (side) {
		case LEFT:
			this.powerL = power;
			left.setSpeed(powerL);
		case RIGHT:
			this.powerR = power;
			right.setSpeed(powerR);
		default:
			break;
		}
	}

	/*---------------------------------------------------------
	両側出力指定
	---------------------------------------------------------*/

	public void setPowerBoth(int powerL, int powerR)
	{
		this.powerL = powerL;
		this.powerR = powerR;
		left.setSpeed(powerL);
		right.setSpeed(powerR);
	}

	/*---------------------------------------------------------
	持続量(実時間)を指定した走行
	---------------------------------------------------------*/

	public void untilTime(int limit)
	{
		timer.delay(limit);
	}

	/*---------------------------------------------------------
	持続量(回転量)を指定した走行
	フィードバック制御で逆進があると意味が異なる
	---------------------------------------------------------*/

	public void untilTacho(int tacho)
	{
		untilTacho(tacho, DirectionSide.LEFT);
	}

	public void untilTacho(int tacho, DirectionSide side)
	{
		int currentTachoCount = this.getTachoCountBySide(side);
		while (Math.abs(this.getTachoCountBySide(side) - currentTachoCount) <= tacho)
		{
		}
		stopForce();
	}

	private int getTachoCountBySide(DirectionSide direct)
	{
		return direct == DirectionSide.LEFT ? left.getTachoCount() : right.getTachoCount();
	}

	/*---------------------------------------------------------
	直進(左/右)  runStraight(X)
	---------------------------------------------------------*/

	public void runStraight(int power, int t, DutationType unit)
	{
		switch (unit) {
		case TIME:
			runStraightByTime(power, t);
			return;
		case TACHO:
			runStraightByTacho(power, t);
			return;
		case INFINITY:
			runStraight(power);
			return;
		}
	}

	/*---------------------------------------------------------
	直進(無限)  runStraight(X)
	---------------------------------------------------------*/

	public void runStraight(int power)
	{
		setPowerBoth(power, power);
		left.forward();
		right.forward();
	}

	/*---------------------------------------------------------
	直進(実時間) runStraightByTime(X, T)
	---------------------------------------------------------*/

	public void runStraightByTime(int power, int time)
	{
		runStraight(power);
		untilTime(time);
	}

	/*---------------------------------------------------------
	直進(回転量)  runStraightByTacho(X, T)
	---------------------------------------------------------*/

	public void runStraightByTacho(int power, int tacho)
	{
		runStraight(power);
		untilTacho(tacho);
	}

	/*---------------------------------------------------------
	転向(左/右)  runSpin(X)
	---------------------------------------------------------*/

	public void runSpin(int power, int t, DutationType unit)
	{
		switch (unit) {
		case TIME:
			runSpinByTime(power, t);
		case TACHO:
			runSpinByTacho(power, t);
		case INFINITY:
			runSpin(power);
		}
	}

	/*---------------------------------------------------------
	転向(無限)  runSpin(X)
	---------------------------------------------------------*/

	public void runSpin(int power)
	{
		setPowerBoth(-power, +power);
		left.forward();
		right.forward();
	}

	/*---------------------------------------------------------
	転向(実時間)  runSpinByTime(X, T)
	---------------------------------------------------------*/

	public void runSpinByTime(int power, int time)
	{
		runSpin(power);
		untilTime(time);
	}

	/*---------------------------------------------------------
	転向(回転量)  runSpinByTacho(X, T)
	---------------------------------------------------------*/

	public void runSpinByTacho(int power, int tacho)
	{
		runSpin(power);
		untilTacho(tacho);
	}

	/*---------------------------------------------------------
	曲進(無限)  runCurve(X_L, X_R)
	---------------------------------------------------------*/

	public void runCurve(int powerL, int powerR)
	{
		setPowerBoth(powerL, powerR);
		left.forward();
		right.forward();
	}

	/*---------------------------------------------------------
	曲進(実時間)  runCurveByTime(X_L, K_R, T)
	---------------------------------------------------------*/

	public void runCurveByTime(int powerL, int powerR, int time)
	{
		runCurve(powerL, powerR);
		untilTime(time);
	}

	/*---------------------------------------------------------
	曲進(回転量)  runCurveByTacho(X_L. X_R, T, W)
	---------------------------------------------------------*/

	public void runCurveByTacho(int powerL, int powerR, int tacho, DirectionSide side)
	{
		runCurve(powerL, powerR);
		untilTacho(tacho, side);
	}

	/*---------------------------------------------------------
	緊急停止  stopForce()    ブレーキで緊急に停止
	---------------------------------------------------------*/

	public void stopForce()
	{
		left.stop();
		right.stop();
	}

	/*---------------------------------------------------------
	自然停止 stopNatural()  出力0で自然に停止
	---------------------------------------------------------*/

	public void stopNatural()
	{
		setPowerBoth(0, 0);
	}

	/*---------------------------------------------------------
	出力量(片側)の増減 changePowerSide(L/R, X)
	---------------------------------------------------------*/

	public void changePowerSide(int diff, DirectionSide side)
	{
		switch (side) {
		case LEFT:
			this.powerL += diff;
			break;
		case RIGHT:
			this.powerR += diff;
			break;
		default:
			break;
		}

		setPowerBoth(powerL, powerR);
	}

	/*---------------------------------------------------------
	出力量(両側)の増減 changePowerBoth(X_L, X_R)
	---------------------------------------------------------*/

	public void changePowerBoth(int diffleft, int diffright)
	{
		this.powerL += diffleft;
		this.powerR += diffright;

		setPowerBoth(powerL, powerR);
	}

	/*---------------------------------------------------------
	逆駆動  reversePedal()
	---------------------------------------------------------*/

	public void reversePedal()
	{
		this.powerL *= -1;
		this.powerR *= -1;
		runCurve(powerL, powerR);
	}

	/*---------------------------------------------------------
	逆操舵  reverseSteering()
	---------------------------------------------------------*/

	public void reverseSteering()
	{
		int tmp = this.powerL;
		this.powerL = this.powerR;
		this.powerR = tmp;

		runCurve(powerL, powerR);
	}

	/*---------------------------------------------------------
	駆動量  getPedal()
	---------------------------------------------------------*/

	public float getPedal()
	{
		return (powerL + powerR) / 2.0F;
	}

	/*---------------------------------------------------------
	速度計算  getVelocity()
	---------------------------------------------------------*/

	public float getVelocity()
	{
		return getPedal() / 100 * maxSpeed;
	}

	/*---------------------------------------------------------
	曲率計算  getCurve()
	---------------------------------------------------------*/

	public float getCurve()
	{
		float powerSum = powerR + powerL;
		float powerDiff = powerR - powerL;

		return powerDiff / powerSum * (wheelSize / 2);
	}

	/*---------------------------------------------------------
	半径計算  getRadius()
	---------------------------------------------------------*/

	public float getRadius()
	{
		return 1 / getCurve();
	}

}
