import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ProviderController implements ActionListener {

	// GLOBAL VARIABLES - DECLARATION
	private ProviderView proView;
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
		if(e.getActionCommand().equals("Add")) {
			
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			proDB.addAvailability();
			
		}
		
		// TO CONFIRM APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Confirm")) {
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			String avai_ref = this.proView.getDataBooked(this.proView.getSelectedRowT(),0);
			proDB.comfirmAppointPro(avai_ref);
		}
		
		// TO DELETE AVAILABILITY SELECTED ON TABLE BY AVAILABILITY REFERENCE
		if(e.getActionCommand().equals("Delete")) {
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
			proDB.cancelAppointPro(this.proView.getDataBooked(this.proView.getSelectedRowT(), 0), errorMg, confMg);;
			this.proView.UpdateFrame();
		}
		
		// TO SET AS COMPLETED TO AN APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if (e.getActionCommand().equals("Completed")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Appointment has been set COMPLETED!";
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			proDB.setCompletedAppointPro(this.proView.getDataBooked(this.proView.getSelectedRowT(), 0), errorMg, confMg);;
			this.proView.UpdateFrame();
		}
		
		// TO SHOW COMMENTS OF AN APPOINTMENT SELECTED ON TABLE BY APPOINTMENT REFERENCE
		if(e.getActionCommand().equals("Show")) {
			
			JOptionPane.showMessageDialog(this.proView, this.proView.getDataBooked(this.proView.getSelectedRowT(), 6));
			
		}
		
		// TO LOGOUT
		if(e.getActionCommand().equals("Logout")) {
			JOptionPane.showMessageDialog(this.proView, "See you soon!", "Logout", JOptionPane.INFORMATION_MESSAGE);
			this.proView.getProView().dispose();
			this.login.getLogin().setVisible(true);
		}
		
		
	}

}
