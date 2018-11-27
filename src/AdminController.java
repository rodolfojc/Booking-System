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
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteCustOrPro("customers", "cust_ID", this.adminView.getDataCust(this.adminView.getSelectedRowCust(), 0));
			adminView.UpdateFrame();
		}
		
		if(e.getActionCommand().equals("Delete Provider")) {
			AdminDBQ data = new AdminDBQ(this.adminView);
			data.deleteCustOrPro("providers", "pro_ID", this.adminView.getDataPro(this.adminView.getSelectedRowPro(), 0));
			adminView.UpdateFrame();
		}

}
}