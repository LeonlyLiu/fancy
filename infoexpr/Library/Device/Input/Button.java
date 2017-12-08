package infoexpr.Library.Device.Input;

/*==========================================================
機素  入力機素  ボタン
==========================================================*/

public class Button
{

	/*---------------------------------------------------------
	コンストラクタ
	---------------------------------------------------------*/

	public Button()
	{

	}

	/*---------------------------------------------------------
	ESCAPE ボタン
	---------------------------------------------------------*/

	public boolean IsEscapeButtonDown()
	{
		return lejos.hardware.Button.ESCAPE.isDown();
	}

	/*---------------------------------------------------------
	ENTER ボタン
	---------------------------------------------------------*/

	public boolean IsEnterButtonDown()
	{
		return lejos.hardware.Button.ENTER.isDown();
	}

	/*---------------------------------------------------------
	LEFT ボタン
	---------------------------------------------------------*/

	public boolean IsLeftButtonDown()
	{
		return lejos.hardware.Button.LEFT.isDown();
	}

	/*---------------------------------------------------------
	RIGHTE ボタン
	---------------------------------------------------------*/

	public boolean IsRihtButtonDown()
	{
		return lejos.hardware.Button.RIGHT.isDown();
	}

	/*---------------------------------------------------------
	UP ボタン
	---------------------------------------------------------*/

	public boolean IsUpButtonDown()
	{
		return lejos.hardware.Button.UP.isDown();
	}

	/*---------------------------------------------------------
	DOWN ボタン
	---------------------------------------------------------*/

	public boolean IsDownButtonDown()
	{
		return lejos.hardware.Button.DOWN.isDown();
	}

}
