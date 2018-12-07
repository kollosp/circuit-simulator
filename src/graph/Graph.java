package graph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

import electricCircut.ElectricCircuitGraph;


/**\
 * 
 * Klasa definiująca graf
 * 
 * plik: Graph.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public class Graph extends Drawable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Lista wierzchołkow grafu
	 */
	LinkedList<GraphNode> nodes = new LinkedList<GraphNode>();
	
	/**
	 * Lista krawedzi grafu
	 */
	LinkedList<GraphEdge> edges = new LinkedList<GraphEdge>();
	
	/**
	 * Lista zawierajaca wszystkie obiety w jedym miejscu
	 */
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

	}
	
	/**
	 * Funkcja pozwala dodać nowy wezel do grafu.  
	 * @param item nowy wezel
	 */
	public void addNode(GraphNode item) {
		item.setPriority(2);
		addComponent(item);
		nodes.add(item);
	}
	
	/**
	 * Funkcja pozwala dodac nowa krawedz do grafu
	 * @param item
	 */
	public void addEdge(GraphEdge item) {
		if(hasEdge(item)) return;
		
		item.setPriority(1);
		addComponent(item);
		edges.add(item);
	}
	
	/**
	 * Iterator = dostep do wierzcholkow grafu
	 * @return iterator po liscie wierzcholkow grafu
	 */
	public Iterator<GraphNode> nodes(){
		return nodes.iterator();
	}
	/**
	 * Iterator = dostep do krawedzi grafu
	 * @return iterator po liscie krawedzi grafu
	 */
	public Iterator<GraphEdge> edges(){
		return edges.iterator();
	}
	
	/**
	 * Iterator = dostep do wszystkich obiektow grafu
	 * @return iterator po liscie obiektow grafu
	 */
	public Iterator<GraphObject> objects(){
		return objects.iterator();
	}
	
	/**
	 * Funkcja pozwala przesunąć wzystkie obiety o wspolrzedne x i y
	 * @param dx przesuniecie w osi x wszyskitch elementow grafu
	 * @param dy przesuniecie w osi y wszyskitch elementow grafu 
	 */
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
				edge.getOpposite(node).removeEdge(edge);
				it = edges.iterator();
			}
		}
		
		this.removeComponent(node);
		nodes.remove(node);
	}
	
	
	/**
	 * Funkcja usuwa krawedz z grafu. 
	 * 
	 * Usuwa krawedz z obiektow grafu a takze rozpina polaczenie krawedzi z wierzchołkami
	 * do których zostala polaczona
	 * @param edge krawedz ktora ma zostac usunieta
	 */
	public void removeEdge(GraphEdge edge) {
		removeComponent(edge);
		edges.remove(edge);
		edge.getN1().removeEdge(edge);
		edge.getN2().removeEdge(edge);
		edge.clear();
	}
	
	/**
	 * funkcja sprawdza czy krawedz pomiedzy dwoma wezlami juz istnieje
	 */
	public Boolean hasEdge(GraphEdge edge) {
		Iterator<GraphEdge> it = edges.iterator();
		
		while(it.hasNext()) {
			if(it.next().hasNodes(edge.getN1(), edge.getN2()))
				return true;
		}
		return false;
	}
	
	@Override
	/**
	 * Przeładowana funkcja toString 
	 * @return tekstowa reprezentacja obiektu
	 */
	public String toString() {
		return "{w: "+ Integer.toString(nodes.size()) + ", k: "+ Integer.toString(edges.size())+"}";
	}
	
	/**
	 * Funkcja zapisuje obiekt do pliku binarnego pod wskazana lokalizacja.
	 * @param path scizka i nazwa pliku zapisu
	 */
	public void save(String path) {
		if(path == null || path.equals("")) return;
		
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(path);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in path");
	   } catch (IOException i) {
	         i.printStackTrace();
	   }
	}
	
	/**
	 * Funckja ladujaca z pliku graf
	 * @param path sciezka i nazwa pliku ktory ma zostac wczytany
	 * @return instancja wczytanego grafu
	 */
	static public ElectricCircuitGraph load(String path) {

		ElectricCircuitGraph ret = new ElectricCircuitGraph();
		
		if(path == null || path.equals("")) return ret;
		
		try {
	         FileInputStream fileIn = new FileInputStream(path);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         ret = (ElectricCircuitGraph) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return null;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return null;
	      }
		
		return ret;
	}
	
	
}
