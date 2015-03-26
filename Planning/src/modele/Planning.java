package modele;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import modele.Seance.Creneau;

public class Planning {
	
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
	 * Permet d'insérer une séance dans le planning
	 * Si la séance est null l'insertion échoue
	 * Si il y a déjà une séance pour ce créneau l'insertion échoue
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
	
	/**
	 * Permet de supprimer une séance dans le planning
	 * @param jour
	 * @param creneau
	 */
	public void deleteSeance(GregorianCalendar jour, Creneau creneau) {
		throw new UnsupportedOperationException();
		
	}

	public Module getModule(Seance seance) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Donne le premier jour de la session
	 * @return
	 */
	public GregorianCalendar getPremierJour(int idSession) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Donne le dernier jour de la session
	 * @return
	 */
	public GregorianCalendar getDernierJour(int idSession) {
		throw new UnsupportedOperationException();
	}

}
