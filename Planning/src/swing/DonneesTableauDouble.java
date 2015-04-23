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

	private static final long serialVersionUID = 1L;

	/* Nombre de millisecondes dans une journée */
	private int milliSecondesParJour = (1000 * 60 * 60 * 24);

	/* Pour formater une date au format désiré */
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");

	/* On remplit le nom des colonnes de titre */
	private final String[] columnNames = { "Semaine", "LUNDI",
			"Après-midi", "MARDI", "Après-midi", "MERCREDI",
			"Après-midi", "JEUDI", "Après-midi", "VENDREDI",
			"Après-midi" };

	/* On créé un tableau d'objet à deux dimensions pour remplir notre JTable */
	private final Object[][] data;

	/**
	 * Constructeur qui prend en paramètre l'id de la session
	 * 
	 * @param idSession
	 */
	public DonneesTableauDouble(int idSession) {

		/* On récupère la session */
		Session session = new Session();
		SessionDao sessionDao = new SessionDao();
		session = sessionDao.findSessionById(idSession);

		/* On récupère le premier et dernier jour de la session */
		GregorianCalendar premierJour = session.getDateDebut();
		GregorianCalendar dernierJour = session.getDateFin();

		/* On calcule le nombre de jour de la session */
		long nbreJours = (dernierJour.getTimeInMillis() - premierJour
				.getTimeInMillis()) / milliSecondesParJour;

		/* On récupère le nombre de semaines correspodant au nombre de jours */
		int nbreSemaines = (int) (nbreJours / 7) + 1;
		int tailleTableau = nbreSemaines * 2;

		/*
		 * On instancie le tableau de données avec les informations récupérées
		 * précédemment
		 */
		data = new Object[tailleTableau][columnNames.length];

		ArrayList<Integer> numeroSemaine = new ArrayList<Integer>();

		/* On initialise les parcoureurs pour remplir le tableau */
		int i = 0;
		int numeroLigne = 0;
		int numeroColonne = 1;

		while (i < nbreJours) {

			/* On récupère les infos du jour */
			Date date = premierJour.getTime();
			String affiche = format.format(date);
			int jourSemaine = premierJour.get(Calendar.DAY_OF_WEEK);

			/*
			 * Si le numéro de la colonne est divisible par 11 alors on est à la
			 * ligne suivante, donc on remet le numéro de colonne à 1 pour
			 * écrire à Lundi
			 */
			if (numeroColonne != 1 & numeroColonne % 11 == 0) {
				numeroColonne = 1;
			}

			/*
			 * La première case de chaque ligne est toujours le numéro de la
			 * semaine
			 */
			data[numeroLigne][0] = premierJour.get(Calendar.WEEK_OF_YEAR);

			/*
			 * On remplit la collection des numéros de semaines pour placer les
			 * séances déjà présentes dans la base au bon endroit
			 */
			if (numeroColonne == 1 && numeroLigne % 2 == 0) {
				numeroSemaine.add(premierJour.get(Calendar.WEEK_OF_YEAR));
			}

			/*
			 * Si le numéro correpondant de la ligne n'est pas 0 ou paire on
			 * remplit la ligne avec du vide
			 */
			if (numeroLigne != 0 & numeroLigne % 2 != 0) {
				for (int y = 0; y < columnNames.length; y++) {
					data[numeroLigne][y] = "";
				}
				numeroLigne++;
			} else {
				if (jourSemaine != 1 || jourSemaine != 7) {
					/*
					 * On étudie chaque jour de la semaine, on remplit le
					 * tableau et on remplit la colonne suivante avec du vide
					 */
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
				/*
				 * On ajoute une journée en milliseconde pour passer au jour
				 * suivant, et on ajoute 1 au parcoureur
				 */
				premierJour.setTimeInMillis(premierJour.getTimeInMillis()
						+ milliSecondesParJour);
				i++;
			}
		}
		;

		ModuleDao moduleDao = new ModuleDao();
		SeanceDao seanceDao = new SeanceDao();

		/* On récupère la liste des séances d'une session */
		ArrayList<Seance> listeSeance = new ArrayList<Seance>();
		listeSeance = seanceDao.findSeanceByIdSession(idSession);

		for (Seance seance : listeSeance) {
			int ligneSemaine = 0;
			int colonneJour = 0;
			String nomModule;

			nomModule = moduleDao.findModuleById(seance.getIdModule())
					.getNomModule();

			/*
			 * On parcourt la collection qui contient les numéros de semaine si
			 * la séance présente dans la base a une semaine qui correspond à un
			 * des éléments de cette collection on lui attribue le bon numéro de
			 * ligne
			 */
			for (Integer numero : numeroSemaine) {
				if (seance.getDebut().get(Calendar.WEEK_OF_YEAR) == numero) {
					ligneSemaine = numeroSemaine.indexOf(numero) * 2 + 1;
				}
			}
			/*
			 * On compare le numéro du jour de la séance et on lui attribue un
			 * numéro de colonne
			 */
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
			/*
			 * Si le numéro de la ligne et de la colonne est diférent de 0 on
			 * inscrit la séance (le nom du module) dans le tableau
			 */
			if (ligneSemaine != 0 && colonneJour != 0) {
				this.setValueAt(nomModule, ligneSemaine, colonneJour);
			}
		}
	}

	/**
	 * Toutes les méthodes de la classe AbstractTableModel pour récupérer et 
	 * savoir si une cellule est éditable et écrire dans une cellule
	 */
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
