import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	Register register;
	Login login;
	CustomerView costView;
	
	public Database() {
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
	
	public Database(CustomerView costView) {
		this.costView = costView;
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
				
				String query = "INSERT INTO costumers (name, sur_name, mob_num, email, address, pass)"
						+ "VALUES (?, ?, ?, ?, ?, ?)";
			
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, this.register.getName());
				preparedStmt.setString(2, this.register.getSur());
				preparedStmt.setInt(3, this.register.getMob());;
				preparedStmt.setString(4, this.register.getEmail());
				preparedStmt.setString(5, this.register.getAddress());
				preparedStmt.setString(6, this.register.getPass());
				
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
				
				String query = "INSERT INTO providers (name, sur_name, mob_num, email, address, pass, location)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, this.register.getName());
				preparedStmt.setString(2, this.register.getSur());
				preparedStmt.setInt(3, this.register.getMob());;
				preparedStmt.setString(4, this.register.getEmail());
				preparedStmt.setString(5, this.register.getAddress());
				preparedStmt.setString(6, this.register.getPass());
				preparedStmt.setString(7, this.register.getLoc());
				
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
		
		
		String query= "SELECT cost_id, name, sur_name FROM costumers WHERE email='"+this.costView.getCustomerEmail()+"';";
		
		try {
			
			rs = stmt.executeQuery(query);
		
			while(rs.next()) {
				costView.setCustomerID(rs.getString("cost_id"));
				costView.setCustomerName(rs.getString("name"));
				costView.setCustmerSurName(rs.getString("sur_name"));
			}
			
			rs.close();
			stmt.close() ;
			conn.close() ;
				
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
 }
	
	

