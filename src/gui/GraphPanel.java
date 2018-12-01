package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;

import electricCircut.ElectricNode;
import electricCircut.Input;
import electricCircut.NANDGate;
import electricCircut.Output;
import electricCircut.electricCircuitGraph;
import graph.*;

public class GraphPanel extends JPanel implements
	MouseListener, KeyListener, MouseMotionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LinkedList<GraphObject> activeNodes = new LinkedList<GraphObject>();
	
	GraphNode activeNode = null; 
	
	electricCircuitGraph graph = new electricCircuitGraph();
	DebugDialog dialog;
	DragLine dragLine = new DragLine();
	
	Point lastMouseClickPosition;
	Boolean leftClicked = new Boolean(false);
	Boolean rightClicked = new Boolean(false);
	
	
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
        
        dragLine.draw(g);
        
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
		case 1: 
			
			break;
		case 2:
			
		break;
			
		//lewy klawisz 
		case 3: 
			if(hasActiveNodes()) createActiveNodePopup(e);
			else createNodePopup(e);
		break;
	
		}
		
		
		update();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastMouseClickPosition = e.getPoint();
		rightClicked = e.getButton() == 3 ? true : false;
		leftClicked = e.getButton() == 1 ? true : false; 
		
		//dopisz do aktywnych wezlow lub ustaw nowe aktywne wezly
		if(isActiveHovered(e.getPoint().x, e.getPoint().y) == false) {		
			if(e.isControlDown()) {
				//dopisz bo ctrl przycisniety
				activeNodes.addAll(hovered(e.getPoint().x, e.getPoint().y));
			}
			else {
				//ustaw nowe aktywne wezly
				activeNodes = hovered(e.getPoint().x, e.getPoint().y);
			}
			
			activeNode = hoveredNode(e.getPoint().x, e.getPoint().y);
		}
		
		drawActiveNodesOnDialog();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		//wlasnie trwa wyznaczanie nowej krawedzi
		if(e.getButton() == 3) {
			if(dragLine.visible() == true && rightClicked) {
				System.out.println("zakonoczono dodawanie krawedzi");

				tryCreateEdge(e);
				dragLine.hide();
		
			}
			
			rightClicked = false;		
		}
		
		if(e.getButton() == 1) {
			leftClicked = false;
		}
		
		update();
	}
	
	//funkcja wywolywana podczas zakonczenie przeciagania 
	//sprawdza czy mysz znajduje sie nad wezlem i jesli tak to tworzy nowa krawedz
	public void tryCreateEdge(MouseEvent e) {
		
		GraphNode node = hoveredNode(e.getPoint().x, e.getPoint().y);
		
		if(node == null || activeNode == null)
			return;
		
		try {
			graph.addEdge(GraphEdge.createEdge(activeNode, node));
		}catch(IllegalArgumentException ex) {
			System.err.println(ex.getMessage());
		}
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
	
		if(leftClicked)
			move(e);
		
		if(rightClicked) {
			dragLine.update(e.getPoint());
		}
		
		
		update();
	}

	
	
	public void move(MouseEvent e) {
		Iterator<GraphObject> it; 
		
		//jezeli sa aktywne wezly to przesun je
		if(activeNodes.size()>0) {
			it = activeNodes.iterator();			
		}
		//jezeli nie ma aktywnych wezlow to przesun cala mape
		else {
			it = graph.objects();
		}
		
		while(it.hasNext()) {
			it.next().move( - lastMouseClickPosition.x + e.getPoint().x,
						    - lastMouseClickPosition.y + e.getPoint().y);
		}
		
		//zapamietaj pozycje myszy
		lastMouseClickPosition = e.getPoint();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public GraphNode hoveredNode(int mx, int my) {
		Iterator<GraphNode> it = graph.nodes();
		
		
		while(it.hasNext()) {
			GraphNode g = it.next();
			
			if(g.hover(mx, my)) {
				return g;
			}
		}
		
		return null;
	}
	
	public LinkedList<GraphObject> hovered(int mx, int my) {
		Iterator<GraphObject> it = graph.objects();
		
		LinkedList<GraphObject> hovered = new LinkedList<GraphObject>();
		
		while(it.hasNext()) {
			GraphObject g = it.next();
			
			if(g.hover(mx, my)) {
				//dialog.addText(g.toString());
				//System.out.println(g.toString());
				hovered.add(g);
			}
		}
		
		return hovered;
	}
	
	public Boolean isActiveHovered(int mx, int my) {
		Iterator<GraphObject> it = activeNodes.iterator();
		while(it.hasNext()) {
			
			if(it.next().hover(mx, my)) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean hasActiveNodes() {
		if(activeNodes.size() > 0) return true;
		else return false;
	}
	
	public void clearActive() {
		activeNodes.clear();
	}
	
	public void addActive(GraphObject active) {
		activeNodes.add(active);
	}
	
	public void drawActiveNodesOnDialog() {		
		dialog.clearText();
		dialog.addText(graph.toString());
		Iterator<GraphObject> it = activeNodes.iterator();
		
		while(it.hasNext()) {
			dialog.addText(it.next().toString());
		}
	}
	
	public void createActiveNodePopup(MouseEvent e) {
		JPopupMenu menu = new JPopupMenu();
		
		JMenuItem remove = new JMenuItem("Usuń");
		
		menu.add(remove);
		
		menu.show(this, e.getPoint().x, e.getPoint().y);
		
		remove.addActionListener((ActionEvent event) -> {
			removeActiveObject();
		});
	}
	
	public void createNodePopup(MouseEvent e) {
		JPopupMenu menu = new JPopupMenu();
		
		JMenuItem create = new JMenuItem("Utwórz węzeł");
		JMenuItem createNAND = new JMenuItem("Utwórz bramkę NAND");
		JMenuItem createSwitch = new JMenuItem("Utwórz przełacznik");
		JMenuItem createLamp = new JMenuItem("Utwórz lampkę");
		
		menu.add(create);
		menu.add(createNAND);
		menu.add(createSwitch);
		menu.add(createLamp);
		
		menu.show(this, e.getPoint().x, e.getPoint().y);
		
		create.addActionListener((ActionEvent event) -> {
			graph.addNode(new GraphNode(e.getPoint().x, e.getPoint().y));
		});

		createNAND.addActionListener((ActionEvent event) -> {
			graph.addNode(new NANDGate(e.getPoint().x, e.getPoint().y));
		});
		
		createSwitch.addActionListener((ActionEvent event) -> {
			graph.addNode(new Input(e.getPoint().x, e.getPoint().y));
		});		

		createLamp.addActionListener((ActionEvent event) -> {
			graph.addNode(new Output(e.getPoint().x, e.getPoint().y));
		});
		
	}
	
	public void removeActiveObject() {
		Iterator<GraphObject> it = activeNodes.iterator();
		
		while(it.hasNext()) {
			GraphObject n = it.next();
			graph.removeObject(n);
			activeNodes.remove(n);
			it = activeNodes.iterator();
		}
		
		update();
	}
	
	public void update() {
		repaint();
		drawActiveNodesOnDialog();
	}	
}

class DragLine {
	private Point p1;
	private Point p2;
	private Boolean visible = new Boolean(false);
	
	public void draw(Graphics g) {
		if(visible) {
			g.setColor(new Color(0,0,255));
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
	}
	
	public void setStartPoint(Point p1) {
		this.p1 = p1;
	}
	
	public void setEndPoint(Point p2) {
		this.p2 = p2;
	}
	
	public void setVisible(Boolean v) {
		visible = v;
	}
	
	public Boolean visible() {
		return visible;
	}
	
	public void show(Point p) {
		setStartPoint(p);
		setEndPoint(p);
		setVisible(true);
	}
	
	public void update(Point p) {
		if(visible() == false) {
			show(p);
		}
		else setEndPoint(p);
	}
	
	public void hide() {
		if(visible()) {
			setVisible(false);
		}
	}
}
