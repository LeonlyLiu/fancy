package project21;

public class StraightTraveling
{
    static int PowerL1 = 100;
    static int PowerL2 =  90;
    static int PowerV1 = 100;
    static int PowerV2 = 100;

    static int TimeL1 = 500;
    static int TimeL2 = 900;
    static int TimeV1 = 150;
    static int TimeV2 = 100;

    public void traveling(String[] args)
    {
        Thread soundThread = new Thread(new SoundPlayer());
        soundThread.start();

        // 直進L1
        FigureDraw.runningActuator.runStraightByTime(PowerL1, TimeL1);

        // 転向V1
        FigureDraw.runningActuator.runSpinByTime(PowerV1, TimeV1);

        // 直進L2
        FigureDraw.runningActuator.runStraightByTime(PowerL2, TimeL2);

        // 転向V2
        FigureDraw.runningActuator.runSpinByTime(PowerV1, TimeV1);
    }

}
