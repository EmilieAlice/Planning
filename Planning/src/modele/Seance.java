package modele;

import java.util.GregorianCalendar;

public class Seance {

	private int idModule;
	private int idSession;
	private int idFormateur;
	private GregorianCalendar debut;
	private GregorianCalendar fin;
	private int idSalle;
	private String contenu;
	private Creneau creneau;

	public Seance() {
	}

	public Seance(int idModule, int idSession, int idFormateur,
			GregorianCalendar debut, GregorianCalendar fin, Creneau creneau,
			int idSalle, String contenu) {
		super();
		this.idModule = idModule;
		this.idSession = idSession;
		this.idFormateur = idFormateur;
		this.debut = debut;
		this.fin = fin;
		this.creneau = creneau;
		this.idSalle = idSalle;
		this.contenu = contenu;

		long journee = debut.getTimeInMillis();
		long millis = (3600000);
		long debutSeance = 0;
		if (getCreneau() == Creneau.APRES_MIDI) {
			debutSeance = 14 * millis;
		} else {
			debutSeance = 9 * millis;
		}
		journee = journee + debutSeance;
		debut.setTimeInMillis(journee);
	}

	public int getIdModule() {
		return idModule;
	}

	public void setIdModule(int idModule) {
		this.idModule = idModule;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	public int getIdFormateur() {
		return idFormateur;
	}

	public void setIdFormateur(int idFormateur) {
		this.idFormateur = idFormateur;
	}

	public GregorianCalendar getDebut() {
		return debut;
	}

	public void setDebut(GregorianCalendar debut) {
		this.debut = debut;
		long journee = debut.getTimeInMillis();
		long millis = (3600000);
		long debutSeance = 0;
		if (getCreneau() == Creneau.APRES_MIDI) {
			debutSeance = 14 * millis;
		} else {
			debutSeance = 9 * millis;
		}
		journee = journee + debutSeance;
		debut.setTimeInMillis(journee);
	}

	public GregorianCalendar getFin() {
		return fin;
	}

	public void setFin(GregorianCalendar fin) {
		this.fin = fin;
	}

	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}

	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	/**
	 * Permet de creer une enumeration pour le creneau
	 * 
	 * @author Alice
	 */
	public enum Creneau {
		MATIN(1), APRES_MIDI(2);

		private int valeur;

		private Creneau(int valeur) {
			this.valeur = valeur;
		}

		public void setValeur(int valeur) {
			this.valeur = valeur;
		}

		public int getValeur() {
			return valeur;
		}
	}

	@Override
	public String toString() {
		return "Seance [idModule=" + idModule + ", idSession=" + idSession
				+ ", idFormateur=" + idFormateur + ", debut=" + debut
				+ ", fin=" + fin + ", idSalle=" + idSalle + ", contenu="
				+ contenu + ", creneau=" + creneau + "]";
	}

	
	
}
