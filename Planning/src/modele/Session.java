package modele;

import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * Objet Session
 * 
 * @author Jerome
 *
 */
public class Session {

	private int idSession;
	private String nom;
	private GregorianCalendar dateDebut;
	private GregorianCalendar dateFin;
	private String description;
	private int idFormation;
	private GregorianCalendar dateDebutInscription;
	private GregorianCalendar dateFinInscription;

	public Session() {

	}

	public Session(int idSession, String nom, GregorianCalendar dateDebut, GregorianCalendar dateFin,
			String description, int idFormation, GregorianCalendar dateDebutInscription,
			GregorianCalendar dateFinInscription) {
		super();
		this.idSession = idSession;
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.description = description;
		this.idFormation = idFormation;
		this.dateDebutInscription = dateDebutInscription;
		this.dateFinInscription = dateFinInscription;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public GregorianCalendar getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(GregorianCalendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	public GregorianCalendar getDateFin() {
		return dateFin;
	}

	public void setDateFin(GregorianCalendar dateFin) {
		this.dateFin = dateFin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}

	public GregorianCalendar getDateDebutInscription() {
		return dateDebutInscription;
	}

	public void setDateDebutInscription(GregorianCalendar dateDebutInscription) {
		this.dateDebutInscription = dateDebutInscription;
	}

	public GregorianCalendar getDateFinInscription() {
		return dateFinInscription;
	}

	public void setDateFinInscription(GregorianCalendar dateFinInscription) {
		this.dateFinInscription = dateFinInscription;
	}

	@Override
	public String toString() {
		return "Session [id_session=" + idSession + ", nom=" + nom
				+ ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", description=" + description + ", id_formation="
				+ idFormation + ", dateDebutInscription="
				+ dateDebutInscription + ", dateFinInscription="
				+ dateFinInscription + "]";
	}

}
