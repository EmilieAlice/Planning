package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.table.AbstractTableModel;

public class DonneesTableau extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int milliSecondesParJour = (1000 * 60 * 60 * 24);

	GregorianCalendar premierJour = new GregorianCalendar(2015, 05, 8, 9, 0);
	GregorianCalendar dernierJour = new GregorianCalendar(2016, 02, 12);

	long nbreJours = (dernierJour.getTimeInMillis() - premierJour
			.getTimeInMillis()) / milliSecondesParJour;

	// Pour formater une date au format désiré
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/YY");

	// On remplit le nom des colonnes de titre
	private final String[] columnNames = { "Semaine", "LUNDI", "MARDI", "MERCREDI",
			"JEUDI", "VENDREDI" };

	// On récupère le nombre de semaines correspodant au nombre de jours
	int nbreSemaines = (int) (nbreJours / 7) + 1;

	// On créé un tableau d'objet à deux dimensions pour remplir notre
	// JTable
	private final Object[][] data = new Object[nbreSemaines][columnNames.length];
			

	
	public DonneesTableau(){
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
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
