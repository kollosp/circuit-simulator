package graph;

import java.awt.Color;
import java.awt.Graphics;

public class GraphNode extends Drawable{
	private int x;
	private int y;
	
	private String id;
	
	public GraphNode(int x, int y){
		setX(x);
		setY(y);
		id = "Wezel";
	}
	
	
	@Override
	public void drawMe(Graphics g) {
	
		g.setColor(new Color(255,0,0));
		g.fillOval(x-10, y-10, 20, 20);
		
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public void setPostion(int x, int y) {
		setX(x);
		setY(y);
	}
	public void move(int dx, int dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	/**
	 * Funckja sprawdza czy kursor znajduje sie ponad obiektem
	 * @param mx x-owa wspolrzedna pozycji myszy
	 * @param my y-owa wspolrzedna pozycji myszy
	 * @return true jezeli mysz znajduje sie nad obiektem
	 */
	public Boolean hover(int mx, int my) {
		if(mx > x - 10 && mx < x + 10 &&
				my > y - 10 && my < y + 10)
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{id:" +id+", x:" + Integer.toString(getX()) + ", y: "+ Integer.toString(getY()) +"}";
	}
}
