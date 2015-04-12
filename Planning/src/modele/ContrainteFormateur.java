package modele;

public class ContrainteFormateur {

	private int idFormateur;
	private int idCreneau;
	private String nomCreneau;
	private int idJour;
	private String nomJour;

	public ContrainteFormateur() {
	}

	public ContrainteFormateur(int idFormateur, int idCreneau,
			String nomCreneau, int idJour, String nomJour) {
		super();
		this.idFormateur = idFormateur;
		this.idCreneau = idCreneau;
		this.nomCreneau = nomCreneau;
		this.idJour = idJour;
		this.nomJour = nomJour;
	}

	public int getIdFormateur() {
		return idFormateur;
	}

	public void setIdFormateur(int idFormateur) {
		this.idFormateur = idFormateur;
	}

	public int getIdCreneau() {
		return idCreneau;
	}

	public void setIdCreneau(int idCreneau) {
		this.idCreneau = idCreneau;
	}

	public String getNomCreneau() {
		return nomCreneau;
	}

	public void setNomCreneau(String nomCreneau) {
		this.nomCreneau = nomCreneau;
	}

	public int getIdJour() {
		return idJour;
	}

	public void setIdJour(int idJour) {
		this.idJour = idJour;
	}

	public String getNomJour() {
		return nomJour;
	}

	public void setNomJour(String nomJour) {
		this.nomJour = nomJour;
	}

}
