package jp.ac.kagawa_u.infoexpr.Device.OutputDevice;

import lejos.hardware.Sound;
import lejos.utility.Delay;

public class SoundSpeaker
{
    /*---------------------------------------------------------
    コンストラクタ
    ---------------------------------------------------------*/

    public SoundSpeaker()
    {
    }

    /*---------------------------------------------------------
    ビープ音を鳴らす
    ---------------------------------------------------------*/

    public void beep(int delayTime)
    {
        Delay.msDelay(delayTime);
        Sound.beep();

    }

    /*---------------------------------------------------------
    duration時間freqの音を鳴らす
    ---------------------------------------------------------*/

    public void playTone(int freq, int duration, int delayTime)
    {
        Sound.playTone(freq, duration);
        Delay.msDelay(delayTime);
    }
}
