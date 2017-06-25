import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class GameOverPanel extends JPanel{
	
	public GameOverPanel() {
		setPreferredSize(new Dimension(PongPanel.WIDTH, PongPanel.HEIGHT));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
		g.drawString("GAME OVER", getWidth() / 2 - 200, getHeight() / 2);
	}
}
