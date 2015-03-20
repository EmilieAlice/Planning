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
		personne = dao.findByNomModule("Maths");

		int idPersonne = personne.getIdPersonne();

		assertEquals(5, idPersonne);

	}

}
