package test;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TableModelTest implements TableModel {

	/*String[] columnNames = {"LUNDI",
            "MARDI",
            "MERCREDI",
            "JEUDI",
            "VENDREDI",
            "SAMEDI",
            "DIMANCHE"};
	
	Object[][] data = {
		    {"01/01", "02/01","03/01","04/01","05/01","06/01","07/01"},
		};*/
	
	String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};
	
	Object[][] data = {
		    {"Kathy", "Smith",
		     "Snowboarding", new Integer(5), new Boolean(false)},
		    {"John", "Doe",
		     "Rowing", new Integer(3), new Boolean(true)},
		    {"Sue", "Black",
		     "Knitting", new Integer(2), new Boolean(false)},
		    {"Jane", "White",
		     "Speed reading", new Integer(20), new Boolean(true)},
		    {"Joe", "Brown",
		     "Pool", new Integer(10), new Boolean(false)}
		};
	
	@Override
	public int getRowCount() {
		return columnNames.length;
	}

	@Override
	public int getColumnCount() {
		return data.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}
