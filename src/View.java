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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class View extends JFrame{

	private JPanel panel;
	private GridLayout grid;
	private BorderLayout border;
	private BoxLayout box;
	
	public View(String name) {
		
		this.setVisible(true);
		this.setSize(400, 600);
		this.setTitle(name);
		this.panel = new JPanel();
		this.add(panel);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setDefaultLookAndFeelDecorated(true);
		setupLoginFrame();
		
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
	
	public JPanel newPanel() {
		
		return new JPanel();
		
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
	
	public void setupLoginFrame() {
		
		//setGrid(4,1,panel);
		setBox(panel,1);
		JPanel centralOne = newPanel();
		JPanel centralTwo = newPanel();
		JPanel top = newPanel();
		JPanel bottom = newPanel();
		JPanel bottomTwo = newPanel();
		
		addLabel("Get Appointments",top);
		addLabel("Login", centralOne);
		addTextField(10, centralOne);
		addLabel("Password", centralTwo);
		addTextField(10, centralTwo);
		addButton("LogIn", bottom);
		addLabel("New Customer?", bottomTwo);
		addButton("Register here", bottomTwo);
		
		panel.setBorder(new EmptyBorder(new Insets(150,125,150,125)));
		
		top.setBorder(BorderFactory.createTitledBorder("Welcome"));
		panel.add(top);
		
		//centralOne.setPreferredSize(new Dimension(5,10));
		//centralOne.setBorder(BorderFactory.createBevelBorder(0));
		panel.add(centralOne);
		
		//centralTwo.setPreferredSize(new Dimension(5,10));
		//centralTwo.setBorder(BorderFactory.);
		panel.add(centralTwo);
		
		panel.add(bottom);
		panel.add(bottomTwo);
		
				
		this.validate();
		this.repaint();
	}
	
	
	
}
