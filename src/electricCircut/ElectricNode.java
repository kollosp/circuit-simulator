package electricCircut;


import java.awt.Color;
import java.awt.Graphics;

import graph.GraphNode;

/**\
 * 
 * Klasa rozszerzajaca wierzcholek grafu o mozliwosc utrzyania stanu i 
 * funkcje przesuwania przez rodzica - klasa <code> Gate </code>
 * plik: ElectricNode.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public class ElectricNode extends GraphNode 
	implements Statable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public ElectricNode(Gate parent, int x, int y, int r, int maxConnecions) {
		super(x, y, r);
		this.parent = parent;
		setMaxConnections(maxConnecions);
	}

	@Override
	public void drawMe(Graphics g) {

		if(state == 1) 
			setColor(new Color(0,255,0));
		else
			setColor(new Color(200,200,200));
		
		super.drawMe(g);
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
	
	public Gate parent() {
		return parent;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{" + super.toString() + ", state: "+ state +"}";
	}
}
