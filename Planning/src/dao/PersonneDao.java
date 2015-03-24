package dao;

import java.sql.ResultSet;

import modele.Personne;
import modele.Session;

public class PersonneDao {

	private static java.sql.PreparedStatement pfindByNomModule = null;
	/**
	 * Requete pour récupérer une session grâce à son nom
	 */
	static {
		try {
			pfindByNomModule = ConnexionBase
					.getConnection()
					.prepareStatement(
							"SELECT * FROM lagarenne2015.formateur "
									+ "INNER JOIN personne on personne.id_personne = formateur.id_formateur "
									+ "INNER JOIN module on module.id_module = formateur.id_module "
									+ "WHERE module.nom = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findByNomModule échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Personne qui est un
	 * formateur
	 * 
	 * @param nomModule
	 * @return personne
	 */
	public Personne findByNomModule(String nomModule) {
		Personne personne = new Personne();
		try {
			pfindByNomModule.setString(1, nomModule);
			ResultSet resultat = pfindByNomModule.executeQuery();
			if (resultat.next()) {
				personne.setIdPersonne(resultat.getInt("id_personne"));
				personne.setCivilite(resultat.getString("civilite"));
				personne.setPrenom(resultat.getString("prenom"));
				personne.setNom(resultat.getString("nom"));
				personne.setAdresse(resultat.getString("adresse"));
				personne.setCodePostal(resultat.getString("code_postal"));
				personne.setVille(resultat.getString("ville"));
				personne.setTelephone(resultat.getString("telephone"));
				personne.setTelephone2(resultat.getString("telephone"));
				personne.setEmail(resultat.getString("email"));
				return personne;
			} else {
				return personne = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
