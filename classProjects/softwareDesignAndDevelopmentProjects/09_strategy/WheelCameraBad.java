import java.util.Random;

public class WheelCameraBad extends RobotBad{
	
	Random rand = new Random();
	
	public WheelCameraBad(String name){
		super.setName(name);
	}
	
	@Override
	public void sense() {
		// Randomly generate a reading of an obstacle in the environment
		// It can be as close as 2 or beyond the max (thus not visible)
		int distance = rand.nextInt(50*2) + 3;
		if (distance > 50) {
		  System.out.println("(IR) No obstacles ahead");
		} else {
		  System.out.printf("(IR) Obstacle %d units ahead.%n",distance);
		}
	}
	
	@Override
	public void move(int heading, int distance) {
		System.out.printf("(Wheels) Arcing to %d degrees for %d units.%n",heading, distance);

		// Differential drive means the robot will arc as it rotates and will not get quite as far
		double radians = heading * Math.PI/180.0;
		int x = getLocation().x + (int)((double)distance*7/8 * Math.cos(radians));
		int y = getLocation().y + (int)((double)distance*7/8 * Math.sin(radians));

		// Update to the new location
		setLocation(new Point(x,y));
		System.out.println("Moved to "+getLocation());
	}
}