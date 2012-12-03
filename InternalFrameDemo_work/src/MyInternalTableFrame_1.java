package components;

import javax.swing.JInternalFrame;

import java.awt.event.*;
import java.awt.*;
import java.lang.reflect.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

/* Used by InternalFrameDemo.java. */
public class MyInternalTableFrame_1 extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 60, yOffset = 60;

    public MyInternalTableFrame_1() {
        super("Сотрудники #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...
        ButtonPanel_1 panel = new ButtonPanel_1();
        add(panel);
        //...Then set the window size or call pack...
        setSize(520, 140);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
}

class ButtonPanel_1 extends JPanel
{
    private boolean DEBUG = false;
    public ButtonPanel_1()
    {                
        
        /* *****  ***begin insert
        Statement statement = null;
        ResultSet resultset;
        Connection conn;
        try
        {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
        }
        catch (Exception ex)
        {
                System.out.println("error!");
        }
        try
        {          
          conn = DriverManager.getConnection("jdbc:mysql://192.168.1.90:3306/test?user=User&password=Password");
          statement = conn.createStatement();    
          //nameField.get
          statement.executeUpdate("INSERT INTO `test` VALUES ('a', 'a', 'a', 'a', 'a', 'ы');");
          System.exit(0); // Для выхода по выполнении (только для удобства тестирования)
        }
        catch (SQLException ex)
        {                     
          System.out.println("SQLException: " + ex.getMessage()); 
          System.out.println("SQLState: "     + ex.getSQLState()); 
          System.out.println("VendorError: "  + ex.getErrorCode());                      
        }      
        
        **** end insert */
        
        
        
        
        String[] columnNames = {"First Name",
                                "Last Name",
                                "Sport",
                                "# of Years",
                                "Vegetarian"};

        Object[][] data = {
            {"Mary", "Campione",
             "Snowboarding", new Integer(5), new Boolean(false)},
            {"Alison", "Huml",
             "Rowing", new Integer(3), new Boolean(true)},
            {"Kathy", "Walrath",
             "Knitting", new Integer(2), new Boolean(false)},
            {"Sharon", "Zakhour",
             "Speed reading", new Integer(20), new Boolean(true)},
            {"Philip", "Milne",
             "Pool", new Integer(10), new Boolean(false)}
        };

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 80));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                   // printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
}