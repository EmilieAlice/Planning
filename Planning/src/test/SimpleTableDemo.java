package test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

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
		GregorianCalendar dernierJour = new GregorianCalendar(2016, 02, 12);

		long nbreJours = (dernierJour.getTimeInMillis() - premierJour
				.getTimeInMillis()) / milliSecondesParJour;

		// Pour formater une date au format désiré
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YY");

		// On remplit le nom des colonnes de titre
		String[] columnNames = { "Semaine", "LUNDI", "MARDI", "MERCREDI",
				"JEUDI", "VENDREDI" };

		// On récupère le nombre de semaines correspodant au nombre de jours
		int nbreSemaines = (int) (nbreJours / 7) + 1;

		// On créé un tableau d'objet à deux dimensions pour remplir notre
		// JTable
		Object[][] data = new Object[nbreSemaines][columnNames.length];

		// On créé trois parcoureurs pour remplir le tableau
		int i = 0;
		int numeroLigne = 0;
		int numeroColonne = 1;

		while (i <= nbreJours) {
			Date date = premierJour.getTime();
			String affiche = format.format(date);
			int jourSemaine = premierJour.get(Calendar.DAY_OF_WEEK);

			// Si le parcoureur de jours est divisible par 7 alors on passe à la
			// semaine suivante
			if (i != 0 & i % 7 == 0) {
				numeroLigne++;
			}
			// Si le numéro de la colonne est divisible par 6 alors on est à la
			// ligne suivante donc on remet ce numéro à 1 pour écrire à Lundi
			if (numeroColonne != 1 & numeroColonne % 6 == 0) {
				numeroColonne = 1;
			}
			// La première case de chaque ligne est toujours le numéro de la
			// semaine
			data[numeroLigne][0] = premierJour.get(Calendar.WEEK_OF_YEAR);

			// Si le numéro correpondant au jour de la semaine n'est pas 1
			// (dimanche) ou 7 (samedi) on ajoute des données au tableau
			if (jourSemaine != 1 || jourSemaine != 7) {

				// On étudie chaque jour de la semaine, on remplit le tableau et
				// on avance la colonne de 1
				switch (jourSemaine) {
				case 2:
					data[numeroLigne][numeroColonne] = affiche;
					numeroColonne++;
					break;
				case 3:
					data[numeroLigne][numeroColonne] = affiche;
					numeroColonne++;
					break;
				case 4:
					data[numeroLigne][numeroColonne] = affiche;
					numeroColonne++;
					break;
				case 5:
					data[numeroLigne][numeroColonne] = affiche;
					numeroColonne++;
					break;
				case 6:
					data[numeroLigne][numeroColonne] = affiche;
					numeroColonne++;
					break;
				default:
					break;
				}

			}
			// On ajoute une journée en milliseconde pour passer au jour suivant
			// et on ajoute 1 au parcoureur
			premierJour.setTimeInMillis(premierJour.getTimeInMillis()
					+ milliSecondesParJour);
			i++;
		}

		;

		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		// Pour ajouter le mode de sélection unique de cellule à unique et à
		// seulement une cellule
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);

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
