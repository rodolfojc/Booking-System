import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class CustomerView extends JFrame {

	// GLOBAL VARIABLES - DECLARATION
	private View custView;
	private JComboBox option;
	private JButton search, get, logout, cancel, set;
	private String custName, custSurName, custEmail, custAddress, custMobile;
	private String[][] dataTableAvai, dataTableStatus;
	private JScrollPane scrollAvai, scrollStatus;
	private JTextField byInput;
	private String[] searchOp = { "Name", "Location" };
	private int selectedRow, selectedRowTwo, customerID;
	private boolean tableflag = true;
	private CustomerController custController;
	private ListSelectionModel model, modelTwo;

	// CONSTRUCTOR
	public CustomerView(CustomerController CustController, String email) {

		// SETTING CUSTOMER ACTION LISTENER CONTROLLER
		this.custController = CustController;
		this.custEmail = email;
		getUserData(); // METHOD THAT GET THE DATA WITH THE EMAIL PRIVIDED IN LOGIN
		// ARRAYS FOR DATA INSTANTIATION
		this.dataTableAvai = new String[40][5];
		this.dataTableStatus = new String[40][7];
		// NEW INSTANCE OF VIEW FOR CUSTOMER VIEW
		this.custView = new View("Customer Manager", 960, 550, false);
		costumerViewSetup();
	}
	
	// GETTER AND SETTER FOR GLOBAL VARIABLES
	
	public ListSelectionModel getModel() {
		return this.model;
	}
	
	public ListSelectionModel getModelTwo() {
		return this.modelTwo;
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
	
	public void setSelectedRowT(int a) {
		this.selectedRow = a;
	}

	public int getSelectedRowTTwo() {
		return this.selectedRowTwo;
	}
	
	public void setSelectedRowTTwo(int a) {
		this.selectedRowTwo = a;
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
	
	public void setCustomerAddress(String custAddr) {
		this.custAddress = custAddr;
	}
	
	public void setCustomerMobile(String custMob) {
		this.custMobile = custMob;
	}

	public void setCustmerSurName(String custSur) {
		this.custSurName = custSur;
	}

	public String getCustomerEmail() {
		return this.custEmail;
	}

	// METHOD TO GET DATA OF THE CUSTOMER FROM THE DATABASE
	public void getUserData() {

		CustomerDBQ custDB = new CustomerDBQ(this);
		custDB.customerLogged();

	}

	// METHOD TO SET UP THE FRAME
	public void costumerViewSetup() {

		// LAYOUT FOR MAIN PANEL
		this.custView.setBorder(custView.panel);

		// COLUMNS FOR TABLES
		String[] columnsNam = { "Nro. Ref", "Name", "Surname", "Date", "Time" };
		String[] statusColumnsNam = { "Nro. Ref.", "Name", "Surname", "Date", "Time", "Status", "Comments" };

		// TOP PANEL
		JPanel top = new JPanel();
		this.custView.addLabel("Welcome " + this.custName + " " + this.custSurName + "", top);

		// LEFT PANEL IN MAIN PANEL
		JPanel left = new JPanel();
		this.custView.setBorder(left);

		// LEFT PANEL FOR TOP IN BORDERLAYOUT
		JPanel inLeftTop = new JPanel();
		this.custView.setBox(inLeftTop, 1);
		
		JPanel inLeftTopOne = new JPanel();
		JPanel inLeftTopNameSur = new JPanel();
		JPanel inLeftTopSearch = new JPanel();
		this.custView.addLabel("Name: ", inLeftTopNameSur);
		this.custView.addLabel(this.custName+" "+this.custSurName, inLeftTopNameSur);
		this.custView.addLabel("Personal details", inLeftTopOne);
		this.custView.addLabel("Find appointment by: ", inLeftTopSearch);
		this.option = this.custView.addComboB(searchOp, inLeftTopSearch);
		this.byInput = this.custView.addTextField(10, inLeftTopSearch);
		inLeftTop.add(inLeftTopOne);
		inLeftTop.add(inLeftTopNameSur);
		inLeftTop.add(inLeftTopSearch);
		inLeftTop.setBorder(new EmptyBorder(new Insets(50, 0, 0, 0)));

		// LEFT PANEL - FOR CENTER IN BORDERLAYOUT
		JPanel inLeftCenter = new JPanel();
		this.search = this.custView.addButton("Search", inLeftCenter);
		this.search.setActionCommand("Search");
		this.search.addActionListener(custController);
		inLeftCenter.setBorder(new EmptyBorder(new Insets(15, 0, 0, 0)));

		// LEFT PANEL - FOR BUTTOM IN BORDERLAYOUT
		JPanel inLeftButtom = new JPanel();
		this.logout = this.custView.addButton("Logout", inLeftButtom);
		this.logout.setActionCommand("Logout");
		this.logout.addActionListener(custController);
		inLeftButtom.setBorder(new EmptyBorder(new Insets(0, 0, 100, 0)));

		// ADDING PANELS TO THE LEFT PANEL IN MAIN PANEL
		left.add(inLeftTop, BorderLayout.PAGE_START);
		left.add(inLeftCenter, BorderLayout.CENTER);
		left.add(inLeftButtom, BorderLayout.PAGE_END);

		// CENTER PANEL IN MAIN PANEL
		JPanel center = new JPanel();
		this.custView.setBox(center, 1);

		// PANEL FOR TABLES AND BUTTONS
		JPanel tableCenter = new JPanel();
		this.custView.setBox(tableCenter, 1);
		JPanel buttonCenter = new JPanel();
		JPanel tableStatus = new JPanel();
		center.add(tableCenter);
		center.add(tableStatus);

		// TABLE AVAILABILITIES
		// GETTING DATA FROM THE DATABASE
		CustomerDBQ custDB = new CustomerDBQ(this);
		// FIRST TIME CHECKING, IF TABLES ARE UPDATE, IF CONDITION WILL NOT BE TRUE
		if (this.tableflag) {
			custDB.searchProvider("All", "All");
		}

		// CREATING A TABLE INDEX 0, CALLING METHOD ADDTABLES (VIEW CLASS METHOD)
		this.scrollAvai = this.custView.addTableS(0, this.dataTableAvai, columnsNam, tableCenter, "Availabilities");
		this.scrollAvai.setPreferredSize(new Dimension(400, 150));

		// LIST SELECTION LISTENER FOR TABLE INDEX 0
		this.model = this.custView.myTable[0].getSelectionModel();
		this.model.addListSelectionListener(this.custController);

		// BUTTONS
		tableCenter.add(buttonCenter);
		this.get = custView.addButton("Get Appointment", buttonCenter);
		this.get.setActionCommand("Get Appoint");
		this.get.addActionListener(custController);

		// TABLE APPOINTMENTS
		// GETTING DATA FROM THE DATABASE
		CustomerDBQ custDBAppoint = new CustomerDBQ(this);
		custDBAppoint.searchAppointRecord();

		// CREATING A TABLE INDEX 1, CALLING METHOD ADDTABLES (VIEW CLASS METHOD)
		this.scrollStatus = this.custView.addTableS(1, this.dataTableStatus, statusColumnsNam, tableStatus, "Appointments");
		this.scrollStatus.setPreferredSize(new Dimension(600, 100));
		center.add(tableStatus);

		// LIST SELECTION LISTENER FOR TABLE INDEX 1
		modelTwo = this.custView.myTable[1].getSelectionModel();
		modelTwo.addListSelectionListener(this.custController);

		// BUTTONS
		JPanel delBtn = new JPanel();
		this.cancel = this.custView.addButton("Cancel", delBtn);
		tableStatus.add(delBtn);
		this.cancel.setActionCommand("Cancel");
		this.cancel.addActionListener(custController);

		JPanel comentBtn = new JPanel();
		this.set = this.custView.addButton("Set a feetback/complain", comentBtn);
		tableStatus.add(comentBtn);
		this.set.setActionCommand("Set");
		this.set.addActionListener(custController);

		JPanel showComentBtn = new JPanel();
		this.set = this.custView.addButton("Show comment", showComentBtn);
		tableStatus.add(showComentBtn);
		this.set.setActionCommand("Show");
		this.set.addActionListener(custController);

		// ADDING TO THE MAIN PANEL
		this.custView.panel.add(top, BorderLayout.PAGE_START);
		this.custView.panel.add(left, BorderLayout.LINE_START);
		this.custView.panel.add(center, BorderLayout.CENTER);

		// CALLING VALIDATE AND REPAINT METHODS
		this.custView.validate();
		this.custView.repaint();

	}

	// METHOD TO UPDATE FRAME BY REMOVING AND CALLING THE FRAME SETUP
	public void UpdateFrame(boolean active) {
		custView.panel.removeAll();
		this.tableflag = active;
		costumerViewSetup();
	}

}
