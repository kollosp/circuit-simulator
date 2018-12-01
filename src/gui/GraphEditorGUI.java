package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class GraphEditorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final String INSTRUCTION = "zaznaczanie węzłów lctrl+l myszy\n"
			+ "przesuwanie zaznaczonych wezlow lmyszy + drag\n"
			+ "przesuwanie calego grafu lmyszy + drag gdy nic nie jest zaznaczone\n"
			+ "";
	
	DebugDialog debug = new DebugDialog(this);
	
	public static void main(String[] args) {
		new GraphEditorGUI();
	}
	
	public GraphEditorGUI() {
	
		super("Edytor grafów - edytor układów cyfrowych w technologii NAND");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());
		
		createMenu();
		createGraphPanel();
		createInfoPanel();
		
		
		pack();
		setBounds(0, 0, 800, 500);
		setVisible(true);
	}
	
	public void close() {
		System.exit(0);
	}
	
	private void createMenu() {
		JMenuBar menubar = new JMenuBar(); 

		JMenu about = new JMenu("Dodatki");
		JMenuItem aboutItem = new JMenuItem("O aplikacji");
		
		about.add(aboutItem);
		
		JMenu file = new JMenu("Plik");
		JMenuItem newFile = new JMenuItem("Nowy");
		JMenuItem loadFile = new JMenuItem("Wczytaj");
		JMenuItem saveFile = new JMenuItem("Zapisz");

		file.add(newFile);
		file.add(loadFile);
		file.add(saveFile);
		
		menubar.add(file);
		menubar.add(about);
		
		setJMenuBar(menubar);
	}
	
	private void createGraphPanel() {
		
		GraphPanel panel = new GraphPanel(debug);
		getContentPane().add(panel, BorderLayout.CENTER);
	}
	
	private void createInfoPanel() {
		JPanel panel = new JPanel();

		JButton exit = new JButton("Exit");
		JButton openDebugWindow = new JButton("Otwórz okno debugowania");
		
		panel.add(openDebugWindow);
		panel.add(exit);
		
		getContentPane().add(panel, BorderLayout.SOUTH);
	
		exit.addActionListener((ActionEvent e) -> {
			close();
		});
		
		openDebugWindow.addActionListener((ActionEvent e) -> {
			debug.showDialog();
		});
		
		debug.showDialog();
		
	}
	

}
