package modele;

import java.sql.Date;

/**
 * Objet Session
 * @author Jerome
 *
 */
public class Session {

	private int id_session;
	private String nom;
	private Date dateDebut;
	private Date dateFin;
	private String description;
	private int id_formation;
	private Date dateDebutInscription;
	private Date dateFinInscription;

	public Session() {

	}

	public Session(int id_session, String nom, Date dateDebut, Date dateFin,
			String description, int id_formation, Date dateDebutInscription,
			Date dateFinInscription) {
		super();
		this.id_session = id_session;
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.description = description;
		this.id_formation = id_formation;
		this.dateDebutInscription = dateDebutInscription;
		this.dateFinInscription = dateFinInscription;
	}

	public int getId_session() {
		return id_session;
	}

	public void setId_session(int id_session) {
		this.id_session = id_session;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId_formation() {
		return id_formation;
	}

	public void setId_formation(int id_formation) {
		this.id_formation = id_formation;
	}

	public Date getDateDebutInscription() {
		return dateDebutInscription;
	}

	public void setDateDebutInscription(Date dateDebutInscription) {
		this.dateDebutInscription = dateDebutInscription;
	}

	public Date getDateFinInscription() {
		return dateFinInscription;
	}

	public void setDateFinInscription(Date dateFinInscription) {
		this.dateFinInscription = dateFinInscription;
	}

	@Override
	public String toString() {
		return "Session [id_session=" + id_session + ", nom=" + nom
				+ ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", description=" + description + ", id_formation="
				+ id_formation + ", dateDebutInscription="
				+ dateDebutInscription + ", dateFinInscription="
				+ dateFinInscription + "]";
	}

}
