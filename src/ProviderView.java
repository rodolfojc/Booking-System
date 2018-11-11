import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

public class ProviderView extends JFrame {
	
	private View proView;
	private Database data;
	private Controller controller;
	private JButton add;
	private JDateChooser calendar;
	private JComboBox hr;
	private String provID, proName, proSurName, proEmail, proLocation;
	
	public ProviderView (Controller controller, String email) {
		
		this.controller = controller;
		this.proEmail = email;
		getUserData(this.proEmail);
		this.proView = new View("Provider Manager", 900, 600, true);
		providerViewSetup();
	}
	
	public String getDatE() {
		return calendar.getDate().toGMTString();
	}
	
	public String getHour() {
		return hr.getName();
	}
	
	public void setProviderID(String proID) {
		this.provID = proID;
	}	
	
	public void setProviderName(String proNam) {
		this.proName = proNam;
	}
	
	public void setProviderSurName(String proSur) {
		this.proSurName = proSur;
	}
	
	public void setProviderLocation(String proLoc) {
		this.proLocation = proLoc;
	}
	
	public String getProviderEmail() {
		return this.proEmail;
	}
	
	public void getUserData(String email) {
			data = new Database(this);
			data.providerLogged();
	}
		
	public void providerViewSetup() {

		String[] hrs= {"8:00", "8:30", "9:00", "9:30", "10:00", "10:30",
				"11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
				"14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
				"17:00", "17:30", "18:00", "18:30", "19:00", "19:30"};
		
		String[] columnsNam = {"Date", "Time"};
		String[][] data = new String[40][2];
		
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
		
		JPanel center = new JPanel();
		proView.addTableS(data, columnsNam, center);
			
		
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
	
	
	
	
	
}
