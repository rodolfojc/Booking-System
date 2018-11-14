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
		
		if (type.equals("Costumer")) {
			try {
				
				String query = "INSERT INTO custumers (cust_name, cust_surname, mob_num, email, address, pass, appoint)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, this.register.getName());
				preparedStmt.setString(2, this.register.getSur());
				preparedStmt.setInt(3, this.register.getMob());;
				preparedStmt.setString(4, this.register.getEmail());
				preparedStmt.setString(5, this.register.getAddress());
				preparedStmt.setString(6, this.register.getPass());
				preparedStmt.setString(7, "No");
				
				preparedStmt.execute();
				conn.close();
				
			}catch (Exception e)
		    	{
			      System.err.println("Got an exception!");
			      System.err.println(e.getMessage());
			    }
		}
		
		if (type.equals("Provider")) {
			
				try {
				
				String query = "INSERT INTO providers (pro_name, pro_surname, mob_num, email, address, pass, location, status)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, this.register.getName());
				preparedStmt.setString(2, this.register.getSur());
				preparedStmt.setInt(3, this.register.getMob());;
				preparedStmt.setString(4, this.register.getEmail());
				preparedStmt.setString(5, this.register.getAddress());
				preparedStmt.setString(6, this.register.getPass());
				preparedStmt.setString(7, this.register.getLoc());
				preparedStmt.setString(8, "Pending");
				
				preparedStmt.execute();
				conn.close();
				
			}catch (Exception e)
		    	{
			      System.err.println("Got an exception!");
			      System.err.println(e.getMessage());
			    }
		}
		
	}
	
	public boolean loginUser(String user, String email, String password) {
		
		String query = "SELECT * FROM "+user+" WHERE email = '" + email + "' AND pass= '" + password + "';";
			
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
		
		
		String query= "SELECT cust_id, cust_name, cust_surname FROM custumers WHERE email='"+this.custView.getCustomerEmail()+"';";
		
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
				proView.setProviderID(rs.getString("pro_id"));
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
			
			String query = "INSERT INTO availabilities (pro_id, pro_name, pro_surname, date, time, location, available)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, this.proView.getProviderID());
			preparedStmt.setString(2, this.proView.getProviderName());
			preparedStmt.setString(3, this.proView.getProviderSurName());
			preparedStmt.setString(4, this.proView.getDatE());
			preparedStmt.setString(5, this.proView.getHour());
			preparedStmt.setString(6, this.proView.getProviderLocation());
			preparedStmt.setString(7, "Yes");
			
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
	
		public void availableProvTable() {
			
			String query = "SELECT date, time FROM availabilities av INNER JOIN providers pr ON av.pro_id = pr.pro_id  WHERE email='"+this.proView.getProviderEmail()+"';";
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
		
		public void availableCosTable() {
			
			String query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities;";
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
		
		public void bookApointment() {
			
			try {
				
				String query = "INSERT INTO appointments (cust_id, cust_name, cust_surname, "
						+ "pro_id, pro_name, pro_surname, date, time, status,)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setInt(1, custView.getCustomerID());
				preparedStmt.setString(2, this.proView.getProviderName());
				preparedStmt.setString(3, this.proView.getProviderSurName());
				preparedStmt.setString(4, this.proView.getDatE());
				preparedStmt.setString(5, this.proView.getHour());
				preparedStmt.setString(6, this.proView.getProviderLocation());
				preparedStmt.setString(7, "Yes");
				
				preparedStmt.execute();
				conn.close();
				
			}catch (Exception e)
		    	{
			      	JOptionPane.showMessageDialog(this.proView, "Ups, there is a error! try again later", "Data Error!", JOptionPane.ERROR_MESSAGE);
					System.err.println("Got an exception!");
					System.err.println(e.getMessage());
			    }
			JOptionPane.showMessageDialog(this.proView, "Your availability has been added");
			
		}
	
		public void searchProvider(String by, String input) {
			
			String query="";
			String[][] data= new String[20][5];
			
			if(by.equals("Name")) {
				query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities WHERE pro_name='"+input+"';";
			}
			else if(by.equals("Location")){
				query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities WHERE location='"+input+"';";
			}
			else {
				query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities;";
			}
			
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
	
	
	
	
	
	
	
	
	
	
	
	
	
 
}
	

