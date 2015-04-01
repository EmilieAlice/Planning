package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import modele.Seance;
import modele.Session;

public class SeanceDao {

	private static java.sql.PreparedStatement pFindSeanceByIdSession = null;
	/**
	 * Requete pour récupérer une une séance grâce à l'id d'une session
	 */
	static {
		try {
			pFindSeanceByIdSession = ConnexionBase.getConnection()
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
			pFindSeanceByIdSession.setInt(1, idSession);
			ResultSet resultat = pFindSeanceByIdSession.executeQuery();

			while (resultat.next()) {
				Seance seance = new Seance();

				seance.setIdModule(resultat.getInt("id_module"));
				seance.setIdSession(idSession);
				seance.setIdFormateur(resultat.getInt("id_formateur"));
				seance.setIdSalle(resultat.getInt("id_salle"));
				seance.setContenu(resultat.getString("contenu"));
				
				// On determine si le creneau est matin ou apres-midi et on l'insere à la seance
				int idCreneau = resultat.getInt("id_creneau");
				Seance.Creneau creneau = Seance.Creneau.MATIN;
				if (idCreneau == 1){
					creneau = Seance.Creneau.APRES_MIDI;
				}
				seance.setCreneau(creneau);

				// Conversion de la date format SQL en format Gregorian Calendar
				Timestamp dateSql = resultat.getTimestamp("debut");
				GregorianCalendar jour = new GregorianCalendar(dateSql.getYear(), dateSql.getMonth(), dateSql.getDay());
				seance.setJour(jour);

				listeSeance.add(seance);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeSeance;
	}
}
