package project21;

public class CurveTraveling
{

    static int PowerC1L = 100;
    static int PowerC1R = 100;
    static int PowerV3 = 100;
    static int PowerC2L = 100;
    static int PowerC2R = 100;
    static int PowerL2 = 90;

    static int TimeC1 = 500;
    static int TimeC2 = 900;
    static int TimeV3 = 150;

    public void traveling()
    {
        // 曲進C1
        FigureDraw.runningActuator.runCurveByTime(PowerC1L, PowerC1R, TimeC1);

        // 転向V3
        FigureDraw.runningActuator.runSpinByTime(PowerV3, TimeV3);

        // 曲進C2
        FigureDraw.runningActuator.runCurveByTime(PowerC2L, PowerC2R, TimeC2);

        // 停止
        FigureDraw.runningActuator.stopForce();
    }

}

