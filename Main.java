import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

public class Main {
	public static void main(String[] args) {

		ControlPanel controlPanel = new ControlPanel(new Coord(0, 0), new Dimension(700, 100), Color.BLUE);
		InformationPanel informationPanel = new InformationPanel(new Coord(0, 700), new Dimension(700, 100), Color.RED);
		MainPanel mainPanel = new MainPanel(new Coord(0, 100), new Dimension(700, 600), Color.GREEN, controlPanel, informationPanel);

		MyFrame frame = new MyFrame(mainPanel, controlPanel, informationPanel);
		frame.run();
	}
}
