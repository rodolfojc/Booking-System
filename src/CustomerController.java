import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CustomerController implements ActionListener, ListSelectionListener {

	// GLOBAL VARIABLES - DECLARATION
	private CustomerView custView;
	private Login login;

	// CONSTRUCTOR
	public CustomerController(String email, Login Login) {

		// NEW INSTANCE OF CUSTOMERVIEW THAT ALSO INSTANCE AN VIEW OBJECT FOR CUSTOMER VIEW
		this.custView = new CustomerView(this, email);
		this.login = Login;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// TO SEARCH AVAILABILITY BY NAME OR LOCATION
		if (e.getActionCommand().equals("Search")) {

			CustomerDBQ custDB = new CustomerDBQ(this.custView);
			custDB.searchProvider(custView.getByOption(), custView.getByField());
			this.custView.UpdateFrame(false);
		}

		// FOR GET APPOINTMENT SELECTED ON TABLE BY AVAILABILITY REFERENCE
		if (e.getActionCommand().equals("Get Appoint")) {

			CustomerDBQ custDB = new CustomerDBQ(this.custView);
			String avai_ref = custDB.bookAppointment();
			CustomerDBQ custDBTwo = new CustomerDBQ(this.custView);
			custDBTwo.setAppointment(avai_ref);
		}

		// TO CANCEL APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Cancel")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Appointment has been CANCELLED!";
			CustomerDBQ custDB = new CustomerDBQ(this.custView);
			custDB.cancelAppoint(errorMg, confMg);
			;
			this.custView.UpdateFrame(false);
		}

		// TO SET A COMMENT TO AN APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Set")) {
			String oldcomment = "Comment: " + this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 6);
			String comment = (String) JOptionPane.showInputDialog(this.custView, oldcomment, "Answer",
					JOptionPane.PLAIN_MESSAGE, null, null, null);

			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Your Comment have been SET!";
			CustomerDBQ custDB = new CustomerDBQ(this.custView);
			custDB.updateRow("appointments", "comments", "Customer: " + comment + "", "appoint_ref",
					this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 0), errorMg, confMg);
			this.custView.UpdateFrame(false);

		}

		// TO SHOW COMMENTS OF AN APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Show")) {

			JOptionPane.showMessageDialog(this.custView,
					this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 6));

		}
		
		// TO LOGOUT
		if (e.getActionCommand().equals("Logout")) {
			JOptionPane.showMessageDialog(this.custView, "See you soon!", "Logout", JOptionPane.INFORMATION_MESSAGE);
			this.custView.getCustView().dispose();
			this.login.getLogin().setVisible(true);
		}

	}

	//LISTSELECTIONLISTENERS
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
		//AVAILABILITIES TABLE 
		if (!this.custView.getModel().isSelectionEmpty()) {
			this.custView.setSelectedRowT(this.custView.getModel().getMinSelectionIndex());
			JOptionPane.showMessageDialog(custView,
					"Appointment selected: " + this.custView.getDataAvai(this.custView.getSelectedRowT(), 0) + " " + ""
							+ this.custView.getDataAvai(this.custView.getSelectedRowT(), 1) + " " + "" 
							+ this.custView.getDataAvai(this.custView.getSelectedRowT(), 2) + " "
							+ "on " + this.custView.getDataAvai(this.custView.getSelectedRowT(), 3) + " " 
							+ "at " + this.custView.getDataAvai(this.custView.getSelectedRowT(), 0) + " o'clock");
		}
		
		//STATUS/APPOINTMENTS TABLE
		if (!this.custView.getModelTwo().isSelectionEmpty()) {
			this.custView.setSelectedRowTTwo(this.custView.getModelTwo().getMinSelectionIndex());
			JOptionPane.showMessageDialog(custView,
					"Appointment selected: " + this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 0) + " " + ""
							+ this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 1) + " " + "" 
							+ this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 2) + " "
							+ "on " + this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 3) + " " 
							+ "at " + this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 4) + " o'clock" 
							+ " STATUS " + this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 5) + "");
		}
	}
}
