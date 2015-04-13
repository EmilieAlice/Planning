package swing;

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

	public TableauPanel(int idSession) {

		this.setBounds(6, 6, 930, 675);

		table = new JTable(new DonneesTableauDouble(idSession));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);

		table.setRowHeight(25);
		table.setRowMargin(5);
		this.add(table);
	}

}
