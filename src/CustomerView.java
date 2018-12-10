import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CustomerView extends JFrame {
	
	private View custView;
	private JComboBox option;
	private Database data;
	//private Controller controller;
	private JButton search, get, logout, cancel, set;
	private JTable table;
	private String custName, custSurName, custEmail;
	private String [][] dataTableAvai, dataTableStatus;;
	private JScrollPane scrollAvai, scrollStatus;
	private JTextField byInput;
	private String byOption, byField;
	String[] searchOp = {"Name", "Location"};
	private int selectedRow, selectedRowTwo, customerID;
	private boolean tableflag=true;
	private CustomerController custController;
	private CustomerDBQ custDB;
	
	/*public CustomerView(Controller controller, String email) {
		
		this.controller = controller;
		this.custEmail = email;
		getUserData();
		this.custView = new View("Customer Manager", 800, 600, true);
		this.dataTableAvai = new String[50][5];
		costumerViewSetup();
	}*/
	
	public CustomerView(CustomerController CustController, String email) {
		
		this.custController = CustController;
		this.custEmail = email;
		getUserData();
		this.custView = new View("Customer Manager", 960, 550, true);
		this.dataTableAvai = new String[50][5];
		this.dataTableStatus = new String[10][7];
		costumerViewSetup();
	}
	
	public View getCustView() {
		return this.custView;
	}
	
	public String getCustName() {
		return this.custName;
	}
	
	public String getCustSurN() {
		return this.custSurName;
	}
	
	public String getByOption() {
		return searchOp[option.getSelectedIndex()];
	}
	
	public String getByField() {
		return byInput.getText();
	}
	
	public int getSelectedRowT() {
		return this.selectedRow;
	}
	
	public int getSelectedRowTTwo() {
		return this.selectedRowTwo;
	}	
	
	public void setCopyDataAvai(String[][] data) {
		this.dataTableAvai = Arrays.copyOf(data, data.length);
	}
	
	public String getDataAvai(int a, int b) {
		return this.dataTableAvai[a][b];
	}
	
	public String getTableStatus(int a, int b) {
		return this.dataTableStatus[a][b];
	}
	
	public void setCopyTableStatus(String[][] data) {
		this.dataTableStatus = Arrays.copyOf(data, data.length);
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
	
	public String getCustomerEmail() {
		return this.custEmail;
	}
	
	
	public void getUserData() {
		
		CustomerDBQ custDB = new CustomerDBQ(this);
		custDB.customerLogged();
		//data = new Database(this);
		//data.customerLogged();
			
	}
	
	public void costumerViewSetup() {
		
		custView.setBorder(custView.panel);
		
		String[] columnsNam = {"Nro. Ref", "Name", "Surname", "Date", "Time"};
		String[] statusColumnsNam = {"Nro. Ref.", "Name", "Surname", "Date", "Time", "Status", "Comments"};
		
		
		JPanel top = new JPanel();
		custView.addLabel("Welcome "+this.custName+" "+this.custSurName+"", top);
		
		
		JPanel left = new JPanel();
		custView.setBorder(left);
		
		JPanel inLeftTop = new JPanel();
		custView.addLabel("Find appointment by: ", inLeftTop);
		option = custView.addComboB(searchOp, inLeftTop);
		byInput = custView.addTextField(10, inLeftTop);
		
		inLeftTop.setBorder(new EmptyBorder(new Insets(50,0,0,0)));
		
		JPanel inLeftCenter = new JPanel();
		search = custView.addButton("Search", inLeftCenter);
		search.setActionCommand("Search");
		//search.addActionListener(controller);
		search.addActionListener(custController);
		inLeftCenter.setBorder(new EmptyBorder(new Insets(15,0,0,0)));
		
		JPanel inLeftButtom = new JPanel();
		logout = custView.addButton("Logout", inLeftButtom);
		logout.setActionCommand("Logout");
		logout.addActionListener(custController);
		inLeftButtom.setBorder(new EmptyBorder(new Insets(0,0,100,0)));
		
		left.add(inLeftTop, BorderLayout.PAGE_START);
		left.add(inLeftCenter, BorderLayout.CENTER);
		left.add(inLeftButtom, BorderLayout.PAGE_END);
		
		JPanel center = new JPanel();
		//this.custView.setGrid(1, 2, center);
		this.custView.setBox(center, 1);
		JPanel tableCenter = new JPanel();
		this.custView.setBox(tableCenter, 1);
		JPanel buttonCenter = new JPanel();
		JPanel tableStatus = new JPanel();
		center.add(tableCenter);
		
		//center.add(buttonCenter);
		center.add(tableStatus);
		CustomerDBQ custDB = new CustomerDBQ(this);
		//Database data = new Database(this);
		if (this.tableflag) {custDB.searchProvider("All","All");}
		
		scrollAvai = custView.addTableS(0, dataTableAvai, columnsNam, tableCenter, "Availabilities");
		scrollAvai.setPreferredSize(new Dimension(400,150));
		ListSelectionModel model = custView.myTable[0].getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!model.isSelectionEmpty()) {
					selectedRow = model.getMinSelectionIndex();
					JOptionPane.showMessageDialog(custView, "Appointment selected: "+dataTableAvai[selectedRow][0]+" "
							+ ""+dataTableAvai[selectedRow][1]+" "
									+ ""+dataTableAvai[selectedRow][2]+" "
											+ "on "+dataTableAvai[selectedRow][3]+" "
													+ "at "+dataTableAvai[selectedRow][4]+" o'clock");
				}
			}
		});
		tableCenter.add(buttonCenter);
		get = custView.addButton("Get Appointment", buttonCenter);
		get.setActionCommand("Get Appoint");
		//get.addActionListener(controller);
		get.addActionListener(custController);
		
		CustomerDBQ custDBAppoint = new CustomerDBQ(this);
		custDBAppoint.searchAppointRecord();
		scrollStatus = custView.addTableS(1, dataTableStatus, statusColumnsNam, tableStatus, "Appointments");
		scrollStatus.setPreferredSize(new Dimension(600,100));
		center.add(tableStatus);
		ListSelectionModel modeltwo = custView.myTable[1].getSelectionModel();
		modeltwo.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!modeltwo.isSelectionEmpty()) {
					selectedRowTwo = modeltwo.getMinSelectionIndex();
					JOptionPane.showMessageDialog(custView, "Appointment selected: "+dataTableStatus[selectedRowTwo][0]+" "
							+ ""+dataTableStatus[selectedRowTwo][1]+" "
									+ ""+dataTableStatus[selectedRowTwo][2]+" "
											+ "on "+dataTableStatus[selectedRowTwo][3]+" "
													+ "at "+dataTableStatus[selectedRowTwo][4]+" o'clock"
															+ " STATUS "+dataTableStatus[selectedRowTwo][5]+"");
				}
			}
		});
		
		JPanel delBtn = new JPanel();
		cancel = this.custView.addButton("Cancel", delBtn);
		tableStatus.add(delBtn);
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(custController);
		
		
		JPanel comentBtn = new JPanel();
		set = this.custView.addButton("Set a feetback/complain", comentBtn);
		tableStatus.add(comentBtn);
		set.setActionCommand("Set");
		set.addActionListener(custController);
		
		
		
		
		//custView.panel.add(top);
		//custView.panel.add(left);
		custView.panel.add(top, BorderLayout.PAGE_START);
		custView.panel.add(left, BorderLayout.LINE_START);
		custView.panel.add(center, BorderLayout.CENTER);
		
		custView.validate();
		custView.repaint();
				
	}
	
	public void UpdateFrame(boolean active) {
		custView.panel.removeAll();
		this.tableflag=active;
		costumerViewSetup();
	}




}
