package test;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import modele.Module;
import modele.Session;
import dao.HeuresSessionModuleDao;

public class TestBoutons {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestBoutons window = new TestBoutons();
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
	public TestBoutons() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		HeuresSessionModuleDao dao = new HeuresSessionModuleDao();

		Module maths = new Module();
		maths.setIdModule(2);

		Module anglais = new Module();
		anglais.setIdModule(3);

		Module siDeux = new Module();
		siDeux.setIdModule(1);

		Session session = new Session();
		session.setIdSession(1);

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JRadioButton mathsBouton = new JRadioButton();
		mathsBouton.setText("Maths : "
				+ dao.findHeuresSessionModule(maths, session)
						.getNbreHeuresDisponibles() + " Heures");

		mathsBouton.setBounds(39, 37, 195, 23);
		frame.getContentPane().add(mathsBouton);

		JRadioButton siDeuxBoutton = new JRadioButton();
		siDeuxBoutton.setText("SI2 : "
				+ dao.findHeuresSessionModule(siDeux, session)
						.getNbreHeuresDisponibles() + " Heures");
		siDeuxBoutton.setBounds(39, 82, 195, 23);
		frame.getContentPane().add(siDeuxBoutton);

		JRadioButton anglaisBoutton = new JRadioButton();
		anglaisBoutton.setText("Anglais : "
				+ dao.findHeuresSessionModule(anglais, session)
						.getNbreHeuresDisponibles() + " Heures");
		anglaisBoutton.setBounds(39, 136, 195, 23);
		frame.getContentPane().add(anglaisBoutton);

		ButtonGroup group = new ButtonGroup();
		group.add(siDeuxBoutton);
		group.add(mathsBouton);
		group.add(anglaisBoutton);
	}
}
