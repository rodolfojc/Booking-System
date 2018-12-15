import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame {

	// GLOBAL VARIABLES - DECLARATION
	private JTextField txtName, txtSur, txtPass, txtVPass, txtMob, txtEmail, txtAddress, txtLocation;
	private JPasswordField pass;
	private View reg;
	private Controller controller;
	private String type;
	private JButton registerBtn, cancelBtn;

	// CONTROLLER
	public Register(Controller controller, String Type) {

		// NEW INSTANCE OF VIEW TO BUILD REGISTRATION VIEW, CONTROLLER
		// TYPE OF USER IS A PARAMETER TO SET DIFERENT REGISTRATION FOR CUSTOMERS AND
		// PROVIDERS
		this.reg = new View("Registration", 400, 800, false);
		this.type = Type;
		this.controller = controller;
		setupRegisterFrame();
	}

	// GETTER AND SETTER FOR GLOBAL VARIABLES
	public View getReg() {
		return this.reg;
	}

	public String getName() {
		return this.txtName.getText();
	}

	public String getSur() {
		return this.txtSur.getText();
	}

	public String getPassField() {
		return new String(this.pass.getPassword());
	}

	public String getMob() {
		return this.txtMob.getText();
	}

	public String getEmail() {
		return this.txtEmail.getText();
	}

	public String getAddress() {
		return this.txtAddress.getText();
	}

	public String getLoc() {
		return this.txtLocation.getText();
	}

	public String getUserType() {
		return this.type;
	}

	// METHOD TO CAST DATE TYPE TO DATE MSQL TYPE FORMAT YYYY-MM-DD
	public java.sql.Date getRegDatE() throws ParseException {
		Date now = new Date();
		SimpleDateFormat myDateSimp = new SimpleDateFormat("yyy-MM-dd"); // "yyyy-MM-dd"
		String dateStr = myDateSimp.format(now);
		java.util.Date getCalendar;
		java.sql.Date dateSql;
		getCalendar = myDateSimp.parse(dateStr);
		dateSql = new java.sql.Date(getCalendar.getTime());
		return dateSql;

	}
	
	// METHOD TO SET UP THE FRAME
	public void setupRegisterFrame() {

		// CHECK VIEW CLASS TO UNDERTAND METHODS THAT WILL BE USED
		// TO SET UP THE FRAME, REGISTER IS AN INSTANCE OF VIEW CLASS

		// MAIN LAYOUT FOR MAIN PANEL
		this.reg.setBox(reg.panel, 1);

		// PANELS FOR MAIN PANEL
		JPanel top = new JPanel();
		JPanel name = new JPanel();
		JPanel surname = new JPanel();
		JPanel password = new JPanel();
		JPanel mobile = new JPanel();
		JPanel email = new JPanel();
		JPanel address = new JPanel();
		JPanel location = new JPanel();
		JPanel regB = new JPanel();

		// TOP PANEL - WELCOME
		top.setBorder(BorderFactory.createTitledBorder("Sing up for FREE"));
		this.reg.addLabel(this.type.toUpperCase() + " REGISTRATION", top);

		// NAME PANEL
		this.reg.addLabel("Name", name);
		this.txtName = reg.addTextField(20, name);

		// SURNAME PANEL
		this.reg.addLabel("Surname", surname);
		this.txtSur = reg.addTextField(20, surname);

		// PASSWORD PANEL
		this.reg.addLabel("Set a Password", password);
		this.pass = reg.addPassField(20, password);

		// MOBILE PANEL
		this.reg.addLabel("Mobile", mobile);
		this.txtMob = reg.addTextField(20, mobile);

		// EMAIL PANEL
		this.reg.addLabel("Email", email);
		this.txtEmail = reg.addTextField(20, email);

		// ADDRESS PANEL
		this.reg.addLabel("Address", address);
		this.txtAddress = reg.addTextField(20, address);

		// LOCATION PANEL
		// THIS PANEL WILL BE SHOW IF THE REGISTRATION IS FOR PROVIDERS
		if (type.equals("Provider")) {
			this.reg.addLabel("Location", location);
			this.txtLocation = reg.addTextField(20, location);
		}

		// BUTTON FOR REGISTRATION ACTION
		this.registerBtn = this.reg.addButton("Register", regB);
		this.registerBtn.setActionCommand("Register");
		this.registerBtn.addActionListener(controller);

		// BUTTON FOR CANCEL REGISTRATION ACTION
		this.cancelBtn = this.reg.addButton("Cancel", regB);
		this.cancelBtn.setActionCommand("Cancel");
		this.cancelBtn.addActionListener(controller);

		// ADDING BORDER AND PANELS TO THE MAIN PANEL
		this.reg.panel.setBorder(new EmptyBorder(new Insets(40, 65, 20, 65)));
		this.reg.panel.add(top);
		this.reg.panel.add(name);
		this.reg.panel.add(surname);
		this.reg.panel.add(password);
		this.reg.panel.add(mobile);
		this.reg.panel.add(email);
		this.reg.panel.add(address);

		// JUST LACATION PANEL WILL BE ADDED FOR PROVIDER
		if (type.equals("Provider")) {
			this.reg.panel.add(location);
		}

		this.reg.panel.add(regB);

		// CALLING VALIDATE AND REPAINT METHODS
		this.reg.validate();
		this.reg.repaint();

	}
}
