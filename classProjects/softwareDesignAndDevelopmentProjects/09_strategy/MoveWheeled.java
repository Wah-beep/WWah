public class MoveWheeled implements MoveBehavior{
	@Override
	public Point move(int heading, int distance) {
		System.out.printf("(Wheels) Arcing to %d degrees for %d units.%n",heading, distance);

		// Differential drive means the robot will arc as it rotates and will not get quite as far
		double radians = heading * Math.PI/180.0;
		int x = (int)((double)distance*7/8 * Math.cos(radians));
		int y = (int)((double)distance*7/8 * Math.sin(radians));

		// Update to the new location
		return new Point(x,y);
	}
}