package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modele.Module;

import org.junit.Test;

import dao.ModuleDao;

public class ModuleDaoTest {

	@Test
	public void testFindModule() {
		Module test = new Module();
		ModuleDao dao = new ModuleDao();

		test = dao.findModule("SI2");

		assertEquals(1, test.getId_module());

	}

	@Test
	public void findModuleAvecHeures() {
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
		ModuleDao dao = new ModuleDao();
		listeModuleDeux = dao.findModuleAvecHeures(2015, "BTS SIO 2016");
		System.out.println(listeModuleDeux);

		Module expected = listeModuleUn.get(1);
		Module test = listeModuleDeux.get(1);

		assertEquals(expected.getId_module(), test.getId_module());

	}

}