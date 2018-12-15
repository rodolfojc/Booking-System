import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

public class View extends JFrame{
	
	//GLOBAL VARIABLES - DECLARATION
	JPanel panel;
	JMenuBar menu;
	JTable[] myTable;
	
	//CONSTRUCTOR
	
	public View(String name, int width, int height, boolean Resizable) {
		
		//SETTING UP THE FRAME
		this.setVisible(true); //VISIBLE
		this.setSize(width, height); //SIZE
		this.setTitle(name); //TITLE
		this.panel = new JPanel(); //PANEL INSTANTIATION
		this.myTable = new JTable[7];
		this.add(panel); //ADDING GLOBAL MAIN PANEL TO THE FRAME
		this.menu = new JMenuBar(); //MENU INSTANTIATION
		this.setJMenuBar(menu); //ADDING MENU
		this.setResizable(Resizable); //RESIZABLE - WINDOW
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //SETTING CLOSE PROGRAM WHEN THE FRAME IS CLOSED
		this.setLocationRelativeTo(null); // SETTING FRAME IN CENTER LOCATION ON THE SCREEN
								
	}
	
	//THESE METHODS HAVE BEEN CREATED TO ADD COMPONENTS TO PANELS DIRECTLY
	//TO SET LAYOUTS TO PANELS, AND TO RETURN OBJECTS THAT ARE CREATED
	
	//SET GRIDLAYOUT TO A PANEL
	public void setGrid(int a, int b, JPanel panel) {
		
		GridLayout grid = new GridLayout(a, b);
		panel.setLayout(grid);
	}
	
	//SET BORDERLAYOUT TO A PANEL
	public void setBorder(JPanel panel) {
		
		BorderLayout border = new BorderLayout();
		panel.setLayout(border);
	}
	
	//SET BOXLAYOUT TO A PANEL
	public void setBox(JPanel panel, int a) {
		
		BoxLayout box = new BoxLayout(panel, a);
		panel.setLayout(box);
		
	}
	
	//CREATE JMENU, ADD TO MAIN MENU
	public JMenu addMenu(String name) {
		
		JMenu myMenu = new JMenu(name);
		this.menu.add(myMenu);
		return myMenu;
		
	}
	
	//CREATE JMENUITEM, ADD TO JMENU
	public JMenuItem addMenuItem(JMenu myMenu, String name) {
		
		JMenuItem myMenuItem = new JMenuItem(name);
		myMenu.add(myMenuItem);
		return myMenuItem;
	}
	
	//CREATE A JTEXTFIELD WITH SIZE AND ADD TO A PANEL
	public JTextField addTextField(int a, JPanel panel) {
		
		JTextField myText = new JTextField(a);
		panel.add(myText);
		return myText;
		
	}
	
	//CREATE A JPASSWORDFIELD WITH SIZE AND ADD TO A PANEL
	public JPasswordField addPassField(int a, JPanel panel) {
		
		JPasswordField myPass = new JPasswordField(a);
		panel.add(myPass);
		return myPass;
	}
	
	//CREATE A JBUTTON WITH NAME, SET COLOR, AND ADD TO A PANEL
	public JButton addButton(String name, JPanel panel) {
		
		JButton myButton= new JButton(name);
		myButton.setBackground(Color.getHSBColor(229, 162, 61));
		myButton.setContentAreaFilled(false);
		myButton.setOpaque(true);
		panel.add(myButton);
		return myButton;
	}
	
	//CREATE A JLABEL WITH TEXT AND ADD TO A PANEL
	public JLabel addLabel(String text, JPanel panel) {
		
		JLabel myLabel = new JLabel(text);
		panel.add(myLabel);
		return myLabel;
	}
	
	//CREATE A JCOMBOBOX WITH DATA OPTIONS AND ADD TO A PANEL
	public JComboBox addComboB(String[] options, JPanel panel) {
		
		JComboBox myComboBox = new JComboBox(options);
		panel.add(myComboBox);
		return myComboBox;
	}
	
	//CREATE A JCALENDAR - JDATECHOOSER WITH SIZE AND ADD TO A PANEL
	public JDateChooser addCalen(JPanel panel) {
		JDateChooser myDateChooser = new JDateChooser();
		myDateChooser.setBounds(20, 20, 200, 20);
		panel.add(myDateChooser);
		return myDateChooser;
	}
	
	//CREATE A JSCROOLPANE WITH INDEX FOR MAIN TABLES, DATA (GETTING FROM DATABASE), COLUMNS NAMES, ADD TO A PANEL, AND TITLE FOR BORDER.
	//ALSO IT CREATES A BORDER WITH A TITLE.
	public JScrollPane addTableS(int tableNum, String[][] data, String[] columnsName, JPanel panel, String title) {
		
		myTable[tableNum] = new JTable(data, columnsName);
		myTable[tableNum].setSize(100, 200);
		JScrollPane myScroll = new JScrollPane(myTable[tableNum]);
		panel.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                title,
                TitledBorder.CENTER,
                TitledBorder.TOP));
		panel.add(myScroll);
		return myScroll;
		
	}
	
		
}
