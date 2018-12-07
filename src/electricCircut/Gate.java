package electricCircut;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import graph.GraphNode;

/**\
 * 
 * Bazowa klasa bramki logicznej. Składa sie z listy wejsc i listy wyjsc, ktore
 * sa typu <code> ElectricNode </code>. Rozrzerza klase wierzcholka o funkcje obliczajaca 
 * stan wyjsc na podstawie wejsc
 * plik: Gate.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */

public class Gate extends GraphNode
	implements Statable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LinkedList<ElectricNode> inputs = new LinkedList<ElectricNode>();
	LinkedList<ElectricNode> outputs = new LinkedList<ElectricNode>();
	
	LinkedList<ElectricNode> objects = new LinkedList<ElectricNode>();
	
	public Gate(int x, int y) {
		super(x, y);
		setMaxConnections(0);
		
	}

	public void addInput(ElectricNode input) {
		
		inputs.add(input);
		objects.add(input);
		input.setMaxConnections(1);
		addComponent(input);
	}
	
	public void addOutput(ElectricNode output) {
		outputs.add(output);
		objects.add(output);
		addComponent(output);
	}
			
	@Override
	public void move(int dx, int dy) {
		super.move(dx, dy);

		Iterator<ElectricNode> it = objects.iterator();
		while(it.hasNext()) {
			it.next().moveByParent(dx,dy);
		}
	}
	
	public Iterator<ElectricNode> objects() {
		return objects.iterator();
	}
	
	public Iterator<ElectricNode> inputs(){
		return inputs.iterator();
	}
	public Iterator<ElectricNode> outputs(){
		return outputs.iterator();
	}
	
	@Override
	public String toString() {
		
		String buf = "";
		Iterator<ElectricNode> it = objects();
		while(it.hasNext()) {
			buf = buf + it.next().toString() + "\n";
		}
		
		
		return "{super: "+ super.toString() + ", i: "+inputs.size() + ", o: "+outputs.size() + "}\n"+buf;
	}
	
	public void calcOutputs() {
		
	}
	
	public int state() {
		return -1;
	}
	
	public void setState(int state) {
	}
	
	public void clear() {
	}
	
}
