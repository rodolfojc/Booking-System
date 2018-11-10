import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class View extends JFrame{

	JPanel panel;
	GridLayout grid;
	BorderLayout border;
	BoxLayout box;
	JMenuBar menu;
			
	public View(String name, int width, int height, boolean Resizable) {
		
		this.setVisible(true);
		this.setSize(width, height);
		this.setTitle(name);
		this.panel = new JPanel();
		this.add(panel);
		this.menu = new JMenuBar();
		this.setJMenuBar(menu);
		this.setResizable(Resizable);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.setDefaultLookAndFeelDecorated(true);
				
	}
	
	public GridLayout setGrid(int a, int b, JPanel panel) {
		
		grid = new GridLayout(a, b);
		panel.setLayout(grid);
		return grid;
	
	}
	
	public BorderLayout setBorder(JPanel panel) {
		
		border = new BorderLayout();
		panel.setLayout(border);
		return border;
	
	}
	
	public BoxLayout setBox(JPanel panel, int a) {
		
		box = new BoxLayout(panel, a);
		panel.setLayout(box);
		return box;
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
	
	public JButton addButton(String name, JPanel panel) {
		
		JButton myButton= new JButton(name);
		panel.add(myButton);
		return myButton;
	}
	
	public JLabel addLabel(String name, JPanel panel) {
		
		JLabel myLabel = new JLabel(name);
		panel.add(myLabel);
		return myLabel;
	}
	
		
}
