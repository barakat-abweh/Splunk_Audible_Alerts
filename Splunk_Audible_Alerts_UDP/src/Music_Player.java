import java.io.PrintStream;














class Music_Player
  implements Runnable
{
  private String sound;
  
  Music_Player() {}
  
  public void setSound(String sound) { this.sound = sound; }
  
  public void run() {
    try {
      int timer = 5;
      SimpleAudioPlayer sap = new SimpleAudioPlayer(sound);
      sap.play();
      while (timer > 0) {
        Thread.sleep(1000L);
        timer--;
      }
      sap.stop();
    } catch (Exception e) {
      System.out.println("Error");
    }
  }
}
