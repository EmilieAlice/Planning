package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dao.ContrainteFormateurDao;

public class ContrainteFormateurDaoTest {

	@Test
	public void testDonneJourCreneauIndispo() {
		ArrayList<Integer> listeEssai = new ArrayList<Integer>();
		listeEssai.add(4);
		listeEssai.add(10);
		
		ArrayList<Integer> listeBase = new ArrayList<Integer>();
		ContrainteFormateurDao contrainteDao = new ContrainteFormateurDao();
		listeBase = contrainteDao.donneJourCreneauIndispo(5);
		
		for (int i = 0; i < listeBase.size(); i++) {
			assertEquals(listeEssai.get(i), listeBase.get(i));
		}
	}

}
