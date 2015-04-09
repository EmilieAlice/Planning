package swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import modele.Module;
import dao.ModuleDao;

public class TableauPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JTable tableau;

	public TableauPanel() {
		tableau = new JTable(new DonneesTableauDouble());
		tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableau.setCellSelectionEnabled(true);
		tableau.addMouseListener(new ecouteur());
		add(tableau);
	}

	public class ecouteur implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			if (tableau.getSelectedRow() % 2 != 0) {
				if (tableau.getSelectedColumn() % 2 != 0) {
					System.out.println(tableau.getValueAt(
							tableau.getSelectedRow() - 1,
							tableau.getSelectedColumn()));
				} else {
					System.out.println(tableau.getValueAt(
							tableau.getSelectedRow() - 1,
							tableau.getSelectedColumn() - 1));
				}
			}
/*
			String texte = "";
			SelectionMatierePanel panelBouttons = new SelectionMatierePanel();
			Component[] tableauComposants = panelBouttons.getComponents();
			ArrayList<JRadioButton> tableauBoutton = new ArrayList<JRadioButton>();

			for (int i = 0; i < tableauComposants.length; i++) {
				tableauBoutton.add((JRadioButton) tableauComposants[i]);
			}

			for (JRadioButton jRadioButton : tableauBoutton) {
				if (jRadioButton.isSelected()) {
					texte = jRadioButton.getText();
					if (!texte.equals("Supprimer")) {
						texte = texte.split(" ")[0];
						tableau.setValueAt(texte, tableau.getSelectedRow(),
								tableau.getSelectedColumn());
						// on fait un insert dans la table
					} else {
						texte = "";
						if (tableau.getSelectedColumn() % 2 == 0) {
							System.out.println(tableau.getValueAt(
									tableau.getSelectedRow(),
									tableau.getSelectedColumn())
									+ " Après-midi");
						} else {
							System.out.println(tableau.getValueAt(
									tableau.getSelectedRow(),
									tableau.getSelectedColumn())
									+ " Matin");
						}
						tableau.setValueAt(texte, tableau.getSelectedRow(),
								tableau.getSelectedColumn());
						// on fait un delete dans la table en se basant sur
						// l'heure de début
					}

				}
			}
			texte = texte.split(" ")[0];
			if (texte != "") {
				ModuleDao moduleDao = new ModuleDao();
				Module module = moduleDao.findModuleByNom(texte);
			}*/

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
