package fenetre;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;

public class MoisAnnee {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoisAnnee window = new MoisAnnee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MoisAnnee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().setAlwaysFireDayProperty(true);
		calendar.getDayChooser().setMaxDayCharacters(5);
		frame.getContentPane().add(calendar, BorderLayout.CENTER);
	}

}
