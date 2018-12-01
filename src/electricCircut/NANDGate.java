package electricCircut;

import java.awt.Graphics;

import graph.GraphNode;

public class NANDGate extends Gate{

	public NANDGate(int x, int y) {
		super(x, y);
		setId("Nand");
		
		addOutput(new ElectricNode(this, x+20, y, 5));
		addInput(new ElectricNode(this, x-20, y-10, 5));
		addInput(new ElectricNode(this, x-20, y+10, 5));
	}

	@Override
	public void drawMe(Graphics g) {
		g.drawRect(getX()-20, getY() - 10, 40, 20);
	}
	

}
