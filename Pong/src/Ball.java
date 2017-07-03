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
	private Player leftPlayer, rightPlayer;
	private Score score;
	private double dx, dy;
	private Vector vector;
	private Random rand;
	
	public Ball(Player leftPlayer, Player rightPlayer) {
		rand = new Random();
		this.leftPlayer = leftPlayer;
		this.rightPlayer = rightPlayer;
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
			if(getBounce().intersects(leftPlayer.getBounce())) {
				angle = y - leftPlayer.getCenterY();
				vector.setAngle(angle + 90);
				dx = vector.getH() * speed;
				dy = vector.getV() * speed;
				x = leftPlayer.getX() + PongPanel.PLAYER_WIDTH;
				speed += 0.1;
			}
			if(getBounce().intersects(rightPlayer.getBounce())) {
				angle = rightPlayer.getCenterY() - y;
				vector.setAngle(angle - 90);
				dx = vector.getH() * speed;
				dy = vector.getV() * speed;
				x = rightPlayer.getX() - radius;
				speed += 0.1;
			}
			
	}
	
	public Rectangle getBounce() {
		return new Rectangle((int)x, (int)y, (int)radius, (int)radius);
	}
	
	public boolean isOut() {
		if(x < -radius) {
			score.addRightScore();
			return true;
		}
		if(x > PongPanel.WIDTH) {
			score.addLeftScore();
			return true;
		}
		return false;
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.fill(new Ellipse2D.Double(x, y, radius, radius));
	}
	
	
	
	
}
