package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class LateReportModal extends JDialog {

	private Container con;

	/**
	 * Holds the list engine
	 */
	private RentalStore lateList;

	/**
	 * Holds JListArea
	 */
	private JList JListArea;

	public LateReportModal(JFrame parent, Date d, RentalStore list) {
		// call parent and create a 'modal' dialog
		super(parent, true);

		con = getContentPane();

		setTitle("Late Unit Report");
		setSize(300, 300);

		lateList = new RentalStore();
		JListArea = new JList(lateList);
		con.add(JListArea, BorderLayout.CENTER);


		// Instantiate and display two buttons
		// okButton = new JButton("OK");
		// cancelButton = new JButton("Cancel");
		// JPanel buttonPanel = new JPanel();
		// buttonPanel.add(okButton);
		// buttonPanel.add(cancelButton);
		// con.add(buttonPanel, BorderLayout.SOUTH);

		// okButton.addActionListener(this);
		// cancelButton.addActionListener(this);

		setVisible(true);
	}

	// public void actionPerformed(ActionEvent e) {
	//
	// 	JButton button = (JButton) e.getSource();
	//
	// 	// if OK clicked the fill the object
	// 	if (button == okButton) {
	// 		// save the information in the object
	// 		closeStatus = true;
	//
	// 		// TODO: set all props for dvd
	// 		unit.setNameOfRenter(renterTxt.getText());
	// 		unit.setTitle(titleTxt.getText());
	// 		// unit.set
	//
	// 	}
	//
	// 	// make the dialog disappear
	// 	dispose();
	// }

	// public boolean closeOK() {
	// 	return closeStatus;
	// }
}
