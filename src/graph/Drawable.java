package graph;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;




/**\
 * 
 * Podstawowa klasa obiektów które mogą być narysowane na grafie
 * 
 * plik: Drawable.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public class Drawable implements Comparable<Drawable>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Wektor zawierający podobiekty(dzieci) tego obiektu
	 */
	LinkedList<Drawable> childs = new LinkedList<Drawable>();
	
	/**
	 * Prioirytet rysowania obiektów. Odpowiada za kolejność pojawiania sie ich na scenie
	 */
	private int priority = 1;
	
	/**
	 * Flaga definiująca czy dzieci obiektu maja być wyswietlane
	 */
	private Boolean drawChilds = true;
	
	/**
	 * Funkcja rysujaca obiekt i wszystkie jego dzieci
	 * @param g kontekst graficzny na którym ma być narysowany obiekt
	 */
	public void draw(Graphics g) {
		
		if(drawChilds) {
			Iterator<Drawable> it = childs.iterator();
			
			while(it.hasNext()) {
				it.next().draw(g);
			}
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
	
	/**
	 * Getter wartości priorytetu rysowania
	 * @return <code> priority </code>
	 */
	public int getPriority() {
		return priority;
	}

	@Override
	/**
	 * Funkcja sluzy do ustawienia priorytetu rysowania obiektow im nizszy prioirytet tym
	 * wczesniej obiekt zostanie narysowany co oznacza, ze znajdzie sie nizej i moze zostac
	 * przysloniety przez inne obiekty
	 * @param o inny obiekt typu <code> Drawable </code>
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
	
	/**
	 * Funkcja zmienia stan rysowania dzieci obiektu. 
	 * 
	 * @param visible jeżeli true to rysuje w przeciwnym wypadku ukryj dzieci 
	 */
	public void setChildVisibility(Boolean visible) {
		drawChilds = visible;
	}
	

}
