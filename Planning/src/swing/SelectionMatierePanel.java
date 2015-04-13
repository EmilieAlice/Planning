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
		bouton.remplir(idSession);
		for (int i = 0; i < bouton.getBoutonDesMatières().size(); i++) {
			add(bouton.getBoutonDesMatières().get(i));
		}
		update(bouton, idSession);
	}

	public void update(Observable o, int idSession) {
		if (bouton == o) {
			bouton.remplir(idSession);
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {		
	}

}
