package components;

import javax.swing.JInternalFrame;

import java.awt.event.*;
import java.awt.*;
import java.lang.reflect.*;
import java.net.*;
import java.text.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.text.*;

/* Used by InternalFrameDemo.java. */
public class MyInternalFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public MyInternalFrame() {
        super("���������� #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...
        ButtonPanel panel = new ButtonPanel();
        add(panel);
        //...Then set the window size or call pack...
        setSize(280,315);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
}

class ButtonPanel extends JPanel
{
    public ButtonPanel()
    {                      
        
        add(new JLabel("���:"));
        final JTextField nameField = new JTextField("������� ���", 20);
        add(nameField);
        
        add(new JLabel("�������:"));
        final JTextField familiaField = new JTextField("������� �������:", 17);
        add(familiaField);
                
        add(new JLabel("�������� :"));
        final JTextField otchestvoField = new JTextField("������� ��������:", 17);
        add(otchestvoField);
        
        add(new JLabel("��������� :"));
        final JTextField dolgnostyField = new JTextField("������� ���������:", 16);
        add(dolgnostyField);
        
        add(new JLabel("������������� :"));
        final JTextField specialnostyField = new JTextField("������� �������������:", 14);
        add(specialnostyField);
        
        add(new JLabel("������� �����. :"));
        final JTextField IntPhoneField = new JTextField("����� ��������:", 14);
        add(IntPhoneField);
        
        add(new JLabel("������� ���.:"));
        final JTextField MobPhoneField = new JTextField("����� ��������:", 15);
        add(MobPhoneField);
        
        add(new JLabel("������� ���.:"));
        final JTextField HomePhoneField = new JTextField("����� ��������:", 15);
        add(HomePhoneField);
        
        final JButton EditButton = new JButton("�������� ������.");
        add(EditButton);
        
        //add(new JLabel("                                                                                              "));
        
        //JLabel textlabel1 = new JLabel("��������� �����.");
        //add(textlabel1);
        
        //Object value = nameField.getValue();
        
        EditButton.addActionListener(new
            ActionListener()
             {
                public void actionPerformed(ActionEvent event)
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
                           System.out.println("error!");
                   }
                   try
                   {          
                     conn = DriverManager.getConnection("jdbc:mysql://192.168.1.90:3306/test?user=User&password=Password&useUnicode=true&characterEncoding=KOI8_R");
                     /*statement = conn.createStatement();    
                     //nameField.get
                     statement.executeUpdate("INSERT INTO `test` VALUES ('a', 'a', 'a', 'a', 'a', '1');");
                     //System.exit(0); // ��� ������ �� ���������� (������ ��� �������� ������������)
                   }
                   catch (SQLException ex)
                   {                     
                     System.out.println("SQLException: " + ex.getMessage()); 
                     System.out.println("SQLState: "     + ex.getSQLState()); 
                     System.out.println("VendorError: "  + ex.getErrorCode());                      
                   }
                   try
                   {*/
                     PreparedStatement stmt = conn.prepareStatement(
                             "UPDATE test SET " + "NV=?, KS=?, DIN=?, VIN=?, DOUT=?" + 
                             "WHERE VOUT=2");
                     stmt.setString(1, nameField.getText());
                     stmt.setString(2, "c");
                     stmt.setString(3, "c");
                     stmt.setString(4, "c");
                     stmt.setString(5, "c");
                     
                     
                     stmt.execute();
                     //statement.executeUpdate("INSERT INTO `test` VALUES ('a', 'a', 'a', 'a', 'a', '2');");
                     System.exit(0); // ��� ������ �� ���������� (������ ��� �������� ������������)
                   }
                   catch (SQLException ex)
                   {                     
                     System.out.println("SQLException: " + ex.getMessage()); 
                     System.out.println("SQLState: "     + ex.getSQLState()); 
                     System.out.println("VendorError: "  + ex.getErrorCode());                      
                   }
                }
             });                      
    }
}