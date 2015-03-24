package test;

import static org.junit.Assert.*;
import modele.Formateur;
import modele.Module;
import modele.Personne;

import org.junit.Test;

import dao.FormateurDao;

public class FormateurDaoTest {
	
	@Test
	public void testFindByIdPersonne() {
		
		Personne personne = new Personne();
		personne.setIdPersonne(4);
		
		Formateur formateur = new Formateur();
		FormateurDao formateurDao = new FormateurDao();
		formateur = formateurDao.findByIdPersonne(personne);
		
		assertEquals("Vérification du formateur par son idPersonne", 4, formateur.getIdPersonne());
		
	}

	@Test
	public void testFindByIdModule() {
		
		Module moduleTest = new Module();
		moduleTest.setId_module(1);
		FormateurDao formateurDao = new FormateurDao();
		Formateur formateur = new Formateur();
		formateur = formateurDao.findByIdModule(moduleTest);
		
		assertEquals("Vérification du formateur par son idModule", 4, formateur.getIdPersonne());
		
	}

}
