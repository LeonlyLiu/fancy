package jp.ac.kagawa_u.infoexpr.Mechanism.ActuatorMechanism;

import jp.ac.kagawa_u.infoexpr.Device.InputDevice.Timer;
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

public class RunningActuator
{
    final int LEFT = 1;
    final int RIGHT = 2;

    final int TIME = 1;
    final int TACHO = 2;
    final int INFINITY = 0;

    private static RegulatedMotor rightMotor = Motor.B;
    private static RegulatedMotor leftMotor = Motor.C;
    private static Timer timer = new Timer();

    private int leftPower = 0;
    private int rightPower = 0;

    final float maxSpeed = 33.0F;
    final float bodyWidth = 12.0F;
    final float wheelSize = 2.5F;

    /*---------------------------------------------------------
    コンストラクタ
    ---------------------------------------------------------*/

    public RunningActuator()
    {
    }

    /*---------------------------------------------------------
    片側出力指定
    ---------------------------------------------------------*/

    public void setPowerSide(int power, int motor)
    {
        switch (motor)
        {
        case LEFT:
            leftPower = power;
            leftMotor.setSpeed(leftPower);
        case RIGHT:
            rightPower = power;
            rightMotor.setSpeed(rightPower);
        }
    }

    /*---------------------------------------------------------
    両側出力指定
    ---------------------------------------------------------*/

    public void setPowerBoth(int leftpower, int rightpower)
    {
        leftPower = leftpower;
        rightPower = rightpower;
        leftMotor.setSpeed(leftPower);
        rightMotor.setSpeed(rightPower);
    }

    /*---------------------------------------------------------
    持続量(実時間)
    ---------------------------------------------------------*/

    public void setDuringTime(int limit)
    {
        timer.deLay(limit);
    }

    /*---------------------------------------------------------
    持続量(回転量)
    ---------------------------------------------------------*/

    public boolean setDuringTacho(int tacho, int motor)
    {
        switch (motor)
        {
        case LEFT:
            if (leftMotor.getTachoCount() <= tacho)
                return true;
        case RIGHT:
            if (rightMotor.getTachoCount() <= tacho)
                return true;
        }
        return false;

    }

    /*---------------------------------------------------------
    直進(左/右)  runStraight(X)
    ---------------------------------------------------------*/

    public void runStraight(int power, int t, int unit)
    {
        switch (unit)
        {
        case TIME:
            runStraightByTime(power, t);
        case TACHO:
            runStraightByTacho(power, t);
        case INFINITY:
            runStraight(power);
        }

    }

    /*---------------------------------------------------------
    直進(実時間) runStraightByTime(X, T)
    ---------------------------------------------------------*/

    public void runStraightByTime(int power, int time)
    {
        leftPower = power;
        rightPower = power;

        setPowerBoth(leftPower, rightPower);

        leftMotor.forward();
        rightMotor.forward();

        setDuringTime(time);

    }

    /*---------------------------------------------------------
    直進(回転量)  runStraightByTacho(X, T)
    ---------------------------------------------------------*/

    public void runStraightByTacho(int power, int tacho)
    {
        while (setDuringTacho(tacho, LEFT))
        {
            leftPower = power;
            rightPower = power;

            setPowerBoth(leftPower, rightPower);

            leftMotor.forward();
            rightMotor.forward();
        }

    }

    /*---------------------------------------------------------
    直進(無限)  runStraight(X)
    ---------------------------------------------------------*/

    public void runStraight(int power)
    {
        leftPower = power;
        rightPower = power;

        setPowerBoth(leftPower, rightPower);

        leftMotor.forward();
        rightMotor.forward();

    }

    /*---------------------------------------------------------
    転回(左/右)  goSpin(X)
    ---------------------------------------------------------*/

    public void runSpin(int power, int t, int unit)
    {
        switch (unit)
        {
        case TIME:
            runSpinByTime(power, t);
        case TACHO:
            runSpinByTacho(power, t);
        case INFINITY:
            runSpin(power);
        }
    }

    /*---------------------------------------------------------
    転向(実時間)  runSpinByTime(X, T)
    ---------------------------------------------------------*/

    public void runSpinByTime(int power, int time)
    {
        leftPower = power;
        rightPower = power;

        setPowerBoth(leftPower, rightPower);

        leftMotor.backward();
        rightMotor.forward();

        setDuringTime(time);
    }

    /*---------------------------------------------------------
    転向(回転量)  runSpinByTacho(X, T)
    ---------------------------------------------------------*/

    public void runSpinByTacho(int power, int tacho)
    {
        while (setDuringTacho(tacho, LEFT))
        {
            leftPower = power;
            rightPower = power;

            setPowerBoth(leftPower, rightPower);

            leftMotor.backward();
            rightMotor.forward();
        }
    }

    /*---------------------------------------------------------
    転回(無限)  goSpin(X)
    ---------------------------------------------------------*/

    public void runSpin(int power)
    {
        leftPower = power;
        rightPower = power;

        setPowerBoth(leftPower, rightPower);

        leftMotor.backward();
        rightMotor.forward();
    }

    /*---------------------------------------------------------
    曲進(実時間)  runCurveByTime(X_L, K_R, T)
    ---------------------------------------------------------*/

    public void runCurveByTime(int leftpower, int rightpower, int time)
    {
        leftPower = leftpower;
        rightPower = rightpower;

        setPowerBoth(leftPower, rightPower);

        leftMotor.forward();
        rightMotor.forward();

        setDuringTime(time);
    }

    /*---------------------------------------------------------
    曲進(回転量)  runCurveByTacho(X_L. X_R, T, W)
    ---------------------------------------------------------*/

    public void runCurveByTacho(int leftpower, int rightpower, int tacho,
            int motor)
    {
        switch (motor)
        {
        case LEFT:
            while (leftMotor.getTachoCount() <= tacho)
            {
                leftPower = leftpower;
                rightPower = rightpower;

                setPowerBoth(leftPower, rightPower);

                leftMotor.forward();
                rightMotor.forward();
            }
        case RIGHT:
            while (rightMotor.getTachoCount() <= tacho)
            {
                leftPower = leftpower;
                rightPower = rightpower;

                setPowerBoth(leftPower, rightPower);

                leftMotor.forward();
                rightMotor.forward();
            }
        }
    }

    /*---------------------------------------------------------
    曲進(無限)  runCurve(X_L, K_R)
    ---------------------------------------------------------*/

    public void runCurve(int leftpower, int rightpower)
    {
        leftPower = leftpower;
        rightPower = rightpower;

        setPowerBoth(leftPower, rightPower);

        leftMotor.forward();
        rightMotor.forward();
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
    出力量(片側)の増減 changePowerEach(L/R, X)
    ---------------------------------------------------------*/

    public void changePowerSide(int diff, int motor)
    {
        switch (motor)
        {
        case LEFT:
            leftPower += diff;
        case RIGHT:
            rightPower += diff;
        }

        setPowerBoth(leftPower, rightPower);
    }

    /*---------------------------------------------------------
    出力量(両側)の増減 changePowerBoht(X_L, X_R)
    ---------------------------------------------------------*/

    public void changePowerBoth(int diffleft, int diffright)
    {
        leftPower += diffleft;
        rightPower += diffright;

        setPowerBoth(leftPower, rightPower);
    }

    /*---------------------------------------------------------
    逆駆動  reversePedal()
    ---------------------------------------------------------*/

    public void reversePedal()
    {
        leftPower = -leftPower;
        rightPower = -rightPower;
        leftMotor.setSpeed(leftPower);
        rightMotor.setSpeed(rightPower);

        leftMotor.forward();
        rightMotor.forward();
    }

    /*---------------------------------------------------------
    逆操舵  reverseSteering()
    ---------------------------------------------------------*/

    public void reverseSteering()
    {
        int mid = leftPower;
        leftPower = rightPower;
        rightPower = mid;

        leftMotor.setSpeed(leftPower);
        rightMotor.setSpeed(rightPower);

        leftMotor.forward();
        rightMotor.forward();
    }

    /*---------------------------------------------------------
    駆動量  getpedal()
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
