package  InternalFrame.frame;

import InternalFrame.logic.ManagementSystem;
import InternalFrame.logic.Region;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;

/* Used by InternalFrameDemo.java. */
public class InternalFrameNewCity extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;


    public InternalFrameNewCity() {
        super("InternalFrameNewCity #" + (++openFrameCount), 
              false, //resizable
              true, //closable
              false, //maximizable
              true);//iconifiable

        //...Create the GUI and put it in the window...
        ButtonPanelNewCity panel = new ButtonPanelNewCity();
        add(panel);
        //...Then set the window size or call pack...
        setSize(280,255);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }
}

class ButtonPanelNewCity extends JPanel
{
	private ManagementSystem ms = null;
    private JComboBox grpList;
	
    public ButtonPanelNewCity() 
    {                      
        final JLabel label = new JLabel("aa");
		try {
                    ms = ManagementSystem.getInstance();
					Vector<Region> gr = new Vector<Region>(ms.getRegions());
					add(new JLabel("Регионы                       :"));
					grpList = new JComboBox(gr);
					grpList.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							grpList = (JComboBox)e.getSource();
							grpList.setSelectedIndex(0);
							String item = "";
							item = (String)grpList.getSelectedItem();
						}
					});
					add(new JScrollPane(grpList));
                } catch (Exception ex) {
                    ex.printStackTrace();
        } 
		
		
        add(new JLabel("Город:"));
        final JTextField nameField = new JTextField("Введите название", 15);
        add(nameField);
		
		add(new JLabel("Описание:"));
        final JTextField descriptionField = new JTextField("Введите описание:", 17);
        add(descriptionField);
        
        final JButton EditButton = new JButton("Добавить город.");
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