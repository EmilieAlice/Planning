package modele;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FenetreTest extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SelectionMatierePanel panel;
	
	public FenetreTest(){
		// Composants
	    initComposants();
	    // Centrer sur l'écran
	    setLocationRelativeTo(null);
	    // Permettre le redimensionnement
	    setResizable(true);
	    // Fermer lors du clic sur la croix
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // Ajuster la taille au contenu
	    pack();
	    setTitle("Liste des matières");
	    // La rendre visible
	    setVisible(true);
	}

	private void initComposants() {
		panel = new SelectionMatierePanel();
		add(panel, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		new FenetreTest();
	}
	
}
