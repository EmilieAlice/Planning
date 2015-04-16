package test;

import static org.junit.Assert.*;
import modele.Session;

import org.junit.Test;

import dao.SessionDao;
/**
 * Classe de test permettant de tester toutes les methodes
 * de la classe SessionDao
 *
 */
public class SessionDaoTest {

	/**
	 * test JUnit de la methode findSessionByNom :
	 * permet de verifier si la methode retourne bien la session
	 * grace à son nom
	 */
	@Test
	public void testFindSessionByNom() {
		Session session = new Session();
		SessionDao sessionDao = new SessionDao();

		session = sessionDao.findSessionByNom("BTS Audio 2016");

		assertEquals(3, session.getIdSession());
	}

	/**
	 * test JUnit de la methode findSessionById :
	 * permet de verifier si la methode retourne bien la session
	 * grace à son id
	 */
	@Test
	public void testFindSessionById(){

	}
}
