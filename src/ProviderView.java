import java.awt.BorderLayout;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

public class ProviderView extends JFrame {
	
	private View proView;
	private Database forUserData;
	private Controller controller;
	private JButton add;
	private JDateChooser calendar;
	private JComboBox hr;
	private JTable table;
	private JPanel center;
	private JScrollPane scrollAvaiTable, scrollBookedTable;
	private String[][] dataTableAvai, dataTableBooked;
	private int proID;
	private String proName, proSurName, proEmail, proLocation;
	private String [] hrs = {"8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
			"11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
			"14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
			"17:00", "17:30", "18:00", "18:30", "19:00", "19:30"};
	
	public ProviderView (Controller controller, String email) {
		
		this.controller = controller;
		this.proEmail = email;
		getUserData(this.proEmail);
		this.proView = new View("Provider Manager", 900, 600, true);
		providerViewSetup();
	}
	
	public java.sql.Date getDatE() throws ParseException {
		SimpleDateFormat myDateSimp = new SimpleDateFormat("yyy-MM-dd"); //"yyyy-MM-dd"
		String dateStr = myDateSimp.format(calendar.getDate());
		java.util.Date getCalendar;
		java.sql.Date dateSql;
		getCalendar = myDateSimp.parse(dateStr);
		dateSql = new java.sql.Date(getCalendar.getTime());
		//String date = myDateSimp.format(calendar.getDate());
		//myDateSimp.applyPattern(calendar.getDate());
		//Date myDate = myDateSimp.parse(myDateSimp.format(calendar.getDate());
		//String date = myDate.format(calendar.getDate());
		return dateSql;		
		//return calendar.getDate().toGMTString();
	}
	
	public void setCopyDataAvai(String[][] data) {
		this.dataTableAvai = Arrays.copyOf(data, data.length);
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
			forUserData = new Database(this);
			forUserData.providerLogged();
	}
		
	public void providerViewSetup() {

		String[] columnsNamAvai = {"Date", "Time"};
		String[] columnsNamAppoint = {"Customer Name", "Customer Surname", "Date", "Time"};
		
		
		dataTableAvai = new String[40][2];
		dataTableBooked = new String[40][4];
		
		proView.setBorder(proView.panel);
		
		JPanel top = new JPanel();
		proView.addLabel("Welcome "+this.proName+" "+this.proSurName+"", top);
	
		JPanel left = new JPanel();
		proView.addLabel("Add Availability: ", left);
		calendar = proView.addCalen(left);
		hr = proView.addComboB(hrs, left);
		add = proView.addButton("Add", left);
		add.setActionCommand("Add");
		add.addActionListener(controller);
		
		scrollBookedTable = proView.addTableS(1, dataTableBooked, columnsNamAppoint, center, "Appointment to be confirm");
		
		this.center = new JPanel();
		this.center.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                title,
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
		
		Database data = new Database(this);
		data.availableProvTable();
		
		
		scrollAvaiTable = proView.addTableS(0, dataTableAvai, columnsNamAvai, center, "My Availabilites");
		
		
		//JPanel right = new JPanel();
		//JPanel buttom = new JPanel();

		proView.panel.add(top, BorderLayout.PAGE_START);
		proView.panel.add(left, BorderLayout.LINE_START);
		proView.panel.add(center, BorderLayout.CENTER);
		
	    //String date = dateChooser.getDate().toString();
	    //System.out.println(date);
	    proView.validate();
	    proView.repaint();
	    
		
	}
	
		public void addTableProView() {
			
			Database data = new Database(this);
			data.availableProvTable();
			String[] columnsNam = {"Date", "Time"};
			scrollAvaiTable = proView.addTableS(0, dataTableAvai, columnsNam, center, "My Availabilites");
			proView.panel.add(center, BorderLayout.CENTER);
			proView.myTable[0].revalidate();
			proView.validate();
			proView.repaint();
		}
	
		public void UpdateFrame() {
			proView.panel.removeAll();
			providerViewSetup();
		}
	
	
	
}
