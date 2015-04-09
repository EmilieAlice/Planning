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
			pFindSeanceByIdSession = DataBase.getConnection()
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
				// On determine si le creneau est matin ou apres-midi pour l'inserer à la seance
				int idCreneau = resultat.getInt("id_creneau");
				Seance.Creneau creneau = Seance.Creneau.MATIN;
				if (idCreneau == 1){
					creneau = Seance.Creneau.APRES_MIDI;
				}
				
				// Conversion de la date format SQL en format Gregorian Calendar
				Timestamp dateSql = resultat.getTimestamp("debut");
				GregorianCalendar jour = new GregorianCalendar();
				jour.setTime(dateSql);
				
				Seance seance = new Seance();

				seance.setIdModule(resultat.getInt("id_module"));
				seance.setIdSession(idSession);
				seance.setIdFormateur(resultat.getInt("id_formateur"));
				seance.setDebut(jour);
				seance.setCreneau(creneau);
				seance.setIdSalle(resultat.getInt("id_salle"));
				seance.setContenu(resultat.getString("contenu"));

				listeSeance.add(seance);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeSeance;
	}

	

	private static java.sql.PreparedStatement pInsertSeance = null;
	/**
	 * Requete pour insérer une séance
	 */
	static {
		try {
			pInsertSeance = DataBase
					.getConnection()
					.prepareStatement(
							"INSERT INTO lagarenne2015.seance (id_module, id_session, "
							+ "id_formateur, debut, fin, id_creneau, id_salle, contenu) "
							+ "VALUES(?,?,?,?,?,?,?,?);");
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
			Timestamp dateSQLDebut = new Timestamp(seance.getDebut().getTimeInMillis());
			
			// On ajoute l'heure d'une seance pour avoir la date de fin
			long journee = seance.getDebut().getTimeInMillis();
			long millis = (3600000);
			long heureCreneau = 0;
			if (seance.getCreneau().equals(Seance.Creneau.APRES_MIDI)){
				heureCreneau = 3 * millis;
			}
			else {
				heureCreneau = 4 * millis;
			}
			journee = journee + heureCreneau;
			Timestamp dateSQLFin = new Timestamp(journee);
			
			// On insere le creneau grace à l'enum
			int idCreneau = 0;
			if (seance.getCreneau().equals(Seance.Creneau.APRES_MIDI)){
				idCreneau = 1;
				}
			
			pInsertSeance.setInt(1, seance.getIdModule());
			pInsertSeance.setInt(2, seance.getIdSession());
			pInsertSeance.setInt(3, seance.getIdFormateur());
			pInsertSeance.setTimestamp(4, dateSQLDebut);
			pInsertSeance.setTimestamp(5, dateSQLFin);
			pInsertSeance.setInt(6, idCreneau);
			pInsertSeance.setInt(7, seance.getIdSalle());
			pInsertSeance.setString(8, seance.getContenu());

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
			pDeleteSeance = DataBase.getConnection().prepareStatement(
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
			GregorianCalendar dateDebut = seance.getDebut();
			Timestamp dateSQL = new Timestamp(dateDebut.getTimeInMillis());

			pDeleteSeance.setTimestamp(1, dateSQL);
			int resultat = pDeleteSeance.executeUpdate();
			if (resultat != 0)
				etat = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etat;
	}
}
