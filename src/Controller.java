import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller implements ActionListener {

	// GLOBAL VARIABLES - DECLARATION
	private Register register;
	private Login login;
	private AdminController adminController;
	private CustomerController custController;
	private ProviderController proController;

	// CONSTRUCTOR
	public Controller() throws ParseException {

		// NEW INSTANCE OF LOGIN VIEW
		Database cleanAvai = new Database();
		cleanAvai.cleanAvailabilities();
		this.custController = new CustomerController("rodolfo@rodolfo.com", this.login);
		//this.proController = new ProviderController("manuel@manuel.com", this.login);
		//this.adminController = new AdminController("admin@admin.admin", this.login);
		//this.login = new Login(this);
		
	}

	// ACTION LISTENER FOR LOGIN AND REGISTER
	@Override
	public void actionPerformed(ActionEvent e) {

		// OPEN REGISTER VIEW AND REQUEST TYPE OF USER
		if (e.getActionCommand().equals("Open Register")) {

			String type = "";

			// REQUEST FOR TYPE OF USER
			String[] _OPT = { "Customer", "Provider" };
			type = (String) JOptionPane.showInputDialog(this.login, "Are you?", "Registration",
					JOptionPane.PLAIN_MESSAGE, null, _OPT, _OPT[0]);

			// CREATING AN INSTANCE OF REGISTER VIEW WITH TYPE VALUE
			if (type.equals("Customer") || type.equals("Provider")) {
				this.register = new Register(this, type);
				this.login.getLogin().setVisible(false);
			} else if (type.equals(null)) {
				JOptionPane.showMessageDialog(this.login, "Please, Login");
			}

		}

		// REGISTER CUSTOMER/PROVIDER
		if (e.getActionCommand().equals("Register")) {

			// VARIABLE TO CHECK IF THE EMAIL IS ALREADY REGISTERED
			boolean custEmail, proEmail;

			// FLAG FOR REGISTER, THE USER WILL BE REGISTER JUST IF IS TRUE
			boolean flag = true;

			// DATABASE CONNECTIONS
			Database emailVerOne = new Database();
			Database emailVerTwo = new Database();

			// EMAIL VARIFICATION IN DATABASE, RETURN TRUE IF THERE IS A MATCH
			custEmail = emailVerOne.emailVerification("customers", "email", this.register.getEmail());
			proEmail = emailVerTwo.emailVerification("providers", "email", this.register.getEmail());

			// PASSWORD VALIDATION WITH JOPTIONPANE ERRORS MESSAGES
			// NAME MUST BE LETTER A-Z, NO EMPTY FIELD AND UP TO 24 CHARACTERS
			if (!this.register.getName().matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$")) {
				JOptionPane.showMessageDialog(register, "The name is not correct or it is empty, " + "try again",
						"Name Error", JOptionPane.ERROR_MESSAGE);
				// IT DOES NOT MATCH, FLAG IS SET FALSE
				flag = false;

				// SURNAME MUST BE LETTER A-Z, NO EMPTY FIELD AND UP TO 24 CHARACTERS
			}
			if (!this.register.getSur().matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$")) {
				JOptionPane.showMessageDialog(register, "The surname is not correct or empty, " + "try again",
						"Surname Error", JOptionPane.ERROR_MESSAGE);
				// IT DOES NOT MATCH, FLAG IS SET FALSE
				flag = false;

				// PASSWORD MUST BE MINIMUN 8 CHARATERS, AT LEAST ONE UPPERCASE AND LOWER,
				// SPECIAL CHARACTER AND AT LEAST ONE NUMBER
			}
			if (!this.register.getPassField()
					.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!$%^&+=])(?=\\S+$).{8,12}$")) {
				JOptionPane.showMessageDialog(register, "The password you have submited is not valid, "
						+ "it must be minimum of 8 characteres up to 12, including at least one uppercase alpha character, "
						+ "one special character '@#$%^&+=', and one number. ", "Password Invalid",
						JOptionPane.ERROR_MESSAGE);
				// IT DOES NOT MATCH, FLAG IS SET FALSE
				flag = false;

				// MOBILE NUMBER MUST BE NUMBER 0-9 AND AT LEAST 8 DIGITS UO TO 10
			}
			if (!this.register.getMob().matches("\\d{8,10}")) {
				JOptionPane.showMessageDialog(register,
						"The mobile is not correct, it must be at least 10 digits, " + "try again", "Mobile Error",
						JOptionPane.ERROR_MESSAGE);
				// IT DOES NOT MATCH, FLAG IS SET FALSE
				flag = false;

				// EMAIL MUST HAVE A SIMBOL @
			}
			if (!this.register.getEmail().matches("^(.+)@(.+)$")) {
				JOptionPane.showMessageDialog(register, "The email is not correct or it is empty, " + "try again",
						"Email Error", JOptionPane.ERROR_MESSAGE);
				// IT DOES NOT MATCH, FLAG IS SET FALSE
				flag = false;

				// ADDRESS MUST BE AT LEAST WITH 7 CHARACTERS
			}
			if (this.register.getAddress().length() <= 7) {
				JOptionPane.showMessageDialog(register, "The address is not correct or it is empty, " + "try again",
						"Address Error", JOptionPane.ERROR_MESSAGE);
				// IT DOES NOT MATCH, FLAG IS SET FALSE
				flag = false;

			}
			if (custEmail == true || proEmail == true) {
				JOptionPane.showMessageDialog(register,
						"The email is already registered, " + "please enter a different email address and try again!",
						"Email Error", JOptionPane.ERROR_MESSAGE);
				// IT DOES NOT MATCH, FLAG IS SET FALSE
				flag = false;

			}
			if (flag == true) {

				// IF THE VALIDATION DOES NOT SET FALSE THE FLAG, THEN THE USER WILL BE
				// REGISTERED
				Database data = new Database(this.register);
				data.registerUser(this.register.getUserType());
				this.login.getLogin().setVisible(true);
			}

		}

		// CANCEL REGISTRATION VIEW, GO BACK TO LOGIN
		if (e.getActionCommand().equals("Cancel")) {

			this.login.getLogin().setVisible(true);
			this.register.getReg().dispose();

		}

		// USER LOGIN
		if (e.getActionCommand().equals("Enter")) {

			// GETTING EMAIL AND PASSWORD FROM TEXTFIELDS
			String email = login.getEmail();
			String password = login.getPassField();

			// CHECKING IF THE CUSTOMER IS REGISTER IN DATABASE, RETURN TRUE IF THERE IS A
			// MATCH
			Database connCust = new Database(login);
			boolean resultOne = connCust.loginUser("customers", email, password, false);

			// CHECKING IF THE PROVIDER IS REGISTER IN DATABASE, RETURN TRUE IF THERE IS A
			// MATCH
			Database connProv = new Database(login);
			boolean resultTwo = connProv.loginUser("providers", email, password, false);

			// CHECKING IF THE ADMINISTRATOR IS REGISTER IN DATABASE, RETURN TRUE IF THERE
			// IS A MATCH
			Database connAdmin = new Database(login);
			boolean resultThree = connAdmin.loginUser("administrators", email, password, true);

			// CHECKING STATUS OF PROVIDER
			Database proSta = new Database(login);
			String status = proSta.getProStatus(email);

			// IF THERE IS A MATH IN ANY CASE
			if (resultOne == true || resultTwo == true || resultThree == true) {
				// CUSTOMER LOGIN
				if (resultOne) {
					JOptionPane.showMessageDialog(this.login, "Welcome Customer!!");
					this.custController = new CustomerController(email, this.login);

					// ERASE LOGIN AND PASSWORD FROM TEXTFIELDS IN LOGIN
					this.login.setEmail().setText("");
					this.login.setJPass().setText("");
					this.login.getLogin().setVisible(false); // HIDE LOGIN VIEW

					// ADMIN LOGIN
				} else if (resultThree == true) {
					JOptionPane.showMessageDialog(this.login, "Welcome Admin!!");
					this.adminController = new AdminController(email, this.login);

					// ERASE LOGIN AND PASSWORD FROM TEXTFIELDS IN LOGIN
					this.login.setEmail().setText("");
					this.login.setJPass().setText("");
					this.login.getLogin().setVisible(false);

					// PROVIDER LOGIN
				} else if (resultTwo == true && status.equals("Confirmed")) {
					JOptionPane.showMessageDialog(this.login, "Welcome Provider!!");
					this.proController = new ProviderController(email, this.login);

					// ERASE LOGIN AND PASSWORD FROM TEXTFIELDS IN LOGIN
					this.login.setEmail().setText("");
					this.login.setJPass().setText("");
					this.login.getLogin().setVisible(false); // HIDE LOGIN VIEW

					// IF PROVIDER STATUS IS PENDING, SHOW A NOTIFICATION
				} else if (resultTwo == true && status.equals("Pending")) {
					JOptionPane.showMessageDialog(this.login, "Your account is not CONFIRMED, please wait or contact "
							+ "an administrator to validate your information account, thanks!");
				}
			}

			// IF THERE IS NOT MATCH FOR EMAIL AND PASSWORD
			else {
				JOptionPane.showMessageDialog(this.login, "Incorrect, please try again!");
			}
		}

	}

}
