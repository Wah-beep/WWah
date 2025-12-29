import java.util.Random;

public class SenseIR implements SenseBehavior{
	@Override 
	public void sense() {
		Random rand = new Random();
		// Randomly generate a reading of an obstacle in the environment
		// It can be as close as 2 or beyond the max (thus not visible)
		int distance = rand.nextInt(50*2) + 3;
		if (distance > 50) {
		  System.out.println("(IR) No obstacles ahead");
		} else {
		  System.out.printf("(IR) Obstacle %d units ahead.%n",distance);
		}
	}
}