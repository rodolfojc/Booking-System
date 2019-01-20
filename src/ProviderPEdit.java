import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProviderPEdit extends JFrame {

	private View proPedit;
	private ProviderView proView;
	private ProviderController proController;
	private String currentEmail, currentMob, currentAddress, currentLocation;
	private JTextField email, mob, address, location;
	private JButton editEmail, setNewEmail, editMob, setNewMob, editAddress, setNewAddress, editLocation, setNewLocation, submit, cancel;
	private boolean emailFlag = true, mobileFlag = true, addressFlag = true, locationFlag, submitFlag = true;
		
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
		
		this.proPedit.setBox(this.proPedit.panel, 1);
		
		JPanel newEmail = new JPanel();
		JPanel newMob = new JPanel();
		JPanel newAddress = new JPanel();
		JPanel newLocation = new JPanel();
		JPanel buttons = new JPanel();
		
		this.proPedit.addLabel("Email ", newEmail);
		this.email = this.proPedit.addTextField(20, newEmail);
		this.email.setText(this.currentEmail);
		if (this.emailFlag) {this.email.setEditable(false);}		
		this.editEmail = this.proPedit.addButton("Edit",newEmail);
		this.editEmail.setActionCommand("Edit Email");
		this.editEmail.addActionListener(proController);
		this.setNewEmail = this.proPedit.addButton("Set", newEmail);
		this.setNewEmail.setActionCommand("Set Email");
		this.setNewEmail.addActionListener(proController);
		
		this.proPedit.addLabel("Mobile ", newMob);		
		this.mob = this.proPedit.addTextField(20, newMob);
		this.mob.setText(this.currentMob);
		if (this.mobileFlag) {this.mob.setEditable(false);}	
		this.editMob = this.proPedit.addButton("Edit",newMob);
		this.editMob.setActionCommand("Edit Mobile");
		this.editMob.addActionListener(proController);
		this.setNewMob = this.proPedit.addButton("Set", newMob);
		this.setNewMob.setActionCommand("Set Mobile");
		this.setNewMob.addActionListener(proController);
		
		this.proPedit.addLabel("Address ", newAddress);
		this.address = this.proPedit.addTextField(20, newAddress);
		this.address.setText(this.currentAddress);
		if (this.addressFlag) {this.address.setEditable(false);}
		this.editAddress = this.proPedit.addButton("Edit",newAddress);
		this.editAddress.setActionCommand("Edit Address");
		this.editAddress.addActionListener(proController);
		this.setNewAddress = this.proPedit.addButton("Set", newAddress);
		this.setNewAddress.setActionCommand("Set Address");
		this.setNewAddress.addActionListener(proController);
		
		
	}
	
	
	
	
}
