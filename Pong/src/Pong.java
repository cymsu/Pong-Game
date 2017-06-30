import java.awt.EventQueue;

/**
 * 
 * @author Hubert Skrzypczak
 *	This is remake of popular old game Pong
 */
public class Pong {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new PongFrame();
			}
		});
	}
}
