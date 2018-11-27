import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class AdminDBQ {

	Database data;
	AdminView adminView;
	AdminController adminController;
	
	public AdminDBQ(AdminView AdminView) {
		
		this.adminView = AdminView;
		this.data = new Database();
	}

	public void getCustomer() {
		
		String query= "SELECT cust_id, cust_name, cust_surname FROM customers;";
		
		String[][] data= new String[20][5];
		
		try {
			
			this.data.rs = this.data.stmt.executeQuery(query);
			int i = 0;
			
			while(this.data.rs.next()) {
				
				data[i][0] = this.data.rs.getString("cust_id");
				data[i][1] = this.data.rs.getString("cust_name");
				data[i][2] = this.data.rs.getString("cust_surname");
								
				i++;
			}
			
			this.data.rs.close();
			this.data.stmt.close() ;
			this.data.conn.close() ;
			
			this.adminView.setCopyDataCust(data);
				
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}
		
		
	public void deleteCustOrPro(String userType, String userIDtype, String userID) {
		
		boolean flag = true;
		
		String query = "DELETE FROM "+userType+" WHERE "+userIDtype+"="+userID+";";
		
		try {
			
			PreparedStatement preparedStmt = this.data.conn.prepareStatement(query);
			preparedStmt.execute();
			this.data.conn.close();
			
		}catch (Exception e)
	    	{
		      	JOptionPane.showMessageDialog(this.adminView, "Ups, there is a problem, please be sure the user does not have"
		      			+ " any availability or appointment registered.", "Error", JOptionPane.ERROR_MESSAGE);
		      	flag=false;
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
		    }
		if(flag)JOptionPane.showMessageDialog(this.adminView, "The user has been DELETED!");
	}
	
	
	public void getProviders() {
		
		String query= "SELECT pro_id, pro_name, pro_surname, status, reg_day FROM providers;";
		
		String[][] data= new String[30][5];
		
		try {
			
			this.data.rs = this.data.stmt.executeQuery(query);
			int i = 0;
			
			while(this.data.rs.next()) {
				
				data[i][0] = this.data.rs.getString("pro_id");
				data[i][1] = this.data.rs.getString("pro_name");
				data[i][2] = this.data.rs.getString("pro_surname");
				data[i][3] = this.data.rs.getString("status");
				data[i][4] = this.data.rs.getString("reg_day");
				
								
				i++;
			}
			
			this.data.rs.close();
			this.data.stmt.close() ;
			this.data.conn.close() ;
			
			this.adminView.setCopyDataPro(data);
				
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
	}
	
	public void validatePro(String pro_ID) {
		
		boolean flag=true;
		
		try {
			
			String query = "UPDATE providers SET status='confirmed' WHERE pro_id='"+pro_ID+"';";
			
			PreparedStatement preparedStmt = this.data.conn.prepareStatement(query);
			preparedStmt.execute();
			this.data.conn.close();
			
		}catch (Exception e)
	    	{
		      	JOptionPane.showMessageDialog(this.adminView, "Ups, there is an internal error, please try again", 
		      			"Error", JOptionPane.ERROR_MESSAGE);
		      	flag=false;
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
		    }
		if(flag)JOptionPane.showMessageDialog(this.adminView, "Provider ID "+pro_ID+" has been CONFIRMED");
				
	}
	
	
	
	
	public void getAvailabilities() {
		
		String query= "SELECT avai_ref, pro_id, date, time, available FROM availabilities;";
		
		String[][] data= new String[30][5];
		
		try {
			
			this.data.rs = this.data.stmt.executeQuery(query);
			int i = 0;
			
			while(this.data.rs.next()) {
				
				data[i][0] = this.data.rs.getString("avai_ref");
				data[i][1] = this.data.rs.getString("pro_id");
				data[i][2] = this.data.rs.getString("date");
				data[i][3] = this.data.rs.getString("time");
				data[i][4] = this.data.rs.getString("available");
				
								
				i++;
			}
			
			this.data.rs.close();
			this.data.stmt.close() ;
			this.data.conn.close() ;
			
			this.adminView.setCopyDataAvai(data);
				
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		
	}
	
	public void getAppointments() {
		
		String query= "SELECT appoint_ref, avai_ref, cust_id, comments FROM appointments;";
		
		String[][] data= new String[30][4];
		
		try {
			
			this.data.rs = this.data.stmt.executeQuery(query);
			int i = 0;
			
			while(this.data.rs.next()) {
				
				data[i][0] = this.data.rs.getString("appoint_ref");
				data[i][1] = this.data.rs.getString("avai_ref");
				data[i][2] = this.data.rs.getString("cust_id");
				data[i][3] = this.data.rs.getString("comments");
						
								
				i++;
			}
			
			this.data.rs.close();
			this.data.stmt.close() ;
			this.data.conn.close() ;
			
			this.adminView.setCopyDataAppoint(data);
				
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		
	}
	
	public void getAdministrators() {
		
		String query= "SELECT admin_id, admin_user, privilege FROM administrators;";
		
		String[][] data= new String[30][3];
		
		try {
			
			this.data.rs = this.data.stmt.executeQuery(query);
			int i = 0;
			
			while(this.data.rs.next()) {
				
				data[i][0] = this.data.rs.getString("admin_id");
				data[i][1] = this.data.rs.getString("admin_user");
				data[i][2] = this.data.rs.getString("privilege");
											
				i++;
			}
			
			this.data.rs.close();
			this.data.stmt.close() ;
			this.data.conn.close() ;
			
			this.adminView.setCopyDataAdmin(data);
				
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
}