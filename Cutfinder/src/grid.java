
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.util.List;

public class grid {

	private static JFrame frame, frame2;
	private static JTextField textField, textField_1, textField_2;
	private static Map<String, JTextField> fieldMap = new HashMap<String, JTextField>();
	static List<List<Integer>> adja = new ArrayList<List<Integer>>();
	private static JPanel panel;
	private static boolean schon = false;
	static JTextArea area;
	static int route = 1;
	public static int n = 0;
	public static PrintWriter writer;
	public static String filename = "";
	public static boolean schon2 = false;

	public static void update(String s) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				area.append(s + "\n");
			}
		});

	}

	public static void save() {

		filename = (String) JOptionPane.showInputDialog(frame, "Filename:", "Saving", JOptionPane.PLAIN_MESSAGE, null,
				null, "");
		
		if(filename == null){
			filename = "default";
		}

		PrintWriter writer;
		try {
			writer = new PrintWriter(filename + ".CFM", "UTF-8");

			writer.println(n);

			for (int i = 0; i < n + 1; i++) {
				for (int ii = 0; ii < n + 2; ii++) {
					if (fieldMap.get("A_" + (ii) + "_" + (i)).getText().equals("")) {
						writer.print("0 ");
					} else {
						writer.print(fieldMap.get("A_" + (ii) + "_" + (i)).getText() + " ");
					}
				}
				writer.println();
			}
			writer.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	public static void add() {
		String ii = textField_1.getText();
		String i = textField_2.getText();
		fieldMap.get("A_" + (i) + "_" + (ii)).setText("1");
		textField_2.setText("");
	}

	public static void menu() {

		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		/////////////// File
		JMenu file = new JMenu("file");
		menubar.add(file);

		// save
		JMenuItem save = new JMenuItem("save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

		file.add(save);
		class save implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		}
		save.addActionListener(new save());

		// open
		JMenuItem open = new JMenuItem("open");
		file.add(open);
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));

		class open implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				FileInputStream fstream;
				int i = 0;

				filename = (String) JOptionPane.showInputDialog(frame, "Filename:", "Loading a file",
						JOptionPane.PLAIN_MESSAGE, null, null, "");

				if (filename != null) {
					try {

						fstream = new FileInputStream(filename + ".CFM");
						BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
						String strLine;
						strLine = br.readLine();


							if (n != Integer.parseInt(strLine)) {

							frame.dispose();
							new grid(Integer.parseInt(strLine));
							
							}

							while ((strLine = br.readLine()) != null) {
								String[] parts = strLine.split(" ");
								for (int ii = 0; ii < n + 2; ii++) {
									if (!parts[ii].equals("0")) {
										fieldMap.get("A_" + (ii) + "_" + (i)).setText(parts[ii]);
									}
								}								
								i++;
							}

						br.close();

					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Throwable e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		open.addActionListener(new open());

		file.addSeparator();

		// add CP
		JMenuItem add = new JMenuItem("add CP");
		file.add(add);
		add.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

		class add implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				JFrame fr = new JFrame();
				fr.setBounds(600, 100, 342, 92);
				fr.setVisible(true);
				fr.getContentPane().setLayout(null);

				textField_1 = new JTextField();
				textField_1.setBounds(71, 19, 41, 23);
				fr.getContentPane().add(textField_1);
				textField_1.setColumns(10);

				textField_2 = new JTextField();
				textField_2.setBounds(153, 19, 41, 23);
				fr.getContentPane().add(textField_2);
				textField_2.setColumns(10);
				textField_2.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent e) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							add();
						} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
							textField_1.setText("");
							textField_2.setText("");
							textField_1.requestFocus();
						}
					}
				});

				JButton btnNewButton = new JButton("Add");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						add();
					}
				});
				btnNewButton.setBounds(223, 18, 89, 23);
				fr.getContentPane().add(btnNewButton);

				JLabel lblFromCp = new JLabel("From CP");
				lblFromCp.setBounds(10, 22, 54, 23);
				fr.getContentPane().add(lblFromCp);

				JLabel lblTo = new JLabel("to");
				lblTo.setBounds(132, 22, 21, 23);
				fr.getContentPane().add(lblTo);

			}
		}
		add.addActionListener(new add());

		/////////////// Compute
		JMenu compute = new JMenu("compute");
		menubar.add(compute);

		JMenuItem simple = new JMenuItem("simple routing");
		compute.add(simple);
		simple.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		class simple implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				Thread queryThread = new Thread() {
					public void run() {
						simple();
					}
				};
				queryThread.start();

			}
		}
		simple.addActionListener(new simple());

		/////////////// Settings
		JMenu settings = new JMenu("options");
		menubar.add(settings);

		// hide same CP
		JCheckBoxMenuItem hide = new JCheckBoxMenuItem("Hide same CP's");
		settings.add(hide);
		class hide implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				boolean state = true;

				if (hide.getState()) {
					state = false;
				}

				for (int i = 0; i < n + 1; i++) {
					for (int ii = 0; ii < n + 2; ii++) {
						if (i == ii) {
							fieldMap.get("A_" + (ii) + "_" + (i)).setVisible(state);
						}
					}
				}
			}
		}
		hide.addActionListener(new hide());

		// set standard route
		JMenuItem route = new JMenuItem("set standard route");
		settings.add(route);
		route.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));

		class route implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < n + 1; i++) {
					fieldMap.get("A_" + (i + 1) + "_" + (i)).setText("1");
				}
			}
		}
		route.addActionListener(new route());

		// set standard route
		JMenuItem cp = new JMenuItem("change number of CP's");
		settings.add(cp);
		class cp implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				String s = (String) JOptionPane.showInputDialog(frame, "new number of CPs", "Changing CP number",
						JOptionPane.PLAIN_MESSAGE, null, null, "");

				if (s != null) {
					frame.dispose();
					new grid(Integer.parseInt(s));
				}
			}
		}
		cp.addActionListener(new cp());
		settings.addSeparator();

		JMenuItem delete = new JMenuItem("Remove all");
		settings.add(delete);
		class delete implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					for (int i = 0; i < n + 2; i++) {
						for (int ii = 0; ii < n + 1; ii++) {
							fieldMap.get("A_" + (i) + "_" + (ii)).setText("");
						}
					}
				}
			}
		}
		delete.addActionListener(new delete());

	}

	public static void adjazenzliste() {

		adja = new ArrayList<List<Integer>>();

		for (int i = 0; i <= n + 1; i++) {
			adja.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < n + 1; i++) {
			for (int ii = 0; ii < n + 2; ii++) {
				if (!fieldMap.get("A_" + (ii) + "_" + (i)).getText().isEmpty()) {
					if (i != ii) {
						adja.get(i).add(ii);
					}
				}
			}
		}

		adja.get(n + 1).add(0);
	}

	public static void simple() {
		route = 1;
		adjazenzliste();

		List<Integer> steps = new ArrayList<Integer>();
		steps.add(0);
		solve(steps, adja);

	}

	public static int time(List<Integer> steps) {

		int erste, zweite;
		int time = 0;

		for (int i = 0; i < steps.size() - 1; i++) {
			erste = steps.get(i);
			zweite = steps.get(i + 1);
			time = time + Integer.parseInt(fieldMap.get("A_" + (zweite) + "_" + (erste)).getText());
		}

		// add finish
		time = time + Integer.parseInt(fieldMap.get("A_" + (n + 1) + "_" + (steps.get(steps.size() - 1))).getText());

		return time;

	}

	public static String luecke(List<Integer> steps) {

		for (int i = 0; i < steps.size() - 2; i++) {
			if (steps.get(i) == steps.get(i + 1) - 1 && steps.get(i + 2) - 2 == steps.get(i)) {
				steps.set(i + 1, -1);
			}
		}

		for (int i = 0; i < steps.size() - 2; i++) {
			if (steps.get(i) == -1 && steps.get(i + 2) == -1) {
				steps.set(i + 1, -1);
			}
		}

		String s = "";

		for (int i = 0; i < steps.size() - 1; i++) {
			if (i == 0) {
				s += "Start";
			} else if (steps.get(i) == -1) {
				s += "- ";
			} else {
				s += steps.get(i) + " ";
			}
		}

		for (int i = 0; i < steps.size() - 1; i++) {
			s = s.replaceAll(" - -", " -");
		}

		s = s.replaceAll(" - ", "-");
		s = s.replaceAll(" ", ", ");
		s = s.replaceAll("--", "-");
		s = s.replaceAll("Start2", "Start,2");
		s = s.replaceAll("Start1", "Start,1");

		s += "Finish";

		return s;
	}

	public static void printlös(List<Integer> steps) {

		if (filename.equals("")) {
			save();
		}

		int fr = 0;

		String s = luecke(steps);

		if (schon == false) {

			schon = true;

			fr = 150 + steps.size() * 20;

			frame2 = new JFrame();
			frame2.setBounds(800, 200, fr, 270);
			frame2.getDefaultCloseOperation();
			frame2.setResizable(false);
			frame2.getContentPane().setLayout(null);
			frame2.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent windowEvent) {
					schon = false;
				}
			});

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, fr, 180);
			frame2.getContentPane().add(scrollPane);
			area = new JTextArea("possible routes are: \n");
			scrollPane.setViewportView(area);

			frame2.setVisible(true);

			update(s);
			route++;

		} else {
			update(s);
			route++;
		}

		try{
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + "-route.txt", true)));				
		    out.append(s + System.getProperty("line.separator"));
		    out.close();
		    
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}

	}

	public static void solve(List<Integer> old, List<List<Integer>> spezi) {

		int akt = old.get(old.size() - 1);

		if (old.size() == n + 1) {
			for (int i = 0; i < adja.get(akt).size(); i++) {
				if (adja.get(akt).get(i) == n + 1) {
					printlös(old);
				}
			}
		} else {

			List<Integer> steps = new ArrayList<Integer>(old);
			List<List<Integer>> a = new ArrayList<List<Integer>>(spezi);

			for (int i = 0; i < a.get(akt).size(); i++) {
				
				int t = a.get(akt).get(i);
				
				if (a.get(t).get(0) != -1) {

					int temp = a.get(akt).get(0);

					steps.add(t);
					a.get(akt).set(0, -1);

					solve(steps, a);

					steps.remove(steps.size() - 1);
					a.get(akt).set(0, temp);
				}
			}
		}
	}

	public static void gui() {

		menu();

		JLabel label;
		label = new JLabel(" ");
		panel.add(label);

		for (int i = 0; i <= n + 1; i++) {
			if (i == 0) {
				label = new JLabel("start");
			} else if (i == n + 1) {
				label = new JLabel("finish");
			} else {
				label = new JLabel("" + (i));
			}
			label.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(label);

		}

		label = new JLabel(" ");
		panel.add(label);

		for (int i = 0; i < n + 1; i++) {
			if (i == 0) {
				label = new JLabel("start");
			} else {
				label = new JLabel("" + (i));
			}
			label.setHorizontalAlignment(SwingConstants.CENTER);

			panel.add(label);

			for (int ii = 0; ii < n + 2; ii++) {	
				textField = new JTextField();
				textField.setHorizontalAlignment(SwingConstants.CENTER);
				fieldMap.put("A_" + (ii) + "_" + (i), textField);
				panel.add(textField);
			}

			label = new JLabel(" ");
			panel.add(label);

		}

		JLabel label2;
		label2 = new JLabel(" ");
		panel.add(label2);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					grid window = new grid(n);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public grid(int a) {
		n = a;
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(400, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setVisible(true);

		panel = new JPanel();
		panel.setBounds(100, 100, 800, 600);
		panel.setLayout(new GridLayout(0, n + 4));

		JScrollPane pane = new JScrollPane(panel);
		frame.getContentPane().add(pane);

		gui();

	}
}
