package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import modele.Seance;
import modele.Session;

import org.junit.Test;

public class SeanceDaoTest {

	@Test
	public void testFindSeanceByIdSession() {
		ArrayList<Seance> listeSeance = new ArrayList<Seance>();
		SeanceDao seanceDao = new SeanceDao();
		listeSeance = seanceDao.findSeanceByIdSession(1);
		
		ArrayList<Seance> listeSeanceAttendue = new ArrayList<Seance>();
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance.Creneau creneau = Seance.Creneau.MATIN;
		Seance seance = new Seance(1, 1, 4, jour, creneau, 1, "Première séance de SI2 avec découverte du fonctionnement d'internet");
	}

}
