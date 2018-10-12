import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame{

	private JPanel panel;
	private GridLayout grid;
	private BorderLayout border;
	
	public View(String name) {
		
		this.setVisible(true);
		this.setSize(800, 600);
		this.setTitle(name);
		this.panel = new JPanel();
		this.add(panel);
		setupLoginFrame();
		
	}
	
	public GridLayout setGrid(int a, int b, JPanel panel) {
		
		grid = new GridLayout(a, b);
		panel.setLayout(grid);
		return grid;
	
	}
	
	public BorderLayout setBorder(JPanel panel) {
		
		border = new BorderLayout();
		panel.setLayout(grid);
		return border;
	
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
		
		setBorder(panel);
		JPanel central = newPanel();
		JPanel top = newPanel();
		JPanel bottom = newPanel();
		
		addLabel("Welcome to the System Appointments",top);
		addLabel("Login", central);
		addTextField(10, central);
		addLabel("Password", central);
		addTextField(10, central);
		addButton("LogIn", central);
		
		//BorderLayout boder = new BorderLayout();
		//this.setLayout(boder);
		
		//GridLayout grid = new GridLayout();
		//this.setLayout(grid);
		
		//JPanel central = new JPanel();
		//JPanel top = new JPanel();
		//JPanel bottom = new JPanel();
		
		
		//////////////////////////////////TOP///////////////////////////////////////
		
		//JLabel welcome = new JLabel("Welcome to the System Appointments");
		//top.add(welcome);
		
		////////////////////////////CENTER//////////////////////////////////////////
		
		//GridLayout centerGrid = new GridLayout(2,2);
		//central.setLayout(centerGrid);
		
		//JLabel enterLogin = new JLabel("Login");
		//central.add(enterLogin);
				
		//JTextField user = new JTextField(20);
		//central.add(user);
		
		//JLabel enterPassword = new JLabel("Password");
		//central.add(enterPassword);
		
		//JTextField password = new JTextField(20);
		//central.add(password);
		
		//JButton enter = new JButton("Log in");
		//central.add(enter);
		
		////////////////////////////////BOTTOM//////////////////////////////////////////
		
		JLabel register = new JLabel("New Customer?");
		bottom.add(register);
		
		JButton regButton = new JButton("Register here");
		bottom.add(regButton);
		
		///////////////////////////////////////////////////////////////////////////////
		
		
		this.add(top, BorderLayout.PAGE_START);
		this.add(central, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.PAGE_END);
		
		
		this.validate();
		this.repaint();
	}
	
	
	
}
