import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;

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
	
	private Player leftPlayer, rightPlayer;
	private Ball ball;
	private Menu menu;
	private Score score;
	private KeyInput keyInput;
	
	private Font fnt0, fnt1;
	
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
		initScore();
		initFonts();
		initKeyInput();
		initThread();
	}
	
	public void initPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDoubleBuffered(true);
	}
	
	public void initObjects() {
		leftPlayer = new Player(200, (HEIGHT - PLAYER_HEIGHT) / 2, PLAYER_WIDTH,
				PLAYER_HEIGHT, new Color(254, 119, 204));
		rightPlayer = new Player(WIDTH - 200 - PLAYER_WIDTH, (HEIGHT - PLAYER_HEIGHT) / 2,
				PLAYER_WIDTH, PLAYER_HEIGHT, new Color(221, 116, 75));
		ball = new Ball(leftPlayer, rightPlayer);
	}
	
	public void initMenu() {
		menu = new Menu();
		addMouseListener(menu);
	}
	
	public void initScore() {
		score = Score.getInstance();
	}
	
	public void initFonts() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("/resources/fonts/Montsterrat/Montsterrat-Regular.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("/resources/fonts/Shrikhand/Shrikhand-Regular.ttf")));
		}catch(Exception e) {
			System.out.println("Nie wczytano czcionek w PongPanel");
		}
		fnt0 = new Font("Montsterrat Regular", Font.PLAIN, 18);
		fnt1 = new Font("Shrikhand Regular", Font.PLAIN, 36);
	}
	
	public void initKeyInput() {
		setFocusable(true);
		keyInput = new KeyInput(ball, leftPlayer, rightPlayer, PLAYER_SPEED);
		addKeyListener(keyInput);
	}
	
	public void initThread() {
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	public void update() {
		if(state == STATE.Game) {
			leftPlayer.update();
			rightPlayer.update();
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
			drawBackground(g2d);
			score.render(g2d, fnt0);
			leftPlayer.render(g2d);
			rightPlayer.render(g2d);
			ball.render(g2d);
			if(!ball.isStarted()) {
				drawStrings(g2d);
			}
		}else if(state == STATE.Menu) {
			menu.render(g2d);
		}
	}
	
	public void drawBackground(Graphics2D g2d) {
		int middle = PongPanel.WIDTH / 2;
		g2d.setColor(new Color(88, 89, 91));
		for(int i = 0; i < HEIGHT / 30; i++) {
			if(i % 2 == 1)
				g2d.fillRect(middle - 1, i * 30, 2, 30);
		}
	}
	
	public void drawStrings(Graphics2D g2d) {
		String pressSpace = "press space", pressEsc = "PRESS ESC TO MENU";
		
		g2d.setFont(fnt1);
		g2d.setColor(Color.BLACK);
		FontMetrics metrics = g2d.getFontMetrics();
		g2d.drawString(pressSpace, (PongPanel.WIDTH - metrics.stringWidth(pressSpace)) / 2,
				(PongPanel.HEIGHT - metrics.getHeight()) / 2);
		
		g2d.setFont(fnt0);
		g2d.setColor(new Color(88, 89, 91));
		metrics = g2d.getFontMetrics();
		g2d.drawString(pressEsc, (PongPanel.WIDTH - metrics.stringWidth(pressEsc)) / 2, metrics.getHeight());
	}

	@Override
	public void run() {
		while(true) {
			if(running) {
				try{
					long currentTime = System.currentTimeMillis();
					update();
					repaint();
					long delta = System.currentTimeMillis() - currentTime;
					Thread.sleep(DELAY - delta);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
