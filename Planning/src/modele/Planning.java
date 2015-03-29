package modele;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import dao.SessionDao;
import modele.Seance.Creneau;

public class Planning {

	private int idPlanning;
	private int idSession;
	private ArrayList<Seance> listeSeance;

	/*
	 * public void set() {
	 * 
	 * }
	 */

	public int getIdPlanning() {
		return idPlanning;
	}

	public void setIdPlanning(int idPlanning) {
		this.idPlanning = idPlanning;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	public ArrayList<Seance> getListeSeance() {
		return listeSeance;
	}

	public void setListeSeance(ArrayList<Seance> listeSeance) {
		this.listeSeance = listeSeance;
	}

	/**
	 * Planning de la session dont l'id est passé en paramètre
	 * 
	 * @param idSession
	 * @return
	 */
	/*
	 * public static Planning get(int idSession) throws SQLException {
	 * 
	 * throw new UnsupportedOperationException(); }
	 */

	public Seance getSeance(GregorianCalendar jour, Creneau creneau) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Permet d'insérer une séance dans le planning
	 * Si la séance est null l'insertion échoue
	 * Si il y a déjà une séance pour ce créneau l'insertion échoue
	 * 
	 * @param seance
	 */
	public void setSeance(Seance seance) {
		if (seance == null) {
			throw new IllegalArgumentException("La seance ne peut etre null");
		}
		if (getSeance(seance.getJour(), seance.getCreneau()) == null) {
			throw new IllegalStateException(
					"Il y a déja une séance pour ce jour et ce créneau");
		}
		throw new UnsupportedOperationException();

	}

	/**
	 * Permet de supprimer une séance dans le planning
	 * 
	 * @param jour
	 * @param creneau
	 */
	public void deleteSeance(GregorianCalendar jour, Creneau creneau) {
		throw new UnsupportedOperationException();

	}

	/**
	 * Permet de retourner un module à une seance donnée
	 * 
	 * @param seance
	 * @return
	 */
	public Module getModule(Seance seance) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Donne le premier jour d'une session grâce à l'id de la session
	 * 
	 * @param idSession
	 *            un entier
	 * @return le premier jour au format GregorianCalendar
	 */
	public GregorianCalendar getPremierJour(int idSession) {
		SessionDao sessionDao = new SessionDao();
		Session session = sessionDao.findSessionById(idSession);
		GregorianCalendar dernierJour;

		dernierJour = session.getDateDebut();
		return dernierJour;
	}

	/**
	 * Donne le dernier jour de la session grâce à l'id de la session
	 * 
	 * @param idSession
	 *            un entier
	 * @return le dernier jour au format GregorianCalendar
	 */
	public GregorianCalendar getDernierJour(int idSession) {
		SessionDao sessionDao = new SessionDao();
		Session session = sessionDao.findSessionById(idSession);
		GregorianCalendar dernierJour;

		dernierJour = session.getDateFin();
		return dernierJour;
	}

	/**
	 * Méthode qui récupère le nombre de jours entre la date de fin et la date
	 * de début du planning
	 * 
	 * @param planning
	 *            (l'objet planning concerné)
	 * @return le nombre de jours
	 */
	public long nbreJours(Planning planning) {
		long nbreJours;
		int milliSecondesParJour = (1000 * 60 * 60 * 24);

		GregorianCalendar premierJour = planning.getPremierJour(planning.getIdSession());
		GregorianCalendar dernierJour = planning.getDernierJour(planning.getIdSession());

		nbreJours = (dernierJour.getTimeInMillis() - premierJour
				.getTimeInMillis()) / milliSecondesParJour;

		return nbreJours;

	}

}
