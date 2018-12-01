package graph;

import java.awt.Color;
import java.awt.Graphics;

public class GraphEdge extends GraphObject {
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
		if(n1 == null ||
		   n2 == null) {
			throw new IllegalArgumentException("n1 and n2 could not be null");	
		}
		
		if(n1.isNewConnectionPossible() == false ||
		   n2.isNewConnectionPossible() == false ||
		   n1 == n2) {
			throw new IllegalArgumentException("n1, n2 could not connect more edges or nodes are equals");
		}
		
		GraphEdge edge = new GraphEdge(n1, n2);
		n1.connectEdge(edge);
		n2.connectEdge(edge);
		
		return edge;
	}
	
	private GraphEdge(GraphNode n1, GraphNode n2) {
		setN1(n1);
		setN2(n2);
		id = "Krawedz";
	}

	@Override
	public void drawMe(Graphics g) {
		g.setColor(new Color(0,255,0));
		g.drawLine(n1.getX(), n1.getY(), n2.getX(), n2.getY());
	}
	
	public Boolean hasNode(GraphNode node) {
		if(node == n1 || node == n2)
			return true;
		else return false;
	}
	
	public Boolean hasNodes(GraphNode n1, GraphNode n2) {
		if(this.n1 == n1 && this.n2 == n2) {
			return true;
		} else if(this.n2 == n1 && this.n1 == n2) {
			return true;
		} else return false;
	}
	
	public GraphNode getN1() {
		return n1;
	}
	public GraphNode getN2() {
		return n2;
	}
	public void setN1(GraphNode n1) {
		this.n1 = n1;
	}
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
