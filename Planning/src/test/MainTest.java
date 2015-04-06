package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dao.SeanceDao;
import modele.Seance;

public class MainTest {

	public static void main(String[] args) {

		int milliSecondesParJour = (1000 * 60 * 60 * 24);

		GregorianCalendar premierJour = new GregorianCalendar(2015, 05, 8, 9, 0);
		GregorianCalendar dernierJour = new GregorianCalendar(2016, 05, 12);

		long nbreJours = (dernierJour.getTimeInMillis() - premierJour
				.getTimeInMillis()) / milliSecondesParJour;

		System.out.println(nbreJours);

		ArrayList<Seance> listeSeance = new ArrayList<Seance>();
		SeanceDao seanceDao = new SeanceDao();
		listeSeance = seanceDao.findSeanceByIdSession(1);
		GregorianCalendar jour = new GregorianCalendar(2015, 0, 12);
		Seance.Creneau creneau = Seance.Creneau.MATIN;

		Seance seance = new Seance(1, 1, 1, jour, creneau, 4, "coucou");

		System.out.println(seance);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM");

		int i = 0;
		while (i < nbreJours) {
			Date d = premierJour.getTime();
			String affiche = format.format(d);
			int jourSemaine = premierJour.get(Calendar.DAY_OF_WEEK);
			if(i%7 == 0){
				System.out.println("Semaine "+ premierJour.get(Calendar.WEEK_OF_YEAR));
			}
			if (jourSemaine != 1 || jourSemaine != 7) {
				switch (jourSemaine) {
				case 2:
					System.out.println("Lundi " + affiche);
					break;
				case 3:
					System.out.println("Mardi " + affiche);
					break;
				case 4:
					System.out.println("Mercredi " + affiche);
					break;
				case 5:
					System.out.println("Jeudi " + affiche);
					break;
				case 6:
					System.out.println("Vendredi " + affiche);
					break;
				default:
					break;
				}
			}

			premierJour
					.setTimeInMillis(premierJour.getTimeInMillis() + milliSecondesParJour);
			i++;
		}

		System.out.println();
		System.out.println(premierJour.get(Calendar.DAY_OF_WEEK));
		System.out.println(premierJour.get(Calendar.WEEK_OF_YEAR));
		System.out.println(premierJour.get(Calendar.DATE));
	}

}
