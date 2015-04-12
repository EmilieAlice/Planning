package modele;

public class Salle {

	private int idSalle;
	private String nomSalle;

	public Salle(int idSalle, String nomSalle) {
		super();
		this.idSalle = idSalle;
		this.nomSalle = nomSalle;
	}

	public Salle() {
	}

	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public String getNomSalle() {
		return nomSalle;
	}

	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}

	@Override
	public String toString() {
		return "Salle [idSalle=" + idSalle + ", nomSalle=" + nomSalle + "]";
	}

}
