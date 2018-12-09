import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CustomerDBQ {

	Database data;
	CustomerView custView;
	CustomerController custController;
	
	public CustomerDBQ(CustomerView CustView) {
			
			this.custView = CustView;
			this.data = new Database();
	}
	
	public void customerLogged() {
		
		
		String query= "SELECT cust_id, cust_name, cust_surname FROM customers WHERE email='"+this.custView.getCustomerEmail()+"';";
		
		try {
			
			this.data.rs = this.data.stmt.executeQuery(query);
		
			while(this.data.rs.next()) {
				custView.setCustomerID(this.data.rs.getInt("cust_id"));
				custView.setCustomerName(this.data.rs.getString("cust_name"));
				custView.setCustmerSurName(this.data.rs.getString("cust_surname"));
			}
			
			this.data.rs.close();
			this.data.stmt.close() ;
			this.data.conn.close() ;
				
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public void searchProvider(String by, String input) {
		
		String query="";
		String[][] data= new String[20][5];
		
		try {
		
			if(by.equals("Name")) {
				query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities "
					+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id WHERE providers.pro_name='"+input+"' AND "
							+ "availabilities.available='Yes';";
			}
			else if(by.equals("Location")){
				query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities "
						+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id WHERE providers.location='"+input+"' AND "
							+ "availabilities.available='Yes';";
			}
			else {
				query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities "
					+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id WHERE availabilities.available='Yes';";
			}
			
			this.data.rs = this.data.stmt.executeQuery(query);
			
			int i = 0;
			
			while(this.data.rs.next()) {
				
				data[i][0] = this.data.rs.getString("avai_ref");
				data[i][1] = this.data.rs.getString("pro_name");
				data[i][2] = this.data.rs.getString("pro_surname");
				data[i][3] = this.data.rs.getString("date");
				data[i][4] = this.data.rs.getString("time");
				i++;
			}
			
			this.data.rs.close();
			this.data.stmt.close() ;
			this.data.conn.close() ;
			
			this.custView.setCopyDataAvai(data);
			
				
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
		
		
	}
	
	public String bookAppointment() {
		
		
		try {
			
			String query = "INSERT INTO appointments (avai_ref, cust_id, comments) "
					+ "VALUES (?, ?, ?)";
		
			PreparedStatement preparedStmt = this.data.conn.prepareStatement(query);
			
			preparedStmt.setString(1, this.custView.getDataAvai(this.custView.getSelectedRowT(),0));
			preparedStmt.setInt(2, this.custView.getCustomerID());
			preparedStmt.setString(3, "No");				
			preparedStmt.execute();
			this.data.conn.close();
			
		}catch (Exception e)
	    	{
		      	JOptionPane.showMessageDialog(this.custView, "Ups, there is a error! try again later", "Data Error!", JOptionPane.ERROR_MESSAGE);
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
		    }
		JOptionPane.showMessageDialog(this.custView, "Your appointment has been added");
		
		return this.custView.getDataAvai(this.custView.getSelectedRowT(),0);
		
	
	}
	
	public void setAppointment(String avai_reference) {
		
		try {
			
			String query = "UPDATE availabilities SET available='Unconfirmed' WHERE avai_ref='"+avai_reference+"';";
			
			PreparedStatement preparedStmt = this.data.conn.prepareStatement(query);
			preparedStmt.execute();
			this.data.conn.close();
			
		}catch (Exception e)
	    	{
		      	
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
		    }
		this.custView.UpdateFrame(true);	
	}
	
	
	
}
