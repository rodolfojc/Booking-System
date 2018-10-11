import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame{

	public View() {
		
		this.setVisible(true);
		this.setSize(800, 600);
		this.setTitle("Online Barber's Shop Appointments");
		setupLoginFrame();
		
	}
	
	public void setupLoginFrame() {
		
		BorderLayout boder = new BorderLayout();
		this.setLayout(boder);
		
		//GridLayout grid = new GridLayout();
		//this.setLayout(grid);
		
		JPanel central = new JPanel();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		
		
		//////////////////////////////////TOP///////////////////////////////////////
		
		JLabel welcome = new JLabel("Welcome to the System Appointments");
		top.add(welcome);
		
		////////////////////////////CENTER//////////////////////////////////////////
		
		JLabel enterLogin = new JLabel("Login");
		central.add(enterLogin);
				
		JTextField user = new JTextField(20);
		central.add(user);
		
		JLabel enterPassword = new JLabel("Password");
		central.add(enterPassword);
		
		JTextField password = new JTextField(20);
		central.add(password);
		
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
