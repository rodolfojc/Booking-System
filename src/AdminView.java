import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AdminView extends JFrame{

	View adminView;
	Controller controller;
	private String [][] dataCust, dataPro, dataAvai, dataAppoint, dataAdmin;
	private JScrollPane srCust, srPro, srAvai, srAppoint, srAdmin;
	
	public AdminView() {
		
		this.adminView = new View("Administrators Manager", 800, 800, true);
		AdmindViewSetup();
		
	}
	
	public void AdmindViewSetup() {
		
		String[] custColumns = {"ID", "Name", "Surname", "Mobile", "Email", "Address"};
		String[] proColumns = {"ID", "Name", "Surname", "Mobile", "Email", "Address", "Location", "Status", "Registered on" };
		String[] avaiColumns = {"Reference", "Providers", "Date", "Time", "Available"};
		String[] appoitColumns = {"Reference", "Availability Reference", "Customer ID", "Comments"};
		String[] adminColumns = {"Admin ID", "Admin User", "Admin Privilege"};
		this.dataCust = new String[30][6];
		this.dataPro = new String[30][9];
		this.dataAvai = new String[30][5];
		this.dataAppoint = new String[30][4];
		this.dataAdmin = new String[10][3];
		
		
		this.adminView.setGrid(2, 6, this.adminView.panel);
		
		JPanel cust = new JPanel();
		this.adminView.addTableS(0, this.dataCust, custColumns, cust, "Customers");
		
		JPanel prov = new JPanel();
		this.adminView.addTableS(1, this.dataPro, proColumns, prov, "Providers");
		
		
		JPanel avail = new JPanel();
		this.adminView.addTableS(1, this.dataAvai, avaiColumns, avail, "Availabilities");
		
		
		JPanel appoints = new JPanel();
		this.adminView.addTableS(1, this.dataAppoint, appoitColumns, appoints, "Appointments");
		
		
		JPanel admins = new JPanel();
		this.adminView.addTableS(1, this.dataAdmin, adminColumns, admins, "Administrators");
		
			
		JPanel comments = new JPanel();
		
		this.adminView.panel.add(cust);
		this.adminView.panel.add(prov);
		this.adminView.panel.add(avail);
		this.adminView.panel.add(appoints);
		this.adminView.panel.add(admins);
		this.adminView.panel.add(comments);
		
		
		this.adminView.validate();
		this.adminView.repaint();
		
		
	}
	
	
	
	
	
	
}