package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import modele.Seance;
import modele.Session;

public class SeanceDao {

	private static java.sql.PreparedStatement pfindSeanceByIdSession = null;
	/**
	 * Requete pour récupérer séance grâce à l'id d'une session
	 */
	static {
		try {
			pfindSeanceByIdSession = ConnexionBase.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.seance "
									+ "WHERE id_session=?; ");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findSeanceByIdSession échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données une liste de Seance grâce à
	 * l'id de la session
	 * 
	 * @param idSession
	 *            entier
	 * @return ArrayList de Séance
	 */
	public ArrayList<Seance> findSeanceByIdSession(int idSession) {
		ArrayList<Seance> listeSeance = new ArrayList<Seance>();
		try {
			pfindSeanceByIdSession.setInt(1, idSession);
			ResultSet resultat = pfindSeanceByIdSession.executeQuery();

			while (resultat.next()) {
				Seance seance = new Seance();

				seance.setIdModule(resultat.getInt("id_module"));
				seance.setIdSession(idSession);
				seance.setIdFormateur(resultat.getInt("id_formateur"));
				seance.setIdSalle(resultat.getInt("id_salle"));
				seance.setContenu(resultat.getString("contenu"));
				seance.setCreneau(resultat.getInt("id_creneau"));

				// Conversion de la date format SQL en format Gregorion Calendar
				GregorianCalendar jour = new GregorianCalendar();
				jour.setTime(resultat.getDate("jour"));
				seance.setJour(jour);
				
				listeSeance.add(seance);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeSeance;
	}
}
