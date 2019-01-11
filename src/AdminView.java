import java.awt.Dimension;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class AdminView extends JFrame {

	// GLOBAL VARIABLES - DECLARATION
	private View adminView;
	private AdminController adminController;
	private String adminUser;
	private String[][] dataCust, dataPro, dataAvai, dataAppoint, dataAdmin;
	private JScrollPane srCust, srPro, srAvai, srAppoint, srAdmin;
	private JButton custDel, proDele, proValid, avaiDele, appointDele, appointManage, adminAdd, adminDele, logout,
			updateTables, showComment;
	private int selectedRowCust, selectedRowPro, selectedRowAvai, selectedRowAppoint, selectedRowAdmin;
	private ListSelectionModel modelCust, modelPro, modelAvai, modelAppoint, modelAdmin;

	// CONSTRUCTOR
	public AdminView(AdminController AdminController, String adminUser) {

		// SETTING ADMINISTRATORS ACTION LISTENER CONTROLLER
		this.adminController = AdminController;
		this.adminUser = adminUser;
		// ARRAYS FOR DATA INSTANTIATION
		this.dataCust = new String[500][3];
		this.dataPro = new String[500][5];
		this.dataAvai = new String[1000][5];
		this.dataAppoint = new String[1000][4];
		this.dataAdmin = new String[30][3];
		// NEW INSTANCE OF VIEW FOR ADMINISTRATOR VIEW
		this.adminView = new View("Administrators Manager", 1300, 800, false);
		AdmindViewSetup();

	}

	// GETTER AND SETTER FOR GLOBAL VARIABLES
	
	public ListSelectionModel getModelAdmin() {
		return this.modelAdmin;
	}
	
	public ListSelectionModel getModelAppoint() {
		return this.modelAppoint;
	}
	
	public ListSelectionModel getModelAvai() {
		return this.modelAvai;
	}
	
	public ListSelectionModel getModelPro() {
		return this.modelPro;
	}
	
	public ListSelectionModel getModelCust() {
		return this.modelCust;
	}	
	
	public View getAdminView() {
		return this.adminView;
	}

	public void setCopyDataCust(String[][] data) {
		this.dataCust = Arrays.copyOf(data, data.length);
	}

	public String getDataCust(int a, int b) {
		return this.dataCust[a][b];
	}

	public void setCopyDataPro(String[][] data) {
		this.dataPro = Arrays.copyOf(data, data.length);
	}

	public String getDataPro(int a, int b) {
		return this.dataPro[a][b];
	}

	public void setCopyDataAvai(String[][] data) {
		this.dataAvai = Arrays.copyOf(data, data.length);
	}

	public String getDataAvai(int a, int b) {
		return this.dataAvai[a][b];
	}

	public void setCopyDataAppoint(String[][] data) {
		this.dataAppoint = Arrays.copyOf(data, data.length);
	}

	public String getDataAppoint(int a, int b) {
		return this.dataAppoint[a][b];
	}

	public void setCopyDataAdmin(String[][] data) {
		this.dataAdmin = Arrays.copyOf(data, data.length);
	}

	public String getDataAdmin(int a, int b) {
		return this.dataAdmin[a][b];
	}

	public int getSelectedRowCust() {
		return this.selectedRowCust;
	}
	
	public void setSelectedRowCust(int a) {
		this.selectedRowCust = a;
	}

	public int getSelectedRowPro() {
		return this.selectedRowPro;
	}
	
	public void setSelectedRowPro(int a) {
		this.selectedRowPro = a;
	}

	public int getSelectedRowAvai() {
		return this.selectedRowAvai;
	}
	
	public void setSelectedRowAvai(int a) {
		this.selectedRowAvai = a;
	}

	public int getSelectedRowAppoint() {
		return this.selectedRowAppoint;
	}
	
	public void setSelectedRowAppoint(int a) {
		this.selectedRowAppoint = a;
	}

	public int getSelectedRowAdmin() {
		return this.selectedRowAdmin;
	}
	
	public void setSelectedRowAdmin(int a) {
		this.selectedRowAdmin = a;
	}

	// METHOD TO SET UP THE FRAME
	public void AdmindViewSetup() {

		// LAYOUT FOR MAIN PANEL
		this.adminView.setGrid(2, 6, this.adminView.panel);

		// COLUMNS FOR TABLES
		String[] custColumns = { "ID", "Name", "Surname" };
		String[] proColumns = { "ID", "Name", "Surname", "Status", "Registered" };
		String[] avaiColumns = { "Reference", "Providers ID", "Date", "Time", "Available" };
		String[] appoitColumns = { "Reference", "Availability Reference", "Customer ID", "Comments" };
		String[] adminColumns = { "Admin ID", "Admin User", "Admin Privilege" };

		// CUSTOMER PANEL - TABLE
		JPanel cust = new JPanel();

		// GETTING DATA FROM THE DATABASE
		AdminDBQ dataCustConn = new AdminDBQ(this);
		dataCustConn.getCustomer();

		// CREATING A TABLE INDEX 0, CALLING METHOD ADDTABLES (VIEW CLASS METHOD)
		this.srCust = this.adminView.addTableS(0, this.dataCust, custColumns, cust, "Customers");
		this.srCust.setPreferredSize(new Dimension(350, 300));

		// LIST SELECTION LISTENER FOR TABLE INDEX 0
		this.modelCust = adminView.myTable[0].getSelectionModel();
		this.modelCust.addListSelectionListener(this.adminController);
		
		// BUTTONS
		this.custDel = this.adminView.addButton("Delete", cust);
		this.custDel.setActionCommand("Delete Customer");
		this.custDel.addActionListener(adminController);

		// PROVIDER PANEL - TABLE
		JPanel prov = new JPanel();

		// GETTING DATA FROM THE DATABASE
		AdminDBQ dataProConn = new AdminDBQ(this);
		dataProConn.getProviders();

		// CREATING A TABLE INDEX 1, CALLING METHOD ADDTABLES (VIEW CLASS METHOD)
		this.srPro = this.adminView.addTableS(1, this.dataPro, proColumns, prov, "Providers");
		this.srPro.setPreferredSize(new Dimension(400, 300));

		// LIST SELECTION LISTENER FOR TABLE INDEX 1
		this.modelPro = adminView.myTable[1].getSelectionModel();
		this.modelPro.addListSelectionListener(this.adminController);

		// BUTTONS
		this.proDele = this.adminView.addButton("Delete", prov);
		this.proDele.setActionCommand("Delete");
		this.proDele.addActionListener(adminController);

		this.proValid = this.adminView.addButton("Validate", prov);
		this.proValid.setActionCommand("Validate Provider");
		this.proValid.addActionListener(adminController);

		// AVAILABILITIES PANEL - TABLE
		JPanel avail = new JPanel();

		// GETTING DATA FROM THE DATABASE
		AdminDBQ dataAvaiConn = new AdminDBQ(this);
		dataAvaiConn.getAvailabilities();

		// CREATING A TABLE INDEX 2, CALLING METHOD ADDTABLES (VIEW CLASS METHOD)
		this.srAvai = this.adminView.addTableS(2, this.dataAvai, avaiColumns, avail, "Availabilities");
		this.srAvai.setPreferredSize(new Dimension(400, 300));

		// LIST SELECTION LISTENER FOR TABLE INDEX 2
		this.modelAvai = adminView.myTable[2].getSelectionModel();
		this.modelAvai.addListSelectionListener(this.adminController);
		
		// BUTTONS
		this.avaiDele = this.adminView.addButton("Delete", avail);
		this.avaiDele.setActionCommand("Delete Availability");
		this.avaiDele.addActionListener(adminController);

		// APPOINTMENTS PANEL - TABLE
		JPanel appoints = new JPanel();

		// GETTING DATA FROM THE DATABASE
		AdminDBQ dataAppointConn = new AdminDBQ(this);
		dataAppointConn.getAppointments();

		// CREATING A TABLE INDEX 3, CALLING METHOD ADDTABLES (VIEW CLASS METHOD)
		this.srAppoint = this.adminView.addTableS(3, this.dataAppoint, appoitColumns, appoints, "Appointments");
		this.srAppoint.setPreferredSize(new Dimension(350, 300));

		// LIST SELECTION LISTENER FOR TABLE INDEX 3
		this.modelAppoint = adminView.myTable[3].getSelectionModel();
		this.modelAppoint.addListSelectionListener(this.adminController);

		// BUTTONS
		this.appointDele = this.adminView.addButton("Delete", appoints);
		this.appointDele.setActionCommand("Delete Appointment");
		this.appointDele.addActionListener(adminController);

		this.appointManage = this.adminView.addButton("Manage Comments", appoints);
		this.appointManage.setActionCommand("Manage");
		this.appointManage.addActionListener(adminController);

		this.showComment = this.adminView.addButton("Show Comment", appoints);
		this.showComment.setActionCommand("Show");
		this.showComment.addActionListener(adminController);

		// ADMINISTRATORS PANEL - TABLE
		JPanel admins = new JPanel();

		// GETTING DATA FROM THE DATABASE
		AdminDBQ dataAdminConn = new AdminDBQ(this);
		dataAdminConn.getAdministrators();

		// CREATING A TABLE INDEX 4, CALLING METHOD ADDTABLES (VIEW CLASS METHOD)
		this.srAdmin = this.adminView.addTableS(4, this.dataAdmin, adminColumns, admins, "Administrators");
		this.srAdmin.setPreferredSize(new Dimension(380, 300));

		// LIST SELECTION LISTENER FOR TABLE INDEX 4
		this.modelAdmin = adminView.myTable[4].getSelectionModel();
		this.modelAdmin.addListSelectionListener(this.adminController);
		
		// BUTTONS
		// IF ADMIN@ADMIN.ADMIN IS LOGIN THEN THE OPTION IS AVAILABLE
		this.adminAdd = this.adminView.addButton("Add", admins);
		if (!this.adminUser.equals("admin@admin.admin")) {
			this.adminAdd.setEnabled(false);
		}
		this.adminAdd.setActionCommand("Add Admin");
		this.adminAdd.addActionListener(adminController);

		// IF ADMIN@ADMIN.ADMIN IS LOGIN THEN THE OPTION IS AVAILABLE
		this.adminDele = this.adminView.addButton("Delete", admins);
		if (!this.adminUser.equals("admin@admin.admin")) {
			this.adminDele.setEnabled(false);
		}
		this.adminDele.setActionCommand("Delete Admin");
		this.adminDele.addActionListener(adminController);

		// CONTROL PANE
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new EmptyBorder(new Insets(150, 0, 0, 0)));

		// BUTTONS
		this.adminView.addLabel("Admin: " + this.adminUser + "", controlPanel);
		this.updateTables = this.adminView.addButton("Update Tables", controlPanel);
		this.updateTables.setActionCommand("Update Tables");
		this.updateTables.addActionListener(adminController);

		this.logout = this.adminView.addButton("Logout", controlPanel);
		this.logout.setActionCommand("Logout");
		this.logout.addActionListener(adminController);

		// ADDING TO THE MAIN PANEL
		this.adminView.panel.add(cust);
		this.adminView.panel.add(prov);
		this.adminView.panel.add(avail);
		this.adminView.panel.add(appoints);
		this.adminView.panel.add(admins);
		this.adminView.panel.add(controlPanel);

		// CALLING VALIDATE AND REPAINT METHODS
		this.adminView.validate();
		this.adminView.repaint();

	}

	// METHOD TO UPDATE FRAME BY REMOVING AND CALLING THE FRAME SETUP
	public void UpdateFrame() {
		adminView.panel.removeAll();
		AdmindViewSetup();
	}

}