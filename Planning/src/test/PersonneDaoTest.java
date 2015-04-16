package test;

import static org.junit.Assert.*;
import modele.Personne;

import org.junit.Test;

import dao.PersonneDao;

/**
 * Classe de test permettant de tester toutes les methodes
 * de la classe PersonneDao
 *
 */
public class PersonneDaoTest {

	/**
	 * test JUnit de la methode findByNomModule :
	 * permet de verifier si la methode retourne bien la personne
	 * grace au nom du module enseigne
	 */
	@Test
	public void testFindByNomModule() {

		Personne personne = new Personne();
		PersonneDao dao = new PersonneDao();
		personne = dao.findPersonneByNomModule("Maths");

		int idPersonne = personne.getIdFormateur();

		assertEquals(5, idPersonne);

	}

}
