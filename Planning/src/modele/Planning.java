package modele;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import modele.Seance.Creneau;

public class Planning {
	
	/**
	 * Pour récupérer le nombre de jours
	 * @param dernierJour 
	 * @param premierJour 
	 * @return un entier qui correspond au nombre de jours
	 */
	public int getNbJours(GregorianCalendar premierJour, GregorianCalendar dernierJour) {
		throw new UnsupportedOperationException();
	}
	
	public void set() {
		
	}
	
	/**
	 * Planning de la session dont l'id est passé en paramètre
	 * @param idSession
	 * @return
	 */
	public static Planning get(int idSession) throws SQLException {
		
		throw new UnsupportedOperationException();
	}

	public Seance getSeance(GregorianCalendar jour, Creneau creneau) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 
	 * @param seance
	 */
	public void setSeance(Seance seance) {
		if (seance == null){
			throw new IllegalArgumentException("La seance ne peut etre null");
		}
		if(getSeance(seance.getJour(), seance.getCreneau()) == null){
			throw new IllegalStateException("Il y a déja une séance pour ce jour et ce créneau");
		}
		throw new UnsupportedOperationException();
		
	}

	public void deleteSeance(GregorianCalendar jour, Creneau creneau) {
		throw new UnsupportedOperationException();
		
	}

	public Module getModule(Seance seance) {
		throw new UnsupportedOperationException();
	}

	public GregorianCalendar getPremierJour() {
		throw new UnsupportedOperationException();
	}

	public GregorianCalendar getDernierJour() {
		throw new UnsupportedOperationException();
	}

}
