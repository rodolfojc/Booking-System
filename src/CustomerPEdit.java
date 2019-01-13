import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerPEdit extends JFrame {

	private View custPedit;
	private CustomerView custView;
	private JTextField email, mob, address;
	
	public CustomerPEdit() {
	
		this.custPedit = new View("Customer Profile Editor", 500, 300, true);
		setupFrame();
		
	}
	
	public void setupFrame() {
		
		this.custPedit.setBox(this.custPedit.panel, 1);
		
		JPanel oldEmail = new JPanel();
		JPanel newEmail = new JPanel();
		JPanel oldMob = new JPanel();
		JPanel newMob = new JPanel();
		JPanel oldAddress = new JPanel();
				
		this.email = this.custPedit.addTextField(20, oldEmail);
		this.email.setText("Old email");
		this.email.setEditable(false);
		
		this.custPedit.addLabel("New email ", newEmail);
		this.custPedit.addTextField(20, newEmail);
		
		this.mob = this.custPedit.addTextField(20, oldMob);
		this.mob.setText("Old Mobile");
		this.mob.setEditable(false);
		
		this.custPedit.addLabel("New Mobile ", newMob);
		this.custPedit.addTextField(20, newMob);
		
		this.custPedit.panel.add(oldEmail);
		this.custPedit.panel.add(newEmail);
		this.custPedit.panel.add(oldMob);
		this.custPedit.panel.add(newMob);
		
		
		
		
		
		
		this.custPedit.validate();
		this.custPedit.repaint();
		
	}
	
	
	
	
}
