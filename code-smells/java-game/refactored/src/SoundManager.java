package src;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
  public void playSound(final String soundFilePath) {
    try {
      final File soundFile = new File(soundFilePath).getAbsoluteFile();
      final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      final Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch (final Exception exception) {
      System.out.println("Error playing sound: " + exception.getMessage());
    }
  }
}
