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
	
	
	public Controller() {
		
		//this.proView = new ProviderView(this, "ce@.com");
		this.custView = new CustomerView(this, "ro@.com");
		//this.register = new Register(this);
		//this.login = new Login(this);
		//this.view = new View ("Online Barber's Appointments", this, 400, 600, false);
		//this.model = new Model();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Open_Register")) {
			//login.close();
			this.register = new Register(this);
		}
		
		if(e.getActionCommand().equals("User_Register")) {
		
			System.out.print("Let's do it");
			data = new Database(register);
			data.registerUser(this.register.getUserType());
			System.out.print("Done");
		}
		
		if(e.getActionCommand().equals("Enter")) {
		
			String email = login.getEmail();
			String password = login.getPass();
		
			data = new Database(login);
			boolean resultOne = data.loginUser("customers", email, password);
			
			Database connProv = new Database(login);
			boolean resultTwo = connProv.loginUser("providers", email, password);
		
				if(resultOne==true || resultTwo==true) {
					JOptionPane.showMessageDialog(this.login,"Welcome!!");
					if (resultOne) {
						this.custView = new CustomerView(this, email);
					}else {
						this.proView = new ProviderView(this, email);
					}
				}else {
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
			this.custView.UpdateFrame();
		}
			
			
			
		if (e.getActionCommand().equals("Get Appoint")) {
			
			Database data = new Database(this.custView, this.proView);
			data.bookAppointment();
		}
		
		
		
	}

}
