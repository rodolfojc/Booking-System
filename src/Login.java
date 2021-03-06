import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	// GLOBAL VARIABLES - DECLARATION
	private View login;
	private Controller controller;
	private JButton enterBtn, regBtn;
	private JTextField emailIn;
	private JPasswordField passwordIn;
	
	public Login(Controller controller) {

		// NEW INSTANCE OF VIEW TO BUILD LOGIN VIEW, CONTROLLER, AND FRAME SETUP
		this.login = new View("Online Appointments System", 400, 600, false);
		this.controller = controller;
		setupLoginFrame();
	}

	// GETTER AND SETTER FOR GLOBAL VARIABLES
	public View getLogin() {
		return this.login;
	}

	public String getEmail() {
		return this.emailIn.getText();
	}

	public JTextField setEmail() {
		return this.emailIn;
	}

	public JPasswordField setJPass() {
		return this.passwordIn;
	}

	public String getPassField() {
		// CASTING JPASSWORDFIELD FROM CHAR TO STRING
		return new String(this.passwordIn.getPassword());
	}

	// METHOD TO SET UP THE FRAME
	public void setupLoginFrame() {

		// CHECK VIEW CLASS TO UNDERTAND METHODS THAT WILL BE USED
		// TO SET UP THE FRAME, LOGIN IS AN INSTANCE OF VIEW CLASS

		// MAIN LAYOUT FOR MAIN PANEL
		this.login.setBox(login.panel, 1);
		
		
		
		// MENU
		JMenu about = login.addMenu("Help");
		this.login.addMenuItem(about, "Admin email: admin@admin.admin");
		
		// PANELS FOR MAIN PANEL
		JPanel centralOne = new JPanel();
		JPanel centralTwo = new JPanel();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		JPanel bottomTwo = new JPanel();

		// WELCOME LOGIN AND PASSWORD
		this.login.addLabel("Appointments System", top);
		this.login.addLabel("Email", centralOne);
		this.emailIn = login.addTextField(10, centralOne);
		this.login.addLabel("Password", centralTwo);
		this.passwordIn = login.addPassField(10, centralTwo);

		// BUTTONS LOGIN AND REGISTER
		this.enterBtn = login.addButton("Login", bottom);
		this.enterBtn.setActionCommand("Enter");
		this.enterBtn.addActionListener(controller);
		//SET ENTER BUTTON AS DEFAULT IF THE KEY "ENTER" IS PRESSED
		this.login.getRootPane().setDefaultButton(enterBtn);
		
		this.login.addLabel("Not yet a member??", bottomTwo);
		this.regBtn = login.addButton("Register here!", bottomTwo);
		this.regBtn.setActionCommand("Open Register");
		this.regBtn.addActionListener(controller);

		// BORDER TO FIT COMPONENTS
		this.login.panel.setBorder(new EmptyBorder(new Insets(130, 120, 140, 120)));

		// ADDING TO THE MAIN PANEL
		top.setBorder(BorderFactory.createTitledBorder("Welcome"));
		this.login.panel.add(top);
		this.login.panel.add(centralOne);
		this.login.panel.add(centralTwo);
		this.login.panel.add(bottom);
		this.login.panel.add(bottomTwo);

		// CALLING VALIDATE AND REPAINT METHODS
		this.login.validate();
		this.login.repaint();

	}

}
