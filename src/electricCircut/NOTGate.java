package electricCircut;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;


/**\
 * Bramka not zawiera jedno wejscie i jedno wyjscie 
 * <center> ~a </center>
 * plik: NOTGate.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public class NOTGate extends Gate{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NOTGate(int x, int y) {
		super(x, y);
		setId("Nand");
		
		addOutput(new ElectricNode(this, x+20, y, 5, 10));
		addInput(new ElectricNode(this, x-20, y, 5, 1));
	}

	@Override
	public void drawMe(Graphics g) {

		g.setColor(new Color(200,200,200));
		int x[] = {getX()-20, getX()+20, getX()-20};
		int y[] = {getY() - 10, getY(), getY() + 10};
		
		g.fillPolygon(x,y, 3);
		
		g.setColor(new Color(0,0,0));
		g.drawString("NOT", getX()-20, getY()-15);
	}
	
	@Override
	public void calcOutputs() {
		
		System.out.println("Ouptuts");
		
		int multiplex = 1;
		
		Iterator<ElectricNode> it = inputs();
		
		while(it.hasNext()) {
			if(it.next().state() != 1) {
				multiplex = -1;
			}
		}
		
		multiplex = -multiplex; //not na koncu
		
		Iterator<ElectricNode> out = outputs();
		while(out.hasNext()) {
			System.out.println(multiplex);
			out.next().setState(multiplex);
		}

		System.out.println(multiplex);
		
	}

}
