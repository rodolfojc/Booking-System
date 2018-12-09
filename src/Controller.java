import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller implements ActionListener {
	
	//View view;
	Register register;
	Login login;
	Model model;
	Database data;
	CustomerView custView;
	ProviderView proView;
	AdminController adminController;
	
	public Controller() {
		
		//this.proView = new ProviderView(this, "jean@jean.com");
		//this.custView = new CustomerView(this, "rodolfo@rodolfo.com");
		//this.register = new Register(this);
		this.login = new Login(this);
		//this.view = new View ("Online Barber's Appointments", this, 400, 600, false);
		//this.model = new Model();
		//this.adminView = new AdminView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Open_Register")) {
			
			this.register = new Register(this);
			
			
		}
		
		if(e.getActionCommand().equals("User_Register")) {
		
			boolean flag = true;
			
			if (!this.register.getName().matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$")) {
				JOptionPane.showMessageDialog(register, "The name is not correct or it is empty, "
						+ "try again", "Name Error", JOptionPane.ERROR_MESSAGE);
				flag=false;
			}if (!this.register.getSur().matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$")) {
				JOptionPane.showMessageDialog(register, "The surname is not correct or it is empty, "
						+ "try again", "Surname Error", JOptionPane.ERROR_MESSAGE);
				flag=false;
			}if (!this.register.getPassField().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,12}$")) {
					JOptionPane.showMessageDialog(register, "The password you have submited is not valid, "
							+ "it must be minimum of 8 characteres up to 12, including at least one uppercase alpha character, "
							+ "one special character '@#$%^&+=', and one number. ", "Password Invalid", JOptionPane.ERROR_MESSAGE);
					flag=false;
			}if (!this.register.getMob().matches("\\d{8,10}")) {
				JOptionPane.showMessageDialog(register, "The mobile is not correct, it must be at least 10 digits, "
						+ "try again", "Mobile Error", JOptionPane.ERROR_MESSAGE);
				flag=false;
			}if (!this.register.getEmail().matches("^(.+)@(.+)$")) {
				JOptionPane.showMessageDialog(register, "The email is not correct or it is empty, "
						+ "try again", "Email Error", JOptionPane.ERROR_MESSAGE);
			}if (this.register.getAddress().length()<=7) {
				JOptionPane.showMessageDialog(register, "The address is not correct or it is empty, "
						+ "try again", "Address Error", JOptionPane.ERROR_MESSAGE);
				flag=false;
			
			}if (flag==true) {
		
				data = new Database(this.register);
				data.registerUser(this.register.getUserType());
			}
			
		}
		
		if(e.getActionCommand().equals("Enter")) {
		
			String email = login.getEmail();
			String password = login.getPassField();
					
			Database connCust = new Database(login);
			boolean resultOne = connCust.loginUser("customers", email, password, false);
						
			Database connProv = new Database(login);
			boolean resultTwo = connProv.loginUser("providers", email, password, false);
			
			Database connAdmin = new Database(login);
			boolean resultThree = connAdmin.loginUser("administrators", email, password, true);
			
			Database proSta = new Database(login);
			String status = proSta.getProStatus(email);
		
				if(resultOne==true || resultTwo==true || resultThree==true) {
					if (resultOne) {
						JOptionPane.showMessageDialog(this.login,"Welcome Customer!!");
						this.custView = new CustomerView(this, email);
					}else if (resultThree==true) {
						JOptionPane.showMessageDialog(this.login,"Welcome Admin!!");
						this.adminController = new AdminController(email);
					}else if (resultTwo==true && status.equals("Confirmed")){
						JOptionPane.showMessageDialog(this.login,"Welcome Provider!!");
						this.proView = new ProviderView(this, email);
					}else if (resultTwo==true && status.equals("Pending")){
						JOptionPane.showMessageDialog(this.login,"Your account is not CONFIRMED, please wait or contact "
								+ "an administrator to validate your information account, thanks!");
					}}
					else {
						JOptionPane.showMessageDialog(this.login,"Incorrect, please try again!");
				}
			}
		
		
		if(e.getActionCommand().equals("Add")) {
			
			Database data = new Database(this.proView);
			data.addAvailability();
						
		}
		
		if (e.getActionCommand().equals("Search")){
			
			Database data = new Database(this.custView);
			data.searchProvider(custView.getByOption(), custView.getByField());
			this.custView.UpdateFrame(false);
		}
			
			
			
		if (e.getActionCommand().equals("Get Appoint")) {
			
			Database data = new Database(this.custView);
			String avai_ref = data.bookAppointment();
			Database dataTwo = new Database(this.custView);
			dataTwo.setAppointment(avai_ref);
		}
		
		if (e.getActionCommand().equals("Confirm")) {
			Database data = new Database(this.proView);
			String avai_ref = this.proView.getDataBooked(this.proView.getSelectedRowT(),0);
			data.comfirmAppointPro(avai_ref);
		}
		
				
	}

}
