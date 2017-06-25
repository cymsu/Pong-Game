import java.awt.EventQueue;

public class Pong {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				PongFrame.getInstance();
			}
		});
	}
}
