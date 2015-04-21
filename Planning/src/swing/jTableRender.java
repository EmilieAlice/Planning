package swing;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Définir l'affichage dans un JTable
 */
public class jTableRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		/**
		 * Fixer la couleur de fond de la première colonne en jaune
		 */
		if (column == 0 || row %2 == 0) {
			Color color = new Color(219, 214, 214);
			component.setBackground(color);
		} else {
			if(! isSelected){
			Color color = Color.white;
			component.setBackground(color);
			}
		}
		/**
		 * Colorier les cellules en orange si la cellule est vide
		 */
		Object o = table.getValueAt(row, 3);
		if (o != null && component instanceof JLabel) {
			JLabel label = (JLabel) component;
			label.setHorizontalAlignment(CENTER);
			label.setVerticalAlignment(CENTER);

		}
		return component;
	}
}