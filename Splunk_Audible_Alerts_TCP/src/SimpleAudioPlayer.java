
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

 class SimpleAudioPlayer {
  Long currentFrame;
  Clip clip;
  String status;
  AudioInputStream audioInputStream;
  String filePath;
  public SimpleAudioPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
   this.filePath = filePath;
   audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
   clip = AudioSystem.getClip();
   clip.open(audioInputStream);
   clip.loop(Clip.LOOP_CONTINUOUSLY);
  }
  public void play() {
   clip.start();
   status = "play";
  }
  public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
   currentFrame = 0L;
   clip.stop();
   clip.close();
  }
 }