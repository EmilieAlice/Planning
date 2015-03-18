package fenetre;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

import java.awt.BorderLayout;

import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.components.JSpinField;
import com.toedter.calendar.JMonthChooser;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JDayChooser;

import javax.swing.JTable;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.SystemColor;

public class Planning {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Planning window = new Planning();
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
	public Planning() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel calendrier = new JPanel();
		calendrier.setBounds(10, 10, 414, 210);
		frame.getContentPane().add(calendrier);
		calendrier.setLayout(null);
		
		JLabel lblSemaine = new JLabel("Semaine");
		lblSemaine.setBounds(0, 0, 50, 20);
		calendrier.add(lblSemaine);
		
		JComboBox nombreSemaine = new JComboBox();
		nombreSemaine.setMaximumRowCount(52);
		nombreSemaine.setBounds(54, 1, 40, 20);
		calendrier.add(nombreSemaine);
		
		int i = 1;
		while (i <= 52) {
			nombreSemaine.addItem(i);
			i++;			
		}
		
		JLabel lblMatiere = new JLabel("Matiere");
		lblMatiere.setBounds(128, 0, 45, 20);
		calendrier.add(lblMatiere);
		
		JComboBox nomMatiere = new JComboBox();
		nomMatiere.setBounds(174, 1, 175, 20);
		calendrier.add(nomMatiere);
		
		JPanel planning = new JPanel();
		planning.setBackground(SystemColor.activeCaption);
		planning.setBounds(0, 20, 415, 190);
		calendrier.add(planning);
		planning.setLayout(null);
		
		JPanel plageLundi = new JPanel();
		plageLundi.setBounds(15, 0, 80, 190);
		planning.add(plageLundi);
		plageLundi.setLayout(null);
		
		JPanel nomLundi = new JPanel();
		nomLundi.setBounds(0, 0, 83, 20);
		plageLundi.add(nomLundi);
		nomLundi.setLayout(null);
		
		JLabel lblLundi = new JLabel("Lundi");
		lblLundi.setBounds(0, 0, 63, 20);
		nomLundi.add(lblLundi);
		
		JLabel lblDateLundi = new JLabel("");
		lblDateLundi.setBounds(63, 0, 20, 20);
		nomLundi.add(lblDateLundi);
		
		JPanel plageLundiMatin = new JPanel();
		plageLundiMatin.setBounds(0, 20, 80, 85);
		plageLundi.add(plageLundiMatin);
		
		JPanel plageLundiApresMidi = new JPanel();
		plageLundiApresMidi.setBounds(0, 105, 80, 85);
		plageLundi.add(plageLundiApresMidi);
		
		JPanel plageMardi = new JPanel();
		plageMardi.setBounds(95, 0, 80, 190);
		planning.add(plageMardi);
		plageMardi.setLayout(null);
		
		JPanel nomMardi = new JPanel();
		nomMardi.setBounds(0, 0, 83, 20);
		plageMardi.add(nomMardi);
		nomMardi.setLayout(null);
		
		JLabel lblMardi = new JLabel("Mardi");
		lblMardi.setBounds(0, 0, 63, 20);
		nomMardi.add(lblMardi);
		
		JLabel lblDateMardi = new JLabel("");
		lblDateMardi.setBounds(63, 0, 20, 20);
		nomMardi.add(lblDateMardi);
		
		JPanel plageMardiMatin = new JPanel();
		plageMardiMatin.setBounds(0, 105, 80, 85);
		plageMardi.add(plageMardiMatin);
		
		JPanel plageMardiApresMidi = new JPanel();
		plageMardiApresMidi.setBounds(0, 20, 80, 85);
		plageMardi.add(plageMardiApresMidi);
		
		JPanel plageMercredi = new JPanel();
		plageMercredi.setBounds(175, 0, 80, 190);
		planning.add(plageMercredi);
		plageMercredi.setLayout(null);
		
		JPanel nomMercredi = new JPanel();
		nomMercredi.setBounds(0, 0, 83, 20);
		plageMercredi.add(nomMercredi);
		nomMercredi.setLayout(null);
		
		JLabel lblMercredi = new JLabel("Mercredi");
		lblMercredi.setBounds(0, 0, 63, 20);
		nomMercredi.add(lblMercredi);
		
		JLabel lblDateMercredi = new JLabel("");
		lblDateMercredi.setBounds(63, 0, 20, 20);
		nomMercredi.add(lblDateMercredi);
		
		JPanel plageMercrediMatin = new JPanel();
		plageMercrediMatin.setBounds(0, 105, 80, 85);
		plageMercredi.add(plageMercrediMatin);
		
		JPanel plageMercrediApresMidi = new JPanel();
		plageMercrediApresMidi.setBounds(0, 20, 80, 85);
		plageMercredi.add(plageMercrediApresMidi);
		
		JPanel plageJeudi = new JPanel();
		plageJeudi.setBounds(255, 0, 80, 190);
		planning.add(plageJeudi);
		plageJeudi.setLayout(null);
		
		JPanel nomJeudi = new JPanel();
		nomJeudi.setBounds(0, 0, 83, 20);
		plageJeudi.add(nomJeudi);
		nomJeudi.setLayout(null);
		
		JLabel lblJeudi = new JLabel("Jeudi");
		lblJeudi.setBounds(0, 0, 63, 20);
		nomJeudi.add(lblJeudi);
		
		JLabel lblDateJeudi = new JLabel("");
		lblDateJeudi.setBounds(63, 0, 20, 20);
		nomJeudi.add(lblDateJeudi);
		
		JPanel plageJeudiMatin = new JPanel();
		plageJeudiMatin.setBounds(0, 105, 80, 85);
		plageJeudi.add(plageJeudiMatin);
		
		JPanel plageJeudiApresMidi = new JPanel();
		plageJeudiApresMidi.setBounds(0, 20, 80, 85);
		plageJeudi.add(plageJeudiApresMidi);
		
		JPanel plageVendredi = new JPanel();
		plageVendredi.setBounds(335, 0, 80, 190);
		planning.add(plageVendredi);
		plageVendredi.setLayout(null);
		
		JPanel nomVendredi = new JPanel();
		nomVendredi.setBounds(0, 0, 83, 20);
		plageVendredi.add(nomVendredi);
		nomVendredi.setLayout(null);
		
		JLabel lblVendredi = new JLabel("Vendredi");
		lblVendredi.setBounds(0, 0, 63, 20);
		nomVendredi.add(lblVendredi);
		
		JLabel lblDateVendredi = new JLabel("");
		lblDateVendredi.setBounds(63, 0, 20, 20);
		nomVendredi.add(lblDateVendredi);
		
		JPanel plageVendrediMatin = new JPanel();
		plageVendrediMatin.setBounds(0, 105, 80, 85);
		plageVendredi.add(plageVendrediMatin);
		
		JPanel plageVendrediApresMidi = new JPanel();
		plageVendrediApresMidi.setBounds(0, 20, 80, 85);
		plageVendredi.add(plageVendrediApresMidi);
		
		JPanel plageHoraire = new JPanel();
		plageHoraire.setBounds(0, 0, 15, 190);
		planning.add(plageHoraire);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnValider.setBounds(341, 227, 83, 23);
		frame.getContentPane().add(btnValider);
	}
}
