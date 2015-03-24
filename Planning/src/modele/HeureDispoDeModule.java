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
	
	private String nomSession;
	private int anneeDeSession;
	

	/**
	 * Permet d'ajouter un ensemble de mot dans la HashMap
	 */
	public void ajoute() {
		session = sessionDao.findSession(nomSession);
		listeModuleDispo = moduleDao.findModuleAvecHeures(anneeDeSession, session);
		
		for(Module unModule  : listeModuleDispo){
			heureDispoDeModule.put(unModule, heureDispoDao.findHeuresSessionModule(unModule, session).getNbreHeuresDisponibles());
		}
	}
	/**
	 * Permet de lister tous les elements contenus dans la base de donnees.
	 * @return 
	 */
	public Dictionnaire listeTout() {
		Set<String> lesMots = table.keySet();
		for(String unMot : lesMots){
			String mot = table.get(unMot);
			System.out.println(unMot);
			System.out.println(mot);
		}
		return null;
	}

}
