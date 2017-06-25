import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Ball {
	
	private double x, y, radius, speed, angle;
	private Player redPlayer, bluePlayer;
	private double dx, dy;
	private Vector vector;
	
	public Ball(double x, double y, Player redPlayer, Player bluePlayer) {
		this.x = x;
		this.y = y;
		this.redPlayer = redPlayer;
		this.bluePlayer = bluePlayer;
		this.speed = 1.5;
		this.angle = 60;
		vector = new Vector(angle);
		this.dx = vector.getH() * speed;
		this.dy = vector.getV() * speed;
		this.radius = 15;
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
		return (x < -radius || x > PongPanel.WIDTH);
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(Color.DARK_GRAY);
		g2d.fill(new Ellipse2D.Double(x, y, radius, radius));
	}
	
	
}
