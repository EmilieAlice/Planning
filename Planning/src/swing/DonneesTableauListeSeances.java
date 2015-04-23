package swing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

import dao.ModuleDao;
import dao.SeanceDao;
import modele.Seance;

/**
 * Classe qui permet de remplir le panneau qui affiche la liste des séances en
 * fonction du module
 * 
 * @author Jerome
 *
 */
public class DonneesTableauListeSeances extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Pour formater une date au format désiré
	 */
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");

	/**
	 * Une liste de séance pour remplir le tableau
	 */
	private ArrayList<Seance> listeSeance;

	/**
	 * Les éléments nécessaires à la construction du tableau
	 */
	private final String[] entete = { "Date", "Creneau","N° de séance" };
	private Object[][] contenu;

	/**
	 * Méhode qui récupère le nombre de colonnes du tableau
	 */
	@Override
	public int getColumnCount() {
		return entete.length;
	}

	/**
	 * Méthode qui récupère le nombre de lignes du tableau
	 */
	@Override
	public int getRowCount() {
		return listeSeance.size();
	}

	/**
	 * Méthode qui récupère la valeur aux coordonnées correspodantes
	 */
	@Override
	public Object getValueAt(int row, int col) {
		return contenu[row][col];
	}

	/**
	 * Méthode qui permet de savoir si on peut éditer une cellule
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	/**
	 * Méthode pour récupérer les en-tetes du tableau
	 */
	public String getColumnName(int columnIndex) {
		return entete[columnIndex];
	}

	/**
	 * Méthode qui remplit les données du données du tableau en fonction du nom
	 * du module et du numéro de la session
	 * 
	 * @param idSession
	 *            un entier
	 * @param nomModule
	 *            une chaine de caractères
	 */
	public void remplir(int idSession, String nomModule) {
		SeanceDao seanceDao = new SeanceDao();
		ModuleDao moduleDao = new ModuleDao();

		int idModule = moduleDao.findModuleByNom(nomModule).getIdModule();

		listeSeance = new ArrayList<Seance>();
		listeSeance = seanceDao.findSeanceByIdModuleByIdSession(idModule, idSession);

		contenu = new Object[listeSeance.size()][entete.length];

		for (Seance seance : listeSeance) {
			int ligne = listeSeance.indexOf(seance);

			/* On récupère les infos du jour */
			Date date = seance.getDebut().getTime();
			String affiche = format.format(date);

			contenu[ligne][0] = affiche;
			contenu[ligne][1] = seance.getCreneau().toString();
			contenu[ligne][2] = ligne + 1;
		}
	}

}
