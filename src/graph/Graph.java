package graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph extends Drawable{

	LinkedList<GraphNode> nodes = new LinkedList<GraphNode>();
	LinkedList<GraphEdge> edges = new LinkedList<GraphEdge>();
	
	LinkedList<GraphObject> objects = new LinkedList<GraphObject>();
	
	
	/**
	 * Przeładowana funkcja dodaniu komponentu do grafu. Wektor drawable i wektor
	 * obiektow grafu
	 * @param item
	 */
	public void addComponent(GraphObject item) {
		super.addComponent(item);
		objects.add(item);
	}
	
	/**
	 * Przeładowana funkcja usuniecia komponentu, obsluguje dodanie 
	 * obiektu do wektora drawable i do wektora obiektow grafu
	 * @param item
	 */
	public void removeComponent(GraphObject item) {
		super.removeComponent(item);
		objects.remove(item);
	}
	
	/**
	 * Funkcja służy do usuwania obiektow nieznanego typu. Funkcja weryfikuje
	 * czy ma do czynienia z wezlem czy z krawedzia i w zaleznosci od obiektu 
	 * wywoluje inny algorytm usuwania
	 * @param item obiekt grafu
	 */
	public void removeObject(GraphObject item) {
		Iterator<GraphNode> nit = nodes.iterator();
		while(nit.hasNext()) {
			if(nit.next() == item){
				//usun item ktory jest wezlem
				System.out.println("Usuwanie wezla");
				removeNode((GraphNode) item);
				return;
			}
		}
		
		Iterator<GraphEdge> eit = edges.iterator();
		while(eit.hasNext()) {
			if(eit.next() == item){
				//usun item ktory jest krawedzia
				removeEdge((GraphEdge) item);
				System.out.println("Usuwanie krawedzi");
				return;
			}
		}
	}
	
	public Graph(){
		GraphNode n1 = new GraphNode(50, 50);
		GraphNode n2 = new GraphNode(150, 50);
		GraphEdge e1 = GraphEdge.createEdge(n1, n2);

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
		if(hasEdge(item)) return;
		
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
	
	public Iterator<GraphObject> objects(){
		return objects.iterator();
	}
	
	public void move(int dx, int dy) {
		Iterator<GraphNode> it = nodes.iterator();
		
		while(it.hasNext()) {
			it.next().move(dx, dy);
		}
	}
	
	
	/**
	 * Funkcja usuwa wezeł a takze wszystkie krawedzie ktore do tego wezla naleza
	 * @param node
	 */
	public void removeNode(GraphNode node) {
	
		System.out.println("removeNode");
		
		Iterator<GraphEdge> it = edges.iterator();
		while(it.hasNext()) {
			GraphEdge edge = it.next();
			if(edge.hasNode(node)) {
			
				removeEdge(edge);
				it = edges.iterator();
			}
		}
		
		this.removeComponent(node);
		nodes.remove(node);
	}
	
	public void removeEdge(GraphEdge edge) {
		removeComponent(edge);
		edges.remove(edge);
		edge.clear();
	}
	
	//funkcja sprawdza czy krawedz pomiedzy dwoma wezlami juz istnieje
	public Boolean hasEdge(GraphEdge edge) {
		Iterator<GraphEdge> it = edges.iterator();
		
		while(it.hasNext()) {
			if(it.next().hasNodes(edge.getN1(), edge.getN2()))
				return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "{w: "+ Integer.toString(nodes.size()) + ", k: "+ Integer.toString(edges.size())+"}";
	}
	
}
