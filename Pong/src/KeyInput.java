import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	private Player redPlayer, bluePlayer;
	private int speed;
	
	public KeyInput(Player redPlayer, Player bluePlayer, int speed) {
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.speed = speed;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
			redPlayer.setDeltaY(0);
		}
		
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			bluePlayer.setDeltaY(0);
		}
	}
	
}
