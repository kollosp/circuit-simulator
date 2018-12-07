package electricCircut;

import java.util.Iterator;
import java.util.LinkedList;


import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;
import graph.GraphObject;


/**\
 * 
 * Klasa definiujaca graf obwodu elektrycznego. Rozbudowuje klase grafu o 
 * oblicznie wartosci "pradu" na wszystkich wezlach i krawedziach 
 * grafu
 * 
 * plik: ElectricCircuitGraph.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public class ElectricCircuitGraph extends Graph{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LinkedList<SwitchGate> inputs = new LinkedList<SwitchGate>();
	
	
	public void addInput(SwitchGate input) {
		inputs.add(input);
		addNode(input);
	}
	
	public void addNode(Gate item) {
		Iterator<ElectricNode> it = item.objects();

		while(it.hasNext()) {
			super.addNode(it.next());
		}
		super.addNode(item);
	}
	
	public void removeObject(GraphObject item) {
		if(item instanceof Gate) {
			Iterator<ElectricNode> it =((Gate) item).objects();
		
			while(it.hasNext()) {
				removeObject(it.next());
			}
		}
		
		super.removeObject(item);
	}
	
	/**
	 * Funkcja przetwarza graf w celu ustawianie odpowiednich wartosci stanow na wszystkich bramkach 
	 * i krawedziach
	 */
	public void calcGraph() {
		
		clearGraph();
		
		Iterator<SwitchGate> it = inputs.iterator();
		
		while(it.hasNext()) {
			SwitchGate input = it.next();
			//input.calcOutputs();
			calcFor(input);
		}
	}
	
	public void calcFor(Gate root) {
		root.calcOutputs();
		
		Iterator<ElectricNode> it = root.outputs();
		
		while(it.hasNext()) {
			ElectricNode node = it.next();
			
			Iterator<GraphEdge> e = node.edges();
			while(e.hasNext()) {
				GraphEdge edge = e.next();
				
				((ElectricEdge)edge).setState(node.state());
				ElectricNode nextNode = (ElectricNode)edge.getOpposite(node);
				nextNode.setState(node.state());
				calcFor(nextNode.parent());
				
			}
		}
	}
	
	public void clearGraph() {
		Iterator<GraphNode> it = nodes();
		
		while(it.hasNext()) {
			((Statable)it.next()).clear();
		}
	}
	
}
