package electricCircut;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Iterator;

/**\
 * Podstawowa bramka wejściowa układu cyfrowego, przełacznik pomiedzy stanem wysokim a niskim.
 * Zawiera jedno wyscie ktore zalezy od zadanego stanu przez uzytkownika
 * plik: SwitchGate.java
 * data: 7.12.18
 * @author Paweł Parczyk
 * 
 *
 */
public class SwitchGate extends Gate{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int state = 0; //0 nie ustawiony, 1 - wysoki -1 = niski
	
	public SwitchGate(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		
		addOutput(new ElectricNode(this, x+getR(), y, 5));
	}

	@Override
	public void addInput(ElectricNode input) {
		//funkcja nie moze nic robic, inputy nie maja wejsc
	}

	@Override
	public void drawMe(Graphics g) {
	
		if(state == 1) 
			g.setColor(new Color(0,255,0));
		else
			g.setColor(new Color(200,200,200));
		
		
		
		g.fillRect(getX()-getR(), getY()-getR(), 2*getR(), 2*getR());

		g.setColor(new Color(0,0,0));
		g.drawRect(getX()-getR(), getY()-getR(), 2*getR(), 2*getR());
		
	}
	
	public void setState(int state) {
		if(state == -1 || state == 1)
				this.state = state;
	}
	
	public int state() {
		return state;
	}
	
	@Override
	public void onClick(MouseEvent e) {
		if(state == 0) state = 1;
		else state = -state;
	}
	
	@Override
	public void calcOutputs() {
	
		Iterator<ElectricNode> it = outputs();
		while(it.hasNext()) {
			it.next().setState(state);
		}
	}
}
