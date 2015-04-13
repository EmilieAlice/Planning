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
import dao.SalleDao;
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 940, 685);
		frame.getContentPane().add(scrollPane);

		panelTableau = new TableauPanel(1);
		table = panelTableau.getTable();
		table.addMouseListener(new ecouteur());

		scrollPane.setViewportView(panelTableau);

		panelBouttons = new SelectionMatierePanel(1);
		panelBouttons.setBounds(959, 172, 235, 315);
		frame.getContentPane().add(panelBouttons);

		session = new Session();
		session.setIdSession(2);


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
			String texteVide;
			String contenu = "null";

			tableau = panelBouttons.getComponents();
			tableauBoutton = new ArrayList<JRadioButton>();
			seance = new Seance();
			Seance.Creneau matin = Creneau.MATIN;
			Seance.Creneau apresMidi = Creneau.APRES_MIDI;
			seanceDao = new SeanceDao();
			moduleDao = new ModuleDao();
			
			

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
							nomModule = texteDuBouton.split(" ")[0];
							seance.setIdModule(moduleDao.findModuleByNom(nomModule).getIdModule());
							// si il n'y a rien dans la case selectionnée
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
							heureDispo = heureDispoDao.findHeuresSessionModule(session, moduleDao.findModuleByNom(nomModule).getIdModule());
							try{
								if (table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) != ""){
									seanceDao.updateSeance(seance.getIdModule(), 
											seance.getIdFormateur(), seance.getIdSalle(), 
											contenu,  session.getIdSession(),recupereDateDeLaCaseSelectionnee());
									heureDispoDao.updateModuleAvecHeures(heureDispo, seance.getCreneau(), true);
								}
								else{
									seanceDao.insertSeance(seance);
									heureDispoDao.updateModuleAvecHeures(heureDispo, seance.getCreneau(), true);
								}
								table.setValueAt(nomModule, 
											table.getSelectedRow(), table.getSelectedColumn());
							}catch (Exception ex){
								ex.getMessage();
								System.out.println("Insert Seance échoué");
							}
						} else {
							texteVide = "";
							// on fait un delete dans la table en se basant sur
							// l'heure de début
							if (table.getSelectedColumn() % 2 == 0) {
								seance.setCreneau(apresMidi);
							}
							else{
								seance.setCreneau(matin);
							}
							seance.setDebut(recupereDateDeLaCaseSelectionnee());
							try{
								seanceDao.deleteSeance(seance.getDebut(), session.getIdSession());
								table.setValueAt(texteVide, table.getSelectedRow(),
										table.getSelectedColumn());

								heureDispoDao.updateModuleAvecHeures(heureDispo, seance.getCreneau(), false);
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
