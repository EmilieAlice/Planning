package modele;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Seance {
	
	private int idModule;
	private int idSession;
	private int idFormateur;
	private GregorianCalendar jour;
	private int idSalle;
	private String contenu;
	private Creneau creneau;
	
	/**
	 * Le jour et le contenu sont null
	 */
	public Seance(){
		
	}
	
	public Seance(int idModule, int idSession, int idFormateur, GregorianCalendar jour,
			Creneau creneau, int idSalle, String contenu) {
		super();
		this.idModule = idModule;
		this.idSession = idSession;
		this.idFormateur = idFormateur;
		this.jour = jour;
		this.idSalle = idSalle;
		this.contenu = contenu;
		this.creneau = creneau;
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

	public Calendar getJour() {
		return jour;
	}

	public void setJour(GregorianCalendar jour) {
		this.jour = jour;
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
	
	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}

	public enum Creneau{
		MATIN(0), APRES_MIDI(1);
		
		private int valeur;

		private Creneau(int valeur){
			this.valeur = valeur;
		}
		
		public int getValeur(){
			return valeur;
		}
	}


}
