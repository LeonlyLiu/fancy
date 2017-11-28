package project21;

import jp.ac.kagawa_u.infoexpr.Mechanism.ActuatorMechanism.RunningActuator;

public class FigureDraw
{
    public static RunningActuator runningActuator = new RunningActuator();

    static int TimeV3 = 15000; // ゴールまでの時間

    public static void main(String[] args)
    {
        Thread soundThread = new Thread(new SoundPlayer());

        soundThread.start();

        StraightTraveling.straightTraveling();

        CurveTraveling.curveTraveling();

    }
}
