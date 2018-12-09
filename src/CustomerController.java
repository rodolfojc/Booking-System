import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController implements ActionListener{

	CustomerView custView;
	
	public CustomerController(String email){
		
		this.custView = new CustomerView(this, email);
		
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
		
				
	}
}
