package graph;

import java.awt.Color;
import java.awt.Graphics;

public class GraphEdge extends Drawable {
	GraphNode n1;
	GraphNode n2;
	
	private String id;
	
	public GraphEdge(GraphNode n1, GraphNode n2) {
		setN1(n1);
		setN2(n2);
		id = "Krawedz";
	}

	@Override
	public void drawMe(Graphics g) {
		g.setColor(new Color(0,255,0));
		g.drawLine(n1.getX(), n1.getY(), n2.getX(), n2.getY());
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
}
