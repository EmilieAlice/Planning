package swing;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import modele.Bouton;

public class SelectionMatierePanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private final Bouton bouton;

	/*
	 * Constructeur qui remplit le panneau des boutons grace à la méthode
	 * remplir de la classe bouton
	 */
	public SelectionMatierePanel(int idSession) {

		bouton = new Bouton();
		bouton.addObserver(this);
		bouton.remplir(idSession);
		for (int i = 0; i < bouton.getBoutonDesModules().size(); i++) {
			add(bouton.getBoutonDesModules().get(i));
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
