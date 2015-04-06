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

		GregorianCalendar premierJour = new GregorianCalendar(2015, 05, 8, 9,
				0);
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
		
		Date d = premierJour.getTime();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM");
		System.out.println(format.format(d));
		
		System.out.println(premierJour.getTime());
		System.out.println(d);
		System.out.println(premierJour.get(Calendar.DAY_OF_WEEK));
		System.out.println(premierJour.get(Calendar.WEEK_OF_YEAR));
		System.out.println(premierJour.get(Calendar.DATE));
	}

}
