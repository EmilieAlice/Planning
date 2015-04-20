package modele;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import dao.SessionDao;
import modele.Seance.Creneau;

public class Planning {

	private int idSession;
	private ArrayList<Seance> listeSeance;

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


	public Seance getSeance(GregorianCalendar jour, Creneau creneau) {
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

	@Override
	public String toString() {
		return "Planning [idSession="
				+ idSession + ", listeSeance=" + listeSeance + "]";
	}


}
