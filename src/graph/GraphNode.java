package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class GraphNode extends GraphObject{
	private int x;
	private int y;
	
	private String id;
	
	LinkedList<GraphEdge> edges = new LinkedList<GraphEdge>();
	private int maxConnections = 2;
	
	
	public GraphNode(int x, int y){
		setX(x);
		setY(y);
		id = "Wezel";
	}
	
	
	@Override
	public void drawMe(Graphics g) {
	
		g.setColor(new Color(255,0,0));
		g.fillOval(x-10, y-10, 20, 20);
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
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
		if(mx > x - 10 && mx < x + 10 &&
				my > y - 10 && my < y + 10)
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{id:" +id+", x:" + Integer.toString(getX()) + ", y: "+ Integer.toString(getY()) +
			", k: " + Integer.toString(edges.size()) + "}";
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
}
