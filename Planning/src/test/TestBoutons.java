package test;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;

import modele.Bouton;
import modele.Seance;
import modele.Seance.Creneau;
import modele.Session;
import dao.ModuleDao;
import dao.SalleDao;
import dao.SeanceDao;

import javax.swing.JPanel;
import javax.swing.JTable;
import swing.DonneesTableauDouble;

import javax.swing.JScrollPane;

public class TestBoutons {

	private JFrame frame;
	private JTable table;
	private JPanel panelBouttons;
	private ButtonGroup group;
	private Session session;
	private JPanel panelTableau;
	private Component[] tableau;
	private ArrayList<JRadioButton> tableauBoutton;
	private Seance seance;
	private SeanceDao seanceDao;
	private ModuleDao moduleDao;
	private Bouton groupeDeBoutons;
	private JScrollPane scrollPane;
	private DonneesTableauDouble donneesTableauDouble = new DonneesTableauDouble(2);
	/*private String[] entetes = { "Semaine", "LUNDI Matin",
			"Après-midi", "MARDI Matin", "Après-midi", "MERCREDI Matin",
			"Après-midi", "JEUDI Matin", "Après-midi", "VENDREDI matin",
			"Après-midi" };*/

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

		panelTableau = new JPanel();
		panelTableau.setBounds(6, 6, 930, 675);
		
		table = new JTable(donneesTableauDouble);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.addMouseListener(new ecouteur());
		table.setRowHeight(25);
		table.setRowMargin(5);
		panelTableau.add(table);
		

		scrollPane.setViewportView(panelTableau);
		//scrollPane.setColumnHeaderView(entetes);

		panelBouttons = new JPanel();
		panelBouttons.setBounds(959, 172, 235, 315);
		frame.getContentPane().add(panelBouttons);
		
		group = new ButtonGroup();
		groupeDeBoutons = new Bouton();
		groupeDeBoutons.remplir();
		ArrayList<JRadioButton> listeDesBoutons = groupeDeBoutons
				.getBoutonDesMatières();

		for (JRadioButton jRadioButton : listeDesBoutons) {
			panelBouttons.add(jRadioButton);
			group.add(jRadioButton);
		}
		session = new Session();
		session.setIdSession(2);


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
			new SalleDao();
			// salle = salleDao.findModuleByNom(1);
			String recupDate;
			int year;
			int month;
			int day;
			GregorianCalendar gregJour;

			for (int i = 0; i < tableau.length; i++) {
				tableauBoutton.add((JRadioButton) tableau[i]);
			}

			if(table.getSelectedRow() % 2 != 0 && table.getSelectedColumn() !=0){
				for (JRadioButton jRadioButton : tableauBoutton) {
					if (jRadioButton.isSelected()) {
						texteDuBouton = jRadioButton.getText();
						if (!texteDuBouton.equals("Supprimer")) {
							nomModule = texteDuBouton.split(" ")[0];
							seance.setIdModule(moduleDao.findModuleByNom(nomModule).getIdModule());
							// on fait un insert dans la table
							if (table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) == ""){
								if (table.getSelectedColumn() % 2 == 0) {
									seance.setCreneau(apresMidi);
									recupDate = (String) table.getValueAt(table.getSelectedRow() - 1, table.getSelectedColumn() -1);
									year = Integer.parseInt(recupDate.split("/")[2]);
									month = Integer.parseInt(recupDate.split("/")[1])-1;
									day= Integer.parseInt(recupDate.split("/")[0]);
									gregJour = new GregorianCalendar(year, month, day);
								}
								else{
									seance.setCreneau(matin);
									recupDate = (String) table.getValueAt(table.getSelectedRow() - 1, table.getSelectedColumn());
									year = Integer.parseInt(recupDate.split("/")[2]);
									month = Integer.parseInt(recupDate.split("/")[1])-1;
									day= Integer.parseInt(recupDate.split("/")[0]);
									gregJour = new GregorianCalendar(year, month, day);
								}
								seance.setDebut(gregJour);
								seance.setIdFormateur(moduleDao.findFormateurByNomModule(nomModule).getIdFormateur());
								seance.setIdSession(session.getIdSession());
								seance.setContenu(contenu);
								seance.setIdSalle(1);
								try{
									seanceDao.insertSeance(seance);
									table.setValueAt(/*"<html><center> " +*/ nomModule /*+ "<br>avec " + moduleDao.findFormateurByNomModule(nomModule).getPrenom()
										+ " " + moduleDao.findFormateurByNomModule(nomModule).getNom() + "<br>salle "
										+ salle.getNomSalle() + "</center></html>"*/, table.getSelectedRow(), table.getSelectedColumn());
								}catch (Exception ex){
									ex.getMessage();
									System.out.println("InserSeance échoué");
								}
							}
							else{
								seance.setIdModule(moduleDao.findModuleByNom(nomModule).getIdModule());
								seance.setIdFormateur(moduleDao.findFormateurByNomModule(nomModule).getIdFormateur());
								seance.setIdSalle(2);
								seanceDao.updateSeance(seance.getIdModule(), 
										seance.getIdFormateur(), seance.getIdSalle(), 
										contenu, gregJour, session.getIdSession());
							}
						} else {
							texteVide = "";
							// on fait un delete dans la table en se basant sur
							// l'heure de début
							if (table.getSelectedColumn() % 2 == 0) {
								seance.setCreneau(apresMidi);
								recupDate = (String) table.getValueAt(table.getSelectedRow() - 1, table.getSelectedColumn() -1);
								year = Integer.parseInt(recupDate.split("/")[2]);
								month = Integer.parseInt(recupDate.split("/")[1])-1;
								day= Integer.parseInt(recupDate.split("/")[0]);
								gregJour = new GregorianCalendar(year, month, day);
							}
							else{
								seance.setCreneau(matin);
								recupDate = (String) table.getValueAt(table.getSelectedRow() - 1, table.getSelectedColumn());
								year = Integer.parseInt(recupDate.split("/")[2]);
								month = Integer.parseInt(recupDate.split("/")[1])-1;
								day= Integer.parseInt(recupDate.split("/")[0]);
								gregJour = new GregorianCalendar(year, month, day);
							}
							seance.setDebut(gregJour);
							try{
								seanceDao.deleteSeance(seance.getDebut(), session.getIdSession());
								table.setValueAt(texteVide, table.getSelectedRow(),
										table.getSelectedColumn());
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
