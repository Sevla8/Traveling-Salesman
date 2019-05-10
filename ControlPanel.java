import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

public class ControlPanel extends JPanel {
	public JButton button1;
	public JButton button2;
	public JButton button3;
	public JButton button4;

	public ControlPanel(Coord location, Dimension size, Color background) {
		this.button1 = new JButton("cas 1");
		this.button2 = new JButton("cas 2");
		this.button3 = new JButton("cas 3");
		this.button4 = new JButton("cas 4");

		this.add(this.button1);
		this.add(this.button2);
		this.add(this.button3);
		this.add(this.button4);
		
		this.setBackground(background);
		this.setSize((int)size.getWidth(), (int)size.getHeight());
		this.setLocation(location.x, location.y);
	}
}
