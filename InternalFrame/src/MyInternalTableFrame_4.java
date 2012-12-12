//package components;

import javax.swing.JInternalFrame;

import java.awt.event.*;
import java.awt.*;
import java.lang.reflect.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;

import javax.swing.table.*;

import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Used by InternalFrameDemo.java. */
public class MyInternalTableFrame_4 extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 60, yOffset = 60;

    public MyInternalTableFrame_4() {
        super("Points #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...
        ButtonPanel_4 panel = new ButtonPanel_4();
        add(panel);
        //...Then set the window size or call pack...
        setSize(520, 140);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
}

class ButtonPanel_4 extends JPanel
{
    private boolean DEBUG = false;
    public ButtonPanel_4()
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