import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */
public class KeyInput implements KeyListener {

	private Player leftPlayer, rightPlayer;
	private Ball ball;
	private Score score;
	private int speed;
	
	public KeyInput(Ball ball, Player leftPlayer, Player rightPlayer, int speed) {
		this.ball = ball;
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;
		score = Score.getInstance();
		this.speed = speed;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(PongPanel.state == STATE.Game) {
			if(key == KeyEvent.VK_W) {
				leftPlayer.setDeltaY(-speed);
			}
			if(key == KeyEvent.VK_S) {
				leftPlayer.setDeltaY(speed);
			}
			
			if(key == KeyEvent.VK_UP) {
				rightPlayer.setDeltaY(-speed);
			}
			if(key == KeyEvent.VK_DOWN) {
				rightPlayer.setDeltaY(speed);
			}
			
			if(!ball.isStarted()) {
				if(key == KeyEvent.VK_SPACE) {
					ball.startBall();
				}
				if(key == KeyEvent.VK_ESCAPE) {
					PongPanel.state = STATE.Menu;
					score.resetScore();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(PongPanel.state == STATE.Game) {
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
				leftPlayer.setDeltaY(0);
			}
			
			if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
				rightPlayer.setDeltaY(0);
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
