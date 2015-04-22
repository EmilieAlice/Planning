package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ContrainteFormateurDao {

	private static java.sql.PreparedStatement pDonneJourCreneauIndispo = null;
	/**
	 * Requete pour récuperer les contraintes d'un formateur
	 */
	static {
		try {
			pDonneJourCreneauIndispo = DataBase
					.getConnection()
					.prepareStatement(
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
	 * Méthode qui récupère dans la base de données une liste d'Integer
	 * grace à l'id formateur
	 * 
	 * @param idFormateur
	 * @return listeContrainteFormateur
	 */
	public ArrayList<Integer> donneJourCreneauIndispo(int idFormateur) {
		ArrayList<Integer> listeContrainteFormateur = new ArrayList<Integer>();
		try {
			pDonneJourCreneauIndispo.setInt(1, idFormateur);
			ResultSet resultat = pDonneJourCreneauIndispo.executeQuery();
			while (resultat.next()) {
				int idJour = resultat.getInt("id_jour");
				int idCreneau = resultat.getInt("id_creneau");
				int numColonne = 0;
				switch (idJour) {
				case 1:
					numColonne = 1;
					if (idCreneau == 2) {
						numColonne = 2;
					}
					break;
				case 2:
					numColonne = 3;
					if (idCreneau == 2) {
						numColonne = 4;
					}
					break;
				case 3:
					numColonne = 5;
					if (idCreneau == 2) {
						numColonne = 6;
					}
					break;
				case 4:
					numColonne = 7;
					if (idCreneau == 2) {
						numColonne = 8;
					}
					break;
				case 5:
					numColonne = 9;
					if (idCreneau == 2) {
						numColonne = 10;
					}
					break;
				}
				listeContrainteFormateur.add(numColonne);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeContrainteFormateur;
	}
}
