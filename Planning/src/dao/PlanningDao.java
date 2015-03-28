package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.GregorianCalendar;

import modele.Planning;
import modele.Seance;
import modele.Session;

public class PlanningDao {

	private static java.sql.PreparedStatement pFindPlanningByIdSession = null;
	/**
	 * Requete pour récupérer un planning correspondant à l'id de la session
	 */
	static {
		try {
			pFindPlanningByIdSession = ConnexionBase.getConnection()
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

	private static java.sql.PreparedStatement pInsertSeance = null;
	/**
	 * Requete pour insérer une séance
	 */
	static {
		try {
			pInsertSeance = ConnexionBase
					.getConnection()
					.prepareStatement(
							"INSERT INTO lagarenne2015.seance (id_module, id_session, id_personne, debut, fin, id_salle,contenu)"
									+ "VALUES(?,?,?,?,?,?,?·);");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete insertSeance échouée.");
		}
	}

	/**
	 * Méthode qui insère une séance dans la base de données
	 * 
	 * @param seance
	 *            (un objet Seance)
	 * @return un booléen
	 */
	public Boolean insertSeance(Seance seance) {
		Boolean etat = new Boolean(false);
		try {
			// Conversion de la date Gregorian en date SQL
			Date dateSQL = new Date(seance.getJour().getTimeInMillis());

			pInsertSeance.setInt(1, seance.getIdModule());
			pInsertSeance.setInt(2, seance.getIdSession());
			pInsertSeance.setInt(3, seance.getIdFormateur());
			pInsertSeance.setDate(4, dateSQL);
			pInsertSeance.setInt(5, seance.getIdModule());
			pInsertSeance.setInt(6, seance.getIdModule());
			pInsertSeance.setInt(7, seance.getIdModule());

			int resultat = pInsertSeance.executeUpdate();
			if (resultat != 0)
				etat = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etat;
	}

	private static java.sql.PreparedStatement pDeleteSeance = null;
	/**
	 * Requete pour supprimer une séance
	 */
	static {
		try {
			pDeleteSeance = ConnexionBase.getConnection().prepareStatement(
					"DELETE FROM lagarenne2015.seance WHERE debut=?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete deleteSeance échouée.");
		}
	}

	/**
	 * Méthode qui supprime une séance
	 * 
	 * @param seance
	 *            (un objet Seance)
	 * @return booléen
	 */
	public Boolean deleteSeance(Seance seance) {
		Boolean etat = new Boolean(false);
		try {
			GregorianCalendar dateDebut = seance.getJour();
			Date dateSQL = new Date(dateDebut.getTimeInMillis());

			pDeleteSeance.setDate(1, dateSQL);
			int resultat = pDeleteSeance.executeUpdate();
			if (resultat != 0)
				etat = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etat;
	}

}
