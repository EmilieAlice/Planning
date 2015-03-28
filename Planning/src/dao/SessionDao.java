package dao;

import java.sql.ResultSet;
import java.util.GregorianCalendar;

import modele.HeuresSessionModule;
import modele.Module;
import modele.Session;

public class SessionDao {

	private static java.sql.PreparedStatement pfindSessionByNom = null;
	/**
	 * Requete pour récupérer une session grâce à son nom
	 */
	static {
		try {
			pfindSessionByNom = ConnexionBase.getConnection().prepareStatement(
					"SELECT * FROM lagarenne2015.session " + "WHERE nom=?; ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findSession échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Session grâce au nom
	 * de la session
	 * 
	 * @param nomSession
	 * @return session
	 */
	public Session findSessionByNom(String nomSession) {
		Session session = new Session();
		try {
			pfindSessionByNom.setString(1, nomSession);
			ResultSet resultat = pfindSessionByNom.executeQuery();

			if (resultat.next()) {
				session.setIdSession(resultat.getInt("id_session"));
				session.setNom(resultat.getString("nom"));

				// Conversion de la date de début SQL en GregorianCalendar
				GregorianCalendar dateDebut = new GregorianCalendar();
				dateDebut.setTime(resultat.getDate("date_debut"));
				session.setDateDebut(dateDebut);

				// Conversion de la date de fin SQL en GregorianCalendar
				GregorianCalendar dateFin = new GregorianCalendar();
				dateFin.setTime(resultat.getDate("date_fin"));
				session.setDateFin(dateFin);

				session.setDescription(resultat.getString("description"));
				session.setIdFormation(resultat.getInt("id_formation"));

				// Conversion de la date de début d'inscription SQL en
				// GregorianCalendar
				GregorianCalendar dateDebutInscription = new GregorianCalendar();
				dateDebutInscription.setTime(resultat
						.getDate("date_debut_inscription"));
				session.setDateDebutInscription(dateDebutInscription);

				// Conversion de la date de fin d'inscription SQL en
				// GregorianCalendar
				GregorianCalendar dateFinInscription = new GregorianCalendar();
				dateFinInscription.setTime(resultat
						.getDate("date_fin_inscription"));
				session.setDateFinInscription(dateFinInscription);

			} else {
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

	private static java.sql.PreparedStatement pfindSessionById = null;
	/**
	 * Requete pour récupérer une session grâce à son id
	 */
	static {
		try {
			pfindSessionById = ConnexionBase.getConnection().prepareStatement(
					"SELECT * FROM lagarenne2015.session "
							+ "WHERE id_session=?; ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findSession échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Session grâce à son id
	 * 
	 * @param un entier idSession
	 * @return une session
	 */
	public Session findSessionById(int idSession) {
		Session session = new Session();
		try {
			pfindSessionById.setInt(1, idSession);
			ResultSet resultat = pfindSessionById.executeQuery();

			if (resultat.next()) {
				session.setIdSession(resultat.getInt("id_session"));
				session.setNom(resultat.getString("nom"));

				// Conversion de la date de début SQL en GregorianCalendar
				GregorianCalendar dateDebut = new GregorianCalendar();
				dateDebut.setTime(resultat.getDate("date_debut"));
				session.setDateDebut(dateDebut);

				// Conversion de la date de fin SQL en GregorianCalendar
				GregorianCalendar dateFin = new GregorianCalendar();
				dateFin.setTime(resultat.getDate("date_fin"));
				session.setDateFin(dateFin);

				session.setDescription(resultat.getString("description"));
				session.setIdFormation(resultat.getInt("id_formation"));

				// Conversion de la date de début d'inscription SQL en
				// GregorianCalendar
				GregorianCalendar dateDebutInscription = new GregorianCalendar();
				dateDebutInscription.setTime(resultat
						.getDate("date_debut_inscription"));
				session.setDateDebutInscription(dateDebutInscription);

				// Conversion de la date de fin d'inscription SQL en
				// GregorianCalendar
				GregorianCalendar dateFinInscription = new GregorianCalendar();
				dateFinInscription.setTime(resultat
						.getDate("date_fin_inscription"));
				session.setDateFinInscription(dateFinInscription);

			} else {
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}

}
