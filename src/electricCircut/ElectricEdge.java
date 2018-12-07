package electricCircut;

import java.awt.Color;
import java.awt.Graphics;

import graph.GraphEdge;
import graph.GraphNode;


/**\
 * Klasa rozszerza obiekt krawedzi grafu o interface Statable
 * umozliwiajacy zapamietanie stanu - pradu
 * 
 * plik: ElectricEdge.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public class ElectricEdge extends GraphEdge 
	implements Statable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int state = 1;
	
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
	public static ElectricEdge createEdge(GraphNode n1, GraphNode n2) {
		
		if(isEdgePossible(n1, n2) == false ) {
			throw new IllegalArgumentException("n1, n2 could not connect more edges or nodes are equals");
		}
		
		ElectricEdge edge = new ElectricEdge(n1, n2);
		n1.connectEdge(edge);
		n2.connectEdge(edge);
		
		return edge;
	}
	
	protected ElectricEdge(GraphNode n1, GraphNode n2) {
		super(n1, n2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void drawMe(Graphics g) {

		if(state == 1) 
			g.setColor(new Color(0,255,0));
		else
			g.setColor(new Color(200,200,200));

		g.drawLine(getN1().getX(), getN1().getY(), getN2().getX(), getN2().getY());
	}
	
	public int state() {
		return state;
	}
	
	public void setState(int state) {
		if(state!=0) {
			this.state = state;
		}
	}
	
	public void clear() {
		state = 0;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{" + super.toString()+", state: "+ state + "}";
	}
}
