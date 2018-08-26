
import java.awt.Toolkit;
import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author theblackdevil
 */
public class Splunk_Audible_Alerts_UDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            new Notification().setVisible(true);
    Scanner scanner;
    File sounds = new File("files/sounds.conf");
    File config = new File("files/config.conf");
    TreeMap < String, String > soundsMap = new TreeMap < String, String > () {};
    scanner = new Scanner(sounds);
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    while (scanner.hasNextLine()) {
     String[] line = scanner.nextLine().split(" ");
     int number = Integer.parseInt(line[0]);
     min = min < number ? min : number;
     max = max > number ? max : number;
     soundsMap.put(line[0], line[1]);
    }
    ExecutorService executor = Executors.newFixedThreadPool(max);
    scanner = new Scanner(config);
    String host = scanner.nextLine().split(" ")[1];
    int port = Integer.parseInt(scanner.nextLine().split(" ")[1]);
    DatagramSocket DS = new DatagramSocket(port);
    byte[] data = new byte[1];
    DatagramPacket packet = new DatagramPacket(data, data.length);
    while (true) {
     DS.receive(packet);
     String address = packet.getAddress().toString();
     int index = Integer.parseInt((char) data[0] + "");
     address = address.substring(1, address.length());
     if (!address.equalsIgnoreCase(host) || (index < min || index > max)) {
      Toolkit.getDefaultToolkit().beep();
      JOptionPane.showMessageDialog(null, "Error, Connection from strange IP or Unknown data.    "+host+"   "+index);
      continue;
     }
     Music_Player pm = new Music_Player();
     pm.setSound(soundsMap.get(index + ""));
     executor.execute(pm);
    }
   } catch (Exception e) {
    System.out.println("Error");
   }
    }
    
}
