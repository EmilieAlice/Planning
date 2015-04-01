package test;

import java.util.GregorianCalendar;

public class MainTest {

	public static void main(String[] args) {
		
		int milliSecondesParJour = (1000 * 60 * 60 * 24);

		GregorianCalendar premierJour = new GregorianCalendar(2015, 05, 07);
		GregorianCalendar dernierJour = new GregorianCalendar(2016, 05, 12);

		long nbreJours = (dernierJour.getTimeInMillis() - premierJour
				.getTimeInMillis()) / milliSecondesParJour;
		
		System.out.println(nbreJours);

	}

}
