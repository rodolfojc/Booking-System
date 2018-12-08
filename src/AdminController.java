import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AdminController implements ActionListener {

	private AdminView adminView;
		
	public AdminController (String AdminUser) {
		
		this.adminView = new AdminView(this, AdminUser);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("Delete Customer")) {
			String errorMg = "Ups, there is a problem, please be sure the customer does not have"
	      			+ " any availability or appointment registered.";
			String confMg = "The user has been DELETED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteRow("customers", "cust_ID", this.adminView.getDataCust(this.adminView.getSelectedRowCust(), 0), errorMg, confMg );
			adminView.UpdateFrame();
		}
		
		if(e.getActionCommand().equals("Delete Provider")) {
			String errorMg = "Ups, there is a problem, please be sure the provider does not have"
	      			+ " any availability or appointment registered.";
			String confMg = "The user has been DELETED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteRow("providers", "pro_ID", this.adminView.getDataPro(this.adminView.getSelectedRowPro(), 0), errorMg,confMg);
			adminView.UpdateFrame();
		}
		
		if (e.getActionCommand().equals("Validate Provider")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Provider has been CONFIRMED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.updateRow("providers", "status", "Confirmed", "pro_id", this.adminView.getDataPro(this.adminView.getSelectedRowPro(), 0), errorMg, confMg);;
			adminView.UpdateFrame();
		}
		
		if(e.getActionCommand().equals("Delete Availability")) {
			String errorMg = "Ups, there is a problem, please be sure the availability does not have"
	      			+ " any appointment registered.";
			String confMg = "The availability has been DELETED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteRow("availabilities", "avai_ref", this.adminView.getDataAvai(this.adminView.getSelectedRowAvai(), 0), errorMg, confMg);
			adminView.UpdateFrame();
		}
		
		if(e.getActionCommand().equals("Delete Appointment")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "The appointment has been DELETED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteRow("appointments", "appoint_ref", this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 0), errorMg, confMg);
			
			////////UPDATE CHILD ROW AVAI_REF TO CANCELLED////////
			String errorMgTwo = "Ups, there is an internal problem, please contact an administrator";
			String confMgTwo = "Availability has been set CANCELLED!";
			AdminDBQ dataTwo = new AdminDBQ(this.adminView);
			dataTwo.updateRow("availabilities", "available", "Cancelled", "avai_ref", this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 1), errorMgTwo, confMgTwo);
			adminView.UpdateFrame();
		}
		
		if(e.getActionCommand().equals("Manage")) {
			String oldcomment = "Comment: "+this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 3);
			String comment = (String)JOptionPane.showInputDialog(this.adminView,
					oldcomment,
					"Answer",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					null);
			
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Your Comment have been SET!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.updateRow("appointments", "comments","admin: "+comment+"", "appoint_ref", this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 0), errorMg, confMg);
			adminView.UpdateFrame();
			
		}
		
		if(e.getActionCommand().equals("Add Admin")) {
			String user = (String)JOptionPane.showInputDialog(this.adminView,
					"Email address",
					"Admin Register",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					null);
			System.out.println(user);
			
			String pass = (String)JOptionPane.showInputDialog(this.adminView,
					"Password?",
					"Admin Register",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					null);
			System.out.println(pass);
			
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.createAdmin(user, pass);
			adminView.UpdateFrame();
			
			}
		
		if(e.getActionCommand().equals("Delete Admin")) {
			String errorMg = "Ups, there is an internal problem!! Check the code";
			String confMg = "The admin has been DELETED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteRow("administrators", "admin_id", this.adminView.getDataAdmin(this.adminView.getSelectedRowAdmin(), 0), errorMg, confMg);
			adminView.UpdateFrame();
		}
		
		if(e.getActionCommand().equals("Update Tables")) {
			adminView.UpdateFrame();
			JOptionPane.showMessageDialog(this.adminView, "Tables have been UPDATED!", "Update", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(e.getActionCommand().equals("Logout")) {
			JOptionPane.showMessageDialog(this.adminView, "See you soon!", "Logout", JOptionPane.INFORMATION_MESSAGE);
			this.adminView.getAdminView().dispose();
		}

}
}