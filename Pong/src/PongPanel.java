import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */
public class PongPanel extends JPanel implements Runnable{
	
	public static final int WIDTH = 1300, HEIGHT = 600, PLAYER_WIDTH = 15,
			PLAYER_HEIGHT = 70, PLAYER_SPEED = 2;
	private long DELAY = 5;
	
	private Player redPlayer, bluePlayer;
	private Ball ball;
	private Menu menu;
	private KeyInput keyInput;
	private MouseInput mouseInput;
	private boolean running = true;
	
	private Thread mainThread;
	
	public static STATE state = STATE.Menu;
	
	public PongPanel() {
		super();
		init();
	}
	
	public void init() {
		initPanel();
		initObjects();
		initMenu();
		initKeyInput();
		initThread();
	}
	
	public void initPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDoubleBuffered(true);
		requestFocus();
	}
	
	public void initObjects() {
		redPlayer = new Player(200, (HEIGHT - PLAYER_HEIGHT) / 2, PLAYER_WIDTH,
				PLAYER_HEIGHT, new Color(250, 130, 170));
		bluePlayer = new Player(WIDTH - 200 - PLAYER_WIDTH, (HEIGHT - PLAYER_HEIGHT) / 2,
				PLAYER_WIDTH, PLAYER_HEIGHT, new Color(140, 130, 250));
		ball = new Ball(redPlayer, bluePlayer);
	}
	
	public void initMenu() {
		mouseInput = new MouseInput();
		addMouseListener(mouseInput);
		menu = new Menu();
	}
	
	public void initKeyInput() {
		setFocusable(true);
		keyInput = new KeyInput(ball, redPlayer, bluePlayer, PLAYER_SPEED);
		addKeyListener(keyInput);
	}
	
	public void initThread() {
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	public void update() {
		if(state == STATE.Game) {
			redPlayer.update();
			bluePlayer.update();
			if(ball.update()) {
				initObjects();
				initKeyInput();
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		if(state == STATE.Game) {
			redPlayer.render(g2d);
			bluePlayer.render(g2d);
			ball.render(g2d);
		}else if(state == STATE.Menu) {
			menu.render(g2d);
		}
		
	}

	@Override
	public void run() {
		while(running) {
			try{
				long currentTime = System.currentTimeMillis();
				update();
				repaint();
				long delta = System.currentTimeMillis() - currentTime;
				Thread.sleep(DELAY - delta);
			} catch(Exception e) {
				
			}
		}
	}
}
