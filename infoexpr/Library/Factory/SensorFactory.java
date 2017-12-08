package infoexpr.Library.Factory;

import infoexpr.Library.Device.Input.Sensor.ColorSensor;
import infoexpr.Library.Device.Input.Sensor.SonicSensor;
import infoexpr.Library.Device.Input.Sensor.TouchSensor;
import infoexpr.Library.Enums.DirectionSide;

import java.security.InvalidParameterException;

import lejos.hardware.port.SensorPort;

/*=============================================================
機素生成  センサ生成
=============================================================*/

public class SensorFactory
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 各種センサ
	private static ColorSensor leftColorSensor = new ColorSensor(SensorPort.S3);
	private static ColorSensor rightColorSensor = new ColorSensor(SensorPort.S2);
	private static TouchSensor touchSensor = new TouchSensor(SensorPort.S1);
	private static SonicSensor sonicSensor = new SonicSensor(SensorPort.S4);

	public SensorFactory() throws InstantiationException {
		throw new InstantiationException("SensorFacctory is not expected creation instance");
	}

	/*------------------------------------------------------
	左右の指定による色彩センサの生成
	！ 色彩センサは、光量センサの機能も兼ねている
	------------------------------------------------------*/

	public static ColorSensor createColorSensor(DirectionSide side)
	{
		if (side == DirectionSide.LEFT)
		{
			return leftColorSensor;
		} else if (side == DirectionSide.RIGHT)
		{
			return rightColorSensor;
		} else
		{
			throw new InvalidParameterException("Unexpected sensor side");
		}
	}

	/*------------------------------------------------------
	単独の接触センサの生成
	------------------------------------------------------*/

	public static TouchSensor createTouchSensor()
	{
		return touchSensor;
	}

	/*------------------------------------------------------
	単独の反響センサの生成
	！ 反響センサの取付部位には、前方と側方(左側)があるが
	！ 生成時に区別する必要はない
	！ 将来的には左右の反響センサを利用する可能性がある
	！ この場合には、左右や前後の指定が必要である
	------------------------------------------------------*/

	public static SonicSensor createSonicSensor()
	{
		return sonicSensor;
	}

	/*------------------------------------------------------
	単独の新規センサの生成
	！ 磁気センサ(方角)  MagneticSensor
	！ 傾斜センサ(回転)  TiltSensor
	！ 加速センサ(重力)  AccelationSensor
	！ 温度センサ  TemperatureSensor
	------------------------------------------------------*/

}
