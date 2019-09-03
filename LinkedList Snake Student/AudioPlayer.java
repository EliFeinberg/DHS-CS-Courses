import javax.sound.sampled.*;
/**
 * Audio.java  
 *
 * @author: Julian Nisenboim 
 *          Seth Friman added adjust volume method
 *          Used by Katharine Stecher and Allison Wexler with permission
 * 
 * Brief Program Description: Allows for audio clips to be added to a project
 * 
 *
 */
public class AudioPlayer 
{
    public static boolean soundOn = true;
    private Clip clip;
    
    /**
     * Constructor for the AudioPlayer Class
     * 
     * @param String a sound file to be accessed in the project files
     */
    public AudioPlayer(String s) 
    {
        try 
        {
            AudioInputStream ais =
                AudioSystem.getAudioInputStream(
                    getClass().getResourceAsStream(
                        s
                    )
                );
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
                );
            AudioInputStream dais =
                AudioSystem.getAudioInputStream(
                    decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * plays the audio file
     */
    public void play() 
    {
        if(soundOn)
        {
            if(clip == null)
            {
                return;
            }
            stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * adjusts the volume of the sound by a specified number of decibels
     * 
     * @param Float the decibels to be increased by
     */
    public void adjustVolume(Float decibels)
    {
        FloatControl gainControl = 
            (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(decibels);
    }

    /**
     * stops the sound
     */
    public void stop() 
    {
        if(clip.isRunning()) 
        {
            clip.stop();
        }
    }

    /**
     * closes the clip
     */
    public void close() 
    {
        stop();
        clip.close();
    }

}