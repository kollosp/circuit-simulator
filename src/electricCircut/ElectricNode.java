package electricCircut;


import graph.GraphNode;

public class ElectricNode extends GraphNode {

	
	private Gate parent;
	
	private int state;
	
	public ElectricNode(Gate parent, int x, int y) {
		super(x, y);
		this.parent = parent;
	}
	
	public ElectricNode(Gate parent, int x, int y, int r) {
		super(x, y, r);
		this.parent = parent;
	}

	public int state() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public void clear() {
		this.state = 0;
	}
	
	public void move(int dx, int dy) {
		
		//parent.move(dx, dy);
	}
	
	public void moveByParent(int dx, int dy) {
		super.move(dx, dy);
	}
	
}
