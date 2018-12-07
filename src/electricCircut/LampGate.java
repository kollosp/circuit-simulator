package electricCircut;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;


/**\
 * Podstawowa bramka zawierajaca jedno wejscie i zero wyjsc. Wyswietla stan wejscia
 * plik: LampGate.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public class LampGate extends Gate{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int state;
	
	public LampGate(int x, int y) {
		super(x, y);
		
		addInput(new ElectricNode(this, x, y+getR(), 5, 1));
	}

	@Override
	public void drawMe(Graphics g) {
	
		updateState();
		
		g.setColor(new Color(0,0,0));
		g.drawLine(getX(), getY()-getR(), getX(), getY()+getR());
		
		
		if(state == -1)
			g.setColor(new Color(200,200,200)); 
		else
			 g.setColor(new Color(0,255,0));
		
		g.fillRect(getX()-getR(), getY()-2*getR(), 2*getR(), 2*getR());
		g.drawRect(getX()-getR(), getY()-2*getR(), 2*getR(), 2*getR());	
		
		
	}
	
	@Override
	public void addOutput(ElectricNode input) {
		//output w bramkach typu output nie wystepuje
	}
	
	public void updateState() {
		Iterator<ElectricNode> it= inputs();
		while(it.hasNext()) {
			state = it.next().state();
		}
	}
}
