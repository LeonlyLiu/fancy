package project21;

import jp.ac.kagawa_u.infoexpr.Device.OutputDevice.SoundSpeaker;

public class SoundPlayer implements Runnable
{
    public static SoundSpeaker voice = new SoundSpeaker();

    @Override
    public void run()
    {

        int soundTime = 3000;

        voice.beep(soundTime);

    }

}
