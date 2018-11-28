package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;
import graph.*;

public class GraphPanel extends JPanel implements
	MouseListener, KeyListener, MouseMotionListener{

	
	LinkedList<GraphNode> activeNodes = new LinkedList<GraphNode>();
	
	Graph graph = new Graph();
	DebugDialog dialog;
	Point lastMouseClickPosition;
	
	public GraphPanel(DebugDialog dialog) {
		
		this.dialog = dialog;
		
		setBackground(new Color(244, 244, 244));

		setBounds(0, 0, 600, 400);
		setFocusable(true);
		requestFocus();
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        graph.draw(g);
    }
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//sprawdz ktore wezly sa najechane przez myszke
		
		
		switch(e.getButton()){
		//prawy klawisz myszy
		case 1: System.out.println("3 klawisz"); 
			break;
		//lewy klawisz 
		case 3: graph.addNode(new GraphNode(e.getPoint().x, e.getPoint().y));
			break;
		}
		
		
		update();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("pressed");
		lastMouseClickPosition = e.getPoint();
		
		if(isActiveHovered(e.getPoint().x, e.getPoint().y) == false) {		
			if(e.isControlDown()) {
				activeNodes.addAll(hovered(e.getPoint().x, e.getPoint().y));
			}
			else {
				activeNodes = hovered(e.getPoint().x, e.getPoint().y);
			}
		}
		
		drawActiveNodesOnDialog();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseDragged(MouseEvent e) {
	
		Iterator<GraphNode> it; 
		
		//jezeli sa aktywne wezly to przesun je
		if(activeNodes.size()>0) {
			it = activeNodes.iterator();			
		}
		//jezeli nie ma aktywnych wezlow to przesun cala mape
		else {
			it = graph.nodes();
		}
		
		while(it.hasNext()) {
			it.next().move( - lastMouseClickPosition.x + e.getPoint().x,
						    - lastMouseClickPosition.y + e.getPoint().y);
		}
		
		//zapamietaj pozycje myszy
		lastMouseClickPosition = e.getPoint();
		
		update();
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public LinkedList<GraphNode> hovered(int mx, int my) {
		Iterator<GraphNode> it = graph.nodes();
		
		LinkedList<GraphNode> hovered = new LinkedList<GraphNode>();
		
		while(it.hasNext()) {
			GraphNode g = it.next();
			
			if(g.hover(mx, my)) {
				//dialog.addText(g.toString());
				//System.out.println(g.toString());
				hovered.add(g);
			}
		}
		
		return hovered;
	}
	
	public Boolean isActiveHovered(int mx, int my) {
		Iterator<GraphNode> it = activeNodes.iterator();
		while(it.hasNext()) {
			
			if(it.next().hover(mx, my)) {
				return true;
			}
		}
		return false;
	}
	
	public void clearActive() {
		activeNodes.clear();
	}
	
	public void addActive(GraphNode active) {
		activeNodes.add(active);
	}
	
	public void drawActiveNodesOnDialog() {		
		dialog.clearText();
		dialog.addText(graph.toString());
		Iterator<GraphNode> it = activeNodes.iterator();
		
		while(it.hasNext()) {
			dialog.addText(it.next().toString());
		}
	}
	
	public void update() {
		repaint();
		drawActiveNodesOnDialog();
	}
	
}
