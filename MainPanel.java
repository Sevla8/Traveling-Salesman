import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Math;


public class MainPanel extends JPanel implements ActionListener {
	public ControlPanel controlPanel;
	public InformationPanel informationPanel;
	public LinkedList<Coord> city;
	public LinkedList<Coord> path;
	private int large = 10;
	private Random random = new Random();

	public MainPanel(Coord location, Dimension size, Color background, ControlPanel controlPanel, InformationPanel informationPanel) {
		this.controlPanel = controlPanel;
		this.informationPanel = informationPanel;

		this.setBackground(background);
		this.setSize((int)size.getWidth(), (int)size.getHeight());
		this.setLocation(location.x, location.y);

		this.city = new LinkedList<Coord>();
		this.path = new LinkedList<Coord>();

		this.controlPanel.button1.addActionListener(this);
		this.controlPanel.button2.addActionListener(this);
		this.controlPanel.button3.addActionListener(this);
		this.controlPanel.button4.addActionListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (this.city != null && !this.city.isEmpty()) {
			g.setColor(Color.GRAY);
			for (Coord coord : this.city) {
				g.fillOval(coord.x*this.large, coord.y*this.large, this.large, this.large);
			}
		}

		if (this.path != null && this.path.size() > 1) {
			g.setColor(Color.BLACK);
			for (int i = 0; i < this.path.size()-1; i += 1)  
				g.drawLine(this.path.get(i).x*this.large+this.large/2, this.path.get(i).y*this.large+this.large/2, this.path.get(i+1).x*this.large+this.large/2, this.path.get(i+1).y*this.large+this.large/2);
		}
	}

	public double getPathLength() {
		double length = 0;
		if (this.path != null) {
			if (this.path.size() < 2)
				return 0;
			else {
				for (int i = 0; i < this.path.size()-1; i += 1) {
					Coord coordA = path.get(i);
					Coord coordB = path.get(i+1);
					length += Math.sqrt(Math.pow(coordA.x-coordB.x, 2) + Math.pow(coordA.y-coordB.y, 2));
				}
			}
		}
		return length;
	}

	public void generateCity(int n) {
		int height = this.getHeight()/this.large;
		int width = this.getWidth()/this.large;

		city = new LinkedList<Coord>();
		int i = 0;
		while (i != n) {
			Coord coord = new Coord(this.random.nextInt(width), this.random.nextInt(height));
			if (!city.contains(coord)) {
				city.add(coord);
				i += 1;
			}
		}

		this.city = city;
	}

	public void randomPath() {
		path = new LinkedList<Coord>();
		int size = this.city.size();

		int i = 0;
		while (i != size) {
			Coord coord = this.city.get(this.random.nextInt(size));
			if (!path.contains(coord)) {
				path.add(coord);
				i += 1; 
			}
		}

		this.path = path;
		this.informationPanel.jLabel.setText((int)this.getPathLength()+" px");
	}

	public void gluttonPath() {
		path = new LinkedList<Coord>();
		int size = this.city.size();

		LinkedList<Coord> temp = new LinkedList<Coord>();
		for (Coord coord : this.city) {
			Coord copy = new Coord(coord.x, coord.y);
			temp.add(copy);
		}

		Coord coord0 = temp.remove(this.random.nextInt(size));
		path.add(coord0);
		while (!temp.isEmpty()) {
			coord0 = temp.remove(coord0.getNearest(temp));
			path.add(coord0);
		}

		this.path = path;
		this.informationPanel.jLabel.setText((int)this.getPathLength()+" px");
	}

	public void simulatedAnnealingPath() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.controlPanel.button1 && 
			!this.informationPanel.jTextField.getText().equals("") && 
			Integer.parseInt(this.informationPanel.jTextField.getText()) <= this.getWidth()/this.large*this.getHeight()/this.large) {
			
			this.path = null;
			this.informationPanel.jLabel.setText("");
			this.generateCity(Integer.parseInt(this.informationPanel.jTextField.getText())); 
			this.repaint();
		}

		if (e.getSource() == this.controlPanel.button2 && 
			this.city != null && 
			!this.city.isEmpty()) {

			this.randomPath();
			this.repaint();
		}

		if (e.getSource() == this.controlPanel.button3 && 
			this.city != null && 
			!this.city.isEmpty()) {

			this.gluttonPath();
			this.repaint();
		}

		if (e.getSource() == this.controlPanel.button4 && 
			this.city != null && 
			!this.city.isEmpty()) {

			this.simulatedAnnealingPath();
			this.repaint();
		}		
	}
}
