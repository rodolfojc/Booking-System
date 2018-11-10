import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame {

	private JTextField txtName, txtSur, txtPass, txtVPass, txtMob, txtEmail, txtAddress, txtLocation;
	private View reg;
	private Controller controller;
	private String type;
	private String[] _OPT = {"Costumer", "Provider"};
	
	public Register(Controller controller) {
		
		this.type=(String)JOptionPane.showInputDialog(this,
				"Are you?", 
				"Registration",
				JOptionPane.PLAIN_MESSAGE,
				null,
				_OPT,
				_OPT[0]);
		
		this.reg = new View("Registration", 400, 800, false);	
		this.controller = controller;
		setupRegisterFrame();
		}
	
	public String getName() {
		return this.txtName.getText();
	}
	
	public String getSur() {
		return this.txtSur.getText();
	}
	
	public String getPass() {
		return this.txtPass.getText();
	}
	
	public int getMob() {
		return Integer.parseInt(this.txtMob.getText());
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
	
	public void setupRegisterFrame() {
		
		reg.setBox(reg.panel,1);
		
		
		String [] userType = {"Customer", "Provider"};
		JComboBox typeUs = new JComboBox(userType);
		
		JPanel typeCombo = new JPanel();
		typeCombo.add(typeUs);
		
		JPanel name = new JPanel();
		JPanel surname = new JPanel();
		JPanel password = new JPanel();
		JPanel verPassword = new JPanel();
		JPanel mobile = new JPanel();
		JPanel email = new JPanel();
		JPanel address= new JPanel();
		JPanel location = new JPanel();
		JPanel regB = new JPanel();
		
		
		//JRadioButton customer = new JRadioButton("Costumer");
		//JRadioButton provider = new JRadioButton("Provider");
		//type.add(customer);
		//type.add(provider);
		
		reg.addLabel("Name", name);
		txtName = reg.addTextField(20, name);
		reg.addLabel("Surname", surname);
		txtSur = reg.addTextField(20, surname);
		reg.addLabel("Password", password);
		txtPass = reg.addTextField(20, password);
		reg.addLabel("Re-entry Password", verPassword);
		txtVPass = reg.addTextField(20, verPassword);
		reg.addLabel("Mobile", mobile);
		txtMob = reg.addTextField(20, mobile);
		reg.addLabel("Email", email);
		txtEmail = reg.addTextField(20, email);
		reg.addLabel("Address", address);
		txtAddress = reg.addTextField(20, address);
		
		if (type.equals("Provider")) {
		reg.addLabel("Location", location);
		txtLocation = reg.addTextField(20, location);
		}
		
		JButton register = new JButton("Register");
		register.setActionCommand("User_Register");
		register.addActionListener(controller);
		
		regB.add(register);
		
		reg.panel.setBorder(new EmptyBorder(new Insets(20,65,20,65)));
		reg.panel.add(typeCombo);
		reg.panel.add(name);
		reg.panel.add(surname);
		reg.panel.add(password);
		reg.panel.add(verPassword);
		reg.panel.add(mobile);
		reg.panel.add(email);
		reg.panel.add(address);
		
		if (type.equals("Provider")) {
		reg.panel.add(location);}
		
		reg.panel.add(regB);
		
		this.validate();
		this.repaint();
		
	}
}
