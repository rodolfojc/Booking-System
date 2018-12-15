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

	private JTextField txtName, txtSur, txtPass, txtVPass, txtMob, txtEmail, txtAddress, txtLocation;
	private JPasswordField pass;
	private View reg;
	private Controller controller;
	private String type;
	private JButton registerBtn, cancelBtn;
	//private String[] _OPT = {"Customer", "Provider"};
	
	public Register(Controller controller, String Type) {
		
//		this.type=(String)JOptionPane.showInputDialog(this,
//				"Are you?", 
//				"Registration",
//				JOptionPane.PLAIN_MESSAGE,
//				null,
//				_OPT,
//				_OPT[0]);
		
		this.reg = new View("Registration", 400, 800, false);
		this.type = Type;
		this.controller = controller;
		setupRegisterFrame();
		}
	
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
	
	public java.sql.Date getRegDatE() throws ParseException {
		Date now = new Date();
		SimpleDateFormat myDateSimp = new SimpleDateFormat("yyy-MM-dd"); //"yyyy-MM-dd"
		String dateStr = myDateSimp.format(now);
		java.util.Date getCalendar;
		java.sql.Date dateSql;
		getCalendar = myDateSimp.parse(dateStr);
		dateSql = new java.sql.Date(getCalendar.getTime());
		return dateSql;		
		
	}
	
	public void setupRegisterFrame() {
		
		reg.setBox(reg.panel,1);
		
		//String [] userType = {"Customer", "Provider"};
		//JComboBox typeUs = new JComboBox(userType);
		
		//JPanel typeCombo = new JPanel();
		//typeCombo.add(typeUs);
		
		JPanel top = new JPanel();
		JPanel name = new JPanel();
		JPanel surname = new JPanel();
		JPanel password = new JPanel();
		//JPanel verPassword = new JPanel();
		JPanel mobile = new JPanel();
		JPanel email = new JPanel();
		JPanel address= new JPanel();
		JPanel location = new JPanel();
		JPanel regB = new JPanel();
		
		
		//JRadioButton customer = new JRadioButton("Costumer");
		//JRadioButton provider = new JRadioButton("Provider");
		//type.add(customer);
		//type.add(provider);
		
		top.setBorder(BorderFactory.createTitledBorder("Sing up for FREE"));		
		reg.addLabel(this.type.toUpperCase()+" REGISTRATION", top);
		reg.addLabel("Name", name);
		txtName = reg.addTextField(20, name);
		reg.addLabel("Surname", surname);
		txtSur = reg.addTextField(20, surname);
		reg.addLabel("Set a Password", password);
		pass = reg.addPassField(20, password);
		//txtPass = reg.addTextField(20, password);
		//reg.addLabel("Re-entry Password", verPassword);
		//txtVPass = reg.addTextField(20, verPassword);
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
		
		this.registerBtn = this.reg.addButton("Register", regB);
		//JButton register = new JButton("Register");
		registerBtn.setActionCommand("Register");
		registerBtn.addActionListener(controller);
		
		this.cancelBtn = this.reg.addButton("Cancel", regB);
		//JButton cancel = new JButton("Cancel");
		cancelBtn.setActionCommand("Cancel");
		cancelBtn.addActionListener(controller);
		
		//regB.add(register);
		//regB.add(cancel);
		
		reg.panel.setBorder(new EmptyBorder(new Insets(40,65,20,65)));
		//reg.panel.add(typeCombo);
		reg.panel.add(top);
		reg.panel.add(name);
		reg.panel.add(surname);
		reg.panel.add(password);
		//reg.panel.add(verPassword);
		reg.panel.add(mobile);
		reg.panel.add(email);
		reg.panel.add(address);
		
		if (type.equals("Provider")) {
		reg.panel.add(location);}
		
		reg.panel.add(regB);
		
		reg.validate();
		reg.repaint();
		
	}
}
