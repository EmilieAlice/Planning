package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import dao.SeanceDao;
import modele.Seance;

public class MainTest {

	public static void main(String[] args) {
		
		int milliSecondesParJour = (1000 * 60 * 60 * 24);

		GregorianCalendar premierJour = new GregorianCalendar(2015, 05, 07);
		GregorianCalendar dernierJour = new GregorianCalendar(2016, 05, 12);

		long nbreJours = (dernierJour.getTimeInMillis() - premierJour
				.getTimeInMillis()) / milliSecondesParJour;
		
		System.out.println(nbreJours);
		
		ArrayList<Seance> listeSeance = new ArrayList<Seance>();
		SeanceDao seanceDao = new SeanceDao();
		listeSeance = seanceDao.findSeanceByIdSession(1);
		
		for (Seance seance : listeSeance) {
			System.out.println(seance);
		}

	}

}
