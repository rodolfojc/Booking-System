import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Controller implements ActionListener {
	
	//View view;
	Register register;
	Login login;
	Model model;
	Database data;
	
	
	public Controller() {
		
		//this.register = new Register(this);
		this.login = new Login(this);
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
		System.out.print("Done");
			}
				
	}

}
