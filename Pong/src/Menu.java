import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */
public class Menu implements MouseListener{
	
	private Rectangle2D playButton, exitButton;
	private String title, play, exit;
	private Font mainFont, itemFont;
	
	private BufferedImage bgImage;
	
	public Menu() {
		playButton = new Rectangle2D.Double(PongPanel.WIDTH / 2 - 100, 330, 200,60);
		exitButton = new Rectangle2D.Double(PongPanel.WIDTH / 2 - 100, 400, 200, 60);
		title = "PONG";
		play = "play";
		exit = "exit";
		try {
			bgImage = ImageIO.read(this.getClass().getResource("menu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initFonts();
	}
	
	public void initFonts() {
		try {
            GraphicsEnvironment ge = 
                GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Montserrat/Montserrat-Light.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Shrikhand/Shrikhand-Regular.ttf")));
        } catch (IOException|FontFormatException e) {
            System.out.print("Nie wczytano czcionek w Menu");
        }
		mainFont = new Font("Shrikhand Regular", Font.PLAIN, 110);
		itemFont = new Font("Montserrat Light", Font.PLAIN, 48);
	}
	
	public void render(Graphics2D g2d) {
		g2d.drawImage(bgImage, 0, 0, null);
		drawButtons(g2d);
		drawStrings(g2d);
	}
	
	public void drawButtons(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fill(playButton);
		g2d.fill(exitButton);
	}
	
	public void drawStrings(Graphics2D g2d) {
		FontMetrics metrics = g2d.getFontMetrics(mainFont);
		g2d.setFont(mainFont);
		g2d.setColor(Color.black);
		g2d.drawString(title, (PongPanel.WIDTH - metrics.stringWidth(title)) / 2, 250);
		
		metrics = g2d.getFontMetrics(itemFont);
		g2d.setFont(itemFont);
		g2d.setColor(Color.WHITE);
		g2d.drawString(play, (PongPanel.WIDTH - metrics.stringWidth(play)) / 2, 
				(int)(playButton.getY() + playButton.getHeight() - metrics.getHeight() / 4));
		g2d.drawString(exit, (PongPanel.WIDTH - metrics.stringWidth(exit)) / 2,
				(int)(exitButton.getY() + exitButton.getHeight() - metrics.getHeight() / 4));
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(PongPanel.state == STATE.Menu) {
			if(x >= playButton.getX() && x < playButton.getX() + playButton.getWidth()) {
				
				if(y >= playButton.getY() && y <= playButton.getY() + playButton.getHeight()) {
					PongPanel.state = STATE.Game;
				}
				else if(y >= exitButton.getY() && y <= exitButton.getY() + exitButton.getHeight()) {
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
