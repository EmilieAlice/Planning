package swing;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modele.Bouton;

public class SelectionMatierePanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private final Bouton bouton;

	public SelectionMatierePanel(int idSession) {

		bouton = new Bouton();
		bouton.addObserver(this);
		update(bouton, null);
		bouton.remplir(idSession);
		for (int i = 0; i < bouton.getBoutonDesMatières().size(); i++) {
			add(bouton.getBoutonDesMatières().get(i));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		this.updateUI();

	}

}
