import java.awt.EventQueue;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JDialog;
import javax.swing.JOptionPane;



























public class Splunk_Audible_Alerts_UDP
{
  public Splunk_Audible_Alerts_UDP() {}
  
  public static void main(String[] args)
  {
    if (!SystemTray.isSupported()) {
      JOptionPane.showMessageDialog(null, "Error: your sysytem don't support tray icon");
      JOptionPane.showMessageDialog(null, "Using Traditional Notifier");
      new Notification().setVisible(true);
    }
    else {
      try {
        System_Tray.createTrayIcon();
      }
      catch (Exception localException) {}
    }
    try
    {
      File sounds = new File("files/sounds.conf");
      File config = new File("files/config.conf");
      File alert = new File("files/alerts.conf");
      TreeMap<String, String> soundsMap = new TreeMap();
      Scanner scanner = new Scanner(sounds);
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      while (scanner.hasNextLine()) {
        String[] line = scanner.nextLine().split(" ");
        int number = Integer.parseInt(line[0]);
        min = min < number ? min : number;
        max = max > number ? max : number;
        soundsMap.put(line[0], line[1]);
      }
      TreeMap<String, String> alertsMap = new TreeMap();
      scanner = new Scanner(alert);
      while (scanner.hasNextLine()) {
        String[] line = scanner.nextLine().split(" ");
        int number = Integer.parseInt(line[0]);
        min = min < number ? min : number;
        max = max > number ? max : number;
        alertsMap.put(line[0], line[1]);
      }
      ExecutorService executor = Executors.newFixedThreadPool(max + 1);
      executor.execute(new Runnable()
      {
        public void run() {
          for (;;) {
            File alertslog = new File("files/alerts.log");
            if (alertslog.exists()) {
              try {
                alertslog.delete();
                Thread.sleep(86400000L);
              } catch (Exception localException) {}
            }
          }
        }
      });
      scanner = new Scanner(config);
      String host = scanner.nextLine().split(" ")[1];
      int port = Integer.parseInt(scanner.nextLine().split(" ")[1]);
      DatagramSocket DS = new DatagramSocket(port);
      byte[] data = new byte[1];
      DatagramPacket packet = new DatagramPacket(data, data.length);
      for (;;) {
        DS.receive(packet);
        String address = packet.getAddress().toString();
        int index = Integer.parseInt((char)data[0] + "");
        address = address.substring(1, address.length());
        if ((!address.equalsIgnoreCase(host)) || (index < min) || (index > max)) {
          Toolkit.getDefaultToolkit().beep();
          JOptionPane.showMessageDialog(null, "Error, Connection from strange IP or Unknown data.    " + address + "   " + index);
          logAlert("Wrong connection or data from host: " + host);
        }
        else {
          Music_Player pm = new Music_Player();
          pm.setSound((String)soundsMap.get(index + ""));
          executor.execute(pm);
          logAlert((String)alertsMap.get(index + ""));
        }
      }
    } catch (Exception localException1) {}
  }
  
  private static void logAlert(String alert) throws Exception {
    File alerts = new File("files/alerts.log");
    PrintWriter pw = new PrintWriter(new FileOutputStream(alerts, true));
    pw.append(new Timestamp(new Date().getTime()) + "," + alert + "\n");
    pw.close();
    EventQueue.invokeLater(new Runnable()
    {
      public void run() {
        JOptionPane op = new JOptionPane(val$alert, 1);
        JDialog dialog = op.createDialog("Break");
        dialog.setAlwaysOnTop(true);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(2);
        dialog.setVisible(true);
      }
    });
  }
}
