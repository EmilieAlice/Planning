package test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SimpleTableDemo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean DEBUG = false;

	public SimpleTableDemo() {
		super(new GridLayout(1, 0));

		int milliSecondesParJour = (1000 * 60 * 60 * 24);

		GregorianCalendar premierJour = new GregorianCalendar(2015, 05, 8, 9, 0);
		GregorianCalendar dernierJour = new GregorianCalendar(2016, 05, 12);

		long nbreJours = (dernierJour.getTimeInMillis() - premierJour
				.getTimeInMillis()) / milliSecondesParJour;

		SimpleDateFormat format = new SimpleDateFormat("dd/MM");

		String[] columnNames = {"Semaine", "LUNDI", "MARDI", "MERCREDI", "JEUDI",
				"VENDREDI" };
		int nbreSemaines = (int) (nbreJours / 7) +1;

		Object[][] data = new Object[nbreSemaines][6];

		int i = 0;
		int j = 0;
		int x = 1;
		while (i < nbreJours) {
			Date d = premierJour.getTime();
			String affiche = format.format(d);
			int jourSemaine = premierJour.get(Calendar.DAY_OF_WEEK);
			System.out.println(i);
			if (i != 0 & i % 7 == 0) {
				j++;
			}
			System.out.println(j);
			if (x != 1 & x % 6 == 0) {
				x = 1;
			}
			data[j][0] = premierJour.get(Calendar.WEEK_OF_YEAR);
			if (jourSemaine != 1 || jourSemaine != 7) {
				
				switch (jourSemaine) {
				case 2:
					data[j][x] = affiche;
					x++;
					break;
				case 3:
					data[j][x] = affiche;
					x++;
					break;
				case 4:
					data[j][x] = affiche;
					x++;
					break;
				case 5:
					data[j][x] = affiche;
					x++;
					break;
				case 6:
					data[j][x] = affiche;
					x++;
					break;
				default:
					break;
				}

			}

			premierJour.setTimeInMillis(premierJour.getTimeInMillis()
					+ milliSecondesParJour);
			i++;
		}

		;

		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		if (DEBUG) {
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					printDebugData(table);
				}
			});
		}

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		add(scrollPane);
	}

	private void printDebugData(JTable table) {
		int numRows = table.getRowCount();
		int numCols = table.getColumnCount();
		javax.swing.table.TableModel model = table.getModel();

		System.out.println("Value of data: ");
		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				System.out.print("  " + model.getValueAt(i, j));
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("SimpleTableDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		SimpleTableDemo newContentPane = new SimpleTableDemo();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
