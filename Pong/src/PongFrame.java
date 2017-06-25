import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;

public class PongFrame extends JFrame{
	
	private PongPanel pongPanel;
	private GameOverPanel gameOverPanel;
	private static PongFrame pongFrame = new PongFrame();
	
	private PongFrame() {
		super("Pong Game");
		init();
	}
	
	public static PongFrame getInstance() {
		return pongFrame;
	}
	
	public void init() {
		initPanels();
		initFrame();
	}
	
	public void initPanels() {
		setLayout(new BorderLayout());
		
		pongPanel = new PongPanel();
		add(pongPanel, BorderLayout.CENTER);
	}
	
	public void initFrame() {
		pack();
		setLocationRelativeTo(null);
		requestFocus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void gameOver() {
		getContentPane().remove(pongPanel);
		gameOverPanel = new GameOverPanel();
		add(gameOverPanel, BorderLayout.CENTER);
		pack();
	}
}
