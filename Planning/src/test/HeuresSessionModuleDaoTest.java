package test;

import static org.junit.Assert.*;
import modele.HeuresSessionModule;
import modele.Module;
import modele.Session;

import org.junit.Test;

import dao.HeuresSessionModuleDao;

public class HeuresSessionModuleDaoTest {

	@Test
	public void testFindHeuresSessionModule()  {

		Session session = new Session();
		session.setId_session(1);
		Module module = new Module(
				1,
				"SI2",
				"Enseigner aux élèves les bases sur le fonctionnement du réseau internet",
				"Des TP et des cours", 30,
				"Les prérequis sont le module SI1 et le binaire");

		HeuresSessionModule test = new HeuresSessionModule();
		HeuresSessionModuleDao dao = new HeuresSessionModuleDao();
		test = dao.findHeuresSessionModule(module, session);

		assertEquals(1, test.getId_module());
	}

	@Test
	public void testUpdateHeuresSessionModule() {

		HeuresSessionModule heureSessionModule = new HeuresSessionModule(1, 1,
				30);
		HeuresSessionModuleDao dao = new HeuresSessionModuleDao();

		Boolean test = dao.updateModuleAvecHeures(heureSessionModule, 10, false);

		assertTrue(test);

	}

}
