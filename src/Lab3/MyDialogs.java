package Lab3;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Program: Klasa zawierajaca tylko metody statyczne, sluz do upozadkowania kodu. 
 * 			zawiera metody ruchamiajace male informacyjne okienka. 
 *    Plik: MyDialogs.java
 *          
 *   Autor: Paweł Parczyk
 *    Data: listopad 2018 r.
 *
 */


public class MyDialogs {
	
	static public Boolean openAgreementDialog(JFrame parent, String title, String message) {
		Object[] options = {"Tak",
                "Nie"};
		
		int n = JOptionPane.showOptionDialog(parent,
				message,
				title,
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,     //do not use a custom Icon
			    options,  //the titles of buttons
			    options[0]); //default button title
	
		return n==0;
	}
	
	static public String openFileSaveDialog(JFrame parent) {
		FileDialog fd = new FileDialog(parent, "Choose a file", FileDialog.SAVE);
		
		fd.setDirectory("");
		fd.setFile("*.*");
		fd.setVisible(true);
		return fd.getDirectory() + fd.getFile();
	}
	
	static public String openFileDialog(JFrame parent) {
		FileDialog fd = new FileDialog(parent, "Choose a file", FileDialog.LOAD);
		
		fd.setDirectory("");
		fd.setFile("*.*");
		fd.setVisible(true);
		return fd.getDirectory() + fd.getFile();
	}
	
	static public void about(JFrame parent, String message) {
		JOptionPane.showMessageDialog(parent, message);
	}

}
