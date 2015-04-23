package dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import modele.Seance;

public class SeanceDao {

	private static java.sql.PreparedStatement pFindSeanceByIdSession = null;
	/**
	 * Requete pour récupérer les séances d'une session grâce à son id
	 */
	static {
		try {
			pFindSeanceByIdSession = DataBase.getConnection().prepareStatement(
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
	 * @param idSession un entier
	 * @return collection de séances
	 */
	public ArrayList<Seance> findSeanceByIdSession(int idSession) {
		ArrayList<Seance> listeSeance = new ArrayList<Seance>();
		try {
			pFindSeanceByIdSession.setInt(1, idSession);
			ResultSet resultat = pFindSeanceByIdSession.executeQuery();

			while (resultat.next()) {
				// On determine si le creneau est matin ou apres-midi pour
				// l'inserer à la seance
				int idCreneau = resultat.getInt("id_creneau");
				Seance.Creneau creneau = Seance.Creneau.MATIN;
				if (idCreneau == 2) {
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
	 * @param seance un objet séance
	 * @return booleen
	 */
	public Boolean insertSeance(Seance seance) {
		Boolean etat = new Boolean(false);
		try {
			// Conversion de la date Gregorian en date SQL
			Timestamp dateSQLDebut = new Timestamp(seance.getDebut()
					.getTimeInMillis());

			// On ajoute l'heure d'une seance pour avoir la date de fin
			long journee = seance.getDebut().getTimeInMillis();
			long millis = (3600000);
			long heureCreneau = 0;
			if (seance.getCreneau().equals(Seance.Creneau.APRES_MIDI)) {
				heureCreneau = 3 * millis;
			} else {
				heureCreneau = 4 * millis;
			}
			journee = journee + heureCreneau;
			Timestamp dateSQLFin = new Timestamp(journee);

			// On insere le creneau grace à l'enum
			int idCreneau = 1;
			if (seance.getCreneau().equals(Seance.Creneau.APRES_MIDI)) {
				idCreneau = 2;
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
			pDeleteSeance = DataBase
					.getConnection()
					.prepareStatement(
							"DELETE FROM lagarenne2015.seance WHERE debut=? AND id_session=?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete deleteSeance échouée.");
		}
	}

	/**
	 * Methode qui supprime une seance grace à la date et heure de debut et l'id
	 * de la session
	 * 
	 * @param debut
	 * @param idSession
	 * @return booléen
	 */
	public Boolean deleteSeance(GregorianCalendar debut, int idSession) {
		Boolean etat = new Boolean(false);
		try {
			Timestamp dateSQL = new Timestamp(debut.getTimeInMillis());

			pDeleteSeance.setTimestamp(1, dateSQL);
			pDeleteSeance.setInt(2, idSession);
			int resultat = pDeleteSeance.executeUpdate();
			if (resultat != 0)
				etat = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etat;
	}

	private static java.sql.PreparedStatement pUpdateSeance = null;
	/**
	 * Requete pour mettre à jour une séance
	 */
	static {
		try {
			pUpdateSeance = DataBase
					.getConnection()
					.prepareStatement(
							"UPDATE lagarenne2015.seance SET "
									+ "id_module = ?, id_formateur = ?, id_salle = ?, contenu = ? "
									+ "WHERE id_session = ? AND debut = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete updateSeance échouée.");
		}
	}

	/**
	 * Methode qui met à jour les elements d'une seance d'une session et d'une
	 * date et heure de debut donnée
	 * 
	 * @param idModule
	 * @param idFormateur
	 * @param idSalle
	 * @param contenu
	 * @param idSession
	 * @param debut
	 * @return booléen
	 */
	public Boolean updateSeance(int idModule, int idFormateur, int idSalle,
			String contenu, int idSession, GregorianCalendar debut) {
		Boolean etat = new Boolean(false);
		try {
			// Conversion de la date Gregorian en date SQL
			Timestamp dateSQLDebut = new Timestamp(debut.getTimeInMillis());

			pUpdateSeance.setInt(1, idModule);
			pUpdateSeance.setInt(2, idFormateur);
			pUpdateSeance.setInt(3, idSalle);
			pUpdateSeance.setString(4, contenu);
			pUpdateSeance.setInt(5, idSession);
			pUpdateSeance.setTimestamp(6, dateSQLDebut);

			int resultat = pUpdateSeance.executeUpdate();
			if (resultat != 0)
				etat = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return etat;
	}
	
	private static java.sql.PreparedStatement pFindSeanceByNomByIdSession = null;
	/**
	 * Requete pour récupérer un module grâce à son nom
	 */
	static {
		try {
			pFindSeanceByNomByIdSession = DataBase
					.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.seance "
							+ "WHERE seance.id_module = ? "
							+ "AND seance.id_session = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findSeanceByNomByIdSession échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données une liste d'objet Seance
	 * grace a l'id module et l'id session
	 * 
	 * @param idModule
	 * @param idSession
	 * @return
	 */
	public ArrayList<Seance> findSeanceByIdModuleByIdSession(int idModule, int idSession) {
		ArrayList<Seance> listeSeance = new ArrayList<Seance>();
		try {
			pFindSeanceByNomByIdSession.setInt(1, idModule);
			pFindSeanceByNomByIdSession.setInt(2, idSession);
			ResultSet resultat = pFindSeanceByNomByIdSession.executeQuery();
			while (resultat.next()) {
				// Conversion de la date format SQL en format Gregorian Calendar
				Timestamp dateSql = resultat.getTimestamp("debut");
				GregorianCalendar jour = new GregorianCalendar();
				jour.setTime(dateSql);
				Timestamp dateSqlFin = resultat.getTimestamp("fin");
				GregorianCalendar fin = new GregorianCalendar();
				fin.setTime(dateSqlFin);
				
				int idCreneau = resultat.getInt("id_creneau");
				Seance.Creneau creneau = Seance.Creneau.APRES_MIDI;
				if (idCreneau == 1) {
					creneau = Seance.Creneau.MATIN;
				} 

				Seance seance = new Seance();
				seance.setDebut(jour);
				seance.setCreneau(creneau);
				seance.setIdModule(resultat.getInt("id_module"));
				seance.setIdSession(resultat.getInt("id_session"));
				seance.setIdFormateur(resultat.getInt("id_formateur"));
				seance.setFin(fin);
				seance.setIdSalle(resultat.getInt("id_salle"));
				listeSeance.add(seance);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeSeance;
	}


}
