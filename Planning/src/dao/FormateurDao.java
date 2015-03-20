package dao;

import java.sql.ResultSet;

import modele.Formateur;
import modele.Personne;

public class FormateurDao {

	private static java.sql.PreparedStatement pfindByIdPersonne = null;
	/**
	 * Requete pour récupérer un formateur grâce à l'id de la personne
	 */
	static {
		try {
			pfindByIdPersonne = ConnexionBase
					.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.formateur "
									+ "INNER JOIN personne on personne.id_personne = formateur.id_personne "
									+ "WHERE formateur.id_personne = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findByIdPersonne échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Formateur
	 * 
	 * @param un
	 *            objet Personne
	 * @return un objet Formateur
	 */
	public Formateur findByNomModule(Personne personne) {
		Formateur formateur = new Formateur();
		try {
			pfindByIdPersonne.setInt(1, personne.getIdPersonne());
			ResultSet resultat = pfindByIdPersonne.executeQuery();
			if (resultat.next()) {
				formateur.setIdPersonne(resultat.getInt("id_personne"));
				formateur.setDateEntree(resultat.getDate("date_entree"));
				formateur.setIdModule(resultat.getInt("id_module"));
				return formateur;
			} else {
				return formateur = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
