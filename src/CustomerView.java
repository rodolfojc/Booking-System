import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CustomerView extends JFrame {
	
	View custView;
	JComboBox option;
	
	public CustomerView() {
		
		this.custView = new View("Customer Manager", 800, 600, true);
		costumerViewSetup();
	}

	public void costumerViewSetup() {
		
		String[] searchOp = {"Name", "Location"};
		
		custView.setBorder(custView.panel);
		
		JPanel top = new JPanel();
		JPanel left = new JPanel();
		JPanel center = new JPanel();
		JPanel right = new JPanel();
		JPanel buttom = new JPanel();
		
		custView.addLabel("Welcome XXXXX", top);
		
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
