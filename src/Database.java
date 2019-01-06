import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Database {

	// GLOBAL VARIABLES - DECLARATION
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	Register register;
	Login login;

	// MAIN CONSTRUCTOR
	public Database() {
		connectDB();
	}

	// CONTSRUCTOR FOR REGISTER VIEW AS A PARAMETER
	public Database(Register register) {

		this.register = register;
		connectDB();
	}

	// CONTSRUCTOR FOR LOGIN VIEW AS A PARAMETER
	public Database(Login login) {

		this.login = login;
		connectDB();
	}

	// DATABSE CONNECTION
	public void connectDB() {

		try {
			// Load the database driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			String dbServer = "jdbc:mysql://localhost:3306/booking";
			String user = "root";
			String password = "";

			// Get a connection to the database
			conn = DriverManager.getConnection(dbServer, user, password);

			// Get a statement from the connection
			stmt = conn.createStatement();

		} catch (SQLException se) {
			System.out.println("SQL Exception:");

			// Loop through the SQL Exceptions
			while (se != null) {
				System.out.println("State  : " + se.getSQLState());
				System.out.println("Message: " + se.getMessage());
				System.out.println("Error  : " + se.getErrorCode());

				se = se.getNextException();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// METHOD TO REGISTER AN USER, TYPE NEEDED
	public void registerUser(String type) {

		// FLAG FOR JOPTIONPANE MESSAGES
		boolean flag = true;

		// FOR CUSTOMER
		if (type.equals("Customer")) {
			try {

				// STATEMENT
				// SHA2 IS THE TYPE OF ENCRYPTION IN MYSQL TO SAVE THE PASSWORD
				String query = "INSERT INTO customers (cust_name, cust_surname, mob_num, email, address, pass)"
						+ "VALUES (?, ?, ?, ?, ?, SHA2(?,512))";

				// PREPARATION
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, this.register.getName());
				preparedStmt.setString(2, this.register.getSur());
				preparedStmt.setString(3, this.register.getMob());
				;
				preparedStmt.setString(4, this.register.getEmail());
				preparedStmt.setString(5, this.register.getAddress());
				preparedStmt.setString(6, this.register.getPassField());

				preparedStmt.execute();
				conn.close();

			} catch (Exception e) {
				// ERROR MESSAGES
				JOptionPane.showMessageDialog(this.register, "Ups, there is a problem, try again!");
				flag = false;
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
			}

			// NO ERRORS
			if (flag == true)
				JOptionPane.showMessageDialog(this.register,
						"Your account has been created " + this.register.getName() + " . Now you can login, thanks");
			this.register.getReg().dispose();

		}

		// FOR PROVIDERS
		// BASICALLY THE SAME CODE AS CUSTOMER BUT WITH LOCATION, AND REGISTRATION DATA
		// ADDED.
		if (type.equals("Provider")) {

			try {

				String query = "INSERT INTO providers (pro_name, pro_surname, mob_num, email, address, pass, location, status, reg_day)"
						+ "VALUES (?, ?, ?, ?, ?, SHA2(?,512), ?, ?, ?)";

				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, this.register.getName());
				preparedStmt.setString(2, this.register.getSur());
				preparedStmt.setString(3, this.register.getMob());
				preparedStmt.setString(4, this.register.getEmail());
				preparedStmt.setString(5, this.register.getAddress());
				preparedStmt.setString(6, this.register.getPassField());
				preparedStmt.setString(7, this.register.getLoc());
				preparedStmt.setString(8, "Pending");
				preparedStmt.setDate(9, this.register.getRegDatE());

				preparedStmt.execute();
				conn.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.register, "Ups, there is a problem, try again!");
				flag = false;
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
			}

			if (flag == true)
				JOptionPane.showMessageDialog(this.register, "Your account has been created " + this.register.getName()
						+ " . Please, wait until an administrator validate your account, thanks");
			this.register.getReg().dispose();
		}

	}

	// METHOD TO CHECK IF THE CUSTOMER OR PROVIDER IS REGISTERED, RETURN TRUE IS
	// THERE IS A MATCH
	public boolean loginUser(String user, String email, String password, boolean flag) {

		String query;

		// QUERIES FOR DIFFERENT TYPE
		if (flag) {
			query = "SELECT * FROM " + user + " WHERE admin_user = '" + email + "' AND admin_pass= SHA2('" + password
					+ "',512);";
		} else {
			query = "SELECT * FROM " + user + " WHERE email = '" + email + "' AND pass= SHA2('" + password + "',512);";
		}

		try {
			rs = stmt.executeQuery(query);

			// This code is telling us whether we have any results
			// in our database or not
			if (rs.isBeforeFirst()) {
				return true;
			}

			// Close the result set, statement and the connection
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	// METHOD TO GET THE PROVIDER STATUS AND RETURN THE STATUS.
	public String getProStatus(String email) {

		String query = "SELECT status FROM providers WHERE email='" + email + "';";
		String result = "";

		try {

			rs = stmt.executeQuery(query);

			while (rs.next()) {
				result = rs.getString("status");
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return result;
	}

	// METHOD TO CHECK IF THE EMAIL ADDRESS IS ALREADY REGISTER IN A TABLE IN
	// DATABASE
	// RETURN TRUE IS THERE IS A MATCH
	public boolean emailVerification(String user, String email) {

		String query = "SELECT * FROM " + user + " WHERE email = '" + email + "';";

		try {
			rs = stmt.executeQuery(query);

			// This code is telling us whether we have any results
			// in our database or not
			if (rs.isBeforeFirst()) {
				return true;
			}

			// Close the result set, statement and the connection
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	public void searchProvider(String by, String input) {

		String query = "";

		String[][] data = new String[20][5];

		try {

			query = "SELECT * FROM availabilities WHERE available='Yes';";
			
			rs = stmt.executeQuery(query);

			int i = 0;

			while (rs.next()) {

				data[i][0] = rs.getString("avai_ref");
				data[i][1] = rs.getString("pro_name");
				data[i][2] = rs.getString("pro_surname");
				data[i][3] = rs.getString("date");
				data[i][4] = rs.getString("time");
				i++;
			}
						
			
			rs.close();
			stmt.close();
			conn.close();

			// THIS CREATE A COPY OF THE LOCAL DATA TO THE ARRAY[][] SETTER IN CUSTOMER
			// TABLES
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// FOR BOKKING AN APPOINTMENT
	
	
	
	
}
	