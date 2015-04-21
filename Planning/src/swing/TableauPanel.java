package swing;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class TableauPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * Constructeur du panel du tableau qui ajoute un tableau qui contient les
	 * données de la classe DonneesTableauDouble. Ajout aussi de la méthode pour
	 * sélectionner une seule case
	 * 
	 * @param idSession
	 */
	public TableauPanel(int idSession) {

		this.setBounds(6, 6, 930, 675);

		table = new JTable(new DonneesTableauDouble(idSession));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setRowHeight(25);
		table.setRowMargin(5);
		table.setDefaultRenderer(Object.class, new JTableRender());
		this.add(table);
	}

}
