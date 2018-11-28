package graph;

import java.awt.Graphics;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import javafx.print.Collation;
import java.util.List;

public class Drawable implements Comparable<Drawable>{
	
	LinkedList<Drawable> childs = new LinkedList<Drawable>();
	private int priority = 1;
	
	/**
	 * Funkcja rysujaca obiekt i wszystkie jego dzieci
	 * @param g
	 */
	public void draw(Graphics g) {
		Iterator<Drawable> it = childs.iterator();
		
		while(it.hasNext()) {
			it.next().draw(g);
		}
		
		drawMe(g);
	}
	
	/**
	 * Funkcja przeznaczona do narysowania obiektu, powinna być wywołana z funkcji draw
	 * 
	 * @param g obiekt graficzny, na ktorym ma być narysowany dany obiekt
	 */
	public void drawMe(Graphics g) {
		//oddzielna implementacja dla wszystkich klas
	}
	
	/**
	 * Funkcja pozwala dodac obiekt typu <code> Drawable </code> jako dziecko obiektu
	 * od teraz item bedzie adutomatycznie odrysowywany podczas wywolanai funkcji draw dla
	 * obiektu ojcowskiego
	 * 
	 * @param item dziecko obiektu
	 */
	public void addComponent(Drawable item) {
		childs.add(item);
		Collections.sort(childs);
	}
	
	/**
	 * Funkcja pozwala usunac obiekt dziecka
	 * 
	 * 
	 * @param item dziecko obiektu
	 */
	public void removeComponent(Drawable item) {
		childs.remove(item);
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}

	@Override
	/**
	 * Funkcja sluzy do ustawienia priorytetu rysowania obiektow im nizszy prioirytet tym
	 * wczesniej obiekt zostanie narysowany co oznacza, ze znajdzie sie nizej i moze zostac
	 * przysloniety przez inne obiekty
	 */
	public int compareTo(Drawable o) {
		
		if(priority > o.priority) {
			return 1;
		}
		else if(priority < o.priority) {
			return -1;
		}
		
		return 0;
	}
	

}
