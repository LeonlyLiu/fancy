package infoexpr.project.project41;

/*=============================================================
個別任務  目標接近  近隣停止
=============================================================*/

public class NeighborStop
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 機構
	private TargetLock targetLock;

	// ---- 距離パラメタ
	private float limitDistance = 40.0F;
	private float bufferDistance = 10.0F;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public NeighborStop()
	{
		targetLock = new TargetLock();
	}

	/*------------------------------------------------------
	近接検知と停止
	------------------------------------------------------*/

	public void CloseDetector()
	{
		while (targetLock.LockWithDistance(limitDistance, bufferDistance))
		{
		}
		TargetAccess.wheelActuator.stopForce();
	}
}
