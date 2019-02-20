import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notification extends javax.swing.JFrame
{
  private JButton exit;
  private JLabel jLabel1;
  private JPanel jPanel1;
  
  public Notification()
  {
    initComponents();
  }
  







  private void initComponents()
  {
    jPanel1 = new JPanel();
    jLabel1 = new JLabel();
    exit = new JButton();
    
    setDefaultCloseOperation(3);
    
    jLabel1.setText("Splunk Audible Alerts is Running");
    jLabel1.setToolTipText("");
    jLabel1.setAlignmentX(0.5F);
    jLabel1.setHorizontalTextPosition(0);
    
    exit.setText("stop service");
    exit.setToolTipText("");
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Notification.this.exitActionPerformed(evt);
      }
      
    });
    GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
      .addGap(51, 51, 51)
      .addComponent(jLabel1)
      .addContainerGap(55, 32767))
      .addComponent(exit, GroupLayout.Alignment.TRAILING, -1, -1, 32767));
    
    jPanel1Layout.setVerticalGroup(jPanel1Layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
      .addComponent(jLabel1, -2, 50, -2)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
      .addComponent(exit, -1, 50, 32767)));
    

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, -1, -1, 32767));
    
    layout.setVerticalGroup(layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, -1, -1, 32767));
    

    pack();
  }
  
  private void exitActionPerformed(ActionEvent evt)
  {
    int dialogResult = javax.swing.JOptionPane.showConfirmDialog(rootPane, "Do you want to stop service?");
    if (dialogResult == 0) {
      System.exit(0);
    }
  }
}
