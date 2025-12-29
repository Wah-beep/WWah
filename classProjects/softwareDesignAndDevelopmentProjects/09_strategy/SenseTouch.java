import java.util.Random;

public class SenseTouch implements SenseBehavior{
	@Override
	public void sense() {
		Random rand = new Random();
		// Obstacle is known only when tsensor in physical contact with obstacle.
		// Randomly generate this signal (1 in 10 chance that there is an obstacle)
		int spinTheWheel = rand.nextInt(10);
		if (spinTheWheel == 1) {
		  System.out.println("(Touch) Hitting obstacle right now. STOP!");
		} else {
		  System.out.println("(Touch) Not in contact with obstacle.");
		}
	}
}