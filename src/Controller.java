import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	
	View view;
	Model model;
	
	public Controller() {
		this.view = new View ("Online Barber's Shop Appointments");
		this.model = new Model();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
