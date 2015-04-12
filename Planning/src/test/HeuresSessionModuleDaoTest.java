package test;

import static org.junit.Assert.*;
import modele.HeuresSessionModule;
import modele.Module;
import modele.Session;

import org.junit.Test;

import dao.HeuresSessionModuleDao;

public class HeuresSessionModuleDaoTest {

	@Test
	public void testFindHeuresSessionModule() {

		Session session = new Session();
		session.setIdSession(3);
		Module module = new Module();
		module.setIdModule(1);

		HeuresSessionModule test = new HeuresSessionModule();
		HeuresSessionModuleDao dao = new HeuresSessionModuleDao();
		test = dao.findHeuresSessionModule(module, session);

		assertNotNull(test);
	}

	@Test
	public void testUpdateModuleAvecHeures(){
		HeuresSessionModule heureSessionModule = new HeuresSessionModule(1, 1,30);
		HeuresSessionModuleDao dao = new HeuresSessionModuleDao();
		Boolean test = dao.updateModuleAvecHeures(heureSessionModule, 10, false);

		assertTrue(test);
	}

	@Test
	public void testNombresHeuresMisesAJourApresUpdateHeuresSessionModule() {
		HeuresSessionModule heureSessionModule = new HeuresSessionModule(1, 1,30);
		HeuresSessionModuleDao heureSessionModuleDao = new HeuresSessionModuleDao();
		heureSessionModuleDao.updateModuleAvecHeures(heureSessionModule, 10,true);

		Session session = new Session();
		session.setIdSession(1);
		Module module = new Module();
		module.setIdModule(1);

		HeuresSessionModule test = new HeuresSessionModule();
		test = heureSessionModuleDao.findHeuresSessionModule(module, session);

		assertEquals(40, test.getNbreHeuresDisponibles());
	}

}
