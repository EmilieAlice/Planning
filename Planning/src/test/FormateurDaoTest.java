package test;

import static org.junit.Assert.*;
import modele.Formateur;
import modele.Personne;

import org.junit.Test;

import dao.FormateurDao;

public class FormateurDaoTest {

	@Test
	public void testFindByNomModule() {

		Personne personne = new Personne();
		personne.setIdPersonne(4);

		Formateur formateur = new Formateur();
		FormateurDao formateurDao = new FormateurDao();
		formateur = formateurDao.findByNomModule(personne);

		assertEquals(1, formateur.getIdModule());
	}

}
