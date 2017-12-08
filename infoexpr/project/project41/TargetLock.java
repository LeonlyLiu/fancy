package infoexpr.project.project41;

/*=============================================================
個別任務  目標接近  目標定位
=============================================================*/

public class TargetLock implements Runnable
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	private float limitDistance;
	private float bufferDistance;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public TargetLock()
	{
	}

	public TargetLock(float limitDistance, float bufferDistance)
	{
		this.limitDistance = limitDistance;
		this.bufferDistance = bufferDistance;
	}

	public void run()
	{
		if (TargetAccess.targetDetection.getDistance() < limitDistance + bufferDistance)
		{
			TargetAccess.wheelActuator.stopForce();
		}
	}

	/*------------------------------------------------------
	distanceで目標を定位
	------------------------------------------------------*/

	public boolean LockWithDistance(float limitDistance, float bufferDistance)
	{
		if (TargetAccess.targetDetection.getDistance() < limitDistance + bufferDistance)
		{
			return true;
		}
		return false;
	}

}
