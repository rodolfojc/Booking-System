import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

public class ProviderView extends JFrame {
	
	private View proView;
	private Database forUserData;
	private Controller controller;
	private ProviderController proController;
	private JButton add, logout;
	private JDateChooser calendar;
	private JComboBox hr;
	private JTable table;
	private JScrollPane scrollAvaiTable, scrollBookedTable;
	private String[][] dataTableAvai, dataTableBooked;
	private int proID;
	private String proName, proSurName, proEmail, proLocation;
	private int selectedRow, selectedRowTow;
	private JButton confirm, cancel, setDone;
	private String [] hrs = {"8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
			"11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
			"14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
			"17:00", "17:30", "18:00", "18:30", "19:00", "19:30"};
	
	/*public ProviderView (Controller controller, String email) {
		
		this.controller = controller;
		this.proEmail = email;
		getUserData(this.proEmail);
		this.proView = new View("Provider Manager", 1200, 600, true);
		providerViewSetup();
	}*/
	
	public ProviderView (ProviderController proController, String email) {
		
		this.proController = proController;
		this.proEmail = email;
		getUserData(this.proEmail);
		this.proView = new View("Provider Manager", 1200, 400, true);
		providerViewSetup();
	}
	
	public java.sql.Date getDatE() throws ParseException {
		SimpleDateFormat myDateSimp = new SimpleDateFormat("yyy-MM-dd"); //"yyyy-MM-dd"
		String dateStr = myDateSimp.format(calendar.getDate());
		java.util.Date getCalendar;
		java.sql.Date dateSql;
		getCalendar = myDateSimp.parse(dateStr);
		dateSql = new java.sql.Date(getCalendar.getTime());
		return dateSql;		
		
	}
	
	public View getProView () {
		return this.proView;
	}
	
	public void setCopyDataAvai(String[][] data) {
		this.dataTableAvai = Arrays.copyOf(data, data.length);
	}
	
	public void setCopyDataBooked(String[][] data) {
		this.dataTableBooked = Arrays.copyOf(data, data.length);
	}
	
	public String getDataBooked (int a, int b) {
		return this.dataTableBooked[a][b];
	}
	
	public String getDataAvai (int a, int b) {
		return this.dataTableAvai[a][b];
	}
	
	public int getSelectedRowT() {
		return this.selectedRow;
	}	
	
	public int getSelectedRowTwo() {
		return this.selectedRowTow;
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
	
	public String getHrs(int i) {
		return this.hrs[i];
	}
	
	public void getUserData(String email) {
		ProviderDBQ proDB = new ProviderDBQ(this);
		proDB.providerLogged();
	}
		
	public void providerViewSetup() {

		String[] columnsNamAvai = {"Reference", "Date", "Time"};
		String[] columnsNamAppoint = {"Reference", "Name", "Surname", "Date", "Time", "Status"};
		
		
		dataTableAvai = new String[40][2];
		dataTableBooked = new String[40][4];
		
		proView.setBorder(proView.panel);
		
		JPanel top = new JPanel();
		proView.addLabel("Welcome "+this.proName+" "+this.proSurName+"", top);
	
		JPanel left = new JPanel();
		proView.setBorder(left);
		
		JPanel inleftTop = new JPanel();
		proView.addLabel("Add Availability: ", inleftTop);
		calendar = proView.addCalen(inleftTop);
		java.util.Date now = new java.util.Date();
		SimpleDateFormat myDateSimp = new SimpleDateFormat("yyy-MM-dd"); //"yyyy-MM-dd"
		myDateSimp.format(now);
		calendar.setMinSelectableDate(now);
		hr = proView.addComboB(hrs, inleftTop);
		
		
		
		JPanel inLeftCenter = new JPanel();
		add = proView.addButton("Add", inLeftCenter);
		add.setActionCommand("Add");
		//add.addActionListener(controller);
		add.addActionListener(proController);
		
		JPanel inLeftButtom = new JPanel();
		logout = proView.addButton("Logout", inLeftButtom);
		logout.setActionCommand("Logout");
		logout.addActionListener(proController);
		inLeftButtom.setBorder(new EmptyBorder(new Insets(0,0,100,0)));
		
		left.add(inleftTop, BorderLayout.PAGE_START);
		left.add(inLeftCenter, BorderLayout.CENTER);
		left.add(inLeftButtom, BorderLayout.PAGE_END);
		
		JPanel center = new JPanel();
		this.proView.setBox(center, 1);
		ProviderDBQ proDB = new ProviderDBQ(this);
		//Database data = new Database(this);
		proDB.availableProvTable();
		
		scrollAvaiTable = proView.addTableS(0, dataTableAvai, columnsNamAvai, center, "My Availabilites");
		scrollAvaiTable.setPreferredSize(new Dimension(300,250));
		ListSelectionModel modelTwo = this.proView.myTable[0].getSelectionModel();
		modelTwo.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!modelTwo.isSelectionEmpty()) {
					selectedRowTow = modelTwo.getMinSelectionIndex();
					JOptionPane.showMessageDialog(proView, "Availability selected: "
							+ "Ref: "+dataTableAvai[selectedRowTow][0]+" "
									+ "date "+dataTableAvai[selectedRowTow][1]+" "
											+ "time "+dataTableAvai[selectedRowTow][2]+"");
				}
			}
		});
		
		JPanel delAvai = new JPanel();
		confirm = this.proView.addButton("Delete", delAvai);
		center.add(delAvai);
		confirm.setActionCommand("Delete");
		confirm.addActionListener(proController);
		
		JPanel rightTable = new JPanel();
		this.proView.setBox(rightTable, 1);
		ProviderDBQ proDBbooked = new ProviderDBQ(this);
		//Database dataBooked = new Database(this);
		proDBbooked.toBeConfirmPro();
		scrollBookedTable = proView.addTableS(1, dataTableBooked, columnsNamAppoint, rightTable, "Appointments");
		scrollBookedTable.setPreferredSize(new Dimension(250,250));
		ListSelectionModel model = this.proView.myTable[1].getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!model.isSelectionEmpty()) {
					selectedRow = model.getMinSelectionIndex();
					JOptionPane.showMessageDialog(proView, "Appointment selected: "
							+ ""+dataTableBooked[selectedRow][0]+" "
									+ ""+dataTableBooked[selectedRow][1]+" "
											+ " "+dataTableBooked[selectedRow][2]+" "
												+ "on "+dataTableBooked[selectedRow][3]+" "
														+ "at "+dataTableBooked[selectedRow][4]+"o'clock");
				}
			}
		});
		JPanel appointBtns = new JPanel();
		confirm = this.proView.addButton("Confirm", appointBtns);
		rightTable.add(appointBtns);
		confirm.setActionCommand("Confirm");
		confirm.addActionListener(proController);
		
		cancel = this.proView.addButton("Cancel", appointBtns);
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(proController);
		
		setDone = this.proView.addButton("Set Completed", appointBtns);
		setDone.setActionCommand("Completed");
		setDone.addActionListener(proController);
		
		
		
			
		proView.panel.add(top, BorderLayout.PAGE_START);
		proView.panel.add(left, BorderLayout.LINE_START);
		proView.panel.add(rightTable, BorderLayout.CENTER);
		proView.panel.add(center, BorderLayout.EAST);
		
	    proView.validate();
	    proView.repaint();
	    
		
	}
	
		public void UpdateFrame() {
			proView.panel.removeAll();
			providerViewSetup();
		}
	
	
	
}
