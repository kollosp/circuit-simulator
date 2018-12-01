package electricCircut;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import graph.GraphNode;


public class Gate extends GraphNode{

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
	
	@Override
	public String toString() {
		
		return "{super: "+ super.toString() + ", i: "+inputs.size() + ", o: "+outputs.size() + "}";
	}
	
}
