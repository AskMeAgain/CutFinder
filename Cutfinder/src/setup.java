import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class setup {

	public JFrame frame;
	public JTextField cp;
	private static int n;

	/**
	 * Launch the application.
	 */

	public static void print(List<List<Integer>> a) {

		for (int i = 0; i < a.size()-1; i++) {
			System.out.print("Von CP" + i + " zu ");
			for (int ii = 0; ii < a.get(i).size(); ii++) {
				System.out.print(a.get(i).get(ii) + " ");
			}
			System.out.println();
		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setup window = new setup();
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
	public setup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(800, 200, 185, 206);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNumberOfCheckpoints = new JLabel("Number of Checkpoints");
		lblNumberOfCheckpoints.setBounds(24, 33, 191, 14);
		frame.getContentPane().add(lblNumberOfCheckpoints);

		cp = new JTextField();
		cp.setHorizontalAlignment(SwingConstants.CENTER);
		cp.setBounds(24, 54, 86, 20);
		frame.getContentPane().add(cp);
		cp.setColumns(10);
		cp.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					frame.setVisible(false);
					n = Integer.parseInt(cp.getText());
					new grid(n);
				}
			}
		});

		JButton btnNewButton = new JButton("GO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				n = Integer.parseInt(cp.getText());
				new grid(n);
			}
		});
		btnNewButton.setBounds(24, 85, 89, 23);
		frame.getContentPane().add(btnNewButton);

	}

}
