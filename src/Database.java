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
	
	public Database(Register register) {
		
		this.register = register;
		
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
	
		
		registerUser(this.register.getUserType());
	
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
	
 }
	
	

