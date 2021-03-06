import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class CustomerDBQ {

	// GLOBAL VARIABLES - DECLARATION
	private Database data;
	private CustomerView custView;
	private CustomerPEdit custPedit;
	private CustomerController custController;

	public CustomerDBQ(CustomerView CustView) {

		this.custView = CustView;
		// NEW INSTANCE OF DATABASE FOR CONNECTION
		this.data = new Database();
	}
	
	public CustomerDBQ(CustomerPEdit CustpEdit, CustomerView CustView) {

		this.custPedit = CustpEdit;
		this.custView = CustView;
		// NEW INSTANCE OF DATABASE FOR CONNECTION
		this.data = new Database();
	}

	// METHOD TO GET DATA OF THE CUSTOMER LOGGED
	public void customerLogged() {

		String query = "SELECT cust_id, cust_name, cust_surname, address, mob_num FROM customers WHERE email='"
				+ this.custView.getCustomerEmail() + "';";

		try {

			this.data.rs = this.data.stmt.executeQuery(query);

			while (this.data.rs.next()) {
				this.custView.setCustomerID(this.data.rs.getInt("cust_id"));
				this.custView.setCustomerName(this.data.rs.getString("cust_name"));
				this.custView.setCustmerSurName(this.data.rs.getString("cust_surname"));
				this.custView.setCustomerAddress(this.data.rs.getString("address"));
				this.custView.setCustomerMobile(this.data.rs.getString("mob_num"));
			}

			this.data.rs.close();
			this.data.stmt.close();
			this.data.conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TO SEARCH PROVIDER BY NAME OR LOCATION
	public void searchProvider(String by, String input) {

		String query = "";

		// LOCAL DATA STORAGE (INTERNAL)
		String[][] data = new String[20][5];

		try {

			// BY NAME
			if (by.equals("Name")) {
				query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities "
						+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id WHERE providers.pro_name='"
						+ input + "' AND " + "availabilities.available='Yes';";
			}
			// BY LOCATION
			else if (by.equals("Location")) {
				query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities "
						+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id WHERE providers.location='"
						+ input + "' AND " + "availabilities.available='Yes';";
			}
			// SHOW ALL AVAILABILITIES
			else {
				query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities "
						+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id WHERE availabilities.available='Yes';";
			}

			this.data.rs = this.data.stmt.executeQuery(query);

			int i = 0;

			while (this.data.rs.next()) {

				data[i][0] = this.data.rs.getString("avai_ref");
				data[i][1] = this.data.rs.getString("pro_name");
				data[i][2] = this.data.rs.getString("pro_surname");
				data[i][3] = this.data.rs.getString("date");
				data[i][4] = this.data.rs.getString("time");
				i++;
			}

			this.data.rs.close();
			this.data.stmt.close();
			this.data.conn.close();

			// THIS CREATE A COPY OF THE LOCAL DATA TO THE ARRAY[][] SETTER IN CUSTOMER
			// TABLES
			this.custView.setCopyDataAvai(data);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// TO SEARCH PROVIDER BY NAME OR LOCATION
		public void searchByDay(Date date) {

			String query = "";

			// LOCAL DATA STORAGE (INTERNAL)
			String[][] data = new String[20][5];

			try {
				
					query = "SELECT avai_ref, pro_name, pro_surname, date, time FROM availabilities "
							+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id WHERE availabilities.available='Yes' "
							+ "AND availabilities.date='"+ date +"';";
				

				this.data.rs = this.data.stmt.executeQuery(query);

				int i = 0;

				while (this.data.rs.next()) {

					data[i][0] = this.data.rs.getString("avai_ref");
					data[i][1] = this.data.rs.getString("pro_name");
					data[i][2] = this.data.rs.getString("pro_surname");
					data[i][3] = this.data.rs.getString("date");
					data[i][4] = this.data.rs.getString("time");
					i++;
				}

				this.data.rs.close();
				this.data.stmt.close();
				this.data.conn.close();

				// THIS CREATE A COPY OF THE LOCAL DATA TO THE ARRAY[][] SETTER IN CUSTOMER
				// TABLES
				this.custView.setCopyDataAvai(data);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	

	// FOR BOKKING AN APPOINTMENT
	public String bookAppointment() {

		try {

			String query = "INSERT INTO appointments (avai_ref, cust_id, comments) " + "VALUES (?, ?, ?)";

			PreparedStatement preparedStmt = this.data.conn.prepareStatement(query);

			preparedStmt.setString(1, this.custView.getDataAvai(this.custView.getSelectedRowT(), 0));
			preparedStmt.setInt(2, this.custView.getCustomerID());
			preparedStmt.setString(3, "No");
			preparedStmt.execute();
			this.data.conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.custView, "Ups, there is a error! try again later", "Data Error!",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		JOptionPane.showMessageDialog(this.custView, "Your appointment has been added");

		return this.custView.getDataAvai(this.custView.getSelectedRowT(), 0);

	}

	// SET APPOINTMENT BOOKED
	public void setAppointment(String avai_reference) {

		try {

			String query = "UPDATE availabilities SET available='Unconfirmed' WHERE avai_ref='" + avai_reference + "';";

			PreparedStatement preparedStmt = this.data.conn.prepareStatement(query);
			preparedStmt.execute();
			this.data.conn.close();

		} catch (Exception e) {

			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		this.custView.UpdateFrame(true);
	}

	// SEARCH ALL APPOINTMENT MADE IT BY THE CUSTOMER
	public void searchAppointRecord() {

		String[][] data = new String[20][7];

		String query = "SELECT appoint_ref, pro_name, pro_surname, date, time, available, comments "
				+ "FROM availabilities INNER JOIN providers " + "ON availabilities.pro_id = providers.pro_id "
				+ "INNER JOIN appointments ON availabilities.avai_ref = appointments.avai_ref "
				+ "INNER JOIN customers ON appointments.cust_id = customers.cust_id " + "WHERE appointments.cust_id="
				+ this.custView.getCustomerID() + ";";

		try {

			this.data.rs = this.data.stmt.executeQuery(query);

			int i = 0;

			while (this.data.rs.next()) {

				data[i][0] = this.data.rs.getString("appoint_ref");
				data[i][1] = this.data.rs.getString("pro_name");
				data[i][2] = this.data.rs.getString("pro_surname");
				data[i][3] = this.data.rs.getString("date");
				data[i][4] = this.data.rs.getString("time");
				data[i][5] = this.data.rs.getString("available");
				data[i][6] = this.data.rs.getString("comments");
				i++;
			}

			this.data.rs.close();
			this.data.stmt.close();
			this.data.conn.close();

			this.custView.setCopyTableStatus(data);
			;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TO CANCEL AN APPOINTMENT
	public void cancelAppoint(String errorMg, String confMg) {

		boolean flag = true;

		try {

			String query = "UPDATE availabilities INNER JOIN appointments ON availabilities.avai_ref = appointments.avai_ref "
					+ "SET availabilities.available='Cancelled' " + "WHERE appointments.appoint_ref="
					+ this.custView.getTableStatus(this.custView.getSelectedRowTTwo(), 0) + ";";

			PreparedStatement preparedStmt = this.data.conn.prepareStatement(query);
			preparedStmt.execute();
			this.data.conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.custView, errorMg, "Error", JOptionPane.ERROR_MESSAGE);
			flag = false;
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		if (flag)
			JOptionPane.showMessageDialog(this.custView, confMg);

	}

	// OPTIONAL METHOD TO UPDATE DATA
	public void updateRow(String table, String attribute, String value, String where, String ID, String errorMg,
			String confMg) {

		boolean flag = true;

		try {

			String query = "UPDATE " + table + " SET " + attribute + "='" + value + "' WHERE " + where + "='" + ID
					+ "';";

			PreparedStatement preparedStmt = this.data.conn.prepareStatement(query);
			preparedStmt.execute();
			this.data.conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.custView, errorMg, "Error", JOptionPane.ERROR_MESSAGE);
			flag = false;
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		if (flag)
			JOptionPane.showMessageDialog(this.custView, confMg);

	}
	
	/////////////////////////////////////////
	/////////CUSTOMER PROFILE - EDIT/////////
	
	public void updateProfile(String ID, String email, String mobile, String address, String errorMg, String confMg) {

		boolean flag = true;
		
		try {

			String query = "UPDATE customers SET email = '"+ email +"', mob_num = '"+ mobile +"', address = '"+ address +"'"
					+ " WHERE cust_id='"+ ID + "';";

			PreparedStatement preparedStmt = this.data.conn.prepareStatement(query);
			preparedStmt.execute();
			this.data.conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.custPedit, errorMg, "Error", JOptionPane.ERROR_MESSAGE);
			flag = false;
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		if (flag)
			JOptionPane.showMessageDialog(this.custPedit, confMg);
			this.custView.setCustomerEmail(email);
			this.custView.setCustomerMobile(mobile);
			this.custView.setCustomerAddress(address);
		
	}
	
	
	
	
}
