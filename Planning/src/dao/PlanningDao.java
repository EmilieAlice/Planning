package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.GregorianCalendar;

import modele.Seance;
import modele.Session;

public class PlanningDao {

	private static java.sql.PreparedStatement pfindByIdSession = null;
	/**
	 * Requete pour récupérer une session grâce à son id
	 */
	static {
		try {
			pfindByIdSession = ConnexionBase.getConnection().prepareStatement(
					"SELECT * FROM lagarenne2015.session "
							+ "WHERE session.id_session = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findByIdSession échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Personne qui est un
	 * formateur
	 * 
	 * @param nomModule
	 * @return personne
	 */
	public Session findByIdSession(int idSession) {
		Session session = new Session();
		try {
			pfindByIdSession.setInt(1, idSession);
			ResultSet resultat = pfindByIdSession.executeQuery();
			if (resultat.next()) {
				// Conversion de la date SQL en date Gregorian
				GregorianCalendar dateDebut = new GregorianCalendar();
				dateDebut.setTime(resultat.getDate("date_debut"));

				GregorianCalendar dateFin = new GregorianCalendar();
				dateDebut.setTime(resultat.getDate("date_fin"));

				session.setIdSession(resultat.getInt("id_session"));
				session.setNom(resultat.getString("nom"));
				session.setDateDebut(dateDebut);
				session.setDateFin(dateFin);
				session.setIdFormation(resultat.getInt("id_formation"));
			} else {
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	private static java.sql.PreparedStatement pInsertSeance = null;
	/**
	 * Requete pour récupérer une session grâce à son id
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
	 * Méthode qui récupère dans la base données un objet Personne qui est un
	 * formateur
	 * 
	 * @param nomModule
	 * @return personne
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
			else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etat;
	}

	private static java.sql.PreparedStatement pDeleteSeance = null;
	/**
	 * Requete pour récupérer une session grâce à son id
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
	 * Méthode qui récupère dans la base données un objet Personne qui est un
	 * formateur
	 * 
	 * @param nomModule
	 * @return personne
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
