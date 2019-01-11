
public class CustomerPEdit {

	private View custPedit;
	
	public CustomerPEdit() {
	
		this.custPedit = new View("Customer Profile Editor", 500, 300, false);
		setupFrame();
		
	}
	
	public void setupFrame() {
		
		this.custPedit.addLabel("New email ", this.custPedit.panel);
		
		this.custPedit.validate();
		this.custPedit.repaint();
		
	}
	
	
	
	
}
