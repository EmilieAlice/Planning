package dao;

import java.sql.ResultSet;

import modele.ContrainteFormateur;

public class ContrainteFormateurDao {

	private static java.sql.PreparedStatement pDonneJourCreneauIndispo = null;
	/**
	 * Requete pour récuperer les contraintes d'un formateur
	 */
	static {
		try {
			pDonneJourCreneauIndispo = DataBase.getConnection().prepareStatement(
					"SELECT contrainte_formateur.id_jour, nom_jour, contrainte_formateur.id_creneau, nom_creneau "
							+ "FROM lagarenne2015.contrainte_formateur "
							+ "inner join jour on jour.id_jour = contrainte_formateur.id_jour "
							+ "inner join creneau on creneau.id_creneau = contrainte_formateur.id_creneau "
							+ "where id_formateur = ?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete donneJourCreneauIndispo échouée.");
		}
	}

	/**
	 * Méthode qui récupère dans la base données un objet ContrainteFormateur 
	 * grace à l'id formateur
	 * @param idFormateur
	 * @return null
	 */
	public ContrainteFormateur donneJourCreneauIndispo(int idFormateur) {
		ContrainteFormateur contrainteFormateur = new ContrainteFormateur();
		try {
			pDonneJourCreneauIndispo.setInt(1, idFormateur);
			ResultSet resultat = pDonneJourCreneauIndispo.executeQuery();
			if (resultat.next()) {
				contrainteFormateur.setIdJour(resultat.getInt("id_jour"));
				contrainteFormateur.setNomJour(resultat.getString("nom_jour"));
				contrainteFormateur.setIdCreneau(resultat.getInt("id_creneau"));
				contrainteFormateur.setNomCreneau(resultat.getString("nom_creneau"));
				// Ajoutez les attributs de la personne
				return contrainteFormateur;
			} else {
				return contrainteFormateur = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
