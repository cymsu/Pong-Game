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
	
	private int redScore, blueScore;
	private Font font;
	
	private Score() {
		redScore = 0;
		blueScore = 0;
		font = new Font("arial", Font.BOLD, 30);
	}
	
	public static Score getInstance() {
		if(score == null) {
			if(score == null) {
				score = new Score();
			}
		}
		return score;
	}
	
	public void addRedScore() {
		redScore++;
	}
	
	public void addBlueScore() {
		blueScore++;
	}
	
	public void resetScore() {
		redScore = blueScore = 0;
	}
	
	public void render(Graphics2D g2d) {
		String redScoreString = "" + redScore;
		String blueScoreString = "" + blueScore;
		g2d.setFont(font);
		FontMetrics metrics = g2d.getFontMetrics();
		//blue player score
		g2d.setColor(new Color(250, 130, 170));
		g2d.drawString(redScoreString, PongPanel.WIDTH / 2 - metrics.stringWidth(redScoreString) - 50, 70);
		
		//red player score
		g2d.setColor(new Color(140, 130, 250));
		g2d.drawString(blueScoreString, PongPanel.WIDTH / 2 + 50, 70);
	}
}
