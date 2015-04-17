package dao;

import java.sql.ResultSet;

import modele.HeuresSessionModule;
import modele.Seance;
import modele.Seance.Creneau;

public class HeuresSessionModuleDao {

	private static java.sql.PreparedStatement pfindHeuresSessionModule = null;
	/**
	 * Requete pour récupérer le nombre d'heures restantes dans la matière
	 */
	static {
		try {
			pfindHeuresSessionModule = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.heures_session_module "
									+ "INNER JOIN module ON heures_session_module.id_module = module.id_module "
									+ "INNER JOIN session ON heures_session_module.id_session = session.id_session "
									+ "HAVING heures_session_module.id_session = ? "
									+ "AND heures_session_module.id_module=?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findHeuresSessionModule échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet HeuresSessionModule
	 * 
	 * @param idSession
	 * @param idModule
	 * @return null
	 */
	public HeuresSessionModule findHeuresSessionModule(int idSession,
			int idModule) {
		HeuresSessionModule heuresSessionModule = new HeuresSessionModule();
		try {
			pfindHeuresSessionModule.setInt(1, idSession);
			pfindHeuresSessionModule.setInt(2, idModule);
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

	private static java.sql.PreparedStatement pUpdateModuleAvecHeures = null;
	/**
	 * Requete pour mettre à jour le nombre d'heures restantes dans le module
	 */
	static {
		try {
			pUpdateModuleAvecHeures = DataBase
					.getConnection()
					.prepareStatement(
							"UPDATE lagarenne2015.heures_session_module "
									+ "SET nbre_heures_disponibles = ? "
									+ "WHERE id_session = ? AND id_module = ?; ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete updateModuleAvecHeures échouée.");
		}
	}

	/**
	 * Méthode qui met à jour dans la base données le nombre d'heures disponible
	 * pour un module pour le module sélectionné
	 * 
	 * @param heureSessionModule
	 * @param creneau
	 * @param action
	 * @return boolean
	 */
	public boolean updateModuleAvecHeures(
			HeuresSessionModule heureSessionModule, Creneau creneau,
			Boolean action) {
		Boolean etat = new Boolean(false);
		int heuresRetirees = 0;
		Seance.Creneau matin = Creneau.MATIN;
		try {
			if (creneau == matin) {
				heuresRetirees = 4;
			} else {
				heuresRetirees = 3;
			}
			if (action) {
				pUpdateModuleAvecHeures
						.setInt(1,
								(heureSessionModule.getNbreHeuresDisponibles() + heuresRetirees));
			} else {
				pUpdateModuleAvecHeures
						.setInt(1,
								(heureSessionModule.getNbreHeuresDisponibles() - heuresRetirees));
			}
			pUpdateModuleAvecHeures.setInt(2,
					heureSessionModule.getId_session());
			pUpdateModuleAvecHeures
					.setInt(3, heureSessionModule.getId_module());
			int resultat = pUpdateModuleAvecHeures.executeUpdate();
			if (resultat != 0)
				etat = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etat;
	}

}
