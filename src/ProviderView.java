import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProviderView extends JFrame {
	
	View proView;
	
	public ProviderView () {
		
		this.proView = new View("Provider Manager", 800, 600, true);
		
		
	}
	
	public void providerViewSetup() {
		
		
		JPanel top = new JPanel();
		proView.addLabel("Welcome XXXXX", top);
	
		
		
		
		
	}
	
	
	
	
	
}
