package modele;

import java.sql.Date;

public class Formateur extends Personne {

	private int idPersonne;
	private Date dateEntree;
	private int idModule;

	public Formateur() {

	}

	public Formateur(int idPersonne, Date dateEntree, int idModule) {
		super();
		this.idPersonne = idPersonne;
		this.dateEntree = dateEntree;
		this.idModule = idModule;
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}

	@Override
	public String toString() {
		return "Formateur [idPersonne=" + idPersonne + ", dateEntree="
				+ dateEntree + ", idModule=" + idModule + "]";
	}

}
