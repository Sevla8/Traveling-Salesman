import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

public class InformationPanel extends JPanel {
	public JLabel jLabel;
	public JTextField jTextField;

	public InformationPanel(Coord location, Dimension size, Color background) {
		this.jLabel = new JLabel();
		this.jTextField = new JTextField(5);

		this.add(this.jLabel);
		this.add(this.jTextField);

		this.setBackground(background);
		this.setSize((int)size.getWidth(), (int)size.getHeight());
		this.setLocation(location.x, location.y);
	}
}
