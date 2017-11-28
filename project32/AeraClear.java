package project32;

import jp.ac.kagawa_u.infoexpr.Device.InputDevice.Timer;
import jp.ac.kagawa_u.infoexpr.Mechanism.ActuatorMechanism.RunningActuator;
import jp.ac.kagawa_u.infoexpr.Mechanism.DetectionMechanism.FloorDetection;

public class AeraClear

{
    public static RunningActuator runningActuator = new RunningActuator();
    public static FloorDetection detector = new FloorDetection();
    public static Timer timer = new Timer();
    private static int speed = 400;
    private static int time = 0;
    private static int timelimit = 6000;
    private static int oneSecend = 100;
    private static int backTime = 500;
    private static int spinTime = 300;

    final static int blackBlack = 1;
    final static int blackWhite = 2;
    final static int whiteBlack = 3;
    final static int whiteWhite = 4;
    final static int error = 0;

    public static void areaSweeping()
    {
        while (time <= timelimit)
        {
            if (detector.BorderDetection() == blackBlack)
            {
                runningActuator.reversePedal();
                runningActuator.setDuringTime(backTime);
                runningActuator.runSpinByTime(speed, spinTime);
                runningActuator.runStraight(speed);
            } else if (detector.BorderDetection() == blackWhite)
            {
                runningActuator.runSpinByTime(-speed, spinTime);
                runningActuator.runStraight(speed);
            } else if (detector.BorderDetection() == whiteBlack)
            {
                runningActuator.runSpinByTime(speed, spinTime);
                runningActuator.runStraight(speed);
            } else if (detector.BorderDetection() == whiteWhite)
            {
                runningActuator.runStraight(speed);
            }

            timer.deLay(oneSecend);
            time += oneSecend;
        }
    }
}
