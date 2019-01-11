import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

public class ProviderView extends JFrame {

	// GLOBAL VARIABLES - DECLARATION
	private View proView;
	private ProviderController proController;
	private JButton addManual, addByDay, logout, updatePro;
	private JDateChooser calendarManual, calendarByDay;
	private JComboBox hr;
	private JScrollPane scrollAvaiTable, scrollBookedTable;
	private String[][] dataTableAvai, dataTableBooked;
	private int proID;
	private String proName, proSurName, proMobile, proAddress, proEmail, proLocation;
	private int selectedRow, selectedRowTow;
	private JButton confirm, cancel, setDone, showComment;
	private String[] hrs = { "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
			"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
			"19:00", "19:30" };
	private ListSelectionModel model, modelTwo;

	// CONSTRUCTOR
	public ProviderView(ProviderController proController, String email) {

		// SETTING PROVIDER ACTION LISTENER CONTROLLER
		this.proController = proController;
		this.proEmail = email;
		getUserData(); // METHOD THAT GET THE DATA WITH THE EMAIL PRIVIDED IN LOGIN
		// ARRAYS FOR DATA INSTANTIATION
		this.dataTableAvai = new String[100][3];
		this.dataTableBooked = new String[100][7];
		// NEW INSTANCE OF VIEW FOR PROVIDER VIEW
		this.proView = new View("Provider Manager", 1200, 400, false);
		providerViewSetup();
	}

	public java.sql.Date getDatE() throws ParseException {
		SimpleDateFormat myDateSimp = new SimpleDateFormat("yyy-MM-dd"); // "yyyy-MM-dd"
		String dateStr = myDateSimp.format(calendarManual.getDate());
		java.util.Date getCalendar;
		java.sql.Date dateSql;
		getCalendar = myDateSimp.parse(dateStr);
		dateSql = new java.sql.Date(getCalendar.getTime());
		return dateSql;
	}

	// GETTER AND SETTER FOR GLOBAL VARIABLES
	
	public ListSelectionModel getModel() {
		return this.model;
	}
	
	public ListSelectionModel getModelTwo() {
		return this.modelTwo;
	}	
	
	public View getProView() {
		return this.proView;
	}

	public void setCopyDataAvai(String[][] data) {
		this.dataTableAvai = Arrays.copyOf(data, data.length);
	}

	public void setCopyDataBooked(String[][] data) {
		this.dataTableBooked = Arrays.copyOf(data, data.length);
	}

	public String getDataBooked(int a, int b) {
		return this.dataTableBooked[a][b];
	}

	public String getDataAvai(int a, int b) {
		return this.dataTableAvai[a][b];
	}

	public int getSelectedRowT() {
		return this.selectedRow;
	}
	
	public void setSelectedRowT(int a) {
		this.selectedRow = a;
	}

	public int getSelectedRowTwo() {
		return this.selectedRowTow;
	}
	
	public void setSelectedRowTwo(int a) {
		this.selectedRowTow = a;
	}

	public String getHour() {
		return hrs[hr.getSelectedIndex()];
	}

	public void setProviderID(int proID) {
		this.proID = proID;
	}

	public int getProviderID() {
		return this.proID;
	}

	public void setProviderName(String proNam) {
		this.proName = proNam;
	}

	public String getProviderName() {
		return this.proName;
	}

	public void setProviderSurName(String proSur) {
		this.proSurName = proSur;
	}

	public String getProviderSurName() {
		return this.proSurName;
	}

	public void setProviderLocation(String proLoc) {
		this.proLocation = proLoc;
	}

	public String getProviderLocation() {
		return this.proLocation;
	}

	public String getProviderEmail() {
		return this.proEmail;
	}
	
	public String getProviderAddress() {
		return this.proAddress;
	}
	
	public void setProviderAddress(String proAddr){
		this.proAddress = proAddr;
	}
	
	public String getProviderMobile() {
		return this.proMobile;
	}
	
	public void setProviderMobile(String proMob) {
		this.proMobile = proMob;
	}

	public String getHrs(int i) {
		return this.hrs[i];
	}

	// METHOD TO GET DATA OF THE CUSTOMER FROM THE DATABASE
	public void getUserData() {
		ProviderDBQ proDB = new ProviderDBQ(this);
		proDB.providerLogged();
	}

	// METHOD TO SET UP THE FRAME
	public void providerViewSetup() {

		// LAYOUT FOR MAIN PANEL
		this.proView.setBorder(proView.panel);

		// COLUMNS FOR TABLES
		String[] columnsNamAvai = { "Reference", "Date", "Time" };
		String[] columnsNamAppoint = { "Reference", "Name", "Surname", "Date", "Time", "Status", "Comments" };

		// TOP PANEL
		JPanel top = new JPanel();
		this.proView.addLabel("Welcome " + this.proName + " " + this.proSurName + "", top);

		// LEFT PANEL IN MAIN PANEL
		JPanel left = new JPanel();
		this.proView.setBorder(left);

		// LEFT PANEL FOR TOP IN BORDERLAYOUT
		JPanel inLeftTop = new JPanel();
		this.proView.setGrid(7, 2, inLeftTop);
		
		this.proView.addLabel("User ID:  ", inLeftTop);
		this.proView.addLabel("PROV"+Integer.toString(this.proID), inLeftTop);
		this.proView.addLabel("Name: ", inLeftTop);
		this.proView.addLabel(this.proName+" "+this.proSurName, inLeftTop);
		this.proView.addLabel("Email: ", inLeftTop);
		this.proView.addLabel(this.proEmail, inLeftTop);
		this.proView.addLabel("Mobile: ", inLeftTop);
		this.proView.addLabel(this.proMobile, inLeftTop);
		this.proView.addLabel("Address: ", inLeftTop);
		this.proView.addLabel(this.proAddress, inLeftTop);
		this.proView.addLabel("Location: ", inLeftTop);
		this.proView.addLabel(this.proLocation, inLeftTop);
		this.proView.addLabel("", inLeftTop);
		this.updatePro = this.proView.addButton("Edit", inLeftTop);
		this.updatePro.setPreferredSize(new Dimension(0,20));
		this.updatePro.setActionCommand("Edit");
		this.updatePro.addActionListener(this.proController);
		inLeftTop.setBorder(new EmptyBorder(new Insets(10, 25, 0, 25)));
		
		// LEFT PANEL - FOR CENTER IN BORDERLAYOUT
		JPanel inLeftCenterMain = new JPanel();
		this.proView.setBox(inLeftCenterMain, 1);
		inLeftCenterMain.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Add Availabilities",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		//inLeftCenterMain.setBorder(new EmptyBorder(new Insets(25, 0, 0, 0)));
		
		//TO ADD AVAILABILITY MANUALLY
		JPanel inLeftCenterManual = new JPanel();
		this.proView.addLabel("Add manually: ", inLeftCenterManual);
		// CREATING A CALENDAR AND GIVING FORMAT YYY-MM-DD
		this.calendarManual = this.proView.addCalen(inLeftCenterManual);
		//SETTING CALENDAR RANGE 
		Calendar calMin = Calendar.getInstance();
		this.calendarManual.setMinSelectableDate(calMin.getTime());
		Calendar calMax = Calendar.getInstance();
		calMax.add(Calendar.DATE, 90); //ADD 30 DAYS FROM CURRENT (TODAY)
		this.calendarManual.setMaxSelectableDate(calMax.getTime());
		this.hr = this.proView.addComboB(hrs, inLeftCenterManual);
		this.addManual = this.proView.addButton("Add", inLeftCenterManual);
		this.addManual.setActionCommand("Add");
		this.addManual.addActionListener(proController);
		
		//TO ADD AVAILABILITY FOR FULL DAY
		JPanel inLeftCenterByDay = new JPanel();
		this.proView.addLabel("Add ALL DAY - Available   ", inLeftCenterByDay);
		this.calendarByDay = this.proView.addCalen(inLeftCenterByDay);
		//SETTING CALENDAR RANGE 
		//Calendar calMin = Calendar.getInstance();
		this.calendarByDay.setMinSelectableDate(calMin.getTime());
		//Calendar calMax = Calendar.getInstance();
		calMax.add(Calendar.DATE, 90); //ADD 30 DAYS FROM CURRENT (TODAY)
		this.calendarByDay.setMaxSelectableDate(calMax.getTime());
		this.addByDay = this.proView.addButton("Add", inLeftCenterByDay);
		this.addByDay.setActionCommand("Add All");
		this.addByDay.addActionListener(proController);
		inLeftCenterMain.add(inLeftCenterManual);
		inLeftCenterMain.add(inLeftCenterByDay);

		// LEFT PANEL - FOR BUTTOM IN BORDERLAYOUT
		JPanel inLeftButtom = new JPanel();
		this.logout = this.proView.addButton("Logout", inLeftButtom);
		this.logout.setActionCommand("Logout");
		this.logout.addActionListener(proController);
		inLeftButtom.setBorder(new EmptyBorder(new Insets(0, 0, 25, 0)));

		// ADDING PANELS TO THE LEFT PANEL IN MAIN PANEL
		left.add(inLeftTop, BorderLayout.PAGE_START);
		left.add(inLeftCenterMain, BorderLayout.CENTER);
		left.add(inLeftButtom, BorderLayout.PAGE_END);

		// CENTER PANEL IN MAIN PANEL
		JPanel center = new JPanel();
		this.proView.setBox(center, 1);

		// TABLE MY AVAILABILITIES
		// GETTING DATA FROM THE DATABASE
		ProviderDBQ proDB = new ProviderDBQ(this);
		proDB.availableProvTable();

		// CREATING A TABLE INDEX 0, CALLING METHOD ADDTABLES (VIEW CLASS METHOD)
		this.scrollAvaiTable = this.proView.addTableS(0, this.dataTableAvai, columnsNamAvai, center, "My Availabilites");
		this.scrollAvaiTable.setPreferredSize(new Dimension(300, 250));

		// LIST SELECTION LISTENER FOR TABLE INDEX 0
		this.modelTwo = this.proView.myTable[0].getSelectionModel();
		this.modelTwo.addListSelectionListener(this.proController);

		// BUTTONS
		JPanel delAvai = new JPanel();
		this.confirm = this.proView.addButton("Delete", delAvai);
		center.add(delAvai);
		this.confirm.setActionCommand("Delete");
		this.confirm.addActionListener(proController);

		// TABLE APPOINTMENTS
		// PANEL FOR TABLES AND BUTTONS
		JPanel rightTable = new JPanel();
		this.proView.setBox(rightTable, 1);

		// GETTING DATA FROM THE DATABASE
		ProviderDBQ proDBbooked = new ProviderDBQ(this);
		proDBbooked.toBeConfirmPro();

		// CREATING A TABLE INDEX 1, CALLING METHOD ADDTABLES (VIEW CLASS METHOD)
		this.scrollBookedTable = this.proView.addTableS(1, this.dataTableBooked, columnsNamAppoint, rightTable, "Appointments");
		this.scrollBookedTable.setPreferredSize(new Dimension(250, 250));

		// LIST SELECTION LISTENER FOR TABLE INDEX 1
		this.model = this.proView.myTable[1].getSelectionModel();
		this.model.addListSelectionListener(this.proController);

		// BUTTONS
		JPanel appointBtns = new JPanel();
		this.confirm = this.proView.addButton("Confirm", appointBtns);
		rightTable.add(appointBtns);
		this.confirm.setActionCommand("Confirm");
		this.confirm.addActionListener(proController);

		this.cancel = this.proView.addButton("Cancel", appointBtns);
		this.cancel.setActionCommand("Cancel");
		this.cancel.addActionListener(proController);

		this.setDone = this.proView.addButton("Set Completed", appointBtns);
		this.setDone.setActionCommand("Completed");
		this.setDone.addActionListener(proController);

		this.showComment = this.proView.addButton("Show comment", appointBtns);
		this.showComment.setActionCommand("Show");
		this.showComment.addActionListener(proController);

		// ADDING TO THE MAIN PANEL
		this.proView.panel.add(top, BorderLayout.PAGE_START);
		this.proView.panel.add(left, BorderLayout.LINE_START);
		this.proView.panel.add(rightTable, BorderLayout.CENTER);
		this.proView.panel.add(center, BorderLayout.EAST);

		// CALLING VALIDATE AND REPAINT METHODS
		this.proView.validate();
		this.proView.repaint();

	}

	// METHOD TO UPDATE FRAME BY REMOVING AND CALLING THE FRAME SETUP
	public void UpdateFrame() {
		proView.panel.removeAll();
		providerViewSetup();
	}

}
