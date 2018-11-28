package graph;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Graph extends Drawable{

	LinkedList<GraphNode> nodes = new LinkedList<GraphNode>();
	LinkedList<GraphEdge> edges = new LinkedList<GraphEdge>();
	
	
	
	public Graph(){
		GraphNode n1 = new GraphNode(50, 50);
		GraphNode n2 = new GraphNode(150, 50);
		GraphEdge e1 = new GraphEdge(n1, n2);

		addNode(n1);
		addNode(n2);
		addEdge(e1);
	}
	
	public void addNode(GraphNode item) {
		item.setPriority(2);
		addComponent(item);
		nodes.add(item);
	}
	
	public void addEdge(GraphEdge item) {
		
		item.setPriority(1);
		addComponent(item);
		edges.add(item);
	}
	
	public Iterator<GraphNode> nodes(){
		return nodes.iterator();
	}
	
	public Iterator<GraphEdge> edges(){
		return edges.iterator();
	}
	
	@Override
	public String toString() {
		return "{w: "+ Integer.toString(nodes.size()) + ", k: "+ Integer.toString(edges.size())+"}";
	}
	
}
