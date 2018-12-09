import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProviderController implements ActionListener {

	ProviderView proView;
	
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
		
		
		
		
	}

}
