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
		
		//ArrayList<Seance> listeSeanceAttendue = new ArrayList<Seance>();
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance.Creneau creneau = Seance.Creneau.MATIN;
		//Seance seance = new Seance(1, 1, 4, jour, creneau, 1, "Première séance de SI2 avec découverte du fonctionnement d'internet");
		String contenu = "Première séance de SI2 avec découverte du fonctionnement d'internet";
		//listeSeanceAttendue.add(seance);

		for (Seance seance : listeSeance) {
			assertEquals(1, seance.getIdModule());
			assertEquals(1, seance.getIdSession());
			assertEquals(4, seance.getIdFormateur());
			assertEquals(jour, seance.getJour());
			assertEquals(creneau, seance.getCreneau());
			assertEquals(1, seance.getIdSalle());
			assertEquals(contenu, seance.getContenu());
		}
			
		}
		


}
