package test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import modele.Planning;
import modele.Seance;

import org.junit.Test;

import dao.PlanningDao;
import dao.SeanceDao;

public class PlanningDaoTest {

	@Test
	public void testFindByIdSession() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertSeance() {
		GregorianCalendar jour = new GregorianCalendar(2017, 02, 31);
		Seance.Creneau creneau = Seance.Creneau.APRES_MIDI;
		Seance seance = new Seance(1, 1, 4, jour, creneau, 1, "Cour du jour");
		System.out.println(seance);
		
		PlanningDao planningDao = new PlanningDao();
		
		assertTrue(planningDao.insertSeance(seance));
	}

	@Test
	public void testDeleteSeance() {
		GregorianCalendar jour = new GregorianCalendar(2017, 02, 31);
		Seance.Creneau creneau = Seance.Creneau.APRES_MIDI;
		Seance seance = new Seance(1, 1, 4, jour, creneau, 1, "Cour du jour");
		
		PlanningDao planningDao = new PlanningDao();
		
		assertTrue(planningDao.deleteSeance(seance));
		
		
	}

}
