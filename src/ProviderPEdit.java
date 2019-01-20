import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProviderPEdit extends JFrame {

	private View proPedit;
	private ProviderView proView;
	private ProviderController proController;
	private String currentEmail, currentMob, currentAddress, currentLocation;
	
		
	public ProviderPEdit(ProviderController ProController, ProviderView ProView ) {
		
		this.proView = ProView;
		this.proController = ProController;
		this.currentEmail = this.proView.getProviderEmail();
		this.currentMob = this.proView.getProviderMobile();
		this.currentAddress = this.proView.getProviderAddress();
		this.currentLocation = this.proView.getProviderLocation();
		this.proPedit = new View("Provider Profile Editor", 500, 300, true);
		this.proPedit.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setupFrame();
	}

	public void setupFrame() {
		
	}
	
	
	
	
}
