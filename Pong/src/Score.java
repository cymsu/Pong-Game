import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * 
 * @author Hubert Skrzypczak
 *
 */
public class Score {
	
	private static Score score = null;
	
	private int leftScore, rightScore;
	
	private Score() {
		leftScore = 0;
		rightScore = 0;
	}
	
	public static Score getInstance() {
		if(score == null) {
			if(score == null) {
				score = new Score();
			}
		}
		return score;
	}
	
	public void addLeftScore() {
		leftScore++;
	}
	
	public void addRightScore() {
		rightScore++;
	}
	
	public void resetScore() {
		leftScore = rightScore = 0;
	}
	
	public void render(Graphics2D g2d, Font font) {
		String leftScoreString = "" + leftScore;
		String rightScoreString = "" + rightScore;
		g2d.setFont(font);
		g2d.setColor(new Color(88, 89, 91));
		FontMetrics metrics = g2d.getFontMetrics();
		
		//left player score
		g2d.drawString(leftScoreString, PongPanel.WIDTH / 2 - metrics.stringWidth(leftScoreString) - 80, 70);
		
		//right player score
		g2d.drawString(rightScoreString, PongPanel.WIDTH / 2 + 80, 70);
	}
}
