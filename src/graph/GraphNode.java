package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;


/**\
 * 
 * Klasa definiująca wierzcholek grafu. Wierzcholek opisuje sie przez jego promien i wspolrzedne
 * na ktorych ma byc narysowany
 * 
 * plik: GraphNode.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public class GraphNode extends GraphObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Wspolrzedna x wierzcholka
	 */
	private int x;
	
	/**
	 * Wspolrzedna y wierzcholka
	 */
	private int y;
	
	/**
	 * promien wierzcholka
	 */
	private int r = 10;
	
	/**
	 * kolor wierzcholka
	 */
	private Color color = new Color(0,0,0);
	
	/**
	 * Potecjalna nazwa wierzcholka - nie wykorzystane
	 */
	private String id;
	
	/**
	 * Wektor krawedzi polaczonych do danego wierzcholka. Suzy do kontroli 
	 * ilości polaczen i do rozlaczania w przypadku usuwania
	 */
	LinkedList<GraphEdge> edges = new LinkedList<GraphEdge>();
	
	/**
	 * Maksymalna ilosc polaczen do weirzcholka w jednym czasie
	 */
	private int maxConnections = 2;
	
	
	/**
	 * Konstruktor ustawia wspolrzedne gdzie ma sie pojawic wezel
	 * @param x xowa wspolrzedna
	 * @param y yowa wspolrzedna
	 */
	public GraphNode(int x, int y){
		setX(x);
		setY(y);
		id = "Wezel";
	}
	
	/**
	 * Konstruktor ustawiajacy pozycje i rozmiar 
	 * @param x 
	 * @param y
	 * @param r
	 */
	public GraphNode(int x, int y, int r){
		setX(x);
		setY(y);
		setR(r);
		id = "Wezel";
	}
	
	
	@Override
	public void drawMe(Graphics g) {
	
		g.setColor(color);
		g.fillOval(x-r, y-r, 2*r, 2*r);
		
	}
	
	/**
	 * Getter wielkosci
	 * @return wielkosc <code> r </code>
	 */
	public int getR() {
		return r;
	}
	
	/**
	 * Setter wielkosci
	 * @param r nowy rozmiar wezla
	 */
	public void setR(int r) {
		this.r = r;
	}
	/**
	 * Getter pozycji x
	 * @return Pozycja <code> x </code>
	 */
	public int getX() {
		return x;
	}
	/**
	 * Getter pozycji y
	 * @return Pozycja <code> y </code>
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setter pozycji x
	 * @param x nowa pozycja
	 */
	public void setX(int x) {
		this.x=x;
	}
	
	/**
	 * Setter pozycji y
	 * @param y nowa pozycja
	 */
	public void setY(int y) {
		this.y=y;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
	/**
	 * Funkcja ustawiajaca kolor jaki ma wypelnic wezel
	 * @param color nowy kolor 
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Funkcja ustawia pozycje obiektu na x,y
	 * @param x nowa x-owa pozycja obiektu
	 * @param y nowa y-owa pozycja obiektu
	 */
	public void setPostion(int x, int y) {
		setX(x);
		setY(y);
	}
	
	/**
	 * Funkcja przesuwa (tzn dodaje do aktualniej pozycji obiektu) 
	 * obiekt o dx pixeli w osi x i dy pikseli w osi y.
	 * 
	 * @param dx przesuniecie w osi x obiektu
	 * @param dy przesuniecie w osi y obiektu
	 */
	public void move(int dx, int dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	/**
	 * Funckja sprawdza czy kursor znajduje sie ponad obiektem
	 * @param mx x-owa wspolrzedna pozycji myszy
	 * @param my y-owa wspolrzedna pozycji myszy
	 * @return true jezeli mysz znajduje sie nad obiektem
	 */
	public Boolean hover(int mx, int my) {
		if(mx > x - r && mx < x + r &&
				my > y - r && my < y + r)
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "{id:" +id+", x:" + Integer.toString(getX()) + ", y: "+ Integer.toString(getY()) +
			", k: " + Integer.toString(edges.size()) + ", kmax: " + maxConnections + "}";
	}
	
	/**
	 * Funkcja dodaje nowa krawedz do wezla jezeli jest to mozliwe
	 * @param edge nowa krawedz
	 */
	public void connectEdge(GraphEdge edge) {
		if(edges.size() < maxConnections) {
			edges.add(edge);
		}
	}
	
	/**
	 * Zwraca iteraztor po wszystkich krawedziach polaczonych z wierzcholkiem
	 * @return iterator po <code> edges </code>
	 */
	public Iterator<GraphEdge> edges(){
		return edges.iterator();
	}
	
	/**
	 * Funkcja usuwajaca krawdz z wierzcholka
	 * @param edge krawedz ktora ma zostac usunieta
	 */
	public void removeEdge(GraphEdge edge) {
		edges.remove(edge);
	}
	
	/**
	 * Funkcja sprawdza czy polaczenie nowej krawedzi jest mozliwe.
	 * 
	 * Połączenie jest możliwe <=> rozmiar wektora krawędzi jest nie wiekszy niz
	 * makasymalna ilosc polaczen minus 1
	 * @return true jezeli mozna dodac nowa krawedz do wezla
	 */
	public Boolean isNewConnectionPossible() {
		return edges.size() < maxConnections;
	}
	
	/**
	 * Funkcja ustawiajaca maksymalna ilosc mozliwych polaczen
	 * @param maxConnecions
	 */
	public void setMaxConnections(int maxConnecions) {
		this.maxConnections = maxConnecions;
	}
}
