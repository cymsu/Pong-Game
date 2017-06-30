import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */
public class Menu{
	
	private Rectangle2D playButton, exitButton;
	private String title, play, exit;
	private Font mainFont, itemFont;
	private Color titleColor, buttonColor;
	
	public Menu() {
		playButton = new Rectangle2D.Double(PongPanel.WIDTH / 2 - 100, 240, 200, 50);
		exitButton = new Rectangle2D.Double(PongPanel.WIDTH / 2 - 100, 300, 200, 50);
		title = "Pong";
		play = "Play";
		exit = "Exit";
		titleColor = new Color(250, 80, 80);
		buttonColor = new Color(80, 80, 250);
		mainFont = new Font(Font.DIALOG, Font.BOLD, 100);
		itemFont = new Font("arial", Font.BOLD, 40);
	}
	
	public void render(Graphics2D g2d) {
		drawButtons(g2d);
		drawStrings(g2d);
	}
	
	public void drawButtons(Graphics2D g2d) {
		g2d.setColor(buttonColor);
		g2d.fill(playButton);
		g2d.fill(exitButton);
	}
	
	public void drawStrings(Graphics2D g2d) {
		FontMetrics metrics = g2d.getFontMetrics(mainFont);
		g2d.setFont(mainFont);
		g2d.setColor(titleColor);
		g2d.drawString(title, (PongPanel.WIDTH - metrics.stringWidth(title)) / 2, 150);
		
		metrics = g2d.getFontMetrics(itemFont);
		g2d.setFont(itemFont);
		g2d.setColor(Color.WHITE);
		g2d.drawString(play, (PongPanel.WIDTH - metrics.stringWidth(play)) / 2, 280);
		g2d.drawString(exit, (PongPanel.WIDTH - metrics.stringWidth(exit)) / 2, 340);
	}
}
