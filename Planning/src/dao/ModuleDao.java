package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import modele.Module;
import modele.Session;

import com.mysql.jdbc.PreparedStatement;

public class ModuleDao {

	private static java.sql.PreparedStatement pfindModuleAvecHeures = null;
	/**
	 * Requete pour récupérer le nombre d'heures restantes dans la matière
	 */
	static{
		try {
			pfindModuleAvecHeures = ConnexionBase.getConnection().prepareStatement("SELECT * FROM lagarenne2015.heures_session_matiere "
					+ "INNER JOIN module ON heures_session_matiere.id_module = module.id_module "
					+ "INNER JOIN session ON heures_session_matiere.id_session = session.id_session "
					+ "HAVING nbre_heures_disponibles > ? "
					+ "AND YEAR(session.date_debut)=? "
					+ "AND session.nom=?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findMatiereAvecHeures échouée.");
		}
	}
	
	/**
	 * Méthode qui récupère dans la base données les modules qui ont encore des heures d'enseignement.
	 * @param annee
	 * @param session
	 * @return
	 */
	public ArrayList<Module> findMatiereAvecHeures(int annee, String session){
		ArrayList<Module> listeModule = new ArrayList<Module>();
		try {
			pfindModuleAvecHeures.setInt(1, 0);
			pfindModuleAvecHeures.setInt(2, annee);
			pfindModuleAvecHeures.setString(3, session);
			ResultSet resultat = pfindModuleAvecHeures.executeQuery();
			if(resultat != null){
				while (resultat.next()){
					Module unModule = new Module();
					unModule.setId_module(resultat.getInt("id_module"));
					unModule.setNom(resultat.getString("nom"));
					unModule.setObjectif(resultat.getString("objectif"));
					unModule.setContenu(resultat.getString("contenu"));
					unModule.setNb_heures_annuelles(resultat.getInt("nb_heures_annuel"));
					unModule.setPrerequis(resultat.getString("prerequis"));
					listeModule.add(unModule);
				}
				return listeModule;
			}
			else{
				return listeModule = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}return null;
	}
	
	
}
