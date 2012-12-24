package  InternalFrame.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

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
        
        add(new JLabel("Регион:"));
        final JTextField nameField = new JTextField("Введите название", 15);
        add(nameField);
		
		add(new JLabel("Описание:"));
        final JTextField descriptionField = new JTextField("Введите описание:", 17);
        add(descriptionField);
        
        final JButton EditButton = new JButton("Добавить регион.");
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
                   catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex)
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