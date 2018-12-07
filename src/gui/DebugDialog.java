package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;


/**\
 * 
 * Plik zawiera defnicje klasy dodatkowego okna, wyswietlajacego dane debugowe 
 * 
 * plik: DebugDialog.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */


public class DebugDialog extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea field;
	
	public DebugDialog(JFrame parent) {
		super("Okno wyswietlające dane wybranych obietów");
		
		field = new JTextArea();
		
		getContentPane().add(field);
		
		setBounds(900, 100, 550, 300);
		
		setLayout(new GridLayout(1,1));
		setVisible(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void showDialog() {
		setVisible(true);
	}
	
	public void hideDialog() {
		setVisible(false);
	}

	public void updateText(String text) {
		field.setText(text);
	}
	
	public void addText(String text) {
		field.setText(field.getText() + "\n" + text);
	}
	
	public void clearText() {
		field.setText("");
	}
}

