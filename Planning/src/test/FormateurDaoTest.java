package test;

import static org.junit.Assert.*;
import modele.Formateur;
import modele.Module;

import org.junit.Test;

import dao.FormateurDao;

public class FormateurDaoTest {

	@Test
	public void testFindFormateurByIdPersonne(){
		fail("Not yet implemented");
	}

	@Test
	public void testFindByIdModule() {

		Module moduleTest = new Module();
		moduleTest.setIdModule(2);
		FormateurDao formateurDao = new FormateurDao();
		Formateur formateur = new Formateur();
		formateur = formateurDao.findFormateurByIdModule(moduleTest);

		assertEquals(0, formateur.getIdFormateur());
	}
}
