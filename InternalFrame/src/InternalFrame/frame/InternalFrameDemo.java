package  InternalFrame.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

/*
 * InternalFrameDemo.java requires:
 *   MyInternalFrame.java
 */
public class InternalFrameDemo extends JFrame
                               implements ActionListener {
    JDesktopPane desktop;

    public InternalFrameDemo() {
        super("InternalFrameDemo");

        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 200;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);

        //Set up the GUI.
        desktop = new JDesktopPane(); //a specialized layered pane
        //createFrame(); //create first "window"
        setContentPane(desktop);
        setJMenuBar(createMenuBar());
		ButtonPanel panelMainButtons = new ButtonPanel();
        //add(desktop);

        //Make dragging a little faster but perhaps uglier.
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //Set up the lone menu.
        JMenu menu0 = new JMenu("File");
        menu0.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menu0);
        
        JMenu menu1 = new JMenu("Auth");
        menu1.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu1);
		
	//Set up the lone menu.
        JMenu menu2 = new JMenu("Points");
        //menu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(menu2);

        //Set up the first menu item.
        JMenuItem menuItem11 = new JMenuItem("Новая карточка");
        menuItem11.setMnemonic(KeyEvent.VK_S);
        menuItem11.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem11.setActionCommand("new");
        menuItem11.addActionListener(this);
        menu1.add(menuItem11);
		
		JMenuItem menuItem21 = new JMenuItem("Новый регион");
        menuItem21.setMnemonic(KeyEvent.VK_R);
        menuItem21.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.ALT_MASK));
        menuItem21.setActionCommand("new_region");
        menuItem21.addActionListener(this);
        menu2.add(menuItem21);
		
		JMenuItem menuItem24 = new JMenuItem("Новый город");
        menuItem24.setMnemonic(KeyEvent.VK_C);
        menuItem24.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.ALT_MASK));
        menuItem24.setActionCommand("new_city");
        menuItem24.addActionListener(this);
        menu2.add(menuItem24);
		
		JMenuItem menuItem22 = new JMenuItem("Новый пункт");
        menuItem22.setMnemonic(KeyEvent.VK_P);
        menuItem22.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, ActionEvent.ALT_MASK));
        menuItem22.setActionCommand("new_point");
        menuItem22.addActionListener(this);
        menu2.add(menuItem22);
		
		JMenuItem menuItem23 = new JMenuItem("Таблица пунктов");
        menuItem23.setMnemonic(KeyEvent.VK_O);
        menuItem23.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.ALT_MASK));
        menuItem23.setActionCommand("table_point");
        menuItem23.addActionListener(this);
        menu2.add(menuItem23);
        
        JMenuItem menuItem12 = new JMenuItem("Таблица персонала");
        menuItem12.setMnemonic(KeyEvent.VK_N);
        menuItem12.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem12.setActionCommand("table");
        menuItem12.addActionListener(this);
        menu1.add(menuItem12);
                
        JMenuItem menuItem13 = new JMenuItem("Таблица");
        menuItem13.setMnemonic(KeyEvent.VK_T);
        menuItem13.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.ALT_MASK));
        menuItem13.setActionCommand("new_table");
        menuItem13.addActionListener(this);
        menu1.add(menuItem13);
        
        JMenuItem menuItem01 = new JMenuItem("Quit");
        menuItem01.setMnemonic(KeyEvent.VK_Q);
        menuItem01.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem01.setActionCommand("quit");
        menuItem01.addActionListener(this);
        menu0.add(menuItem01);

        return menuBar;
    }

	//protected JButtonPanel panelMainButtons(){}
	
    public void actionPerformed(ActionEvent e)
    {
        int choise = 0;
        if ("new".equals(e.getActionCommand())) choise = 1;
        if ("table".equals(e.getActionCommand())) choise = 2;
        if ("new_table".equals(e.getActionCommand())) choise = 3;
		if ("new_region".equals(e.getActionCommand())) choise = 5;
		if ("new_point".equals(e.getActionCommand())) choise = 6;
		if ("table_point".equals(e.getActionCommand())) choise = 7;
		if ("new_city".equals(e.getActionCommand())) choise = 8;
		
        if ("quit".equals(e.getActionCommand())) choise = 99;
        switch (choise)
        {
            case 1:
                createFrame();
                break;
            case 2:
                createFrame_1();
                break;
            case 3:
                createFrame_2();
                break;
			case 5:
                createFrame_5();
                break;
			case 6:
             //   createFrame_2();
                break;
			case 7:
                createFrame_4();
                break;
			case 8:
                createFrame_8();
                break;
            case 99:
                quit();
                break;
            default:
                break;
        }
    }

    //Create a new internal frame.
    protected void createFrame() {
        MyInternalFrame frame = new MyInternalFrame();
        frame.setVisible(true); //necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

     protected void createFrame_1() {
        MyInternalTableFrame_1 frame2 = new MyInternalTableFrame_1();
        frame2.setVisible(true); //necessary as of 1.3
        desktop.add(frame2);
        try {
            frame2.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
     
     protected void createFrame_2() {
        MyInternalTableFrame_2 frame3 = new MyInternalTableFrame_2();        
        frame3.setVisible(true); //necessary as of 1.3
        desktop.add(frame3);
        try {
            frame3.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
	
	protected void createFrame_4() {
        MyInternalTableFrame_4 frame4 = new MyInternalTableFrame_4();        
        frame4.setVisible(true); //necessary as of 1.3
        desktop.add(frame4);
        try {
            frame4.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
	
	protected void createFrame_5() {
        InternalFrameNewRegion frame5 = new InternalFrameNewRegion();        
        frame5.setVisible(true); //necessary as of 1.3
        desktop.add(frame5);
        try {
            frame5.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
	
	protected void createFrame_8() {
        InternalFrameNewCity frame8 = new InternalFrameNewCity();        
        frame8.setVisible(true); //necessary as of 1.3
        desktop.add(frame8);
        try {
            frame8.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
    
    //Quit the application.
    protected void quit() {
        System.exit(0);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        InternalFrameDemo frame = new InternalFrameDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
