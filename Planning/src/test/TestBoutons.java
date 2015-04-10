package test;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import modele.Module;
import modele.Salle;
import modele.Seance;
import modele.Seance.Creneau;
import modele.Session;
import dao.HeuresSessionModuleDao;
import dao.ModuleDao;
import dao.SalleDao;
import dao.SeanceDao;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;

import swing.DonneesTableau;
import swing.DonneesTableauDouble;

public class TestBoutons {

	private JFrame frame;
	private JTable table;
	private JPanel panelBouttons;
	private ButtonGroup group;
	private HeuresSessionModuleDao heuresSessionModuleDao;
	private Module maths;
	private Module anglais;
	private Module siDeux;
	private Session session;
	private JRadioButton mathsBouton;
	private JPanel panelTableau;
	private JRadioButton siDeuxBoutton;
	private JRadioButton anglaisBoutton;
	private JRadioButton rdbtnSupprimer;
	private Component[] tableau;
	private ArrayList<JRadioButton> tableauBoutton;
	private Seance seance; 
	private SeanceDao seanceDao;
	private ModuleDao moduleDao;
	private Salle salle;
	private SalleDao salleDao;

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
		heuresSessionModuleDao = new HeuresSessionModuleDao();

		maths = new Module();
		maths.setIdModule(2);

		anglais = new Module();
		anglais.setIdModule(3);

		siDeux = new Module();
		siDeux.setIdModule(1);

		session = new Session();
		session.setIdSession(1);

		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		group = new ButtonGroup();

		panelTableau = new JPanel();
		panelTableau.setBounds(6, 6, 941, 666);
		frame.getContentPane().add(panelTableau);

		table = new JTable(new DonneesTableauDouble());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.addMouseListener(new ecouteur());
		table.setRowHeight(25);
		table.setRowMargin(5);
		panelTableau.add(table);

		panelBouttons = new JPanel();
		panelBouttons.setBounds(973, 294, 195, 193);
		frame.getContentPane().add(panelBouttons);

		siDeuxBoutton = new JRadioButton();
		panelBouttons.add(siDeuxBoutton);
		siDeuxBoutton.setText("SI2 : "
				+ heuresSessionModuleDao.findHeuresSessionModule(siDeux, session)
				.getNbreHeuresDisponibles() + " Heures");
		group.add(siDeuxBoutton);

		anglaisBoutton = new JRadioButton();
		panelBouttons.add(anglaisBoutton);
		anglaisBoutton.setText("Anglais : "
				+ heuresSessionModuleDao.findHeuresSessionModule(anglais, session)
				.getNbreHeuresDisponibles() + " Heures");
		group.add(anglaisBoutton);

		mathsBouton = new JRadioButton();
		panelBouttons.add(mathsBouton);
		mathsBouton.setText("Maths : "
				+ heuresSessionModuleDao.findHeuresSessionModule(maths, session)
				.getNbreHeuresDisponibles() + " Heures");
		group.add(mathsBouton);

		rdbtnSupprimer = new JRadioButton("Supprimer");
		group.add(rdbtnSupprimer);
		panelBouttons.add(rdbtnSupprimer);
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
			salleDao = new SalleDao();
			//salle = salleDao.findModuleByNom(1);
			String recupDate;
			int year;
			int month;
			int day;
			GregorianCalendar gregJour;

			for (int i = 0; i < tableau.length; i++) {
				tableauBoutton.add((JRadioButton) tableau[i]);
			}

			for (JRadioButton jRadioButton : tableauBoutton) {
				if (jRadioButton.isSelected()) {
					texteDuBouton = jRadioButton.getText();
					if (!texteDuBouton.equals("Supprimer")) {
						nomModule = texteDuBouton.split(" ")[0];
						seance.setIdModule(moduleDao.findModuleByNom(nomModule).getIdModule());
						table.setValueAt(/*"<html><center> " +*/ nomModule /*+ "<br>avec " + moduleDao.findFormateurByNomModule(nomModule).getPrenom()
								+ " " + moduleDao.findFormateurByNomModule(nomModule).getNom() + "<br>salle "
								+ salle.getNomSalle() + "</center></html>"*/, table.getSelectedRow(), table.getSelectedColumn());
						// on fait un insert dans la table
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
						seanceDao.insertSeance(seance);
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
						seanceDao.deleteSeance(seance.getDebut());
						table.setValueAt(texteVide, table.getSelectedRow(),
								table.getSelectedColumn());
					}

				}
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
