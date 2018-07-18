package Project3;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.util.*;

public class RentalStore extends AbstractListModel {

	private ArrayList<DVD> DVDList;

	private boolean filter;

	public RentalStore() {
		super();
		filter = false;
		DVDList = new ArrayList<DVD>();
	}

	public void add (DVD a) {
		DVDList.add(a);
		fireIntervalAdded(this, 0, DVDList.size());
	}

	public void remove (int index) {
		DVDList.remove(index);
		fireIntervalRemoved(this, 0, DVDList.size());
	}

	public DVD get (int i) {
		return DVDList.get(i);
	}

	public Object getElementAt(int index) {
		DVD unit = DVDList.get(index);

		//	String rentedOnDateStr = DateFormat.getDateInstance(DateFormat.SHORT)
		//				.format(unit.getRentedOn().getTime());

		String line = "Name: " + " " + DVDList.get(index).getNameOfRenter() +
				"  Title: " + DVDList.get(index).getTitle();

		//		if (unit instanceof Game)
		//			line += ", Car Player: " + ((Game)unit).getPlayer();

		return line;
	}

	public int getSize() {
		return DVDList.size();
	}

	public void saveAsSerializable(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(DVDList);
			os.close();
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error saving db");
		}
	}

	public void loadFromSerializable(String filename) {
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream is = new ObjectInputStream(fis);

			DVDList = (ArrayList<DVD>) is.readObject();
			fireIntervalAdded(this, 0, DVDList.size() - 1);
			is.close();
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error loading db");
		}
	}
}
