import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerPEdit extends JFrame {

	private View custPedit;
	private CustomerView custView;
	private JTextField email;
	
	public CustomerPEdit() {
	
		this.custPedit = new View("Customer Profile Editor", 500, 300, false);
		setupFrame();
		
	}
	
	public void setupFrame() {
		
		JPanel myPanel = new JPanel();
		
		this.email = this.custPedit.addTextField(20, this.custPedit.panel);
		this.custPedit.addLabel("New email ", this.custPedit.panel);
		
		this.custPedit.validate();
		this.custPedit.repaint();
		
	}
	
	
	
	
}
