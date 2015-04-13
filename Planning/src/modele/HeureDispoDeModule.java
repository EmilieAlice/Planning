package modele;

import java.util.ArrayList;
import java.util.HashMap;

import dao.HeuresSessionModuleDao;
import dao.ModuleDao;
import dao.SessionDao;

public class HeureDispoDeModule {

	private HashMap<Module, Integer> heureDispoDeModule = new HashMap<Module, Integer>();
	private HeuresSessionModuleDao heureDispoDao = new HeuresSessionModuleDao();

	private ArrayList<Module> listeModuleDispo = new ArrayList<Module>();
	private ModuleDao moduleDao = new ModuleDao();

	private Session session = new Session();
	private SessionDao sessionDao = new SessionDao();

	public HashMap<Module, Integer> getHeureDispoDeModule() {
		return heureDispoDeModule;
	}
	public void setHeureDispoDeModule(HashMap<Module, Integer> heureDispoDeModule) {
		this.heureDispoDeModule = heureDispoDeModule;
	}


	/**
	 * Permet d'ajouter dans la HashMap les modules comprenant encore des heures pour une session donnée
	 * 
	 * @param nomSession
	 * @param anneeDeSession
	 */
	public void ajoute(String nomSession, int anneeDeSession) {
		session = sessionDao.findSessionByNom(nomSession);
		listeModuleDispo = moduleDao.findModuleAvecHeures(anneeDeSession, session);

		for(Module unModule  : listeModuleDispo){
			int heureDispo = heureDispoDao.findHeuresSessionModule(session, unModule.getIdModule()).getNbreHeuresDisponibles();
			if (heureDispo > 3)
				heureDispoDeModule.put(unModule, heureDispo);
		}
	}
	/**
	 * Permet d'actualiser les heures dispo d'un module à une session donnée
	 * 
	 * @param nomSession
	 * @param anneeDeSession
	 * @param unModule
	 */
	public void actualiser(String nomSession, int anneeDeSession, Module unModule){
		session = sessionDao.findSessionByNom(nomSession);
		listeModuleDispo = moduleDao.findModuleAvecHeures(anneeDeSession, session);

		for (Module leModule : listeModuleDispo) {
			int heureDispo = heureDispoDao.findHeuresSessionModule(session, leModule.getIdModule()).getNbreHeuresDisponibles();
			if (leModule.equals(unModule) && heureDispo > 3)
				heureDispoDeModule.put(leModule, heureDispo);
		}
	}	

}
