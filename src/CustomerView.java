import java.awt.BorderLayout;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CustomerView extends JFrame {
	
	private View custView;
	private JComboBox option;
	private Database data;
	private Controller controller;
	private JButton search, get;
	private JTable table;
	private String custName, custSurName, custEmail;
	private String [][] dataTableAvai;
	private int selectedRow, customerID;
	
	public CustomerView(Controller controller, String email) {
		
		this.controller = controller;
		this.custEmail = email;
		getUserData(this.custEmail);
		this.custView = new View("Customer Manager", 800, 600, true);
		costumerViewSetup();
	}
	
	public int getSelectedRowT() {
		return this.selectedRow;
	}	
	
	public void setCopyDataAvai(String[][] data) {
		this.dataTableAvai = Arrays.copyOf(data, data.length);
	}
	
	public String getDataAvai(int a, int b) {
		return this.dataTableAvai[a][b];
	}
	
	public void setCustomerID(int custID) {
		this.customerID = custID;
	}
	
	public int getCustomerID() {
		return this.customerID;
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
		
		String[] columnsNam = {"Reference,", "Name", "Surname", "Date", "Time"};
		dataTableAvai = new String[40][5];
		String[] searchOp = {"Name", "Location"};
		custView.setBorder(custView.panel);
		
		JPanel top = new JPanel();
		custView.addLabel("Welcome "+this.custName+" "+this.custSurName+"", top);
		
		
		JPanel left = new JPanel();
		custView.setBorder(left);
		
		JPanel inLeftTop = new JPanel();
		custView.addLabel("Find appointment by: ", inLeftTop);
		option = custView.addComboB(searchOp, inLeftTop);
		search = custView.addButton("Search", inLeftTop);
		search.setActionCommand("Search");
		search.addActionListener(controller);
		inLeftTop.setBorder(new EmptyBorder(new Insets(50,0,0,0)));
		
		JPanel inLeftCenter = new JPanel();
		get = custView.addButton("Get Appointment", inLeftCenter);
		get.setActionCommand("Get Appoint");
		get.addActionListener(controller);
		
		inLeftCenter.setBorder(new EmptyBorder(new Insets(80,0,0,0)));
		
		JPanel inLeftButtom = new JPanel();
		custView.addButton("Logout", inLeftButtom);
		inLeftButtom.setBorder(new EmptyBorder(new Insets(0,0,100,0)));
		
		left.add(inLeftTop, BorderLayout.PAGE_START);
		left.add(inLeftCenter, BorderLayout.CENTER);
		left.add(inLeftButtom, BorderLayout.PAGE_END);
		
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
					selectedRow = model.getMinSelectionIndex();
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
