package swing;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableauPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JTable tableau;
	
	public TableauPanel(){
		tableau = new JTable(new DonneesTableauDouble());
		JScrollPane scrollPane = new JScrollPane(tableau);
		add(tableau);
	}
}
