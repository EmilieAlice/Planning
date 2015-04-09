package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import modele.Planning;
import modele.Seance;
import modele.Session;
import modele.Seance.Creneau;

public class PlanningDao {

	private static java.sql.PreparedStatement pFindPlanningByIdSession = null;
	/**
	 * Requete pour récupérer un planning correspondant à l'id de la session
	 */
	static {
		try {
			pFindPlanningByIdSession = DataBase.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.session "
									+ "WHERE session.id_session = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findByIdSession échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Planning avec l'id de
	 * la session correspodant
	 * 
	 * @param idSession
	 *            entier
	 * @return planning un objet Planning
	 */
	public Planning findByIdSession(int idSession) {
		Planning planning = new Planning();
		try {
			pFindPlanningByIdSession.setInt(1, idSession);
			ResultSet resultat = pFindPlanningByIdSession.executeQuery();
			if (resultat.next()) {
				/*
				 * // Conversion de la date SQL en date Gregorian
				 * GregorianCalendar dateDebut = new GregorianCalendar();
				 * dateDebut.setTime(resultat.getDate("date_debut"));
				 * 
				 * GregorianCalendar dateFin = new GregorianCalendar();
				 * dateDebut.setTime(resultat.getDate("date_fin"));
				 */

				// Set du planning
				planning.setIdSession(resultat.getInt("id_session"));
			} else {
				planning = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planning;
	}


}
