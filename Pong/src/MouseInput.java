import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */
public class MouseInput implements MouseListener{
	/*
	 * playButton = new Rectangle2D.Double(PongPanel.WIDTH / 2 - 100, 240, 200, 50);
	 *	exitButton = new Rectangle2D.Double(PongPanel.WIDTH / 2 - 100, 300, 200, 50);
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(PongPanel.state == STATE.Menu) {
			if(x >= PongPanel.WIDTH / 2 - 100 && x < PongPanel.WIDTH / 2 + 100) {
				
				//play button
				if(y >= 240 && y <= 290) {
					PongPanel.state = STATE.Game;
				}
				else if(y >= 300 && y <= 350) {
					System.exit(0);
				}
				
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
