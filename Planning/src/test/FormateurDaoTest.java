package test;

import static org.junit.Assert.*;
import modele.Formateur;
import modele.Module;

import org.junit.Test;

import dao.FormateurDao;

public class FormateurDaoTest {

	@Test
	public void testFindByIdPersonne() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByIdModule() {
		
		Module moduleTest = new Module();
		moduleTest.setId_module(1);
		FormateurDao formateurDao = new FormateurDao();
		Formateur formateur = new Formateur();
		formateur = formateurDao.findByIdModule(moduleTest);
		
		assertEquals(4, formateur.getIdPersonne());
		
	}

}
