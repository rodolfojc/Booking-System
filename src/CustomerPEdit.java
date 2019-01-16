import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerPEdit extends JFrame {

	private View custPedit;
	private CustomerView custView;
	private CustomerController custController;
	private JTextField email, mob, address;
	private JButton editEmail, editMob, editAddress, submit, cancel;
	
	public CustomerPEdit(CustomerController CustController, CustomerView CustView) {
	
		this.custView = CustView;
		this.custController = CustController;
		this.custPedit = new View("Customer Profile Editor", 500, 300, true);
		setupFrame();
		
	}
	
	public void setupFrame() {
		
		this.custPedit.setBox(this.custPedit.panel, 1);
		
		//JPanel oldEmail = new JPanel();
		JPanel newEmail = new JPanel();
		//JPanel oldMob = new JPanel();
		JPanel newMob = new JPanel();
		//JPanel oldAddress = new JPanel();
		JPanel newAddress = new JPanel();
		JPanel buttons = new JPanel();
				
		
		this.custPedit.addLabel("Email ", newEmail);
		this.email = this.custPedit.addTextField(20, newEmail);
		this.email.setText(this.custView.getCustomerEmail());
		this.email.setEditable(false);
		this.editEmail = this.custPedit.addButton("Edit",newEmail);
		this.editEmail.setActionCommand("Email Edit");
		this.editEmail.addActionListener(custController);
		
		this.custPedit.addLabel("Mobile ", newMob);		
		this.mob = this.custPedit.addTextField(20, newMob);
		this.mob.setText(this.custView.getCustomerMobile());
		this.mob.setEditable(false);
		this.editMob = this.custPedit.addButton("Edit",newMob);
		this.editMob.setActionCommand("Mobile Edit");
		this.editMob.addActionListener(custController);
		
		this.custPedit.addLabel("Address ", newAddress);
		this.address = this.custPedit.addTextField(20, newAddress);
		this.address.setText(this.custView.getCustomerAddress());
		this.address.setEditable(false);
		this.editAddress = this.custPedit.addButton("Edit",newAddress);
		this.editAddress.setActionCommand("Address Edit");
		this.editAddress.addActionListener(custController);
		
		this.submit = this.custPedit.addButton("Submit", buttons);
		this.submit.setActionCommand("Submit");
		this.submit.addActionListener(custController);
		
		this.cancel = this.custPedit.addButton("Cancel", buttons);
		this.cancel.setActionCommand("Cancel Edit");
		this.cancel.addActionListener(custController);
		
		//this.custPedit.panel.add(oldEmail);
		this.custPedit.panel.add(newEmail);
		//this.custPedit.panel.add(oldMob);
		this.custPedit.panel.add(newMob);
		//this.custPedit.panel.add(oldAddress);
		this.custPedit.panel.add(newAddress);
		this.custPedit.panel.add(buttons);
		
		this.custPedit.validate();
		this.custPedit.repaint();
		
	}
	
	
	
	
}
