public class Wheeled extends Robot {
    public Wheeled(String name, SenseBehavior strategy) {
        super(name);
        this.moveBehavior = new MoveWheeled(); 
        this.senseBehavior = strategy; 
    }
}