import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPanel extends JPanel implements ActionListener {
	public ControlPanel controlPanel;
	public InformationPanel informationPanel;
	public LinkedList<Coord> ville;
	public LinkedList<Coord> path;
	public int large = 10;
	public Random random = new Random();

	public MainPanel(Coord location, Dimension size, Color background, ControlPanel controlPanel, InformationPanel informationPanel) {
		this.controlPanel = controlPanel;
		this.informationPanel = informationPanel;

		this.setBackground(background);
		this.setSize((int)size.getWidth(), (int)size.getHeight());
		this.setLocation(location.x, location.y);

		this.ville = new LinkedList<Coord>();
		this.path = new LinkedList<Coord>();

		this.controlPanel.button1.addActionListener(this);
		this.controlPanel.button2.addActionListener(this);
		this.controlPanel.button3.addActionListener(this);
		this.controlPanel.button4.addActionListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (this.ville != null && !this.ville.isEmpty()) {
			g.setColor(Color.RED);
			for (Coord coord : this.ville) {
				g.fillOval(coord.x*this.large, coord.y*this.large, this.large, this.large);
			}
		}

		if (this.path != null && this.path.size() > 1) {
			g.setColor(Color.BLACK);
			for (int i = 0; i < this.path.size()-1; i += 1)  
				g.drawLine(this.path.get(i).x*this.large+this.large/2, this.path.get(i).y*this.large+this.large/2, this.path.get(i+1).x*this.large+this.large/2, this.path.get(i+1).y*this.large+this.large/2);
		}
	}

	public void generateCity(int n) {
		int height = this.getHeight()/this.large;
		int width = this.getWidth()/this.large;

		ville = new LinkedList<Coord>();
		int i = 0;
		while (i != n) {
			Coord coord = new Coord(this.random.nextInt(width), this.random.nextInt(height));
			if (!ville.contains(coord)) {
				ville.add(coord);
				i += 1;
			}
		}

		this.ville = ville;
	}

	public void findRandomPath() {
		path = new LinkedList<Coord>();
		int size = this.ville.size();

		int i = 0;
		while (i != size) {
			Coord coord = this.ville.get(this.random.nextInt(size));
			if (!path.contains(coord)) {
				path.add(coord);
				i += 1; 
			}
		}

		this.path = path;
	}

	public void glouton() {
		path = new LinkedList<Coord>();
		int size = this.ville.size();

		Coord coord = this.ville.get(this.random.nextInt(size));
		path.add(coord);
		for (int i = 0; i < this.ville.size()-1; i += 1) {
			Coord coord = this.ville.get(i);
			if (!path.contains(coord)) {
				path.add(coord);
				i += 1; 
			}
		}

		this.path = path;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.controlPanel.button1 && !this.informationPanel.jTextField.getText().equals("")) {
			this.path = null;
			this.generateCity(Integer.parseInt(this.informationPanel.jTextField.getText())); 
			this.repaint();
		}

		else if (e.getSource() == this.controlPanel.button2 && this.ville != null && !this.ville.isEmpty()) {
			this.findRandomPath();
			this.repaint();
		}

		else if (e.getSource() == this.controlPanel.button3 && this.ville != null && !this.ville.isEmpty()) {
			this.glouton();
			this.repaint();
		}
	}
}
