package infoexpr.project.Project32;

/*=============================================================
応用課題  領域掃出
=============================================================*/

public class AreaKeeping
{
	/*------------------------------------------------------
	クラス変数
	------------------------------------------------------*/

	// ---- 任務
	private final RandomTraveling randomTraveling;

	/*------------------------------------------------------
	コンストラクタ
	------------------------------------------------------*/

	public AreaKeeping()
	{
		this.randomTraveling = new RandomTraveling();
	}

	/*------------------------------------------------------
	メイン
	------------------------------------------------------*/

	public static void main(String[] args)
	{
		new AreaKeeping().doGame();

	}

	/*------------------------------------------------------
	課題の実行
	------------------------------------------------------*/

	public void doGame()
	{
		randomTraveling.traveling();
	}

}