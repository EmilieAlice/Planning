package swing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import modele.Session;

public class DonneesTableauDouble extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Session session;

	public DonneesTableauDouble(Session session) {
		super();
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	int milliSecondesParJour = (1000 * 60 * 60 * 24);
	/*
	 * GregorianCalendar premierJour = session.getDateDebut(); GregorianCalendar
	 * dernierJour = session.getDateFin();
	 */

	GregorianCalendar premierJour = new GregorianCalendar(2015, 05, 8, 9, 0);
	GregorianCalendar dernierJour = new GregorianCalendar(2016, 01, 12);

	long nbreJours = (dernierJour.getTimeInMillis() - premierJour
			.getTimeInMillis()) / milliSecondesParJour;

	// Pour formater une date au format désiré
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");

	// On remplit le nom des colonnes de titre
	private final String[] columnNames = { "Semaine", "LUNDI Matin",
			"Après-midi", "MARDI Matin", "Après-midi", "MERCREDI Matin",
			"Après-midi", "JEUDI Matin", "Après-midi", "VENDREDI matin",
			"Après-midi" };

	// On récupère le nombre de semaines correspodant au nombre de jours
	int nbreSemaines = (int) (nbreJours / 7) + 1;
	int tailleTableau = nbreSemaines * 2;

	// On créé un tableau d'objet à deux dimensions pour remplir notre
	// JTable
	private final Object[][] data = new Object[tailleTableau][columnNames.length];

	public DonneesTableauDouble() {

		int i = 0;
		int numeroLigne = 0;
		int numeroColonne = 1;
		while (i <= nbreJours) {
			Date date = premierJour.getTime();
			String affiche = format.format(date);
			int jourSemaine = premierJour.get(Calendar.DAY_OF_WEEK);

			// Si le parcoureur de jours est divisible par 7 alors on passe à la
			// semaine suivante
			/*
			 * if (i != 0 & i % 7 == 0) { numeroLigne++; }
			 */
			// Si le numéro de la colonne est divisible par 6 alors on est à la
			// ligne suivante donc on remet ce numéro à 1 pour écrire à Lundi
			if (numeroColonne != 1 & numeroColonne % 11 == 0) {
				numeroColonne = 1;
			}
			// La première case de chaque ligne est toujours le numéro de la
			// semaine
			data[numeroLigne][0] = premierJour.get(Calendar.WEEK_OF_YEAR);

			// Si le numéro correpondant au jour de la semaine n'est pas 1
			// (dimanche) ou 7 (samedi) on ajoute des données au tableau

			if (numeroLigne != 0 & numeroLigne % 2 != 0) {
				for (int y = 0; y < columnNames.length; y++) {
					data[numeroLigne][y] = "";
				}
				numeroLigne++;
			} else {
				if (jourSemaine != 1 || jourSemaine != 7) {

					// On étudie chaque jour de la semaine, on remplit le
					// tableau et
					// on avance la colonne de 1
					switch (jourSemaine) {
					case 2:
						data[numeroLigne][numeroColonne] = affiche;
						numeroColonne++;
						data[numeroLigne][numeroColonne] = "";
						numeroColonne++;
						break;
					case 3:
						data[numeroLigne][numeroColonne] = affiche;
						numeroColonne++;
						data[numeroLigne][numeroColonne] = "";
						numeroColonne++;
						break;
					case 4:
						data[numeroLigne][numeroColonne] = affiche;
						numeroColonne++;
						data[numeroLigne][numeroColonne] = "";
						numeroColonne++;
						break;
					case 5:
						data[numeroLigne][numeroColonne] = affiche;
						numeroColonne++;
						data[numeroLigne][numeroColonne] = "";
						numeroColonne++;
						break;
					case 6:
						data[numeroLigne][numeroColonne] = affiche;
						numeroColonne++;
						data[numeroLigne][numeroColonne] = "";
						numeroColonne++;
						numeroLigne++;
						break;
					default:
						break;
					}

				}
				// On ajoute une journée en milliseconde pour passer au jour
				// suivant
				// et on ajoute 1 au parcoureur
				premierJour.setTimeInMillis(premierJour.getTimeInMillis()
						+ milliSecondesParJour);
				i++;
			}
		}

		;
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if (row % 2 != 0) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
	}

}
