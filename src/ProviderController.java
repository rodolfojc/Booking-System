import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ProviderController implements ActionListener {

	private ProviderView proView;
	private Login login;
	
	public ProviderController(String email, Login Login) {
		
		this.proView = new ProviderView(this, email);
		this.login = Login;
	}
	
	public ProviderController(String email) {
		
		this.proView = new ProviderView(this, email);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("Add")) {
			
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			proDB.addAvailability();
		}
		
		if (e.getActionCommand().equals("Confirm")) {
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			String avai_ref = this.proView.getDataBooked(this.proView.getSelectedRowT(),0);
			proDB.comfirmAppointPro(avai_ref);
		}
		
		if(e.getActionCommand().equals("Logout")) {
			JOptionPane.showMessageDialog(this.proView, "See you soon!", "Logout", JOptionPane.INFORMATION_MESSAGE);
			this.proView.getProView().dispose();
			this.login.getLogin().setVisible(true);
		}
		
		if(e.getActionCommand().equals("Delete")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
	      	String confMg = "The availability has been DELETED!";
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			proDB.deleteRow("availabilities", "avai_ref", this.proView.getDataAvai(this.proView.getSelectedRowTwo(), 0), errorMg, confMg);
			proView.UpdateFrame();
		}
		
		if (e.getActionCommand().equals("Cancel")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Appointment has been CANCELLED!";
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			proDB.cancelAppointPro(this.proView.getDataBooked(this.proView.getSelectedRowT(), 0), errorMg, confMg);;
			this.proView.UpdateFrame();
		}
		
		if (e.getActionCommand().equals("Completed")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Appointment has been set COMPLETED!";
			ProviderDBQ proDB = new ProviderDBQ(this.proView);
			proDB.setCompletedAppointPro(this.proView.getDataBooked(this.proView.getSelectedRowT(), 0), errorMg, confMg);;
			this.proView.UpdateFrame();
		}
		
		if(e.getActionCommand().equals("Show")) {
			
			JOptionPane.showMessageDialog(this.proView, this.proView.getDataBooked(this.proView.getSelectedRowT(), 6));
			
		}
		
		
	}

}
