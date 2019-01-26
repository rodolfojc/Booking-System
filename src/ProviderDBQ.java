import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ProviderDBQ {

	// GLOBAL VARIABLES - DECLARATION
	private Database proDB;
	private ProviderView proView;
	private ProviderPEdit proPedit;
	private ProviderController proController;

	public ProviderDBQ(ProviderView ProView) {

		this.proView = ProView;
		// NEW INSTANCE OF DATABASE FOR CONNECTION
		this.proDB = new Database();

	}
	
	public ProviderDBQ(ProviderPEdit ProPedit, ProviderView ProView) {

		this.proPedit = ProPedit;
		this.proView = ProView;
		// NEW INSTANCE OF DATABASE FOR CONNECTION
		this.proDB = new Database();
	}

	// METHOD TO GET DATA OF THE PROVIDER LOGGED
	public void providerLogged() {

		String query = "SELECT pro_id, pro_name, pro_surname, mob_num, address, location FROM providers WHERE email='"
				+ this.proView.getProviderEmail() + "';";

		try {

			this.proDB.rs = this.proDB.stmt.executeQuery(query);

			while (this.proDB.rs.next()) {
				this.proView.setProviderID(this.proDB.rs.getInt("pro_id"));
				this.proView.setProviderName(this.proDB.rs.getString("pro_name"));
				this.proView.setProviderSurName(this.proDB.rs.getString("pro_surname"));
				this.proView.setProviderMobile(this.proDB.rs.getString("mob_num"));
				this.proView.setProviderAddress(this.proDB.rs.getString("address"));
				this.proView.setProviderLocation(this.proDB.rs.getString("location"));
			}

			this.proDB.rs.close();
			this.proDB.stmt.close();
			this.proDB.conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TO ADD AN AVAILABILITY
	public void addAvailability() {

		try {

			String query = "INSERT INTO availabilities (pro_id, date, time, available)" + "VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStmt = this.proDB.conn.prepareStatement(query);
			preparedStmt.setInt(1, this.proView.getProviderID());
			preparedStmt.setDate(2, this.proView.getDateManual());
			preparedStmt.setString(3, this.proView.getHour());
			preparedStmt.setString(4, "Yes");

			preparedStmt.execute();
			this.proDB.conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.proView, "Ups, there is a error! try again later", "Data Error!",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		JOptionPane.showMessageDialog(this.proView, "Your availability has been added");
		this.proView.UpdateFrame();

	}
	
	public void addAvailability(String hr) {

		try {

			String query = "INSERT INTO availabilities (pro_id, date, time, available)" + "VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStmt = this.proDB.conn.prepareStatement(query);
			preparedStmt.setInt(1, this.proView.getProviderID());
			preparedStmt.setDate(2, this.proView.getDateByDay());
			preparedStmt.setString(3, hr);
			preparedStmt.setString(4, "Yes");

			preparedStmt.execute();
			this.proDB.conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.proView, "Ups, there is a error! try again later", "Data Error!",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
	
	}


	// TO GET DATA OF CURRENT AVAILABILITIES
	public void availableProvTable() {

		String query = "SELECT avai_ref, date, time FROM availabilities av INNER JOIN providers pr ON av.pro_id = pr.pro_id  "
				+ "WHERE email='" + this.proView.getProviderEmail() + "' AND available='Yes';";
		String[][] data = new String[1000][3];

		try {

			this.proDB.rs = this.proDB.stmt.executeQuery(query);
			int i = 0;

			while (this.proDB.rs.next()) {

				data[i][0] = this.proDB.rs.getString("avai_ref");
				data[i][1] = this.proDB.rs.getString("date");
				data[i][2] = this.proDB.rs.getString("time");
				i++;
			}

			this.proDB.rs.close();
			this.proDB.stmt.close();
			this.proDB.conn.close();

			this.proView.setCopyDataAvai(data);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// TO GET DATA OF APPOINTMENTS
	public void toBeConfirmPro() {

		String query = "SELECT cust_name, cust_surname, date, time, availabilities.avai_ref, available, comments FROM appointments "
				+ "INNER JOIN customers ON customers.cust_id = appointments.cust_id "
				+ "INNER JOIN availabilities ON availabilities.avai_ref = appointments.avai_ref "
				+ "WHERE availabilities.pro_id = '" + this.proView.getProviderID() + "';";

		String[][] data = new String[20][7];

		try {

			this.proDB.rs = this.proDB.stmt.executeQuery(query);
			int i = 0;

			while (this.proDB.rs.next()) {

				data[i][0] = this.proDB.rs.getString("avai_ref");
				data[i][1] = this.proDB.rs.getString("cust_name");
				data[i][2] = this.proDB.rs.getString("cust_surname");
				data[i][3] = this.proDB.rs.getString("date");
				data[i][4] = this.proDB.rs.getString("time");
				data[i][5] = this.proDB.rs.getString("available");
				data[i][6] = this.proDB.rs.getString("comments");

				i++;
			}

			this.proDB.rs.close();
			this.proDB.stmt.close();
			this.proDB.conn.close();

			this.proView.setCopyDataBooked(data);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkDuplicate() {

		String query = "SELECT date FROM availabilities "
				+ "INNER JOIN providers ON availabilities.pro_id = providers.pro_id "
				+ "WHERE availabilities.date='';";

		try {
			this.proDB.rs = this.proDB.stmt.executeQuery(query);

			// This code is telling us whether we have any results
			// in our database or not
			if (this.proDB.rs.isBeforeFirst()) {
				return true;
			}

			// Close the result set, statement and the connection
			this.proDB.rs.close();
			this.proDB.stmt.close();
			this.proDB.conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}


	// SET TO CONFIRMED AN APPOINTMENT
	public void comfirmAppointPro(String avai_reference) {

		try {

			String query = "UPDATE availabilities SET available='Confirmed' WHERE avai_ref='" + avai_reference + "';";

			PreparedStatement preparedStmt = this.proDB.conn.prepareStatement(query);
			preparedStmt.execute();
			this.proDB.conn.close();

		} catch (Exception e) {

			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		JOptionPane.showMessageDialog(this.proView, "The appointment has been confirmed. Thanks");
		this.proView.UpdateFrame();
	}

	// OPTIONAL METHOD TO DELETE DATA
	public void deleteRow(String from, String column, String ID, String errorMg, String confMg) {

		boolean flag = true;

		String query = "DELETE FROM " + from + " WHERE " + column + "=" + ID + ";";

		try {

			PreparedStatement preparedStmt = this.proDB.conn.prepareStatement(query);
			preparedStmt.execute();
			this.proDB.conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.proView, errorMg, "Error", JOptionPane.ERROR_MESSAGE);
			flag = false;
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		if (flag)
			JOptionPane.showMessageDialog(this.proView, confMg);
	}

	// TO SET CALLED AN APPOINTMENT
	public void cancelAppointPro(String avai_reference, String errorMg, String confMg) {

		boolean flag = true;

		try {

			String query = "UPDATE availabilities SET available='Cancelled' WHERE avai_ref='" + avai_reference + "';";

			PreparedStatement preparedStmt = this.proDB.conn.prepareStatement(query);
			preparedStmt.execute();
			this.proDB.conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.proView, errorMg, "Error", JOptionPane.ERROR_MESSAGE);
			flag = false;
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		if (flag)
			JOptionPane.showMessageDialog(this.proView, confMg);

	}

	// TO SET COMPLETED AN APPOINTMENT
	public void setCompletedAppointPro(String avai_reference, String errorMg, String confMg) {

		boolean flag = true;

		try {

			String query = "UPDATE availabilities SET available='COMPLETED' WHERE avai_ref='" + avai_reference + "';";

			PreparedStatement preparedStmt = this.proDB.conn.prepareStatement(query);
			preparedStmt.execute();
			this.proDB.conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.proView, errorMg, "Error", JOptionPane.ERROR_MESSAGE);
			flag = false;
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		if (flag)
			JOptionPane.showMessageDialog(this.proView, confMg);

	}
	
	/////////////////////////////////////////
	/////////CUSTOMER PROFILE - EDIT/////////

	public void updateProfile(String ID, String email, String mobile, String address, String location, String errorMg, String confMg) {

		boolean flag = true;

		try {

			String query = "UPDATE providers SET email = '"+ email +"', mob_num = '"+ mobile +"', address = '"+ address +"', location = '"+ location +"'"
						 + " WHERE pro_id='"+ ID + "';";

			PreparedStatement preparedStmt = this.proDB.conn.prepareStatement(query);
			preparedStmt.execute();
			this.proDB.conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.proPedit, errorMg, "Error", JOptionPane.ERROR_MESSAGE);
			flag = false;
			System.err.println("Got an exception!");
			System.err.println(e.getMessage());
		}
		if (flag)
			JOptionPane.showMessageDialog(this.proPedit, confMg);
			this.proView.setProviderEmail(email);
			this.proView.setProviderMobile(mobile);
			this.proView.setProviderAddress(address);
			this.proView.setProviderLocation(location);

	}

}
