package modele;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import modele.Seance.Creneau;

public class Planning {
	
	/**
	 * Pour récupérer le nombre de jours
	 * @return un entier qui correspond au nombre de jours
	 */
	public int getNbJours() {
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

	public void setSeance(Seance seance) {
		throw new UnsupportedOperationException();
		
	}

	public void deleteSeance(GregorianCalendar jour, Creneau creneau) {
		throw new UnsupportedOperationException();
		
	}

	public Module getModule(Seance seance) {
		throw new UnsupportedOperationException();
	}

}
