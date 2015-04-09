package test;

import static org.junit.Assert.*;
import modele.Personne;

import org.junit.Test;

import dao.PersonneDao;

public class PersonneDaoTest {

	@Test
	public void testFindByNomModule() {

		Personne personne = new Personne();
		PersonneDao dao = new PersonneDao();
		personne = dao.findPersonneByNomModule("Maths");

		int idPersonne = personne.getIdFormateur();

		assertEquals(5, idPersonne);

	}

}
