import java.awt.Color;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
































































































































































































































class JTABLE
  extends JFrame
{
  JTable Alerts_Table;
  JScrollPane jScrollPane1;
  
  public JTABLE()
  {
    initComponents();
  }
  

  private void initComponents()
  {
    jScrollPane1 = new JScrollPane();
    Alerts_Table = new JTable();
    
    setDefaultCloseOperation(2);
    setTitle("Alerts");
    
    Alerts_Table.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
    Alerts_Table.setModel(new DefaultTableModel(new Object[0][], new String[] { "Time", "Alert" })
    {






      boolean[] canEdit = { false, false };



    });
    Alerts_Table.setToolTipText("");
    jScrollPane1.setViewportView(Alerts_Table);
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, -1, 557, 32767));
    
    layout.setVerticalGroup(layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, -1, 300, 32767));
    

    pack();
  }
}
