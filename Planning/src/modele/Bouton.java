package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import dao.HeuresSessionModuleDao;
import dao.ModuleDao;

public class Bouton extends Observable {

	private ArrayList<JRadioButton> boutonDesMatières;
	private ButtonGroup groupeDeBoutons;

	public ArrayList<JRadioButton> getBoutonDesMatières() {
		return boutonDesMatières;
	}

	public void setBoutonDesMatières(ArrayList<JRadioButton> boutonDesMatières) {
		this.boutonDesMatières = boutonDesMatières;
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
	 */
	public void remplir(int idSession) {
		boutonDesMatières = new ArrayList<JRadioButton>();
		groupeDeBoutons = new ButtonGroup();

		ArrayList<Module> listeModule = new ArrayList<Module>();
		ModuleDao moduleDao = new ModuleDao();
		Session session = new Session();
		session.setIdSession(idSession);
		listeModule = moduleDao.findModuleAvecHeures(idSession);

		HashMap<Module, Integer> liste = new HashMap<>();
		HeuresSessionModuleDao heureSessionModuleDao = new HeuresSessionModuleDao();
		for (Module module : listeModule) {
			liste.put(module, heureSessionModuleDao.findHeuresSessionModule(session.getIdSession(), module.getIdModule())
					.getNbreHeuresDisponibles());

		}

		for (Module clefs : liste.keySet()) {
			JRadioButton bouton = new JRadioButton(clefs.getNomModule() + " : "
					+ liste.get(clefs) + "/" + clefs.getNbHeuresAnnuelles() + " heures disponibles");
			boutonDesMatières.add(bouton);
			groupeDeBoutons.add(bouton);
		}

		JRadioButton boutonSupprimer = new JRadioButton("Supprimer");
		boutonDesMatières.add(boutonSupprimer);
		groupeDeBoutons.add(boutonSupprimer);
		
		setChanged();
		notifyObservers();

	}
}
