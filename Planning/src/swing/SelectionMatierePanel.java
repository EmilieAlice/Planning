package swing;


import javax.swing.JPanel;

import modele.Bouton;

public class SelectionMatierePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Bouton bouton;

	/*
	 * Constructeur qui remplit le panneau des boutons grace à la méthode
	 * remplir de la classe bouton
	 */
	public SelectionMatierePanel(int idSession) {

		bouton = new Bouton();
		bouton.remplir(idSession);
		for (int i = 0; i < bouton.getBoutonDesModules().size(); i++) {
			add(bouton.getBoutonDesModules().get(i));
		}
	}

}
