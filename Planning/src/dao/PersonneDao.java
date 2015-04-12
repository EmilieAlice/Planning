package dao;

import java.sql.ResultSet;

import modele.Personne;

public class PersonneDao {

	private static java.sql.PreparedStatement pFindPersonneByNomModule = null;
	/**
	 * Requete pour récupérer une personne grâce à son nom de module
	 */
	static {
		try {
			pFindPersonneByNomModule = DataBase.getConnection().prepareStatement(
					"SELECT * FROM lagarenne2015.formateur "
							+ "INNER JOIN personne on personne.id_personne = formateur.id_formateur "
							+ "INNER JOIN module on module.id_module = formateur.id_module "
							+ "WHERE module.nom = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findPersonneByNomModule échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet Personne qui est un
	 * formateur grace au module qu'elle enseigne
	 * 
	 * @param nomModule
	 * @return
	 */
	public Personne findPersonneByNomModule(String nomModule) {
		Personne personne = new Personne();
		try {
			pFindPersonneByNomModule.setString(1, nomModule);
			ResultSet resultat = pFindPersonneByNomModule.executeQuery();
			if (resultat.next()) {
				personne.setIdFormateur(resultat.getInt("id_personne"));
				personne.setCivilite(resultat.getString("civilite"));
				personne.setPrenom(resultat.getString("prenom"));
				personne.setNom(resultat.getString("nom"));
				personne.setAdresse(resultat.getString("adresse"));
				personne.setCodePostal(resultat.getString("code_postal"));
				personne.setVille(resultat.getString("ville"));
				personne.setTelephone(resultat.getString("telephone"));
				personne.setTelephone2(resultat.getString("telephone"));
				personne.setEmail(resultat.getString("email"));
			} else {
				personne = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personne;
	}

}
