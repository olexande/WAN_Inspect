package  InternalFrame.frame;

import InternalFrame.logic.MyDatabaseTableModel;
import java.sql.*;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

/* Used by InternalFrameDemo.java. */
public class MyInternalTableFrame_2 extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 60, yOffset = 60;

    public MyInternalTableFrame_2() {
        super("Работники #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...
        ButtonPanel_2 panel = new ButtonPanel_2();
        add(panel);
        //...Then set the window size or call pack...
        setSize(520, 140);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
}

class ButtonPanel_2 extends JPanel
{
    private boolean DEBUG = false;
    public ButtonPanel_2()
    {
        Statement statement = null;
        ResultSet resultset;
        Connection conn;
        try
        {
               Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception ex)
        {
                System.out.println("error!" + ex.getMessage());
        }
        try
        {          
            conn = DriverManager.getConnection("jdbc:mysql://192.168.2.224:3306/lan_db?user=landb_user&password=landb_user&useUnicode=true&characterEncoding=KOI8_R");
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM `Auth_Table`");
            
            try {
                MyDatabaseTableModel model = new MyDatabaseTableModel();
                JTable table = new JTable(model);
                add(table);
                model.setDataSource(resultSet);
                }
            catch (Exception ex)
            {
                System.out.println("error!" + ex.getMessage());
            }
            
        }
        catch (SQLException ex)
        {                     
            System.out.println("SQLException: " + ex.getMessage()); 
            System.out.println("SQLState: "     + ex.getSQLState()); 
            System.out.println("VendorError: "  + ex.getErrorCode());                      
        }
    }
}