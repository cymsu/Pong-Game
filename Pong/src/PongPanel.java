import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PongPanel extends JPanel implements Runnable{
	
	public static final int WIDTH = 1200, HEIGHT = 600, PLAYER_WIDTH = 15,
			PLAYER_HEIGHT = 70, PLAYER_SPEED = 2;
	private long DELAY = 5;
	
	private Player redPlayer, bluePlayer;
	private Ball ball;
	private KeyInput keyInput;
	private boolean running = true;
	
	private Thread mainThread;
	
	public PongPanel() {
		super();
		
		init();
	}
	
	public void init() {
		initPanel();
		initObjects();
		initKeyInput();
		initThread();
	}
	
	public void initPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDoubleBuffered(true);
		setFocusable(true);
		requestFocus();
	}
	
	public void initObjects() {
		redPlayer = new Player(200, (HEIGHT - PLAYER_HEIGHT) / 2, PLAYER_WIDTH, PLAYER_HEIGHT, new Color(250, 130, 170), ID.red);
		bluePlayer = new Player(WIDTH - 200 - PLAYER_WIDTH, (HEIGHT - PLAYER_HEIGHT) / 2, PLAYER_WIDTH, PLAYER_HEIGHT, new Color(140, 130, 250), ID.blue);
		ball = new Ball(100, 110, redPlayer, bluePlayer);
		
	}
	
	public void initKeyInput() {
		setFocusable(true);
		keyInput = new KeyInput(redPlayer, bluePlayer, PLAYER_SPEED);
		addKeyListener(keyInput);
	}
	
	public void initThread() {
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	public void update() {
		redPlayer.update();
		bluePlayer.update();
		if(ball.update()) {
			running = false;
			PongFrame.getInstance().gameOver();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		redPlayer.render(g2d);
		bluePlayer.render(g2d);
		ball.render(g2d);
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		while(running) {
			try{
				update();
				repaint();
				Thread.sleep(DELAY);
			} catch(Exception e) {
				
			}
		}
	}
}
