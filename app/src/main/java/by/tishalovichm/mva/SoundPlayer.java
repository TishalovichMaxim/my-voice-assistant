package by.tishalovichm.mva;

import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.io.InputStream;

@Service
public class SoundPlayer {

    public void play(InputStream inputStream) throws Exception {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(inputStream));
        clip.addLineListener((v) -> {
            if (v.getType() == LineEvent.Type.STOP) {
                synchronized(this) {
                    notify();
                }
            }
        });

        clip.start();
        synchronized(this) {
            wait();
        }
    }
}
