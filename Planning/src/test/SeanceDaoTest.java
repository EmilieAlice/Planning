package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import modele.Seance;

import org.junit.Test;

import dao.SeanceDao;

/**
 * Classe de test permettant de tester toutes les methodes de la classe
 * SeanceDao
 *
 */
public class SeanceDaoTest {

	/**
	 * test JUnit de la methode findSeanceByIdSession : permet de verifier si la
	 * methode retourne la liste des seances grace à l'id de la session
	 */
	@Test
	public void testFindSeanceByIdSession() {
		ArrayList<Seance> listeSeance = new ArrayList<Seance>();
		SeanceDao seanceDao = new SeanceDao();
		listeSeance = seanceDao.findSeanceByIdSession(1);

		// ArrayList<Seance> listeSeanceAttendue = new ArrayList<Seance>();
		GregorianCalendar jour = new GregorianCalendar(2015, 05, 02);
		Seance.Creneau creneau = Seance.Creneau.MATIN;
		// Seance seance = new Seance(1, 1, 4, jour, creneau, 1,
		// "Première séance de SI2 avec découverte du fonctionnement d'internet");
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

	/**
	 * test JUnit de la methode deleteSeance : permet de verifier si la methode
	 * supprime bien la seance grace à un id_session et une date de debut donnée
	 */
	@Test
	public void testDeleteSeance() throws SQLException {
		GregorianCalendar debut = new GregorianCalendar(2015, 05, 02, 14, 00,
				00);
		SeanceDao seanceDao = new SeanceDao();

		assertTrue(seanceDao.deleteSeance(debut, 0));
	}

	/**
	 * test JUnit de la methode insertSeance : permet de verifier si la methode
	 * insert bien la seance dans la table
	 */
	@Test
	public void testInsertSeance() throws SQLException {
		// On créé une seance
		GregorianCalendar debut = new GregorianCalendar(2015, 05, 02, 14, 00,
				00);
		GregorianCalendar fin = new GregorianCalendar(2015, 05, 02, 17, 00, 00);
		Seance seance = new Seance(1, 1, 4, debut, fin,
				Seance.Creneau.APRES_MIDI, 1, null);
		SeanceDao seanceDao = new SeanceDao();

		assertTrue(seanceDao.insertSeance(seance));
	}

	/**
	 * test JUnit de la methode updateSeance : permet de verifier si la methode
	 * met bien à jour la seance grace à un id_session et une date de debut
	 * donnée
	 */
	@Test
	public void testUpdateSeance() {

	}

}
