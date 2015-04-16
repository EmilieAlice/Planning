package test;

import static org.junit.Assert.*;
import modele.Formateur;
import modele.Module;
import modele.Personne;

import org.junit.Test;

import dao.FormateurDao;
import dao.ModuleDao;
/**
 * Classe de test permettant de tester toutes les methodes
 * de la classe FormateurDao
 *
 */
public class FormateurDaoTest {

	/**
	 * test JUnit de la methode findFormateurByIdPersonne :
	 * permet de verifier si la methode retourne bien le formateur
	 * grace à l'id de la personne donnée
	 */
	@Test
	public void testFindFormateurByIdPersonne(){
		
		Personne personneTest = new Personne();
		personneTest.setIdFormateur(4);

		FormateurDao formateurDao = new FormateurDao();
		Formateur formateur = new Formateur();
		formateur = formateurDao.findFormateurByIdPersonne(4);
		
		assertEquals(personneTest.getIdFormateur(), formateur.getIdFormateur());
		
	}

	/**
	 * test JUnit de la methode findByIdModule :
	 * permet de verifier si la methode retourne bien le formateur
	 * grace à l'id du module enseigné
	 */
	@Test
	public void testFindByIdModule() {

		Personne personneTest = new Personne();
		personneTest.setIdFormateur(4);
		
		Module module = new Module();
		ModuleDao moduleDao = new ModuleDao();
		
		module = moduleDao.findModuleById(1);
		
		FormateurDao formateurDao = new FormateurDao();
		Formateur formateur = new Formateur();
		formateur = formateurDao.findFormateurByIdModule(module.getIdModule());

		assertEquals(personneTest.getIdFormateur(), formateur.getIdFormateur());
	}
}
