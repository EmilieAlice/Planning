package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modele.Planning;
import modele.Seance;

import org.junit.Test;

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
		Planning p = Planning.get(1);

		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);

		Seance.Creneau creneauMatin = Seance.Creneau.MATIN;
		Seance.Creneau creneauApresMidi = Seance.Creneau.APRES_MIDI;

		assertEquals(seance, p.getSeance(jour, creneauMatin));
		assertNull(p.getSeance(jour, creneauApresMidi));
	}

	@Test
	public void testSetSeance() throws SQLException {
		Planning p = Planning.get(1);
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);

		Duration dureeSeance = Duration.parse("PT3H30M");
		Duration dureeAvant = p.getModule(seance).getDureeDisponible();
		p.setSeance(seance);
		Duration dureeApres = p.getModule(seance).getDureeDisponible();

		assertEquals(seance, p.getSeance(jour, Seance.Creneau.MATIN));
		assertEquals(dureeApres, dureeAvant.minus(dureeSeance));

	}

	@Test
	public void testSetSeanceInterdit() throws SQLException {
		Planning p = Planning.get(1);
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);
		
		try {
			p.setSeance(seance);
			fail("Exception xxx non levée.");
		} catch (Exception e) {
			assertEquals(Exception exception, e);
		}
	}

	@Test
	public void testDeleteSeance() throws SQLException {
		Planning p = Planning.get(1);
		GregorianCalendar jour = new GregorianCalendar(2015, 06, 02);
		Seance seance = new Seance(1, 1, 4, jour, Seance.Creneau.MATIN, 1, null);

		Duration dureeSeance = Duration.parse("PT3H30M");
		Duration dureeAvant = p.getModule(seance).getDureeDisponible();
		p.deleteSeance(jour, Seance.Creneau.MATIN);
		Duration dureeApres = p.getModule(seance).getDureeDisponible();

		assertEquals(dureeApres, dureeAvant.plus(dureeSeance));
		assertNull(p.getSeance(jour, Seance.Creneau.MATIN));

	}

}
