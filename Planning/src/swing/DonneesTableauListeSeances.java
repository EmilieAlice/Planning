package swing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import dao.ModuleDao;
import dao.SeanceDao;
import modele.Seance;

public class DonneesTableauListeSeances extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	/* Pour formater une date au format désiré */
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");

	private ArrayList<Seance> listeSeance;
	private final String[] entete = {"Date", "Creneau"};
	private Object[][] contenu;
	
	@Override
	public int getColumnCount() {
		return entete.length;
	}

	@Override
	public int getRowCount() {
		return listeSeance.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return contenu[row][col];
	}
	
	public boolean isCellEditable(int row, int col){
		return false;
	}
	
	public String getColumnName(int columnIndex){
		return entete[columnIndex];
	}
	
	public void remplir(int idSession, String nomModule){
		SeanceDao seanceDao = new SeanceDao();
		ModuleDao moduleDao = new ModuleDao();
		
		int idModule = moduleDao.findModuleByNom(nomModule).getIdModule();
		
		listeSeance = new ArrayList<Seance>();
		listeSeance = seanceDao.findSeanceByNomByIdSession(idModule, idSession);
		
		contenu = new Object[listeSeance.size()][entete.length];
		
		for (Seance seance : listeSeance) {			
			int ligne = listeSeance.indexOf(seance);
			
			/* On récupère les infos du jour */
			Date date = seance.getDebut().getTime();
			String affiche = format.format(date);
			
			contenu[ligne][0] = affiche;
			contenu[ligne][1] = seance.getCreneau().toString();
		}
	}
	

}
