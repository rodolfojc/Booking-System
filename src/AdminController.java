import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AdminController implements ActionListener, ListSelectionListener {

	// GLOBAL VARIABLES - DECLARATION
	private AdminView adminView;
	private Login login;

	// CONSTRUCTOR
	public AdminController(String AdminUser, Login Login) {

		// NEW INSTANCE OF ADMINVIEW THAT ALSO INSTANCE AN VIEW OBJECT FOR ADMIN VIEW
		this.adminView = new AdminView(this, AdminUser);
		this.login = Login;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// TO DELECT A CUSTOMER SELECTED IN TABLE BY ID
		if (e.getActionCommand().equals("Delete Customer")) {
			String errorMg = "Ups, there is a problem, please be sure the customer does not have"
					+ " any availability or appointment registered.";
			String confMg = "The user has been DELETED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteRow("customers", "cust_ID", this.adminView.getDataCust(this.adminView.getSelectedRowCust(), 0),
					errorMg, confMg);
			adminView.UpdateFrame();
		}

		// TO DELECT A PROVIDER SELECTED IN TABLE BY ID
		if (e.getActionCommand().equals("Delete Provider")) {
			String errorMg = "Ups, there is a problem, please be sure the provider does not have"
					+ " any availability or appointment registered.";
			String confMg = "The user has been DELETED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteRow("providers", "pro_ID", this.adminView.getDataPro(this.adminView.getSelectedRowPro(), 0),
					errorMg, confMg);
			adminView.UpdateFrame();
		}

		// TO VALIDATE A CUSTOMER SELECTED IN TABLE BY ID (IN STATUS PENDING)
		if (e.getActionCommand().equals("Validate Provider")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Provider has been CONFIRMED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.updateRow("providers", "status", "Confirmed", "pro_id",
					this.adminView.getDataPro(this.adminView.getSelectedRowPro(), 0), errorMg, confMg);
			;
			adminView.UpdateFrame();
		}

		// TO DELETE AVAILABILITY SELECTED ON TABLE BY AVAILABILITY REFERENCE
		if (e.getActionCommand().equals("Delete Availability")) {
			String errorMg = "Ups, there is a problem, please be sure the availability does not have"
					+ " any appointment registered.";
			String confMg = "The availability has been DELETED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteRow("availabilities", "avai_ref",
					this.adminView.getDataAvai(this.adminView.getSelectedRowAvai(), 0), errorMg, confMg);
			adminView.UpdateFrame();
		}

		// TO DELETE APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Delete Appointment")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "The appointment has been DELETED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteRow("appointments", "appoint_ref",
					this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 0), errorMg, confMg);

			//////// UPDATE CHILD ROW AVAI_REF TO CANCELLED////////
			String errorMgTwo = "Ups, there is an internal problem, please contact an administrator";
			String confMgTwo = "Availability has been set CANCELLED!";
			AdminDBQ dataTwo = new AdminDBQ(this.adminView);
			dataTwo.updateRow("availabilities", "available", "Cancelled", "avai_ref",
					this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 1), errorMgTwo, confMgTwo);
			adminView.UpdateFrame();
		}

		// TO MANAGE/SET A COMMENT TO AN APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Manage")) {
			String oldcomment = "Comment: " + this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 3);
			String comment = (String) JOptionPane.showInputDialog(this.adminView, oldcomment, "Answer",
					JOptionPane.PLAIN_MESSAGE, null, null, null);

			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Your Comment have been SET!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.updateRow("appointments", "comments", "Admin: " + comment + "", "appoint_ref",
					this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 0), errorMg, confMg);
			adminView.UpdateFrame();

		}

		// TO SHOW COMMENTS OF AN APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Show")) {

			JOptionPane.showMessageDialog(this.adminView,
					this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 3));

		}

		// TO CREATE AN ADMINSTRATOR, INPUTS MUST BE PROVIDED
		if (e.getActionCommand().equals("Add Admin")) {
			String user = (String) JOptionPane.showInputDialog(this.adminView, "Email address", "Admin Register",
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			System.out.println(user);

			String pass = (String) JOptionPane.showInputDialog(this.adminView, "Password?", "Admin Register",
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			System.out.println(pass);

			AdminDBQ data = new AdminDBQ(this.adminView);
			data.createAdmin(user, pass);
			adminView.UpdateFrame();

		}

		// TO DELETE ADMINISTRATOR SELECTED ON TABLE BY ADMINISTRATOR ID
		if (e.getActionCommand().equals("Delete Admin")) {
			String errorMg = "Ups, there is an internal problem!! Check the code";
			String confMg = "The admin has been DELETED!";
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteRow("administrators", "admin_id",
					this.adminView.getDataAdmin(this.adminView.getSelectedRowAdmin(), 0), errorMg, confMg);
			adminView.UpdateFrame();
		}

		// TO UPDATE ALL TABLES IN ADMINISTRATOR MANAGER
		if (e.getActionCommand().equals("Update Tables")) {
			adminView.UpdateFrame();
			JOptionPane.showMessageDialog(this.adminView, "Tables have been UPDATED!", "Update",
					JOptionPane.INFORMATION_MESSAGE);
		}

		// TO LOGOUT
		if (e.getActionCommand().equals("Logout")) {
			JOptionPane.showMessageDialog(this.adminView, "See you soon!", "Logout", JOptionPane.INFORMATION_MESSAGE);
			this.adminView.getAdminView().dispose();
			this.login.getLogin().setVisible(true);
		}

	}

	//LISTSELECTIONLISTENERS
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
		//CUSTOMERS TABLE
		if (!this.adminView.getModelCust().isSelectionEmpty()) {
			this.adminView.setSelectedRowCust(this.adminView.getModelCust().getMinSelectionIndex());
			JOptionPane.showMessageDialog(adminView,
					"Customer selected: ID " + this.adminView.getDataCust(this.adminView.getSelectedRowCust(), 0) + ", " + ""
							+ this.adminView.getDataCust(this.adminView.getSelectedRowCust(), 1) + " " 
							+"" + this.adminView.getDataCust(this.adminView.getSelectedRowCust(), 2) +" "
							+". If you want to delete it, press DELETE!");

		}
		
		//PROVIDERS TABLE
		else if (!this.adminView.getModelPro().isSelectionEmpty()) {
			this.adminView.setSelectedRowPro(this.adminView.getModelPro().getMinSelectionIndex());
			JOptionPane.showMessageDialog(adminView,
					"Provider selected: ID " + this.adminView.getDataPro(this.adminView.getSelectedRowPro(), 0)+ ", " + ""
							+ this.adminView.getDataPro(this.adminView.getSelectedRowPro(), 1) + " " 
							+ "" + this.adminView.getDataPro(this.adminView.getSelectedRowPro(), 2) + ""
							+ ". If you want to delete it, press DELETE. "
							+ "If you want to validate it, press VALIDATE!");

		}
		
		//AVAILABILITIES TABLE
		else if (!this.adminView.getModelAvai().isSelectionEmpty()) {
			this.adminView.setSelectedRowAvai(this.adminView.getModelAvai().getMinSelectionIndex());
			JOptionPane.showMessageDialog(adminView,
					"Availability selected: Ref " + this.adminView.getDataAvai(this.adminView.getSelectedRowAvai(), 0) + ", " 
							+ "Provider ID " + this.adminView.getDataAvai(this.adminView.getSelectedRowAvai(), 1) + ", " 
							+ "On " + this.adminView.getDataAvai(this.adminView.getSelectedRowAvai(), 2) + ""
							+ ". If you want to delete it, press DELETE!");

		}
		
		//APPOINTMENTS TABLE
		else if (!this.adminView.getModelAppoint().isSelectionEmpty()) {
			this.adminView.setSelectedRowAppoint(this.adminView.getModelAppoint().getMinSelectionIndex());
			JOptionPane.showMessageDialog(adminView,
					"Appointment selected: Ref " + this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 0) + ", "
							+ "Availability Ref " + this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 1) + ", "
							+ "Customer ID " + this.adminView.getDataAppoint(this.adminView.getSelectedRowAppoint(), 2) + ""
							+ ". If you want to delete it, press DELETE!");

		}
		
		//ADMINISTRATORS TABLE
		else if (!this.adminView.getModelAdmin().isSelectionEmpty()) {
			this.adminView.setSelectedRowAdmin(this.adminView.getModelAdmin().getMinSelectionIndex());
			JOptionPane.showMessageDialog(adminView,
					"Administrador selected: ID " + this.adminView.getDataAdmin(this.adminView.getSelectedRowAdmin(), 0) + ", " 
							+ "Admin user " + this.adminView.getDataAdmin(this.adminView.getSelectedRowAdmin(), 1) + ", " 
							+ "Privilege " 	+ this.adminView.getDataAdmin(this.adminView.getSelectedRowAdmin(), 2) + ""
							+ ". If you want to delete it, press DELETE!");

		}
		
		
	}
}