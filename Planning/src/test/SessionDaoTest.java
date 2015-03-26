package test;

import static org.junit.Assert.*;
import modele.Session;

import org.junit.Test;

import dao.SessionDao;

public class SessionDaoTest {

	@Test
	public void testSessionDao() {
		Session session = new Session();
		SessionDao dao = new SessionDao();

		session = dao.findSession("BTS Audio 2016");

		assertEquals(3, session.getIdSession());
	}

}
