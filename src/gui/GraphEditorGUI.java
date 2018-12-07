package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

import Lab3.MyDialogs;
import electricCircut.ElectricCircuitGraph;


/**\
 * 
 * Plik zawiera defnicje klasy głównego okna aplikacji i funkcje main
 * 
 * plik: GraphEditorGUI.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
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
	GraphPanel panel;
	
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
		
		aboutItem.addActionListener((ActionEvent e) -> {
			MyDialogs.about(this, "Edytor grafów - Edytor układów cyfrowych w technologi NAND\n"
					+ "Bramki dostępne w edytorze:\n"
					+ " - NAND = ~(a * b)\n"
					+ " - NOT = ~a\n"
					+ " - OR z prawa demorgana: \n"
					+ "    NOT-NAND = OR\n"
					+ "                |\n"
					+ "             NOT\n"
					+ "    * zanegowane wejścia na NAND\n"
					+ " - przełącznik - nadający sygnał 0/1 po kliknięciu lewym myszy\n"
					+ " - lampa - wyswietla wprowadzony do niej sygnał\n"
					+ "Sposób działania: \n"
					+ " - bramki mają wejścia i wyjścia. Do wejść można podłączyć maksymalnie 1 krawędz\n"
					+ " - do wyjść można podłączyć dowolną ilość krawędzi\n"
					+ " - usunięcie bramki jest jednoznaczne z usunięciem wszystkich połączonych krawędzi\n"
					+ "Edytor: \n"
					+ " - lewy myszy odpala menu kontekstowe\n"
					+ " - lewy myszy na obiekcie lub po zaznaczeniu obiektów odpala menu dla wybranych obiektów\n"
					+ " - zaznaczanie obiektów: lewy myszy + ctrl (zaznaczne wezły i krawędzie widać na okienku\n"
					+ " - zmiana stanu obiektu typy switch - lmyszy nad obiektem\n"
					+ " - przesuwanie: po zaznaczeniu grupy obiektów lmyszy + przeciągnięcie\n"
					+ " - tworzenie krawędzi: pmyszy nad wezlem + przeciągnięcie na inny wezeł\n"
					+ " - aby wyświetlić caly układ w postaci tekstowej należy zaznaczyć wszystkie obiekty grafu\n"
					+ "   i uruchomić okno debugowe\n"
					+ "Przykładowy układ (bramka xor (~a)*b + a*(~b)) dostępny jest w pliku xor.bin\n"
					+ "Autor: Paweł Parczyk 241390\n"
					+ "Data: 7.12.18\n"
					+ "Repozytorium: https://gitlab.com/kollosp/19-electriccircuitsim.git");
		});
		
		saveFile.addActionListener((ActionEvent e) -> {
			panel.graph().save(MyDialogs.openFileSaveDialog(this));
		});
		
		loadFile.addActionListener((ActionEvent e) -> {
			panel.graph(ElectricCircuitGraph.load(MyDialogs.openFileDialog(this)));
		});
		
		newFile.addActionListener((ActionEvent e) -> {
			panel.graph(new ElectricCircuitGraph());
		});
	}
	
	private void createGraphPanel() {
		
		panel = new GraphPanel(debug);
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
