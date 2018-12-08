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
	AdminView adminView;
	AdminController adminController;
	
	public Controller() {
		
		//this.proView = new ProviderView(this, "vi@vi.vom");
		//this.custView = new CustomerView(this, "ro@ro.com");
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
		
			//this.login.getLogin().setVisible(false);
			this.login.dispose();
			data = new Database(this.register);
			data.registerUser(this.register.getUserType());
			//this.login.getLogin().setVisible(true);
			
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
