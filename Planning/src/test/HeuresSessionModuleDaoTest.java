package test;

import static org.junit.Assert.*;
import modele.HeuresSessionModule;
import modele.Module;
import modele.Seance;
import modele.Session;
import modele.Seance.Creneau;

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
		test = dao.findHeuresSessionModule(session.getIdSession(), module.getIdModule());

		assertNotNull(test);
	}

	@Test
	public void testUpdateModuleAvecHeures(){
		Seance.Creneau matin = Creneau.MATIN;
		HeuresSessionModule heureSessionModule = new HeuresSessionModule(1, 1, 30);
		HeuresSessionModuleDao dao = new HeuresSessionModuleDao();
		Boolean test = dao.updateModuleAvecHeures(heureSessionModule, matin, false);

		assertTrue(test);
	}

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
