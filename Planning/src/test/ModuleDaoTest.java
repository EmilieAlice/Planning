package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modele.Module;
import modele.Session;

import org.junit.Test;

import dao.ModuleDao;
/**
 * Classe de test permettant de tester toutes les methodes
 * de la classe ModuleDao
 *
 */
public class ModuleDaoTest {

	/**
	 * test JUnit de la methode findModuleByNom :
	 * permet de verifier si la methode retourne bien le module
	 * grace au nom
	 */
	@Test
	public void testFindModuleByNom() {
		Module test = new Module();
		ModuleDao dao = new ModuleDao();

		test = dao.findModuleByNom("SI2");

		assertEquals(1, test.getIdModule());

	}

	/**
	 * test JUnit de la methode findModuleAvecHeures :
	 * permet de verifier si la methode retourne bien le module
	 * avec ses heures
	 */
	@Test
	public void testFindModuleAvecHeures() {
		ArrayList<Module> listeModuleUn = new ArrayList<Module>();
		Module moduleUn = new Module(
				1,
				"SI2",
				"Enseigner aux élèves les bases sur le fonctionnement du réseau internet",
				"Des TP et des cours", 30,
				"Les prérequis sont le module SI1 et le binaire");
		Module moduleDeux = new Module(
				2,
				"Maths",
				"Enseigner aux élèves les notions de mathématiques nécessaires en informatique",
				"Des cours théoriques et du python", 50,
				"Les prérequis sont le BAC");
		listeModuleUn.add(moduleUn);
		listeModuleUn.add(moduleDeux);

		ArrayList<Module> listeModuleDeux = new ArrayList<Module>();
		Session session = new Session();
		session.setIdSession(1);
		ModuleDao moduleDao = new ModuleDao();
		listeModuleDeux = moduleDao.findModuleAvecHeures(session.getIdSession());
		System.out.println(listeModuleDeux);

		Module expected = listeModuleUn.get(1);
		Module test = listeModuleDeux.get(1);

		assertEquals(expected.getIdModule(), test.getIdModule());

	}

	/**
	 * test JUnit de la methode findFormateurByNomModule :
	 * permet de verifier si la methode retourne bien le formateur
	 * qui enseigne ce module grace au nom du module
	 */
	@Test
	public void testFindFormateurByNomModule(){

	}

	/**
	 * test JUnit de la methode findModuleById :
	 * permet de verifier si la methode retourne bien le module
	 * grace à son id
	 */
	@Test
	public void testFindModuleById(){
		Module moduleAttendu = new Module(
				1,
				"SI2",
				"Enseigner aux élèves les bases sur le fonctionnement du réseau internet",
				"Des TP et des cours", 30,
				"Les prérequis sont le module SI1 et le binaire");

		Module test = new Module();
		ModuleDao moduleDao = new ModuleDao();
		test = moduleDao.findModuleById(1);
		System.out.println(moduleAttendu);
		System.out.println(test);

		assertEquals(moduleAttendu.getIdModule(), test.getIdModule());
	}

}
