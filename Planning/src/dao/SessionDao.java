package dao;

import java.sql.ResultSet;

import modele.HeuresSessionModule;
import modele.Module;
import modele.Session;

public class SessionDao {

	private static java.sql.PreparedStatement pfindSession = null;
	/**
	 * Requete pour récupérer une session grâce à son nom
	 */
	static {
		try {
			pfindSession = ConnexionBase.getConnection().prepareStatement(
					"SELECT * FROM lagarenne2015.session " + "WHERE nom=?; ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findSession échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Session
	 * 
	 * @param nom
	 * @return session
	 */
	public Session findSession(String nom) {
		Session session = new Session();
		try {
			pfindSession.setString(1, nom);
			ResultSet resultat = pfindSession.executeQuery();
			if (resultat.next()) {
				session.setId_session(resultat.getInt("id_session"));
				session.setNom(resultat.getString("nom"));
				session.setDateDebut(resultat.getDate("date_debut"));
				session.setDateFin(resultat.getDate("date_fin"));
				session.setDescription(resultat.getString("description"));
				session.setId_formation(resultat.getInt("id_formation"));
				session.setDateDebutInscription(resultat
						.getDate("date_debut_inscription"));
				session.setDateFinInscription(resultat
						.getDate("date_fin_inscription"));
				return session;
			} else {
				return session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
