import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ProviderController implements ActionListener, ListSelectionListener {

	// GLOBAL VARIABLES - DECLARATION
	private ProviderView proView;
	private ProviderPEdit proPedit;
	private Login login;

	// CONSTRUCTOR
	public ProviderController(String email, Login Login) {

		// NEW INSTANCE OF PROVIDERVIEW THAT ALSO INSTANCE AN VIEW OBJECT FOR PROVIDER VIEW
		this.proView = new ProviderView(this, email);
		this.login = Login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		// TO ADD AN AVAILABILITY
		if (e.getActionCommand().equals("Add")) {

			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			proDB.addAvailability();

		}
		
		//TO ADD ALL DAY AVAILABLE
		if (e.getActionCommand().equals("Add All")) {
			
			//FOR LOOP - WORKING HRS
			for (int x = 0; x < 24; x++) {
				String hr = this.proView.getHrs(x);
				ProviderDBQ proDB = new ProviderDBQ(this.proView);
				proDB.addAvailability(hr);
			}

			JOptionPane.showMessageDialog(this.proView, "Your availabilities have been added");
			this.proView.UpdateFrame();
		}

		// TO CONFIRM APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Confirm")) {
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			String avai_ref = this.proView.getDataBooked(this.proView.getSelectedRowT(), 0);
			proDB.comfirmAppointPro(avai_ref);
		}

		// TO DELETE AVAILABILITY SELECTED ON TABLE BY AVAILABILITY REFERENCE
		if (e.getActionCommand().equals("Delete")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "The availability has been DELETED!";
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			proDB.deleteRow("availabilities", "avai_ref", this.proView.getDataAvai(this.proView.getSelectedRowTwo(), 0), errorMg, confMg);
			proView.UpdateFrame();
		}

		// TO CANCEL APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Cancel")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Appointment has been CANCELLED!";
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			proDB.cancelAppointPro(this.proView.getDataBooked(this.proView.getSelectedRowT(), 0), errorMg, confMg);
			this.proView.UpdateFrame();
		}

		// TO SET AS COMPLETED TO AN APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Completed")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Appointment has been set COMPLETED!";
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			proDB.setCompletedAppointPro(this.proView.getDataBooked(this.proView.getSelectedRowT(), 0), errorMg, confMg);
			this.proView.UpdateFrame();
		}

		// TO SHOW COMMENTS OF AN APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Show")) {

			JOptionPane.showMessageDialog(this.proView, this.proView.getDataBooked(this.proView.getSelectedRowT(), 6));

		}

		// TO LOGOUT
		if (e.getActionCommand().equals("Logout")) {
			JOptionPane.showMessageDialog(this.proView, "See you soon!", "Logout", JOptionPane.INFORMATION_MESSAGE);
			this.proView.getProView().dispose();
			this.login.getLogin().setVisible(true);
		}
		
		/////////////////////////////////////////
		/////////PROVIDER PROFILE - EDIT/////////
		//CREATE AN INSTANCE OF CUSTOMERPEDIT////

		if (e.getActionCommand().equals("Edit")) {

			this.proPedit = new ProviderPEdit(this, this.proView);
		
		}
		
		//EMAIL - EDIT
		if (e.getActionCommand().equals("Edit Email")) {
			
			this.proPedit.getCurrentEmail().setEditable(true);
			this.proPedit.setEmailFlag(false);
			this.proPedit.setSubmitFlag(false);
			this.proPedit.UpdateFrame();
		}
		
		//EMAIL - SET	
		if (e.getActionCommand().equals("Set Email")) {
			
			this.proPedit.setNewEmail(this.proPedit.getCurrentEmail().getText());
			this.proPedit.setEmailFlag(true);
			this.proPedit.UpdateFrame();
		}
		
		//MOBILE - EDIT
		if (e.getActionCommand().equals("Edit Mobile")) {
			
			this.proPedit.getCurrentMobile().setEditable(true);
			this.proPedit.setMobileFlag(false);
			this.proPedit.setSubmitFlag(false);
			this.proPedit.UpdateFrame();
		}
		
		//MOBILE - SET
		if (e.getActionCommand().equals("Set Mobile")) {
			
			this.proPedit.setNewMobile(this.proPedit.getCurrentMobile().getText());
			this.proPedit.setMobileFlag(true);
			this.proPedit.UpdateFrame();
		}

	}

	//LISTSELECTIONLISTENERS
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
		//AVAILABILITIES TABLE
		if (!this.proView.getModelTwo().isSelectionEmpty()) {
			this.proView.setSelectedRowTwo(this.proView.getModelTwo().getMinSelectionIndex());
			JOptionPane.showMessageDialog(proView,
					"Availability selected: " + "Ref: " + this.proView.getDataAvai(this.proView.getSelectedRowTwo(), 0) + " " 
							+ "date " + this.proView.getDataAvai(this.proView.getSelectedRowTwo(), 1) + " " 
							+ "time " + this.proView.getDataAvai(this.proView.getSelectedRowTwo(), 2) + "");
		}
		
		//BOOKING/APPOINTMENT TABLE
		if (!this.proView.getModel().isSelectionEmpty()) {
			this.proView.setSelectedRowT(this.proView.getModel().getMinSelectionIndex());
			JOptionPane.showMessageDialog(proView,
					"Appointment selected: " + "" + this.proView.getDataBooked(this.proView.getSelectedRowT(), 0) + " " 
							+ "" + this.proView.getDataBooked(this.proView.getSelectedRowT(), 1)+ " " 
							+ "" + this.proView.getDataBooked(this.proView.getSelectedRowT(), 2)+ " " 
							+ "on " + this.proView.getDataBooked(this.proView.getSelectedRowT(), 3) + " " 
							+ "at " + this.proView.getDataBooked(this.proView.getSelectedRowT(), 4) + "o'clock");
		}
	}

}
