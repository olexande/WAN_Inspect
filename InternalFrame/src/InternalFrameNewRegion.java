//package components;

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
public class InternalFrameNewRegion extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public InternalFrameNewRegion() {
        super("InternalFrameNewRegion #" + (++openFrameCount), 
              false, //resizable
              true, //closable
              false, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...
        ButtonPanelNewRegion panel = new ButtonPanelNewRegion();
        add(panel);
        //...Then set the window size or call pack...
        setSize(280,115);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
}

class ButtonPanelNewRegion extends JPanel
{
    public ButtonPanelNewRegion()
    {                      
        
        add(new JLabel("������:"));
        final JTextField nameField = new JTextField("������� ��������", 15);
        add(nameField);
		
		add(new JLabel("��������:"));
        final JTextField descriptionField = new JTextField("������� ��������:", 17);
        add(descriptionField);
        
        final JButton EditButton = new JButton("�������� ������.");
        add(EditButton);
        
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
                     conn = DriverManager.getConnection("jdbc:mysql://192.168.2.224:3306/lan_db?user=landb_user&password=landb_user&useUnicode=true&characterEncoding=KOI8_R");

                     PreparedStatement stmt = conn.prepareStatement(
                             "INSERT INTO Regions_Table SET " + "Region_name=?," + "Region_Description=?"
							 );
                     stmt.setString(1, nameField.getText());
                     stmt.setString(2, descriptionField.getText());

                     stmt.execute();
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