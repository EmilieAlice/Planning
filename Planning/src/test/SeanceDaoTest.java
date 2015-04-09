package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import modele.Planning;
import modele.Seance;
import modele.Session;

import org.junit.Test;

import dao.PlanningDao;
import dao.SeanceDao;

public class SeanceDaoTest {

	@Test
	public void testFindSeanceByIdSession() {
		ArrayList<Seance> listeSeance = new ArrayList<Seance>();
		SeanceDao seanceDao = new SeanceDao();
		listeSeance = seanceDao.findSeanceByIdSession(1);

		// ArrayList<Seance> listeSeanceAttendue = new ArrayList<Seance>();
		GregorianCalendar jour = new GregorianCalendar(2015, 05, 02);
		Seance.Creneau creneau = Seance.Creneau.MATIN;
		// Seance seance = new Seance(1, 1, 4, jour, creneau, 1, "Première séance de SI2 avec découverte du fonctionnement d'internet");
		String contenu = "Première séance de SI2 avec découverte du fonctionnement d'internet";
		// listeSeanceAttendue.add(seance);

		for (Seance seance : listeSeance) {
			assertEquals(1, seance.getIdModule());
			assertEquals(1, seance.getIdSession());
			assertEquals(4, seance.getIdFormateur());
			assertEquals(jour, seance.getDebut());
			assertEquals(creneau, seance.getCreneau());
			assertEquals(1, seance.getIdSalle());
			assertEquals(contenu, seance.getContenu());
		}

	}
	

	@Test
	public void testInsertSeance() throws SQLException {
		// On créé une seance
		GregorianCalendar debut = new GregorianCalendar(2015, 06, 02, 14, 00, 00);
		GregorianCalendar fin = new GregorianCalendar(2015, 06, 02, 17, 00, 00);
		Seance seance = new Seance(1, 1, 4, debut, fin, Seance.Creneau.APRES_MIDI, 1, null);
		SeanceDao seanceDao = new SeanceDao();
		
		seanceDao.insertSeance(seance);

	}
	

	@Test
	public void testDeleteSeance() throws SQLException {
		/*Planning planning = new Planning();
		PlanningDao planningDao = new PlanningDao();
		planning = planningDao.findByIdSession(1);
		
		// On créé une seance
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);

		
		// On créé une durée de seance
		Duration dureeSeance = Duration.parse("PT3H30M");
		Duration dureeAvant = planning.getModule(seance).getDureeDisponible();
		// On supprime celle ci du planning
		planning.deleteSeance(jour, Seance.Creneau.MATIN);
		Duration dureeApres = planning.getModule(seance).getDureeDisponible();

		// On verifie que le creneau est vide, et que les heures se sont bien rajouter au module
		assertEquals(dureeApres, dureeAvant.plus(dureeSeance));
		assertNull(planning.getSeance(jour, Seance.Creneau.MATIN));*/

	}

}
