package electricCircut;

import java.util.Iterator;

import graph.Graph;

public class electricCircuitGraph extends Graph{

	
	public void addNode(Gate item) {
		Iterator<ElectricNode> it = item.objects();

		while(it.hasNext()) {
			super.addNode(it.next());
		}
		super.addNode(item);
	}
}
