package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class IhmTest {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IhmTest window = new IhmTest();
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
	public IhmTest() {
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
		
		table = new JTable(new TableModelTest());
		table.setBounds(6, 6, 438, 266);
		frame.getContentPane().add(table);
	
	}
	
	
}
