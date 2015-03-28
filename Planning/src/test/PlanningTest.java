package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.Duration;
import java.util.GregorianCalendar;

import modele.Planning;
import modele.Seance;

import org.junit.Test;

import dao.PlanningDao;

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

		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);
		GregorianCalendar premierJour = new GregorianCalendar(2015, 05, 07);
		GregorianCalendar dernierJour = new GregorianCalendar(2016, 05, 12);

		Seance.Creneau creneauMatin = Seance.Creneau.MATIN;
		Seance.Creneau creneauApresMidi = Seance.Creneau.APRES_MIDI;

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
		
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);

		Duration dureeSeance = Duration.parse("PT3H30M");
		Duration dureeAvant = planning.getModule(seance).getDureeDisponible();
		planning.setSeance(seance);
		Duration dureeApres = planning.getModule(seance).getDureeDisponible();

		assertEquals(seance, planning.getSeance(jour, Seance.Creneau.MATIN));
		assertEquals(dureeApres, dureeAvant.minus(dureeSeance));

	}

	@Test(expected = AssertionError.class)
	public void testSetSeanceInterdit() throws SQLException {
		Planning planning = new Planning();
		PlanningDao planningDao = new PlanningDao();
		planning = planningDao.findByIdSession(1);
		
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);

		planning.setSeance(seance);

	}

	@Test
	public void testDeleteSeance() throws SQLException {
		Planning planning = new Planning();
		PlanningDao planningDao = new PlanningDao();
		planning = planningDao.findByIdSession(1);
		
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);

		Duration dureeSeance = Duration.parse("PT3H30M");
		Duration dureeAvant = planning.getModule(seance).getDureeDisponible();
		planning.deleteSeance(jour, Seance.Creneau.MATIN);
		Duration dureeApres = planning.getModule(seance).getDureeDisponible();

		assertEquals(dureeApres, dureeAvant.plus(dureeSeance));
		assertNull(planning.getSeance(jour, Seance.Creneau.MATIN));

	}
	
	@Test
	private void getNbreJours() {

	}

}
