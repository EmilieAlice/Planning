package test;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

import modele.DonneesTableau;
import modele.Module;
import modele.Session;
import dao.HeuresSessionModuleDao;
import dao.ModuleDao;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;

public class TestBoutons {

	private JFrame frame;
	private JTable table;
	private JPanel panelBouttons;
	private ButtonGroup group;
	private JRadioButton mathsBouton;

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
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		group = new ButtonGroup();

		JPanel panelTableau = new JPanel();
		panelTableau.setBounds(6, 6, 941, 666);
		frame.getContentPane().add(panelTableau);

		table = new JTable(new DonneesTableau());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.addMouseListener(new ecouteur());
		panelTableau.add(table);

		panelBouttons = new JPanel();
		panelBouttons.setBounds(973, 294, 195, 193);
		frame.getContentPane().add(panelBouttons);

		JRadioButton siDeuxBoutton = new JRadioButton();
		panelBouttons.add(siDeuxBoutton);
		siDeuxBoutton.setText("SI2 : "
				+ dao.findHeuresSessionModule(siDeux, session)
						.getNbreHeuresDisponibles() + " Heures");
		group.add(siDeuxBoutton);

		JRadioButton anglaisBoutton = new JRadioButton();
		panelBouttons.add(anglaisBoutton);
		anglaisBoutton.setText("Anglais : "
				+ dao.findHeuresSessionModule(anglais, session)
						.getNbreHeuresDisponibles() + " Heures");
		group.add(anglaisBoutton);

		mathsBouton = new JRadioButton();
		panelBouttons.add(mathsBouton);
		mathsBouton.setText("Maths : "
				+ dao.findHeuresSessionModule(maths, session)
						.getNbreHeuresDisponibles() + " Heures");
		group.add(mathsBouton);
	}

	public class ecouteur implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println(table.getValueAt(table.getSelectedRow(),
					table.getSelectedColumn()));
			String texte = "";
			Component[] tableau = panelBouttons.getComponents();
			ArrayList<JRadioButton> tableauBoutton = new ArrayList<JRadioButton>();

			for (int i = 0; i < tableau.length; i++) {
				tableauBoutton.add((JRadioButton) tableau[i]);
			}

			for (JRadioButton jRadioButton : tableauBoutton) {
				if (jRadioButton.isSelected()) {
					texte = jRadioButton.getText();
				}
			}
			texte = texte.split(" ")[0];
			if (texte != "") {
				ModuleDao moduleDao = new ModuleDao();
				Module module = moduleDao.findModuleByNom(texte);
				System.out.println(module.getIdModule());
			}
			System.out.println(texte);

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
