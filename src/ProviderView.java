import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

public class ProviderView extends JFrame {
	
	View proView;
	
	public ProviderView () {
		
		this.proView = new View("Provider Manager", 900, 600, true);
		providerViewSetup();
		
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
		proView.addLabel("Welcome XXXXX", top);
	
		JPanel left = new JPanel();
		proView.addLabel("Add Availability: ", left);
		proView.addCalen(left);
		proView.addComboB(hrs, left);
		proView.addButton("Add", left);
		
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
