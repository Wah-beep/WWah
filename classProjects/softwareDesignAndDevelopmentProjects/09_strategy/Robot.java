public abstract class Robot {
    protected SenseBehavior senseBehavior;
    protected MoveBehavior moveBehavior;
    protected Point location; 
    protected String name; // Changed to protected for subclass access

    public Robot(String name) {
        this.name = name;
        this.location = new Point(0, 0);
    }

    public void move(int heading, int distance) {
        Point change = moveBehavior.move(heading, distance); // ✅ Get movement result
        location.x += change.x; // ✅ Update x-coordinate
        location.y += change.y; // ✅ Update y-coordinate
        System.out.println("Moved to " + location);
    }

    public void sense() {
        senseBehavior.sense();
    }

    public void describe() {
        System.out.println("I am " + name + " located at " + location);
    }
    
    public String getName() {
        return name;
    }
}