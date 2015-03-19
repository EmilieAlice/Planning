package fenetre;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;












import dao.HeuresSessionModuleDao;
import dao.ModuleDao;
import dao.SessionDao;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import modele.HeuresSessionModule;
import modele.Module;
import modele.Session;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.border.BevelBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.Component;

import javax.swing.border.EmptyBorder;

public class Planning {

	private JFrame frame = new JFrame();
	private JPanel calendrier = new JPanel();
	private JLabel lblSemaine = new JLabel("Semaine");
	private JLabel numeroSemaine = new JLabel("");
	private JLabel lblMatiere = new JLabel("Matiere");
	private JComboBox nomModule = new JComboBox();
	private JPanel planning = new JPanel();
	private JPanel plageLundi = new JPanel();
	private JPanel nomLundi = new JPanel();
	private JLabel lblLundi = new JLabel("   Lundi");
	private JLabel lblDateLundi = new JLabel("");
	private JPanel plageLundiMatin = new JPanel();
	private JPanel plageLundiApresMidi = new JPanel();
	private JPanel plageMardi = new JPanel();
	private JPanel nomMardi = new JPanel();
	private JLabel lblMardi = new JLabel("   Mardi");
	private JLabel lblDateMardi = new JLabel("");
	private JPanel plageMardiMatin = new JPanel();
	private JPanel plageMardiApresMidi = new JPanel();
	private JPanel plageMercredi = new JPanel();
	private JPanel nomMercredi = new JPanel();
	private JLabel lblMercredi = new JLabel("   Mercredi");
	private JLabel lblDateMercredi = new JLabel("");
	private JPanel plageMercrediMatin = new JPanel();
	private JPanel plageMercrediApresMidi = new JPanel();
	private JPanel plageJeudi = new JPanel();
	private JPanel nomJeudi = new JPanel();
	private JLabel lblJeudi = new JLabel("   Jeudi");
	private JLabel lblDateJeudi = new JLabel("");
	private JPanel plageJeudiMatin = new JPanel();
	private JPanel plageJeudiApresMidi = new JPanel();
	private JPanel plageVendredi = new JPanel();
	private JPanel nomVendredi = new JPanel();
	private JLabel lblVendredi = new JLabel("   Vendredi");
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
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(222, 184, 135));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		calendrier.setBackground(new Color(222, 184, 135));
		//frame.setResizable(true);

		calendrier.setBounds(10, 10, 414, 220);
		frame.getContentPane().add(calendrier);
		calendrier.setLayout(null);

		lblSemaine.setBounds(5, 2, 60, 20);
		calendrier.add(lblSemaine);

		numeroSemaine.setBounds(65, 2, 40, 20);
		calendrier.add(numeroSemaine);

		lblMatiere.setBounds(128, 2, 45, 20);
		calendrier.add(lblMatiere);
		nomModule.setBackground(new Color(210, 180, 140));

		nomModule.setBounds(174, 2, 200, 20);
		nomModule.addItem(" Selectionner le module ");
		calendrier.add(nomModule);
		planning.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));

		planning.setBackground(SystemColor.activeCaption);
		planning.setBounds(0, 30, 415, 190);
		calendrier.add(planning);
		planning.setLayout(null);

		plageLundi.setBounds(15, 0, 80, 190);
		planning.add(plageLundi);
		plageLundi.setLayout(null);
		nomLundi.setBackground(SystemColor.inactiveCaption);

		nomLundi.setBounds(0, 0, 83, 20);
		plageLundi.add(nomLundi);
		nomLundi.setLayout(null);
		lblLundi.setAlignmentX(Component.CENTER_ALIGNMENT);

		lblLundi.setBounds(0, 0, 63, 20);
		nomLundi.add(lblLundi);

		lblDateLundi.setBounds(63, 0, 20, 20);
		nomLundi.add(lblDateLundi);
		plageLundiMatin.setBackground(new Color(230, 230, 250));
		plageLundiMatin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(210, 180, 140)));

		plageLundiMatin.setBounds(0, 20, 80, 85);
		plageLundi.add(plageLundiMatin);
		plageLundiApresMidi.setBackground(new Color(230, 230, 250));
		plageLundiApresMidi.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(210, 180, 140)));

		plageLundiApresMidi.setBounds(0, 105, 80, 85);
		plageLundi.add(plageLundiApresMidi);

		plageMardi.setBounds(95, 0, 80, 190);
		planning.add(plageMardi);
		plageMardi.setLayout(null);
		nomMardi.setBackground(SystemColor.inactiveCaption);

		nomMardi.setBounds(0, 0, 83, 20);
		plageMardi.add(nomMardi);
		nomMardi.setLayout(null);
		lblMardi.setBackground(SystemColor.inactiveCaption);

		lblMardi.setBounds(0, 0, 63, 20);
		nomMardi.add(lblMardi);

		lblDateMardi.setBounds(63, 0, 20, 20);
		nomMardi.add(lblDateMardi);
		plageMardiMatin.setBackground(new Color(230, 230, 250));
		plageMardiMatin.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(210, 180, 140)));

		plageMardiMatin.setBounds(0, 105, 80, 85);
		plageMardi.add(plageMardiMatin);
		plageMardiApresMidi.setBackground(new Color(230, 230, 250));
		plageMardiApresMidi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(210, 180, 140)));

		plageMardiApresMidi.setBounds(0, 20, 80, 85);
		plageMardi.add(plageMardiApresMidi);

		plageMercredi.setBounds(175, 0, 80, 190);
		planning.add(plageMercredi);
		plageMercredi.setLayout(null);
		nomMercredi.setBackground(SystemColor.inactiveCaption);

		nomMercredi.setBounds(0, 0, 83, 20);
		plageMercredi.add(nomMercredi);
		nomMercredi.setLayout(null);

		lblMercredi.setBounds(0, 0, 63, 20);
		nomMercredi.add(lblMercredi);

		lblDateMercredi.setBounds(63, 0, 20, 20);
		nomMercredi.add(lblDateMercredi);
		plageMercrediMatin.setBackground(new Color(230, 230, 250));
		plageMercrediMatin.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(210, 180, 140)));

		plageMercrediMatin.setBounds(0, 105, 80, 85);
		plageMercredi.add(plageMercrediMatin);
		plageMercrediApresMidi.setBackground(new Color(230, 230, 250));
		plageMercrediApresMidi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(210, 180, 140)));

		plageMercrediApresMidi.setBounds(0, 20, 80, 85);
		plageMercredi.add(plageMercrediApresMidi);

		plageJeudi.setBounds(255, 0, 80, 190);
		planning.add(plageJeudi);
		plageJeudi.setLayout(null);
		nomJeudi.setBackground(SystemColor.inactiveCaption);

		nomJeudi.setBounds(0, 0, 83, 20);
		plageJeudi.add(nomJeudi);
		nomJeudi.setLayout(null);

		lblJeudi.setBounds(0, 0, 63, 20);
		nomJeudi.add(lblJeudi);

		lblDateJeudi.setBounds(63, 0, 20, 20);
		nomJeudi.add(lblDateJeudi);
		plageJeudiMatin.setBackground(new Color(230, 230, 250));
		plageJeudiMatin.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(210, 180, 140)));

		plageJeudiMatin.setBounds(0, 105, 80, 85);
		plageJeudi.add(plageJeudiMatin);
		plageJeudiApresMidi.setBackground(new Color(230, 230, 250));
		plageJeudiApresMidi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(210, 180, 140)));

		plageJeudiApresMidi.setBounds(0, 20, 80, 85);
		plageJeudi.add(plageJeudiApresMidi);

		plageVendredi.setBounds(335, 0, 80, 190);
		planning.add(plageVendredi);
		plageVendredi.setLayout(null);
		nomVendredi.setBackground(SystemColor.inactiveCaption);

		nomVendredi.setBounds(0, 0, 83, 20);
		plageVendredi.add(nomVendredi);
		nomVendredi.setLayout(null);

		lblVendredi.setBounds(0, 0, 63, 20);
		nomVendredi.add(lblVendredi);

		lblDateVendredi.setBounds(63, 0, 20, 20);
		nomVendredi.add(lblDateVendredi);
		plageVendrediMatin.setBackground(new Color(230, 230, 250));
		plageVendrediMatin.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(210, 180, 140)));

		plageVendrediMatin.setBounds(0, 105, 80, 85);
		plageVendredi.add(plageVendrediMatin);
		plageVendrediApresMidi.setBackground(new Color(230, 230, 250));
		plageVendrediApresMidi.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(210, 180, 140)));

		plageVendrediApresMidi.setBounds(0, 20, 80, 85);
		plageVendredi.add(plageVendrediApresMidi);
		plageHoraire.setBackground(SystemColor.inactiveCaption);

		plageHoraire.setBounds(0, 0, 15, 190);
		planning.add(plageHoraire);

		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnValider.setBounds(345, 235, 80, 20);
		frame.getContentPane().add(btnValider);

		/**
		 * On ajoute la liste des modules avec un nombre d'heures supérieures à 0
		 */
		ArrayList<Module> listeModuleDispo = new ArrayList<Module>();
		ModuleDao moduleDao = new ModuleDao();
		listeModuleDispo = moduleDao.findModuleAvecHeures(2015, "BTS SIO 2016");

		Session session = new Session();
		SessionDao sessionDao = new SessionDao();
		session = sessionDao.findSession("BTS SIO 2016");

		HashMap<Module, Integer> heureDispo = new HashMap<Module, Integer>();
		HeuresSessionModuleDao heureDispoDao = new HeuresSessionModuleDao();

		for(Module unModule  : listeModuleDispo){
			heureDispo.put(unModule, heureDispoDao.findHeuresSessionModule(unModule, session).getNbreHeuresDisponibles());
		}

		Set<Module> lesModules = heureDispo.keySet();
		for(Module leModule : lesModules){
			nomModule.addItem(" " + leModule.getNom() + " (" + heureDispo.get(leModule) + " heures disponible)");
		}

		/**
		 * On ajoute le module à une plage horaire, cela diminue le nombre d'heure restant du module
		 */
		SelectionnePlage selectionnePlage = new SelectionnePlage();
		plageLundiMatin.addMouseListener(selectionnePlage);
		plageLundiApresMidi.addMouseListener(selectionnePlage);
		plageMardiMatin.addMouseListener(selectionnePlage);
		plageMardiApresMidi.addMouseListener(selectionnePlage);
		plageMercrediMatin.addMouseListener(selectionnePlage);
		plageMercrediApresMidi.addMouseListener(selectionnePlage);
		plageJeudiMatin.addMouseListener(selectionnePlage);
		plageJeudiApresMidi.addMouseListener(selectionnePlage);
		plageVendrediMatin.addMouseListener(selectionnePlage);
		plageVendrediApresMidi.addMouseListener(selectionnePlage);		

	}

	public class SelectionnePlage implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			Object module = nomModule.getSelectedItem();
			System.out.println(module);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}

