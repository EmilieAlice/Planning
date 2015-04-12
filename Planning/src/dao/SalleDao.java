package dao;

import java.sql.ResultSet;

import modele.Salle;

public class SalleDao {

	private static java.sql.PreparedStatement pFindSalleById = null;
	/**
	 * Requete pour récupérer une salle grace à son id
	 */
	static {
		try {
			pFindSalleById = DataBase.getConnection().prepareStatement(
					"SELECT nom_salle FROM lagarenne2015.salle where id_salle =?;");
		} catch (Exception e) {
			e.getMessage();
			System.out.println("Requete findSalleById échouée.");
		}
	}
	/**
	 * Méthode qui récupère dans la base données un objet Salle grace à son id
	 * 
	 * @param idSalle
	 * @return
	 */
	public Salle findModuleByNom(int idSalle) {
		Salle salle = new Salle();
		try {
			pFindSalleById.setInt(1, idSalle);
			ResultSet resultat = pFindSalleById.executeQuery();
			System.out.println(pFindSalleById);
			if (resultat.next()) {
				salle.setIdSalle(resultat.getInt("id_salle"));
				salle.setNomSalle(resultat.getString("nom_salle"));
			} else {
				salle = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salle;
	}
}
