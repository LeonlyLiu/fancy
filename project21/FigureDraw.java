package project21;

import jp.ac.kagawa_u.infoexpr.Mechanism.ActuatorMechanism.RunningActuator;

public class FigureDraw
{
    public static RunningActuator runningActuator = new RunningActuator();

    private static final int timeV3 = 15000;
    private final StraightTraveling straightTraveling;
    private final CurveTraveling curveTraveling;

    public FigureDraw()
    {
        this.straightTraveling = new StraightTraveling();
        this.curveTraveling = new CurveTraveling():

    }

    public static void main(String[] args)
    {
        int timeCurve = 0;

        int timeStraight = timeV3 - timeCurve;
        this.straightTraveling.traveling(timeStraight):

        this.curveTraveling.traveling():
    }

}

