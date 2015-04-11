package swing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.table.AbstractTableModel;

import dao.ModuleDao;
import dao.SeanceDao;
import dao.SessionDao;
import modele.Seance;
import modele.Seance.Creneau;
import modele.Session;

public class DonneesTableauDouble extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int milliSecondesParJour = (1000 * 60 * 60 * 24);

	// Pour formater une date au format désiré
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");

	// On remplit le nom des colonnes de titre
	private final String[] columnNames = { "Semaine", "LUNDI Matin",
			"Après-midi", "MARDI Matin", "Après-midi", "MERCREDI Matin",
			"Après-midi", "JEUDI Matin", "Après-midi", "VENDREDI matin",
			"Après-midi" };

	// On créé un tableau d'objet à deux dimensions pour remplir notre
	// JTable
	private final Object[][] data;

	public DonneesTableauDouble(int idSession) {

		Session session = new Session();
		SessionDao sessionDao = new SessionDao();
		session = sessionDao.findSessionById(idSession);
		GregorianCalendar premierJour = session.getDateDebut();
		GregorianCalendar dernierJour = session.getDateFin();

		long nbreJours = (dernierJour.getTimeInMillis() - premierJour
				.getTimeInMillis()) / milliSecondesParJour;

		// On récupère le nombre de semaines correspodant au nombre de jours
		int nbreSemaines = (int) (nbreJours / 7) + 1;
		int tailleTableau = nbreSemaines * 2 + 1;

		data = new Object[tailleTableau][columnNames.length];

		ArrayList<Integer> numeroSemaine = new ArrayList<Integer>();

		int i = 0;
		int numeroLigne = 0;
		int numeroColonne = 1;

		while (i < nbreJours) {
			Date date = premierJour.getTime();
			String affiche = format.format(date);
			int jourSemaine = premierJour.get(Calendar.DAY_OF_WEEK);

			// Si le numéro de la colonne est divisible par 11 alors on est à la
			// ligne suivante donc on remet ce numéro à 1 pour écrire à Lundi
			if (numeroColonne != 1 & numeroColonne % 11 == 0) {
				numeroColonne = 1;
			}
			// La première case de chaque ligne est toujours le numéro de la
			// semaine
			data[numeroLigne][0] = premierJour.get(Calendar.WEEK_OF_YEAR);

			if (numeroColonne == 1 && numeroLigne % 2 == 0) {
				numeroSemaine.add(premierJour.get(Calendar.WEEK_OF_YEAR));
			}

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
						data[numeroLigne][1] = affiche;
						numeroColonne++;
						data[numeroLigne][numeroColonne] = "";
						numeroColonne++;
						break;
					case 3:
						data[numeroLigne][3] = affiche;
						numeroColonne++;
						data[numeroLigne][numeroColonne] = "";
						numeroColonne++;
						break;
					case 4:
						data[numeroLigne][5] = affiche;
						numeroColonne++;
						data[numeroLigne][numeroColonne] = "";
						numeroColonne++;
						break;
					case 5:
						data[numeroLigne][7] = affiche;
						numeroColonne++;
						data[numeroLigne][numeroColonne] = "";
						numeroColonne++;
						break;
					case 6:
						data[numeroLigne][9] = affiche;
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
		ModuleDao moduleDao = new ModuleDao();
		SeanceDao seanceDao = new SeanceDao();

		ArrayList<Seance> listeSeance = new ArrayList<Seance>();
		listeSeance = seanceDao.findSeanceByIdSession(idSession);

		for (Seance seance : listeSeance) {
			int ligneSemaine = 0;
			int colonneJour = 0;
			String nomModule;

			nomModule = moduleDao.findModuleById(seance.getIdModule())
					.getNomModule();

			for (Integer numero : numeroSemaine) {
				if (seance.getDebut().get(Calendar.WEEK_OF_YEAR) == numero) {
					ligneSemaine = numeroSemaine.indexOf(numero) * 2 + 1;
				}
			}
			if (seance.getDebut().get(Calendar.DAY_OF_WEEK) == 2) {
				if (seance.getCreneau().equals(Creneau.APRES_MIDI)) {
					colonneJour = 2;
				} else {
					colonneJour = 1;
				}
			} else if (seance.getDebut().get(Calendar.DAY_OF_WEEK) == 3) {
				if (seance.getCreneau().equals(Creneau.APRES_MIDI)) {
					colonneJour = 4;
				} else {
					colonneJour = 3;
				}
			} else if (seance.getDebut().get(Calendar.DAY_OF_WEEK) == 4) {
				if (seance.getCreneau().equals(Creneau.APRES_MIDI)) {
					colonneJour = 6;
				} else {
					colonneJour = 5;
				}
			} else if (seance.getDebut().get(Calendar.DAY_OF_WEEK) == 5) {
				if (seance.getCreneau().equals(Creneau.APRES_MIDI)) {
					colonneJour = 8;
				} else {
					colonneJour = 7;
				}
			} else if (seance.getDebut().get(Calendar.DAY_OF_WEEK) == 6) {
				if (seance.getCreneau().equals(Creneau.APRES_MIDI)) {
					colonneJour = 10;
				} else {
					colonneJour = 9;
				}
			}

			if (ligneSemaine != 0 && colonneJour != 0) {
				this.setValueAt(nomModule, ligneSemaine, colonneJour);
			}

		}
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
