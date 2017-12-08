package infoexpr.project.Project21;

import infoexpr.Library.Device.Output.Speaker;

/*=============================================================
図形模走  警音任務
=============================================================*/

public class BeepAtPosition implements Runnable
{
	public static Speaker speaker = new Speaker();

	@Override
	public void run()
	{
		int delayTime = 3000;

		speaker.beepAfterTime(delayTime);
	}
}
