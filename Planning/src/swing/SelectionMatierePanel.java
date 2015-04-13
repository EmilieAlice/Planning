package swing;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import modele.Bouton;

public class SelectionMatierePanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;

	//private final ArrayList<JRadioButton> boutonDesMatières;
	//private final ButtonGroup groupeDeBoutons;
	private final Bouton bouton;

	public SelectionMatierePanel() {

		/*
		 * boutonDesMatières = new ArrayList<JRadioButton>(); groupeDeBoutons =
		 * new ButtonGroup();
		 * 
		 * ArrayList<Module> listeModule = new ArrayList<Module>(); ModuleDao
		 * moduleDao = new ModuleDao(); Session session = new Session();
		 * session.setNomSession("BTS SIO 2016"); session.setIdSession(1);
		 * listeModule = moduleDao.findModuleAvecHeures(2015, session);
		 * 
		 * HashMap<Module, Integer> liste = new HashMap<>();
		 * HeuresSessionModuleDao dao = new HeuresSessionModuleDao(); for
		 * (Module module : listeModule) { liste.put(module,
		 * dao.findHeuresSessionModule(module, session)
		 * .getNbreHeuresDisponibles());
		 * 
		 * }
		 * 
		 * for (Module clefs : liste.keySet()) { JRadioButton bouton = new
		 * JRadioButton(clefs.getNomModule() + " : " + liste.get(clefs) +
		 * " heures disponibles"); boutonDesMatières.add(bouton);
		 * groupeDeBoutons.add(bouton); }
		 * 
		 * JRadioButton boutonSupprimer = new JRadioButton("Supprimer");
		 * boutonDesMatières.add(boutonSupprimer);
		 * groupeDeBoutons.add(boutonSupprimer);
		 * 
		 * for (int i = 0; i < boutonDesMatières.size(); i++) {
		 * add(boutonDesMatières.get(i)); }
		 */

		bouton = new Bouton();
		bouton.addObserver(this);
		update(bouton, null);
		bouton.remplir();
		for (int i = 0; i < bouton.getBoutonDesMatières().size(); i++) {
			add(bouton.getBoutonDesMatières().get(i));
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.updateUI();


	}

}
