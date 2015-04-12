package swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class FenetreTest extends JFrame {
	

	private static final long serialVersionUID = 1L;
	SelectionMatierePanel selectionMatierePanel;
	TableauPanel tableauPanel;
	
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

	/**
	 * A COMPLETER
	 */
	private void initComposants() {
		selectionMatierePanel = new SelectionMatierePanel();
		tableauPanel = new TableauPanel();
		add(selectionMatierePanel, BorderLayout.NORTH);
		add(tableauPanel, BorderLayout.SOUTH);
		
	}
	
	
	public static void main(String[] args) {
		new FenetreTest();
	}
	
}
