package infoexpr.Library.Factory;

import infoexpr.Library.Enums.DirectionSide;

import java.security.InvalidParameterException;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;

/*=============================================================
機素生成  モーター生成
=============================================================*/

public class MotorFactory
{

	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private static RegulatedMotor left = Motor.C;
	private static RegulatedMotor right = Motor.B;
	private static RegulatedMotor center = Motor.A;

	/*------------------------------------------------------
	コンストラクタ
	ポートを固定したモーターの生成
	------------------------------------------------------*/

	public MotorFactory() throws InstantiationException {
		throw new InstantiationException("SensorFacctory is not expected creation instance");
	}


	public static RegulatedMotor createMotor(DirectionSide side)
	{
		if (side == DirectionSide.LEFT)
		{
			return left;
		} else if (side == DirectionSide.RIGHT)
		{
			return right;
		} else if (side == DirectionSide.CENTER)
		{
			return center;
		} else
		{
			throw new InvalidParameterException("Unexpected motor side");
		}
	}

}
