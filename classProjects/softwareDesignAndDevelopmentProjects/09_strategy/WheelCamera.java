public class WheelCamera extends Robot {
    public WheelCamera(String name) {
        super(name);
        this.moveBehavior = new MoveWheeled(); 
        this.senseBehavior = new SenseCamera(); 
    }
}