package graph;

import java.awt.event.MouseEvent;


/**\
 * Bazowa klasa obiektow grafu. Gwarantuje istnienie funkcji 
 *  <ul>
 *  	<li> hover </li>
 *  	<li> move </li>
 *  	<li> onClick </li>
 * 
 * plik: GraphObject.java
 * data: 7.12.18
 * @author Paweł Parczyk
 */
public class GraphObject extends Drawable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Funckja sprawdza czy kursor znajduje sie ponad obiektem
	 * @param mx x-owa wspolrzedna pozycji myszy
	 * @param my y-owa wspolrzedna pozycji myszy
	 * @return true jezeli mysz znajduje sie nad obiektem
	 */
	public Boolean hover(int mx, int my) {
		return false;
	}
	
	
	/**
	 * Funkcja przesuwa obiekt o dx w osi x i dy w osi y
	 * @param dx przesuniecie w osi x obiektu i jego dzieci
	 * @param dy przesuniecie w osi y obiekut i jego dzieci
	 */
	public void move(int dx, int dy) {
		
	}
	
	/**
	 * Funkcja umozliwia reakcje obiektu na klikniecie w niego myszka
	 * @param e event myszy wygenerowany podczas klikniecia
	 */
	public void onClick(MouseEvent e) {
		
	}
}
