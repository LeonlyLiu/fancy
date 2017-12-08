package infoexpr.Library.Device.Input.Sensor;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;


/*==========================================================
機素  入力機素  センサ  反響センサ
==========================================================*/

public class SonicSensor extends EV3UltrasonicSensor implements Sensor
{

    private SensorMode mode = this.getMode(0);

    /**
     * SonicSensorのコンストラクタ
     *
     * @param Port
     *            SensorPort
     */
    public SonicSensor(Port port)
    {
        super(port);
    }

    /**
     * センサー値を取得
     *
     * @return float[] データが格納されたfloat型配列
     */
    public float[] getValue()
    {
        float[] result = new float[mode.sampleSize()];
        mode.fetchSample(result, 0);
        return result;
    }

    /**
     * 距離の値を取得
     *
     * @return float 距離の値が格納されたfloat型
     */
    public float getDistance()
    {
        return this.getValue()[0];
    }

}
