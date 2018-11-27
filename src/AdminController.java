import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController implements ActionListener {

	AdminView adminView;
	
	public AdminController () {
		
		this.adminView = new AdminView(this);
	
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

}
}