import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

class System_Tray
{
  System_Tray() {}
  
  public static void createTrayIcon()
    throws Exception
  {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    TrayIcon trayIcon = new TrayIcon(new ImageIcon("files/splunk.png", "splunk audible alerts").getImage());
    trayIcon.setImageAutoSize(true);
    trayIcon.setToolTip("splunk audible alerts");
    SystemTray tray = SystemTray.getSystemTray();
    trayIcon.addMouseListener(new MouseListener()
    {
      public void mouseClicked(MouseEvent e) {
        PopupMenu pum = new PopupMenu("POP UP MENU");
        MenuItem showAlerts = new MenuItem("Show Alerts");
        MenuItem exit = new MenuItem("Exit");
        showAlerts.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            try {
              
            } catch (Exception localException) {}
          }
        });
        exit.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });
        pum.add(showAlerts);
        pum.addSeparator();
        pum.add(exit);
        pum.setEnabled(true);
        val$trayIcon.setPopupMenu(pum);
      }
      



      public void mousePressed(MouseEvent e) {}
      



      public void mouseReleased(MouseEvent e) {}
      



      public void mouseEntered(MouseEvent e) {}
      



      public void mouseExited(MouseEvent e) {}
    });
    tray.add(trayIcon);
  }
  
  static void showAlerts() throws Exception { 
File alerts = new File("files/alerts.log");
    if ((!alerts.exists()) || (alerts.length() <= 0L)) {
      JOptionPane.showMessageDialog(null, "can't find any alerts");
      return;
    }
    JTABLE jtable = new JTABLE();
    jtable.setVisible(true);
    Scanner scanner = new Scanner(alerts);
    while (scanner.hasNextLine()) {
      try {
        String[] line = scanner.nextLine().split(",");
        ((DefaultTableModel)Alerts_Table.getModel()).addRow(new Object[] { line[0], line[1] });
      }
      catch (Exception localException) {}
    }
  }
}
