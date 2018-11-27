import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AdminView extends JFrame{

	View adminView;
	AdminController adminController;
	
	private String [][] dataCust, dataPro, dataAvai, dataAppoint, dataAdmin;
	private JScrollPane srCust, srPro, srAvai, srAppoint, srAdmin;
	private JButton custDel, proDele, proValid, avaiDele, appointDele, adminAdd, adminDele;
	private int selectedRowCust, selectedRowPro, selectedRowAvai, selectedRowAppoint, selectedRowAdmin;
	
	public AdminView(AdminController AdminController) {
		
		this.adminController = AdminController;
		this.adminView = new View("Administrators Manager", 1300, 800, true);
		AdmindViewSetup();
		
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
	
	public int getSelectedRowPro() {
		return this.selectedRowPro;
	}
	
	
	public void AdmindViewSetup() {
		
		
		String[] proColumns = {"ID", "Name", "Surname","Status", "Registered" };
		String[] avaiColumns = {"Reference", "Providers ID", "Date", "Time", "Available"};
		String[] appoitColumns = {"Reference", "Availability Reference", "Customer ID", "Comments"};
		String[] adminColumns = {"Admin ID", "Admin User", "Admin Privilege"};
		this.dataCust = new String[30][3];
		this.dataPro = new String[30][5];
		this.dataAvai = new String[30][5];
		this.dataAppoint = new String[30][4];
		this.dataAdmin = new String[30][3];
		
		
		this.adminView.setGrid(2, 6, this.adminView.panel);
		
		///////////////////////////////////////////////////////////////////////////////////////////
		JPanel cust = new JPanel();
		String[] custColumns = {"ID", "Name", "Surname"};
		AdminDBQ dataCustConn = new AdminDBQ(this);
		dataCustConn.getCustomer();
		this.srCust= this.adminView.addTableS(0, this.dataCust, custColumns, cust, "Customers");
		this.srCust.setPreferredSize(new Dimension(350,300));
		ListSelectionModel modelCust = adminView.myTable[0].getSelectionModel();
		modelCust.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!modelCust.isSelectionEmpty()) {
					selectedRowCust = modelCust.getMinSelectionIndex();
					JOptionPane.showMessageDialog(adminView, "Customer selected: ID "+dataCust[selectedRowCust][0]+", "
							+ ""+dataCust[selectedRowCust][1]+" "
									+ ""+dataCust[selectedRowCust][2]+""
											+ ". If you want to delete it, press DELETE!");
											
				}
			}
			
		});
		
		this.custDel = this.adminView.addButton("Delete", cust);
		this.custDel.setActionCommand("Delete Customer");
		this.custDel.addActionListener(adminController);
		
		
		///////////////////////////////////////////////////////////////////////////////////////////
		JPanel prov = new JPanel();
		AdminDBQ dataProConn = new AdminDBQ(this);
		dataProConn.getProviders();
		this.srPro = this.adminView.addTableS(1, this.dataPro, proColumns, prov, "Providers");
		this.srPro.setPreferredSize(new Dimension(400,300));
		ListSelectionModel modelPro = adminView.myTable[1].getSelectionModel();
		modelPro.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!modelPro.isSelectionEmpty()) {
					selectedRowPro = modelPro.getMinSelectionIndex();
					JOptionPane.showMessageDialog(adminView, "Provider selected: ID "+dataPro[selectedRowPro][0]+", "
							+ ""+dataPro[selectedRowPro][1]+" "
									+ ""+dataPro[selectedRowPro][2]+""
											+ ". If you want to delete it, press DELETE. "
												+ "If you want to validate it, press VALIDATE!");
											
				}
			}
			
		});
		
		this.proDele = this.adminView.addButton("Delete", prov);
		this.proDele.setActionCommand("Delete");
		this.proDele.addActionListener(adminController);
		
		this.proValid = this.adminView.addButton("Validate", prov);
		this.proValid.setActionCommand("Validate Provider");
		this.proValid.addActionListener(adminController);
		
		
		///////////////////////////////////////////////////////////////////////////////////////////
		JPanel avail = new JPanel();
		AdminDBQ dataAvaiConn = new AdminDBQ(this);
		dataAvaiConn.getAvailabilities();
		this.srAvai = this.adminView.addTableS(2, this.dataAvai, avaiColumns, avail, "Availabilities");
		this.srAvai.setPreferredSize(new Dimension(400,300));
		ListSelectionModel modelAvai = adminView.myTable[2].getSelectionModel();
		modelAvai.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!modelAvai.isSelectionEmpty()) {
					selectedRowAvai = modelAvai.getMinSelectionIndex();
					JOptionPane.showMessageDialog(adminView, "Availability selected: ID "+dataAvai[selectedRowAvai][0]+", "
							+ "Provider ID "+dataAvai[selectedRowAvai][1]+", "
									+ "On "+dataAvai[selectedRowAvai][2]+""
											+ ". If you want to delete it, press DELETE!");
											
				}
			}
			
		});
		
		this.avaiDele = this.adminView.addButton("Delete", avail);
		this.avaiDele.setActionCommand("Delete Availability");
		this.avaiDele.addActionListener(adminController);
		
		///////////////////////////////////////////////////////////////////////////////////////////
		JPanel appoints = new JPanel();
		AdminDBQ dataAppointConn = new AdminDBQ(this);
		dataAppointConn.getAppointments();
		this.srAppoint = this.adminView.addTableS(3, this.dataAppoint, appoitColumns, appoints, "Appointments");
		this.srAppoint.setPreferredSize(new Dimension(350,300));
		ListSelectionModel modelAppoint = adminView.myTable[3].getSelectionModel();
		modelAppoint.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!modelAppoint.isSelectionEmpty()) {
					selectedRowAppoint = modelAppoint.getMinSelectionIndex();
					JOptionPane.showMessageDialog(adminView, "Appointment selected: Ref "+dataAppoint[selectedRowAppoint][0]+", "
							+ "Availability Ref "+dataAppoint[selectedRowAppoint][1]+", "
									+ "Customer ID "+dataAppoint[selectedRowAppoint][2]+""
											+ ". If you want to delete it, press DELETE!");
											
				}
			}
			
		});
		
		
		
		this.appointDele = this.adminView.addButton("Delete", appoints);
		this.appointDele.setActionCommand("Delete Appointment");
		this.appointDele.addActionListener(adminController);
		
		///////////////////////////////////////////////////////////////////////////////////////////
		JPanel admins = new JPanel();
		AdminDBQ dataAdminConn = new AdminDBQ(this);
		dataAdminConn.getAdministrators();
		this.srAdmin = this.adminView.addTableS(4, this.dataAdmin, adminColumns, admins, "Administrators");
		this.srAdmin.setPreferredSize(new Dimension(350,300));
		ListSelectionModel modelAdmin = adminView.myTable[4].getSelectionModel();
		modelAdmin.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!modelAdmin.isSelectionEmpty()) {
					selectedRowAdmin = modelAdmin.getMinSelectionIndex();
					JOptionPane.showMessageDialog(adminView, "Administrador selected: ID "+dataAdmin[selectedRowAdmin][0]+", "
							+ "Admin user "+dataAdmin[selectedRowAdmin][1]+", "
									+ "Privilege "+dataAdmin[selectedRowAdmin][2]+""
											+ ". If you want to delete it, press DELETE!");
											
				}
			}
			
		});
		
		
		this.adminAdd = this.adminView.addButton("Add", admins);
		this.adminAdd.setActionCommand("Add Admin");
		this.adminAdd.addActionListener(adminController);
		
		this.adminDele = this.adminView.addButton("Delete", admins);
		this.adminDele.setActionCommand("Delete Admin");
		this.adminDele.addActionListener(adminController);
		
		///////////////////////////////////////////////////////////////////////////////////////////
		JPanel comments = new JPanel();
		//scroll.setPreferredSize(new Dimension(400,250));
		
		this.adminView.panel.add(cust);
		this.adminView.panel.add(prov);
		this.adminView.panel.add(avail);
		this.adminView.panel.add(appoints);
		this.adminView.panel.add(admins);
		this.adminView.panel.add(comments);
		
		
		this.adminView.validate();
		this.adminView.repaint();
		
		
	}
	
	public void UpdateFrame() {
		adminView.panel.removeAll();
		AdmindViewSetup();
	}
	
	
	
	
	
	
}