package jp.ac.kagawa_u.infoexpr.Mechanism.ActuatorMechanism;

import jp.ac.kagawa_u.infoexpr.Device.InputDevice.Timer;
import jp.ac.kagawa_u.infoexpr.Enums;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class RunningActuator
{
    public enum MotorSide {
        LEFT, 
        RIGHT,
    }

    public enum DuringUnit {
        INFINITY,
        TIME,
        TACHO,
    }

    private static RegulatedMotor rightMotor = Motor.B;
    private static RegulatedMotor leftMotor = Motor.C;
    private Timer timer = new Timer();

    private int leftPower = 0;
    private int rightPower = 0;

    private final float maxSpeed = 33.0F;
    private final float bodyWidth = 12.0F;
    private final float wheelSize = 2.5F;

    /*---------------------------------------------------------
    コンストラクタ
    ---------------------------------------------------------*/

    public RunningActuator() 
    {
    }

    public RunningActuator(float width, float radius)
    {
        bodyWidth = width;
        wheelRadius = radius;
    }

    /*---------------------------------------------------------
    片側出力指定
    ---------------------------------------------------------*/

    public void setPowerSide(int power, Motorside side) 
    {
        switch (side)
        {
        case LEFT:
            this.leftPower = power;
            leftMotor.setSpeed(leftPower);
        case RIGHT:
            this.rightPower = power;
            rightMotor.setSpeed(rightPower);
        }
    }

    /*---------------------------------------------------------
    両側出力指定
    ---------------------------------------------------------*/

    public void setPowerBoth(int leftpower, int rightpower) 
    {
        this.leftPower = leftpower;
        this.rightPower = rightpower;
        leftMotor.setSpeed(leftPower);
        rightMotor.setSpeed(rightPower);
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
        return untilTacho(tacho, LEFT);
    }

    public void untilTacho(int tacho, MotorSide side) 
    {
        private RegulatedMotor theMotor;
        switch (side) {
        case LEFT: 
            theMotor= leftMotor;
            break;
        case RIGHT: 
            theMotor= rightMotor;
            break;
        }

        int current = theMotor.getTachoCount();
        while ( Math.abs(theMotor.getTachoCount() - current) <= tacho ) { }
        stopForce();
    }

    /*---------------------------------------------------------
    直進(左/右)  runStraight(X)
    ---------------------------------------------------------*/

    public void runStraight(int power, int t, Unit unit)
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
        leftMotor.forward();
        rightMotor.forward();
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

    public void runSpin(int power, int t, Unit unit) 
    {
        switch (unit){
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
        leftMotor.forward();
        rightMotor.forward();
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

    public void runCurve(int leftpower, int rightpower) 
    {
        setPowerBoth(leftPower, rightPower);
        leftMotor.forward();
        rightMotor.forward();
    }

    /*---------------------------------------------------------
    曲進(実時間)  runCurveByTime(X_L, K_R, T)
    ---------------------------------------------------------*/

    public void runCurveByTime(int leftpower, int rightpower, int time) 
    {
        runCurve(leftpower, rightpower);
        untilTime(time);
    }

    /*---------------------------------------------------------
    曲進(回転量)  runCurveByTacho(X_L. X_R, T, W)
    ---------------------------------------------------------*/

    public void runCurveByTacho(int leftpower, int rightpower, 
        int tacho, int side)
    {
        runCurve(leftpower, rightpower);
        untilTacho(tacho, side);
    }

    /*---------------------------------------------------------
    緊急停止  stopForce()    ブレーキで緊急に停止
    ---------------------------------------------------------*/

    public void stopForce() 
    {
        leftMotor.stop();
        rightMotor.stop();
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

    public void changePowerSide(int diff, MotorSide side) 
    {
        switch (side) {
        case LEFT:
            this.leftPower += diff;
            break;
        case RIGHT:
            this.rightPower += diff;
            break;
        }

        setPowerBoth(leftPower, rightPower);
    }

    /*---------------------------------------------------------
    出力量(両側)の増減 changePowerBoth(X_L, X_R)
    ---------------------------------------------------------*/

    public void changePowerBoth(int diffleft, int diffright) 
    {
        this.leftPower += diffleft;
        this.rightPower += diffright;

        setPowerBoth(leftPower, rightPower);
    }

    /*---------------------------------------------------------
    逆駆動  reversePedal()
    ---------------------------------------------------------*/

    public void reversePedal() 
    {
        this.leftPower *= -1;
        this.rightPower *= -1;
        runCurve(leftPower, rightPower);
    }

    /*---------------------------------------------------------
    逆操舵  reverseSteering()
    ---------------------------------------------------------*/

    public void reverseSteering() 
    {
        int tmp = this.leftPower;
        this.leftPower = this.rightPower;
        this.rightPower = tmp;

        runCurve(leftPower, rightPower);
    }

    /*---------------------------------------------------------
    駆動量  getPedal()
    ---------------------------------------------------------*/

    public float getPedal() 
    {
        return (leftPower + rightPower) / 2.0F;
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
        float powerSum = rightPower + leftPower;
        float powerDiff = rightPower - leftPower;

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
