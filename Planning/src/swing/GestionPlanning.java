package swing;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import dao.ContrainteFormateurDao;
import dao.HeuresSessionModuleDao;
import dao.ModuleDao;
import dao.SeanceDao;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class GestionPlanning {

	private JFrame frame;
	private JTable table;
	private JPanel panelBouttons;
	private Session session;
	private TableauPanel panelTableau;
	private Component[] tableau;
	private ArrayList<JRadioButton> tableauBoutton;
	private JScrollPane scrollPane;
	private JTable tableListeSeance;
	private JScrollPane scrollPaneTableauSeance;
	private DonneesTableauListeSeances donneesSeance;

	/**
	 * Permet de lancer la fenetre
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionPlanning window = new GestionPlanning();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialise la fenetre
	 */
	public GestionPlanning() {
		initialize(1);
	}

	/**
	 * Methode qui initialise la fenetre grace à un id session
	 * 
	 * @param idSession
	 */
	private void initialize(int idSession) {

		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 940, 685);
		frame.getContentPane().add(scrollPane);

		scrollPaneTableauSeance = new JScrollPane();
		scrollPaneTableauSeance.setBounds(981, 385, 293, 320);
		frame.getContentPane().add(scrollPaneTableauSeance);

		panelTableau = new TableauPanel();
		panelTableau.remplir(idSession);
		table = panelTableau.getTable();
		table.addMouseListener(new ecouteur());
		table.addKeyListener(new ecouteur());

		scrollPane.setViewportView(table);

		panelBouttons = new SelectionMatierePanel(idSession);
		panelBouttons.setBounds(981, 64, 293, 276);
		frame.getContentPane().add(panelBouttons);

		tableau = panelBouttons.getComponents();
		tableauBoutton = new ArrayList<JRadioButton>();
		for (int i = 0; i < tableau.length; i++) {
			tableauBoutton.add((JRadioButton) tableau[i]);
		}
		for (JRadioButton jRadioButton : tableauBoutton) {
			String texteDuBouton = jRadioButton.getText();
			/* si le texte du bouton selectionné n'est pas Supprimer */
			if (!texteDuBouton.equals("Supprimer")) {
				jRadioButton.addActionListener(new ecouteurBoutton());
			}
		}

		session = new Session();
		session.setIdSession(idSession);
	}

	/**
	 * Methode qui recupere la date de la case dans laquel on à cliquer
	 * 
	 * @return GregorianCalendar
	 */
	public GregorianCalendar recupereDateDeLaCaseSelectionnee() {
		String recupDate;
		int year;
		int month;
		int day;
		GregorianCalendar gregJour;

		/*
		 * Si le numéro de colonne est paire alors la date est située juste au
		 * dessus
		 */
		if (table.getSelectedColumn() % 2 == 0) {
			recupDate = (String) table.getValueAt(table.getSelectedRow() - 1,
					table.getSelectedColumn() - 1);
			if (recupDate != null) {
				year = Integer.parseInt(recupDate.split("/")[2]);
				month = Integer.parseInt(recupDate.split("/")[1]) - 1;
				day = Integer.parseInt(recupDate.split("/")[0]);
				gregJour = new GregorianCalendar(year, month, day);
			} else {
				gregJour = null;
			}
		} else {
			recupDate = (String) table.getValueAt(table.getSelectedRow() - 1,
					table.getSelectedColumn());
			if (recupDate != null) {
				year = Integer.parseInt(recupDate.split("/")[2]);
				month = Integer.parseInt(recupDate.split("/")[1]) - 1;
				day = Integer.parseInt(recupDate.split("/")[0]);
				gregJour = new GregorianCalendar(year, month, day);
			} else {
				gregJour = null;
			}
		}
		return gregJour;

	}

	/**
	 * 
	 * Inner Classe permettant d'implementer le MouseListener et KeyListener
	 * pour reagir au click et au bouton clavier
	 * 
	 * @author Alice
	 *
	 */
	public class ecouteur implements MouseListener, KeyListener {

		/* On initialise les objets */
		Seance seance = new Seance();
		SeanceDao seanceDao = new SeanceDao();
		ModuleDao moduleDao = new ModuleDao();
		HeuresSessionModule heureDispo = new HeuresSessionModule();
		HeuresSessionModuleDao heureDispoDao = new HeuresSessionModuleDao();

		Seance.Creneau matin = Creneau.MATIN;
		Seance.Creneau apresMidi = Creneau.APRES_MIDI;

		String texteDuBouton = "";
		String nomModule;
		String texteVide = "";
		String contenu = "null";

		/**
		 * Permet l'insertion, la suppression et l'update d'une seance
		 */
		@Override
		public void mouseClicked(MouseEvent e) {

			ArrayList<Integer> listeContrainteFormateur = new ArrayList<Integer>();
			ContrainteFormateurDao contrainteDao = new ContrainteFormateurDao();
			/*
			 * On récupère les boutons du panel des boutons et on remplit un
			 * tableau avec
			 */
			tableau = panelBouttons.getComponents();
			tableauBoutton = new ArrayList<JRadioButton>();
			for (int i = 0; i < tableau.length; i++) {
				tableauBoutton.add((JRadioButton) tableau[i]);
			}

			/*
			 * si la ligne selectionnée est impaire et la colonne differente de
			 * 0
			 */
			if (table.getSelectedRow() % 2 != 0
					&& table.getSelectedColumn() != 0) {
				for (JRadioButton jRadioButton : tableauBoutton) {
					/* Si un bouton est selectione */
					if (jRadioButton.isSelected()) {
						texteDuBouton = jRadioButton.getText();
						/* si le texte du bouton selectionné n'est pas Supprimer */
						if (!texteDuBouton.equals("Supprimer")) {
							if (jRadioButton.getText().split(" ")[2]
									.equals("0")) {
								break;
							}
							nomModule = texteDuBouton.split(" ")[0];
							int idFormateur = moduleDao.findFormateurByNomModule(nomModule).getIdFormateur();
							listeContrainteFormateur = contrainteDao.donneJourCreneauIndispo(idFormateur);
							boolean testContrainte = false;
							for (Integer integer : listeContrainteFormateur) {
								if (table.getSelectedColumn() == integer) {	
									testContrainte = true;
									break;
								}
							}
							if (testContrainte) {
								break;
							}
							seance.setIdModule(moduleDao.findModuleByNom(
									nomModule).getIdModule());
							/* si la colonne est paire */
							if (table.getSelectedColumn() % 2 == 0) {
								seance.setCreneau(apresMidi);
							} else {
								seance.setCreneau(matin);
							}
							/*
							 * On remplit l'objet séance si la date recuperé est
							 * non null
							 */
							if (recupereDateDeLaCaseSelectionnee() != null) {
								seance.setDebut(recupereDateDeLaCaseSelectionnee());
								seance.setIdFormateur(moduleDao
										.findFormateurByNomModule(nomModule)
										.getIdFormateur());
								seance.setIdSession(session.getIdSession());
								seance.setContenu(contenu);
								seance.setIdSalle(1);
								/*
								 * On recupere le nombre d'heure dispo pour le
								 * module
								 */
								heureDispo = heureDispoDao
										.findHeuresSessionModule(
												seance.getIdSession(),
												seance.getIdModule());

								try {
									/*
									 * Si il y a un module dans la case
									 * selectionnée
									 */
									if (table.getValueAt(
											table.getSelectedRow(),
											table.getSelectedColumn()) != "") {
										/* On recupere le nom de l'ancien Module */
										String ancienModule = (String) table
												.getValueAt(
														table.getSelectedRow(),
														table.getSelectedColumn());
										seanceDao.updateSeance(
												seance.getIdModule(),
												seance.getIdFormateur(),
												seance.getIdSalle(), contenu,
												seance.getIdSession(),
												seance.getDebut());
										/*
										 * On instanci le tableau contenant la
										 * liste des seances puis on le rempli
										 */
										tableListeSeance = new JTable(
												donneesSeance);
										donneesSeance.remplir(
												session.getIdSession(),
												nomModule);
										scrollPaneTableauSeance
												.setViewportView(tableListeSeance);
										/* On recupere le nom du nouveau Module */
										String nouveauModule = jRadioButton
												.getText().split(" ")[0];
										/* On parcourt les boutons */
										for (JRadioButton jRadioButtonSuppr : tableauBoutton) {
											/*
											 * Si le nom du bouton est egale à
											 * l'ancien module
											 */
											if (jRadioButtonSuppr.getText()
													.split(" ")[0]
													.equals(ancienModule)) {
												/*
												 * On recupere le nombre d'heure
												 * avant l'update
												 */
												heureDispo = heureDispoDao
														.findHeuresSessionModule(
																seance.getIdSession(),
																moduleDao
																		.findModuleByNom(
																				ancienModule)
																		.getIdModule());
												heureDispoDao
														.updateModuleAvecHeures(
																heureDispo,
																seance.getCreneau(),
																true);
												/*
												 * On recupere le nombre d'heure
												 * apres l'update
												 */
												heureDispo = heureDispoDao
														.findHeuresSessionModule(
																seance.getIdSession(),
																moduleDao
																		.findModuleByNom(
																				ancienModule)
																		.getIdModule());
												/*
												 * on met à jour le texte du
												 * bouton pour afficher la bonne
												 * heure
												 */
												jRadioButtonSuppr
														.setText(ancienModule
																+ " : "
																+ heureDispo
																		.getNbreHeuresDisponibles()
																+ "/"
																+ moduleDao
																		.findModuleByNom(
																				ancienModule)
																		.getNbHeuresAnnuelles()
																+ " heures disponibles");
											}
											/*
											 * Si le nom du bouton est egale au
											 * nouveau module
											 */
											if (jRadioButtonSuppr.getText()
													.split(" ")[0]
													.equals(nouveauModule)) {
												/*
												 * On recupere le nombre d'heure
												 * avant l'update
												 */
												heureDispo = heureDispoDao
														.findHeuresSessionModule(
																seance.getIdSession(),
																moduleDao
																		.findModuleByNom(
																				nouveauModule)
																		.getIdModule());
												heureDispoDao
														.updateModuleAvecHeures(
																heureDispo,
																seance.getCreneau(),
																false);
												/*
												 * On recupere le nombre d'heure
												 * apres l'update
												 */
												heureDispo = heureDispoDao
														.findHeuresSessionModule(
																seance.getIdSession(),
																moduleDao
																		.findModuleByNom(
																				nouveauModule)
																		.getIdModule());
												/*
												 * On met à jour le texte du
												 * bouton pour afficher la bonne
												 * heure
												 */
												jRadioButtonSuppr
														.setText(nouveauModule
																+ " : "
																+ heureDispo
																		.getNbreHeuresDisponibles()
																+ "/"
																+ moduleDao
																		.findModuleByNom(
																				nouveauModule)
																		.getNbHeuresAnnuelles()
																+ " heures disponibles");
											}
										}
									} else {
										/*
										 * Si il n'y a pas de module dans la
										 * case sélectionnées on insere la
										 * nouvelle seance
										 */
										seanceDao.insertSeance(seance);
										heureDispoDao.updateModuleAvecHeures(
												heureDispo,
												seance.getCreneau(), false);
										/*
										 * On instanci la table de la liste des
										 * seances et on la rempli
										 */
										tableListeSeance = new JTable(
												donneesSeance);
										donneesSeance.remplir(
												session.getIdSession(),
												nomModule);
										scrollPaneTableauSeance
												.setViewportView(tableListeSeance);
										/*
										 * On recupere le nombre d'heure apres
										 * le update
										 */
										heureDispo = heureDispoDao
												.findHeuresSessionModule(
														seance.getIdSession(),
														seance.getIdModule());
										/*
										 * On met à jour le texte du bouton pour
										 * afficher la bonne heure
										 */
										jRadioButton
												.setText(nomModule
														+ " : "
														+ heureDispo
																.getNbreHeuresDisponibles()
														+ "/"
														+ moduleDao
																.findModuleByNom(
																		nomModule)
																.getNbHeuresAnnuelles()
														+ " heures disponibles");
									}
									/*
									 * On affiche le nom du module dans le
									 * planning
									 */
									table.setValueAt(nomModule,
											table.getSelectedRow(),
											table.getSelectedColumn());
								} catch (Exception ex) {
									ex.getMessage();
									System.out.println("Insert Seance échoué");
								}
							}
						} else {
							/*
							 * Si le bouton sélectionné est supprimer on
							 * recupere a nouveau le nom du module
							 */
							nomModule = (String) table.getValueAt(
									table.getSelectedRow(),
									table.getSelectedColumn());
							/* Si la case selectionné est vide et non null */
							if (nomModule != "" && nomModule != null) {
								/*
								 * On recupe le creneau en fonction de la
								 * colonne choisie
								 */
								if (table.getSelectedColumn() % 2 == 0) {
									seance.setCreneau(apresMidi);
								} else {
									seance.setCreneau(matin);
								}
								/* Si la date est non null */
								if (recupereDateDeLaCaseSelectionnee() != null) {
									seance.setDebut(recupereDateDeLaCaseSelectionnee());
									seance.setIdModule(moduleDao
											.findModuleByNom(nomModule)
											.getIdModule());
									seance.setIdSession(session.getIdSession());
									/* On recupere le nombre d'heure du module */
									heureDispo = heureDispoDao
											.findHeuresSessionModule(
													seance.getIdSession(),
													seance.getIdModule());

									try {
										/* On supprime la seance */
										seanceDao.deleteSeance(
												seance.getDebut(),
												session.getIdSession());
										/*
										 * On instanci la table de la liste des
										 * seances et on la rempli
										 */
										tableListeSeance = new JTable(
												donneesSeance);
										donneesSeance.remplir(
												session.getIdSession(),
												nomModule);
										scrollPaneTableauSeance
												.setViewportView(tableListeSeance);
										/* On vide la case du planning */
										table.setValueAt(texteVide,
												table.getSelectedRow(),
												table.getSelectedColumn());
										heureDispoDao.updateModuleAvecHeures(
												heureDispo,
												seance.getCreneau(), true);
										/* On recupere le nombre d'heure dispo */
										heureDispo = heureDispoDao
												.findHeuresSessionModule(
														seance.getIdSession(),
														seance.getIdModule());
										/* On va parcourir les boutons */
										for (JRadioButton jRadioButtonSuppr : tableauBoutton) {
											/*
											 * Pour trouver celui du module
											 * selectionné
											 */
											if (jRadioButtonSuppr.getText()
													.split(" ")[0]
													.equals(nomModule)) {
												/*
												 * Et mettre à jour son nombre
												 * d'heure
												 */
												jRadioButtonSuppr
														.setText(nomModule
																+ " : "
																+ heureDispo
																		.getNbreHeuresDisponibles()
																+ "/"
																+ moduleDao
																		.findModuleByNom(
																				nomModule)
																		.getNbHeuresAnnuelles()
																+ " heures disponibles");
											}
										}
									} catch (Exception exc) {
										exc.getMessage();
										System.out
												.println("DeleteSeance échoué");
									}
								}
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

		/**
		 * Permet de supprimer la seance grace au bouton suppr du clavier
		 */
		@Override
		public void keyPressed(KeyEvent arg0) {
			/* Si la touche clavier est "suppr" */
			if (arg0.getKeyCode() == KeyEvent.VK_DELETE
					&& table.getSelectedRow() % 2 != 0) {
				nomModule = (String) table.getValueAt(table.getSelectedRow(),
						table.getSelectedColumn());
				/*
				 * On recupe le creneau en fonction de la colonne choisie
				 */
				if (nomModule != "") {
					/* On determine si c'est le creneau matin ou apres-midi */
					if (table.getSelectedColumn() % 2 == 0) {
						seance.setCreneau(apresMidi);
					} else {
						seance.setCreneau(matin);
					}
					/* Si la date est non null */
					if (recupereDateDeLaCaseSelectionnee() != null) {
						seance.setDebut(recupereDateDeLaCaseSelectionnee());
						seance.setIdModule(moduleDao.findModuleByNom(nomModule)
								.getIdModule());
						seance.setIdSession(session.getIdSession());
						/* On recupere le nombre d'heure du module */
						heureDispo = heureDispoDao.findHeuresSessionModule(
								seance.getIdSession(), seance.getIdModule());

						try {
							/* On supprime la seance */
							seanceDao.deleteSeance(seance.getDebut(),
									session.getIdSession());
							/*
							 * On instanci la table de la liste des seances et
							 * on la rempli
							 */
							tableListeSeance = new JTable(donneesSeance);
							donneesSeance.remplir(session.getIdSession(),
									nomModule);
							scrollPaneTableauSeance
									.setViewportView(tableListeSeance);
							/* On vide la case du planning */
							table.setValueAt(texteVide, table.getSelectedRow(),
									table.getSelectedColumn());
							heureDispoDao.updateModuleAvecHeures(heureDispo,
									seance.getCreneau(), true);
							/* On recupere le nombre d'heure dispo */
							heureDispo = heureDispoDao
									.findHeuresSessionModule(
											seance.getIdSession(),
											seance.getIdModule());
							/* On va parcourir les boutons */
							for (JRadioButton jRadioButtonSuppr : tableauBoutton) {
								/* Pour trouver celui du module selectionné */
								if (jRadioButtonSuppr.getText().split(" ")[0]
										.equals(nomModule)) {
									/* Et mettre à jour son nombre d'heure */
									jRadioButtonSuppr.setText(nomModule
											+ " : "
											+ heureDispo
													.getNbreHeuresDisponibles()
											+ "/"
											+ moduleDao.findModuleByNom(
													nomModule)
													.getNbHeuresAnnuelles()
											+ " heures disponibles");
								}
							}

						} catch (Exception exc) {
							exc.getMessage();
							System.out.println("DeleteSeance échoué");
						}
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {

		}

		@Override
		public void keyTyped(KeyEvent arg0) {

		}

	}

	public class ecouteurBoutton implements ActionListener {

		String texteDuBouton = "";
		String nomModule;
		String texteVide = "";
		String contenu = "null";
		Seance.Creneau matin = Creneau.MATIN;
		Seance.Creneau apresMidi = Creneau.APRES_MIDI;
		GregorianCalendar dateSeance;

		Seance seance = new Seance();
		SeanceDao seanceDao = new SeanceDao();
		ModuleDao moduleDao = new ModuleDao();
		HeuresSessionModule heureDispo = new HeuresSessionModule();
		HeuresSessionModuleDao heureDispoDao = new HeuresSessionModuleDao();

		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			 * On récupère les boutons du panel des boutons et on remplit un
			 * tableau avec
			 */
			ArrayList<Integer> listeContrainteFormateur = new ArrayList<Integer>();
			ContrainteFormateurDao contrainteDao = new ContrainteFormateurDao();
			
			tableau = panelBouttons.getComponents();
			tableauBoutton = new ArrayList<JRadioButton>();
			for (int i = 0; i < tableau.length; i++) {
				tableauBoutton.add((JRadioButton) tableau[i]);
			}
			for (JRadioButton jRadioButton : tableauBoutton) {
				/* Si un bouton est selectione */
				if (jRadioButton.isSelected()) {
					texteDuBouton = jRadioButton.getText();
					/* si le texte du bouton selectionné n'est pas Supprimer */
					if (!texteDuBouton.equals("Supprimer")) {
						nomModule = texteDuBouton.split(" ")[0];
					}
					
					/* Si la case selectionné est non null */
					if (nomModule != null) {
						int idFormateur = moduleDao.findFormateurByNomModule(nomModule).getIdFormateur();
						listeContrainteFormateur = contrainteDao.donneJourCreneauIndispo(idFormateur);
						JTableRender graphiqueTableau = new JTableRender();
						graphiqueTableau.setListeContrainteFormateurs(listeContrainteFormateur);
						panelTableau.setjTableRender(graphiqueTableau);
						panelTableau.remplir(1);
						table = panelTableau.getTable();
						scrollPane.setViewportView(table);
						table.addMouseListener(new ecouteur());
						table.addKeyListener(new ecouteur());
						/* On rempli la table de la liste des seances */
						donneesSeance = new DonneesTableauListeSeances();
						donneesSeance
								.remplir(session.getIdSession(), nomModule);
						tableListeSeance = new JTable(donneesSeance);
						scrollPaneTableauSeance
								.setViewportView(tableListeSeance);
					}
				}
			}

		}

	}
}