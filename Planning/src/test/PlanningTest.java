package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.Duration;
import java.util.GregorianCalendar;

import modele.Planning;
import modele.Seance;
import modele.Session;

import org.junit.Test;

import dao.PlanningDao;
import dao.SessionDao;

/**
 * Hérite de AgrioteTestCase qui appelle la procédure stockée RefreshBase pour
 * remettre la base à zéro avant chaque test
 * 
 * @author Jerome
 *
 */
public class PlanningTest {

	@Test
	public void testDuGet() throws SQLException {
		Planning planning = new Planning();
		PlanningDao planningDao = new PlanningDao();
		planning = planningDao.findByIdSession(1);

		// On créé une seance
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);
		// On cree une date de debut et une date de fin de session
		GregorianCalendar premierJour = new GregorianCalendar(2015, 05, 07);
		GregorianCalendar dernierJour = new GregorianCalendar(2016, 05, 12);
		// On créé 2 creneau pour un jour
		Seance.Creneau creneauMatin = Seance.Creneau.MATIN;
		Seance.Creneau creneauApresMidi = Seance.Creneau.APRES_MIDI;

		// On verifit que ce qu'on à créé plus haut et egal ou non à ce qu'on recupere du planning
		assertEquals(seance, planning.getSeance(jour, creneauMatin));
		assertNull(planning.getSeance(jour, creneauApresMidi));
		assertEquals(premierJour, planning.getPremierJour(1));
		assertEquals(dernierJour, planning.getDernierJour(1));
	}

	@Test
	public void testSetSeance() throws SQLException {
		Planning planning = new Planning();
		PlanningDao planningDao = new PlanningDao();
		planning = planningDao.findByIdSession(1);
		
		// On créé une seance
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);

		// On determine la durée d'une seance et on l'ajoute au planning
		Duration dureeSeance = Duration.parse("PT3H30M");
		Duration dureeAvant = planning.getModule(seance).getDureeDisponible();
		// On l'ajoute au planning
		planning.setSeance(seance);
		Duration dureeApres = planning.getModule(seance).getDureeDisponible();

		// On verifie que le module est bien ajouter et que son nombre d'heure dispo a bien diminué
		assertEquals(seance, planning.getSeance(jour, Seance.Creneau.MATIN));
		assertEquals(dureeApres, dureeAvant.minus(dureeSeance));

	}

	/* Suppression? c'est verifié dans SetSeance
	 * @Test(expected = AssertionError.class)
	public void testSetSeanceInterdit() throws SQLException {
		Planning planning = new Planning();
		PlanningDao planningDao = new PlanningDao();
		planning = planningDao.findByIdSession(1);
		
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);

		planning.setSeance(seance);

	}*/

	@Test
	public void testDeleteSeance() throws SQLException {
		Planning planning = new Planning();
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
		assertNull(planning.getSeance(jour, Seance.Creneau.MATIN));

	}
	
	@Test
	public void getNbreJours() {		
		Planning planning = new Planning();
		PlanningDao planningDao = new PlanningDao();
		planning = planningDao.findByIdSession(1);
		
		// On recupere les premier et dernier jour de la session
		long nbJours = planning.nbreJours(planning);
				
		// Pour la comparer avec la soustraction du 1er et du dernier jour
		assertEquals(371, nbJours);		
		
	}

}
