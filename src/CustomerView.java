import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class CustomerView extends JFrame {

	// GLOBAL VARIABLES - DECLARATION
	private View custView;
	private JComboBox option;
	private JButton search, searchByDay, get, logout, cancel, set, updatePro;
	private String custName, custSurName, custEmail, custAddress, custMobile;
	private String[][] dataTableAvai, dataTableStatus;
	private JScrollPane scrollAvai, scrollStatus;
	private JTextField byInput;
	private String[] searchOp = { "Name", "Location" };
	private int selectedRow, selectedRowTwo, customerID;
	private boolean tableflag = true;
	private CustomerController custController;
	private JDateChooser calendarByDay;
	private ListSelectionModel model, modelTwo;

	// CONSTRUCTOR
	public CustomerView(CustomerController CustController, String email) {

		// SETTING CUSTOMER ACTION LISTENER CONTROLLER
		this.custController = CustController;
		this.custEmail = email;
		getUserData(); // METHOD THAT GET THE DATA WITH THE EMAIL PRIVIDED IN LOGIN
		// ARRAYS FOR DATA INSTANTIATION
		this.dataTableAvai = new String[1000][5];
		this.dataTableStatus = new String[1000][7];
		// NEW INSTANCE OF VIEW FOR CUSTOMER VIEW
		this.custView = new View("Customer Manager", 1100, 550, true);
		costumerViewSetup();
	}
	
	// GETTER AND SETTER FOR GLOBAL VARIABLES
	
	public java.sql.Date getDateByDay() throws ParseException {
		SimpleDateFormat myDateSimp = new SimpleDateFormat("yyy-MM-dd"); // "yyyy-MM-dd"
		String dateStr = myDateSimp.format(calendarByDay.getDate());
		java.util.Date getCalendar;
		java.sql.Date dateSql;
		getCalendar = myDateSimp.parse(dateStr);
		dateSql = new java.sql.Date(getCalendar.getTime());
		return dateSql;
	}
	
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
	
	public void setCustomerEmail (String email) {
		this.custEmail = email;
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
	
	public String getCustomerAddress() {
		return this.custAddress;
	}
	
	public void setCustomerMobile(String custMob) {
		this.custMobile = custMob;
	}
	
	public String getCustomerMobile() {
		return this.custMobile;
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
		this.custView.setGrid(6, 2, inLeftTop);
				
		this.custView.addLabel("User ID:  ", inLeftTop);
		this.custView.addLabel("CUST"+Integer.toString(this.customerID), inLeftTop);
		this.custView.addLabel("Name: ", inLeftTop);
		this.custView.addLabel(this.custName+" "+this.custSurName, inLeftTop);
		this.custView.addLabel("Email: ", inLeftTop);
		this.custView.addLabel(this.custEmail, inLeftTop);
		this.custView.addLabel("Address: ", inLeftTop);
		this.custView.addLabel(this.custAddress, inLeftTop);
		this.custView.addLabel("Mobile: ", inLeftTop);
		this.custView.addLabel(this.custMobile, inLeftTop);
		this.custView.addLabel("", inLeftTop);
		this.updatePro = this.custView.addButton("Edit", inLeftTop);
		this.updatePro.setPreferredSize(new Dimension(0,20));
		this.updatePro.setActionCommand("Edit");
		this.updatePro.addActionListener(this.custController);
		inLeftTop.setBorder(new EmptyBorder(new Insets(10, 25, 0, 25)));

		// LEFT PANEL - FOR CENTER IN BORDERLAYOUT
		JPanel inLeftCenter = new JPanel();
		this.custView.setBox(inLeftCenter, 1);
		JPanel inLeftCenterSearch = new JPanel();
		//JPanel inLeftCenterBtn = new JPanel();
		JPanel inLeftCenterByDay = new JPanel();
		
		
		this.custView.addLabel("Find appointment by: ", inLeftCenterSearch);
		this.option = this.custView.addComboB(searchOp, inLeftCenterSearch);
		this.byInput = this.custView.addTextField(10, inLeftCenterSearch);
		this.search = this.custView.addButton("Search", inLeftCenterSearch);
		this.search.setActionCommand("Search");
		this.search.addActionListener(custController);
		
		this.custView.addLabel("Find appointment by day: ", inLeftCenterByDay);
		this.calendarByDay = this.custView.addCalen(inLeftCenterByDay);
		Calendar calMin = Calendar.getInstance();
		this.calendarByDay.setMinSelectableDate(calMin.getTime());
		Calendar calMax = Calendar.getInstance();
		calMax.add(Calendar.DATE, 90); //ADD 30 DAYS FROM CURRENT (TODAY)
		this.calendarByDay.setMaxSelectableDate(calMax.getTime());
		this.searchByDay = this.custView.addButton("Search", inLeftCenterByDay);
		this.searchByDay.setActionCommand("Search ByDay");
		this.searchByDay.addActionListener(custController);
		this.custView.addLabel("                        ", inLeftCenterByDay);
		
		inLeftCenter.add(inLeftCenterSearch);
		inLeftCenter.add(inLeftCenterByDay);
		//inLeftCenter.add(inLeftCenterBtn);
		//inLeftCenterBtn.setBorder(new EmptyBorder(new Insets(0, 0, 100, 0)));
		inLeftCenter.setBorder(new EmptyBorder(new Insets(25, 20, 0, 0)));

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
		this.modelTwo = this.custView.myTable[1].getSelectionModel();
		this.modelTwo.addListSelectionListener(this.custController);

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
