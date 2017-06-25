import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends Rectangle{
	
	private Color color;
	private ID id;
	private int deltaY;
	
	public Player(int x, int y, int width, int height, Color color, ID id) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.id = id;
		this.deltaY = 0;
	}
	
	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}
	
	public int getDeltaY() {
		return deltaY;
	}
	
	public void update() {
		y += deltaY;
		
		if(y <= 0) {
			y = 0;
		}
		
		if(y > PongPanel.HEIGHT - height) {
			y = PongPanel.HEIGHT - height;
		}
	}
	
	public void render(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fill(this);
	}
	
	public Rectangle getBounce() {
		return this;
	}
}
