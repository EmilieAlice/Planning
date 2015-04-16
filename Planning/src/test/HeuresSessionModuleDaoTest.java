package test;

import static org.junit.Assert.*;
import modele.HeuresSessionModule;
import modele.Module;
import modele.Seance;
import modele.Session;
import modele.Seance.Creneau;

import org.junit.Test;

import dao.HeuresSessionModuleDao;

/**
 * Classe de test permettant de tester toutes les methodes
 * de la classe HeuresSessionModuleDao
 *
 */
public class HeuresSessionModuleDaoTest {

	/**
	 * test JUnit de la methode findHeuresSessionModule :
	 * permet de verifier si la methode retourne bien le nombre d'heure restant
	 * d'un module d'une session
	 */
	@Test
	public void testFindHeuresSessionModule() {

		Session session = new Session();
		session.setIdSession(3);
		Module module = new Module();
		module.setIdModule(1);

		HeuresSessionModule test = new HeuresSessionModule();
		HeuresSessionModuleDao dao = new HeuresSessionModuleDao();
		test = dao.findHeuresSessionModule(session.getIdSession(), module.getIdModule());

		assertNotNull(test);
	}

	/**
	 * test JUnit de la methode updateModuleAvecHeures :
	 * permet de verifier si la methode actualise bien 
	 * le nombre d'heure d'un module
	 */
	@Test
	public void testUpdateModuleAvecHeures(){
		Seance.Creneau matin = Creneau.MATIN;
		HeuresSessionModule heureSessionModule = new HeuresSessionModule(1, 1, 30);
		HeuresSessionModuleDao dao = new HeuresSessionModuleDao();
		Boolean test = dao.updateModuleAvecHeures(heureSessionModule, matin, false);

		assertTrue(test);
	}

	/**
	 * test JUnit de la methode findHeuresSessionModule :
	 * permet de verifier si la methode update a bien fontionné
	 */
	@Test
	public void testNombresHeuresMisesAJourApresUpdateHeuresSessionModule() {
		Seance.Creneau matin = Creneau.MATIN;
		HeuresSessionModule heureSessionModule = new HeuresSessionModule(1, 1,30);
		HeuresSessionModuleDao heureSessionModuleDao = new HeuresSessionModuleDao();
		heureSessionModuleDao.updateModuleAvecHeures(heureSessionModule, matin,true);

		Session session = new Session();
		session.setIdSession(1);
		Module module = new Module();
		module.setIdModule(1);

		HeuresSessionModule test = new HeuresSessionModule();
		test = heureSessionModuleDao.findHeuresSessionModule(session.getIdSession(), module.getIdModule());

		assertEquals(40, test.getNbreHeuresDisponibles());
	}

}
