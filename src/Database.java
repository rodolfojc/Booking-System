import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Database {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	Register register;
	Login login;
	CustomerView custView;
	ProviderView proView;
	
	public Database() {
		connectDB();
	}
	
	public Database(ProviderView proView) {
		this.proView = proView;
		connectDB();
	}
	
	public Database(Register register) {
		
		this.register = register;
		connectDB();
	}
	
	public Database(Login login) {
		
		this.login = login;
		connectDB();
	}
	
	public Database(CustomerView custView) {
		this.custView = custView;
		connectDB();
	}
	
	public Database(CustomerView custView, ProviderView proView) {
		this.custView = custView;
		this.proView = proView;
		connectDB();
	}
	
	public void connectDB() {
		
		try{
			// Load the database driver
			Class.forName("com.mysql.jdbc.Driver").newInstance() ;
			
			String dbServer = "jdbc:mysql://localhost:3306/booking";
			String user = "root";
			String password ="";

			// Get a connection to the database
			conn = DriverManager.getConnection(dbServer, user, password) ;

			// Get a statement from the connection
			stmt = conn.createStatement() ;


		}catch( SQLException se ){
			System.out.println( "SQL Exception:" ) ;

			// Loop through the SQL Exceptions
			while( se != null ){
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e ){
			System.out.println( e ) ;
		}
	
		
	}
	
	public void registerUser(String type) {
		
		boolean flag=true;
		
		if (type.equals("Customer")) {
			try {
				
				String query = "INSERT INTO customers (cust_name, cust_surname, mob_num, email, address, pass)"
						+ "VALUES (?, ?, ?, ?, ?, SHA2(?,512))";
			
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, this.register.getName());
				preparedStmt.setString(2, this.register.getSur());
				preparedStmt.setString(3, this.register.getMob());;
				preparedStmt.setString(4, this.register.getEmail());
				preparedStmt.setString(5, this.register.getAddress());
				preparedStmt.setString(6, this.register.getPassField());
								
				preparedStmt.execute();
				conn.close();
				
			}catch (Exception e)
		    	{
			      JOptionPane.showMessageDialog(this.register, "Ups, there is a problem, try again!");
			      flag=false;
				  System.err.println("Got an exception!");
			      System.err.println(e.getMessage());
			    }
			
			if(flag==true) JOptionPane.showMessageDialog(this.register, "Your account has been created "
					+this.register.getName()+" . Now you can login, thanks");
			this.register.getReg().dispose();
			
		}
		
		if (type.equals("Provider")) {
			
				try {
				
				String query = "INSERT INTO providers (pro_name, pro_surname, mob_num, email, address, pass, location, status, reg_day)"
						+ "VALUES (?, ?, ?, ?, ?, SHA2(?,512), ?, ?, ?)";
			
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, this.register.getName());
				preparedStmt.setString(2, this.register.getSur());
				preparedStmt.setString(3, this.register.getMob());;
				preparedStmt.setString(4, this.register.getEmail());
				preparedStmt.setString(5, this.register.getAddress());
				preparedStmt.setString(6, this.register.getPassField());
				preparedStmt.setString(7, this.register.getLoc());
				preparedStmt.setString(8, "unconfirmed");
				preparedStmt.setDate(9, this.register.getRegDatE());
				
				preparedStmt.execute();
				conn.close();
				
			}catch (Exception e)
		    	{
				  JOptionPane.showMessageDialog(this.register, "Ups, there is a problem, try again!");
			      flag=false;
			      System.err.println("Got an exception!");
			      System.err.println(e.getMessage());
			    }
		
			if(flag==true) JOptionPane.showMessageDialog(this.register, "Your account has been created "
						+this.register.getName()+" . Please, wait until an administrator validate your account, thanks");
			this.register.getReg().dispose();
		}
		
	}
	
	public boolean loginUser(String user, String email, String password) {
		
		String query = "SELECT * FROM "+user+" WHERE email = '" + email + "' AND pass= SHA2('"+ password + "',512);";
			
		try {
			rs = stmt.executeQuery(query) ;
			
			// This code is telling us whether we have any results
			// in our database or not
			if (rs.isBeforeFirst()){
				return true;
			}
			
			// Close the result set, statement and the connection
			rs.close() ;
			stmt.close() ;
			conn.close() ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public void customerLogged() {
		
		
		String query= "SELECT cust_id, cust_name, cust_surname FROM customers WHERE email='"+this.custView.getCustomerEmail()+"';";
		
		try {
			
			rs = stmt.executeQuery(query);
		
			while(rs.next()) {
				custView.setCustomerID(rs.getInt("cust_id"));
				custView.setCustomerName(rs.getString("cust_name"));
				custView.setCustmerSurName(rs.getString("cust_surname"));
			}
			
			rs.close();
			stmt.close() ;
			conn.close() ;
				
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public void providerLogged() {
		
		
		String query= "SELECT pro_id, pro_name, pro_surname, location FROM providers WHERE email='"+this.proView.getProviderEmail()+"';";
		
		try {
			
			rs = stmt.executeQuery(query);
		
			while(rs.next()) {
				proView.setProviderID(rs.getInt("pro_id"));
				proView.setProviderName(rs.getString("pro_name"));
				proView.setProviderSurName(rs.getString("pro_surname"));
				proView.setProviderLocation(rs.getString("location"));
			}
			
			rs.close();
			stmt.close() ;
			conn.close() ;
				
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	  }	
	
	public void addAvailability() {
		
		try {
			
			String query = "INSERT INTO availabilities (pro_id, date, time, available)"
					+ "VALUES (?, ?, ?, ?)";
		
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, this.proView.getProviderID());
			preparedStmt.setDate(2, this.proView.getDatE());
			preparedStmt.setString(3, this.proView.getHour());
			preparedStmt.setString(4, "Yes");
			
			preparedStmt.execute();
			conn.close();
			
		}catch (Exception e)
	    	{
		      	JOptionPane.showMessageDialog(this.proView, "Ups, there is a error! try again later", "Data Error!", JOptionPane.ERROR_MESSAGE);
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
		    }
		JOptionPane.showMessageDialog(this.proView, "Your availability has been added");
		this.proView.UpdateFrame();
		
	}
	
	public void toBeConfirmPro() {
		
		String query = "SELECT cust_name, cust_surname, date, time, availabilities.avai_ref FROM appointments "
				+ "INNER JOIN customers ON customers.cust_id = appointments.cust_id "
					+ "INNER JOIN availabilities ON availabilities.avai_ref = appointments.avai_ref "
						+ "WHERE availabilities.pro_id = '"+this.proView.getProviderID()+"' "
							+ "AND availabilities.available='unconfirmed';";
		String[][] data= new String[20][5];
		
		try {
			
			rs = stmt.executeQuery(query);
			int i = 0;
			
			while(rs.next()) {
				
				data[i][0] = rs.getString("avai_ref");
				data[i][1] = rs.getString("cust_name");
				data[i][2] = rs.getString("cust_surname");
				data[i][3] = rs.getString("date");
				data[i][4] = rs.getString("time");
				
				i++;
			}
			
			rs.close();
			stmt.close() ;
			conn.close() ;
			
			this.proView.setCopyDataBooked(data);
			
												
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
		
		public void availableProvTable() {
			
			String query = "SELECT date, time FROM availabilities av INNER JOIN providers pr ON av.pro_id = pr.pro_id  "
					+ "WHERE email='"+this.proView.getProviderEmail()+"' AND available='Yes';";
			String[][] data= new String[20][2];
			
			try {
				
				rs = stmt.executeQuery(query);
				int i = 0;
				
				while(rs.next()) {
					
					data[i][0] = rs.getString("date");
					data[i][1] = rs.getString("time");
					i++;
				}
				
				rs.close();
				stmt.close() ;
				conn.close() ;
				
				this.proView.setCopyDataAvai(data);
				
													
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		  
		}
		//ERASE
		public void availableCosTable() {
			
			String query = "SELECT av.avai_ref, pr.pro_name, pr.pro_surname, av.date, av.time FROM availabilities av "
					+ "INNER JOIN providers pr ON av.pro_id = pr.pro_id;";
			String[][] data= new String[20][5];
			
			try {
				
				rs = stmt.executeQuery(query);
				int i = 0;
				
				while(rs.next()) {
					
					data[i][0] = rs.getString("avai_ref");
					data[i][1] = rs.getString("pro_name");
					data[i][2] = rs.getString("pro_surname");
					data[i][3] = rs.getString("date");
					data[i][4] = rs.getString("time");
					i++;
				}
				
				rs.close();
				stmt.close() ;
				conn.close() ;
				
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
			
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				
				preparedStmt.setString(1, this.custView.getDataAvai(this.custView.getSelectedRowT(),0));
				preparedStmt.setInt(2, this.custView.getCustomerID());
				preparedStmt.setString(3, "No");				
				preparedStmt.execute();
				conn.close();
				
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
				
				String query = "UPDATE availabilities SET available='unconfirmed' WHERE avai_ref='"+avai_reference+"';";
				
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.execute();
				conn.close();
				
			}catch (Exception e)
		    	{
			      	
					System.err.println("Got an exception!");
					System.err.println(e.getMessage());
			    }
			this.custView.UpdateFrame(true);	
		}
		
		public void comfirmAppointPro(String avai_reference) {
			
			try {
				
				String query = "UPDATE availabilities SET available='confirmed' WHERE avai_ref='"+avai_reference+"';";
				
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.execute();
				conn.close();
				
			}catch (Exception e)
		    	{
			      	
					System.err.println("Got an exception!");
					System.err.println(e.getMessage());
			    }
			JOptionPane.showMessageDialog(this.proView, "The appointment has been confirm. Thanks");
			this.proView.UpdateFrame();	
		}
		
			
			
			
			
		
	
		public void searchProvider(String by, String input) {
			
			String query="";
			String[][] data= new String[20][5];
			
			try {
			
				if(by.equals("Name")) {
					query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities"
						+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id WHERE providers.pro_name='"+input+"' AND "
								+ "availabilities.available='Yes';";
				}
				else if(by.equals("Location")){
					query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities"
						+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id WHERE providers.location='"+input+"' AND "
								+ "availabilities.available='Yes';";
				}
				else {
					query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities "
						+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id WHERE availabilities.available='Yes';";
				}
				
				rs = stmt.executeQuery(query);
				
				int i = 0;
				
				while(rs.next()) {
					
					data[i][0] = rs.getString("avai_ref");
					data[i][1] = rs.getString("pro_name");
					data[i][2] = rs.getString("pro_surname");
					data[i][3] = rs.getString("date");
					data[i][4] = rs.getString("time");
					i++;
				}
				
				rs.close();
				stmt.close() ;
				conn.close() ;
				
				this.custView.setCopyDataAvai(data);
				
					
			}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
			
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
 
}
	

