import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomerView extends JFrame {
	
	private View custView;
	private JComboBox option;
	private Database data;
	private Controller controller;
	private String customerID, custName, custSurName, custEmail;
	
	public CustomerView(Controller controller, String email) {
		
		this.controller = controller;
		this.custEmail = email;
		getUserData(this.custEmail);
		this.custView = new View("Customer Manager", 800, 600, true);
		costumerViewSetup();
	}
	
	public void setCustomerID(String custID) {
		this.customerID = custID;
	}
	
	public void setCustomerName(String custNam) {
		this.custName = custNam;
	}
	
	public void setCustmerSurName(String custSur) {
		this.custSurName = custSur;
	}
	
	//public void setCustomerEmail(String custEmail) {
	//	this.custEmail = custEmail;
	//}
	
	public String getCustomerEmail() {
		return this.custEmail;
	}
	
	
	public void getUserData(String email) {
		
		data = new Database(this);
		data.customerLogged();
			
	}
	
	public void costumerViewSetup() {
		
		String[] searchOp = {"Name", "Location"};
		
		custView.setBorder(custView.panel);
		
		JPanel top = new JPanel();
		JPanel left = new JPanel();
		JPanel center = new JPanel();
		JPanel right = new JPanel();
		JPanel buttom = new JPanel();
		
		custView.addLabel("Welcome "+this.custName+" "+this.custSurName+"", top);
		
		custView.addLabel("Find appointment by: ", left);
		option = custView.addComboB(searchOp, left);
		int opt = option.getSelectedIndex();
		System.out.print(opt);
		custView.addButton("Search", left);
		
		
		//custView.panel.add(top);
		//custView.panel.add(left);
		custView.panel.add(top, BorderLayout.PAGE_START);
		custView.panel.add(left, BorderLayout.LINE_START);
		
		custView.validate();
		custView.repaint();
		
		
		
		
	}




}
