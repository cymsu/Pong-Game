import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */

public class Ball {
	
	private double x, y, radius, speed, angle;
	private Player redPlayer, bluePlayer;
	private Score score;
	private double dx, dy;
	private Vector vector;
	private Random rand;
	
	public Ball(Player redPlayer, Player bluePlayer) {
		rand = new Random();
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		score = Score.getInstance();
		this.speed = 0;
		this.radius = 15;
		this.x = (PongPanel.WIDTH - radius) / 2;
		this.y = (PongPanel.HEIGHT - radius) / 2;
	}
	
	public boolean isStarted() {
		if(speed == 0) return false;
		else return true;
	}
	
	public void startBall() {
		speed = 2;
		angle = rand.nextInt(120) + 30;
		if(rand.nextInt(2) == 0) {
			angle *= -1;
		}
		vector = new Vector(angle);
		dx = vector.getH() * speed;
		dy = vector.getV() * speed;
	}
	
	public boolean update() {
		x += dx;
		y += dy;
		
		if(y <= 0) {
			y = 0;
			dy = -dy;
		}
		
		if(y > PongPanel.HEIGHT - radius) {
			y = PongPanel.HEIGHT - radius;
			dy = -dy;
		}
		
		collision();
		
		return isOut();
	}
	
	public void collision() {
			if(getBounce().intersects(redPlayer.getBounce())) {
				angle = y - redPlayer.getCenterY();
				vector.setAngle(angle + 90);
				dx = vector.getH() * speed;
				dy = vector.getV() * speed;
				x = redPlayer.getX() + PongPanel.PLAYER_WIDTH;
				speed += 0.1;
			}
			if(getBounce().intersects(bluePlayer.getBounce())) {
				angle = bluePlayer.getCenterY() - y;
				vector.setAngle(angle - 90);
				dx = vector.getH() * speed;
				dy = vector.getV() * speed;
				x = bluePlayer.getX() - radius;
				speed += 0.1;
			}
			
	}
	
	public Rectangle getBounce() {
		return new Rectangle((int)x, (int)y, (int)radius, (int)radius);
	}
	
	public boolean isOut() {
		if(x < -radius) {
			score.addBlueScore();
			return true;
		}
		if(x > PongPanel.WIDTH) {
			score.addRedScore();
			return true;
		}
		return false;
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.DARK_GRAY);
		g2d.fill(new Ellipse2D.Double(x, y, radius, radius));
		
		if(speed == 0) {
			drawStrings(g2d);
		}
	}
	
	public void drawStrings(Graphics2D g2d) {
		String pressSpace = "PRESS SPACE", pressEsc = "PRESS ESC TO MENU";
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		g2d.setFont(font);
		g2d.setColor(new Color(240, 30, 30));
		
		FontMetrics metrics = g2d.getFontMetrics();
		
		g2d.drawString(pressSpace, (PongPanel.WIDTH - metrics.stringWidth(pressSpace)) / 2,
				(PongPanel.HEIGHT - metrics.getHeight()) / 2);
		
		
		font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
		g2d.setFont(font);
		metrics = g2d.getFontMetrics();
		
		g2d.drawString(pressEsc, (PongPanel.WIDTH - metrics.stringWidth(pressEsc)) / 2, metrics.getHeight());
	}
	
	
}
