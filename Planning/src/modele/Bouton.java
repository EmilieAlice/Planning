package modele;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import dao.HeuresSessionModuleDao;
import dao.ModuleDao;

/**
 * Objet qui contient la liste des boutons reliés aux matières de la session
 * 
 * @author Jerome
 *
 */
public class Bouton {

	private ArrayList<JRadioButton> boutonDesModules;
	private ButtonGroup groupeDeBoutons;

	public ArrayList<JRadioButton> getBoutonDesModules() {
		return boutonDesModules;
	}

	public void setBoutonDesModules(ArrayList<JRadioButton> boutonDesModules) {
		this.boutonDesModules = boutonDesModules;
	}

	public ButtonGroup getGroupeDeBoutons() {
		return groupeDeBoutons;
	}

	public void setGroupeDeBoutons(ButtonGroup groupeDeBoutons) {
		this.groupeDeBoutons = groupeDeBoutons;
	}

	/**
	 * Méthode qui remplit une liste de Bouton avec les matières et leur nombre
	 * d'heures si ils ont encore des heures de disponibles
	 * 
	 * @param idSession
	 */
	public void remplir(int idSession) {
		boutonDesModules = new ArrayList<JRadioButton>();
		groupeDeBoutons = new ButtonGroup();

		ArrayList<Module> listeModule = new ArrayList<Module>();
		ModuleDao moduleDao = new ModuleDao();
		Session session = new Session();
		session.setIdSession(idSession);
		listeModule = moduleDao.findModuleAvecHeures(idSession);

		HashMap<Module, Integer> liste = new HashMap<>();
		HeuresSessionModuleDao heureSessionModuleDao = new HeuresSessionModuleDao();
		for (Module module : listeModule) {
			liste.put(
					module,
					heureSessionModuleDao.findHeuresSessionModule(
							session.getIdSession(), module.getIdModule())
							.getNbreHeuresDisponibles());

		}

		for (Module clefs : liste.keySet()) {
			JRadioButton bouton = new JRadioButton(clefs.getNomModule() + " : "
					+ liste.get(clefs) + "/" + clefs.getNbHeuresAnnuelles()
					+ " heures disponibles");
			boutonDesModules.add(bouton);
			groupeDeBoutons.add(bouton);
		}

		JRadioButton boutonSupprimer = new JRadioButton("Supprimer");
		boutonDesModules.add(boutonSupprimer);
		groupeDeBoutons.add(boutonSupprimer);


	}
}
