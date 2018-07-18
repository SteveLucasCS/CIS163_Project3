package Project3;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class RentGameDialog  extends JDialog implements ActionListener {

	/** List of available PlayerTypes that will appear in the playerList **/
	private final String[] SYSTEM_LIST = {"Xbox One", "Xbox 360",
			"PS4", "Nintendo Switch", "WiiU"};

	/** Text field where user enters the name of the rented game **/
	private JTextField titleTxt;

	/** JList for the user to choose the game system the game is for **/
	private JTextField playerTxt;

	/** Text field where user enters their own name **/
	private JTextField renterTxt;

	/** Text field that displays the date the game was rented on **/
	private JTextField rentedOnTxt;

	/** Text field that displays the date the game is due back **/
	private JTextField DueBackTxt;

	/** Button to confirm and submit rental dialog **/
	private JButton okButton;

	/** Button to cancel the current dialog **/
	private JButton cancelButton;

	/** closeStatus will be 'true' if the dialog closed properly **/
	private boolean closeStatus;

	private Container con;

	private Game unit;
	public RentGameDialog(JFrame parent, Game d) {
		// call parent and create a 'modal' dialog
		super(parent, true);

		con = getContentPane();

		setTitle("Rent a Game:");
		closeStatus = false;
		setSize(400, 200);

		unit = d;
		// prevent user from closing window
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		// instantiate and display text fields

		JPanel textPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane();

		textPanel.setLayout(new GridLayout(6, 2));

		/* Text box for input of renter's name */
		textPanel.add(new JLabel("Your Name:"));
		renterTxt = new JTextField("John Doe", 30);
		textPanel.add(renterTxt);

		/* Text box for input of game title */
		textPanel.add(new JLabel("Title of Game:"));
		titleTxt = new JTextField("Avengers", 30);
		textPanel.add(titleTxt);

		/* Text box for input of game system */
		textPanel.add(new JLabel("Game System:"));
		playerTxt = new JTextField("Xbox One", 30);
		textPanel.add(playerTxt);

		/* Gets today's date */
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		/* Displays the date the game was rented on */
		textPanel.add(new JLabel("Rented on Date: "));
		rentedOnTxt = new JTextField(df.format(date), 30);
		textPanel.add(rentedOnTxt);

		/* Calculates the date the game is due back */
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);  // number of days to add
		date = c.getTime();

		/* Displays the date the game is due to be returned */
		textPanel.add(new JLabel("Due Back: "));
		DueBackTxt = new JTextField(df.format(date), 15);
		textPanel.add(DueBackTxt);

		con.add(textPanel, BorderLayout.CENTER);

		// Instantiate and display two buttons
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		con.add(buttonPanel, BorderLayout.SOUTH);

		okButton.addActionListener(this);
		cancelButton.addActionListener(this);

		setSize(300, 300);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		JButton button = (JButton) e.getSource();

		// if OK clicked the fill the object
		if (button == okButton) {
			// save the information in the object
			closeStatus = true;

			unit.setNameOfRenter(renterTxt.getText());
			unit.setTitle(titleTxt.getText());
			unit.setBought(textToCalendar(rentedOnTxt.getText()));
			unit.setDueBack(textToCalendar(DueBackTxt.getText()));
			// User input for PlayerType parsed
			playerTxt.setText(playerTxt.getText().toLowerCase());
			if (playerTxt.getText().matches("(^xbox1$)|(^xbox360$)|"
					+ "(^wiiu$)|(^ps4$)|(^nintendoswitch$)")) {
				
				if (playerTxt.getText().contains("xbox1"))
					unit.setPlayerType(PlayerType.XBox1);
				else if (playerTxt.getText().contains("xbox360"))
					unit.setPlayerType(PlayerType.Xbox360);
				else if (playerTxt.getText().contains("wiiu"))
					unit.setPlayerType(PlayerType.WiiU);
				else if (playerTxt.getText().contains("ps4"))
					unit.setPlayerType(PlayerType.PS4);
				else if (playerTxt.getText().contains("nintendoswitch"))
					unit.setPlayerType(PlayerType.NintendoSwitch);
				else {
					JOptionPane.showMessageDialog(null, "Game System Not Found");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Game System Not Found");
				
			}
		}

		// make the dialog disappear
		dispose();
	}

	/****************************************************************************
	 * Converts a String input into a GregorianCalendar date and returns it.
	 * @param text is a date in the form of a string
	 * @return GregorianCalendar date converted from text input. Will return
	 * today's date if the input is invalid
	 */
	private GregorianCalendar textToCalendar(String text) {
		int tempDay = 0;
		int tempMonth = 0;
		int tempYear = 0;
		Scanner scnr = new Scanner(text);
		
		//Validate and set month
		if (scnr.hasNextInt()) {
			tempMonth = scnr.nextInt();
			if (tempMonth > 12)
				tempMonth = tempMonth / 12 + tempMonth % 12;
			else if (tempMonth < 1) {
				JOptionPane.showMessageDialog(null, "Invalid Date");
				scnr.close();
				return null;
			}
		}
		//Validate and set day
		if (scnr.hasNextInt()) {
			tempDay = scnr.nextInt();
			if (tempDay < 1) {
				JOptionPane.showMessageDialog(null, "Invalid Date");
				scnr.close();
				return new GregorianCalendar();
			}
			if (tempMonth % 2 == 0 && tempMonth != 2) 
				if (tempDay > 31)
					tempDay = tempDay / 31 + tempDay % 31;
			else if (tempMonth == 2)
				if (tempDay > 28)
					tempDay = tempDay / 28 + tempDay % 28;
			else 
				if (tempDay > 30)
					tempDay = tempDay / 30 + tempDay % 30;
		}
		//Validate and set year
		if (scnr.hasNextInt()) {
			tempYear = scnr.nextInt();
		}
		if (tempMonth == 0 || tempDay == 0 || tempYear == 0) {
			JOptionPane.showMessageDialog(null, "Invalid Date");
			scnr.close();
			return new GregorianCalendar();
		}
		
		GregorianCalendar dueBack = new GregorianCalendar(
				tempYear, tempMonth, tempDay);
		scnr.close();
		//returns converted date
		return dueBack;
	}
	
	public boolean closeOK() {
		return closeStatus;
	}
}
