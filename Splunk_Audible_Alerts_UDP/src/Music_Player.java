
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author theblackdevil
 */
class Music_Player implements Runnable {
  private String sound;
  public void setSound(String sound) {
   this.sound = sound;
  }
  public void run() {
   try {
    int timer = 5;
    SimpleAudioPlayer sap = new SimpleAudioPlayer(this.sound);
    sap.play();
    while (timer > 0) {
     Thread.sleep(1000);
     timer--;
    }
    sap.stop();
   } catch (Exception e) {
    System.out.println("Error");
   }
  }
 }
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
