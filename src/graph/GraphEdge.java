package graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;



/**\
 * 
 * Klasa definiująca krawdz grafu. Aby narysowac krawedz potrzebne sa dwa wezly n1 i n2,
 * Krawedz jest rysowana pomiedzy nimi
 * 
 * plik: GraphEdge.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public class GraphEdge extends GraphObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GraphNode n1;
	GraphNode n2;
	
	private final int maxPointDistance = 5;
	
	private String id;
	
	/**
	 * Funkcja tworzaca nowa krawedz
	 * 
	 * Funkcja tworzy nowa krawedz jezeli 
	 * <ul>
	 *  <li> wezly n1 i n2 moga przyjac wiecej krawdzi</li>
	 *  <li> wezly n1 i n2 sa innymi wezlami n1 != n2</li>
	 * </ul>
	 * 
	 * 
	 * @param n1 jeden z wezlow krawedzi
	 * @param n2 jeden z wezlow krawedzi
	 * @return nowy obiekt krawedzi jezeli wszytko ok, w przeciwnym wypadku null
	 */
	public static GraphEdge createEdge(GraphNode n1, GraphNode n2) {
		
		if(isEdgePossible(n1, n2) == false ) {
			throw new IllegalArgumentException("n1, n2 could not connect more edges or nodes are equals");
		}
		
		GraphEdge edge = new GraphEdge(n1, n2);
		n1.connectEdge(edge);
		n2.connectEdge(edge);
		
		return edge;
	}
	
	/**
	 * Funkcja sprawdza czy istnieje mozliwosc utworzenia krawedzi pomiedzy dwoma punktami.
	 * Warunki:
	 * <ul>
	 *  <li> wezly n1 i n2 moga przyjac wiecej krawdzi</li>
	 *  <li> wezly n1 i n2 sa innymi wezlami n1 != n2</li>
	 * </ul>

	 * @param n1 wezel 1
	 * @param n2 wezel 2
	 * @return true jezeli mozna utowrzyc nowy wezel
	 */
	protected static Boolean isEdgePossible(GraphNode n1, GraphNode n2) {
		if(n1 == null ||
		   n2 == null) {
			return false; 
		}
		
		if(n1.isNewConnectionPossible() == false ||
		   n2.isNewConnectionPossible() == false ||
		   n1 == n2) {
			return false; 
		}
		
		return true;
	}
	
	
	/**
	 * Konstrukor grafu, niezbedne jest podanie dwoch weirzcholkow. 
	 * Funcja jest chroniona aby nie mozna bylo utowrzyc krawedzi bez kontroli warunkow z funkji
	 * <code>isEdgePossible</code>
	 * @param n1
	 * @param n2
	 */
	protected GraphEdge(GraphNode n1, GraphNode n2) {
		setN1(n1);
		setN2(n2);
		id = "Krawedz";
	}

	/**
	 * Funkcja zwraca przeciwny wierzcholek do podanego
	 * @param n jeden z wierzcholkow krawedzi
	 * @return drugi wierzcholek krawedzi. Jezeli n nie nalezy do krawedzi to null
	 */
	public GraphNode getOpposite(GraphNode n) {
		if(n1 == n) {
			return n2;
		}else if(n2 == n){
			return n1;
		}
		else return null;
	}
	
	@Override
	public void drawMe(Graphics g) {

		Graphics2D canvas = (Graphics2D)g;
		canvas.setStroke(new BasicStroke(2));
		
		g.setColor(new Color(0,0,0));
		g.drawLine(n1.getX(), n1.getY(), n2.getX(), n2.getY());
	}
	
	/**
	 * Funkcja sprawdza czy podany wierzcholek nalezy do krawedzi
	 * @param node wierzcholek ktory nalezy zwerfikowac
	 * @return true jezeli wierzcholek nalezy do grafu
	 */
	public Boolean hasNode(GraphNode node) {
		if(node == n1 || node == n2)
			return true;
		else return false;
	}
	
	/**
	 * Funkcja sprawdza czy dwa wierzcholki buduja krawedz
	 * @param n1 jeden z wierzcholkow
	 * @param n2 drugi z wierzcholkow
	 * @return true jezeli n1 i n2 naleza do krawedzi
	 */
	public Boolean hasNodes(GraphNode n1, GraphNode n2) {
		if(this.n1 == n1 && this.n2 == n2) {
			return true;
		} else if(this.n2 == n1 && this.n1 == n2) {
			return true;
		} else return false;
	}
	
	/**
	 * Funkcja zwraca wierzcholek
	 * @return n1
	 */
	public GraphNode getN1() {
		return n1;
	}
	/**
	 * Funkcja zwraca wierzcholek
	 * @return n2
	 */
	public GraphNode getN2() {
		return n2;
	}
	/**
	 * Funkcja ustawia wierzcholek
	 * @param n1 wierzcholek ktry ma zostac ustaiony
	 */
	public void setN1(GraphNode n1) {
		this.n1 = n1;
	}
	/**
	 * Funkcja ustawia wierzcholek
	 */
	public void setN2(GraphNode n2) {
		this.n2 = n2;
	}
	
	@Override
	public String toString() {
		return "{id: " + id+ ", n1:" + n1.toString() + ", n2: "+ n2.toString()+"}";
	}
	
	/**
	 * Funkcja usuwa krawedz z wierzcholkow do ktorych jest przymocowana.
	 * Wywołuje <code>n1.removeEdge(...)</code> i <code>n2.removeEdge(...) </code>
	 */
	public void clear() {
		n1.removeEdge(this);
		n2.removeEdge(this);
	}
	
	/**
	 * Funkcja sprawdza czy podany punkt o wspolrzednych 
	 * <center>P(mx,my)</center> 
	 * lezy nad obiektem
	 * @param mx xowa wspolrzedna sprawdzanego punktu
	 * @param my yowa wspolrzedna sprawdzanego punktu
	 * @return true jezeli podany punkt lezy nad obiektem
	 */
	public Boolean hover(int mx, int my) {
		float a = (float)(n1.getY() - n2.getY()) / (n1.getX() - n2.getX());
		float b = n1.getY() - n1.getX() * a;
	
		float d = (float) (Math.abs(a * mx - my + b) / Math.sqrt(a*a +1));

		System.out.println(a + "x + "+b+", d: "+d);
		
		if(d < maxPointDistance) {
			if(mx > Math.min(n1.getX(), n2.getX()) - maxPointDistance &&
			   mx < Math.max(n1.getX(), n2.getX()) + maxPointDistance &&
			   my > Math.min(n1.getY(), n2.getY()) - maxPointDistance &&
			   my < Math.max(n1.getY(), n2.getY()) + maxPointDistance)  {
				return true;
			}
		}

		
		return false;
		
	}
}
