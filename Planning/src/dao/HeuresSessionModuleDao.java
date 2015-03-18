package dao;

import java.sql.ResultSet;

import modele.HeuresSessionModule;
import modele.Module;
import modele.Session;

public class HeuresSessionModuleDao {

	private static java.sql.PreparedStatement pfindHeuresSessionModule = null;
	/**
	 * Requete pour récupérer le nombre d'heures restantes dans la matière
	 */
	static {
		try {
			pfindHeuresSessionModule = ConnexionBase
					.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.heures_session_matiere "
									+ "INNER JOIN module ON heures_session_matiere.id_module = module.id_module "
									+ "INNER JOIN session ON heures_session_matiere.id_session = session.id_session "
									+ "HAVING id_session = ? "
									+ "AND id_module=?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findMatiereAvecHeures échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet HeuresSessionModule
	 * 
	 * @param annee
	 * @param session
	 * @return
	 */
	public HeuresSessionModule findHeuresSessionModule(Module module,
			Session session) {
		HeuresSessionModule heuresSessionModule = new HeuresSessionModule();
		try {
			pfindHeuresSessionModule.setInt(1, session.getId_session());
			pfindHeuresSessionModule.setInt(2, module.getId_module());
			ResultSet resultat = pfindHeuresSessionModule.executeQuery();
			if (resultat.next()) {
				heuresSessionModule.setId_module(resultat.getInt("id_module"));
				heuresSessionModule
						.setId_session(resultat.getInt("id_session"));
				heuresSessionModule.setNbreHeuresDisponibles(resultat
						.getInt("nbre_heures_disponibles"));
				return heuresSessionModule;
			} else {
				return heuresSessionModule = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static java.sql.PreparedStatement pupdateModuleAvecHeures = null;
	/**
	 * Requete pour mettre le nombre d'heures restantes dans le module
	 */
	static {
		try {
			pupdateModuleAvecHeures = ConnexionBase
					.getConnection()
					.prepareStatement(
							"UPDATE `lagarenne2015`.`heures_session_matiere`"
									+ "SET `nbre_heures_disponibles` = ?"
									+ "WHERE `id_session` = ? AND `id_module` = ?; ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete updateMatiereAvecHeures échouée.");
		}
	}

	/**
	 * Méthode qui met à jour dans la base données le nombre d'heures disponible
	 * pour un module pour le module sélectionné
	 * 
	 * @param annee
	 * @param session
	 * @return
	 */
	public boolean updateModuleAvecHeures(HeuresSessionModule heureSessionModule) {
		Boolean etat = new Boolean(false);
		try {
			pupdateModuleAvecHeures.setInt(1,
					heureSessionModule.getNbreHeuresDisponibles());
			pupdateModuleAvecHeures.setInt(2,
					heureSessionModule.getId_session());
			pupdateModuleAvecHeures
					.setInt(3, heureSessionModule.getId_module());
			int resultat = pupdateModuleAvecHeures.executeUpdate();
			if (resultat != 0)
				etat = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etat;
	}

}
