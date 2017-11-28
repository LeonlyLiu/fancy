package jp.ac.kagawa_u.infoexpr.Mechanism.DetectionMechanism;

import jp.ac.kagawa_u.infoexpr.Device.InputDevice.Sensor.ColorSensor;
import lejos.hardware.port.SensorPort;

public class FloorDetection
{
    static ColorSensor right = new ColorSensor(SensorPort.S2);
    static ColorSensor left = new ColorSensor(SensorPort.S3);

    final int blackBlack = 1;
    final int blackWhite = 2;
    final int whiteBlack = 3;
    final int whiteWhite = 4;
    final int error = 0;

    public int BorderDetection()
    {

        float middleValue = 0.40F;
        // 黒＆黒
        if (left.getLight() < middleValue && right.getLight() < middleValue)
        {
            return blackBlack;
        }

        // 黒＆白
        else if (left.getLight() < middleValue
                && right.getLight() >= middleValue)
        {
            return blackWhite;
        }

        // 白＆黒
        else if (left.getLight() >= middleValue
                && right.getLight() < middleValue)
        {
            return whiteBlack;
        }

        // 白＆白
        else if (left.getLight() >= middleValue
                && right.getLight() >= middleValue)
        {
            return whiteWhite;
        }
        return error;
    }
}
