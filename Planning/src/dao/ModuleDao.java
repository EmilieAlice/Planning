package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import modele.Module;
import modele.Session;

import com.mysql.jdbc.PreparedStatement;

public class ModuleDao {

	private static java.sql.PreparedStatement pfindModule = null;
	/**
	 * Requete pour récupérer un module grâce à son nom
	 */
	static {
		try {
			pfindModule = ConnexionBase.getConnection().prepareStatement(
					"SELECT * FROM lagarenne2015.module " + "WHERE nom=?; ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findSession échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Module
	 * 
	 * @param nom
	 * @return Module
	 */
	public Module findModule(String nom) {
		Module module = new Module();
		try {
			pfindModule.setString(1, nom);
			ResultSet resultat = pfindModule.executeQuery();
			if (resultat.next()) {
				module.setId_module(resultat.getInt("id_module"));
				module.setNom(resultat.getString("nom"));
				module.setObjectif(resultat.getString("objectif"));
				module.setContenu(resultat.getString("contenu"));
				module.setNb_heures_annuelles(resultat
						.getInt("nb_heures_annuel"));
				module.setPrerequis(resultat.getString("prerequis"));
				return module;
			} else {
				return module = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static java.sql.PreparedStatement pfindModuleAvecHeures = null;
	/**
	 * Requete pour récupérer une collection de modules dont le nombre d'heures
	 * restantes est supérieur à 0
	 */
	static {
		try {
			pfindModuleAvecHeures = ConnexionBase
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
			System.out.println("Requete findMatiereAvecHeures échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données une collection de modules qui
	 * ont encore des heures d'enseignement.
	 * 
	 * @param annee
	 * @param session
	 * @return Collection de Module
	 */
	public ArrayList<Module> findModuleAvecHeures(int annee, String session) {
		ArrayList<Module> listeModule = new ArrayList<Module>();
		try {
			pfindModuleAvecHeures.setInt(1, 0);
			pfindModuleAvecHeures.setInt(2, annee);
			pfindModuleAvecHeures.setString(3, session);
			ResultSet resultat = pfindModuleAvecHeures.executeQuery();
			if (resultat != null) {
				while (resultat.next()) {
					Module unModule = new Module();
					unModule.setId_module(resultat.getInt("id_module"));
					unModule.setNom(resultat.getString("nom"));
					unModule.setObjectif(resultat.getString("objectif"));
					unModule.setContenu(resultat.getString("contenu"));
					unModule.setNb_heures_annuelles(resultat
							.getInt("nb_heures_annuel"));
					unModule.setPrerequis(resultat.getString("prerequis"));
					listeModule.add(unModule);
				}
				return listeModule;
			} else {
				return listeModule = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
