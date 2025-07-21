package quizzbuzzpro;

import javax.sound.sampled.*;
import java.io.InputStream;

public class SoundPlayer {

    private Clip clip;

    public void playLoop(String fileName) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream("/sound/" + fileName);
            if (audioSrc == null) {
                System.out.println("⚠ File not found: " + fileName);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("⚠ Failed to play looped sound: " + e.getMessage());
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    public void playOnce(String fileName) {
        try {
            InputStream audioSrc = getClass().getResourceAsStream("/sound/" + fileName);
            if (audioSrc == null) {
                System.out.println("⚠ File not found: " + fileName);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            Clip oneShotClip = AudioSystem.getClip();
            oneShotClip.open(audioStream);
            oneShotClip.start();
        } catch (Exception e) {
            System.out.println("⚠ Failed to play one-shot sound: " + e.getMessage());
        }
    }
}
