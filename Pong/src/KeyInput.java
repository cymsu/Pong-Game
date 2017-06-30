import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */
public class KeyInput implements KeyListener {

	private Player redPlayer, bluePlayer;
	private Ball ball;
	private int speed;
	
	public KeyInput(Ball ball, Player redPlayer, Player bluePlayer, int speed) {
		this.ball = ball;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.speed = speed;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(PongPanel.state == STATE.Game) {
			if(key == KeyEvent.VK_W) {
				redPlayer.setDeltaY(-speed);
			}
			if(key == KeyEvent.VK_S) {
				redPlayer.setDeltaY(speed);
			}
			
			if(key == KeyEvent.VK_UP) {
				bluePlayer.setDeltaY(-speed);
			}
			if(key == KeyEvent.VK_DOWN) {
				bluePlayer.setDeltaY(speed);
			}
			
			if(!ball.isStarted()) {
				if(key == KeyEvent.VK_SPACE) {
					ball.startBall();
				}
				if(key == KeyEvent.VK_ESCAPE) {
					PongPanel.state = STATE.Menu;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(PongPanel.state == STATE.Game) {
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
				redPlayer.setDeltaY(0);
			}
			
			if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
				bluePlayer.setDeltaY(0);
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
