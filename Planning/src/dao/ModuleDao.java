package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import modele.Module;
import modele.Session;

import com.mysql.jdbc.PreparedStatement;

public class ModuleDao {

	private static java.sql.PreparedStatement pFindModuleByNom = null;
	/**
	 * Requete pour récupérer un module grâce à son nom
	 */
	static {
		try {
			pFindModuleByNom = DataBase.getConnection().prepareStatement(
					"SELECT * FROM lagarenne2015.module " + "WHERE nom=?; ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findModuleByNom échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Module grâce à son nom
	 * 
	 * @param nom
	 * @return Module
	 */
	public Module findModuleByNom(String nom) {
		Module module = new Module();
		try {
			pFindModuleByNom.setString(1, nom);
			ResultSet resultat = pFindModuleByNom.executeQuery();
			if (resultat.next()) {
				module.setIdModule(resultat.getInt("id_module"));
				module.setNom(resultat.getString("nom"));
				module.setObjectif(resultat.getString("objectif"));
				module.setContenu(resultat.getString("contenu"));
				module.setNbHeuresAnnuelles(resultat.getInt("nb_heures_annuel"));
				module.setPrerequis(resultat.getString("prerequis"));
			} else {
				module = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return module;
	}

	private static java.sql.PreparedStatement pFindModuleAvecHeures = null;
	/**
	 * Requete pour récupérer une collection de modules dont le nombre d'heures
	 * restantes est supérieur à 0
	 */
	static {
		try {
			pFindModuleAvecHeures = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.heures_session_module "
									+ "INNER JOIN module ON heures_session_module.id_module = module.id_module "
									+ "INNER JOIN session ON heures_session_module.id_session = session.id_session "
									+ "HAVING nbre_heures_disponibles > ? "
									+ "AND YEAR(session.date_debut)=? "
									+ "AND session.nom=?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findModuleAvecHeures échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données une collection de modules qui
	 * ont encore des heures disponibles.
	 * 
	 * @param annee
	 * @param session
	 * @return Collection de Module
	 */
	public ArrayList<Module> findModuleAvecHeures(int annee, Session session) {
		ArrayList<Module> listeModule = new ArrayList<Module>();
		try {
			pFindModuleAvecHeures.setInt(1, 0);
			pFindModuleAvecHeures.setInt(2, annee);
			pFindModuleAvecHeures.setString(3, session.getNom());
			ResultSet resultat = pFindModuleAvecHeures.executeQuery();
			if (resultat != null) {
				while (resultat.next()) {
					Module unModule = new Module();
					unModule.setIdModule(resultat.getInt("id_module"));
					unModule.setNom(resultat.getString("nom"));
					unModule.setObjectif(resultat.getString("objectif"));
					unModule.setContenu(resultat.getString("contenu"));
					unModule.setNbHeuresAnnuelles(resultat
							.getInt("nb_heures_annuel"));
					unModule.setPrerequis(resultat.getString("prerequis"));
					listeModule.add(unModule);
				}
			} else {
				listeModule = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeModule;
	}

}
