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
import dao.PersonneDao;
import dao.SessionDao;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import modele.HeuresSessionModule;
import modele.Module;
import modele.Personne;
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

import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;

public class Planning {

	private int heuresDuModule;
	private JFrame frame = new JFrame();
	private JPanel calendrier = new JPanel();
	private JLabel lblSemaine = new JLabel("Semaine");
	private JLabel numeroSemaine = new JLabel("");
	private JLabel lblMatiere = new JLabel("Matiere");
	private JComboBox nomModule = new JComboBox();
	private JPanel planning = new JPanel();
	private JPanel plageLundi = new JPanel();
	private JPanel nomLundi = new JPanel();
	private JLabel lblLundi = new JLabel("  Lundi");
	private JLabel lblDateLundi = new JLabel("");
	private JLabel plageLundiMatin = new JLabel();
	private JLabel plageLundiApresMidi = new JLabel();
	private JPanel plageMardi = new JPanel();
	private JPanel nomMardi = new JPanel();
	private JLabel lblMardi = new JLabel("  Mardi");
	private JLabel lblDateMardi = new JLabel("");
	private JLabel plageMardiMatin = new JLabel();
	private JLabel plageMardiApresMidi = new JLabel();
	private JPanel plageMercredi = new JPanel();
	private JPanel nomMercredi = new JPanel();
	private JLabel lblMercredi = new JLabel("  Mercredi");
	private JLabel lblDateMercredi = new JLabel("");
	private JLabel plageMercrediMatin = new JLabel();
	private JLabel plageMercrediApresMidi = new JLabel();
	private JPanel plageJeudi = new JPanel();
	private JPanel nomJeudi = new JPanel();
	private JLabel lblJeudi = new JLabel("   Jeudi");
	private JLabel lblDateJeudi = new JLabel("");
	private JLabel plageJeudiMatin = new JLabel();
	private JLabel plageJeudiApresMidi = new JLabel();
	private JPanel plageVendredi = new JPanel();
	private JPanel nomVendredi = new JPanel();
	private JLabel lblVendredi = new JLabel("   Vendredi");
	private JLabel lblDateVendredi = new JLabel("");
	private JLabel plageVendrediMatin = new JLabel();
	private JLabel plageVendrediApresMidi = new JLabel();
	private JPanel plageHoraire = new JPanel();
	private JButton btnValider = new JButton("Valider");
	private JButton btnSupprimer = new JButton();
	private JLabel lblPlageSelectionne = new JLabel();
	private JButton btnSuppLundiMatin = new JButton("Supprimer");
	private JButton btnSuppLundiApresMidi = new JButton("Supprimer");
	private JButton btnSuppMardiMatin = new JButton("Supprimer");
	private JButton btnSuppMardiApresMidi = new JButton("Supprimer");
	private JButton btnSuppMercrediMatin = new JButton("Supprimer");
	private JButton btnSuppMercrediApresMidi = new JButton("Supprimer");
	private JButton btnSuppJeudiMatin = new JButton("Supprimer");
	private JButton btnSuppJeudiApresMidi = new JButton("Supprimer");
	private JButton btnSuppVendrediMatin = new JButton("Supprimer");
	private JButton btnSuppVendrediApresMidi = new JButton("Supprimer");


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
		frame.setResizable(false);
		frame.setBounds(50, 50, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		calendrier.setBackground(new Color(222, 184, 135));
		//frame.setResizable(true);

		calendrier.setBounds(25, 25, 950, 600);
		frame.getContentPane().add(calendrier);
		calendrier.setLayout(null);
		lblSemaine.setBounds(5, 5, 50, 20);
		calendrier.add(lblSemaine);
		numeroSemaine.setBounds(55, 5, 15, 20);
		calendrier.add(numeroSemaine);
		lblMatiere.setBounds(400, 5, 50, 20);
		calendrier.add(lblMatiere);
		nomModule.setBounds(450, 5, 300, 20);
		nomModule.setBackground(new Color(210, 180, 140));
		nomModule.addItem(" Selectionner le module ");
		calendrier.add(nomModule);
		planning.setBounds(0, 30, 950, 550);

		planning.setBackground(SystemColor.activeCaption);
		planning.setLayout(null);

		plageLundi.setBounds(25, 0, 185, 550);
		planning.add(plageLundi);
		plageLundi.setLayout(null);
		nomLundi.setBackground(SystemColor.inactiveCaption);

		nomLundi.setBounds(0, 0, 185, 50);
		plageLundi.add(nomLundi);
		nomLundi.setLayout(null);
		lblLundi.setBackground(new Color(230, 230, 250));

		lblLundi.setBounds(0, 0, 135, 50);
		nomLundi.add(lblLundi);

		lblDateLundi.setBounds(150, 0, 35, 50);
		nomLundi.add(lblDateLundi);
		plageLundiMatin.setBackground(new Color(230, 230, 250));
		plageLundiMatin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(210, 180, 140)));

		plageLundiMatin.setBounds(0, 50, 185, 200);
		plageLundi.add(plageLundiMatin);
		btnSuppLundiMatin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnSuppLundiMatin.setBounds(75, 165, 90, 20);
		btnSuppLundiApresMidi.setBounds(75, 210, 90, 20);
		btnSuppMardiMatin.setBounds(75, 165, 90, 20);
		btnSuppMardiApresMidi.setBounds(75, 210, 90, 20);
		btnSuppMercrediMatin.setBounds(75, 165, 90, 20);
		btnSuppMercrediApresMidi.setBounds(75, 210, 90, 20);
		btnSuppJeudiMatin.setBounds(75, 165, 90, 20);
		btnSuppJeudiApresMidi.setBounds(75, 210, 90, 20);
		btnSuppVendrediMatin.setBounds(75, 165, 90, 20);
		btnSuppVendrediApresMidi.setBounds(75, 210, 90, 20);
		plageLundiMatin.add(btnSuppLundiMatin);
		plageLundiApresMidi.add(btnSuppLundiApresMidi);
		plageMardiMatin.add(btnSuppMardiMatin);
		plageMardiApresMidi.add(btnSuppMardiApresMidi);
		plageMercrediMatin.add(btnSuppMercrediMatin);
		plageMercrediApresMidi.add(btnSuppMercrediApresMidi);
		plageJeudiMatin.add(btnSuppJeudiMatin);
		plageJeudiApresMidi.add(btnSuppJeudiApresMidi);
		plageVendrediMatin.add(btnSuppVendrediMatin);
		plageVendrediApresMidi.add(btnSuppVendrediApresMidi);
		btnSuppLundiMatin.setVisible(false);
		btnSuppLundiApresMidi.setVisible(false);
		btnSuppMardiMatin.setVisible(false);
		btnSuppMardiApresMidi.setVisible(false);
		btnSuppMercrediMatin.setVisible(false);
		btnSuppMercrediApresMidi.setVisible(false);
		btnSuppJeudiMatin.setVisible(false);
		btnSuppJeudiApresMidi.setVisible(false);
		btnSuppVendrediMatin.setVisible(false);
		btnSuppVendrediApresMidi.setVisible(false);
		
		plageLundiApresMidi.setBackground(new Color(230, 230, 250));
		plageLundiApresMidi.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(210, 180, 140)));

		plageLundiApresMidi.setBounds(0, 300, 185, 250);
		plageLundi.add(plageLundiApresMidi);

		plageMardi.setBounds(210, 0, 185, 550);
		planning.add(plageMardi);
		plageMardi.setLayout(null);
		nomMardi.setBackground(SystemColor.inactiveCaption);

		nomMardi.setBounds(0, 0, 185, 50);
		plageMardi.add(nomMardi);
		nomMardi.setLayout(null);
		lblMardi.setBackground(SystemColor.inactiveCaption);

		lblMardi.setBounds(0, 0, 135, 50);
		nomMardi.add(lblMardi);

		lblDateMardi.setBounds(150, 0, 35, 50);
		nomMardi.add(lblDateMardi);
		plageMardiMatin.setBackground(new Color(230, 230, 250));
		plageMardiMatin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(210, 180, 140)));

		plageMardiMatin.setBounds(0, 50, 185, 200);
		plageMardi.add(plageMardiMatin);
		plageMardiApresMidi.setBackground(new Color(230, 230, 250));
		plageMardiApresMidi.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(210, 180, 140)));

		plageMardiApresMidi.setBounds(0, 300, 185, 250);
		plageMardi.add(plageMardiApresMidi);

		plageMercredi.setBounds(395, 0, 185, 550);
		planning.add(plageMercredi);
		plageMercredi.setLayout(null);
		nomMercredi.setBackground(SystemColor.inactiveCaption);

		nomMercredi.setBounds(0, 0, 185, 50);
		plageMercredi.add(nomMercredi);
		nomMercredi.setLayout(null);

		lblMercredi.setBounds(0, 0, 135, 50);
		nomMercredi.add(lblMercredi);

		lblDateMercredi.setBounds(150, 0, 35, 50);
		nomMercredi.add(lblDateMercredi);
		plageMercrediMatin.setBackground(new Color(230, 230, 250));
		plageMercrediMatin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(210, 180, 140)));

		plageMercrediMatin.setBounds(0, 50, 183, 200);
		plageMercredi.add(plageMercrediMatin);
		plageMercrediApresMidi.setBackground(new Color(230, 230, 250));
		plageMercrediApresMidi.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(210, 180, 140)));

		plageMercrediApresMidi.setBounds(0, 300, 185, 250);
		plageMercredi.add(plageMercrediApresMidi);

		plageJeudi.setBounds(580, 0, 185, 550);
		planning.add(plageJeudi);
		plageJeudi.setLayout(null);
		nomJeudi.setBackground(SystemColor.inactiveCaption);

		nomJeudi.setBounds(0, 0, 185, 50);
		plageJeudi.add(nomJeudi);
		nomJeudi.setLayout(null);

		lblJeudi.setBounds(0, 0, 135, 50);
		nomJeudi.add(lblJeudi);

		lblDateJeudi.setBounds(150, 0, 35, 50);
		nomJeudi.add(lblDateJeudi);
		plageJeudiMatin.setBackground(new Color(230, 230, 250));
		plageJeudiMatin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(210, 180, 140)));

		plageJeudiMatin.setBounds(0, 50, 183, 200);
		plageJeudi.add(plageJeudiMatin);
		plageJeudiApresMidi.setBackground(new Color(230, 230, 250));
		plageJeudiApresMidi.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(210, 180, 140)));

		plageJeudiApresMidi.setBounds(0, 300, 185, 250);
		plageJeudi.add(plageJeudiApresMidi);

		plageVendredi.setBounds(765, 0, 185, 550);
		planning.add(plageVendredi);
		plageVendredi.setLayout(null);
		nomVendredi.setBackground(SystemColor.inactiveCaption);

		nomVendredi.setBounds(0, 0, 185, 50);
		plageVendredi.add(nomVendredi);
		nomVendredi.setLayout(null);

		lblVendredi.setBounds(0, 0, 135, 50);
		nomVendredi.add(lblVendredi);

		lblDateVendredi.setBounds(150, 0, 35, 50);
		nomVendredi.add(lblDateVendredi);
		plageVendrediMatin.setBackground(new Color(230, 230, 250));
		plageVendrediMatin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(210, 180, 140)));

		plageVendrediMatin.setBounds(0, 50, 185, 200);
		plageVendredi.add(plageVendrediMatin);
		plageVendrediApresMidi.setBackground(new Color(230, 230, 250));
		plageVendrediApresMidi.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(210, 180, 140)));

		plageVendrediApresMidi.setBounds(0, 300, 185, 250);
		plageVendredi.add(plageVendrediApresMidi);
		plageHoraire.setBackground(SystemColor.inactiveCaption);

		plageHoraire.setBounds(0, 0, 25, 550);
		planning.add(plageHoraire);
		calendrier.add(planning);
		btnValider.setBounds(850, 5, 80, 20);
		calendrier.add(btnValider);

		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSupprimer.setBounds(0, 0, 100, 20);

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
			String string = module.toString();
			String[] infos = string.split(" ");
			lblPlageSelectionne= (JLabel) e.getComponent();
			

			Personne personne = new Personne();
			PersonneDao personneDao = new PersonneDao();
			personne = personneDao.findByNomModule(infos[1]);
			lblPlageSelectionne.setText("<html><center>Séance de " + infos[1] + "<br>Avec " + personne.getNom() + " " +
					personne.getPrenom() + "</center></html>");
			
			if (lblPlageSelectionne.getSize().height < 201){
				btnSupprimer = (JButton) lblPlageSelectionne.getComponentAt(75, 165);
				btnSupprimer.setVisible(true);
				heuresDuModule = 3;
				}
			else{
				btnSupprimer = (JButton) lblPlageSelectionne.getComponentAt(75, 210);
				btnSupprimer.setVisible(true);
				heuresDuModule = 4;
			}
				
			Module leModule = new Module();
			ModuleDao unModuleDao = new ModuleDao();
			leModule = unModuleDao.findModule(infos[1]);
			
			Session session = new Session();
			SessionDao sessionDao = new SessionDao();
			session = sessionDao.findSession("BTS SIO 2016");
			
			HeuresSessionModule heureSessionModule = new HeuresSessionModule();
			HeuresSessionModuleDao heuresSessionDao = new HeuresSessionModuleDao();
			heureSessionModule = heuresSessionDao.findHeuresSessionModule(leModule, session);
			heuresSessionDao.updateModuleAvecHeures(heureSessionModule, heuresDuModule, false);
			
			if(infos[1] == "Selectionner")
				System.out.println("Selectionner un module");
			else 
				System.out.println("Séance de " + infos[1]);

			ArrayList<Module> listeModuleDispo = new ArrayList<Module>();
			ModuleDao moduleDao = new ModuleDao();
			listeModuleDispo = moduleDao.findModuleAvecHeures(2015, "BTS SIO 2016");
			
			HashMap<Module, Integer> heureDispo = new HashMap<Module, Integer>();
			HeuresSessionModuleDao heureDispoDao = new HeuresSessionModuleDao();

			for(Module unModule  : listeModuleDispo){
				heureDispo.put(unModule, heureDispoDao.findHeuresSessionModule(unModule, session).getNbreHeuresDisponibles());
			}

			Set<Module> lesModules = heureDispo.keySet();
			String item;
			for(Module ceModule : lesModules){
				item = " " + ceModule.getNom() + " (" + heureDispo.get(ceModule) + " heures disponible)";
				nomModule.addItem(item);
			}
			
			btnSupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Module leModule = new Module();
					ModuleDao unModuleDao = new ModuleDao();
					leModule = unModuleDao.findModule(infos[1]);
					
					Session session = new Session();
					SessionDao sessionDao = new SessionDao();
					session = sessionDao.findSession("BTS SIO 2016");
					
					HeuresSessionModule heureSessionModule = new HeuresSessionModule();
					HeuresSessionModuleDao heuresSessionDao = new HeuresSessionModuleDao();
					heureSessionModule = heuresSessionDao.findHeuresSessionModule(leModule, session);
					
					heuresSessionDao.updateModuleAvecHeures(heureSessionModule, heuresDuModule, true);
					//lblPlageSelectionne.remove
					btnSupprimer.setVisible(false);
				}
			});
			 
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			/*if(plageLundiMatin.getToolTipText() != null) 
			System.out.println("plein");
			else
				System.err.println("vide");*/
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//vide pour le moment
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//vide pour le moment
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//vide pour le moment
		}
	}
}

