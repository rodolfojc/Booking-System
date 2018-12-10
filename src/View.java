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

	JPanel panel;
	JMenuBar menu;
	JTable[] myTable;
			
	public View(String name, int width, int height, boolean Resizable) {
		
		this.setVisible(true);
		this.setSize(width, height);
		this.setTitle(name);
		this.panel = new JPanel();
		this.myTable = new JTable[7];
		this.add(panel);
		this.menu = new JMenuBar();
		this.setJMenuBar(menu);
		this.setResizable(Resizable);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.getHSBColor(169, 206, 6));
						
	}
	
	public void setGrid(int a, int b, JPanel panel) {
		
		GridLayout grid = new GridLayout(a, b);
		panel.setLayout(grid);
	}
	
	public void setBorder(JPanel panel) {
		
		BorderLayout border = new BorderLayout();
		panel.setLayout(border);
	}
	
	public void setBox(JPanel panel, int a) {
		
		BoxLayout box = new BoxLayout(panel, a);
		panel.setLayout(box);
		
	}
	
	public JMenu addMenu(String name) {
		
		JMenu myMenu = new JMenu(name);
		this.menu.add(myMenu);
		return myMenu;
		
	}
	
	public JMenuItem addMenuItem(JMenu myMenu, String name) {
		
		JMenuItem myMenuItem = new JMenuItem(name);
		myMenu.add(myMenuItem);
		return myMenuItem;
	}
	
	
	public JTextField addTextField(int a, JPanel panel) {
		
		JTextField myText = new JTextField(a);
		panel.add(myText);
		return myText;
		
	}
	
	public JPasswordField addPassField(int a, JPanel panel) {
		
		JPasswordField myPass = new JPasswordField(a);
		panel.add(myPass);
		return myPass;
	}
	
	public JButton addButton(String name, JPanel panel) {
		
		JButton myButton= new JButton(name);
		myButton.setBackground(Color.getHSBColor(229, 162, 61));
		myButton.setContentAreaFilled(false);
		myButton.setOpaque(true);
		panel.add(myButton);
		return myButton;
	}
	
	public JLabel addLabel(String name, JPanel panel) {
		
		JLabel myLabel = new JLabel(name);
		panel.add(myLabel);
		return myLabel;
	}
	
	public JComboBox addComboB(String[] options, JPanel panel) {
		
		JComboBox myComboBox = new JComboBox(options);
		panel.add(myComboBox);
		return myComboBox;
	}
	
	public JDateChooser addCalen(JPanel panel) {
		JDateChooser myDateChooser = new JDateChooser();
		myDateChooser.setBounds(20, 20, 200, 20);
		panel.add(myDateChooser);
		return myDateChooser;
	}
	
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
