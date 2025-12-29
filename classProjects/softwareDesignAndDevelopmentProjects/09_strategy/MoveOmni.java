public class MoveOmni implements MoveBehavior{
	@Override
	public Point move(int heading, int distance) {
		System.out.printf("(Omni) Heading %d degrees for %d units.%n",heading, distance);

		// Calculate the new location based on the requested movement
		double radians = heading * Math.PI/180.0;
		int x =  (int)(distance * Math.cos(radians));
		int y = (int)(distance * Math.sin(radians));
		
		return new Point(x,y);
	}
}