package test;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JRadioButton;

import modele.HeuresSessionModule;
import modele.Seance;
import modele.Seance.Creneau;
import modele.Session;
import dao.HeuresSessionModuleDao;
import dao.ModuleDao;
import dao.SeanceDao;

import javax.swing.JPanel;
import javax.swing.JTable;

import swing.SelectionMatierePanel;
import swing.TableauPanel;

import javax.swing.JScrollPane;

public class TestBoutons {

	private JFrame frame;
	private JTable table;
	private JPanel panelBouttons;
	private Session session;
	private TableauPanel panelTableau;
	private Component[] tableau;
	private ArrayList<JRadioButton> tableauBoutton;
	private Seance seance;
	private SeanceDao seanceDao;
	private ModuleDao moduleDao;
	private JScrollPane scrollPane;
	private HeuresSessionModule heureDispo;
	private HeuresSessionModuleDao heureDispoDao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestBoutons window = new TestBoutons();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestBoutons() {
		initialize(1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int idSession) {

		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 940, 685);
		frame.getContentPane().add(scrollPane);

		panelTableau = new TableauPanel(idSession);
		table = panelTableau.getTable();
		table.addMouseListener(new ecouteur());

		scrollPane.setViewportView(panelTableau);

		panelBouttons = new SelectionMatierePanel(idSession);
		panelBouttons.setBounds(959, 172, 235, 315);
		frame.getContentPane().add(panelBouttons);

		session = new Session();
		session.setIdSession(idSession);
	}

	/**
	 * Methode qui recupere la date de la case dans laquel on à cliquer
	 * @return
	 */
	public GregorianCalendar recupereDateDeLaCaseSelectionnee() {
		String recupDate;
		int year;
		int month;
		int day;
		GregorianCalendar gregJour;

		if (table.getSelectedColumn() % 2 == 0) {
			recupDate = (String) table.getValueAt(table.getSelectedRow() - 1, table.getSelectedColumn() -1);
			year = Integer.parseInt(recupDate.split("/")[2]);
			month = Integer.parseInt(recupDate.split("/")[1])-1;
			day= Integer.parseInt(recupDate.split("/")[0]);
			gregJour = new GregorianCalendar(year, month, day);
		}
		else{
			recupDate = (String) table.getValueAt(table.getSelectedRow() - 1, table.getSelectedColumn());
			year = Integer.parseInt(recupDate.split("/")[2]);
			month = Integer.parseInt(recupDate.split("/")[1])-1;
			day= Integer.parseInt(recupDate.split("/")[0]);
			gregJour = new GregorianCalendar(year, month, day);
		}
		return gregJour;

	}

	public class ecouteur implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			String texteDuBouton = "";
			String nomModule;
			String texteVide = "";
			String contenu = "null";

			tableau = panelBouttons.getComponents();
			tableauBoutton = new ArrayList<JRadioButton>();
			seance = new Seance();
			Seance.Creneau matin = Creneau.MATIN;
			Seance.Creneau apresMidi = Creneau.APRES_MIDI;
			seanceDao = new SeanceDao();
			moduleDao = new ModuleDao();
			heureDispo = new HeuresSessionModule();
			heureDispoDao = new HeuresSessionModuleDao();


			for (int i = 0; i < tableau.length; i++) {
				tableauBoutton.add((JRadioButton) tableau[i]);
			}

			// si la ligne selectionnée est impaire et la colonne  different de 0
			if(table.getSelectedRow() % 2 != 0 && table.getSelectedColumn() !=0){
				for (JRadioButton jRadioButton : tableauBoutton) {
					if (jRadioButton.isSelected()) {
						texteDuBouton = jRadioButton.getText();
						// si le texte du bouton selectionné n'est pas Supprimer
						if (!texteDuBouton.equals("Supprimer")) {
							if (jRadioButton.getText().split(" ")[2].equals("0")) {
								break;
							}
							nomModule = texteDuBouton.split(" ")[0];
							seance.setIdModule(moduleDao.findModuleByNom(nomModule).getIdModule());
							// si la colonne est paire
							if (table.getSelectedColumn() % 2 == 0) {
								seance.setCreneau(apresMidi);
							}
							else{
								seance.setCreneau(matin);
							}
							
							seance.setDebut(recupereDateDeLaCaseSelectionnee());
							seance.setIdFormateur(moduleDao.findFormateurByNomModule(nomModule).getIdFormateur());
							seance.setIdSession(session.getIdSession());
							seance.setContenu(contenu);
							seance.setIdSalle(1);
							// On recupere le nombre d'heure dispo pour le module
							heureDispo = heureDispoDao.findHeuresSessionModule(seance.getIdSession(), seance.getIdModule());
							
							try{
								// Si il y a un module dans la case selectionnée
								if (table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) != ""){
									// On recupere le nom de l'ancien Module
									String ancienModule = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
									seanceDao.updateSeance(seance.getIdModule(), seance.getIdFormateur(), seance.getIdSalle(), 
											contenu, seance.getIdSession(), seance.getDebut());
									// On recupere le nom du nouveau Module
									String nouveauModule = jRadioButton.getText().split(" ")[0];
									// On parcourt les boutons
									for (JRadioButton jRadioButtonSuppr : tableauBoutton) {
										// Si le nom du bouton est egale à l'ancien
										if(jRadioButtonSuppr.getText().split(" ")[0].equals(ancienModule)){
											// On recupere le nombre d'heure avant l'update
											heureDispo = heureDispoDao.findHeuresSessionModule(seance.getIdSession(), 
													moduleDao.findModuleByNom(ancienModule).getIdModule());
											heureDispoDao.updateModuleAvecHeures(heureDispo, seance.getCreneau(), true);
											// On recupere le nombre d'heure apres l'update
											heureDispo = heureDispoDao.findHeuresSessionModule(seance.getIdSession(), 
													moduleDao.findModuleByNom(ancienModule).getIdModule());
											// On met à jour le texte du bouton pour afficher la bonne heure
											jRadioButtonSuppr.setText(ancienModule + " : " 	+ heureDispo.getNbreHeuresDisponibles() + " heures disponibles");
										}
										if(jRadioButtonSuppr.getText().split(" ")[0].equals(nouveauModule)){
											// On recupere le nombre d'heure avant l'update
											heureDispo = heureDispoDao.findHeuresSessionModule(seance.getIdSession(), 
													moduleDao.findModuleByNom(nouveauModule).getIdModule());
											heureDispoDao.updateModuleAvecHeures(heureDispo, seance.getCreneau(), false);
											// On recupere le nombre d'heure apres l'update
											heureDispo = heureDispoDao.findHeuresSessionModule(seance.getIdSession(), 
													moduleDao.findModuleByNom(nouveauModule).getIdModule());
											// On met à jour le texte du bouton pour afficher la bonne heure
											jRadioButtonSuppr.setText(nouveauModule + " : " 	+ heureDispo.getNbreHeuresDisponibles() + " heures disponibles");
										}
									}
								}
								else{
									// On insere la nouvelle seance
									seanceDao.insertSeance(seance);
									heureDispoDao.updateModuleAvecHeures(heureDispo, seance.getCreneau(), false);
									// On recupere le nombre d'heure apres le update
									heureDispo = heureDispoDao.findHeuresSessionModule(seance.getIdSession(), seance.getIdModule());
									// On met à jour le texte du bouton pour afficher la bonne heure
									jRadioButton.setText(nomModule + " : " 	+ heureDispo.getNbreHeuresDisponibles() + " heures disponibles");
								}
								// On affiche le nom du module dans le planning
								table.setValueAt(nomModule, 
										table.getSelectedRow(), table.getSelectedColumn());
							}catch (Exception ex){
								ex.getMessage();
								System.out.println("Insert Seance échoué");
							}
						} else {
							// On recupere a nouveau le nom du module
							nomModule = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
							// On recupe le creneau en fonction de la colonne choisie
							if (table.getSelectedColumn() % 2 == 0) {
								seance.setCreneau(apresMidi);
							}
							else{
								seance.setCreneau(matin);
							}
							
							seance.setDebut(recupereDateDeLaCaseSelectionnee());
							seance.setIdModule(moduleDao.findModuleByNom(nomModule).getIdModule());
							seance.setIdSession(session.getIdSession());
							// On recupere le nombre d'heure du module
							heureDispo = heureDispoDao.findHeuresSessionModule(seance.getIdSession(), seance.getIdModule());
							
							try{
								// On supprime la seance
								seanceDao.deleteSeance(seance.getDebut(), session.getIdSession());
								// On vide la case du planning
								table.setValueAt(texteVide, table.getSelectedRow(),
										table.getSelectedColumn());
								heureDispoDao.updateModuleAvecHeures(heureDispo, seance.getCreneau(), true);
								// On recupere le nombre d'heure dispo
								heureDispo = heureDispoDao.findHeuresSessionModule(seance.getIdSession(), seance.getIdModule());
								// On va parcourir les boutons
								for (JRadioButton jRadioButtonSuppr : tableauBoutton) {
									// Pour trouver celui du module selectionner
									if(jRadioButtonSuppr.getText().split(" ")[0].equals(nomModule)){
										// Et mettre à jour son nombre d'heure
										jRadioButtonSuppr.setText(nomModule + " : " + heureDispo.getNbreHeuresDisponibles() + " heures disponibles");
									}
								}
							}catch (Exception exc){
								exc.getMessage();
								System.out.println("DeleteSeance échoué");
							}
						}

					}
				}
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}
}
