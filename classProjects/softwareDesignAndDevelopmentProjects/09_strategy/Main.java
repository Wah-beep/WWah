public class Main {
  public static void main(String[] args) {

    System.out.println("^^^^^^^^  ROBOTS  ^^^^^^^");

    RobotBad rosie = new RobotBad("Rosie");

    OmniCameraBad eva = new OmniCameraBad("Eva");

    OmniTouchBad roomba = new OmniTouchBad("Hoover");

    WheelCameraBad walle  = new WheelCameraBad("Wall-E");
	
	Robot chakong = new OmniTouch("chakong");
	

	Robot wah = new WheelCamera("wah");

	Robot daredevil = new Wheeled("daredevil", new SenseTouch());

    RobotBad[] robots = {rosie, eva , roomba, walle};
    for (RobotBad robot : robots) {
      System.out.println("----------------------------");
      robot.describe();
      robot.move(45,10);
      robot.sense();
    }
	
	System.out.println("----------------------------");
        System.out.println("Testing robot: " + chakong.getName());
        chakong.describe();  
        chakong.move(45, 10);  
        chakong.sense();  
		
	System.out.println("----------------------------");
        System.out.println("Testing robot: " + wah.getName());
        wah.describe();  
        wah.move(45, 10);  
        wah.sense();  
    
	System.out.println("----------------------------");
        System.out.println("Testing robot: " + daredevil.getName());
        daredevil.describe();
        daredevil.move(45, 10);
        daredevil.sense();
	
	
	Reconfigurable ghostRider = new Reconfigurable("GhostRider", new SenseIR(), new MoveWheeled());
	System.out.println("----------------------------");
	ghostRider.describe();
	ghostRider.move(45,10);
	ghostRider.sense();
	System.out.println("Changing sense to touch, changing move to omni");
	ghostRider.setMoveBehavior(new MoveOmni());
	ghostRider.setSenseBehavior(new SenseTouch());
	ghostRider.describe();
	ghostRider.move(45,10);
	ghostRider.sense();
	
  }
}
