import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class CustomerController implements ActionListener{

	CustomerView custView;
	Login login;
	
	public CustomerController(String email, Login Login){
		
		this.custView = new CustomerView(this, email);
		this.login = Login;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Search")){
			
			CustomerDBQ custDB = new CustomerDBQ(this.custView);
			//Database data = new Database(this.custView);
			custDB.searchProvider(custView.getByOption(), custView.getByField());
			this.custView.UpdateFrame(false);
		}
		
		if (e.getActionCommand().equals("Get Appoint")) {
			
			CustomerDBQ custDB = new CustomerDBQ(this.custView);
			//Database data = new Database(this.custView);
			String avai_ref = custDB.bookAppointment();
			CustomerDBQ custDBTwo = new CustomerDBQ(this.custView);
			//Database dataTwo = new Database(this.custView);
			custDBTwo.setAppointment(avai_ref);
		}
		
		if(e.getActionCommand().equals("Logout")) {
			JOptionPane.showMessageDialog(this.custView, "See you soon!", "Logout", JOptionPane.INFORMATION_MESSAGE);
			this.custView.getCustView().dispose();
			this.login.getLogin().setVisible(true);
		}
		
		if (e.getActionCommand().equals("Cancel")) {
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Appointment has been CANCELLED!";
			CustomerDBQ custDB = new CustomerDBQ(this.custView);
			custDB.cancelAppoint(errorMg, confMg);;
			this.custView.UpdateFrame(false);
		}
		
		if(e.getActionCommand().equals("Set")) {
			String oldcomment = "Comment: "+this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 6);
			String comment = (String)JOptionPane.showInputDialog(this.custView,
					oldcomment,
					"Answer",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					null);
			
			String errorMg = "Ups, there is an internal problem, please contact an administrator";
			String confMg = "Your Comment have been SET!";
			CustomerDBQ custDB = new CustomerDBQ(this.custView);
			custDB.updateRow("appointments", "comments","Customer: "+comment+"", "appoint_ref", this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 0), errorMg, confMg);
			this.custView.UpdateFrame(false);
			
		}
		
		
		
	}
}
