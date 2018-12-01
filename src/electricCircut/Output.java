package electricCircut;

import java.awt.Color;
import java.awt.Graphics;

public class Output extends Gate{

	
	int state;
	
	public Output(int x, int y) {
		super(x, y);
		
		addInput(new ElectricNode(this, x, y+getR(), 5));
		
	}

	@Override
	public void drawMe(Graphics g) {
	
		g.setColor(new Color(0,0,0));
		g.drawLine(getX(), getY()-getR(), getX(), getY()+getR());
		
		
		switch(state) {
		case -1: g.setColor(new Color(0,0,255)); break;
		case 1: g.setColor(new Color(0,255,0)); break;
		case 0: g.setColor(new Color(200,200,200)); break;
		}

		g.fillRect(getX()-getR(), getY()-2*getR(), 2*getR(), 2*getR());
		g.drawRect(getX()-getR(), getY()-2*getR(), 2*getR(), 2*getR());
		
		
	}
	
	@Override
	public void addOutput(ElectricNode input) {
		//output w bramkach typu output nie wystepuje
	}
}
