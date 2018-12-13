import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame{
	
	private View login;
	private Controller controller;
	private JButton enter, reg;
	private JTextField email;
	private JPasswordField passwordIn;
		
	public Login(Controller controller) {
		login = new View ("Online Barber's Appointments", 400, 600, true);
		this.controller = controller;
		setupLoginFrame();
	}

	public View getLogin() {
		return this.login;
	}
	
	public String getEmail() {
		return this.email.getText();
	}
	
	public JTextField setEmail() {
		return this.email;
	}
	
	public JPasswordField setJPass() {
		return this.passwordIn;
	}
	
	public String getPassField() {
		return new String(this.passwordIn.getPassword());
	}
	 
	public void setupLoginFrame() {
		
		login.setBox(login.panel,1);
				
		JMenu about = login.addMenu("Help");
		login.addMenuItem(about, "About");
		JMenu close = login.addMenu("Close");
		login.addMenuItem(close, "Are you sure?");
				
		JPanel centralOne = new JPanel();
		JPanel centralTwo = new JPanel();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		JPanel bottomTwo = new JPanel();
				
		login.addLabel("Get Appointments",top);
		login.addLabel("Email", centralOne);
		email = login.addTextField(10, centralOne);
		login.addLabel("Password", centralTwo);
		
		passwordIn = login.addPassField(10, centralTwo);
		
		
		enter = login.addButton("Login", bottom);
		enter.setActionCommand("Enter");
		enter.addActionListener(controller);
		login.addLabel("Not yet a member??", bottomTwo);
		reg = login.addButton("Register here!", bottomTwo);
		reg.setActionCommand("Open Register");
		reg.addActionListener(controller);
				
		login.panel.setBorder(new EmptyBorder(new Insets(130,120,140,120)));
				
		top.setBorder(BorderFactory.createTitledBorder("Welcome"));
		login.panel.add(top);
				
		//centralOne.setPreferredSize(new Dimension(5,10));
		//centralOne.setBorder(BorderFactory.createBevelBorder(0));
		login.panel.add(centralOne);
				
		//centralTwo.setPreferredSize(new Dimension(5,10));
		//centralTwo.setBorder(BorderFactory.);
		login.panel.add(centralTwo);
		login.panel.add(bottom);
		login.panel.add(bottomTwo);
				
						
		login.validate();
		login.repaint();
	}	
	
	
	
	
	
	
}
