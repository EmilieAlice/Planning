package modele;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import dao.HeuresSessionModuleDao;
import dao.ModuleDao;

public class Bouton {

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

	public Bouton() {
		boutonDesMatières = new ArrayList<JRadioButton>();
		groupeDeBoutons = new ButtonGroup();

		ArrayList<Module> listeModule = new ArrayList<Module>();
		ModuleDao moduleDao = new ModuleDao();
		Session session = new Session();
		session.setNomSession("BTS SIO 2016");
		session.setIdSession(1);
		listeModule = moduleDao.findModuleAvecHeures(2015, session);

		HashMap<Module, Integer> liste = new HashMap<>();
		HeuresSessionModuleDao dao = new HeuresSessionModuleDao();
		for (Module module : listeModule) {
			liste.put(module, dao.findHeuresSessionModule(module, session)
					.getNbreHeuresDisponibles());

		}

		for (Module clefs : liste.keySet()) {
			JRadioButton bouton = new JRadioButton(clefs.getNomModule() + " : "
					+ liste.get(clefs) + " heures disponibles");
			boutonDesMatières.add(bouton);
			groupeDeBoutons.add(bouton);
		}

		JRadioButton boutonSupprimer = new JRadioButton("Supprimer");
		boutonDesMatières.add(boutonSupprimer);
		groupeDeBoutons.add(boutonSupprimer);
	}
}
