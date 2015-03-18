package fenetre;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;


import dao.ModuleDao;

import javax.swing.JPanel;

import javax.swing.JComboBox;
import javax.swing.JButton;

import modele.Module;

import java.awt.SystemColor;
import java.util.ArrayList;

public class Planning {

	private JFrame frame = new JFrame();
	private JPanel calendrier = new JPanel();
	private JLabel lblSemaine = new JLabel("Semaine");
	private JLabel numeroSemaine = new JLabel("");
	private JLabel lblMatiere = new JLabel("Matiere");
	private JComboBox nomMatiere = new JComboBox();
	private JPanel planning = new JPanel();
	private JPanel plageLundi = new JPanel();
	private JPanel nomLundi = new JPanel();
	private JLabel lblLundi = new JLabel("Lundi");
	private JLabel lblDateLundi = new JLabel("");
	private JPanel plageLundiMatin = new JPanel();
	private JPanel plageLundiApresMidi = new JPanel();
	private JPanel plageMardi = new JPanel();
	private JPanel nomMardi = new JPanel();
	private JLabel lblMardi = new JLabel("Mardi");
	private JLabel lblDateMardi = new JLabel("");
	private JPanel plageMardiMatin = new JPanel();
	private JPanel plageMardiApresMidi = new JPanel();
	private JPanel plageMercredi = new JPanel();
	private JPanel nomMercredi = new JPanel();
	private JLabel lblMercredi = new JLabel("Mercredi");
	private JLabel lblDateMercredi = new JLabel("");
	private JPanel plageMercrediMatin = new JPanel();
	private JPanel plageMercrediApresMidi = new JPanel();
	private JPanel plageJeudi = new JPanel();
	private JPanel nomJeudi = new JPanel();
	private JLabel lblJeudi = new JLabel("Jeudi");
	private JLabel lblDateJeudi = new JLabel("");
	private JPanel plageJeudiMatin = new JPanel();
	private JPanel plageJeudiApresMidi = new JPanel();
	private JPanel plageVendredi = new JPanel();
	private JPanel nomVendredi = new JPanel();
	private JLabel lblVendredi = new JLabel("Vendredi");
	private JLabel lblDateVendredi = new JLabel("");
	private JPanel plageVendrediMatin = new JPanel();
	private JPanel plageVendrediApresMidi = new JPanel();
	private JPanel plageHoraire = new JPanel();
	private JButton btnValider = new JButton("Valider");
	

	/**
	 * Permet de lancer le planning
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Planning window = new Planning();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructeur comprenant la methode d'initialisation
	 */
	public Planning() {
		initialize();
	}

	/**
	 * Methode qui initialise la fenetre
	 */
	private void initialize() {
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//frame.setResizable(true);
		
		calendrier.setBounds(10, 10, 414, 210);
		frame.getContentPane().add(calendrier);
		calendrier.setLayout(null);
		
		lblSemaine.setBounds(0, 0, 60, 20);
		calendrier.add(lblSemaine);
		
		numeroSemaine.setBounds(60, 0, 40, 20);
		calendrier.add(numeroSemaine);

		lblMatiere.setBounds(128, 0, 45, 20);
		calendrier.add(lblMatiere);
		
		nomMatiere.setBounds(174, 1, 175, 20);
		calendrier.add(nomMatiere);
		
		planning.setBackground(SystemColor.activeCaption);
		planning.setBounds(0, 20, 415, 190);
		calendrier.add(planning);
		planning.setLayout(null);
		
		plageLundi.setBounds(15, 0, 80, 190);
		planning.add(plageLundi);
		plageLundi.setLayout(null);
		
		nomLundi.setBounds(0, 0, 83, 20);
		plageLundi.add(nomLundi);
		nomLundi.setLayout(null);
		
		lblLundi.setBounds(0, 0, 63, 20);
		nomLundi.add(lblLundi);
		
		lblDateLundi.setBounds(63, 0, 20, 20);
		nomLundi.add(lblDateLundi);
		
		plageLundiMatin.setBounds(0, 20, 80, 85);
		plageLundi.add(plageLundiMatin);
		
		plageLundiApresMidi.setBounds(0, 105, 80, 85);
		plageLundi.add(plageLundiApresMidi);
		
		plageMardi.setBounds(95, 0, 80, 190);
		planning.add(plageMardi);
		plageMardi.setLayout(null);
		
		nomMardi.setBounds(0, 0, 83, 20);
		plageMardi.add(nomMardi);
		nomMardi.setLayout(null);
		
		lblMardi.setBounds(0, 0, 63, 20);
		nomMardi.add(lblMardi);
		
		lblDateMardi.setBounds(63, 0, 20, 20);
		nomMardi.add(lblDateMardi);
		
		plageMardiMatin.setBounds(0, 105, 80, 85);
		plageMardi.add(plageMardiMatin);
		
		plageMardiApresMidi.setBounds(0, 20, 80, 85);
		plageMardi.add(plageMardiApresMidi);
		
		plageMercredi.setBounds(175, 0, 80, 190);
		planning.add(plageMercredi);
		plageMercredi.setLayout(null);
		
		nomMercredi.setBounds(0, 0, 83, 20);
		plageMercredi.add(nomMercredi);
		nomMercredi.setLayout(null);
		
		lblMercredi.setBounds(0, 0, 63, 20);
		nomMercredi.add(lblMercredi);
		
		lblDateMercredi.setBounds(63, 0, 20, 20);
		nomMercredi.add(lblDateMercredi);
		
		plageMercrediMatin.setBounds(0, 105, 80, 85);
		plageMercredi.add(plageMercrediMatin);
		
		plageMercrediApresMidi.setBounds(0, 20, 80, 85);
		plageMercredi.add(plageMercrediApresMidi);
		
		plageJeudi.setBounds(255, 0, 80, 190);
		planning.add(plageJeudi);
		plageJeudi.setLayout(null);
		
		nomJeudi.setBounds(0, 0, 83, 20);
		plageJeudi.add(nomJeudi);
		nomJeudi.setLayout(null);
		
		lblJeudi.setBounds(0, 0, 63, 20);
		nomJeudi.add(lblJeudi);
		
		lblDateJeudi.setBounds(63, 0, 20, 20);
		nomJeudi.add(lblDateJeudi);
		
		plageJeudiMatin.setBounds(0, 105, 80, 85);
		plageJeudi.add(plageJeudiMatin);
		
		plageJeudiApresMidi.setBounds(0, 20, 80, 85);
		plageJeudi.add(plageJeudiApresMidi);
		
		plageVendredi.setBounds(335, 0, 80, 190);
		planning.add(plageVendredi);
		plageVendredi.setLayout(null);
		
		nomVendredi.setBounds(0, 0, 83, 20);
		plageVendredi.add(nomVendredi);
		nomVendredi.setLayout(null);
		
		lblVendredi.setBounds(0, 0, 63, 20);
		nomVendredi.add(lblVendredi);
		
		lblDateVendredi.setBounds(63, 0, 20, 20);
		nomVendredi.add(lblDateVendredi);
		
		plageVendrediMatin.setBounds(0, 105, 80, 85);
		plageVendredi.add(plageVendrediMatin);
		
		plageVendrediApresMidi.setBounds(0, 20, 80, 85);
		plageVendredi.add(plageVendrediApresMidi);
		
		plageHoraire.setBounds(0, 0, 15, 190);
		planning.add(plageHoraire);
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnValider.setBounds(341, 227, 83, 23);
		frame.getContentPane().add(btnValider);

		/**
		 * On ajoute la liste des matières avec un nombre d'heures supérieures à 0
		 */
		/*ArrayList<Module> listeModuleDispo = new ArrayList<Module>();
		ModuleDao remplir = new ModuleDao();
		listeModuleDispo = remplir.findMatiereAvecHeures(2015, "BTS SIO 2016");
		
		for(Module uneMatiere  : listeModuleDispo){
			nomMatiere.addItem(uneMatiere.getNom());
		}*/
	}
}