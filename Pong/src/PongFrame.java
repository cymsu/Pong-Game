import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */
public class PongFrame extends JFrame{
	
	private PongPanel pongPanel;
	
	public PongFrame() {
		super("Pong Game");
		init();
	}
	
	public void init() {
		initPanels();
		initFrame();
	}
	
	public void initPanels() {
		pongPanel = new PongPanel();
		add(pongPanel);
	}
	
	public void initFrame() {
		pack();
		setLocationRelativeTo(null);
		requestFocus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
