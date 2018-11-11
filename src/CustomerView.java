import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CustomerView extends JFrame {
	
	private View custView;
	private JComboBox option;
	private Database data;
	private Controller controller;
	private JButton search;
	private JTable table;
	private String customerID, custName, custSurName, custEmail;
	private String [][] dataTableAvai;
	
	public CustomerView(Controller controller, String email) {
		
		this.controller = controller;
		this.custEmail = email;
		getUserData(this.custEmail);
		this.custView = new View("Customer Manager", 800, 600, true);
		costumerViewSetup();
	}
	
	public void setCopyDataAvai(String[][] data) {
		this.dataTableAvai = Arrays.copyOf(data, data.length);
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
		
		String[] columnsNam = {"Name", "Surname", "Date", "Time"};
		dataTableAvai = new String[40][4];
				
		String[] searchOp = {"Name", "Location"};
		
		custView.setBorder(custView.panel);
		
		JPanel top = new JPanel();
		JPanel left = new JPanel();
		
		JPanel right = new JPanel();
		JPanel buttom = new JPanel();
		
		custView.addLabel("Welcome "+this.custName+" "+this.custSurName+"", top);
		
		custView.addLabel("Find appointment by: ", left);
		option = custView.addComboB(searchOp, left);
		int opt = option.getSelectedIndex();
		System.out.print(opt);
		search = custView.addButton("Search", left);
		search.setActionCommand("Search");
		search.addActionListener(controller);
		
		JPanel center = new JPanel();
		Database data = new Database(this);
		data.availableCosTable();
		
		custView.addTableS(dataTableAvai, columnsNam, center, "Availabilites");
		//custView.myTable.setDefaultEditor(Object.class, null);
		ListSelectionModel model = custView.myTable.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!model.isSelectionEmpty()) {
					int selectedRow = model.getMinSelectionIndex();
					JOptionPane.showMessageDialog(custView, "Appointment selected: "+dataTableAvai[selectedRow][0]+" "
							+ ""+dataTableAvai[selectedRow][1]+" "
									+ "on "+dataTableAvai[selectedRow][2]+" "
											+ "at "+dataTableAvai[selectedRow][3]+" o'clock");
				}
			}
		});
		//custView.panel.add(top);
		//custView.panel.add(left);
		custView.panel.add(top, BorderLayout.PAGE_START);
		custView.panel.add(left, BorderLayout.LINE_START);
		custView.panel.add(center, BorderLayout.CENTER);
		
		custView.validate();
		custView.repaint();
		
		
		
		
	}




}
