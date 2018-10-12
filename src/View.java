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
		this.setSize(400, 600);
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
		panel.setLayout(border);
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
		
		setGrid(4,1,panel);
		JPanel centralOne = newPanel();
		JPanel centralTwo = newPanel();
		JPanel top = newPanel();
		JPanel bottom = newPanel();
		
		addLabel("Welcome to the System Appointments",top);
		addLabel("Login", centralOne);
		addTextField(10, centralOne);
		addLabel("Password", centralTwo);
		addTextField(10, centralTwo);
		addButton("LogIn", bottom);
		addLabel("New Customer?", bottom);
		addButton("Register here", bottom);
		
		panel.add(top);
		panel.add(centralOne);
		panel.add(centralTwo);
		panel.add(bottom);
		
				
		this.validate();
		this.repaint();
	}
	
	
	
}
